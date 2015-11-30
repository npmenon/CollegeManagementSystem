package com.system.management.college.student_module;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.management.college.common_module.Connection_config_variables;
import com.system.management.college.common_module.ConstantClass;
import com.system.management.college.common_module.DAO;

public class StudentProcess implements Connection_config_variables{

	private Connection conn = null;
	private DAO dao = null;	
	private ResultSet rst= null;
	private PreparedStatement ptmt= null;
	@SuppressWarnings("unused")
	private BufferedReader bin = null;
	
	public ArrayList<StudentPersonalModel> viewStudentPersonalDetails(StudentPersonalModel student)
	{
		ArrayList<StudentPersonalModel> listStudentDet = new ArrayList<StudentPersonalModel>();
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
		
		//retrieve student personal details from the database and store it in a list
		
		String sql = "select * from student_personal_details where student_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, student.getStudentId());
			rst = ptmt.executeQuery();
			
			while(rst.next()){
				student.setFirstName(rst.getString(2));
				student.setLastName(rst.getString(3));
				student.setEmailId(rst.getString(4));
				student.setContact(rst.getString(5));
				student.setJoinDate(rst.getString(6));
				student.setDateOfBirth(rst.getString(7));
				listStudentDet.add(student);
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
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
	
	
		return listStudentDet;
	}
	
	public ArrayList<StudentPrevAcademicModel > viewStudentPrevAcademicDetails(StudentPrevAcademicModel student)
	{
		ArrayList<StudentPrevAcademicModel > listStudentDet = new ArrayList<StudentPrevAcademicModel >();
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
	
		//retrieve student previous academic details and store it in the list 
		
		String sql = "select * from  student_prev_academic_details where student_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, student.getStudentId());
			rst = ptmt.executeQuery();
			
			while(rst.next()){
				student.setTenInstit((rst.getString(2)));
				student.setTenMarks((rst.getFloat(3)));
				student.setTwelveInstit((rst.getString(4)));
				student.setTwelveMarks((rst.getFloat(5)));
				student.setPrevProjects((rst.getString(6)));
				listStudentDet.add(student);
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
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		
		return listStudentDet;
	}
	
	public ArrayList<StudentCurrAcademicModel> viewStudentCurrAcademicDetails(StudentCurrAcademicModel student)
	{
		ArrayList<StudentCurrAcademicModel> listStudentDet = new ArrayList<StudentCurrAcademicModel>();
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
	
		
		String sql = "select * from  student_curr_academic_details where student_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, student.getStudentId());
			rst = ptmt.executeQuery();
			
			while(rst.next()){
				student.setDepartmentId((rst.getString(2)));
				student.setCurrentSem((rst.getInt(3)));
				student.setSem1Marks((rst.getFloat(4)));
				student.setSem2Marks((rst.getFloat(5)));
				student.setSem3Marks((rst.getFloat(6)));
				student.setSem4Marks((rst.getFloat(7)));
				listStudentDet.add(student);
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
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		
		
		return listStudentDet;
	}
	
	public boolean modifyPersonalDetails(StudentPersonalModel student,List<Integer> choiceList)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
		
		boolean updationStatus = false;
		int update = 0;
		//delete student details from database
		
		String sql = null;
		for(Integer choice: choiceList){
			
			updationStatus = false;
			update = 0;

			switch (choice) {
			case 1:
				sql = "update student_personal_details set first_name = \""+ student.getFirstName()
				+"\" where student_id = \""+student.getStudentId()+ "\";";
				break;
				
			case 2:
				sql = "update student_personal_details set last_name = \""+ student.getLastName()
						+"\" where student_id = \""+student.getStudentId()+ "\";";
				break;

			case 3:
				sql = "update student_personal_details set email_id = \""+ student.getEmailId()
						+"\" where student_id = \""+student.getStudentId()+ "\";";
				break;
			
			case 4:
				sql = "update student_personal_details set contact = \""+ student.getContact()
						+"\" where student_id = \""+student.getStudentId()+ "\";";
				break;
				
			case 5:
				sql = "update student_personal_details set join_date = \""+ student.getJoinDate()
						+"\" where student_id = \""+student.getStudentId()+ "\";";
				break;
				
			case 6:
				sql = "update student_personal_details set date_of_birth = \""+ student.getDateOfBirth()
						+"\" where student_id = \""+student.getStudentId()+ "\";";
				break;

			default:
				break;
			}
				
			try {

				ptmt = conn.prepareStatement(sql);
				update = ptmt.executeUpdate();
				
			    if(update!=0)
			    	updationStatus = true;
			    else{
			    	ConstantClass.errorMessage = "Error in: " + sql + " .";
			    	break;
			    }
			    
			}//try 
			catch (SQLException e) {
				updationStatus = false;
				ConstantClass.errorMessage = " invalid: " + e.getMessage() + " .";
				break;
			}//catch
		}//forEach loop
	//============================================================================	
		
				
		try {
			
			ptmt.close();
		    conn.close();
			
		} catch(Exception e){
			updationStatus = false;
			ConstantClass.errorMessage = " invalid: " + e.getMessage() + " .";
		      
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
		    	  updationStatus = false;
		    	  ConstantClass.errorMessage = " invalid: " + se.getMessage() + " .";
		      }//end finally try
		   }//end try
		
		return updationStatus;	

	}
	
	public List<String> getStudentList(String userId)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
		List<String> listOfStud = new ArrayList<String>();
		
		String sql = "select student_id from student_curr_academic_details  inner join faculty_prof_details " +
				"USING (department_id) where faculty_id = \"" + userId + "\"";
		
		try {
			ptmt = conn.prepareStatement(sql);
			rst = ptmt.executeQuery();
			
			while(rst.next()){
				listOfStud.add(rst.getString(1));
			}
			
		      try {
				rst.close();
				  ptmt.close();
				  conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
			}
			
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
		
		return listOfStud;
	}
	 
}
