package com.studentmanager.service.impl;

import com.studentmanager.entity.Student;
import com.studentmanager.reponsitoty.IStudentRepo;
import com.studentmanager.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    IStudentRepo studentRepo;

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        return studentRepo.findById(id);
    }
}
