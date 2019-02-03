package cn.chenxins.cms.controller;

import cn.chenxins.authorization.annotation.AdminRequired;
import cn.chenxins.authorization.annotation.LoggerReg;
import cn.chenxins.authorization.annotation.LoginRequired;
import cn.chenxins.authorization.annotation.RefreshTokenRequired;
import cn.chenxins.authorization.manager.TokenManager;
import cn.chenxins.cms.model.entity.LinGroup;
import cn.chenxins.cms.model.entity.LinUser;
import cn.chenxins.cms.model.json.*;
import cn.chenxins.cms.service.AdminService;
import cn.chenxins.cms.service.UserService;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.exception.TokenException;
import cn.chenxins.utils.ConstConfig;
import cn.chenxins.utils.MetaJson;
import cn.chenxins.utils.ResultJson;
import cn.chenxins.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("cms/admin")
public class AdminConroller {

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    private AdminService service;

    protected HttpServletRequest request;
    protected HttpServletResponse response;


    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }



    @GetMapping(value = "authority",name = "")
    @AdminRequired
    public Object authority()
    {
        Map<String, MetaJson> map = MetaJson.getMetaMap(applicationContext);

        HashMap<String,Object> moduleMap=new HashMap<>();
        HashMap<String,List<String>> authMap;
        List<String> uriSet;
        String module;

        for(MetaJson meta:map.values()){
            module=meta.getModule();
            if (moduleMap.get(module)==null)
            {
                authMap=new HashMap<>();
                for (MetaJson meta2:map.values())
                {
                    if (module.equals(meta2.getModule()))
                    {
                        uriSet=new ArrayList<>();
                        uriSet.add(meta2.getUri());
                        authMap.put(meta2.getAuth(),uriSet);
                    }
                }
                moduleMap.put(module,authMap);
            }
        }
        return moduleMap;

    }

    @GetMapping(value = "users",name = "")
    @AdminRequired
    public Object getInformation(@RequestParam(required = false) Integer group_id,
                                 @RequestParam(required = false) Integer page,@RequestParam(required = false) Integer count)
    {
        try {
            if (page==null) {
                page=1;
            }
            if (count==null) {
                count=10;
            }

            UserPageJsonOut userPage=service.getAllUsers(page,count,group_id);
            if (userPage==null) {
                return ResultJson.BussinessException("找不到用户信息");
            }
            return userPage;
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @PutMapping("password/{id}")
    @AdminRequired
    public Object ChangeUserPwd(@PathVariable Integer id,@RequestBody UserJsonIn userJsonIn)
    {
        /**
         * 校验入参
         */
        try{
            UserJsonIn.ValidRequiredChangeApwd(userJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), userJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }
        try {

            service.updatePwd(id,userJsonIn);
            return ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }


    }

    @DeleteMapping("{id}")
    @AdminRequired
    public Object deleteUser(@PathVariable Integer id){
        try {

            UserJsonOut user=service.adminDeleteUser(id);
            return ResultJson.DelSucess(user);
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @PutMapping("{id}")
    @AdminRequired
    public Object updateUser(@PathVariable Integer id ,@RequestBody UserJsonIn userJsonIn){
        /**
         * 校验入参
         */
        try{
            UserJsonIn.ValidRequiredAdminUpd(userJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), userJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }
        try {

            service.updateUser(id,userJsonIn);
            this.response.setStatus(201);
            return ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }


    @PutMapping("disable/{id}")
    @AdminRequired
    public Object trans2disable(@PathVariable Integer id ) {
        try {

            service.disableUser(id);
            return ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @PutMapping("active/{id}")
    @AdminRequired
    public Object trans2active(@PathVariable Integer id ) {
        try {

            service.activeUser(id);
            return ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }


    @GetMapping("groups")
    @AdminRequired
    public Object getAdminGroups( @RequestParam(required = false) Integer page,@RequestParam(required = false) Integer count)
    {
        try {
            if (page==null) {
                page=1;
            }
            if (count==null) {
                count=10;
            }

            GroupPageJsonOut groupPageJson=service.getAdminGroups(page,count);
            if (groupPageJson==null) {
                return ResultJson.BussinessException("不存在任何权限组");
            }
            return groupPageJson;
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @GetMapping("group/all")
    @AdminRequired
    public Object getAllGroups()
    {
        try {

            List<LinGroup> list=service.getAllGroups();
            if (list==null || list.size()==0) {
                return ResultJson.BussinessException("不存在任何管理组");
            }
            return list;
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @GetMapping("group/{id}")
    @AdminRequired
    public Object getGroup(@PathVariable Integer id ){
        try {
            GroupAuthJsonOut group=service.getOneGroup(id);
            if (group==null ) {
                return ResultJson.BussinessException("找不到对应的组");
            }
            return group;
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @PostMapping("group")
    @AdminRequired
    @LoggerReg(template = "管理员新建了一个权限组") //记录日志
    public Object createGroup(@RequestBody  GroupAuthJsonIn groupAuthJsonIn){

        /**
         * 校验入参
         */
        try{
            GroupAuthJsonIn.ValidRequiredCreateGroup(groupAuthJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), groupAuthJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }

        try {
            Map<String, MetaJson> map = MetaJson.getMetaMap(applicationContext);

            service.createGroup(groupAuthJsonIn,map);
            return ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

    @PutMapping("group/{id}")
    @AdminRequired
    public Object updateGroup(@RequestBody GroupAuthJsonIn groupAuthJsonIn,@PathVariable Integer id){
        /**
         * 校验入参
         */
        try{
            GroupAuthJsonIn.ValidRequiredUpdateGroup(groupAuthJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), groupAuthJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }

        try {
            service.updateGroup(groupAuthJsonIn,id);
            return ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @DeleteMapping("group/{id}")
    @AdminRequired
    public Object deleteGroup(@PathVariable Integer id){

        try {
            service.deleteGroup(id);
            return ResultJson.DelSucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @PostMapping("dispatch")
    @AdminRequired
    public Object dispatchAuth(@RequestBody AuthJsonIn authJsonIn){
        /**
         * 校验入参
         */
        try{
            AuthJsonIn.ValidRequiredPatch(authJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), authJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }


        try {
            Map<String, MetaJson> authMap = MetaJson.getMetaMap(applicationContext);
            service.patchAuth(authJsonIn,authMap);
            return ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @PostMapping("dispatch/patch")
    @AdminRequired
    public Object dispatchAuths(@RequestBody AuthJsonIn authJsonIn){
        /**
         * 校验入参
         */
        try{
            AuthJsonIn.ValidRequiredPatchs(authJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), authJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }


        try {
            Map<String, MetaJson> authMap = MetaJson.getMetaMap(applicationContext);
            service.patchAuths(authJsonIn,authMap);
            return ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

    @PostMapping("remove")
    @AdminRequired
    public Object removeAuths(@RequestBody AuthJsonIn authJsonIn)
    {
        /**
         * 校验入参
         */
        try{
            AuthJsonIn.ValidRequiredPatchs(authJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), authJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }


        try {
            service.removeAuths(authJsonIn);
            return ResultJson.DelSucess(authJsonIn);
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

}
