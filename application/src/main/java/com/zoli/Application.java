package com.zoli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@ComponentScan("com.zoli")
public class Application {

    // must have a main method spring-boot can run
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}
