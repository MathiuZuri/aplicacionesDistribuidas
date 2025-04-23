package com.example.ms_test01post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsCatalogoApplication {

    public static void main(String[] args) {

        SpringApplication.run(MsCatalogoApplication.class, args);
    }

}
