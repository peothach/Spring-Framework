package com.studentmanager.restcontroller;

import com.studentmanager.entity.Student;
import com.studentmanager.exception.Payload;
import com.studentmanager.exception.RecordNotFoundException;
import com.studentmanager.service.IStudentService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping("students/paging")
    public ResponseEntity<?> showStudentByPage(@RequestParam(required = false, defaultValue = "0") Integer page,
                                               @RequestParam(required = false, defaultValue = "3") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Student> studentList = studentService.findStudentByPage(pageRequest);
        return new ResponseEntity(studentList, HttpStatus.OK);
    }

    @GetMapping("students/sort")
    public ResponseEntity<?> showStudentByPage(@RequestParam(required = false, defaultValue = "name") String field,
                                               @RequestParam(required = false, defaultValue = "ASC") String sort){
        Sort sortable = Sort.by(field);
        sortable = sort.equals("ASC") ? sortable.ascending(): sortable.descending();

        return new ResponseEntity<>(studentService.findStudentAndSort(sortable), HttpStatus.OK);
    }

    @GetMapping("students/search")
    public ResponseEntity<?> showStudentByKeyWord(@RequestParam(required = false) Optional<String> keyword){
        return new ResponseEntity<>(studentService.search(keyword), HttpStatus.OK);
    }

    @GetMapping("students/filter")
    public ResponseEntity<?> filterStudent(@ModelAttribute Student student){
        return new ResponseEntity<>(studentService.filter(student), HttpStatus.OK);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<?> findStudentByID(@PathVariable Optional<Long> id){
        if (studentService.findStudentById(id.get()).isEmpty()){
            throw new RecordNotFoundException("Student not found!");
        }

        Optional<Student> studentList = studentService.findStudentById(id.get());
        System.out.println(studentList.get().getBirthday());
        return new ResponseEntity(studentList.get(), HttpStatus.OK);
    }

    @PostMapping("students")
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        System.out.println(student);
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
