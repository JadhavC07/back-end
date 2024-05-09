package com.student_form.demo.repo;

import com.student_form.demo.entity.StudEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudEntity, Integer> {


    Optional<StudEntity> findByEmail(String email);

    List<StudEntity> findAllByEmail(String email);

    StudentRepository  findById(int id);

    boolean existsByEmail(String email);
}
