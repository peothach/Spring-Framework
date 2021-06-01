package com.studentmanager.entity;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "student_name")
    private String name;

    @Column(name = "student_address")
    private String address;
}
