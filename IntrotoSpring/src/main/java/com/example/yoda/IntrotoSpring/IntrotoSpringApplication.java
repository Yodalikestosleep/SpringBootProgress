package com.example.yoda.IntrotoSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntrotoSpringApplication implements CommandLineRunner {
    @Autowired
    Apple obj;

    @Autowired
    DBService dbservice;


    public static void main(String[] args) {
		SpringApplication.run(IntrotoSpringApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        obj.eatApple();
        System.out.println(dbservice.getData());



    }
}
