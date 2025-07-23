package com.example.auth_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthAppApplication.class, args);
        System.out.println("🚀 AuthApp started successfully!");
        System.out.println("📍 Application URL: http://localhost:8080");
        System.out.println("🔐 Features:");
        System.out.println("   📝 Signup: http://localhost:8080/signup");
        System.out.println("   🔑 Login: http://localhost:8080/login");
        System.out.println("   📊 Dashboard: http://localhost:8080/dashboard (JWT Protected)");
    }
}
