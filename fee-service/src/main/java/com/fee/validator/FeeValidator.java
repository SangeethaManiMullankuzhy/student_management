package com.fee.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.interfaces.StudentServiceInterface;
import com.library.so.FeeSO;

@Component
public class FeeValidator {
	
	@Autowired
	StudentServiceInterface studentServiceInterface;
	
	public void validate(FeeSO feeSO) {
		studentServiceInterface.searchById(feeSO.getStudentId());		
	}

}
