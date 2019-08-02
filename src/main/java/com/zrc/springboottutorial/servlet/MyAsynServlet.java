package com.zrc.springboottutorial.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/8/2 14:06
 * Description: No Description
 */

@WebServlet(value = "/asyn",asyncSupported = true)
public class MyAsynServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AsyncContext asyncContext = req.startAsync();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");

        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println(sdf.format(new Date())+":"+Thread.currentThread().getName()+":接收异步请求<br/>");

        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                writer.println(sdf.format(new Date())+":"+Thread.currentThread().getName()+":异步请求结束<br/>");
                System.out.println(sdf.format(new Date())+":"+Thread.currentThread().getName()+":异步请求结束");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                writer.println("异步请求超时<br/>");
                System.out.println("异步请求超时");
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {

            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {

            }
        });

        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                writer.println(sdf.format(new Date())+":"+Thread.currentThread().getName()+":异步请求正在工作-1<br/>");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                writer.println(sdf.format(new Date())+":"+Thread.currentThread().getName()+":异步请求正在工作-2<br/>");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                writer.println(sdf.format(new Date())+":"+Thread.currentThread().getName()+":异步请求正在工作-3<br/>");

                asyncContext.complete();
            }
        });


        writer.println(sdf.format(new Date())+":"+Thread.currentThread().getName()+":接收异步完毕<br/>");
    }
}
