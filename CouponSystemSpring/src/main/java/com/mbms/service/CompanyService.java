package com.mbms.service;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Company;
import com.mbms.model.Coupon;
import com.mbms.model.CouponCaregory;

public interface CompanyService {
	
	Coupon createCoupon(Coupon coupon) throws Exception;

	boolean checkIfTitleAlreadyExists(String title);

	void updateCoupon(Coupon coupon, Date endDate, double price);
	
	void updateCoupon(Coupon coupon, java.util.Date endDate, double price);

	Company getCompany(int id);

	void deleteCoupon(int couponId) throws Exception;

	void setCompany(Company company);

	List<Coupon> getAllCompanyCoupons(int company_id) throws Exception;

	List<Coupon> couponByPrice(double price) throws Exception;

	List<Coupon> couponByCouponType(CouponCaregory couponType) throws Exception;

	List<Coupon> couponByDate(Date endDate) throws Exception;

	List<Coupon> couponByDate(java.util.Date endDate) throws Exception;

	

}
