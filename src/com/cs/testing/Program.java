package com.cs.testing;

import java.sql.SQLException;

import com.cs.exceptions.AlreadyExistException;

public class Program {

	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, InterruptedException, AlreadyExistException {
		Test.testAll();

	}

}
