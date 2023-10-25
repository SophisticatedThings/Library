package com.artem.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static com.artem.library.config.HibernateUtil.setUp;

@SpringBootApplication
@EnableAspectJAutoProxy
public class LibraryApplication {
    public static void main(String[] args) {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication.run(LibraryApplication.class, args);
    }
}
