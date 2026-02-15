package com.example.studentLogIn.Controller;

import com.example.studentLogIn.model.Student;
import com.example.studentLogIn.services.impl.StudentServiceImpliment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/student")

public class RestApiController {
   // private Student st;
    @Autowired
    private StudentServiceImpliment imp;
    @GetMapping("{id}")
    public Student getStudentById(@PathVariable("id") String id)  {
        if(id.isEmpty())
            throw new RuntimeException("id can't be empty");
        return imp.getStudentById(id);
    }
    @GetMapping()
    public List<Student> getAllStudent() {

        return imp.getAllStudents();
    }
    @PostMapping
    public String CreateStudent(@RequestBody Student student){
        return imp.addStudent(student);
    }

    @PutMapping
    public String updateStudent(@RequestBody Student student){
        return imp.updateStudent(student);
    }
    @DeleteMapping("{id}")
    public String CreateStudent(@PathVariable("id") String id){
       if(id.isEmpty())
           throw new RuntimeException("id can't be empty");
        return imp.deleteStudent(id);
    }

}
