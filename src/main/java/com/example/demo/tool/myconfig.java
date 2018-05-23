package com.example.demo.tool;


import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

public class myconfig {
    @Bean
    public  ConfigurationCustomizer configuration(){
     return new ConfigurationCustomizer(){

         @Override
         public void customize(Configuration configuration) {
             configuration.setMapUnderscoreToCamelCase(true);
         }
     };
    }
}
