package com.imaginnovatecodingtest.codingtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTaxResponseDTO {

    @JsonProperty("employeeCode")
    Long id;

    String firstName;

    String lastName;

    double yearlySalary;

    double taxAmount;

    double cessAmount;


}
