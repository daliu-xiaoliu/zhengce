package com.yang.person;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.yang.person.dao")
@EnableTransactionManagement
public class PersonApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonApplication.class,args);
    }
}
