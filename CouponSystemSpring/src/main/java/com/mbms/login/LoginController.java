package com.mbms.login;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.exceptions.CouponSystemException;

@RestController
@RequestMapping("loginService")
public class LoginController {
	
	public static Map<String, Session> tokens = new HashMap<String,Session>();
	@Autowired
	private SystemService couponSystem;

//	@PostMapping("login")
//	public ResponseEntity<String>login(@RequestParam String name, @RequestParam String password, @RequestParam String clientType) throws CouponSystemException{
//		if (!clientType.equals("ADMIN") && !clientType.equals("COMPANY") && !clientType.equals("CUSTOMER")) {
//			return new ResponseEntity<>("Check clientType again", HttpStatus.UNAUTHORIZED);
//		}
//		Session session = new Session();
//		CouponClientFacade facade = null;
//		String token = UUID.randomUUID().toString();
//		long lastAccessed=System.currentTimeMillis();
//		try {
//			facade = couponSystem.login(name, password, LoginType.valueOf(clientType));
//			session.setFacade(facade);
//			session.setLastAccesed(lastAccessed);
//			tokens.put(token, session);
//			return new ResponseEntity<>(token,HttpStatus.OK);
//
//		} catch (CouponSystemException e) {
//
//			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//		}
//	}

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
