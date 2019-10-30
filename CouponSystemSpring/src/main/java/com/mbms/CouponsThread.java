package com.mbms;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mbms.epository.CompanyRepository;
import com.mbms.epository.CouponRepository;
import com.mbms.epository.CustomerRepository;

@Component
public class CouponsThread {
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	CouponRepository couponRepository;

	private boolean exit = false;
	private boolean running = true;

	public void removeExpiredCoupons(Date date) {
		couponRepository.deleteAll(couponRepository.findByEndDateBefore(date));
	}


	public void startThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (running) {
					removeExpiredCoupons(new Date(System.currentTimeMillis()));
					try {
						Thread.sleep(1000 * 60 * 60 * 24);
					} catch (InterruptedException e) {
						System.out.println("Eroor " + e.getMessage());
					}
				}
			}
		}).start();
	}
	
	public void stopThread() {
		this.running = false;
	}

}
