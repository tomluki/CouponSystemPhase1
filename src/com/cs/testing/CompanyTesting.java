package com.cs.testing;

import java.sql.Date;

import com.cs.artUtils.Titles;
import com.cs.bean.Category;
import com.cs.bean.Coupon;
import com.cs.dao.CouponDAO;
import com.cs.dbdao.CouponDBDAO;
import com.cs.exceptions.AlreadyExistException;
import com.cs.exceptions.InvalidActionException;
import com.cs.facade.CompanyFacade;
import com.cs.login.ClientType;
import com.cs.login.LoginMaster;

public class CompanyTesting implements Titles {
	public static void companyFacadeTesting() throws AlreadyExistException, InvalidActionException {
		Titles.companyFacade();
		System.out.println("Part 1:creating and editing coupons");
		LoginMaster loginMaster = new LoginMaster();
		CompanyFacade companyFacade = (CompanyFacade) loginMaster.login("cola@changed.com", "cocacolalife",
				ClientType.Company);
		System.out.println("calling on couponDBDAO to add coupons not related to coca cola");
		CouponDAO couponDAO = new CouponDBDAO();
		Coupon co1 = new Coupon(1, Category.valueOf("Food"), "5+1", "Buy 5 cans and 1 for FREE!!!",
				Date.valueOf("2007-02-29"), Date.valueOf("2021-02-29"), 5, 15.99, "1234");
		Coupon co2 = new Coupon(1, Category.valueOf("Gaming"), "Gaming Kit",
				"Get a brand new gaming kit at a 50% discount!", Date.valueOf("2007-02-29"), Date.valueOf("2020-10-23"),
				2, 9.99, "1234");
		Coupon co3 = new Coupon(3, Category.valueOf("Electronics"), "TV", "Get a brand new TV at a 40% discount",
				Date.valueOf("2007-02-29"), Date.valueOf("2020-10-25"), 5, 15.99, "1234");
		Coupon co4 = new Coupon(2, Category.valueOf("Gaming"), "expired", "this is an expired coupon",
				Date.valueOf("2007-02-29"), Date.valueOf("2021-02-29"), 0, 9.99, "1234");
		Coupon co5 = new Coupon(1, Category.valueOf("Electronics"), "5+1", "you get stuff for free",
				Date.valueOf("2007-02-29"), Date.valueOf("2020-10-25"), 5, 9.99, "1234");
		System.out.println("Adding coupons");
		companyFacade.addCoupon(co1);
		couponDAO.addCoupon(co2);
		couponDAO.addCoupon(co3);
		companyFacade.addCoupon(co4);
		try {
			companyFacade.addCoupon(co5);
		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}

		Titles.getAll("Company", "Coupons");
		System.out.println("A Coupon with the same name was not added");
		System.out.println(companyFacade.getCompanyCoupon());
		Titles.update("Coupon");
		System.out.println("Id was not changed as changing any id is not allowed on the DBDAO level");
		co1.setAmount(16);
		companyFacade.updateCoupon(co1);
		Titles.getAll("Company", "Coupons");
		System.out.println(companyFacade.getCompanyCoupon());
		Titles.getAllByCategory("Company");
		System.out.println(companyFacade.getCompanyCouponByCategory(Category.valueOf("Food")));
		Titles.getAllByPrice("Company");
		System.out.println(companyFacade.getCompanyCouponByPrice(10.99));
		Titles.getAllCoupons("All Coupons");
		System.out.println(couponDAO.getAllCoupons());
		System.out.println("Company Details");
		System.out.println(companyFacade.getCompanyDetails());
	}
}
