package com.mbms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbms.epository.CompanyRepository;
import com.mbms.epository.CustomerRepository;
import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Company;
import com.mbms.model.Customer;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private String adminName = "admin";
	private String adminPassword = "1234";

	@Autowired
	public AdminServiceImpl() {
	}

	public boolean performLogin(String name, String password) throws CouponSystemException {
		if (adminName.equals(name) && adminPassword.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	// ******* Company *******

	public boolean checkIfCompanyNameAlreadyExists(String companyName) {
		if (companyRepository.findByCompanyName(companyName) != null) {
			return true;
		}
		return false;
	}

	public Company createCompany(Company company) throws CouponSystemException {
		if (checkIfCompanyNameAlreadyExists(company.getCompanyName()) == false) {
			companyRepository.save(company);
		} else {
			throw new CouponSystemException(
					"The company " + company.getCompanyName() + " already exist, please try another name");
		}
		return company;
	}

	@Override
	public void deleteCompany(int id) {
		companyRepository.deleteById(id);
	}

	@Override
	public List<Company> allCompanies() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();
	}

	@Override
	public Company companyById(int id) {
		return companyRepository.findById(id).get();
	}

	@Override
	public void updateCompany(Company company, String password, String email) {
		company.setPassword(password);
		company.setEmail(email);
		companyRepository.save(company);

	}

	//// ******* Customer *******

	public boolean checkIfCustomerNameAlreadyExists(String custName) {
		if (customerRepository.findByCustomerName(custName) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Customer createCustomer(Customer customer) throws CouponSystemException {
		if (checkIfCustomerNameAlreadyExists(customer.getCustomerName()) == false) {

			customerRepository.save(customer);

		} else {
			throw new CouponSystemException(
					"The company " + customer.getCustomerName() + " already exist, please try another name");
		}
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<Customer> allCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer customerById(int id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public void updateCustomer(Customer customer, String password) {
		customer.setPassword(password);
		customerRepository.save(customer);
	}
}
