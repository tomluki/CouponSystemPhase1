package com.cs.bean;

import java.util.List;

public class Company {
	private static int count = 0;
	private int id;
	private String name;
	private String email;
	private String password;
	private List<Coupon> coupons;

	public Company() {

	}
//no id constructor
	public Company( String name, String email, String password) {
		super();
		this.id = ++count;
		this.name = name;
		this.email = email;
		this.password = password;
	}
//full constructor
	public Company(int id, String name, String email, String password, List<Coupon> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}
// no coupons constructor
	public Company(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Companies [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
				+ coupons + "]";
	}

}