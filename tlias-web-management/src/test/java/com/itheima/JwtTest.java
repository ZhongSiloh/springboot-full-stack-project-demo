package com.itheima;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

//    生成JWT令牌
    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","damin");

        String jwt=Jwts.builder().signWith(SignatureAlgorithm.HS256,"emhvbmd4aWxpbg==")//指定加密算法，密钥(baes64)
                .addClaims(dataMap)//添加自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置过期时间
                .compact();//生成令牌
        System.out.println(jwt);
    }

//    解析JWT令牌
    @Test
    public void testParseJWT(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJkYW1pbiIsImV4cCI6MTc4MzUwMDI4NX0.xT_EkSohqu5yc2ooJ0OaCRaCqyhUiPnL2g-BHJCdMP0";
        Claims claims = Jwts.parser()
                .setSigningKey("emhvbmd4aWxpbg==")//指定密钥，要一致
                .parseClaimsJws(token)//解析令牌
                .getBody();//获取自定义信息
        System.out.println(claims);
    }
}
