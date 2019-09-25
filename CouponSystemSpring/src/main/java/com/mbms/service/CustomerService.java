package com.mbms.service;

import java.util.List;

import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Coupon;
import com.mbms.model.CouponCaregory;
import com.mbms.model.Customer;

public interface CustomerService {
	
	void setCustomer(Customer customer);

	Customer purchaseCoupon(long couponId) throws CouponSystemException ;

	List<Coupon> getAllCustomerPurchases(long customer_id) throws Exception;

	List<Coupon> couponByType(CouponCaregory couponCaregory) throws Exception;

	List<Coupon> couponByPrice(double price) throws Exception;



}