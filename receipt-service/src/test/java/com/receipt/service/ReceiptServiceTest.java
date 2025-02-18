package com.receipt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.library.interfaces.FeeServiceInterface;
import com.library.interfaces.StudentServiceInterface;
import com.library.so.FeeSO;
import com.library.so.FeeTypeSO;
import com.library.so.StudentSO;
import com.receipt.so.ReceiptSO;


public class ReceiptServiceTest {
	
    @Mock
    private StudentServiceInterface studentServiceInterface;
    
    @Mock
    private FeeServiceInterface feeInterface;

    @InjectMocks
    private ReceiptService receiptService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testViewReceiptsByStudentId() {
        StudentSO student = new StudentSO();
        student.setStudentId(1L);
        student.setStudentName("Sara");
        student.setGrade("5");
        student.setMobileNo("+971 736452635");
        student.setParentName("Mathew");
        student.setSchoolName("PWS School");
        
        FeeSO feeSO = new FeeSO();
    	FeeTypeSO feeTypeSO1 = new FeeTypeSO();
    	feeTypeSO1.setAmount(100.00);
    	feeTypeSO1.setFeeDescription("Tution Fee");
    	FeeTypeSO feeTypeSO2 = new FeeTypeSO();
    	feeTypeSO2.setAmount(50.00);
    	feeTypeSO2.setFeeDescription("Bus Fee");
    	feeSO.setFeeTypes(Arrays.asList(feeTypeSO1,feeTypeSO2));
    	feeSO.setStudentId(1L);
    	
    	when(studentServiceInterface.searchById(any())).thenReturn(student);
    	when(feeInterface.getFeesByStudentId(any())).thenReturn(Arrays.asList(feeSO));
    	
    	//When
    	List<ReceiptSO> receiptSOs =  receiptService.viewReceiptsByStudentId(1L);
    	
    	//Then
        assertEquals(1, receiptSOs.size());
        assertEquals("Sara", receiptSOs.get(0).getStudentSO().getStudentName());
    }

}
