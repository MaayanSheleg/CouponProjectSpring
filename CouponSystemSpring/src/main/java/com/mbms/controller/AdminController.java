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

	private Session exists(String token) {
		return LoginController.tokens.get(token);
	}

	// COMPANY:

	@GetMapping("/getAllCompnies/{token}")
	public ResponseEntity<List<Company>> getAllCompany(@PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("wrong session");
		}
		session.setLastAccesed(System.currentTimeMillis());
		ResponseEntity<List<Company>> result = new ResponseEntity<List<Company>>(adminService.allCompanies(),
				HttpStatus.OK);
		return result;
	}

	@GetMapping("/getCompany/{id}/{token}")
	public Company getCompany(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((AdminServiceImpl) session.getFacade()).companyById(id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@PostMapping("/createCompany/{token}")
	public ResponseEntity<String> createCompany(@RequestBody Company company, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		System.out.println(session);
		if (session == null) {
			throw new Exception("wrong session");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {

				((AdminServiceImpl) session.getFacade()).createCompany(company);
				return new ResponseEntity<>("company created", HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<>("wrong", HttpStatus.UNAUTHORIZED);
			}
		}
		return null;

	}

	@PostMapping("/updateCompany/{token}")
	public ResponseEntity<String> updateCompany(@RequestParam long id, @RequestParam String password,
			@RequestParam String email, @PathVariable String token) throws Exception {

		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Company company = null;
				company = adminService.companyById(id);
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

	@DeleteMapping("/deleteCompany/{id}/{token}")
	public void deleteCompany(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Company company = null;
				company = adminService.companyById(id);
				if (company != null) {
					try {
						((AdminServiceImpl) session.getFacade()).deleteCompany(id);
						System.out.println("Admin successfully deleted company " + id);
					} catch (Exception e) {
						e.getMessage();
					}
				}
			} catch (Exception e) {
				System.err.println("Failed to delete company, please insert another id");
			}
		}
	}

	// Customer

	@GetMapping("/getAllCustomers/{token}")
	public ResponseEntity<List<Customer>> allcustomers(@PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				if (((AdminServiceImpl) session.getFacade()).allCustomers() != null) {
					ResponseEntity<List<Customer>> result = new ResponseEntity<List<Customer>>(
							adminService.allCustomers(), HttpStatus.OK);
					return result;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@GetMapping("/getCustomer/{id}/{token}")
	public Customer getCustomer(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				return ((AdminServiceImpl) session.getFacade()).customerById(id);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	@PostMapping("/createCustomer/{token}")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer, @PathVariable String token)
			throws Exception {
		Session session = exists(token);
		System.out.println(session);
		if (session == null) {
			throw new Exception("wrong session");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				((AdminServiceImpl) session.getFacade()).createCustomer(customer);
				return new ResponseEntity<>("customer created", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>("wrong", HttpStatus.UNAUTHORIZED);
			}
		}
		return null;

	}

	@PostMapping("/updateCustomer/{token}")
	public ResponseEntity<String> updateCustomer(@PathVariable String token, @RequestParam long id,
			@RequestParam String password) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Customer customer = null;
				customer = adminService.customerById(id);
				if (customer != null) {
					((AdminServiceImpl) session.getFacade()).updateCustomer(customer, password);
					return new ResponseEntity<>("customer " + customer.getCustomerName() + " was updated",
							HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				System.out.println("Failed to update company !!");
			}
		}
		return null;
	}

	@DeleteMapping("/deleteCustomer/{id}/{token}")
	public void deleteCustomer(@PathVariable long id, @PathVariable String token) throws Exception {
		Session session = exists(token);
		if (session == null) {
			throw new Exception("Something went wrong with the session !!");
		} else if (session != null) {
			session.setLastAccesed(System.currentTimeMillis());
			try {
				Customer customer = null;
				customer = adminService.customerById(id);
				if (customer != null) {
					((AdminServiceImpl) session.getFacade()).deleteCustomer(id);
				}
			} catch (Exception e) {
				System.err.println("Failed to delete customer, please insert another id");
			}
		}
	}
}