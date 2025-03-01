package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id not found: " + studentId);
        }
        return students.get(studentId);
   }

   @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
       StudentErrorResponse error = new StudentErrorResponse();

       error.setStatus(HttpStatus.BAD_REQUEST.value());
       error.setMessage(exc.getMessage());
       error.setTimestamp(System.currentTimeMillis());

       return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
   }
}
