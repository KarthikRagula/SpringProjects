package org.example.config;

import org.example.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("org.example")
public class AppConfig {
    @Bean
    public Employee employee(){
        return new Employee();
    }
}