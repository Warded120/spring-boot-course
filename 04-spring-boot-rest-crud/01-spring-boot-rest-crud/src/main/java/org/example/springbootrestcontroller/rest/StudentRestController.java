package org.example.springbootrestcontroller.rest;

import jakarta.annotation.PostConstruct;
import org.example.springbootrestcontroller.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    @PostConstruct
    public void initStudents() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Ivan", "Hrenevych"));
        theStudents.add(new Student("Dave", "mayble"));
        theStudents.add(new Student("John", "Doe"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    //define endpoint of "/students/{studentId}
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        //check if the index is valid
        if(studentId < 0 || studentId >= theStudents.size()) {
            throw new StudentNotFoundException("Student not found with id " + studentId);
        }

        //later we'll implement DB for this
        return theStudents.get(studentId);
    }
}
