package com.springdatajpa.service;

import com.springdatajpa.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IStudentService {

    Student createStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(long id);

     List<Student> findAll();
}
