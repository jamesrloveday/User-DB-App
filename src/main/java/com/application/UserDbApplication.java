package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by james on 02/06/2016.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.application.userDbApp")
public class UserDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDbApplication.class, args);
    }

}
