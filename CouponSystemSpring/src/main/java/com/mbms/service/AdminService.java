package com.mbms.service;

import java.util.List;

import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Company;
import com.mbms.model.Customer;

public interface AdminService {

boolean performLogin(String name, String password) throws CouponSystemException;
	
	//company
	Company createCompany(Company company) throws CouponSystemException;
	void deleteCompany(int id);
	List<Company> allCompanies();
	
	boolean checkIfCompanyNameAlreadyExists(String companyName);
	Company companyById(int id);
	void updateCompany(Company company, String password, String email);

	//customer
	Customer createCustomer(Customer customer) throws Exception;
	void deleteCustomer(int id);


	List<Customer> allCustomers();

	Customer customerById(int id);

	boolean checkIfCustomerNameAlreadyExists(String customerName);
	void updateCustomer(Customer customer, String password);

}
