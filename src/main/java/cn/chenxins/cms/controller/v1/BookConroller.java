package cn.chenxins.cms.controller.v1;

import cn.chenxins.authorization.annotation.AdminRequired;
import cn.chenxins.authorization.annotation.LoginRequired;
import cn.chenxins.cms.model.entity.Book;
import cn.chenxins.cms.model.entity.LinUser;
import cn.chenxins.cms.model.json.BookJsonIn;
import cn.chenxins.cms.model.json.UserJsonIn;
import cn.chenxins.cms.service.BookService;
import cn.chenxins.cms.service.UserService;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.exception.TokenException;
import cn.chenxins.utils.ConstConfig;
import cn.chenxins.utils.ResultJson;
import cn.chenxins.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

@RestController
@EnableAutoConfiguration
@RequestMapping("v1/book")
public class BookConroller {

    @Autowired
    private BookService bookService;

    @GetMapping("{id}")
    @LoginRequired
    public Object getBookDetail(@PathVariable(required = false) Integer id) {
        try {
            if (id == null) {
                return ResultJson.ParameterException("id是必填项！", id);
            }
            return bookService.getBookDetail(id);
        }catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }

    @GetMapping("/")
    @LoginRequired
    public Object getAllBooks() {
        //取所有没有删除的书
        return bookService.getListPageS(null);

    }

    @GetMapping("/search")
    @LoginRequired
    public Object searchBook(@RequestParam (required = false) String q) {
        // 按关键词查收书籍
        if (!StringUtil.isNotBlank(q)){
            return ResultJson.ParameterException("必须传入搜索关键字",null);
        }
        return bookService.getListPageS(q);
    }

    @PostMapping("/")
    @LoginRequired
    public Object createBook(@RequestBody BookJsonIn bookJsonIn) {
        try{
            BookJsonIn.ValidRequired(bookJsonIn);
        }catch (ParamValueException pe) {
            return ResultJson.ParameterException(pe.getLocalizedMessage(),bookJsonIn);
        }

        try {
            bookService.addmodelS(bookJsonIn);
            return ResultJson.Sucess();

        }catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }catch (Exception e){
            e.printStackTrace();
           return ResultJson.ServerError();
        }
    }

    @PutMapping("{id}")
    @LoginRequired
    public Object updateBook(@PathVariable Integer id,@RequestBody BookJsonIn bookJsonIn) {
        try{
            BookJsonIn.ValidRequired(bookJsonIn);
        }catch (ParamValueException pe) {
            return ResultJson.ParameterException(pe.getLocalizedMessage(),bookJsonIn);
        }

        try {
            bookService.updModelS(id,bookJsonIn);
            return ResultJson.Sucess();

        }catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

    @DeleteMapping(value = "{id}",name="图书#删除图书")
    @LoginRequired
    public Object deleteBook(@PathVariable Integer id) {
        try {
            bookService.delModelS(id);
            return ResultJson.Sucess();

        }catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
    }


}
