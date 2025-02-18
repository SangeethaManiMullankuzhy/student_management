package com.fee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fee.entity.FeeType;

@Repository
public interface FeeTypeRepository extends JpaRepository<FeeType, Long>{

}
