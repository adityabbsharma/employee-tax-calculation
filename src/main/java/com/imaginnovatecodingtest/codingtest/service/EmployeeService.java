package com.imaginnovatecodingtest.codingtest.service;

import com.imaginnovatecodingtest.codingtest.entity.Employee;
import com.imaginnovatecodingtest.codingtest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
       return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    public double getCurrentYearTax(Employee employee) {
        LocalDate currentDate = LocalDate.now();
        LocalDate financialYearStart = LocalDate.of(currentDate.getYear(), Month.APRIL, 1);
        LocalDate financialYearEnd = financialYearStart.plusYears(1).minusDays(1);

        LocalDate joiningDate = employee.getDateOfJoining().toLocalDate();
        double salaryPerMonth = employee.getSalaryPerMonth();
        double annualSalary = salaryPerMonth * 12;
        if (joiningDate.isBefore(financialYearStart)) {
            return calculateTax(annualSalary);
        } else {
            int daysEmployed = (int)ChronoUnit.DAYS.between(joiningDate, financialYearEnd);
            double salaryEarned = (salaryPerMonth * daysEmployed) / 365 * 12;
            return calculateTax(salaryEarned);
        }
    }

    public double calculateTax(double annualSalary) {
        double tax = 0.0;
        if (annualSalary <= 250000) {
            return 0.0;
        } else if (annualSalary > 250000 && annualSalary <= 500000) {
            annualSalary = annualSalary - 250000;
            tax += annualSalary*0.05;
            return tax;

        } else if (annualSalary > 500000 && annualSalary <= 1000000) {
            annualSalary = annualSalary - 500000;
            tax += 12500 + (annualSalary*0.1);
            return tax;
        } else {
            annualSalary = annualSalary - 1000000;
            tax += 12500 + 50000 + (annualSalary*0.2);
            return tax;
        }
    }
}
