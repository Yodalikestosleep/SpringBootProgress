package com.example.yoda.Chapter21.controllers;
import com.example.yoda.Chapter21.dto.EmployeeDto;
import com.example.yoda.Chapter21.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @GetMapping
    public ResponseEntity<EmployeeDto> getById(@RequestParam(required = true) Long id ){
        Optional<EmployeeDto> employeeDto = (employeeService.getById(id));
         //return 404 error , status code
         //return 200 status code along with employeedto
        return employeeDto.map(employeeDto1->ResponseEntity.ok(employeeDto1)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path="/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees()); //either empty list or filled so no need for
        //checks right now
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody(required = true) EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.create(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping(path="/{employeeId}")
    public EmployeeDto updateEmployeeById(@RequestBody(required = true) EmployeeDto employeeDto, @PathVariable(required = true) Long employeeId){
        return employeeService.updateEmployeeById(employeeDto,employeeId);
    }

    @DeleteMapping
    public boolean deleteEmployeeById(@RequestParam(required = true) Long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }
    @PatchMapping
    public EmployeeDto updatePartialEmployee(@RequestParam Long employeeId, @RequestBody Map<String,Object> updates){
        return employeeService.updatePartialEmployee(employeeId,updates);
    }



}
