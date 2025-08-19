package com.example.yoda.IntrotoSpring;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Apple {
    void eatApple(){
        System.out.print("I am eating apple");
    }
    @PostConstruct //This tells the spring to call this method after the bean is created
    void postConstructMethod(){
        System.out.println("After bean is created");
    }
    @PreDestroy //This tells the spring to call this method before the destruction of bean
    void preDestroyMethod(){
        System.out.println("Before destruction of bean");
    }


}
