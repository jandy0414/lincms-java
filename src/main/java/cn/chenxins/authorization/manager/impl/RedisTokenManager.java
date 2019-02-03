package cn.chenxins.authorization.manager.impl;


import cn.chenxins.authorization.manager.TokenManager;
import cn.chenxins.exception.TokenException;
import cn.chenxins.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 通过Redis存储和验证token的实现类
 * @see cn.chenxins.authorization.manager.TokenManager
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class RedisTokenManager implements TokenManager {


    @Autowired
    private RedisOperator redis;


    public String createToken(String uid) throws TokenException {
        //使用uuid作为源token,一步部分
        String preToken = UUID.randomUUID().toString().replace("-", "");

        String key=preToken+"."+DesUtils.md5("access_token");

        //存储到redis并设置过期时间
        redis.set(key,uid, ConstConfig.TOKEN_EXPIRES_HOUR*3600);

        return key;
    }

    /**
     * 创建一个RefreshToken
     */
    public String createReToken(String accessToken) throws TokenException {
        //使用uuid作为源token,一步部分
        String preToken = UUID.randomUUID().toString().replace("-", "");

        String key=preToken+"."+DesUtils.md5("refresh_token");


        //存储到redis并设置过期时间
        redis.set(key,accessToken, ConstConfig.RETOKEN_EXPIRES_HOUR*3600);
        return key;
    }


    public Integer checkToken(String authentication)throws TokenException {

        if (authentication == null || "".equals(authentication.trim())) {
            return null;
        }
        String tokenValue = redis.get(authentication);
        if (tokenValue == null || "".equals(tokenValue.trim())) {
            return null;
        } else {
            return Integer.parseInt(tokenValue);

        }

        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
//        redis.expire(authentication, ConstConfig.TOKEN_EXPIRES_HOUR*3600);
    }

    public Integer refreshCheckToken(String refreshToken) throws TokenException {
        String accessToken = redis.get(refreshToken);
        if (accessToken == null || "".equals(accessToken.trim()) ) {
            return null;
        } else {
            String uid=redis.get(accessToken);
            if (uid == null || "".equals(uid.trim()) ) {
                return null;
            }
            deleteToken(accessToken);//清除原来的accessToken
            return Integer.parseInt(uid);

        }
    }


    public void deleteToken(String tokenKey) {
//       redisTemplate.delete(userId);
        redis.del(tokenKey);
    }
}
