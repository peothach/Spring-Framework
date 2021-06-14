package com.studentmanager.valid.entity;


import com.studentmanager.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;

@Component
public class CustomValidatorStudent implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
        int ageStudent = Period.between(student.getBirthday(), LocalDate.now()).getYears();
        // Valid age from 18 to 80.
        if(ageStudent < 18 || ageStudent > 80){
            errors.rejectValue("birthday",
                    null,
                    "Age must be greatest than 18 and less than 80!!!");
        }

        if(student.getEmail() == null && student.getPhone() == null){
            errors.rejectValue("phone",
                    null,
                    "Phone or email cannot be empty at the same time");
            errors.rejectValue("email",
                    null,
                    "Phone or email cannot be empty at the same time");
        }

    }
}
