package cn.chenxins.cms.service;


import cn.chenxins.cms.model.entity.LinAuth;
import cn.chenxins.cms.model.entity.LinGroup;
import cn.chenxins.cms.model.entity.LinUser;
import cn.chenxins.cms.model.entity.mapper.LinAuthMapper;
import cn.chenxins.cms.model.entity.mapper.LinGroupMapper;
import cn.chenxins.cms.model.entity.mapper.LinUserMapper;
import cn.chenxins.cms.model.json.*;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.utils.DesUtils;
import cn.chenxins.utils.JdateUtils;
import cn.chenxins.utils.MetaJson;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {


    @Autowired
    private LinUserMapper linUserMapper;

    @Autowired
    private LinGroupMapper linGroupMapper;

    @Autowired
    private LinAuthMapper linAuthMapper;



    private HashMap<Integer, LinGroup> getGroupHList(Integer gid){
        HashMap<Integer, LinGroup> hashMap=new HashMap<>();
        if (gid!=null)
        {
            LinGroup linGroup=linGroupMapper.selectByPrimaryKey(gid);
            hashMap.put(gid,linGroup);
        } else {
            List<LinGroup> list=linGroupMapper.selectAll();
            LinGroup tmp;
            for(int i=0;i<list.size();i++){
                tmp=list.get(i);
                hashMap.put(tmp.getId(),tmp);
            }
        }
        return hashMap;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public UserPageJsonOut getAllUsers(Integer page,Integer count,Integer gid)throws Exception{
        // 开始分页
        PageHelper.startPage(page, count);
        Example example = new Example(LinUser.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteTime",null);
        criteria.andEqualTo("isSuper",1);
        if( gid !=null ){
            criteria.andEqualTo("groupId", gid);
        }

        example.orderBy("id").desc();


        List<LinUser> alist=linUserMapper.selectByExample(example);
        int total=linUserMapper.selectCountByExample(example);
        if (total>0 && alist!=null && alist.size()>0){
            HashMap<Integer, LinGroup> groupMap=getGroupHList(gid);
            UserJsonOut userJson;
            LinUser user;
            String groupName;
            LinGroup tmp;
            List<UserJsonOut> list=new ArrayList<>();

            for (int i=0;i<alist.size();i++){
                user=alist.get(i);
                userJson=new UserJsonOut(user);
                tmp=groupMap.get(user.getGroupId());
                if (tmp !=null){
                    groupName=tmp.getName();
                    userJson.setGroup_name(groupName);
                }
                list.add(userJson);
            }
            return new UserPageJsonOut(total,list);
        }
        return null;
    }
    public LinUser getUserById(Integer uid) throws Exception{
        Example example = new Example(LinUser.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteTime",null);
        criteria.andEqualTo("id",uid);
        return linUserMapper.selectOneByExample(example);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void updatePwd(Integer uid,UserJsonIn userJsonIn) throws BussinessErrorException,Exception {
        LinUser existUser=linUserMapper.selectByPrimaryKey(uid);
        if (existUser==null){
            throw new BussinessErrorException("您要修改密码的用户信息不存在了");
        }

        existUser.setPassword(DesUtils.GeneratePasswordHash(userJsonIn.getNew_password()));
        existUser.setUpdateTime(JdateUtils.getCurrentDate());
        linUserMapper.updateByPrimaryKey(existUser);

    }

    public UserJsonOut adminDeleteUser(Integer uid)throws BussinessErrorException,Exception {
        LinUser existUser=linUserMapper.selectByPrimaryKey(uid);
        if (existUser==null){
            throw new BussinessErrorException("您要删除用户信息不存在了");
        }

        linUserMapper.deleteByPrimaryKey(uid); //硬删除，不是假删除
        return new UserJsonOut(existUser);
    }

    private boolean isExistEMail(String email){
        LinUser user=new LinUser();
        user.setEmail(email);
        int count=linUserMapper.selectCount(user);
        return count>0;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(Integer uid,UserJsonIn userJsonIn)throws BussinessErrorException,Exception {
        LinUser user=linUserMapper.selectByPrimaryKey(uid);
        if (user==null)
        {
            throw new BussinessErrorException("用户不存在");
        }

        if (!user.getEmail().equals(userJsonIn.getEmail())&&isExistEMail(userJsonIn.getEmail())){
            throw new BussinessErrorException("该邮箱"+userJsonIn.getEmail()+"已经注册过了");
        }

        user.setEmail(userJsonIn.getEmail());
        user.setGroupId(userJsonIn.getGroup_id());
        user.setUpdateTime(JdateUtils.getCurrentDate());
        linUserMapper.updateByPrimaryKeySelective(user);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void disableUser(Integer uid)throws BussinessErrorException,Exception {

        LinUser user=linUserMapper.selectByPrimaryKey(uid);
        if (user==null){
            throw new BussinessErrorException("您要处理用户信息不存在了");
        }
        if (user.getActive()!=(short)1){
            throw new BussinessErrorException("当前用户已处于禁止状态");
        }
        user.setActive((short)2);
        user.setUpdateTime(JdateUtils.getCurrentDate());
        linUserMapper.updateByPrimaryKeySelective(user);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void activeUser(Integer uid)throws BussinessErrorException,Exception {

        LinUser user=linUserMapper.selectByPrimaryKey(uid);
        if (user==null){
            throw new BussinessErrorException("您要处理用户信息不存在了");
        }
        if (user.getActive()==(short)1){
            throw new BussinessErrorException("当前用户已处于激活状态");
        }
        user.setActive((short)1);
        user.setUpdateTime(JdateUtils.getCurrentDate());
        linUserMapper.updateByPrimaryKeySelective(user);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public GroupPageJsonOut getAdminGroups(Integer page,Integer count)throws BussinessErrorException,Exception {
        // 开始分页
        PageHelper.startPage(page, count);
        Example example = new Example(LinGroup.class);
        example.orderBy("id").desc();

        List<LinGroup> alist = linGroupMapper.selectByExample(example);
        int total = linGroupMapper.selectCountByExample(example);
        if (total==0){
            throw new BussinessErrorException("不存在任何权限组");
        }
        List<GroupAuthJsonOut> list=new ArrayList<>();
        LinGroup tmpGroup;
        GroupAuthJsonOut groupAuthJsonOut;
        List<Map.Entry<String, List>> auths;
        for (int i=0;i<alist.size();i++){
            tmpGroup=alist.get(i);
            auths=getAuthList(tmpGroup.getId());
            groupAuthJsonOut=new GroupAuthJsonOut(tmpGroup.getId(), tmpGroup.getInfo(), tmpGroup.getName(), auths);
            list.add(groupAuthJsonOut);
        }

        GroupPageJsonOut groupPageJsonOut=new GroupPageJsonOut(total,list);
        return groupPageJsonOut;
    }

    private List<Map.Entry<String, List>> getAuthList(Integer gid) throws BussinessErrorException,Exception {
        LinAuth tmp=new LinAuth();
        tmp.setGroupId(gid);
        List<LinAuth> alist=linAuthMapper.select(tmp);
        List<AuthJosnOut> inList=new ArrayList<>();

        HashMap<String,List> moduleMap=new HashMap<String,List>();
        String curModule="";
        for (int i=0; i<alist.size();i++){
            tmp=alist.get(i);
            if (!curModule.equals(tmp.getModule())){
                inList=new ArrayList<>();
                curModule=tmp.getModule();
            }
            inList.add(new AuthJosnOut(tmp.getAuth(),tmp.getModule()));
            moduleMap.put(tmp.getModule(),inList);

        }
        List<Map.Entry<String, List>> res=new ArrayList<>();
        for(Map.Entry<String, List> entry:moduleMap.entrySet()){
            res.add(entry);
        }
        return res;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<LinGroup> getAllGroups()throws BussinessErrorException,Exception {
        return  linGroupMapper.selectAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public GroupAuthJsonOut getOneGroup(Integer id) throws BussinessErrorException,Exception{
        LinGroup linGroup=linGroupMapper.selectByPrimaryKey(id);
        if (linGroup !=null){
            List<Map.Entry<String, List>> auths=getAuthList(id);
            return new GroupAuthJsonOut(linGroup.getId(),linGroup.getInfo(),linGroup.getName(),auths);
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createGroup(GroupAuthJsonIn groupAuthJsonIn,Map<String, MetaJson> map) throws BussinessErrorException,Exception{
        LinGroup linGroup=new LinGroup();
        linGroup.setName(groupAuthJsonIn.getName());
        linGroup.setInfo(groupAuthJsonIn.getInfo());
        LinGroup existGroup=linGroupMapper.selectOne(linGroup);
        if (existGroup!=null)
        {
            throw new BussinessErrorException("分组已存在，不可创建同名分组");
        }
        linGroupMapper.insert(linGroup);
        existGroup=linGroupMapper.selectOne(linGroup);
        if (existGroup==null || existGroup.getId()==null)
        {
            throw new BussinessErrorException("新增分组失败，请联系管理员");
        }
        String tmpAuth;
        MetaJson metaJson;
        LinAuth linAuth;
        for (int i=0;i<groupAuthJsonIn.getAuths().size();i++){
            tmpAuth=groupAuthJsonIn.getAuths().get(i);
            metaJson=map.get(tmpAuth);
            if (metaJson!=null){
                linAuth=new LinAuth();
                linAuth.setGroupId(existGroup.getId());
                linAuth.setModule(metaJson.getModule());
                linAuth.setAuth(metaJson.getAuth());
                linAuthMapper.insert(linAuth);
            }
        }

    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGroup(GroupAuthJsonIn groupAuthJsonIn,Integer gid) throws BussinessErrorException,Exception{

        LinGroup existGroup=linGroupMapper.selectByPrimaryKey(gid);
        if (existGroup==null)
        {
            throw new BussinessErrorException("分组不存在，更新失败");
        } else {
            existGroup.setName(groupAuthJsonIn.getName());
            existGroup.setInfo(groupAuthJsonIn.getInfo());
        }
        linGroupMapper.updateByPrimaryKey(existGroup);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteGroup(Integer gid) throws BussinessErrorException,Exception{
        LinGroup existGroup=linGroupMapper.selectByPrimaryKey(gid);
        if (existGroup==null)
        {
            throw new BussinessErrorException("分组不存在，删除失败");
        }
        LinUser user=new LinUser();
        user.setGroupId(gid);
        int count=linUserMapper.selectCount(user);
        if (count>0) {
            throw new BussinessErrorException("分组下存在用户，不可删除");
        }
        LinAuth auth=new LinAuth();
        auth.setGroupId(gid);
        linAuthMapper.delete(auth);
        linGroupMapper.deleteByPrimaryKey(gid);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void patchAuth(AuthJsonIn authJsonIn,Map<String, MetaJson> authMap) throws BussinessErrorException,Exception{
        LinAuth auth=new LinAuth();
        auth.setGroupId(authJsonIn.getGroup_id());
        auth.setAuth(authJsonIn.getAuth());
        int count=linAuthMapper.selectCount(auth);
        if (count>0){
            throw new BussinessErrorException("已有权限，不可重复添加");
        }
        MetaJson meta=authMap.get(authJsonIn.getAuth());
        if (meta==null){
            throw new BussinessErrorException("权限auth："+authJsonIn.getAuth()+"，不存在。");
        }
        auth.setModule(meta.getModule());
        linAuthMapper.insert(auth);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void patchAuths(AuthJsonIn authJsonIn,Map<String, MetaJson> authMap) throws BussinessErrorException,Exception{
        List<String> auths=authJsonIn.getAuths();
        AuthJsonIn tmp;
        for (int i=0;i<auths.size();i++){
            tmp=new AuthJsonIn();
            tmp.setAuth(auths.get(i));
            tmp.setGroup_id(authJsonIn.getGroup_id());
            patchAuth(tmp,authMap);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeAuths(AuthJsonIn authJsonIn) throws BussinessErrorException,Exception{

        Example example = new Example(LinAuth.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("groupId",authJsonIn.getGroup_id());
        criteria.andIn("auth",authJsonIn.getAuths());

        linAuthMapper.deleteByExample(example);
    }





}
