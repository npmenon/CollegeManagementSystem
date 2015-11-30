package com.system.management.college.common_module.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.management.college.common_module.Connection_config_variables;
import com.system.management.college.common_module.ConstantClass;
import com.system.management.college.common_module.DAO;


public class LoginProcess implements Connection_config_variables{

	private Connection conn = null;
	private DAO dao = null;
	private ResultSet rst= null;
	private PreparedStatement ptmt= null;
	
	public void validateUser(LoginModel login)
	{
		dao = DAO.getDAOInstance();		
		conn = dao.getConnection(driver, url, usr, pwd);
		//validate user
		String message = null;
		String pwdDb = null;
		int privilegeLevelDb = 0;
		boolean loginStatus = false;
		
		String sql = "select pwd,privilege_level from login_details where user_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, login.getUserId());
			rst = ptmt.executeQuery();
			
			//extracting data
			while(rst.next()){
				pwdDb = rst.getString(1);
				privilegeLevelDb = rst.getInt(2);
			}
			
			//System.out.println("returned pass: " + pwdDb + "privilege: " + privilegeLevelDb);
			
			if(login.getPwd().equals(pwdDb) && privilegeLevelDb == login.getPrivilege_level())
				loginStatus = true;
			else{
				
				if(!login.getPwd().equals(pwdDb)){
					message = "password incorrect";
					login.setMessage(message);
				}
					
				else if(privilegeLevelDb != login.getPrivilege_level()){
					message = "User does not have the privilege";
					login.setMessage(message);
				}
				else
					login.setMessage("Cannot login at this moment...Please try later");
			}
			
		      rst.close();
		      ptmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		      
		   }finally{
		      //finally block used to close resources
		      try{
		         if(ptmt!=null)
		            ptmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
			
		login.setStatus(loginStatus);	
	}
	
	public void insertUser(LoginModel login)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
		//insert user
		//validate user
		int noOfRowsUpdated=0;
		boolean loginStatus;
		
		//String sql = "select pwd,privilege_level from login_details where user_id = ?";
		String sql = "update login_details set pwd = ? where user_id = ?";
		try {

			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, login.getPwd());
			ptmt.setString(2, login.getUserId());
			noOfRowsUpdated = ptmt.executeUpdate();		
			
		      ptmt.close();
		      conn.close();

		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		      
		   }finally{
		      //finally block used to close resources
		      try{
		         if(ptmt!=null)
		            ptmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		
		
		if(noOfRowsUpdated==0)
			loginStatus = false;
		else
			loginStatus = true;
		login.setStatus(loginStatus);			
	}
	
	public boolean checkUserExistence(LoginModel login)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
		
		boolean flag = false;
		String sql = null,obtainedId = null;
		try {
			sql = "select * from login_details where user_id = \"" + login.getUserId() + "\" and " +
					"privilege_level = " + login.getPrivilege_level() + ";";
			ptmt = conn.prepareStatement(sql);
			rst = ptmt.executeQuery();
			
			while(rst.next()){
				obtainedId = rst.getString(1);
			}
			
			if(obtainedId!=null && obtainedId.equals(login.getUserId()))
			{
				flag = true;
			}
			else if(obtainedId == null)
				flag = false;
			
		      try {
				ptmt.close();
				  conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
			}

		}catch(SQLException se){
		      //Handle errors for JDBC
			ConstantClass.errorMessage = "invalid: " + se.getMessage() + " .";
		      
		   }catch(Exception e){
		      //Handle errors for Class.forName
			   ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
		      
		   }finally{
		      //finally block used to close resources
		      try{
		         if(ptmt!=null)
		            ptmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException se){
		    	  ConstantClass.errorMessage = "invalid: " + se.getMessage() + " .";
		      }
		   }
		
		return flag;
	}

}
