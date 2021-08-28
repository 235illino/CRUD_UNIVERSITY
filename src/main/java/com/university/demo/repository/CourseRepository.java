package com.university.demo.repository;

import com.university.demo.model.Course;
import com.university.demo.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);
}
