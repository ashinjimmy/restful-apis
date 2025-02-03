package com.project.student_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest.Headers;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;

import com.project.student_management.Entity.Student;
import com.project.student_management.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	@GetMapping("/student")
	public ResponseEntity<Student> getStudentInfo() {
		return ResponseEntity.ok().header("custom-header", "text").body(service.getStudentInfo());
	}

	@GetMapping("/")
	public List<Student> getStudentsList() {
		return service.getStudnetsList();
	}

	// http://localhost:7070/student/1
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable("id") long StudentId) {
		return service.getStudnetById(StudentId);
	}

	// http://localhost:7070/student/query?id=1&firstName
	@GetMapping("/query")
	public Student getStudentByQuery(@RequestParam(name = "id", required = true) long StudentId,
			@RequestParam String firstName, @RequestParam String lastName) {
		return service.getStudentByQuery(StudentId, firstName, lastName);
	}

	@DeleteMapping("/{id}")
	public String deleteStudentById(@PathVariable("id") long StudentId) {
		return service.deleteStudentById(StudentId);
	}

	@PostMapping("/create")
	//@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		return new ResponseEntity<String>(service.createStudentInfo(student), HttpStatus.CREATED);
	}

	@PutMapping("/{id}/update")
	public Student updateStudentInfo(@RequestBody Student student, @PathVariable("id") long sdtudentId) {
		return student;
	}

}
