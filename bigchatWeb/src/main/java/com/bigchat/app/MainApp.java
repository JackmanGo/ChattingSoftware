package com.bigchat.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by wang on 16-11-25.
 */
@SpringBootApplication
@ComponentScan("com.bigchat")
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class);
    }
}
