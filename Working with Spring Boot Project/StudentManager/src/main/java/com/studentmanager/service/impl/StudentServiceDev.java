package com.studentmanager.service.impl;

import com.studentmanager.entity.Student;
import com.studentmanager.reponsitoty.IStudentRepo;
import com.studentmanager.service.IStudentService;
import com.studentmanager.specifications.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("dev")
public class StudentServiceDev implements IStudentService {

    @Autowired
    IStudentRepo studentRepo;

    @Override
    public String environment() {
        return "DEV";
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
        PageRequest pageRequest = PageRequest.of(page - 1, size);
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
    public List<Student> search(String keyword) {
        if (keyword == null) return studentRepo.findAll();
        return studentRepo.findAll(
                Specification.where(
                        StudentSpecification.containsName(keyword)
                                .or(StudentSpecification.containsAddress(keyword))
                                .or(StudentSpecification.containsGender(keyword))
                                .or(StudentSpecification.containsBirthday(keyword))
                                .or(StudentSpecification.containsPhone(keyword))
                                .or(StudentSpecification.containsEmail(keyword))
                )
        );
    }

    @Override
    public List<Student> filter(Student student) {
        if (student == null) return studentRepo.findAll();
        System.out.println(student.getName());
        System.out.println(student.getBirthday());

        return studentRepo.findAll(
                Specification.where(
                        StudentSpecification.containsName(student.getName()!=null?student.getName():"")
                                .and(StudentSpecification.containsAddress(student.getAddress()!=null?student.getAddress():""))
                                .and(StudentSpecification.containsGender(student.getGender()!=null?student.getGender():""))
                                .and(StudentSpecification.containsBirthday(student.getBirthday()!=null?student.getBirthday().toString():""))
                                .and(StudentSpecification.containsPhone(student.getPhone()!=null?student.getPhone():""))
                                .and(StudentSpecification.containsEmail(student.getEmail()!=null?student.getEmail():""))
                )
        );
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepo.saveAndFlush(student);
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
