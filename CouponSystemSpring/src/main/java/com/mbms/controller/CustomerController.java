package com.mbms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.epository.CouponRepository;
import com.mbms.login.LoginController;
import com.mbms.login.Session;
import com.mbms.service.CustomerService;

@RestController
@RequestMapping("/company")
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
	
	
}
