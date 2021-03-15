package com.yang.config;

import com.yang.util.CorsConfig;
import com.yang.util.CrossDomainConfig;
import com.yang.util.DruidConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyConfig {

    @Bean
    public DruidConfig config(){
        return new DruidConfig();
    }

    @Bean
    public CrossDomainConfig corsConfig(){
        return new CrossDomainConfig();
    }
}
