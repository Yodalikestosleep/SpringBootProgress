package com.example.yoda.Chapter21.services;

import com.example.yoda.Chapter21.dto.EmployeeDto;
import com.example.yoda.Chapter21.entities.EmployeeEntity;
import com.example.yoda.Chapter21.exceptions.ResourceNotFoundException;
import com.example.yoda.Chapter21.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;
    public boolean isExistById(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }
    public Optional<EmployeeDto> getById(Long id){
        //Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        //return employeeEntity.map(employeeEntity1->mapper.map(employeeEntity,EmployeeDto.class));

        return employeeRepository.findById(id).map(employeeEntity ->mapper.map(employeeEntity,EmployeeDto.class));


    }

    public List<EmployeeDto> getAllEmployees() {
       List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
       return employeeRepository.findAll().stream().map(employeeEntity -> mapper.map(employeeEntity,EmployeeDto.class)).toList();
    }

    public EmployeeDto create(EmployeeDto employeeDto) {
        EmployeeEntity toSaveEntity = mapper.map(employeeDto,EmployeeEntity.class);
        return mapper.map(employeeRepository.save(toSaveEntity),EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long employeeId) {
        boolean exist = isExistById(employeeId);
        if(!exist)throw new ResourceNotFoundException("Employee With this Id was not found: "+employeeId); //throwing an exception in service class
        //even this exception will be caught by our exception handler
        EmployeeEntity employeeEntity = mapper.map(employeeDto,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        employeeRepository.save(employeeEntity);
        return mapper.map(employeeEntity,EmployeeDto.class);

    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exist = isExistById(employeeId);
        if(!exist)return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDto updatePartialEmployee(Long employeeId,Map<String, Object> updates) {
        boolean exist = isExistById(employeeId);
        if(!exist) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        updates.forEach((key,value)->{ //we are using a reflection function to map the changes into a employee entity
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,key);
            fieldToBeUpdated.setAccessible(true); //since the fields are private we are setting them accessible here
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value); //setting the field using setField function
        });
        return mapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class); //saving the employeeEntity to the database


    }
}
