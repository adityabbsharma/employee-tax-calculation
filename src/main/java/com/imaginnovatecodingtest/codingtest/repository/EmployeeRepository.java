package com.imaginnovatecodingtest.codingtest.repository;

import com.imaginnovatecodingtest.codingtest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
