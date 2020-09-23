package com.cs.bean;

import java.sql.Date;

public class Coupon {

	private int id;
	private int companyId;
	private Category category;
	private String title;
	private String description;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private int amount;
	private double price;
	private String image;
	private static int count = 0;

	// get from sql
	public Coupon(int id, int companyId, int category, String title, String decription, Date startDate, Date endDate,
			int amount, double price, String image) {

		this.id = id;
		this.companyId = companyId;
		this.category = Category.values()[category];
		this.title = title;
		this.description = decription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

// insert to sql
	public Coupon(int companyId, Category category, String title, String decription, java.sql.Date startDate,
			java.sql.Date endDate, int amount, double price, String image) {
		this.id = ++count;
		this.companyId = companyId;
		this.category = category;
		this.title = title;
		this.description = decription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	public Coupon() {
		this.id = ++count;
	}

	public Coupon(int int1, int int2, Category categories, String string, String string2, Date date, Date date2,
			int int3, double double1, String string3) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String decription) {
		this.description = decription;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupons [id=" + id + ", companyId=" + companyId + ", category=" + category + ", title=" + title
				+ ", decription=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

}