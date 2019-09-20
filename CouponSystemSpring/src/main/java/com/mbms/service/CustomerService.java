package com.mbms.service;

import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Customer;

public interface CustomerService {
	boolean performLogin(String name, String password) throws CouponSystemException;

	Customer getCustomerName(String name);

}