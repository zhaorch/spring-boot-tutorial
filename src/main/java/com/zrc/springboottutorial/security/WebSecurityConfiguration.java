package com.zrc.springboottutorial.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.AllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: ZRC
 * Date Time: 2019/8/2 15:55
 * Description: No Description
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //CSRF  POST 必须包含CSRF token，
        http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
                .requireCsrfProtectionMatcher(httpServletRequest ->
                        httpServletRequest.getMethod().toUpperCase().equals("POST")
                && httpServletRequest.getRequestURI().startsWith("/th/login"));

        //CSP -- 白名单，脚本以下面开头的才可以访问
        http.headers().contentSecurityPolicy("script-src https://code.jquery.com/");


        //X-Frame-Options header  允许同域请求
        //http.headers().frameOptions().sameOrigin();

        // X-Frame-Options header  跨域请求白名单
        http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(new AllowFromStrategy() {
            @Override
            public String getAllowFromValue(HttpServletRequest request) {
                return "www.zrc.com";
            }
        }));

        //XSS header
        http.headers().xssProtection().block(true);
    }
}
