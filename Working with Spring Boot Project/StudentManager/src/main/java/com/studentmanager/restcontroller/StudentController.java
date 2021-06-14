package com.studentmanager.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/api/test/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
}
