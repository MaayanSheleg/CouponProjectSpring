package com.mbms.epository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mbms.model.Coupon;
import com.mbms.model.CouponCaregory;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>{
	
	Coupon findByTitle(String title);

	List<Coupon> findByCompanyId(int companyId);

	List<Coupon> findByType(CouponCaregory type);

	List<Coupon> findByPriceLessThan(double price);

	List<Coupon> findByEndDateBefore(Date date);

	@Query("select c from Coupon c where c.id = ?1  AND  c.company.id = ?2")

	Coupon getCouponCompany(int couponId, int companyId);

	@Query("SELECT DISTINCT c FROM Coupon c INNER JOIN c.customers t where t.id = ?1")

	List<Coupon> couponsCustomerByCustomerId(int customerId);

	@Query("SELECT DISTINCT c FROM Coupon c INNER JOIN c.customers t where t.id = ?1 and c.id = ?2")

	Coupon couponByCustomerIdAndCouponId(int customerId, int couponId);

	List<Coupon> findByEndDateBefore(java.util.Date date);

}
