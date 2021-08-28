package com.university.demo.service;

import com.university.demo.model.Course;

import java.util.List;

public interface CourseService {

    Course findById(Long id);

    List<Course> findALl();

    Course saveCourse(Course course);

    void deleteById(Long id);


}
