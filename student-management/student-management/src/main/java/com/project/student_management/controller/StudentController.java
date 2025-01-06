package com.project.student_management.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.student_management.Entity.Student;
import com.project.student_management.service.StudentService;

@RestController
public class StudentController {
	
	private StudentService service;
	
	public StudentController(StudentService service) {
		this.service = service;
	}

	@GetMapping("/info")
	public Student getInfo() {
		return service.getStudentInfo();
	}
	
	
}
