package com.studentmanager.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllExceptions(Exception ex) {
        Payload error = new Payload();
        error.setMessage("Server Error!");
        error.setStatus(false);
        ex.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Valid with Parameter or PathVariable
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<?> handlerConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(i -> {
            String[] arrayPropertyPath = i.getPropertyPath().toString().split("\\.");
            String propertyPath = arrayPropertyPath[arrayPropertyPath.length - 1];
            String detailMessage = i.getMessage();
            errors.put(propertyPath, detailMessage);
        });

        Payload apiError = new Payload();
        apiError.setMessage("Valid param!");
        apiError.setDetails(errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PageNotFoundException.class)
    public final ResponseEntity<?> handlerPageNotFoundException(PageNotFoundException ex) {
        return new ResponseEntity<>(new Payload(ex.getMessage(), false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public final ResponseEntity<?> handleUserNotFoundException(StudentNotFoundException ex) {
        Payload error = new Payload();
        error.setMessage(ex.getMessage());
        error.setStatus(false);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyExistException.class)
    public final ResponseEntity<?> handlerStudentAlreadyExistException(StudentAlreadyExistException ex) {
        Payload payload = new Payload();
        payload.setMessage(ex.getMessage());
        payload.setStatus(false);
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentNameExistException.class)
    public final ResponseEntity<?> handlerStudentNameExistException(StudentNameExistException ex) {
        Payload payload = new Payload();
        payload.setMessage(ex.getMessage());
        payload.setStatus(false);
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Payload payload = new Payload();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        payload.setMessage("Valid data!!!");
        payload.setStatus(false);
        payload.setDetails(errors);
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }

}
