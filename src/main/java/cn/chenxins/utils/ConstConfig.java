package cn.chenxins.utils;

public class ConstConfig {

    /**
     * 存储当前登录用户的对象系列
     */
    public static final String CURRENT_USER_TOKEN = "CURRENT_USER_TOKEN";

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 28;

    /**
     * refresh_token有效期（小时）
     */
    public static final int RETOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";


    public static String SECRET_KEY="abcedefighijklmn12345678";



}
