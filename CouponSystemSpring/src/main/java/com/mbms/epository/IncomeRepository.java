package com.mbms.epository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbms.model.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

	List<Income> findAllByClientId(long clientId);
}
