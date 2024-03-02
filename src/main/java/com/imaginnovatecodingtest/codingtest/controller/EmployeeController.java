package com.imaginnovatecodingtest.codingtest.controller;

import com.imaginnovatecodingtest.codingtest.entity.Employee;
import com.imaginnovatecodingtest.codingtest.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody Employee employee) {
        try {
            Employee savedEmp = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/currentyeartax/{employeeId}")
    public ResponseEntity<?> getCurrentYearTax(@PathVariable(name = "employeeId") Long employeeId) {
        try {
            Optional<Employee> employeeOp = employeeService.getEmployee(employeeId);
            if (employeeOp.isEmpty()) {
                return new ResponseEntity<>("Provided employee id doesnot exist", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(employeeService.getCurrentYearTax(employeeOp.get()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
