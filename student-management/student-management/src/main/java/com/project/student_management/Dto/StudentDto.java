package com.project.student_management.Dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

	private long id;
	private String firstName;
	private String lastName;
	private List<String> courses;

}
