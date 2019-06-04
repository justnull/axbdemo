package com.live.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.live"})
public class DemoApplication {

    public static void main(String[] args) {
        String hello = "Hello world,I'm writin Hello code now!";
        String s = "s";

        SpringApplication.run(DemoApplication.class, args);
    }

}
