package com.cs.thread;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.cs.bean.Coupon;
import com.cs.dao.CouponDAO;
import com.cs.dbdao.CouponDBDAO;

public class DailyinvalidCouponJob implements Runnable {
	CouponDAO couponDAO = new CouponDBDAO();
	private boolean quit = false;

	@Override
	public void run() {
		while (quit = false) {
			List<Coupon> coupons = couponDAO.getAllCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
					couponDAO.deleteCouponPurchaseByCouponID(coupon.getId());
					couponDAO.deleteCoupon(coupon.getId());
				}
			}
			try {
				Thread.sleep(1000 * 30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}

	}

	public void stop() {
		quit = true;
		Thread.currentThread().interrupt();
	}
}
