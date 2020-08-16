/**
 * 
 */
package com.spring.boot.springsecuritydemo.conroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.springsecuritydemo.domain.Student;

/**
 * @author Mayank
 *
 */
@RestController
@RequestMapping(path = "/management/api/v1/students")
public class StudentManagementController {
	
	private static final List<Student> students = Arrays.asList(
			new Student(1, "Clark"),
			new Student(2, "Bruce"),
			new Student(3, "Diane")
			);
	
	//hasRole('ROLE_'), hasAnyRole('ROLE_'), hasAuthority('permission'), hasAnyAuthority('permission')
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'ADMIN_TRAINEE')")
	public List<Student> getAllStudents(){
		return students;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('course:write')")
	public void registerStudents(@RequestBody Student student) {
		System.out.println(student);
	}
	
	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable Integer id) {
		System.out.println(id);
	}
	
	@PutMapping(path = "/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable Integer id, @RequestBody Student student) {
		System.out.println(String.format("%s %s", id, student));
	}

}
