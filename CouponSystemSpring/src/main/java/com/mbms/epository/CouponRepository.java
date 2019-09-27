package com.mbms.epository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mbms.model.Coupon;
import com.mbms.model.CouponType;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
		Coupon findByTitle(String title);

//		List<Coupon> findByCompanyId(long companyId);

		List<Coupon> findByType(CouponType couponType);

		List<Coupon> findByPriceLessThan(double price);

//		@Query("select c from Coupon c where c.id = ?1  AND  c.company.id = ?2")
////		Coupon getCouponCompany(long couponId, long companyId);
//
//		@Query("SELECT DISTINCT c FROM Coupon c INNER JOIN c.customers t where t.id = ?1")
//		List<Coupon> couponsCustomerByCustomerId(long customerId);
//
//		@Query("SELECT DISTINCT c FROM Coupon c INNER JOIN c.customers t where t.id = ?1 and c.id = ?2")
//		Coupon couponByCustomerIdAndCouponId(long customerId, long couponId);
//
		List<Coupon> findByEndDateBefore(Date date);
		
		List<Coupon> findAllById(long id);
}