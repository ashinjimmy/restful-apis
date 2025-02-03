package com.project.student_management.service;

import java.util.List;

import com.project.student_management.Entity.Student;

public interface StudentService {

	Student getStudentInfo();

	List<Student> getStudnetsList();

	Student getStudnetById(long studentId);

	String deleteStudentById(long studentId);

	Student getStudentByQuery(long studentId, String firstName, String lastName);

	String createStudentInfo(Student student);

}
