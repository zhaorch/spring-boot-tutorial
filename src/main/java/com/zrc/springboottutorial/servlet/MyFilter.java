package com.zrc.springboottutorial.servlet;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/25 13:50
 * Description: No Description
 */
@WebFilter(servletNames = {"myServlet"})
public class MyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ServletContext servletContext = request.getServletContext();

        servletContext.log("code in myfilter");

        filterChain.doFilter(request,response);
    }
}
