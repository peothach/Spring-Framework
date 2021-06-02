package com.studentmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "student_birthday")
    private LocalDate birthday;
}
