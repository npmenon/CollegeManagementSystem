package com.system.management.college.faculty_module;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.system.management.college.common_module.Connection_config_variables;
import com.system.management.college.common_module.ConstantClass;
import com.system.management.college.common_module.DAO;
import com.system.management.college.student_module.StudentCurrAcademicModel;

public class FacultyProcess implements Connection_config_variables{

	private Connection conn = null;
	private DAO dao = null;	
	private ResultSet rst= null;
	private PreparedStatement ptmt= null;
	@SuppressWarnings("unused")
	private BufferedReader bin = null;
	
	public ArrayList<FacultyPersonalModel> viewPersonalProfile(FacultyPersonalModel faculty)
	{
		String sql = null;
		FacultyPersonalModel facultyObj = null;
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
		ArrayList<FacultyPersonalModel> listFacultyDet = new ArrayList<FacultyPersonalModel>();
		//get facultyId and retrieve personal profile, store it in a list and return it to FacultyView
		
		//retrieve student personal details from the database and store it in a list
		
		String pat1 = "listOffaculties:";
		String pat2 = "viewingPersonalProfile";
		Pattern pattern1 = Pattern.compile(pat1);
		Pattern pattern2 = Pattern.compile(pat2);
		
		if(pattern1.matcher(faculty.getMessage()).find()){
		
			String deptId = faculty.getMessage().split(":")[1];
			sql = "select faculty_id,first_name,last_name from faculty_personal_details where faculty_id IN " +
					"(select faculty_id from faculty_prof_details where department_id = \"" + deptId + "\" )";
			
			try {
				ptmt = conn.prepareStatement(sql);
				rst = ptmt.executeQuery();
				
				while(rst.next()){
					
					facultyObj = new FacultyPersonalModel();
					facultyObj.setFacultyId(rst.getString(1));
					facultyObj.setFirstName(rst.getString(2));
					facultyObj.setLastName(rst.getString(3));
					listFacultyDet.add(facultyObj);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				ConstantClass.errorMessage = "invalid: " + e1.getMessage() + " .";
			}

		}
		else if (pattern2.matcher(faculty.getMessage()).matches()){
			sql = "select * from faculty_personal_details where faculty_id = ?";
			
			try {
				ptmt = conn.prepareStatement(sql);
				ptmt.setString(1,faculty.getFacultyId());
				rst = ptmt.executeQuery();
				
				while(rst.next()){
					faculty.setFirstName(rst.getString("first_name"));
					faculty.setLastName(rst.getString("last_name"));
					faculty.setEmailId(rst.getString("email_id"));
					faculty.setContact(rst.getString("contact"));
					faculty.setHire_date(rst.getString("hire_date"));
					faculty.setDateOfBirth(rst.getString("date_of_birth"));
					listFacultyDet.add(faculty);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				ConstantClass.errorMessage = "invalid: " + e1.getMessage() + " .";
			}
		}	
				
		try {
		      rst.close();
		      ptmt.close();
		      conn.close();
		      
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
		      }catch(SQLException e){
		    	  ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException e){
		    	  ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
		      }//end finally try
		   }//end try
	
	
		return listFacultyDet;
		
	}
	
	public ArrayList<FacultyProfModel> viewProfessionalProfile(FacultyProfModel faculty)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
		ArrayList<FacultyProfModel> listFacultyDet = new ArrayList<FacultyProfModel>();
		
		String sql = "select * from faculty_prof_details where faculty_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1,faculty.getFacultyId());
			rst = ptmt.executeQuery();
			
			while(rst.next()){
				faculty.setDepartmentId(rst.getString("department_id"));
				faculty.setGradInstit(rst.getString("grad_instit"));
				faculty.setGradMarks(rst.getFloat("grad_marks"));
				faculty.setPostGradInstit(rst.getString("post_grad_instit"));
				faculty.setPostGradMarks(rst.getFloat("post_grad_marks"));
				faculty.setCurrentDesign(rst.getString("current_design"));
				listFacultyDet.add(faculty);
			}
			
		      rst.close();
		      ptmt.close();
		      conn.close();
		      
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
		      }catch(SQLException e){
		    	  ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		         
		      }catch(SQLException e){
		    	  ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
		      }//end finally try
		   }//end try
		
		return listFacultyDet;
	}

	public boolean updatePersonalProfile(FacultyPersonalModel faculty, List<Integer> choiceList)
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
				sql = "update faculty_personal_details set first_name = \""+ faculty.getFirstName()
				+"\" where faculty_id = \""+faculty.getFacultyId()+ "\";";
				break;
				
			case 2:
				sql = "update faculty_personal_details set last_name = \""+ faculty.getLastName()
						+"\" where faculty_id = \""+faculty.getFacultyId()+ "\";";
				break;

			case 3:
				sql = "update faculty_personal_details set email_id = \""+ faculty.getEmailId()
						+"\" where faculty_id = \""+faculty.getFacultyId()+ "\";";
				break;
			
			case 4:
				sql = "update faculty_personal_details set contact = \""+ faculty.getContact()
						+"\" where faculty_id = \""+faculty.getFacultyId()+ "\";";
				break;
				
			case 5:
				sql = "update faculty_personal_details set hire_date = \""+ faculty.getHire_date()
						+"\" where faculty_id = \""+faculty.getFacultyId()+ "\";";
				break;
				
			case 6:
				sql = "update faculty_personal_details set date_of_birth = \""+ faculty.getDateOfBirth()
						+"\" where faculty_id = \""+faculty.getFacultyId()+ "\";";
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
	
	
	public boolean checkDept(StudentCurrAcademicModel student, String facultyId)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url, usr, pwd);
		boolean updationStatus = true;
		
		String studentDeptId = null,facultyDeptId=null;
			try {
			
				System.out.println("Checking if you are authorized to update the academic profile of " +
						"student: " + student.getStudentId());
				
				String sql1 = "select department_id from faculty_prof_details where faculty_id = ?";
				String sql2 = "select department_id from student_curr_academic_details where student_id = ?";
				
				ptmt = conn.prepareStatement(sql1);
				ptmt.setString(1, facultyId);
				rst = ptmt.executeQuery();
				
				while(rst.next()){
					facultyDeptId = rst.getString(1);
				}
				
				ptmt = conn.prepareStatement(sql2);
				ptmt.setString(1, student.getStudentId());
				rst = ptmt.executeQuery();
				
				while(rst.next()){
					studentDeptId = rst.getString(1);
				}
				
			} catch(Exception e){
				System.out.println(e.getMessage());
			}	
			
			try {
				
				ptmt.close();
			    conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}  catch(Exception e){
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
			
			System.out.println("student dept id: " + studentDeptId);
			System.out.println("faculty dept id: " + facultyDeptId);
			
			if(!studentDeptId.equals(facultyDeptId)){
					System.out.println("Sorry!..you cannot update the academic details of students of " +
						"other departments");
					updationStatus = false;
			}
			
			return updationStatus;		
	}
	
	
	public boolean updateStudentAcademicProfile(StudentCurrAcademicModel student, List<Integer> choiceList)
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
				sql = "update student_curr_academic_details set sem_1_marks = " + student.getSem1Marks() + " where student_id = \""+
						student.getStudentId() + "\" ;";
				break;
				
			case 2:
				sql = "update student_curr_academic_details set sem_2_marks = " + student.getSem2Marks() + " where student_id = \""+
						student.getStudentId() + "\" ;";
				break;

			case 3:
				sql = "update student_curr_academic_details set sem_3_marks = " + student.getSem3Marks() + " where student_id = \""+
						student.getStudentId() + "\" ;";
				break;
			
			case 4:
				sql = "update student_curr_academic_details set sem_4_marks = " + student.getSem4Marks() + " where student_id = \""+
						student.getStudentId() + "\" ;";
				break;
				
			case 5:
				sql = "update student_curr_academic_details set current_sem = " + student.getCurrentSem() + " where student_id = \""+
						student.getStudentId() + "\" ;";
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
}

