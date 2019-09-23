package com.mbms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.login.LoginController;
import com.mbms.login.Session;
import com.mbms.model.Company;
import com.mbms.model.Customer;
import com.mbms.service.AdminService;
import com.mbms.service.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private Map<String, Session> tokens;
	
	private Session exists (String token) {
		return LoginController.tokens.get(token);
	}
	
	//COMPANY:
	@GetMapping("/getAllCompnies/{token}")
	public ResponseEntity<List<Company>> getAllCompany(@PathVariable String token)throws Exception{
		Session session = exists(token);
		if (session==null) {
			throw new Exception("wrong session");
		}
		session.setLastAccesed(System.currentTimeMillis());
		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(adminService.allCompanies(), HttpStatus.OK);
	return result;
	}
	
	@GetMapping("/getCompany/{id}/{token}")
	public Company companyById(@PathVariable int id, @PathVariable String token)throws Exception{
		Session session = exists(token);
		if (session==null) {
			throw new Exception("wrong session");
		}
		session.setLastAccesed(System.currentTimeMillis()); 
		return adminService.companyById(id);
	}
	
	@PostMapping("/createCompany/{token}")
	public ResponseEntity<String> createCompany (@RequestBody Company company, @PathVariable String token) throws Exception{
		Session session = exists(token);
		System.out.println(session);
		if (session==null) {
			throw new Exception("wrong session");
		} else if(session!=null) {
			session.setLastAccesed(System.currentTimeMillis());
		try {
			
			((AdminServiceImpl)session.getFacade()).createCompany(company);
			return new ResponseEntity<>("company created", HttpStatus.OK);
			
	}catch (Exception e){
		return new ResponseEntity<>("wrong", HttpStatus.UNAUTHORIZED);
	}
	}
		return null;
		
	}
//not work!!
	@PostMapping("/updateCompany/{token}")
	public ResponseEntity<String> updateCompany(@PathVariable String token, @RequestParam int id,
			@RequestParam String password, @RequestParam String email) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Company company = null;
				company = adminService.companyById(id);
				System.out.println(company);
				if (company != null) {
					((AdminServiceImpl) session.getFacade()).updateCompany(company, password, email);
					return new ResponseEntity<>("company " + company.getCompanyName() + " was updated", HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				System.out.println("Failed to update company !!");
			}
		}

		return null;

	}
	
	
	//no work
	@DeleteMapping ("/deleteCompany/{id}/(token}")
	public void deleteCompany (@PathVariable int id, @PathVariable String token) throws Exception{
		Session session = exists(token);
		if (session==null) {
			throw new Exception("wrong session");
		}
		session.setLastAccesed(System.currentTimeMillis());
		adminService.deleteCompany(id);	
	}
	

	
	//Customer
	
	@GetMapping("/getAllCustomers/{token}")
	public ResponseEntity<List<Customer>> getAllCustomer(@PathVariable String token)throws Exception{
		Session session = exists(token);
		if (session==null) {
			throw new Exception("wrong session");
		}
		session.setLastAccesed(System.currentTimeMillis());
		ResponseEntity<List<Customer>> result = new ResponseEntity<List<Customer>>(adminService.allCustomers(), HttpStatus.OK);
		return result;
	}
	

	
	@GetMapping("/getCustomer/{id}/{token}")
	public Customer customerById(@PathVariable int id, @PathVariable String token)throws Exception{
		Session session = exists(token);
		if (session==null) {
			throw new Exception("wrong session");
		}
		session.setLastAccesed(System.currentTimeMillis()); 
		return adminService.customerById(id);
	}
	
	
	
	
	@PostMapping("/createCustomer/{token}")
	public ResponseEntity<String> createCustomer (@RequestBody Customer customer, @PathVariable String token) throws Exception{
		Session session = exists(token);
		System.out.println(session);
		if (session==null) {
			throw new Exception("wrong session");
		} else if(session!=null) {
			session.setLastAccesed(System.currentTimeMillis());
		try {
			((AdminServiceImpl)session.getFacade()).createCustomer(customer);
			return new ResponseEntity<>("customer created", HttpStatus.OK);
	}catch (Exception e){
		return new ResponseEntity<>("wrong", HttpStatus.UNAUTHORIZED);
	}
	}
		return null;
		
	}
	
	
//not work
	@PostMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestParam int id, @RequestParam String password){
		Customer customer = null;
		customer = adminService.customerById(id);
		if (customer != null) {
		//	adminService.updateCustomer(customer, password);
			ResponseEntity<Customer> result = new ResponseEntity<>(customer,HttpStatus.OK);
			return result;	
		}
		return null;
	}

	@DeleteMapping("/deleteCustomer/{id}/{token}")	
	public void deleteCustomer(@PathVariable int id,  @PathVariable String token)throws Exception{
		Session session = exists(token);
		if (session==null) {
			throw new Exception("wrong session");
		}
		session.setLastAccesed(System.currentTimeMillis()); 
		 adminService.deleteCustomer(id);
	}
}