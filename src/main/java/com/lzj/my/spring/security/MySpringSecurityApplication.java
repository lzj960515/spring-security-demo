package com.lzj.my.spring.security;

import com.lzj.my.spring.security.security.properties.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.lzj.my.spring.security.mapper"})
@SpringBootApplication
//@EnableConfigurationProperties(RsaKeyProperties.class)
public class MySpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringSecurityApplication.class, args);
    }

}
