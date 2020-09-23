package com.cs.testing;

import com.cs.artUtils.Titles;
import com.cs.bean.Category;
import com.cs.bean.Coupon;
import com.cs.dao.CouponDAO;
import com.cs.dbdao.CouponDBDAO;
import com.cs.exceptions.AlreadyExistException;
import com.cs.exceptions.InvalidActionException;
import com.cs.exceptions.NotExistException;
import com.cs.facade.CustomerFacade;
import com.cs.login.ClientType;
import com.cs.login.LoginMaster;

public class CustomerTesting implements Titles {

	public static void customerFacadeTesting() throws InvalidActionException, AlreadyExistException, NotExistException {

		CouponDAO couponDAO = new CouponDBDAO();
		Coupon co1 = couponDAO.getOne(1);
		Coupon co2 = couponDAO.getOne(2);
		Coupon co3 = couponDAO.getOne(3);

		Titles.customerFacade();
		LoginMaster loginMaster = new LoginMaster();
		CustomerFacade customerFacade = (CustomerFacade) loginMaster.login("tom@changed.com", "1234",
				ClientType.Customer);
		customerFacade.purchaseCoupon(co1);
		customerFacade.purchaseCoupon(co2);
		customerFacade.purchaseCoupon(co3);
		try {

			customerFacade.purchaseCoupon(co1);

		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}
		System.out.println(customerFacade.getCustomerCoupons());
		System.out.println(customerFacade.getCustomerDetails());
		Titles.getAllCoupons("Customer");
		System.out.println("An already exisiting coupon was not added, and expired Coupon was not added");
		System.out.println(customerFacade.getCustomerCoupons());
		Titles.getAllByCategory("Customer");
		System.out.println(customerFacade.getCustomerCouponsByCategory(Category.valueOf("Food")));
		Titles.getAllByPrice("Customer");
		System.out.println(customerFacade.getCustomerCouponsByPrice(10.99));

	}
}
