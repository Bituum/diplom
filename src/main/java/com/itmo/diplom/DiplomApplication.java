package com.itmo.diplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
        "com.itmo.diplom.DAO",
        "com.itmo.diplom.service",
        "com.itmo.diplom.config",
        "com.itmo.diplom.controllers",
        "com.itmo.diplom.entities"})
public class DiplomApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomApplication.class, args);
    }

}
