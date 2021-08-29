package com.university.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    @Column(nullable = false, length = 45, unique = true)
    private String name;

    @NotEmpty(message = "Date should not be empty")
    @Size(min = 10, max = 10, message = "Date format 00.00.0000")
    @Column(name = "start_course", nullable = false, length = 10)
    private String startCourse;

    @NotEmpty(message = "Date should not be empty")
    @Size(min = 10, max = 10, message = "Date format 00.00.0000")
    @Column(name = "end_course", nullable = false, length = 10)
    private String endCourse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();

    @Transient
    private Integer studentSize;


}
