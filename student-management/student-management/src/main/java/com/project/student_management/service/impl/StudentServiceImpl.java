package com.project.student_management.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.project.student_management.Entity.Student;
import com.project.student_management.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Override
	public Student getStudentInfo() {
		return new Student(5965, "Ashin", "Jimmy", "91 Thornbury", Arrays.asList("Microservices, CICD, Advance-Java, AWS"));
	}

}
