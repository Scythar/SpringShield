package com.springshield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringShieldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringShieldApplication.class, args);
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║  SpringShield Started Successfully!   ║");
        System.out.println("║  Secured by JWT Authentication        ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }
}