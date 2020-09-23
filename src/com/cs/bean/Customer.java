package com.cs.bean;

import java.util.List;

public class Customer {
	private static int count = 0;
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String passWord;
	private List<Coupon> coupons;

//empty Constructor
	public Customer() {
		super();
	}

//Constructor with no id(auto increment)
	public Customer(String firstName, String lastName, String email, String passWord) {
		this.id = ++count;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passWord = passWord;
	}

//Constructor with no List
	public Customer(int id, String firstName, String lastName, String email, String passWord) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passWord = passWord;
	}

//Full constructor
	public Customer(int id, String firstName, String lastName, String email, String passWord, List<Coupon> coupons) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passWord = passWord;
		this.coupons = coupons;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Customers [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", passWord=" + passWord + ", coupons=" + coupons + "]";
	}

}