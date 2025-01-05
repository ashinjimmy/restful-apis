package com.project.student_management.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.student_management.Entity.Student;

@RestController
public class StudentController {

	@GetMapping("/info")
	public Student getInfo() {
		return new Student(5965, "Ashin", "Jimmy", "91 Thornbury", Arrays.asList("Microservices, CICD, Advance-Java, AWS"));
	}
	
	
}
