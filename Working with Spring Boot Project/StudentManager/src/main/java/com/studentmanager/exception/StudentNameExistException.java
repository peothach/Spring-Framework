package com.studentmanager.exception;

public class StudentNameExistException extends RuntimeException{

    public StudentNameExistException(String message){
        super(message);
    }
}
