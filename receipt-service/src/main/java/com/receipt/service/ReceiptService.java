package com.receipt.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.interfaces.FeeServiceInterface;
import com.library.interfaces.StudentServiceInterface;
import com.library.so.FeeSO;
import com.library.so.StudentSO;
import com.receipt.so.ReceiptSO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ReceiptService {
	
	@Autowired
	StudentServiceInterface studentServiceInterface;
	
	@Autowired
	FeeServiceInterface feeInterface;
	
    @CircuitBreaker(name = "receiptServiceCB", fallbackMethod = "fallbackForGetAllReceipts")
	public List<ReceiptSO> viewAllReceipts(){
		List<StudentSO> studentSOs =  studentServiceInterface.getAllStudents();
		List<FeeSO> feeSOs = feeInterface.getFees();
        Map<Long, StudentSO> studentMap = studentSOs.stream()
                .collect(Collectors.toMap(StudentSO::getStudentId, student -> student));
        return feeSOs.stream().map(fee -> {        	
        	StudentSO student = studentMap.get(fee.getStudentId());
            if (student != null) {
            	ReceiptSO receiptSO = new ReceiptSO();
                receiptSO.setStudentSO(student);
                receiptSO.setFeeSO(fee);
                return receiptSO;
             }
             return null;
             }).filter(Objects::nonNull).collect(Collectors.toList());	
	
	}
    
	public List<ReceiptSO> viewReceiptsByStudentId(Long studentId){
		StudentSO studentSO =  studentServiceInterface.searchById(studentId);
		List<FeeSO> feeSOs = feeInterface.getFeesByStudentId(studentId);

        return feeSOs.stream().map(fee -> {
            ReceiptSO receiptSO = new ReceiptSO();
            receiptSO.setStudentSO(studentSO);
            receiptSO.setFeeSO(fee);
            return receiptSO;
        }).collect(Collectors.toList());	
	
	}
    
    public List<ReceiptSO> fallbackForGetAllReceipts(Exception e) {
        return List.of(new ReceiptSO());
    }

}
