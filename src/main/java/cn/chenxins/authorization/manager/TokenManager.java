package cn.chenxins.authorization.manager;

import cn.chenxins.exception.TokenException;

/**
 * 对Token进行操作的接口
 * @author ScienJus
 * @date 2015/7/31.
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     */
    public String createToken(String uid) throws TokenException;

    /**
     * 创建一个RefreshToken
     */
    public String createReToken(String accessToken) throws TokenException;

    /**
     * 检查token是否有效
     * @param tokenKey
     * @return 是否有效
     */
    public Integer checkToken(String tokenKey)throws TokenException;


    /**
     * 更新Token
     */
    public Integer refreshCheckToken(String refreshToken) throws TokenException;



    /**
     * 清除token
     * @param tokenKey 登录用户的id
     */
    public void deleteToken(String tokenKey);

}
