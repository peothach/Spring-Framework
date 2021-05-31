package com.springdatajpa;

import com.springdatajpa.entity.Student;
import com.springdatajpa.service.IStudentService;
import com.springdatajpa.service.StudentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDataJpaApplication{

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringDataJpaApplication.class, args);
		IStudentService studentService = applicationContext.getBean(StudentServiceImpl.class);
		Student student = new Student("John", "US");

		//Create
		student = studentService.createStudent(student);
		System.out.println("List student after create: ");
		System.out.println(studentService.findAll());
		System.out.println("----------------------------");

		//Update
		student.setAddress("UK");
		studentService.updateStudent(student);
		System.out.println("List student after update: ");
		System.out.println(studentService.findAll());
		System.out.println("----------------------------");

		//Delete
		studentService.deleteStudent(student.getId());
		System.out.println("List student after delete: ");
		System.out.println(studentService.findAll());
		System.out.println("----------------------------");

		System.exit(0);
	}

}
