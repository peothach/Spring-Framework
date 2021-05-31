package com.springdatajpa.service;

import com.springdatajpa.entity.Student;
import com.springdatajpa.reponsitory.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService{

    @Autowired
    private IStudentRepo studentRepo;

    @Override
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }
}
