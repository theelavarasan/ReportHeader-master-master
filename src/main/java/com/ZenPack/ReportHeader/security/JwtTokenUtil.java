package com.ZenPack.ReportHeader.security;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class JwtTokenUtil implements Serializable {
    @Autowired
    private RedisUtil redisUtil;

    private static final long serialVersionUID = 1L;

    public Boolean validateToken(String token, UserDetails userDetails) {
        boolean toRet = false;
        if (redisUtil.getValue(token) != null) {
            toRet = true;
        }

        return toRet;
    }

    public String getUserId(String authorisation)
    {
        authorisation = authorisation.replace(Constants.TOKEN_PREFIX,"");
        if (redisUtil.getValue(authorisation) == null) {
            throw new RuntimeException("User id is not valid or session expired!");
        }
        JSONObject userObj =  (JSONObject) redisUtil.getValue(authorisation);
        return (String) userObj.get("userId");
    }
}
