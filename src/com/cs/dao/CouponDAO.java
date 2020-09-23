package com.cs.dao;

import java.util.List;

import com.cs.bean.Coupon;
import com.cs.bean.CustomerVsCoupon;

public interface CouponDAO {

	void addCoupon(Coupon coupon);

	void updateCoupon(Coupon coupon);

	Coupon getOne(int id);

	List<Coupon> getAllCoupons();

	void addCouponPurchase(int customerID, int couponID);

	void deleteCouponPurchase(int customerID, int couponID);

	void deleteCouponPurchaseByCouponID(int couponID);

	void deleteCouponPurchaseByCustomerID(int customerID);

	List<CustomerVsCoupon> getAllCouponsIDByCustomerID(int customerID);

	void deleteCoupon(int couponID);

	List<Coupon> getAllCompanyCouponByCompanyId(int id);

}