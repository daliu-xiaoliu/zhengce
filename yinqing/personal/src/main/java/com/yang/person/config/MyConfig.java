package com.yang.person.config;

import com.yang.util.CrossDomainConfig;
import com.yang.util.DruidConfig;
import com.yang.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public DruidConfig druidConfig(){
        return new DruidConfig();
    }

    @Bean
    public RedisUtil redisUtil(){
        return new RedisUtil();
    }

    @Bean
    public CrossDomainConfig crossDomainConfig(){
        return new CrossDomainConfig();
    }

}
