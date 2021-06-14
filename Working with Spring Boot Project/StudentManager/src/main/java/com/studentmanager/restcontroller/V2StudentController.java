package com.studentmanager.restcontroller;

import com.studentmanager.entity.Student;
import com.studentmanager.exception.StudentNotFoundException;
import com.studentmanager.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class V2StudentController {

    @Autowired
    IStudentService studentService;

    // Media Type Versioning
    @GetMapping(value = "students/{id}/produces", produces = "application/vnd.students.app-v1+json")
    public ResponseEntity<?> findStudentByIDMediaTypeV1(@PathVariable Long id){
        Map<Object, Object> result = new HashMap<>();
        Optional<Student> student = studentService.findStudentById(id);
        // Check null
        student.orElseThrow(() -> {
            throw new StudentNotFoundException("Student not found!!!");
        });

        result.put("versioning", "1");
        result.put("type", "Media Type Versioning");
        result.put("student", student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "students/{id}/produces", produces = "application/vnd.students.app-v2+json")
    public ResponseEntity<?> findStudentByIDMediaTypeV2(@PathVariable Long id){
        Map<Object, Object> result = new HashMap<>();
        Optional<Student> student = studentService.findStudentById(id);
        // Check null
        student.orElseThrow(() -> {
            throw new StudentNotFoundException("Student not found!!!");
        });

        result.put("versioning", "2");
        result.put("type", "Media Type Versioning");
        result.put("student", student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // URI Versioning
    @GetMapping("/api/v1/students/{id}")
    public ResponseEntity<?> findStudentByIDV1(@PathVariable Long id){
        Map<Object, Object> result = new HashMap<>();
        Optional<Student> student = studentService.findStudentById(id);
        // Check null
        student.orElseThrow(() -> {
            throw new StudentNotFoundException("Student not found!!!");
        });

        result.put("versioning", "1");
        result.put("type", "URI Versioning");
        result.put("student", student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/api/v2/students/{id}")
    public ResponseEntity<?> getStudentByIDV2(@PathVariable Long id){
        Map<Object, Object> result = new HashMap<>();
        Optional<Student> student = studentService.findStudentById(id);
        // Check null
        student.orElseThrow(() -> {
            throw new StudentNotFoundException("Student not found!!!");
        });

        result.put("versioning", "2");
        result.put("type", "URI Versioning");
        result.put("student", student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Request Parameter versioning
    @GetMapping(value = "students/{id}/param", params = "version=1")
    public ResponseEntity<?> findStudentByIDParamV1(@PathVariable Long id){
        Map<Object, Object> result = new HashMap<>();
        Optional<Student> student = studentService.findStudentById(id);
        // Check null
        student.orElseThrow(() -> {
            throw new StudentNotFoundException("Student not found!!!");
        });

        result.put("versioning", "1");
        result.put("type", "Parameter Versioning");
        result.put("student", student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "students/{id}/param", params = "version=2")
    public ResponseEntity<?> findStudentByIDParamV2(@PathVariable Long id){
        Map<Object, Object> result = new HashMap<>();
        Optional<Student> student = studentService.findStudentById(id);
        // Check null
        student.orElseThrow(() -> {
            throw new StudentNotFoundException("Student not found!!!");
        });

        result.put("versioning", "2");
        result.put("type", "Parameter Versioning");
        result.put("student", student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Headers Versioning
    @GetMapping(value = "students/{id}/header", headers = "X-API-VERSION=1")
    public ResponseEntity<?> findStudentByIDHeaderV1(@PathVariable Long id){
        Map<Object, Object> result = new HashMap<>();
        Optional<Student> student = studentService.findStudentById(id);
        // Check null
        student.orElseThrow(() -> {
            throw new StudentNotFoundException("Student not found!!!");
        });

        result.put("versioning", "1");
        result.put("type", "Header Versioning");
        result.put("student", student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "students/{id}/header", headers = "X-API-VERSION=2")
    public ResponseEntity<?> findStudentByIDHeaderV2(@PathVariable Long id){
        Map<Object, Object> result = new HashMap<>();
        Optional<Student> student = studentService.findStudentById(id);
        // Check null
        student.orElseThrow(() -> {
            throw new StudentNotFoundException("Student not found!!!");
        });

        result.put("versioning", "2");
        result.put("type", "Header Versioning");
        result.put("student", student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
