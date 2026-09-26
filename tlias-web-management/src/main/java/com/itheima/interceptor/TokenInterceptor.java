package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//令牌校验拦截器
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.获取请求路径
        String requestURI=request.getRequestURI();
        //2.判断是否是登录请求，是则放行
        if (requestURI.contains("/login")){
            log.info("登录请求，放行");
            return true;
        }

        //3.获取请求头中的token
        String token=request.getHeader("token");

        //4.判断token是否存在，不存在则响应401
        if (token==null||token.isEmpty()){
            response.setStatus(401);
            return false;
        }

        //5.token存在则校验合法性，失败则响应401
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        //6.合法则放行
        return true;
    }
}
