package com.spring;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.spring")
@PropertySource("application.properties")
public class AppConfig {
}
