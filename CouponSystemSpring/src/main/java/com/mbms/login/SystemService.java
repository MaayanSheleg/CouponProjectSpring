package com.mbms.login;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mbms.epository.CompanyRepository;
import com.mbms.epository.CouponRepository;
import com.mbms.epository.CustomerRepository;
import com.mbms.epository.LogRepository;
import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Company;
import com.mbms.model.Coupon;
import com.mbms.model.Customer;
import com.mbms.model.Log;
import com.mbms.service.AdminServiceImpl;
import com.mbms.service.CompanyServiceImpl;
import com.mbms.service.CustomerServiceImpl;

/**
 * The class will perform general actions related to the system. Log in, and delete expired coupons.
 */

@Service
public class SystemService {
	
	@Autowired
	private AdminServiceImpl adminService;

	@Autowired
	private CompanyServiceImpl companyService;

	@Autowired
	private CustomerServiceImpl customerService;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired 
	private CompanyRepository companyRepository;
	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private ApplicationContext context;

	public CouponClientFacade login(String name, String password, LoginType loginType) throws CouponSystemException {
		switch (loginType) {
		
		case ADMIN:
				if (name.equals("admin") && password.equals("1234")) {
					adminService = context.getBean(AdminServiceImpl.class);
					return adminService;
				} else {
					throw new CouponSystemException("incorect password or userName");
				}
				
				
			case COMPANY:
				Company company = companyRepository.findByCompanyNameAndPassword(name, password);
				if (company!=null) {
					CompanyServiceImpl comp= context.getBean(CompanyServiceImpl.class);
					comp.setCompany(company);
					return companyService;
				} else {

					throw new CouponSystemException("incorect password");
				}
				
			case CUSTOMER:
				Customer customer = customerRepository.findByCustomerNameAndPassword(name, password);
				if (customer!=null) {
					customerService = context.getBean(CustomerServiceImpl.class);
					customerService.setCustomer(customer);
					return customerService;

				} else {
					throw new CouponSystemException("incorect password");
				}
		}
		return null;
	}
//
//	/**
//	 * The function will be activated when the system is activated or every 24 hours
//	 * in which the system operates. The function will get from the DB all expiring
//	 * coupons and remove them from the DB.
//	 */
//
//	@Scheduled(fixedRateString = "${coupon.project.remove.daily.coupon.every.day}")
//	@Transactional
//	public void removeInvalidCoupon() {
//		List<Coupon> allCoupons = couponRepository.findByEndDateBefore(new Date());
//		for (Coupon coupon : allCoupons) {
//			Log log = new Log(new Date(), "FROM  SYSTEM SERVER", "eclipse",
//				"remove daily coupon remove the coupon " + coupon.getTitle(), true);
//			logRepository.save(log);
//			couponRepository.delete(coupon);
//		}
//	}
	
	
}
