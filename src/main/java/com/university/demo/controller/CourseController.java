package com.university.demo.controller;

import com.university.demo.model.Course;
import com.university.demo.model.Professor;
import com.university.demo.model.Student;
import com.university.demo.service.CourseService;
import com.university.demo.service.ProfessorService;
import com.university.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CourseController {

    private final CourseService courseService;

    private final ProfessorService professorService;

    private final StudentService studentService;


    @Autowired
    public CourseController(CourseService coureService, ProfessorService professorService, StudentService studentService) {
        this.courseService = coureService;
        this.professorService = professorService;
        this.studentService = studentService;
    }


    @GetMapping("/courses")
    public String findAll(Model model) {
        List<Course> courses = courseService.findALl();
        model.addAttribute("courses", courses);

        return "courses/list";
    }

    @GetMapping("/courses/create")
    public String createCourseForm(Model model) {
        List<Professor> professors = professorService.findALl();
        List<Student> students = studentService.findALl();
        model.addAttribute("course", new Course());
        model.addAttribute("professors", professors);
        model.addAttribute("students", students);
        return "courses/create";
    }

    @PostMapping("/courses/create")
    public String createCourse(@Valid Course course, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "courses/create";
        }
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/courses/update/{id}")
    public String updateCourseForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        List<Professor> professors = professorService.findALl();
        model.addAttribute("professors", professors);
        courseService.deleteById(id);
        return "courses/create";
    }


    @GetMapping("/resources/report/courseReport.csv")
    public void exportToCsv(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        LocalDateTime currentDateTime = LocalDateTime.now();

        File file = new File("/home/ilya/Downloads/demo/src/main/resources/report/courses_"
                + currentDateTime + ".csv");
        if (!file.exists()) {
            file.createNewFile();
        }


        FileWriter fileWriter = new FileWriter(file);
        ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);


        List<Course> courses = courseService.findALl()
                .stream()
                .filter(course -> course.getStudents().size() > 1)
                .collect(Collectors.toList());


        String[] csvHeader = {"id", "name", "startDate",
                "endDate", "numberOfStudentsEnrolled", "professor"};

        String[] nameMapping = {"id", "name", "startCourse",
                "endCourse", "studentSize", "professor"};

        csvBeanWriter.writeHeader(csvHeader);

        for (Course course : courses) {

            csvBeanWriter.write(course, nameMapping);
        }

        csvBeanWriter.close();
    }


}
