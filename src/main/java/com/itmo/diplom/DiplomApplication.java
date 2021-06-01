package com.itmo.diplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
@SpringBootApplication(scanBasePackages = {
        "com.itmo.diplom.repository",
        "com.itmo.diplom.service",
        "com.itmo.diplom.config",
        "com.itmo.diplom.controller",
        "com.itmo.diplom.entity"})
public class DiplomApplication{
    public static void main(String[] args) {
        SpringApplication.run(DiplomApplication.class, args);
    }

}
