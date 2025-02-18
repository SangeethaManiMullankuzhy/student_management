package com.fee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fee.service.FeeService;
import com.fee.validator.FeeValidator;
import com.library.so.FeeSO;


@RestController
@RequestMapping("fee")
public class FeeController {
	
	@Autowired
	FeeService feeService;
	
	@Autowired
	FeeValidator feeValidator;
	
	@PostMapping("/collectFees")
	public ResponseEntity<String> collectFees(@RequestBody FeeSO fee) {
		feeValidator.validate(fee);
		String successMsg = feeService.collectFees(fee);
		return ResponseEntity.ok(successMsg);
	}
	
	@GetMapping("/getFees")
	public List<FeeSO> getFees() {
		List<FeeSO> feeList = feeService.getFees();
		return feeList;
	}
	
	@GetMapping("/getFeesByStudentId/{studentId}")
	public List<FeeSO> getFeesByStudentId(@PathVariable Long studentId) {
		List<FeeSO> feeList = feeService.getFeesByStudentId(studentId);
		return feeList;
	}

}
