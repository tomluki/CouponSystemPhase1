package com.cs.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cs.bean.Company;
import com.cs.dao.CompanyDAO;
import com.cs.utils.ConnectionPool;

public class CompanyDBDAO implements CompanyDAO {

	private static final String INSERT_QUERY = "INSERT INTO `couponsystem`.`companies` (`name`, `email`, `password`) VALUES (?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE `couponsystem`.`companies` SET `name` = ?, `email`= ?, `password`= ?  WHERE (`id` = ?);";
	private static final String DELETE_QUERY = "DELETE FROM `couponsystem`.`companies` WHERE (`id` = ?);";
	private static final String GETONE_QUERY = "SELECT * FROM couponsystem. companies WHERE id=?;";
	private static final String GETALL_QUERY = "SELECT * FROM couponsystem.companies;";
	private static final String FIND_BY_EMAIL_PASS = "SELECT * FROM couponsystem.companies WHERE email = ? and password =?;";

	@Override
	public void addCompany(Company company) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(INSERT_QUERY);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.executeUpdate();
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);
	}

	@Override
	public void updateCompany(Company company) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.setInt(4, company.getId());
			statement.executeUpdate();
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);
	}

	@Override
	public void deleteCompany(int id) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (InterruptedException | SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);
	}

	@Override
	public Company getOneCompany(int companyid) {
		Company c1 = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(GETONE_QUERY);
			statement.setInt(1, companyid);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			c1 = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

		return c1;
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(GETALL_QUERY);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				companies.add(new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4)));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

		return companies;
	}

	@Override
	public boolean isCompanyExist(String email, String password) {
		Company c1 = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(FIND_BY_EMAIL_PASS);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				c1 = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4));
				if (c1.getEmail().equals(email) && c1.getPassword().equals(password)) {

					return true;
				}

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

		return false;
	}

}
