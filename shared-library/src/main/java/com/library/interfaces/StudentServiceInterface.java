package com.library.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.library.so.StudentSO;

@FeignClient(name = "student-service", url = "http://localhost:8080")
public interface StudentServiceInterface {
	
	@GetMapping("/student/searchAllStudents")
	public List<StudentSO> getAllStudents();
	
	@GetMapping("/student/searchById/{id}")
	public StudentSO searchById(@PathVariable Long id);
	
	

}
