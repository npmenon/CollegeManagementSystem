package com.system.management.college.common_module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

	private static DAO d = null;
	private DAO() {}
	
	//singleton
	public static DAO getDAOInstance(){
	if(d==null)
		d = new DAO();

	return d;
	}
	
	Connection conn = null;
	
	public Connection getConnection(String driver, String url, String usr, String pwd)
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url,usr,pwd);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		return conn;
	}
}
