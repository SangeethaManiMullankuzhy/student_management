package com.receipt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.receipt.service.ReceiptService;
import com.receipt.so.ReceiptSO;

@RestController
@RequestMapping("receipt")
public class ReceiptController {
	
	@Autowired
	ReceiptService receiptService;
	
	@GetMapping("/viewAllReceipts")
	public List<ReceiptSO> viewAllReceipts(){
		return receiptService.viewAllReceipts();
	}
	
	@GetMapping("/viewReceiptsByStudentId/{studentId}")
	public List<ReceiptSO> viewReceiptsByStudentId(@PathVariable Long studentId){
		return receiptService.viewReceiptsByStudentId(studentId);
	}



}
