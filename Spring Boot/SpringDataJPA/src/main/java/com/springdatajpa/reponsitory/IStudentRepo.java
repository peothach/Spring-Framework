package com.springdatajpa.reponsitory;

import com.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepo extends JpaRepository<Student, Long> {
}
