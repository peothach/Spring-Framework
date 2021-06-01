package com.studentmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        Payload error = new Payload("Server Error", false, details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<?> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        Payload error = new Payload();
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
