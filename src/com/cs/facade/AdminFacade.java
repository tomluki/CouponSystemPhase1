package com.cs.facade;

import java.util.List;

import com.cs.bean.Company;
import com.cs.bean.Coupon;
import com.cs.bean.Customer;
import com.cs.dbdao.CompanyDBDAO;
import com.cs.dbdao.CouponDBDAO;
import com.cs.dbdao.CustomerDBDAO;
import com.cs.exceptions.AlreadyExistException;
import com.cs.exceptions.InvalidActionException;
import com.cs.exceptions.NotExistException;

public class AdminFacade extends ClientFacade {

	private CompanyDBDAO companyDBDAO = new CompanyDBDAO();
	private CouponDBDAO couponDBDAO = new CouponDBDAO();
	private CustomerDBDAO customerDBDAO = new CustomerDBDAO();

	@Override
	public boolean login(String email, String password) throws NotExistException {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			System.out.println("Welcome back MASTER");
			return true;
		}
		throw new NotExistException("Wrong Email or Password!");
	}

	public AdminFacade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addCompany(Company company) throws AlreadyExistException {
		List<Company> companies = companyDBDAO.getAllCompanies();
		for (Company company2 : companies) {
			if (company2.getName().equals(company.getName()) || company2.getEmail().equals(company.getEmail())) {
				throw new AlreadyExistException("Company name/Email");
			}
		}

		companyDBDAO.addCompany(company);

	}

	public void updateCompany(Company company) throws InvalidActionException, AlreadyExistException {
		Company c1 = companyDBDAO.getOneCompany(company.getId());
		if (c1.getId() == company.getId() && company.getName().equals(c1.getName())) {
			companyDBDAO.updateCompany(company);

		} else {
			throw new InvalidActionException("Changing company id/name is not allowed");
		}

	}

	public void deleteCompany(int companyID) {
		List<Coupon> coupons = couponDBDAO.getAllCompanyCouponByCompanyId(companyID);
		if (coupons.size() > 0) {
			for (Coupon coupon : coupons) {
				couponDBDAO.deleteCouponPurchaseByCouponID(coupon.getId());
				couponDBDAO.deleteCoupon(coupon.getId());
			}

		}

		companyDBDAO.deleteCompany(companyID);

	}

	public List<Company> getAllCompanies() {
		List<Company> comp = companiesDAO.getAllCompanies();
		return comp;
	}

	public Company getOneCompany(int companyID) {
		Company comp = companiesDAO.getOneCompany(companyID);
		return comp;

	}

	public void addCustomer(Customer customer) throws AlreadyExistException {
		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer customer2 : customers) {
			if (customer2.getEmail().equals(customer.getEmail())) {
				throw new AlreadyExistException("Customer Email");
			}
		}

		customerDBDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws AlreadyExistException {
		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer customer2 : customers) {
			if (customer2.getEmail().equals(customer.getEmail())) {
				throw new AlreadyExistException("Customer Email");
			}
		}
		customerDAO.updateCustomer(customer);
	}

	public void deleteCustomer(int customerID) {
		couponDBDAO.deleteCouponPurchaseByCustomerID(customerID);
		customerDBDAO.deleteCustomer(customerID);
	}

	public List<Customer> getAllCustomers() {
		List<Customer> cust = customerDAO.getAllCustomers();
		return cust;

	}

	public Customer getOneCustomer(int customerID) {
		Customer cust = customerDAO.getOneCustomer(customerID);
		return cust;

	}
}