package com.fee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fee.entity.Fee;
import com.fee.entity.FeeType;
import com.fee.repo.FeeRepository;
import com.fee.repo.FeeTypeRepository;
import com.library.so.FeeSO;
import com.library.so.FeeTypeSO;

@Service
public class FeeService {
	
	@Autowired
	FeeRepository feeRepository;
	
	@Autowired
	FeeTypeRepository feeTypeRepository;
	
	public String collectFees(FeeSO feeSO) {
		Fee fee = new Fee();
		BeanUtils.copyProperties(feeSO, fee, "feeTypes");
		List<FeeType> feeTypeList = new ArrayList<FeeType>();
		double total = 0.0;
		for (FeeTypeSO feeTypeSO :feeSO.getFeeTypes()) { 
			FeeType feeType = new FeeType();
			BeanUtils.copyProperties(feeTypeSO, feeType);
			total += feeTypeSO.getAmount();
			feeType.setFee(fee);
			feeTypeList.add(feeType);
		}
		fee.setFeeTypes(feeTypeList); 
		fee.setTotal(total);
		Fee feeRet = feeRepository.save(fee);
		return "Fees submitted successfully!! Reference Id : " + feeRet.getReferenceNo() ;
	}
	
	public List<FeeSO> getFees() {
		List<Fee> feeList =  feeRepository.findAll();
		List<FeeSO> feeSOs = feeList.stream().map(fee -> {
			FeeSO feeSO = new FeeSO();
		    BeanUtils.copyProperties(fee, feeSO);
			List<FeeTypeSO> feeTypeSOs = new ArrayList<FeeTypeSO>();
			for (FeeType feeType :fee.getFeeTypes()) { 
				FeeTypeSO feeTypeSO = new FeeTypeSO();
				BeanUtils.copyProperties(feeType, feeTypeSO);
				feeTypeSOs.add(feeTypeSO);
			}
			feeSO.setFeeTypes(feeTypeSOs);
		    return feeSO;
		}).collect(Collectors.toList());
		return feeSOs;
	}
	
	public List<FeeSO> getFeesByStudentId(Long studentId) {
		List<Fee> feeList =  feeRepository.findByStudentId(studentId);
		List<FeeSO> feeSOs = feeList.stream().map(fee -> {
			FeeSO feeSO = new FeeSO();
		    BeanUtils.copyProperties(fee, feeSO);
			List<FeeTypeSO> feeTypeSOs = new ArrayList<FeeTypeSO>();
			for (FeeType feeType :fee.getFeeTypes()) { 
				FeeTypeSO feeTypeSO = new FeeTypeSO();
				BeanUtils.copyProperties(feeType, feeTypeSO);
				feeTypeSOs.add(feeTypeSO);
			}
			feeSO.setFeeTypes(feeTypeSOs);
		    return feeSO;
		}).collect(Collectors.toList());
		return feeSOs;
	}

}
