package com.studentmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class StudentManagerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test_birthday(){
		LocalDate actual = LocalDate.of(1997, 11, 05);

		System.out.println(actual.getYear());

	}

}
