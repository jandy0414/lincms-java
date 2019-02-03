package cn.chenxins.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResultJson {

    private Integer error_code;
    private String msg;
    private Object data;

    public static ResponseEntity<ResultJson>  Sucess201(){
       return new ResponseEntity<ResultJson> (new ResultJson(0,"操作成功",null), HttpStatus.OK);
    }

    public static ResultJson Sucess(){
        return new ResultJson(0,"操作成功",null);
    }
    public static ResultJson Sucess(Object data){
        return new ResultJson(0,"操作成功",data);
    }

    public static ResultJson DelSucess(){
        return new ResultJson(0,"删除操作成功",null);
    }

    public static ResultJson DelSucess(Object data){
        return new ResultJson(0,"删除操作成功",data);
    }

    public static ResultJson ServerError(){
        return new ResultJson(9999,"系统错误，请联系开发人员！",null);
    }

    public static ResultJson ParameterError(){
        return new ResultJson(1000,"请求的参数出错了",null);
    }

    public static ResultJson ParameterException(String emsg, Object data){
        return new ResultJson(1001,emsg,data);
    }

    public static ResultJson TokenRedisException(){
        return new ResultJson(1005,"token存储redis时出错",null);
    }

    public static ResultJson BussinessException(String emsg){
        return new ResultJson(1002,emsg,null);
    }


    public static ResultJson Forbidden(String msg){
        return new ResultJson(1003,msg,null);
    }


    public static ResultJson NotFound(){
        return new ResultJson(1004,"请求内容未找到",null);
    }




    public ResultJson(Integer errCode, String msg, Object data) {
        this.error_code = errCode;
        this.msg = msg;
        this.data=data;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
