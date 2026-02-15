package com.example.studentLogIn.services;

import com.example.studentLogIn.model.Student;

import java.util.List;

public interface StudentService {
    public Student getStudentById(String id);
    public List<Student> getAllStudents();
    public String addStudent(Student student);
    public String updateStudent(Student student);
    public String deleteStudent(String id);
}
