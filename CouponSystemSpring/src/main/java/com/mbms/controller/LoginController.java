package com.mbms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.CustomLogin;
import com.mbms.model.LoginType;
import com.mbms.service.SystemService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private SystemService loginService;
	private String loggedDetails;
	
	@PostMapping
	public boolean login(@RequestParam String name, @RequestParam String password, @RequestParam String loginType,
	HttpServletResponse response, HttpServletRequest request) {
	
		if (!loginType.equals("ADMIN") && !loginType.equals("COMPANY") && !loginType.equals("CUSTOMER")) {
			return false;
		}
		try {
			HttpSession session = request.getSession();
			CustomLogin customLogin = loginService.login(name, password, LoginType.valueOf( loginType));
			session.setAttribute(loggedDetails, customLogin);
		} catch (CouponSystemException e) {
			return false;
		}
		return true;
	}

	@RequestMapping(path = "logout")
	public boolean logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			session.invalidate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
