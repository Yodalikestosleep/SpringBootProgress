package com.example.yoda.Chapter21.services;

import com.example.yoda.Chapter21.dto.EmployeeDto;
import com.example.yoda.Chapter21.entities.EmployeeEntity;
import com.example.yoda.Chapter21.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }
    public EmployeeDto getById(Long id){
        return mapper.map(employeeRepository.findById(id).orElse(null),EmployeeDto.class);
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
        EmployeeEntity employeeEntity = mapper.map(employeeDto,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        employeeRepository.save(employeeEntity);
        return mapper.map(employeeEntity,EmployeeDto.class);

    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist)return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }
}
