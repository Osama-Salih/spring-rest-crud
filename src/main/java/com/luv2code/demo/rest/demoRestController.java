package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class demoRestController {

    List<Student> students;

    @PostConstruct
    private void loadData() {
        students = new ArrayList<>();

        students.add(new Student("Poornima", "Patel"));
        students.add(new Student("Mario","Rossi"));
        students.add(new Student("Mary", "Smith"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

   @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        return students.get(studentId);
   }
}
