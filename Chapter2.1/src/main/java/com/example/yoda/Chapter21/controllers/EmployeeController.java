package com.example.yoda.Chapter21.controllers;
import com.example.yoda.Chapter21.dto.EmployeeDto;
import com.example.yoda.Chapter21.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @GetMapping
    public EmployeeDto getById(@RequestParam(required = true) Long id ){
        return employeeService.getById(id);
    }

    @GetMapping(path="/all")
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDto create(@RequestBody(required = true) EmployeeDto employeeDto){
        return employeeService.create(employeeDto);
    }

    @PutMapping(path="/{employeeId}")
    public EmployeeDto updateEmployeeById(@RequestBody(required = true) EmployeeDto employeeDto, @PathVariable(required = true) Long employeeId){
        return employeeService.updateEmployeeById(employeeDto,employeeId);
    }

    @DeleteMapping
    public boolean deleteEmployeeById(@RequestParam(required = true) Long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }



}
