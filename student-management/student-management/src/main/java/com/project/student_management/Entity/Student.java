package com.project.student_management.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
	
	private long id;
	private String firstName;
	private String lastName;
	private String address;
	private List<String> courses;
	

}
