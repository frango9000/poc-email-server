package com.kurama.email_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class EmailServiceApplication {

    public static void main(String[] args) {
        System.setProperty("spring.amqp.deserialization.trust.all", "true");
        SpringApplication.run(EmailServiceApplication.class, args);
    }

}
