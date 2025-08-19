package com.example.yoda.IntrotoSpring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name= "deploy.env", havingValue = "Prod")
public class ProdDB implements DB {
   public String getData(){
        return "Pord Data";
    }
}
