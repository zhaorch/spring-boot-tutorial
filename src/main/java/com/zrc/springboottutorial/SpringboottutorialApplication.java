package com.zrc.springboottutorial;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.zrc.springboottutorial.mapper")
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"com.zrc"})
public class SpringboottutorialApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringboottutorialApplication.class, args);
    }
}
