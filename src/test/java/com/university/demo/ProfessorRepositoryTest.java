package com.university.demo;


import com.university.demo.model.Professor;
import com.university.demo.model.Student;
import com.university.demo.repository.ProfessorRepository;
import com.university.demo.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfessorRepositoryTest {


    @Autowired
    private ProfessorRepository professorRepository;



    @Test
    @Order(1)
    @Rollback(value = false)
    public void createProfessorTest() {
        Professor professor = Professor.builder().firstName("Prof1").lastName("Prof1").build();
        professorRepository.save(professor);
        Assertions.assertThat(professor.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getProfessorTest() {
        Professor professor = professorRepository.findById(1L).get();
        Assertions.assertThat(professor.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfProfessorsTest() {
        List<Professor> professors = professorRepository.findAll();
        Assertions.assertThat(professors.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateProfessorTest() {
        Professor professor = professorRepository.findById(1L).get();
        professor.setFirstName("Prof2");
        Professor save = professorRepository.save(professor);
        Assertions.assertThat(save.getFirstName()).isEqualTo("Prof2");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteProfessorTest() {
        Professor professor = professorRepository.findById(1L).get();
        professorRepository.delete(professor);
        Professor professor1 = null;
        Optional<Professor> optionalProfessor = professorRepository.findByFirstName("Prof2");
        if(optionalProfessor.isPresent()) {
            professor1 = optionalProfessor.get();
        }
        Assertions.assertThat(professor1).isNull();
    }
    
}
