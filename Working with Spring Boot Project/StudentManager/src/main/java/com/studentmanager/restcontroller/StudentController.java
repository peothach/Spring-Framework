package com.studentmanager.restcontroller;

import com.studentmanager.entity.Student;
import com.studentmanager.exception.Payload;
import com.studentmanager.exception.RecordNotFoundException;
import com.studentmanager.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    IStudentService studentService;

    @GetMapping("students")
    public ResponseEntity<?> showAll(){
        List<Student> studentList = studentService.findAll();
        return new ResponseEntity(studentList, HttpStatus.OK);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<?> findStudentByID(@PathVariable Optional<Long> id){
        if (studentService.findStudentById(id.get()).isEmpty()){
            throw new RecordNotFoundException("Student not found!");
        }

        List<Student> studentList = studentService.findAll();
        return new ResponseEntity(studentList, HttpStatus.OK);
    }

    @PostMapping("students")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        Student st = studentService.createStudent(student);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @PutMapping("student/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Optional<Long> id){
        if (studentService.findStudentById(id.get()).isEmpty()){
            throw new RecordNotFoundException("Student not found!");
        }

        Student st = studentService.createStudent(student);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Optional<Long> id){
        if (studentService.findStudentById(id.get()).isEmpty()){
            throw new RecordNotFoundException("Student not found!");
        }

        studentService.deleteStudent(id.get());
        Payload payload = new Payload("Delete student successful!", true, null);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
