package com.restfull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@RestController
public class HelloWorldRestController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("hello-world")
    public String sayStringHello(){
        return "Hello World with Restfull Webservice!";
    }

    @GetMapping("hello-world-object")
    public HelloWorld sayObjectHello(){

        return new HelloWorld("Hello World with Restfull Webservice!");
    }

    @GetMapping("/hello-world-internalization")
    public HelloWorld sayHelloWorldWithInternalization(Locale locale){
        return new HelloWorld(messageSource.getMessage("message", null, locale));
    }
}
