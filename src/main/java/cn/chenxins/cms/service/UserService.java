package cn.chenxins.cms.service;


import cn.chenxins.cms.model.entity.LinAuth;
import cn.chenxins.cms.model.entity.LinUser;
import cn.chenxins.cms.model.entity.mapper.LinAuthMapper;
import cn.chenxins.cms.model.entity.mapper.LinUserMapper;
import cn.chenxins.cms.model.json.AuthJosnOut;
import cn.chenxins.cms.model.json.UserJsonIn;
import cn.chenxins.cms.model.json.UserPageJsonOut;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.utils.DesUtils;
import cn.chenxins.utils.JdateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class UserService {


    @Autowired
    private LinUserMapper dbMapper;

    @Autowired
    private LinAuthMapper linAuthMapper;



    private LinUser getUserByName(String nickname) throws Exception{
        Example example = new Example(LinUser.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteTime",null);
        criteria.andEqualTo("nickname",nickname.trim());
        return dbMapper.selectOneByExample(example);

    }

    public LinUser getUserById(Integer uid) throws Exception{
        Example example = new Example(LinUser.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteTime",null);
        criteria.andEqualTo("id",uid);
        return dbMapper.selectOneByExample(example);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public LinUser loginUser(UserJsonIn userJsonIn) throws BussinessErrorException,Exception {
        LinUser user=getUserByName(userJsonIn.getNickname());
        if (user==null) {
            throw new BussinessErrorException("用户名或密码错误");
        }
        if (!DesUtils.CheckPasswordHash(user.getPassword(), userJsonIn.getPassword())) {
            throw new BussinessErrorException("用户名或密码错误");
        }
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void register(UserJsonIn userJsonIn) throws BussinessErrorException,Exception {
        LinUser existUser=getUserByName(userJsonIn.getNickname());
        if (existUser!=null){
            throw new BussinessErrorException("此名已经存在,请更换另一个用户名");
        }
        LinUser user=new LinUser(userJsonIn);
        existUser=dbMapper.selectOne(user);
        if (existUser==null || existUser.getDeleteTime()!=null){
            String encPwd=DesUtils.GeneratePasswordHash(userJsonIn.getPassword());
            user.setPassword(encPwd);
            user.setActive((short)1);
            user.setIsSuper((short)1);
            user.setCreateTime(JdateUtils.getCurrentDate());
            dbMapper.insert(user);
        } else {
            throw new BussinessErrorException("此信息已经存在,请不要重复创建");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void updateS(Integer uid,UserJsonIn userJsonIn) throws BussinessErrorException,Exception {
        LinUser existUser=getUserById(uid);
        if (existUser==null){
            throw new BussinessErrorException("您要修改的用户信息不存在了");
        }
        existUser.setEmail(userJsonIn.getEmail());
        existUser.setUpdateTime(JdateUtils.getCurrentDate());
        dbMapper.updateByPrimaryKey(existUser);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void updatePwd(Integer uid,UserJsonIn userJsonIn) throws BussinessErrorException,Exception {
        LinUser existUser=getUserById(uid);
        if (existUser==null){
            throw new BussinessErrorException("您要修改密码的用户信息不存在了");
        }
        if (!DesUtils.CheckPasswordHash(existUser.getPassword(), userJsonIn.getOld_password())) {
            throw new BussinessErrorException("原始密码错误");
        }
        existUser.setPassword(DesUtils.GeneratePasswordHash(userJsonIn.getNew_password()));
        existUser.setUpdateTime(JdateUtils.getCurrentDate());
        dbMapper.updateByPrimaryKey(existUser);

    }

    public boolean checkHasAuth(Integer gid,String auth) throws Exception{
        LinAuth tmp=new LinAuth();
        tmp.setGroupId(gid);
        tmp.setAuth(auth);
        return linAuthMapper.selectCount(tmp)==1;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public HashMap<String,List> getAuthList(Integer gid) throws BussinessErrorException,Exception {
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

        return moduleMap;


    }






}
