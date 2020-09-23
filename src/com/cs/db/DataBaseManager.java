package com.cs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cs.utils.ConnectionPool;

public class DataBaseManager {

	private final static String URL = "jdbc:mysql://localhost:3306/CouponSystem?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=UTC";
	private final static String USER = "root";
	private final static String PASS = "glikamashe95";

	private final static String QUARY_CREATE_COMPANIES = "CREATE TABLE `couponsystem`.`companies` (\r\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `email` VARCHAR(45) NOT NULL,\r\n" + "  `password` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`id`));";

	private final static String QUARY_CREATE_CUSTOMER = "CREATE TABLE `couponsystem`.`customers` (\r\n"
			+ " `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `first_name` VARCHAR(45) NOT NULL,\r\n"
			+ " `last_name` VARCHAR(45) NOT NULL,\r\n" + "  `email` VARCHAR(45) NOT NULL,\r\n"
			+ "	`password` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`));";

	private final static String QUARY_CREATE_CATEGORIES = "CREATE TABLE `couponsystem`.`categories` (\r\n"
			+ "	 `id` INT NOT NULL AUTO_INCREMENT,\r\n" + " `name` VARCHAR(45) NOT NULL,\r\n"
			+ "	 PRIMARY KEY (`id`));";

	private static final String QUARY_INSERT_CATEGORY = "INSERT INTO `couponsystem`.`categories` (`name`) VALUES (?);";

	private final static String QUARY_CREATE_COUPON = "CREATE TABLE `couponsystem`.`coupons` (\r\n"
			+ " `id` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `company_id` INT NOT NULL,\r\n"
			+ "	`category_id` INT NOT NULL,\r\n" + "  `title` VARCHAR(45) NOT NULL,\r\n"
			+ "	`description` VARCHAR(45) NOT NULL,\r\n" + "  `start_date` DATE NOT NULL,\r\n"
			+ "	`end_date` DATE NOT NULL,\r\n" + "  `amount` INT NOT NULL,\r\n" + "  `price` DOUBLE NOT NULL,\r\n"
			+ "	`image` VARCHAR(45) NOT NULL,\r\n" + "  PRIMARY KEY (`id`),\r\n"
			+ "	 INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\r\n"
			+ "INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `company_id`\r\n"
			+ "	FOREIGN KEY (`company_id`)\r\n" + "    REFERENCES `couponsystem`.`companies` (`id`)\r\n"
			+ "	ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `category_id`\r\n"
			+ "	FOREIGN KEY (`category_id`)\r\n" + "    REFERENCES `couponsystem`.`categories` (`id`)\r\n"
			+ "	ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);";

	private final static String QUARY_CREATE_CUSTOMERS_VS_COUPON = "CREATE TABLE `couponsystem`.`customers_vs_coupons` (\r\n"
			+ " `customer_id` INT NOT NULL,\r\n" + "  `coupon_id` INT NOT NULL,\r\n"
			+ " INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\r\n" + "  CONSTRAINT `customer_id`\r\n"
			+ " FOREIGN KEY (`customer_id`)\r\n" + "    REFERENCES `couponsystem`.`customers` (`id`)\r\n"
			+ " ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `coupon_id`\r\n"
			+ " FOREIGN KEY (`coupon_id`)\r\n" + "    REFERENCES `couponsystem`.`coupons` (`id`)\r\n"
			+ " ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION);";

	private final static String QUARY_DROP_CATEGORIES = "DROP TABLE `couponsystem`.`categories`;";
	private final static String QUARY_DROP_COMPANIES = "DROP TABLE `couponsystem`.`companies`;";
	private final static String QUARY_DROP_CUSTOMERS = "DROP TABLE `couponsystem`.`customers`;";
	private final static String QUARY_DROP_COUPON = "DROP TABLE `couponsystem`.`coupons`;";
	private final static String QUARY_DROP_CUSTOMERS_VS_COUPONS = "DROP TABLE `couponsystem`.`customers_vs_coupons`;";

	public static void createTableCustomers() throws SQLException {
		createAndDropTable(QUARY_CREATE_CUSTOMER);
	}

	public static void createTableCompanies() throws SQLException {
		createAndDropTable(QUARY_CREATE_COMPANIES);
	}

	public static void createTableCategories() throws SQLException {
		createAndDropTable(QUARY_CREATE_CATEGORIES);
	}

	public static void createTableCoupon() throws SQLException {
		createAndDropTable(QUARY_CREATE_COUPON);
	}

	public static void createTableCustomersVsCoupon() throws SQLException {
		createAndDropTable(QUARY_CREATE_CUSTOMERS_VS_COUPON);
	}

	public static void dropTableCustomers() throws SQLException {
		createAndDropTable(QUARY_DROP_CUSTOMERS);
	}

	public static void dropTableCompanies() throws SQLException {
		createAndDropTable(QUARY_DROP_COMPANIES);
	}

	public static void dropTableCategories() throws SQLException {
		createAndDropTable(QUARY_DROP_CATEGORIES);
	}

	public static void dropTableCoupon() throws SQLException {
		createAndDropTable(QUARY_DROP_COUPON);
	}

	public static void dropTableCustomersVsCoupon() throws SQLException {
		createAndDropTable(QUARY_DROP_CUSTOMERS_VS_COUPONS);
	}

	public static void buildCatergories(String category) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(QUARY_INSERT_CATEGORY);
			statement.setString(1, category);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ConnectionPool.getInstance().returnConnection(conn);

	}

	public static void dropTables() throws SQLException {
		dropTableCustomersVsCoupon();
		dropTableCoupon();
		dropTableCategories();
		dropTableCompanies();
		dropTableCustomers();
	}

	public static void createAllTables() throws SQLException {

		createTableCategories();
		buildCatergories("Food");
		buildCatergories("Electronics");
		buildCatergories("Vacation");
		buildCatergories("Gaming");
		buildCatergories("Cloths");
		createTableCompanies();
		createTableCoupon();
		createTableCustomers();
		createTableCustomersVsCoupon();

	}

	public static void createAndDropTable(String sql) throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(URL, USER, PASS);
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.executeUpdate();
		ConnectionPool.getInstance().returnConnection(conn);
	}

	public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPass() {
		return PASS;
	}
}