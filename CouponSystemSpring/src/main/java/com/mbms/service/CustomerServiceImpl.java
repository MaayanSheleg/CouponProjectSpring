package com.mbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbms.epository.CouponRepository;
import com.mbms.epository.CustomerRepository;
import com.mbms.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CouponRepository couponRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, CouponRepository couponRepository) {

		this.couponRepository = couponRepository;
		this.customerRepository = customerRepository;
	}

	public boolean performLogin(String name, String password) {

		Customer customer = customerRepository.findByCustomerNameAndPassword(name, password);
		if (customer == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Customer getCustomerName(String name) {
		return null;
	}
}
