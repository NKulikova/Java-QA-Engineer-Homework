package com.spring.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class C {

    @Value("${app.message}")
    String name;
}
