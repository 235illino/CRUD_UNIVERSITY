package com.university.demo;


import com.university.demo.model.Course;
import com.university.demo.model.Professor;
import com.university.demo.model.Student;
import com.university.demo.repository.CourseRepository;
import com.university.demo.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseRepositoryTest {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    @Order(1)
    public void createStudentsTest() {
        Student student1 = Student.builder().firstName("Student1").lastName("Student1").build();
        Student student2 = Student.builder().firstName("Student2").lastName("Student2").build();
        Student student3 = Student.builder().firstName("Student3").lastName("Student3").build();

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
    }

    @Test
    @Order(2)
    public void createProfessorTest() {
        Professor professor = Professor.builder().firstName("Professor").lastName("Professor").build();
        entityManager.persist(professor);

    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void createNewCourseWithThreeStudentsAndOneProfessor() {
        Professor professor = entityManager.find(Professor.class, 1L);
        Student student1 = entityManager.find(Student.class, 1L);
        Student student2 = entityManager.find(Student.class, 2L);
        Student student3 = entityManager.find(Student.class, 3L);

        Set<Student> studentSet = new HashSet<>();
        studentSet.add(student1);
        studentSet.add(student2);
        studentSet.add(student3);

        Course course = Course.builder().name("Course").startCourse(LocalDate.of(2001, 1, 1))
                .endCourse(LocalDate.of(2001, 1, 1)).professor(professor).students(studentSet).build();
        courseRepository.save(course);
        Assertions.assertThat(course.getId()).isGreaterThan(0);

    }


    @Test
    @Order(4)
    public void getCourseTest() {
        Course course = courseRepository.findById(1L).get();
        Assertions.assertThat(course.getId()).isEqualTo(1L);
    }

    @Test
    @Order(5)
    public void getListOfCoursesTest() {
        List<Course> courses = courseRepository.findAll();
        Assertions.assertThat(courses.size()).isGreaterThan(0);
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    public void updateCourseTest() {
        Course course = courseRepository.findById(1L).get();
        course.setName("NewCourse");
        Course save = courseRepository.save(course);
        Assertions.assertThat(save.getName()).isEqualTo("NewCourse");
    }

    @Test
    @Order(7)
    @Rollback(value = false)
    public void deleteCourseTest() {
        Course course = courseRepository.findById(1L).get();
        courseRepository.delete(course);
        Course course1 = null;
        Optional<Course> optionalCourse = courseRepository.findByName("NewCourse");
        if (optionalCourse.isPresent()) {
            course1 = optionalCourse.get();
        }
        Assertions.assertThat(course1).isNull();
    }

}
