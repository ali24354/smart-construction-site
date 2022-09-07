package com.scs.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.scs.system.mapper"})
@ComponentScan(value = {"com.scs.*"})
@ServletComponentScan
@EnableCaching
public class ScsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScsAdminApplication.class, args);
    }

}
