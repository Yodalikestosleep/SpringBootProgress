package com.example.yoda.Chapter21.controllers;

import com.example.yoda.Chapter21.entities.EmployeeEntity;
import com.example.yoda.Chapter21.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

    private final EmployeeRepository employeerepository;

    public EmployeeController(EmployeeRepository employeerepository) {
        this.employeerepository = employeerepository;
    }


    @GetMapping(path="/{id}")
    public EmployeeEntity getEmployeeById1(@PathVariable(name="id") Long id){
        return employeerepository.findById(id).orElse(null);

    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeerepository.save(inputEmployee);
    }
    @GetMapping(path="/all")
    public List<EmployeeEntity> getAllEmployees(){
        return employeerepository.findAll();
    }
}
