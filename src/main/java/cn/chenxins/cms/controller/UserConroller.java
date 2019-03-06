package cn.chenxins.cms.controller;

import cn.chenxins.authorization.annotation.AdminRequired;
import cn.chenxins.authorization.annotation.LoggerReg;
import cn.chenxins.authorization.annotation.LoginRequired;
import cn.chenxins.authorization.annotation.RefreshTokenRequired;
import cn.chenxins.authorization.manager.TokenManager;
import cn.chenxins.cms.model.entity.LinUser;
import cn.chenxins.cms.model.json.TokenJsonOut;
import cn.chenxins.cms.model.json.UserJsonIn;
import cn.chenxins.cms.model.json.UserJsonOut;
import cn.chenxins.cms.service.UserService;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.exception.TokenException;
import cn.chenxins.utils.ConstConfig;
import cn.chenxins.utils.JsonUtils;
import cn.chenxins.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("cms/user")
public class UserConroller {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;




    @PostMapping("login")
    public Object userLogin(@RequestBody UserJsonIn userJsonIn){

        /**
         * 校验入参
         */
        try{
            UserJsonIn.ValidRequiredLogin(userJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), userJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }

        try {
            LinUser user=userService.loginUser(userJsonIn);
            String uid=user.getId().toString();
            //创建token，并设进redis中
            String accessToken=tokenManager.createToken(uid);
            //设置refresh token进，并设进redis中
            String refreshToken=tokenManager.createReToken(accessToken);

            return  new TokenJsonOut(accessToken,refreshToken);

        }catch (TokenException te){
            return ResultJson.TokenRedisException();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

    @PostMapping("register")
    @AdminRequired
    @LoggerReg(template = "管理员新建了一个用户")
    public Object userRegister(@RequestBody UserJsonIn userJsonIn){

        /**
         * 校验入参
         */
        try{
            UserJsonIn.ValidRequiredRegister(userJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), userJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }

        try {
            userService.register(userJsonIn);
            return  ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

    @PutMapping("/")
    @LoginRequired
    public Object userUpdate(@RequestBody UserJsonIn userJsonIn, NativeWebRequest webRequest){

        /**
         * 校验入参
         */
        try{
            UserJsonIn.ValidRequiredUserUpd(userJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), userJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }

        try {
            Integer uid=(Integer)webRequest.getAttribute(ConstConfig.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
            if (uid==null){
                return ResultJson.ServerError();
            }
            userService.updateS(uid,userJsonIn);
            return  ResultJson.Sucess();
        }catch (TokenException te){
            return ResultJson.TokenRedisException();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

    @PutMapping("change_password")
    @LoginRequired
    public Object changePwd(@RequestBody UserJsonIn userJsonIn, NativeWebRequest webRequest){

        /**
         * 校验入参
         */
        try{
            UserJsonIn.ValidRequiredChangeUpd(userJsonIn);
        }catch (ParamValueException pe){
            return ResultJson.ParameterException(pe.getLocalizedMessage(), userJsonIn);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }

        try {
            Integer uid=(Integer)webRequest.getAttribute(ConstConfig.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
            if (uid==null){
                return ResultJson.ServerError();
            }
            userService.updatePwd(uid,userJsonIn);
            return  ResultJson.Sucess();
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

    @GetMapping("information")
    @LoginRequired
    public Object getInformation(NativeWebRequest webRequest){
        try {
            Integer uid=(Integer)webRequest.getAttribute(ConstConfig.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
            if (uid==null){
                return ResultJson.ServerError();
            }
            LinUser linUser=userService.getUserById(uid);
            if (linUser==null) {
                return ResultJson.BussinessException("找不到用户信息");
            }
            UserJsonOut out=new UserJsonOut(linUser);
            return JsonUtils.objectToJsonSpecial(out);  //为适应前端做特殊处理
//            return out;
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @GetMapping("refresh")
    @RefreshTokenRequired
    public Object refresh(NativeWebRequest webRequest){
        try {
            Integer uid=(Integer)webRequest.getAttribute(ConstConfig.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
            if (uid==null){
                return ResultJson.ServerError();
            }
            //创建token，并设进redis中
            String accessToken=tokenManager.createToken(uid.toString());


            return new TokenJsonOut(accessToken,"");
        }
        catch (TokenException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @GetMapping("auths")
    @LoginRequired
    public  Object getAllowedAPI(NativeWebRequest webRequest){
        try {
            Integer uid=(Integer)webRequest.getAttribute(ConstConfig.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
            if (uid==null){
                return ResultJson.ServerError();
            }
            LinUser user=userService.getUserById(uid);
            if (user==null ){
                return ResultJson.NotFound();
            }
            UserJsonOut userOut=new UserJsonOut(user);
            if (user.getGroupId()==null) {
//                return userOut;
                return  JsonUtils.objectToJsonSpecial(userOut);//为了前端做适配的
            }

            HashMap<String, List> res=userService.getAuthList(user.getGroupId());

            userOut.setAuths(res);
//            return userOut;
            return  JsonUtils.objectToJsonSpecial(userOut);//为了前端做适配的
        }
        catch (TokenException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

}
