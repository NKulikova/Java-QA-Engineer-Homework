package com.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class A {

    @Autowired
    B b;

    public String returnCName() {
        return b.c.name;
    }
}
