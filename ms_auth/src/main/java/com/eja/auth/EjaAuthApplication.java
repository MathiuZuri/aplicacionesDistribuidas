package com.eja.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class EjaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjaAuthApplication.class, args);
    }

}
