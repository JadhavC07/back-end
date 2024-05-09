package com.student_form.demo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Data
public class StudDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String studentName;
    private String fatherName;
    private String lastName;
    private LocalDate dob;
    private String course;
    private String bloodGroup;
    private String gender;
    private String email;
    private String password;
}
