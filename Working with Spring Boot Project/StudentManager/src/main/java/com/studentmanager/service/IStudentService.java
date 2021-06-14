package com.studentmanager.service;

import com.studentmanager.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface IStudentService {

    /**
     * Test Evironment
     * @return
     */
    String environment();

    List<Student> findAll();

    /**
     * Pagination
     * @param pageable
     * @return List<Student>
     */
    List<Student> findStudentByPage(int page, int size);

    /**
     * Sort Student
     * @param sort
     * @return List<Student>
     */
    List<Student> findStudentAndSort(Sort sort);

    Optional<Student> findStudentByName(String name);
    /**
     * Search Student
     * @param keyword
     * @return List<Student>
     */
    List<Student> search(String keyword);

    /**
     * Filter Student
     * @param Student
     * @return List<Student>
     */
    List<Student> filter(Student student);

    Student createStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Long id);

    Optional<Student> findStudentById(Long id);
}
