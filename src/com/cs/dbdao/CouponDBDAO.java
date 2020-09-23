package com.cs.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cs.bean.Coupon;
import com.cs.bean.CustomerVsCoupon;
import com.cs.dao.CouponDAO;
import com.cs.utils.ConnectionPool;

public class CouponDBDAO implements CouponDAO {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static final String INSERT = "INSERT INTO `couponsystem`.`coupons` ( `company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES ( ?, ?, ?, ?,?, ?,?, ?, ?);";
	private static final String UPDATE = "UPDATE `couponsystem`.`coupons` SET  `category_id` = ?, `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?);";
	private static final String DELETE = "DELETE FROM `couponsystem`.`coupons` WHERE (`id` = ?);";
	private static final String GET_ONE = "SELECT * FROM couponsystem.coupons where id = ?;";
	private static final String GET_ALL = "SELECT * FROM couponsystem.coupons;";
	private static final String ADD_PURCHASE = "INSERT INTO `couponsystem`.`customers_vs_coupons` (`customer_id`, `coupon_id`) VALUES (?, ?);";
	private static final String DELETE_PURCHASE = "DELETE FROM `couponsystem`.`customers_vs_coupons` WHERE (`customer_id` = ?) and (`coupon_id` = ?);";
	private static final String FIND_COMPANY_COUPONS = "SELECT * FROM couponsystem.coupons where company_id = ?;";
	private static final String Get_COUPON_ID_BY_CUSTOMER_ID = "SELECT * FROM couponsystem.customers_vs_coupons WHERE customer_id = ?;";
	private static final String DELETE_COUPON_PURCHASE_BY_COUPON_ID = "DELETE FROM `couponsystem`.`customers_vs_coupons` WHERE `coupon_id` = ?;";
	private static final String DELETE_COUPON_PURCHASE_BY_CUSTOMER_ID = "DELETE FROM `couponsystem`.`customers_vs_coupons` WHERE `customer_id` = ?;";

	@Override
	public void addCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(INSERT);
			statement.setInt(1, coupon.getCompanyId());
			statement.setInt(2, coupon.getCategory().ordinal() + 1);
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, coupon.getStartDate());
			statement.setDate(6, coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void updateCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(UPDATE);
			statement.setInt(1, coupon.getCategory().ordinal() + 1);
			statement.setString(2, coupon.getTitle());
			statement.setString(3, coupon.getDescription());
			statement.setDate(4, coupon.getStartDate());
			statement.setDate(5, coupon.getEndDate());
			statement.setInt(6, coupon.getAmount());
			statement.setDouble(7, coupon.getPrice());
			statement.setString(8, coupon.getImage());
			statement.setInt(9, coupon.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public void deleteCoupon(int couponID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(DELETE);
			statement.setInt(1, couponID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public Coupon getOne(int id) {
		Coupon p1 = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(GET_ONE);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			p1 = new Coupon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3) - 1, resultSet.getString(4),
					resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7), resultSet.getInt(8),
					resultSet.getDouble(9), resultSet.getString(10));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

		return p1;
	}

	@Override
	public List<Coupon> getAllCoupons() {
		List<Coupon> coupons = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(GET_ALL);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				coupons.add(new Coupon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3) - 1,
						resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
						resultSet.getInt(8), resultSet.getDouble(9), resultSet.getString(10)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

		return coupons;
	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println("am i the Erorr?" + e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(ADD_PURCHASE);
			statement.setInt(1, customerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("am i the Erorr?" + e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}

	}

	@Override
	public void deleteCouponPurchase(int customerID, int coupomID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(DELETE_PURCHASE);
			statement.setInt(1, customerID);
			statement.setInt(2, coupomID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	@Override
	public List<Coupon> getAllCompanyCouponByCompanyId(int id) {
		List<Coupon> coupons = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(FIND_COMPANY_COUPONS);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				coupons.add(new Coupon(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3) - 1,
						resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
						resultSet.getInt(8), resultSet.getDouble(9), resultSet.getString(10)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

		return coupons;
	}

	public void deleteCouponPurchaseByCouponID(int couponID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(DELETE_COUPON_PURCHASE_BY_COUPON_ID);
			statement.setInt(1, couponID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	public void deleteCouponPurchaseByCustomerID(int customerID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(DELETE_COUPON_PURCHASE_BY_CUSTOMER_ID);
			statement.setInt(1, customerID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	public List<CustomerVsCoupon> getAllCouponsIDByCustomerID(int customerID) {
		List<CustomerVsCoupon> cvc = new ArrayList<CustomerVsCoupon>();
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			PreparedStatement statement = conn.prepareStatement(Get_COUPON_ID_BY_CUSTOMER_ID);
			statement.setInt(1, customerID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int couponID = resultSet.getInt(2);
				cvc.add(new CustomerVsCoupon(customerID, couponID));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connectionPool.returnConnection(conn);
		}
		return cvc;
	}

}