package cn.chenxins.cms.controller;

import cn.chenxins.authorization.annotation.AdminRequired;
import cn.chenxins.authorization.annotation.GroupRequired;
import cn.chenxins.authorization.annotation.LoginRequired;
import cn.chenxins.cms.model.entity.LinUser;
import cn.chenxins.cms.model.json.UserJsonIn;
import cn.chenxins.cms.service.UserService;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.exception.TokenException;
import cn.chenxins.utils.ConstConfig;
import cn.chenxins.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class TestConroller {



    @GetMapping(value = "/info",name="信息#查看lin的信息")
    @GroupRequired
    public Object info(){

        return " { \"msg\": \"Lin 是一套基于 Python-Flask 的一整套开箱即用的后台管理系统（CMS）。" +
                "Lin 遵循简洁、高效的原则，通过核心库加插件的方式来驱动整个系统高效的运行\" }";


    }

    @GetMapping("/")
    public Object slogan(){
            return "<style type=\"text/css\">*{ padding: 0; margin: 0; } " +
                    "div{ padding: 4px 48px;} a{color:#2E5CD5;cursor: pointer;text-decoration: none} " +
                    "a:hover{text-decoration:underline; } body{ background: #fff; " +
                    "font-family: \"Century Gothic\",\"Microsoft yahei\"; color: #333;font-size:18px;} " +
                    "h1{ font-size: 100px; font-weight: normal; margin-bottom: 12px; }" +
                    " p{ line-height: 1.6em; font-size: 42px }</style><div style=\"padding: 24px 48px;\">" +
                    "<p> Lin <br/><span style=\"font-size:30px\">心上无垢，林间有风。</span></p></div> ";
    }



}
