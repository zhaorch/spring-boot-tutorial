package com.zrc.springboottutorial.springboot;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/25 13:50
 * Description: No Description
 */
public class MyFilter2 extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ServletContext servletContext = request.getServletContext();

        String uri = request.getRequestURI();

        servletContext.log("code in myfilter2");


        doSomething();
        filterChain.doFilter(request,response);
    }

    public void doSomething(){
        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request =  servletRequestAttributes.getRequest();

        ServletContext servletContext = request.getServletContext();
        servletContext.log("code in myfilter2 doSomething");
    }
}
