package com.roxi.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebFilter(filterName = "xml",urlPatterns = "/write")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("我准备开始了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("可以拦截吗");
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        String account= (String) session.getAttribute("user");
        if(account==null){
            response.getWriter().write("请先登录");
        }else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("我死了");
    }
}
