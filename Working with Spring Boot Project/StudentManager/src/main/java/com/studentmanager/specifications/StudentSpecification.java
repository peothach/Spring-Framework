package com.studentmanager.specifications;

import com.studentmanager.entity.Student;
import com.studentmanager.entity.Student_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Component
public class StudentSpecification {

    public static Specification<Student> containsName(String name){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(
                            root.get(Student_.NAME)), "%" + name.toLowerCase(Locale.ROOT).trim() + "%");
        });
    }

    public static Specification<Student> containsAddress(String address){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(
                            root.get(Student_.ADDRESS)), "%" + address.toLowerCase(Locale.ROOT).trim() + "%");
        });
    }

    public static Specification<Student> containsBirthday(String birthday){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(
                            root.get(Student_.BIRTHDAY).as(String.class)), "%" + birthday.toLowerCase(Locale.ROOT).trim() + "%");
        });
    }

    public static Specification<Student> containsEmail(String email){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(
                            root.get(Student_.EMAIL)), "%" + email.toLowerCase(Locale.ROOT).trim() + "%");
        });
    }

    public static Specification<Student> containsPhone(String phone){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(
                            root.get(Student_.PHONE)), "%" + phone.toLowerCase(Locale.ROOT).trim() + "%");
        });
    }

    public static Specification<Student> containsGender(String gender){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(
                            root.get(Student_.GENDER)), "%" + gender.toLowerCase(Locale.ROOT).trim() + "%");
        });
    }

}
