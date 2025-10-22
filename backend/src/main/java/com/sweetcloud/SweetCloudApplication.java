package com.sweetcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SweetCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweetCloudApplication.class, args);
        System.out.println("🚀 SweetCloud Server is running...");
    }
}
