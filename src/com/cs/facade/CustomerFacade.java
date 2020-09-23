package com.cs.facade;

import java.sql.Date;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cs.bean.Category;
import com.cs.bean.Coupon;
import com.cs.bean.Customer;
import com.cs.bean.CustomerVsCoupon;
import com.cs.dao.CustomerDAO;
import com.cs.dbdao.CustomerDBDAO;
import com.cs.exceptions.AlreadyExistException;
import com.cs.exceptions.InvalidActionException;
import com.cs.exceptions.NotExistException;

public class CustomerFacade extends ClientFacade {
	private int customerID;

	public CustomerFacade() {
		super();
	}

	public CustomerFacade(int customerID) {
		this.customerID = customerID;
	}

	CustomerDAO customerDAO = new CustomerDBDAO();
	Customer c1 = null;

	private List<Coupon> customerCoupons = new ArrayList<>();
	private List<CustomerVsCoupon> customerCouponsID = new ArrayList<>();

	@Override
	public boolean login(String email, String password) throws NotExistException {
		List<Customer> customers = customerDAO.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getEmail().equals(email) && customer.getPassWord().equals(password)) {
				c1 = customer;
				customerID = c1.getId();
				System.out.println("Welcome back " + c1.getFirstName());
				return true;
			}
		}

		throw new NotExistException("The password/email appears to be incorrect");

	}

	public void purchaseCoupon(Coupon coupon) throws InvalidActionException, AlreadyExistException {
		List<CustomerVsCoupon> customerCoupons = couponDAO.getAllCouponsIDByCustomerID(customerID);
		if (customerCoupons.size() > 0) {
			for (CustomerVsCoupon coupon2 : customerCoupons) {
				if (coupon2.getCouponID() == coupon.getId()) {
					throw new AlreadyExistException("Buying duplicates is not allowed");
				}
			}
		}
		if (coupon.getAmount() == 0 || coupon.getEndDate().before(Date.valueOf(LocalDate.now().toString()))) {
			throw new InvalidActionException("Buying  coupons that have ran out or expired is not allowed");
		} else {
			System.out.println("i made it");
			int CouponStock = coupon.getAmount();
			coupon.setAmount(CouponStock - 1);
			couponDAO.updateCoupon(coupon);
			couponDAO.addCouponPurchase(customerID, coupon.getId());
		}
	}

	public List<Coupon> getCustomerCoupons() {
		List<Coupon> customerCoupons = new ArrayList<Coupon>();
		List<CustomerVsCoupon> customerCouponsID = couponDAO.getAllCouponsIDByCustomerID(customerID);
		for (CustomerVsCoupon customer_VS_Coupon : customerCouponsID) {
			customerCoupons.add(couponDAO.getOne(customer_VS_Coupon.getCouponID()));
		}
		return customerCoupons;
	}

	public List<Coupon> getCustomerCouponsByCategory(Category category) {

		List<Coupon> customerCoupons = c1.getCoupons();
		List<Coupon> couponsByCategory = new ArrayList<Coupon>();
		for (Coupon coupon : customerCoupons) {
			if (coupon.getCategory().equals(category)) {
				couponsByCategory.add(coupon);
			}
		}
		return couponsByCategory;
	}

	public List<Coupon> getCustomerCouponsByPrice(double maxPrice) {
		List<Coupon> customerCoupons = c1.getCoupons();
		List<Coupon> couponsByPrice = new ArrayList<Coupon>();
		for (Coupon coupon : customerCoupons) {
			if (coupon.getPrice() < maxPrice) {
				couponsByPrice.add(coupon);
			}
		}
		return couponsByPrice;

	}

	public Customer getCustomerDetails() {
		c1 = customerDAO.getOneCustomer(customerID);
		customerCouponsID = couponDAO.getAllCouponsIDByCustomerID(customerID);
		for (CustomerVsCoupon coupon : customerCouponsID) {
			customerCoupons.add(couponDAO.getOne(coupon.getCouponID()));
		}
		c1.setCoupons(customerCoupons);

		return c1;
	}
}