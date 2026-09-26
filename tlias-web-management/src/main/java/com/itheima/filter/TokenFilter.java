package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestURI=request.getRequestURI();
        //2.判断是否是登录请求，是则放行
        if (requestURI.contains("/login")){
            log.info("登录请求，放行");
            filterChain.doFilter(request,response);
            return;
        }

        //3.获取请求头中的token
        String token=request.getHeader("token");
        //4.判断token是否存在，不存在则响应401
        if (token==null||token.isEmpty()){
            response.setStatus(401);
            return;
        }

        //5.token存在则校验合法性，失败则响应401
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);//存入ThreadLocal
            log.info("当前登录员工ID：{}，存入ThreadLocal",empId);

        } catch (Exception e) {
            response.setStatus(401);
            return;
        }
        //6.合法则放行
        filterChain.doFilter(request,response);

        //7.删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
