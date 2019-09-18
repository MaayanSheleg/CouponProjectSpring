package com.mbms.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mbms.epository.CouponRepository;
import com.mbms.epository.LogRepository;
import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Company;
import com.mbms.model.Coupon;
import com.mbms.model.CustomLogin;
import com.mbms.model.Customer;
import com.mbms.model.Log;
import com.mbms.model.LoginType;

/**
 * The class will perform general actions related to the system. Log in, and delete expired coupons.
 */

@Service
public class SystemService {
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private LogRepository logRepository;

	public CustomLogin login(String name, String password, LoginType loginType) throws CouponSystemException {
		switch (loginType) {
			case ADMIN:
				if (adminService.performLogin(name, password)) {
					CustomLogin customLogin = new CustomLogin(LoginType.ADMIN, 1);
					return customLogin;
				} else {
					throw new CouponSystemException("incorect password");
				}
				
				
			case COMPANY:

				if (companyService.performLogin(name, password)) {
					Company company = companyService.getComapnyByName(name);
					return new CustomLogin(LoginType.COMPANY, company.getId());
				} else {

					throw new CouponSystemException("incorect password");
				}
				
			case CUSTOMER:

				if (customerService.performLogin(name, password)) {
					Customer customer = customerService.getCustomerName(name);
//					return new CustomLogin(loginType.CUSTOMER, customer.getId());
				} else {
					throw new CouponSystemException("incorect password");
				}
			default:
				throw new CouponSystemException("incorect password");
		}
	}

	/**
	 * The function will be activated when the system is activated or every 24 hours
	 * in which the system operates. The function will get from the DB all expiring
	 * coupons and remove them from the DB.
	 */

	@Scheduled(fixedRateString = "${coupon.project.remove.daily.coupon.every.day}")
	@Transactional
	public void removeInvalidCoupon() {
		List<Coupon> allCoupons = couponRepository.findByEndDateBefore(new Date());
		for (Coupon coupon : allCoupons) {
			Log log = new Log(new Date(), "FROM  SYSTEM SERVER", "eclipse",
				"remove daily coupon remove the coupon " + coupon.getTitle(), true);
			logRepository.save(log);
			couponRepository.delete(coupon);
		}
	}
}
