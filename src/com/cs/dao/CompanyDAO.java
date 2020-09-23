package com.cs.dao;

import java.util.List;

import com.cs.bean.Company;

public interface CompanyDAO {

	boolean isCompanyExist(String email, String password);

	void addCompany(Company company);

	void updateCompany(Company company);

	Company getOneCompany(int id);

	List<Company> getAllCompanies();

	void deleteCompany(int id);

}