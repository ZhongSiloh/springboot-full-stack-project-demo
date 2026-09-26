package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")//拦截所有请求
@Slf4j
public class DemoFilter implements Filter {

    //每拦截到请求之后执行
    /**
     *
     * @param servletRequest 请求对象
     * @param servletResponse 响应对象
     * @param filterChain

     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //拦截到请求
        log.info("拦截到了请求");

        //放行，以成功访问到后面的资源
        filterChain.doFilter(servletRequest, servletResponse);


    }

    //初始化方法，web服务器启动时执行（资源的准备），只执行一次，接口可以不实现
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法");
    }

    //销毁方法，web服务器关闭时执行（资源的释放），只执行一次，接口可以不实现
    @Override
    public void destroy() {
        log.info("destroy 销毁方法");
    }
}
