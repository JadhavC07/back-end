package com.student_form.demo.controller;

import com.student_form.demo.entity.StudEntity;
import com.student_form.demo.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api")
public class StudController {

    @Autowired
    private StudentRepository repository;


    @PostMapping("/submitForm")
    public ResponseEntity<StudEntity> handleSubmit(@RequestBody StudEntity student) {

        if (repository.existsByEmail(student.getEmail())) {

            StudEntity errorStudent = new StudEntity();
            errorStudent.setEmail(student.getEmail());
            errorStudent.setStudentName(student.getStudentName());
            errorStudent.setErrorMessage("Duplicate Email Buddy");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorStudent);
        } else {
            StudEntity student1 = repository.save(student);
            return ResponseEntity.ok(student1);

        }

    }

    @GetMapping("/getAllStudents")
    public List<StudEntity> getAllStudent() {

        return repository.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody StudEntity loginRequest) {
        List<StudEntity> students = repository.findAllByEmail(loginRequest.getEmail());

        if (!students.isEmpty()) {
            boolean validCredentials = students.stream()
                    .anyMatch(student -> student.getPassword().equals(loginRequest.getPassword()));

            if (validCredentials) {
                StudEntity loggedInStudent = students.get(0);

                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login Successful");
                response.put("userId", loggedInStudent.getId());
                response.put("Student Name ", loggedInStudent.getStudentName());
                response.put("Father Name", loggedInStudent.getFatherName());
                response.put("Last Name", loggedInStudent.getLastName());
                response.put("Gender", loggedInStudent.getGender());
                response.put("Blood Group", loggedInStudent.getBloodGroup());
                response.put("Course", loggedInStudent.getCourse());
                response.put("Password", loggedInStudent.getPassword());

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("message", "Invalid Password"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "User Not Found"));
        }
    }

}
