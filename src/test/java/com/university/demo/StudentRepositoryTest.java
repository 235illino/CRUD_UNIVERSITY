package com.university.demo;


import com.university.demo.model.Student;
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
public class StudentRepositoryTest {


    @Autowired
    private StudentRepository studentRepository;



    @Test
    @Order(1)
    @Rollback(value = false)
    public void createStudentTest() {
        Student student = Student.builder().firstName("Stud1").lastName("Stud1").build();
        studentRepository.save(student);
        Assertions.assertThat(student.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getStudentTest() {
        Student student = studentRepository.findById(1L).get();
        Assertions.assertThat(student.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfStudentsTest() {
        List<Student> students = studentRepository.findAll();
        Assertions.assertThat(students.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateStudentTest() {
        Student student = studentRepository.findById(1L).get();
        student.setFirstName("Stud2");
        Student save = studentRepository.save(student);
        Assertions.assertThat(save.getFirstName()).isEqualTo("Stud2");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteStudentTest() {
        Student student = studentRepository.findById(1L).get();
        studentRepository.delete(student);
        Student student1 = null;
        Optional<Student> optionalStudent = studentRepository.findByFirstName("Stud2");
        if(optionalStudent.isPresent()) {
            student1 = optionalStudent.get();
        }
        Assertions.assertThat(student1).isNull();
    }
    
}
