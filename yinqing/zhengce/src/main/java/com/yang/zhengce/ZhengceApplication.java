package com.yang.zhengce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yang.zhengce.dao")
public class ZhengceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhengceApplication.class,args);
    }
}
