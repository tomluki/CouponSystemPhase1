package com.cs.testing;

import java.sql.SQLException;

import com.cs.artUtils.Titles;
import com.cs.dao.CouponDAO;
import com.cs.db.DataBaseManager;
import com.cs.dbdao.CouponDBDAO;
import com.cs.exceptions.AlreadyExistException;
import com.cs.facade.AdminFacade;
import com.cs.facade.CompanyFacade;
import com.cs.facade.CustomerFacade;
import com.cs.thread.DailyinvalidCouponJob;
import com.cs.utils.ConnectionPool;

public class Test implements Titles {
	public static void testAll()
			throws ClassNotFoundException, SQLException, InterruptedException, AlreadyExistException {
		try {
			System.out.println("START");
			Class.forName("com.mysql.cj.jdbc.Driver");
			DataBaseManager.dropTables();
			System.out.println("Dropping all tables");
			System.out.println("Creating all tables");
			DataBaseManager.createAllTables();
			DailyinvalidCouponJob dailyJob = new DailyinvalidCouponJob();
			Thread d1 = new Thread(dailyJob);
			d1.start();
			AdminTesting.adminFacadeTesting();
			CompanyTesting.companyFacadeTesting();
			CustomerTesting.customerFacadeTesting();
			CompanyFacade companyFacade = new CompanyFacade(1);
			CustomerFacade customerFacade = new CustomerFacade(1);
			AdminFacade adminFacade = new AdminFacade();
			CouponDAO couponDAO = new CouponDBDAO();
			System.out.println("Part 2: deleting coupons+purchse history");
			Titles.delete("Coupon");
			companyFacade.deleteCoupon(1);
			Titles.getAllCoupons("Customer");
			System.out.println("coupon was deleted from the customers list");
			System.out.println(customerFacade.getCustomerCoupons());
			Titles.getAllCoupons("Company");
			System.out.println("coupon was deleted from the companys list");
			System.out.println(companyFacade.getCompanyCoupon());
			Titles.getAllCoupons("Coupons");
			System.out.println("coupon was deleted");
			System.out.println(couponDAO.getAllCoupons());
			Titles.adminFacade();
			System.out.println("Part 2: deleting company/customers with coupons/purchse history history");
			Titles.delete("Company");
			System.out.println("company was deleted");
			adminFacade.deleteCompany(1);
			adminFacade.getAllCompanies();
			System.out.println("coupon removed from customer list");
			System.out.println(customerFacade.getCustomerCoupons());
			System.out.println("coupon was removed");
			System.out.println(couponDAO.getAllCoupons());
			Titles.delete("Customer");
			adminFacade.deleteCustomer(1);
			System.out.println("Customer was deleted");
			Titles.getAll("Admin Facade", "Customers");
			System.out.println(adminFacade.getAllCustomers());
			System.out.println("END");
			Thread.sleep(1000 * 90);
			dailyJob.stop();
			System.out.println("expired coupon was deleted by dailyJob");
			System.out.println(couponDAO.getAllCoupons());
			d1.interrupt();
			ConnectionPool.getInstance().closeAllConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
