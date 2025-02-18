package com.library.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.so.FeeSO;

@FeignClient(name = "fee-service", url = "http://localhost:8081")
public interface FeeServiceInterface {
	
	@GetMapping("/fee/getFees")
	public List<FeeSO> getFees();
	
	@GetMapping("/fee/getFeesByStudentId/{studentId}")
	public List<FeeSO> getFeesByStudentId(@PathVariable Long studentId);

}
