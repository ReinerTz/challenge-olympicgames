package com.challenge.olympicgames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class OlympicgamesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlympicgamesApplication.class, args);
    }

}
