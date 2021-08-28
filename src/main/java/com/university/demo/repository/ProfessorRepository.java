package com.university.demo.repository;

import com.university.demo.model.Professor;
import com.university.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByFirstName(String firstName);
}
