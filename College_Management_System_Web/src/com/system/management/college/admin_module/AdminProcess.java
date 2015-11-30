package com.system.management.college.admin_module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.system.management.college.common_module.Connection_config_variables;
import com.system.management.college.common_module.ConstantClass;
import com.system.management.college.common_module.DAO;
import com.system.management.college.department_module.DepartmentModel;
import com.system.management.college.faculty_module.FacultyPersonalModel;
import com.system.management.college.faculty_module.FacultyProfModel;
import com.system.management.college.student_module.StudentPersonalModel;
import com.system.management.college.student_module.StudentPrevAcademicModel;

public class AdminProcess implements Connection_config_variables{
	
	private Connection conn = null;
	private DAO dao = null;	
	private PreparedStatement ptmt = null;
	
	public boolean addUserDetails(String userId,final int privilege,DepartmentModel department)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url , usr, pwd);
		//adds student/faculty userId ,privilege and department id
		boolean insertionStatus = false;
		
		String generatedPass = generatePass(userId);
		String sql = "insert into login_details(user_id,pwd,privilege_level) values (\""+userId+"\"," +
				"\""+generatedPass+"\",\""+privilege+"\");";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.execute();
			
			String sql2 = null,sql3 = null,sql4 = null;
		    if(privilege == 1)//student
		    {
		    	sql2 = "insert into student_curr_academic_details(student_id,department_id) values " +
		    			 "(\""+userId+"\",\""+department.getDepartmentId()+"\");";
		    	sql3 = "insert into student_prev_academic_details(student_id) values " +
		    			 "(\""+userId+"\");";
		    	sql4 = "insert into student_personal_details(student_id) values " +
		    			 "(\""+userId+"\");";	
				ptmt = conn.prepareStatement(sql2);
				ptmt.execute();
				ptmt = conn.prepareStatement(sql3);
				ptmt.execute();
				ptmt = conn.prepareStatement(sql4);
				ptmt.execute();
		    }
		    else if(privilege == 2)//faculty
		    {
		    	sql2 = "insert into faculty_prof_details(faculty_id,department_id) values " +
		    			 "(\""+userId+"\",\""+department.getDepartmentId()+"\");";
		    	sql3 = "insert into faculty_personal_details(faculty_id) values " +
		    			 "(\""+userId+"\");";		    	
				ptmt = conn.prepareStatement(sql2);
				ptmt.execute();
				ptmt = conn.prepareStatement(sql3);
				ptmt.execute();
		    }
			
			ptmt.close();
		    conn.close();
		    insertionStatus = true;
		    			
		} catch (SQLException e) {
			
			ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
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
		      }//end finally try
		   }//end try
		
		return insertionStatus;		
	}
	
	//Method that generates userId specific password
	private String generatePass(String userId) {

		StringBuffer string = new StringBuffer(userId);
		String output = "";
		/*
		 * a) take the ACII value of first alphabet and add the last digit to it
		   b) first digit of sum of squares all digits 
		   c) concatenate alternative digits % 100
		 */
		
		//a)
		
		int first = string.charAt(0) + Integer.parseInt(String.valueOf(string.reverse().charAt(0)));
		string.reverse();
		string.deleteCharAt(0);
		
		//b)
		int second=0,num;
		
		for(int i=0; i<string.length();i++){
			num = Integer.parseInt(String.valueOf(string.charAt(i)));
			second += Math.pow(num, 2);
		}
		second = Integer.toString(second).charAt(0);
		
		//c)
		
		int third = 0;
		String temp = "";
		for(int i=0;i<string.length();i+=2)
			temp += string.charAt(i);
		
		third = Integer.parseInt(temp)%100;
		
		output += String.valueOf(first) + String.valueOf(second) + String.valueOf(third);
		
		return output;
	}

	public boolean addDepartmentDet(DepartmentModel dept)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url , usr, pwd);
		boolean insertionStatus = false;
		
		String sql="insert into department_details values (\""+dept.getDepartmentId()+"\",\""+dept.getDepartmentName()+"\");";
		
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.execute();
			
			ptmt.close();
		    conn.close();
		    insertionStatus = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
		} catch(Exception e){
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
		      }//end finally try
		   }//end try
		
		//add department details
		return insertionStatus;
	}
	
	public boolean deleteStudent(StudentPersonalModel student)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url , usr, pwd);
		boolean  deletionStatus =false;
		int status1,status2,status3,status4 ;
		status1=status2=status3=status4=0;
		//delete student details from database
		
		String sql1 = "delete from student_curr_academic_details where student_id = \"" 
		+ student.getStudentId() + "\"";
		String sql2 = "delete from student_prev_academic_details where student_id = \"" 
		+ student.getStudentId() + "\"";
		String sql3 = "delete from student_personal_details where student_id = \"" 
		+ student.getStudentId() + "\"";
		String sql4 = "delete from login_details where user_id = \"" 
		+ student.getStudentId() + "\"";
		
		try {
			
			ptmt = conn.prepareStatement(sql1);
			status1 = ptmt.executeUpdate();
			ptmt = conn.prepareStatement(sql2);
			status2 = ptmt.executeUpdate();
			ptmt = conn.prepareStatement(sql3);
			status3 = ptmt.executeUpdate();
			ptmt = conn.prepareStatement(sql4);
			status4 = ptmt.executeUpdate();
			
			ptmt.close();
		    conn.close();
		    if(status1!=0 && status2!=0 && status3!=0 && status4!=0)
		    	deletionStatus = true;
		    
		    String updateProblem = null;
		    if(deletionStatus == false)
		    {
		    	if(status1==0)
		    		updateProblem += "trouble updating student_curr_details table; ";
		    	if(status2==0)
		    		updateProblem += "trouble updating student_prev_details table; ";
		    	if(status3==0)
		    		updateProblem += "trouble updating student_personal_details table; ";
		    	if(status4==0)
		    		updateProblem += "trouble updating login_details table; ";
		    	ConstantClass.errorMessage = updateProblem;
		    }
			
		} catch (SQLException e) {
			
			
		}  catch(Exception e){
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
		      }//end finally try
		   }//end try
		
		return  deletionStatus;
	}
	
	public boolean deleteFaculty(FacultyPersonalModel faculty)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url , usr, pwd);
		boolean  deletionStatus = false;
		int status1,status2,status3;
		status1=status2=status3=0;
		//delete student details from database
		
		String sql1 = "delete from faculty_prof_details where faculty_id = \"" 
		+ faculty.getFacultyId() + "\"";
		String sql2 = "delete from faculty_personal_details where faculty_id = \"" 
		+ faculty.getFacultyId() + "\"";
		String sql3 = "delete from login_details where user_id = \"" 
		+ faculty.getFacultyId() + "\"";
		
		try {
			
			ptmt = conn.prepareStatement(sql1);
			status1 = ptmt.executeUpdate();
			ptmt = conn.prepareStatement(sql2);
			status2 = ptmt.executeUpdate();
			ptmt = conn.prepareStatement(sql3);
			status3 = ptmt.executeUpdate();
			
			ptmt.close();
		    conn.close();
		    if(status1!=0 && status2!=0 && status3!=0)
		    	deletionStatus = true;
		    
		    String updateProblem = null;
		    if(deletionStatus == false)
		    {
		    	if(status1==0)
		    		updateProblem += "trouble updating faculty_prof_details table; ";
		    	if(status2==0)
		    		updateProblem += "trouble updating faculty_personal_details table; ";
		    	if(status3==0)
		    		updateProblem += "trouble updating login_details table; ";
		    	
		    	ConstantClass.errorMessage = updateProblem;
		    }
			
		} catch (SQLException e) {
			ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
			
		}  catch(Exception e){
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
		      }//end finally try
		   }//end try
		
		return deletionStatus;
	}
	
	public boolean updateFacultyProfDet(FacultyProfModel facultyProfObj, List<Integer> choiceList)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url , usr, pwd);
		
		boolean updationStatus = false;
		int update = 0;
		//delete student details from database
		
		String sql = null;
		for(Integer choice: choiceList){
			
			updationStatus = false;
			update = 0;

			
			switch (choice) {
			case 1:
				sql = "update faculty_prof_details set grad_instit = \""+facultyProfObj.getGradInstit()+ "\" where " +
						"faculty_id = \""+facultyProfObj.getFacultyId()+"\"";
				break;
				
			case 2:
				sql = "update faculty_prof_details set grad_marks = \""+facultyProfObj.getGradMarks()+
				"\" where faculty_id = \"" + facultyProfObj.getFacultyId() + "\"";
				break;

			case 3:
				sql = "update faculty_prof_details set post_grad_instit = \""+facultyProfObj.getPostGradInstit()
				+ "\" where faculty_id = \"" + facultyProfObj.getFacultyId() + "\"";
				break;
			
			case 4:
				sql = "update faculty_prof_details set post_grad_marks = \""+facultyProfObj.getPostGradMarks()
				+ "\" where faculty_id = \"" + facultyProfObj.getFacultyId() + "\"";
				break;
				
			case 5:
				sql = "update faculty_prof_details set current_design = \""+facultyProfObj.getCurrentDesign()
				+ "\" where faculty_id = \"" + facultyProfObj.getFacultyId() + "\"";
				break;

			default:
				break;
			}//switch
					
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
			try{
				ptmt.close();
			    conn.close();
			    
			}  catch(Exception e){
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
	
	public boolean updateStudentPrevAcademicDet(StudentPrevAcademicModel student,List<Integer> choiceList)
	{
		dao = DAO.getDAOInstance();
		conn = dao.getConnection(driver, url , usr, pwd);
		
		boolean updationStatus = false;
		int update = 0;
		//delete student details from database
		
		String sql = null;
		for(Integer choice: choiceList){
			
			updationStatus = false;
			update = 0;

		
			switch (choice) {
			case 1:
				sql = "update student_prev_academic_details set ten_instit = \""+student.getTenInstit()+ 
				"\" where student_id = \""+student.getStudentId()+"\"";
				break;
				
			case 2:
				sql = "update student_prev_academic_details set ten_marks = \""+student.getTenMarks()+
				"\" where student_id = \"" + student.getStudentId() + "\"";
				break;

			case 3:
				sql = "update student_prev_academic_details set twelve_instit = \""+student.getTwelveInstit()
				+ "\" where student_id = \"" + student.getStudentId() + "\"";
				break;
			
			case 4:
				sql = "update student_prev_academic_details set twelve_marks = \""+student.getTwelveMarks()
				+ "\" where student_id = \"" + student.getStudentId() + "\"";
				break;
				
			case 5:
				sql = "update student_prev_academic_details set prev_projects = \""+student.getPrevProjects()
				+ "\" where student_id = \"" + student.getStudentId() + "\"";
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
			    }//catch block 
			}//forEach loop
			try{
					ptmt.close();
					conn.close();
			   }
		    catch(Exception e){
		    	
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
		    	  ConstantClass.errorMessage = "invalid: " + se.getMessage() + " .";
		      }//end finally try
		   }//end try
		
		
		return updationStatus;		
	}
}
