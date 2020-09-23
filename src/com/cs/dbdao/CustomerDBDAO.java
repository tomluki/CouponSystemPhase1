package com.cs.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cs.bean.Customer;
import com.cs.dao.CustomerDAO;
import com.cs.utils.ConnectionPool;

public class CustomerDBDAO implements CustomerDAO {

	private static final String insert = "INSERT INTO `couponsystem`.`customers` ( `first_name`, `last_name`, `email`, `password`) VALUES ( ?, ?, ?, ?);";
	private static final String update = "UPDATE `couponsystem`.`customers` SET `first_name` = ?, `last_name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";
	private static final String delete = "DELETE FROM `couponsystem`.`customers` WHERE (`id` = ?);";
	private static final String getOne = "SELECT * FROM couponsystem.customers where id = ?;";
	private static final String getAll = "SELECT * FROM couponsystem.customers;";
	private static final String FindByEmailAndPassword = "SELECT * FROM couponsystem.customers where email = ? and password =?;";

	@Override
	public boolean isCustomerExist(String email, String password) {
		Customer c1 = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(FindByEmailAndPassword);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				c1 = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5));
				if (c1.getEmail().equals(email) && c1.getPassWord().equals(password)) {

					return true;
				}

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

		return false;
	}

	@Override
	public void addCustomer(Customer customer) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(insert);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassWord());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void updateCustomer(Customer customer) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(update);
			statement.setInt(5, customer.getId());
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassWord());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void deleteCustomer(int id) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(delete);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public Customer getOneCustomer(int id) {
		Customer u1 = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(getOne);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			u1 = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

		return u1;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(getAll);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				customers.add(new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5)));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

		return customers;
	}

}