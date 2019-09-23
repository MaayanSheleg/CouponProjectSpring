package com.mbms.epository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbms.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer>{

}
