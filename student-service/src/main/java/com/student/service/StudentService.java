package com.student.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.exception.StudentNotFoundException;
import com.library.exception.StudentUnderAgeException;
import com.library.so.StudentSO;
import com.student.entity.Student;
import com.student.repo.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	public List<StudentSO> getAllStudents(){
		List<Student> students = studentRepo.findAll();
		List<StudentSO> studentSOs = students.stream().map(student -> {
			StudentSO studentSO = new StudentSO();
		    BeanUtils.copyProperties(student, studentSO);
		    return studentSO;
		}).collect(Collectors.toList());
		return studentSOs;
	}
	
	public StudentSO searchById(Long id){
		Student student = studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
		StudentSO studentSO = new StudentSO();
		BeanUtils.copyProperties(student, studentSO);
		return studentSO;
	}
	
	public Student addStudent(Student student){
		student.setStudentFullName(student.getStudentFirstName()+ " " + student.getStudentLastName());
		LocalDate birthDate = LocalDate.parse(student.getDob().toString());
		LocalDate currentDate = LocalDate.now();
		Period age = Period.between(birthDate, currentDate);
		if(age.getYears() < 4) {
			throw new StudentUnderAgeException("Student cant be registered if below age 4");	
		}
		student.setAge(age.getYears());

		return studentRepo.save(student);
	}
	
	public Student updateStudent(Long id, Student student){
		Student existingStudent = studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id)); 
		BeanUtils.copyProperties(student, existingStudent, "studentId");
		return studentRepo.save(existingStudent); 
	}
	
	public void deleteStudent(Long id){
		Student existingStudent = studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id)); 
		studentRepo.delete(existingStudent); 
	}

}
