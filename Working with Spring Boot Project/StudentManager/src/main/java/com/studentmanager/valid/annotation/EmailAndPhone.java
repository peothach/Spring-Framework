package com.studentmanager.valid.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailAndPhoneValidation.class)
public @interface EmailAndPhone {

    String message() default "Email and Phone are required!!!";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
