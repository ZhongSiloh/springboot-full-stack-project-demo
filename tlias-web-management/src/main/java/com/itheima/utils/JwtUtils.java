package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 固定密钥
    private static final String SECRET_KEY = "emhvbmd4aWxpbg==";
    // 令牌有效期：12小时，换算成毫秒
    private static final long EXPIRE_MILLIS = 12 * 3600 * 1000L;

    /**
     * 生成JWT令牌
     * @param claimsData 自定义要存入令牌的载荷数据
     * @return 生成完成的jwt字符串
     */
    public static String generateToken(Map<String, Object> claimsData) {
        String jwt = Jwts.builder()
                // 指定HS256加密算法以及固定密钥
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // 塞入自定义载荷
                .addClaims(claimsData)
                // 设置过期时刻：当前时间 + 12小时
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_MILLIS))
                .compact();
        return jwt;
    }
    /**
     * 解析JWT令牌，取出载荷
     * @param token 前端传过来的jwt令牌
     * @return 令牌内部的Claims载荷对象
     */
    public static Claims parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
