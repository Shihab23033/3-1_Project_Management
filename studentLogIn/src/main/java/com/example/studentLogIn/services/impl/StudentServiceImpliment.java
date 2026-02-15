package com.example.studentLogIn.services.impl;

import com.example.studentLogIn.model.Student;
import com.example.studentLogIn.repository.StudentRepo;
import com.example.studentLogIn.services.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class StudentServiceImpliment implements StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Override
    public Student getStudentById(String id) {
        return  studentRepo.findById(id).get();
    }

    @Override
    public List<Student> getAllStudents() {

        return  studentRepo.findAll();
    }

    @Override
    public String addStudent(Student student) {
        studentRepo.save(student);
        return "Success";
    }

    @Override
    public String updateStudent(Student student) {
        studentRepo.save(student);
        return "Success";
    }

    @Override
    public String deleteStudent(String id) {
        studentRepo.deleteById(id);
        return "Deleted id "+ id;
    }
}
