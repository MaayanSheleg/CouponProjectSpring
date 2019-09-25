package com.mbms.epository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbms.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByCustomerName(String customerName);
	Customer findByCustomerNameAndPassword(String name, String password);
}