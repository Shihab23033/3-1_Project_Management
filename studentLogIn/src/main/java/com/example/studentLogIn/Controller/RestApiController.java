package com.example.studentLogIn.Controller;

import com.example.studentLogIn.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/student")
public class RestApiController {
    private Student st;

    @GetMapping("{id}")
    public Student getStudent( String id){

        return st;
    }
    @PostMapping
    public String CreateStudent(@RequestBody Student student){
        this.st = student;
        return "Student Created Successfully";
    }

    @PutMapping
    public String updateStudent(@RequestBody Student student){
        this.st = student;
        return "Student Updated Successfully";
    }
    @DeleteMapping("{id}")
    public String CreateStudent(String id){
        if(Objects.equals(id, st.getId())) st=null;
        return "Student Deleted Successfully";
    }

}
