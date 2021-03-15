package com.yang.zhengce.config;

import com.yang.util.CrossDomainConfig;
import com.yang.util.DruidConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public DruidConfig druidConfig(){
        return new DruidConfig();
    }

    @Bean
    public CrossDomainConfig domainConfig(){
        return new CrossDomainConfig();
    }
}
