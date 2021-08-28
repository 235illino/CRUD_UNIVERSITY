package com.university.demo.service;

import com.university.demo.model.Professor;
import com.university.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    @Override
    public Professor findById(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Professor> findALl() {
        return professorRepository.findAll();
    }

    @Override
    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }
}
