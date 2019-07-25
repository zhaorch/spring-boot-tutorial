package com.zrc.springboottutorial.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/25 13:59
 * Description: No Description
 */
@WebListener
public class MyListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();

        ServletContext servletContext = request.getServletContext();
        servletContext.log("code in myListener Init");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();

        ServletContext servletContext = request.getServletContext();
        servletContext.log("code in myListener Destroyed");
    }
}
