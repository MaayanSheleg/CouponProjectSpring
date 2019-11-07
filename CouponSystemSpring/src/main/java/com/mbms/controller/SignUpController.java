package com.mbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.model.Company;
import com.mbms.model.Customer;
import com.mbms.service.AdminService;

@RestController
@RequestMapping("/signUp")
public class SignUpController {
	
	@Autowired
	private AdminService adminService;
	
	// COMPANY:

	
	@PostMapping("/createCompany")
	public ResponseEntity<Company> createCompany (@RequestBody Company company) throws Exception{
		Company company2 = adminService.createCompany(company);
		ResponseEntity<Company> result = new ResponseEntity<Company>(company2,HttpStatus.OK);
		return result;
	}

	
	// Customer
	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer (@RequestBody Customer customer) throws Exception{
		Customer customer2 = adminService.createCustomer(customer);
		ResponseEntity<Customer> result = new ResponseEntity<Customer>(customer2,HttpStatus.OK);
		return result;
	}


}

}
