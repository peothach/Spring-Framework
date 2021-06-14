package com.studentmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Student implements Serializable {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty( value = "Id of student",
            name = "id",
            dataType = "Long",
            example = "1")
    private Long id;

    @ApiModelProperty(value = "Name of student")
    @Column(name = "student_name")
    private String name;

    @ApiModelProperty(value = "Address of student")
    @Column(name = "student_address")
    private String address;

    @ApiModelProperty(value = "Birthday of student")
    @Column(name = "student_birthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty(value = "Email of student")
    @Column(name = "student_email")
    @Email(message = "Email is not valid!")
    private String email;

    @ApiModelProperty(value = "Phone of student")
    @Column(name = "student_phone")
    @Pattern(regexp = "^(\\+84)(\\d{9})$", message = "Phone is not valid!")
    private String phone;

    @ApiModelProperty(value = "Gender of student")
    @Pattern(regexp = "(M|F)", message = "Gender is not valid!")
    @Column(name = "student_gender")
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String gender;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(updatable = false)
    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDate lastModifiedDate;
}
