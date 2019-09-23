package com.mbms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbms.config.Utils;
import com.mbms.epository.CompanyRepository;
import com.mbms.epository.CouponRepository;
import com.mbms.epository.CustomerRepository;
import com.mbms.epository.IncomeRepository;
import com.mbms.exceptions.CouponSystemException;
import com.mbms.login.CouponClientFacade;
import com.mbms.login.LoginType;
import com.mbms.model.Company;
import com.mbms.model.Coupon;
import com.mbms.model.CouponCaregory;
import com.mbms.model.Customer;
import com.mbms.model.Income;
import com.mbms.model.IncomeType;

@Service
public class CompanyServiceImpl implements CompanyService, CouponClientFacade {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Autowired
	private IncomeRepository incomeRepository;
	private Customer customer;
	private Company company;

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public boolean performLogin(String name, String password) {
		Company company = companyRepository.findByCompanyNameAndPassword(name, password);
		if (company == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Coupon createCoupon(Coupon coupon) throws Exception {
		if (checkIfTitleAlreadyExists(coupon.getTitle()) == false) {
			couponRepository.save(coupon);
			Company comp = companyRepository.findById(this.company.getId()).get();
			comp.getCoupons().add(coupon);
			System.err.println(coupon);
			companyRepository.save(comp);
			Income income = new Income();
			income.setId(this.company.getId());
			income.setAmount(100.0);
			income.setDescription(IncomeType.COMPANY_NEW_COUPON);
			income.setDate((Date) Utils.getCurrentDate());
			income.setName("Company " + company.getCompanyName());
			incomeRepository.save(income);
		} else {
			throw new Exception("The title " + coupon.getTitle() + " already exist, please try another title");
		}
		return coupon;
	}

	
	@Override
	public List<Coupon> getAllCompanyCoupons(int company_id) throws Exception {
		Company company = companyRepository.getOne(company_id);
		if (company != null) {
			List<Coupon> coupons = company.getCoupons();
			if (coupons != null) {
				return coupons;
			} else {
				throw new CouponSystemException("This company doesn't have any coupons");
			}
		} else {
			throw new Exception("This company doesn't exist");
		}
	}

	@Override
	public void updateCoupon(Coupon coupon, Date endDate, double price) {
		coupon.setEndDate(endDate);
		coupon.setPrice(price);
		couponRepository.save(coupon);
		Income income = new Income();
		income.setId(this.company.getId());
		income.setAmount(10.0);
		income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);
		income.setDate((Date) Utils.getCurrentDate());
		income.setName("Company " + company.getCompanyName());
		incomeRepository.save(income);
	}

	@Override
	public void deleteCoupon(int couponId) throws Exception {
//		if (!couponRepository.existsById(couponId)) {
//			throw new Exception("This coupon id doesn't exist in DataBase");
//		}
//		List<Coupon> companyCoupons = couponRepository.findAllById(this.company.getId());
//		this.company.setCoupons(companyCoupons);
//		companyRepository.save(this.company);
//		customerServiceImpl.deleteCoupon(couponId);
//		couponRepository.deleteById(couponId);
	}

	@Override
	public Company getCompany(int id) {
		return companyRepository.findById(id).get();
	}
	


	@Override
	public List<Coupon> couponByCouponType(CouponCaregory couponType) throws Exception {
		List<Coupon> allCompanycoupons = getAllCompanyCoupons(this.company.getId());
		List<Coupon> couponsByType = couponRepository.findByType(couponType);
		try {
			for (Coupon coupon : allCompanycoupons) {
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
		List<Coupon> allCompanyCoupons = getAllCompanyCoupons(this.company.getId());
		List<Coupon> couponsByPrice = couponRepository.findByPriceLessThan(price);
		try {
			for (Coupon coupon : allCompanyCoupons) {
				if (coupon.getPrice() <= price) {
					couponsByPrice.add(coupon);
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to get all coupons by price " + e.getMessage());
		}
		return couponsByPrice;
	}

	@Override
	public List<Coupon> couponByDate(Date endDate) throws Exception {
		List<Coupon> allCompanyCoupons = getAllCompanyCoupons(this.company.getId());
		List<Coupon> couponsByDate = couponRepository.findByEndDateBefore(endDate);
		try {
			for (Coupon coupon : allCompanyCoupons) {
				if (coupon.getEndDate().equals(endDate) || coupon.getEndDate().before(endDate)) {
					couponsByDate.add(coupon);
				}
			}
		} catch (Exception e) {
			System.out.println("Failed to get all coupons by date " + e.getMessage());
		}
		return couponsByDate;
	}

	

	@Override
	public boolean checkIfTitleAlreadyExists(String title) {
		if (couponRepository.findByTitle(title) != null) {
			return true;
		}
		return false;
	}

	@Override
	public CouponClientFacade login(String name, String password, LoginType clientType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCoupon(Coupon coupon, java.sql.Date endDate, double price) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Coupon> couponByDate(java.sql.Date endDate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
