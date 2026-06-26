package com.shopease.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("""
                ╔══════════════════════════════════════════╗
                ║       User Service Started!  🚀          ║
                ║       http://localhost:8081              ║
                ║       Health: /actuator/health           ║
                ╚══════════════════════════════════════════╝
                """);
    }

}
