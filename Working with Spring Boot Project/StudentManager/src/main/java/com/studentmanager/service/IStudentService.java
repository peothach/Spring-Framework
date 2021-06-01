package com.studentmanager.service;

import com.studentmanager.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    List<Student> findAll();

    Student createStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Long id);

    Optional<Student> findStudentById(Long id);
}
