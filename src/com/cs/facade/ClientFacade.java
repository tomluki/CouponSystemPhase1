package com.cs.facade;

import com.cs.dao.CompanyDAO;
import com.cs.dao.CouponDAO;
import com.cs.dao.CustomerDAO;
import com.cs.dbdao.CompanyDBDAO;
import com.cs.dbdao.CouponDBDAO;
import com.cs.dbdao.CustomerDBDAO;

import com.cs.exceptions.NotExistException;

public abstract class ClientFacade {

	protected CompanyDAO companiesDAO;
	protected CustomerDAO customerDAO;
	protected CouponDAO couponDAO;

	public ClientFacade() {
		this.companiesDAO = new CompanyDBDAO();
		this.customerDAO = new CustomerDBDAO();
		this.couponDAO = new CouponDBDAO();
	}

	public abstract boolean login(String email, String password) throws NotExistException;
}