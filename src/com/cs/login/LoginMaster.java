package com.cs.login;

import com.cs.facade.AdminFacade;
import com.cs.facade.ClientFacade;
import com.cs.facade.CompanyFacade;
import com.cs.facade.CustomerFacade;

public class LoginMaster {
	private static LoginMaster instance = null;

	public LoginMaster() {
		// TODO Auto-generated constructor stub
	}

	public ClientFacade login(String email, String password, ClientType clientType) {
		switch (clientType) {
		case Admin:
			AdminFacade adminFacade = new AdminFacade();
			try {
				adminFacade.login(email, password);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return adminFacade;
		case Company:
			CompanyFacade companyFacade = new CompanyFacade();
			try {
				companyFacade.login(email, password);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return companyFacade;
		case Customer:
			CustomerFacade customerFacade = new CustomerFacade();
			try {
				customerFacade.login(email, password);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return customerFacade;
		default:
			break;
		}

		return null;

	}

	public static LoginMaster getInstance() {
		if (instance == null) {
			synchronized (LoginMaster.class) {
				if (instance == null) {
					instance = new LoginMaster();
				}
			}
		}
		return instance;
	}
}