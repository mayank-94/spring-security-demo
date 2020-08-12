/**
 * 
 */
package com.spring.boot.springsecuritydemo.conroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.springsecuritydemo.domain.Student;

/**
 * @author Mayank
 *
 */
@RestController
@RequestMapping(path = "/api/v1")
public class StudentController {
	
	private static final List<Student> students = Arrays.asList(
			new Student(1, "Clark"),
			new Student(2, "Bruce"),
			new Student(3, "Diane")
			);
	
	@GetMapping(path = "/welcome")
	public String helloWorld() {
		
		return "Hello World!";
	}
	
	@GetMapping(path = "/students/{studentId}")
	public Student getStudents(@PathVariable Integer studentId) {
		
		return students.stream()
			.filter(student -> student.getId().equals(studentId))
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("Student with Id "+studentId+" does not exist"));
	}
	
}
