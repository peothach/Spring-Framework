package com.studentmanager.valid.annotation;

import com.studentmanager.entity.Student;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAndPhoneValidation implements ConstraintValidator<EmailAndPhone, Student> {

    @Override
    public void initialize(EmailAndPhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(Student student, ConstraintValidatorContext context) {
        if(student.getEmail() == null && student.getName() == null){
            return false;
        }
        return true;
    }
}
