package com.project.student_management.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.student_management.Entity.Student;
import com.project.student_management.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Override
	public Student getStudentInfo() {
		return new Student(5965, "Ashin", "Jimmy", "91 Thornbury",
				Arrays.asList("Microservices, CICD, Advance-Java, AWS"));
	}

	@Override
	public List<Student> getStudnetsList() {
		List<Student> list = new ArrayList<>();
		list.add(new Student(1, "Ashin", "Jimmy", "91 Thornbury",
				Arrays.asList("Microservices, CICD, Advance-Java, AWS")));
		list.add(new Student(2, "Sharun", "Thomas", "91 Thornbury",
				Arrays.asList("Microservices, CICD, Advance-Java, AWS")));
		list.add(new Student(3, "Aswathy", "Leelamani", "Cyprus Graden",
				Arrays.asList("Machine Learning, Big Data, Advance-Java, Data Mining")));
		return list;
	}

	@Override
	public Student getStudnetById(long studentId) {
		return new Student(studentId, "Amanta", "Roy", "Alappat House",
				Arrays.asList("Medical Anatomy, General Medicine, Pediatric Nursing"));

	}

	@Override
	public String deleteStudentById(long studentId) {
		return new String("Student deleted successfully ....");
	}

	@Override
	public Student getStudentByQuery(long studentId, String firstName, String lastName) {
		return new Student(studentId, firstName, lastName, lastName, Arrays.asList("Ethical Hacking, Pisching, Incident Tracking"));
	}

	@Override
	public String createStudentInfo(Student student) {
		return "Created new student data";
	}

}
