package com.studentmanager.valid.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {

    @Override
    public void initialize(Age constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
        int ageStudent = Period.between(LocalDate.now(), localDate).getYears();
        boolean isValid = (ageStudent >=18 && ageStudent <=80)? true:false;
        return isValid;
    }
}
