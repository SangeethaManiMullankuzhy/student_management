package com.student.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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
        student2.setStudentName("Sara");
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
        assertEquals("Kate", result.get(0).getStudentName());
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
        assertEquals("Kate", studentFound.getStudentName());
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
        assertEquals("Kate", savedStudent.getStudentName());
        verify(studentRepository, times(1)).save(any(Student.class));
    }
    
	private Student setStudent() {
		Student student1 = new Student();
        student1.setStudentName("Kate");
        student1.setGrade("1");
        student1.setMobileNo("+971 736452432");
        student1.setParentName("John");
        student1.setSchoolName("PWS School");
		return student1;
	}	

}
