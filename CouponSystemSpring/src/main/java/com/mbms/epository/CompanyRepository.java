package com.mbms.epository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbms.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findByCompanyName(String companyName);

	void deleteById(int compId);

	Company findByCompanyNameAndPassword(String name, String password);
	
}
