package com.imaginnovatecodingtest.codingtest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s+[a-zA-Z]+)*$", message = "First name must contain only alphabets and spaces, without trailing spaces")
    private String firstname;

    @Column
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s+[a-zA-Z]+)*$", message = "First name must contain only alphabets and spaces, without trailing spaces")
    private String lastName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(unique = true)
    private String email;

//    @ElementCollection
//    @CollectionTable(name = "employee_phone_numbers", joinColumns = @JoinColumn(name = "employee_id"))
//    @Column(name = "phone_number")
    @Column(columnDefinition = "json")
    @JsonIgnore
    private String phoneNumbersJson;

    @Transient
    private Set<String> phoneNumbers;

    @PrePersist
    @PreUpdate
    private void updatePhoneNumbersJson() {
        if (phoneNumbers != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                phoneNumbersJson = objectMapper.writeValueAsString(phoneNumbers);
            } catch (JsonProcessingException e) {
                // Handle the exception as needed
                e.printStackTrace();
            }
        }
    }

    @Column
    private Date dateOfJoining;

    @Column
    private double salaryPerMonth;



}
