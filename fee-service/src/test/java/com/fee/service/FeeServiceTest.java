package com.fee.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fee.entity.Fee;
import com.fee.repo.FeeRepository;
import com.library.so.FeeSO;
import com.library.so.FeeTypeSO;

public class FeeServiceTest {
	
    @Mock
    private FeeRepository feeRepository;

    @InjectMocks
    private FeeService feeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCollectFee() {
        //Given
    	FeeSO feeSO = new FeeSO();
    	feeSO.setStudentId(1L);
    	feeSO.setCardType("Master Card");
    	FeeTypeSO feeTypeSO1 = new FeeTypeSO();
    	feeTypeSO1.setAmount(100.00);
    	feeTypeSO1.setFeeDescription("Tution Fee");
    	FeeTypeSO feeTypeSO2 = new FeeTypeSO();
    	feeTypeSO2.setAmount(50.00);
    	feeTypeSO2.setFeeDescription("Bus Fee");
    	feeSO.setFeeTypes(Arrays.asList(feeTypeSO1,feeTypeSO2));

    	Fee fee = new Fee();
    	fee.setReferenceNo(1L);
        
        when(feeRepository.save(any(Fee.class))).thenReturn(fee);

        //When
        String msg = feeService.collectFees(feeSO);

        //Then
        verify(feeRepository, times(1)).save(any(Fee.class));
    }

}
