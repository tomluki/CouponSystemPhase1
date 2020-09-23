package com.cs.bean;

public class CustomerVsCoupon {
	private int customerID;
	private int couponID;

	public CustomerVsCoupon(int customerID, int couponID) {
		this.customerID = customerID;
		this.couponID = couponID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getCouponID() {
		return couponID;
	}

	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}

	@Override
	public String toString() {
		return "Customer_VS_Coupon [customerID=" + customerID + ", couponID=" + couponID + "]";
	}

}
