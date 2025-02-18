package com.fee.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Fee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long referenceNo;
	private Long studentId;
	@JsonFormat(pattern = "dd MMM yyyy, HH:mm")
	private Date date;
	private double total;
	private String cardType;
	private String cardNumber;
	@OneToMany(mappedBy = "fee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FeeType> feeTypes = new ArrayList<FeeType>();

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

	public List<FeeType> getFeeTypes() {
		return feeTypes;
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

	public void setFeeTypes(List<FeeType> feeTypes) {
	  
		  this.feeTypes.clear(); 
		  this.feeTypes.addAll(feeTypes); 
		  for (FeeType feeType :feeTypes) { 
			  feeType.setFee(this); 
		  }
	  
		  this.feeTypes=feeTypes; 
	  }
	 
		/*
		 * public void addFeeType(FeeType feeType) { feeTypes.add(feeType);
		 * feeType.setFee(this); }
		 */
}
