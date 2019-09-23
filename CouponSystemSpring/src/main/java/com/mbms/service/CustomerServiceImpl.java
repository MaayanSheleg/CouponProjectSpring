package com.mbms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbms.epository.CouponRepository;
import com.mbms.epository.CustomerRepository;
import com.mbms.exceptions.CouponSystemException;
import com.mbms.login.CouponClientFacade;
import com.mbms.login.LoginType;
import com.mbms.model.ClientType;
import com.mbms.model.Coupon;
import com.mbms.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService, CouponClientFacade {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;

	private Customer customer;
	
	public void purchaseCoupon (long couponId) throws CouponSystemException {
		Coupon coupon = couponRepository.findById((int)couponId).get();
		
		if (coupon==null) {
			throw new CouponSystemException("This coupon doesn't exists, please thy another one");
		}
	}

	@Override
	public Customer getCustomerName(String name) {
		return null;
	}

	@Override
	public CouponClientFacade login(String name, String password, LoginType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean performLogin(String name, String password) throws CouponSystemException {
		// TODO Auto-generated method stub
		return false;
	}

	public void setCustomer(Customer customer) {
		this.customer=customer;
		
	}
}
