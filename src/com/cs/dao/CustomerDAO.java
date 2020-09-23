package com.cs.dao;

import java.util.List;

import com.cs.bean.Customer;

public interface CustomerDAO {

	boolean isCustomerExist(String email, String password);

	void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	Customer getOneCustomer(int id);

	List<Customer> getAllCustomers();

	void deleteCustomer(int customerID);

}