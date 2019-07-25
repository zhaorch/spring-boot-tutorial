package com.zrc.springboottutorial.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/7/25 10:56
 * Description: No Description
 */

@WebServlet(name = "myServlet",
        urlPatterns = "/myservlet",
        initParams = {
            @WebInitParam(name = "name",value = "zrc")
        })
public class MyServlet extends HttpServlet {
    private String DefaultName;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.DefaultName = config.getInitParameter("name");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();
        servletContext.log("code in myServlet doGet");

        Writer writer = resp.getWriter();

        writer.write("<html><body>Hello Servlet,Hi "+this.DefaultName+"</body></html>");
    }
}
