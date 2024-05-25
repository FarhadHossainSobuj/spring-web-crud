package com.example.spring_boot_web_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_web_crud.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
