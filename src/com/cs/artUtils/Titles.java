package com.cs.artUtils;

public interface Titles {

	public static void adminFacade() {
		System.out.println(
				"============================================================ADMIN FACADE TESTING============================================================");
	}

	public static void companyFacade() {
		System.out.println(
				"============================================================COMPANY FACADE TESTING============================================================");
	}

	public static void customerFacade() {
		System.out.println(
				"============================================================CUSTOMER FACADE TESTING============================================================");
	}

	public static void login(String client) {
		System.out.println("============================================================Login " + client
				+ "============================================================");

	}

	public static void add(String object) {
		System.out.println("============================================================Add " + object
				+ "============================================================");
	}

	public static void update(String object) {
		System.out.println("============================================================Update " + object
				+ "============================================================");
	}

	public static void delete(String object) {
		System.out.println("============================================================Delete " + object
				+ "============================================================");
	}

	public static void getOne(String object) {
		System.out.println("============================================================Get One " + object
				+ "============================================================");
	}

	public static void getDetails(String client) {
		System.out.println("============================================================Get Details For " + client
				+ "============================================================");
	}

	public static void getAllCoupons(String client) {
		System.out.println("============================================================Get All Coupons for " + client
				+ "============================================================");
	}

	public static void getAllByCategory(String client) {
		System.out.println("============================================================Get All Coupons For " + client
				+ " By Category============================================================");
	}

	public static void getAllByPrice(String client) {
		System.out.println("============================================================Get All Coupons For " + client
				+ " By Price============================================================");
	}

	public static void getAll(String client, String object) {
		System.out.println("============================================================Get All " + object + " For "
				+ client + "============================================================");
	}

}
