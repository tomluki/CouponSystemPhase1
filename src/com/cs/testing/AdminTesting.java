package com.cs.testing;

import com.cs.artUtils.Titles;
import com.cs.bean.Company;
import com.cs.bean.Customer;
import com.cs.exceptions.AlreadyExistException;
import com.cs.exceptions.InvalidActionException;
import com.cs.facade.AdminFacade;
import com.cs.login.ClientType;
import com.cs.login.LoginMaster;

public class AdminTesting implements Titles {
	public static void adminFacadeTesting() throws AlreadyExistException, InvalidActionException {
		LoginMaster loginMaster = new LoginMaster();
		Titles.adminFacade();
		System.out.println("Part 1:creating and editing companies and customers");
		AdminFacade adminFacade = (AdminFacade) loginMaster.login("admin@admin.com", "admin", ClientType.Admin);
		System.out.println("Initializing companies");
		Company c1 = new Company("Coca-Cola", "coca@food.com", "cocacolalife");
		Company c2 = new Company("Razer", "razer@gaming.com", "razerlife");
		Company c3 = new Company("Sony", "Sony@electronics.com", "teslalife");
		Titles.add("Company");
		adminFacade.addCompany(c1);
		adminFacade.addCompany(c2);
		adminFacade.addCompany(c3);
		try {
			adminFacade.addCompany(c3);
		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}
		System.out.println("Unable to add a company with an existing name/email");
		Titles.getAll("AdminFacade", "Companies");
		System.out.println(adminFacade.getAllCompanies());
		Titles.update("Company");
		c1.setEmail("cola@changed.com");
		System.out.println("Id was not changed as changing any id is not allowed on the DBDAO level");
		adminFacade.updateCompany(c1);
		Titles.getOne("Company");
		System.out.println(adminFacade.getOneCompany(1));
		Customer cu1 = new Customer("Tom", "lukman", "tom@lukman.com", "1234");
		Customer cu2 = new Customer("Shalev", "Grinberg", "shalev@grinberg.com", "1234");
		Customer cu3 = new Customer("Jon", "snow", "jon@snow.com", "ghost");
		Titles.add("Customers");
		adminFacade.addCustomer(cu1);
		adminFacade.addCustomer(cu2);
		adminFacade.addCustomer(cu3);

		try {
			adminFacade.addCustomer(cu3);
		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}
		Titles.getAll("Admin Facade", "Customers");
		System.out.println("Unable to add a customer with an existing name/email");
		System.out.println(adminFacade.getAllCustomers());
		Titles.update("Customer");
		cu1.setEmail("tom@changed.com");
		System.out.println("Id was not changed as changing any id is not allowed on the DBDAO level");
		adminFacade.updateCustomer(cu1);
		Titles.getOne("Customer");
		System.out.println(adminFacade.getOneCustomer(cu1.getId()));

	}

}
