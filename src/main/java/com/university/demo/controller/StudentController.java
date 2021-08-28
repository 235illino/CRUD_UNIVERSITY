package com.university.demo.controller;


import com.university.demo.model.Course;
import com.university.demo.model.Professor;
import com.university.demo.model.Student;
import com.university.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<Student> students = service.findALl();
        model.addAttribute("students", students);

        return "students/list";
    }

    @GetMapping("/create")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/create";
    }

    @PostMapping("/create")
    public String createStudent(Student student) {
        service.saveStudent(student);
        return "redirect:/students";
    }


    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String updateStudentForm(@PathVariable("id") Long id, Model model) {
        Student student = service.findById(id);
        model.addAttribute("student", student);
        service.deleteById(id);
        return "students/create";
    }



}
