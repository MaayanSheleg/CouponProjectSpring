package com.mbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbms.epository.CouponRepository;
import com.mbms.epository.CustomerRepository;
import com.mbms.login.CouponClientFacade;
import com.mbms.login.LoginType;
import com.mbms.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService, CouponClientFacade {
	
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
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public CouponClientFacade login(String name, String password, LoginType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteCoupon(int couponId) {

		
	}
}
