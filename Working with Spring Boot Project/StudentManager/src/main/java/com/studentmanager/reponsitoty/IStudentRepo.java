package com.studentmanager.reponsitoty;

import com.studentmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Long> {
}
