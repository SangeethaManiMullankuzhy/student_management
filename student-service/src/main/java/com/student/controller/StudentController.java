package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.so.StudentSO;
import com.student.entity.Student;
import com.student.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("student")
@Tag(name = "Student API", description = "Operations related to students")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
    @Operation(summary = "Get all students", description = "Fetches list of all students")
	@GetMapping("/searchAllStudents")
	public List<StudentSO> getAllStudents(){
		List<StudentSO> studenList = studentService.getAllStudents();
		return studenList;
	}
	
    @Operation(summary = "Search by id", description = "Fetches student details of given student id")
	@GetMapping("/searchById/{id}")
	public StudentSO searchById(@PathVariable Long id) {
		StudentSO studentSO = studentService.searchById(id);
		return studentSO;
	}
	
    @Operation(summary = "Add student", description = "Add a new student")
	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student newStudent = studentService.addStudent(student);
		return ResponseEntity.ok(newStudent);
	}
	
    @Operation(summary = "Update student", description = "Update existing student")
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
		Student updatedStudent = studentService.updateStudent(id, student);
		return ResponseEntity.ok(updatedStudent);
	}
	
    @Operation(summary = "Delete student", description = "Delete existing student")
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.ok("Student deleted successfully!");
	}

}
