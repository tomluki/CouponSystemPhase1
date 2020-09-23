package com.cs.facade;

import java.util.ArrayList;
import java.util.List;

import com.cs.bean.Category;
import com.cs.bean.Company;
import com.cs.bean.Coupon;
import com.cs.dao.CompanyDAO;

import com.cs.dbdao.CompanyDBDAO;

import com.cs.exceptions.AlreadyExistException;
import com.cs.exceptions.InvalidActionException;
import com.cs.exceptions.NotExistException;

public class CompanyFacade extends ClientFacade {
	private int companyID;

	public CompanyFacade() {

	}

	public CompanyFacade(int companyID) {
		this.companyID = companyID;
	}

	CompanyDAO companyDao = new CompanyDBDAO();
	Company c1 = null;

	@Override
	public boolean login(String email, String password) throws NotExistException {
		List<Company> companies = companiesDAO.getAllCompanies();
		for (Company company : companies) {
			if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
				c1 = company;
				companyID = c1.getId();
				System.out.println("Welcome back " + c1.getName());
				return true;
			}
		}

		throw new NotExistException("The password/email appears to be incorrect");

	}

	public void addCoupon(Coupon coupon) throws AlreadyExistException {
		List<Coupon> companyCoupons = couponDAO.getAllCompanyCouponByCompanyId(companyID);
		if (coupon.getCompanyId() == companyID) {
			for (Coupon coup : companyCoupons) {
				if (coup.getTitle().equals(coupon.getTitle())) {
					throw new AlreadyExistException("Coupon title");

				}

			}
			couponDAO.addCoupon(coupon);
		}

	}

	public void updateCoupon(Coupon coupon) throws InvalidActionException {
		Coupon coupon2 = couponDAO.getOne(coupon.getId());
		if (coupon.getId() == coupon2.getId() && coupon.getCompanyId() == coupon2.getCompanyId()) {
			couponDAO.updateCoupon(coupon);
		} else {
			throw new InvalidActionException("coupon id or coupon's company id should not change");
		}

	}

	public void deleteCoupon(int couponID) throws InvalidActionException {
		Coupon coup = new Coupon();
		coup = couponDAO.getOne(couponID);
		if (coup.getCompanyId() == companyID) {
			couponDAO.deleteCouponPurchaseByCouponID(couponID);
			couponDAO.deleteCoupon(couponID);
		} else {
			throw new InvalidActionException("deleting another companys coupon is not allowed");
		}

	}

	public List<Coupon> getCompanyCoupon() {
		List<Coupon> companyCoupons = couponDAO.getAllCompanyCouponByCompanyId(companyID);
		return companyCoupons;

	}

	public List<Coupon> getCompanyCouponByCategory(Category category) {
		List<Coupon> couponsBycompany = couponDAO.getAllCompanyCouponByCompanyId(companyID);
		List<Coupon> couponsByCategory = new ArrayList<Coupon>();
		for (Coupon coupon : couponsBycompany) {
			if (coupon.getCategory().equals(category)) {
				couponsByCategory.add(coupon);
			}
		}
		return couponsByCategory;

	}

	public List<Coupon> getCompanyCouponByPrice(double maxPrice) {
		List<Coupon> couponsBycompany = couponDAO.getAllCompanyCouponByCompanyId(companyID);
		List<Coupon> couponsByPrice = new ArrayList<Coupon>();
		for (Coupon coupon : couponsBycompany) {
			if (coupon.getPrice() < maxPrice) {
				couponsByPrice.add(coupon);
			}
		}
		return couponsByPrice;
	}

	public Company getCompanyDetails() {

		return companiesDAO.getOneCompany(companyID);

	}
}