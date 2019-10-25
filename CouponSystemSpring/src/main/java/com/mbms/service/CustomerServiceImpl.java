package com.mbms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbms.config.Utils;
import com.mbms.epository.CouponRepository;
import com.mbms.epository.CustomerRepository;
import com.mbms.exceptions.CouponSystemException;
import com.mbms.login.CouponClientFacade;
import com.mbms.login.LoginType;
import com.mbms.model.Coupon;
import com.mbms.model.CouponType;
import com.mbms.model.Customer;
import com.mbms.model.Income;
import com.mbms.model.IncomeType;

@Service
public class CustomerServiceImpl implements CustomerService, CouponClientFacade {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private IncomeService incomeService;

	private Customer customer;

	@Override
	public CouponClientFacade login(String name, String password, LoginType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer=customer;
	}

	@Override
	public Customer purchaseCoupon(long couponId) throws CouponSystemException {
		try {
			
			if (!couponRepository.existsById(couponId)) {
				throw new CouponSystemException("Coupon doesn't exist");		
			}
			
			Coupon coupon = couponRepository.findById(couponId).get();
			
			if (coupon.getAmount() <= 0) {
				throw new CouponSystemException("This coupon is out of stock !!");
			}
			
			if (coupon.getEndDate().getTime() <= coupon.getStartDate().getTime()) {
				throw new CouponSystemException("This coupon has been expired");
			}
			
			couponRepository.save(coupon);
			Customer customer = customerRepository.findById(this.customer.getId()).get();
			customer.getCoupons().add(coupon);
			customerRepository.save(customer);
			coupon.setAmount(coupon.getAmount() - 1);
			
			Income income = new Income();
			income.setClientId(this.customer.getId());
			income.setAmount(coupon.getPrice());
			income.setDate((Date) Utils.getCurrentDate());
			income.setDescription(IncomeType.CUSTOMER_PURCHASE);
			income.setName("customer " + customer.getCustomerName());
			incomeService.storeIncome(income);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}

	@Override
	public List<Coupon> getAllCustomerPurchases(long customer_id) throws Exception {
		Customer customer = customerRepository.getOne(customer_id);
		if (customer != null) {
			List<Coupon> coupons = customer.getCoupons();
			if (coupons != null) {
				return coupons;
			} else {
				throw new CouponSystemException("This customer doesn't have any coupons");
			}
		} else {
			throw new Exception("This customer doesn't exist");
		}
	}

	@Override
	public List<Coupon> couponByType(CouponType couponType) throws Exception {
		List<Coupon> allCustomercoupons = getAllCustomerPurchases(this.customer.getId());
		List<Coupon> couponsByType = couponRepository.findByType(couponType);
		try {
			for (Coupon coupon : allCustomercoupons) {
				if (coupon.getType().equals(couponsByType)) {
					couponsByType.add(coupon);
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to get all coupons by type " + e.getMessage());
		}
		return couponsByType;
	}

	@Override
	public List<Coupon> couponByPrice(double price) throws Exception {
		List<Coupon> allCustomerCoupons = getAllCustomerPurchases(this.customer.getId());
		List<Coupon> couponsByPrice = couponRepository.findByPriceLessThan(price);
		try {
			for (Coupon coupon : allCustomerCoupons) {
				if (coupon.getPrice() <= price) {
					couponsByPrice.add(coupon);
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to get all coupons by price " + e.getMessage());
		}
		return couponsByPrice;
	}
	
//	public List<Coupon> getAllCoupons() throws Exception{
//		
//	}
	
	@Override
	public List<Coupon> allCoupons(){
		return couponRepository.findAll();
	}
}
