package com.system.management.college.department_module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.management.college.common_module.Connection_config_variables;
import com.system.management.college.common_module.ConstantClass;
import com.system.management.college.common_module.DAO;

public class DepartmentProcess implements Connection_config_variables{

	private Connection conn = null;
	private DAO dao = null;	
	private PreparedStatement ptmt= null;
	private ResultSet rst= null;
	
	public DepartmentModel getDeptId(DepartmentModel dept){

		String returnString = "noDept";
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);

		String sql = "select department_id from department_details where department_name = \"" +
				dept.getDepartmentName().toLowerCase()+ "\";";
		
		
		try {
			ptmt = conn.prepareStatement(sql);
			rst = ptmt.executeQuery();
			
			while(rst.next()){
				returnString = rst.getString(1);
			}
			
		      rst.close();
		      ptmt.close();
		      conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}catch(Exception e){
		      //Handle errors for Class.forName
			ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
		      
		}finally{
		      //finally block used to close resources
		      try{
		         if(ptmt!=null)
		            ptmt.close();
		      }catch(SQLException se2){
		    	  ConstantClass.errorMessage = "invalid: " + se2.getMessage() + " .";
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException se){
		    	  ConstantClass.errorMessage = "invalid: " + se.getMessage() + " .";
		      }//end finally try
		   }//end try
		
		dept.setDepartmentId(returnString);
		return dept;
	}
	
	public List<String> getDeptName()
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
		List<String> listOfDept = new ArrayList<String>();
		
		String sql = "select department_name from department_details";
		
		try {
			ptmt = conn.prepareStatement(sql);
			rst = ptmt.executeQuery();
			
			while(rst.next()){
				listOfDept.add(rst.getString(1));
			}
			
		      rst.close();
		      ptmt.close();
		      conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}catch(Exception e){
		      //Handle errors for Class.forName
			ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
		      
		}finally{
		      //finally block used to close resources
		      try{
		         if(ptmt!=null)
		            ptmt.close();
		      }catch(SQLException se2){
		    	  ConstantClass.errorMessage = "invalid: " + se2.getMessage() + " .";
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException se){
		    	  ConstantClass.errorMessage = "invalid: " + se.getMessage() + " .";
		      }//end finally try
		   }//end try
		
		return listOfDept;
	}

}
