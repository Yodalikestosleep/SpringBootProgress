package com.example.yoda.Chapter21.controllers;
import com.example.yoda.Chapter21.dto.EmployeeDto;
import com.example.yoda.Chapter21.exceptions.ResourceNotFoundException;
import com.example.yoda.Chapter21.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
        return employeeDto
                .map(employeeDto1->ResponseEntity.ok(employeeDto1))
                .orElseThrow(()->new ResourceNotFoundException("Employee Not Found with id : "+id));
        //mapping employeedto to a responseentity and using orElseThrow to throw an exception
        //we are using exception handler annotation for exception handling
    }


    @GetMapping(path="/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees()); //either empty list or filled so no need for
        //checks right now
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody(required = true) @Valid EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.create(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody(required = true) @Valid EmployeeDto employeeDto, @PathVariable(required = true) Long employeeId){
        EmployeeDto employeeDto1 = employeeService.updateEmployeeById(employeeDto,employeeId); //if no employee with this id was found
        //this will throw an exception
        return  ResponseEntity.ok(employeeDto1);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteEmployeeById(@RequestParam(required = true) Long employeeId){
       boolean gotDeleted =  employeeService.deleteEmployeeById(employeeId);
       if(gotDeleted)return ResponseEntity.ok(true);
       return ResponseEntity.notFound().build();}
    @PatchMapping
    public ResponseEntity<EmployeeDto> updatePartialEmployee(@RequestParam Long employeeId, @RequestBody Map<String,Object> updates){
        EmployeeDto employeeDto = employeeService.updatePartialEmployee(employeeId,updates);
        if(employeeDto==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDto);
    }



}
