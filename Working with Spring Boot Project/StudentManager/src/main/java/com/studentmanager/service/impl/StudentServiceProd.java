package com.studentmanager.service.impl;

import com.studentmanager.entity.Student;
import com.studentmanager.reponsitoty.IStudentRepo;
import com.studentmanager.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Profile("prod")
@Transactional
public class StudentServiceProd implements IStudentService {

    @Autowired()
    IStudentRepo studentRepo;

    @Override
    public String environment() {
        return "PROD";
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public List<Student> findStudentByPage(int page, int size) {
        // Lấy ra 5 user đầu tiên
        // PageRequest.of(0,5) tương đương với lấy ra page đầu tiên, và mỗi page sẽ có 5 phần tử
        // PageRequest là một đối tượng kế thừa Pageable
        page --;
        PageRequest pageRequest = PageRequest.of(page, size);
        return studentRepo.findAll(pageRequest).getContent();
    }

    @Override
    public List<Student> findStudentAndSort(Sort sort) {
        return studentRepo.findAll(sort);
    }

    @Override
    public Optional<Student> findStudentByName(String name) {
        return studentRepo.findByName(name);
    }

    @Override
    public List<Student> search(Optional<String> keyword) {
        if(!keyword.isPresent()) return studentRepo.findAll();
        return studentRepo.search(keyword.get());
    }

    @Override
    public List<Student> filter(Student student) {
        if(student == null) return studentRepo.findAll();

        return studentRepo.filter(student);
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
