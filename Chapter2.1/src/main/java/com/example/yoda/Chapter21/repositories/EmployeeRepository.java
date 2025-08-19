package com.example.yoda.Chapter21.repositories;

import com.example.yoda.Chapter21.entities.EmployeeEntity;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
