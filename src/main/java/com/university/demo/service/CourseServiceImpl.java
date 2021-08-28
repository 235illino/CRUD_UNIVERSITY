package com.university.demo.service;

import com.university.demo.model.Course;
import com.university.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{


    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public Course findById(Long id) {

        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> findALl() {

        List<Course> courses = courseRepository.findAll();
        courses.forEach(course -> course.setStudentSize(course.getStudents().size()));
        return courses;
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
