package com.university.demo.service;

import com.university.demo.model.Professor;

import java.util.List;

public interface ProfessorService {
    Professor findById(Long id);

    List<Professor> findALl();

    Professor saveProfessor(Professor professor);

    void deleteById(Long id);
}
