package com.studentmanager.reponsitoty;

import com.studentmanager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM student s WHERE CONCAT(s.name, ' ', s.address) LIKE %?#{age}%")
    List<Student> search(String keyword);

    @Query("SELECT s FROM student s"
            + " WHERE s.name LIKE %:#{#student.name != null ? #student.name:''}%"
            + " AND s.address LIKE %:#{#student.address != null ? #student.address:''}%")
    List<Student> filter(@Param("student") Student student);
}