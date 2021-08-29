package com.university.demo.controller;


import com.university.demo.model.Professor;
import com.university.demo.model.Student;
import com.university.demo.service.ProfessorService;
import com.university.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService service;

    @Autowired
    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping()
    public String findAll(Model model) {
        List<Professor> professors = service.findALl();
        model.addAttribute("professors", professors);

        return "professors/list";
    }

    @GetMapping("/create")
    public String createProfessorForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "professors/create";
    }

    @PostMapping("/create")
    public String createProfessor(@Valid Professor professor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "professors/create";
        }
        service.saveProfessor(professor);
        return "redirect:/professors";
    }


    @GetMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/professors";
    }

    @GetMapping("/update/{id}")
    public String updateProfessorForm(@PathVariable("id") Long id, Model model) {
        Professor professor = service.findById(id);
        model.addAttribute("professor", professor);
        service.deleteById(id);
        return "professors/create";
    }



}
