package cn.chenxins.cms.controller;

import cn.chenxins.authorization.annotation.GroupRequired;
import cn.chenxins.cms.service.LogService;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.TokenException;
import cn.chenxins.utils.ResultJson;
import cn.chenxins.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@EnableAutoConfiguration
@RequestMapping("cms/log")
public class LogConroller {

    @Autowired
    private LogService logService;


    @GetMapping(value = "/",name="日志#查询所有日志")
    @GroupRequired
    public Object searchAllLog(@RequestParam(required = false) String name,@RequestParam(required = false) Date start,@RequestParam(required = false) Date end,
                               @RequestParam(required = false) Integer page,@RequestParam(required = false) Integer count){
        try{
            if (page==null) {
                page=1;
            }
            if (count==null) {
                count=10;
            }
            return logService.getAllLog(name,start,end,page,count);

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

    @GetMapping(value = "search",name="日志#搜索日志")
    @GroupRequired
    public Object searchAllLogByKey(@RequestParam(required = false) String name,@RequestParam(required = false) String start,@RequestParam(required = false) String end
            ,@RequestParam(required = false) String keyword,@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer count){

        /**
         * 校验入参
         */
        if (!StringUtil.isNotBlank(keyword)) {
            return ResultJson.ParameterException("搜索关键字不可为空",keyword);
        }
        if (page==null) {
            page=1;
        }
        if (count==null) {
            count=10;
        }

        try {
            return logService.getAllLogByKey(keyword,name,start,end,page,count);
        }
        catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

    @GetMapping(value = "users",name = "日志#查询日志记录的用户")
    @GroupRequired
    public Object getLogUser(){

        try {
            return logService.getAllLogUser();
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
