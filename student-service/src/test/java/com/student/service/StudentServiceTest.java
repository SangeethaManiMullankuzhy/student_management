package com.student.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.library.so.StudentSO;
import com.student.entity.Student;
import com.student.repo.StudentRepository;

public class StudentServiceTest {
	
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testGetAllStudents() {
    	//Given
        Student student1 = setStudent();
        
        Student student2 = new Student();
        student2.setStudentFirstName("Kate");
        student2.setStudentLastName("John");
        student2.setGrade("5");
        student2.setMobileNo("+971 736452635");
        student2.setParentName("Mathew");
        student2.setSchoolName("PWS School");

        List<Student> studentList = Arrays.asList(student1, student2);
        when(studentRepository.findAll()).thenReturn(studentList);

        //When
        List<StudentSO> result = studentService.getAllStudents();

        //Then
        assertEquals(2, result.size());
        assertEquals("Sara", result.get(0).getStudentFirstName());
        verify(studentRepository, times(1)).findAll();
    }
    
    @Test
    void givenStudentIdWhenSearchByIdCalledReturnStudentDetails() {
    	//Given
    	Long studentId = 1L;
        Student student1 = setStudent();
        student1.setStudentId(studentId);

        when(studentRepository.findById(any())).thenReturn(Optional.of(student1));

        //When
        StudentSO studentFound = studentService.searchById(studentId);

        //Then
        assertEquals("Sara", studentFound.getStudentFirstName());
        verify(studentRepository, times(1)).findById(studentId);
    }	

    @Test
    void givenStudentDetailsVerifySavedSuccessfully() {
    	Student student = setStudent();
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        
        //When
        Student savedStudent = studentService.addStudent(student);

        //Then
        assertNotNull(savedStudent);
        assertEquals("Sara John", savedStudent.getStudentFullName());
        verify(studentRepository, times(1)).save(any(Student.class));
    }
    
	private Student setStudent() {
		Student student1 = new Student();

		try {
		student1.setStudentFirstName("Sara");
		student1.setStudentLastName("John");
        student1.setGrade("1");
        student1.setMobileNo("+971 736452432");
        student1.setParentName("John");
        student1.setSchoolName("PWS School");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        LocalDate dob = LocalDate.of(1995, 5, 10);
        student1.setDob(dob);

		}catch (Exception e) {
			// TODO: handle exception
		}
		return student1;
	}	

}
