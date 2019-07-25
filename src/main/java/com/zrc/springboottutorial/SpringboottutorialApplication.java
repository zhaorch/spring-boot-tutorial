package com.zrc.springboottutorial;

import com.zrc.springboottutorial.springboot.MyFilter2;
import com.zrc.springboottutorial.springboot.MyListener2;
import com.zrc.springboottutorial.springboot.MyServlet2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.DispatcherType;

@SpringBootApplication
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.zrc.springboottutorial.mapper")
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径 默认会扫描该类所在包及子包
//@ComponentScan(basePackages = {"com.zrc"})
//扫描Servlet
@ServletComponentScan(basePackages = {"com.zrc.springboottutorial.servlet"})
public class SpringboottutorialApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringboottutorialApplication.class, args);
    }

    @Bean
    public static ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new MyServlet2());
        servletRegistrationBean.addUrlMappings("/myservlet2");
        servletRegistrationBean.addInitParameter("name","ZRC");

        return servletRegistrationBean;
    }

    @Bean
    public static FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean servletRegistrationBean = new FilterRegistrationBean();
        servletRegistrationBean.setFilter(new MyFilter2());
        servletRegistrationBean.addUrlPatterns("/myservlet2");

        servletRegistrationBean.setDispatcherTypes(DispatcherType.FORWARD,DispatcherType.REQUEST);

        return servletRegistrationBean;
    }

    @Bean
    public static ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean temp = new ServletListenerRegistrationBean();
        temp.setListener(new MyListener2());
        return temp;
    }

    protected SpringApplicationBuilder config(SpringApplicationBuilder builder){

        builder.sources(SpringboottutorialApplication.class);

        return builder;
    }
}
