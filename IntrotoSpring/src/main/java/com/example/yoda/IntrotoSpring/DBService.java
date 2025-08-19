package com.example.yoda.IntrotoSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    private final DB db;

    public DBService(DB db){ //now whenever we create an object of DBService DB object is required in constructor
        this.db = db;
    }

    String getData(){
        return db.getData();

    }
}
