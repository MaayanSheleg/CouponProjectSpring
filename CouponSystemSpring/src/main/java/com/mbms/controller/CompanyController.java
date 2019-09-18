package com.mbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.epository.CouponRepository;
import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Company;
import com.mbms.model.Coupon;
import com.mbms.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private CouponRepository couponRepository;

	@GetMapping("/companyById/{id}")
	public Company companyById(@PathVariable int id) throws CouponSystemException {
		return companyService.getCompany(id);
	}
	
	
	@PostMapping("/insertCoupon/{companyId}")
	public ResponseEntity<Coupon> insertCoupon(@RequestBody Coupon coupon, @RequestBody int companyId) throws CouponSystemException {

		Coupon coupi=companyService.insertCoupon(coupon, companyId);
		ResponseEntity<Coupon> result = new ResponseEntity<Coupon>(coupi,HttpStatus.OK);
		return result;

	}

}
