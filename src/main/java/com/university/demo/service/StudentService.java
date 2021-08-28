package com.university.demo.service;

import com.university.demo.model.Student;

import java.util.List;

public interface StudentService {

    Student findById(Long id);

    List<Student> findALl();

    Student saveStudent(Student student);

    void deleteById(Long id);
}
