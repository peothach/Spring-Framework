package com.studentmanager.exception;

public class PageNotFoundException extends RuntimeException{

    public PageNotFoundException(String message){
        super(message);
    }
}
