package com.fee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fee.entity.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long>{
	
	public List<Fee> findByStudentId(Long studentId);

}
