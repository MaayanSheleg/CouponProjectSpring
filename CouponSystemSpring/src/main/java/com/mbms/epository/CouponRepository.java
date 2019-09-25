package com.mbms.epository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mbms.model.Coupon;
import com.mbms.model.CouponCaregory;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
	Coupon findByTitle(String title);

		List<Coupon> findByCompanyId(int companyId);

		List<Coupon> findByType(CouponCaregory type);

		List<Coupon> findByPriceLessThan(double price);

		@Query("select c from Coupon c where c.id = ?1  AND  c.company.id = ?2")
		Coupon getCouponCompany(int couponId, int companyId);

		@Query("SELECT DISTINCT c FROM Coupon c INNER JOIN c.customers t where t.id = ?1")
		List<Coupon> couponsCustomerByCustomerId(int customerId);

		@Query("SELECT DISTINCT c FROM Coupon c INNER JOIN c.customers t where t.id = ?1 and c.id = ?2")
		Coupon couponByCustomerIdAndCouponId(int customerId, int couponId);

		List<Coupon> findByEndDateBefore(java.util.Date date);
		
		List<Coupon> findAllById(int id);
}