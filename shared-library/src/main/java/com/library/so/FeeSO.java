package com.library.so;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FeeSO {
	private Long referenceNo;
	private Long studentId;
	@JsonFormat(pattern = "dd MMM yyyy, HH:mm")
	private Date date;
	private double total;
	private String cardType;
	private String cardNumber;
	private List<FeeTypeSO> feeTypes;
	
	public Long getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(Long referenceNo) {
		this.referenceNo = referenceNo;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<FeeTypeSO> getFeeTypes() {
		return feeTypes;
	}
	public void setFeeTypes(List<FeeTypeSO> feeTypes) {
		this.feeTypes = feeTypes;
	}

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	

}
