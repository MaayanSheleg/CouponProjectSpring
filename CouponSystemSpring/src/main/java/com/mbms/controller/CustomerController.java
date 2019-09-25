package com.mbms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.epository.CouponRepository;
import com.mbms.exceptions.CouponSystemException;
import com.mbms.login.LoginController;
import com.mbms.login.Session;
import com.mbms.service.CustomerService;
import com.mbms.service.CustomerServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private Map<String, Session> tokens;

	private Session exists(String token) {
		return LoginController.tokens.get(token);
	}

	@PostMapping("/purchaseCoupon/{couponId}/{token}")
	public ResponseEntity<String> purchaseCoupon(@PathVariable long couponId, @PathVariable String token)
			throws Exception {
		
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				if (((CustomerServiceImpl) session.getFacade()).purchaseCoupon(couponId) != null) {
				}
				return new ResponseEntity<>("Customer purchaed coupon :  " + couponId, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.UNAUTHORIZED);
			}
		}

		return null;
	}

}
