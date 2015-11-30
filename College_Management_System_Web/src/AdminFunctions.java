

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.management.college.admin_module.AdminProcess;
import com.system.management.college.common_module.ConstantClass;
import com.system.management.college.common_module.login.LoginModel;
import com.system.management.college.common_module.login.LoginProcess;
import com.system.management.college.department_module.DepartmentModel;
import com.system.management.college.department_module.DepartmentProcess;
import com.system.management.college.faculty_module.FacultyPersonalModel;
import com.system.management.college.faculty_module.FacultyProfModel;
import com.system.management.college.student_module.StudentPersonalModel;
import com.system.management.college.student_module.StudentPrevAcademicModel;

public class AdminFunctions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int student_privilege = 1;
    private static final int faculty_privilege = 2;
	
    public AdminFunctions() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getParameter("adminFunc")!=null){
			
			HttpSession session = request.getSession();
			
			String requestType = request.getParameter("requestType").toString();
			AdminProcess adminProcess = new AdminProcess();
			
			/*
			 * IF THE REQUEST IS TO ADD A NEW USER TO THE DATABASE
			 * 
			 */
			if(requestType.equals("adduser")){
				String userType = request.getParameter("userType");
				String userId = request.getParameter("userId");
				String department = request.getParameter("dept");
				
				DepartmentModel deptModel = new DepartmentModel();
				deptModel.setDepartmentName(department);
				
				DepartmentProcess deptProcess = new DepartmentProcess();
				String deptId = deptProcess.getDeptId(deptModel).getDepartmentId();
				deptModel.setDepartmentId(deptId);
				
				
				if(userType.equals("student")){
					if(adminProcess.addUserDetails(userId, student_privilege, deptModel))
						ConstantClass.message = "Successfully added student having id " + userId +" to the database";
					else
						ConstantClass.message = "Unsuccessful in adding student to the database " + 
					ConstantClass.errorMessage;
				}
				else if(userType.equals("faculty")){
					if(adminProcess.addUserDetails(userId, faculty_privilege, deptModel))
						ConstantClass.message = "Successfully added faculty having id " + userId +" to the database";
					else
						ConstantClass.message = "Unsuccessful in adding faculty to the database " +
					ConstantClass.errorMessage;
				}
				session.setAttribute("message", ConstantClass.message);
				response.sendRedirect("1_admin_module/adminPortal.jsp");
			}
			/*
			 * 
			 * 	IF THE REQUEST TO ADD A NEW DEPARTMENT
			 * 
			 */
			if(requestType.equals("adddept")){
				String deptId = request.getParameter("deptId");
				String deptName = request.getParameter("deptName");
				
				DepartmentModel deptModel = new DepartmentModel();
				deptModel.setDepartmentId(deptId);
				deptModel.setDepartmentName(deptName);
				
				if(adminProcess.addDepartmentDet(deptModel))
					ConstantClass.message = "Successfully added department: " + deptName + " to the " +
							"database";
				else 
					ConstantClass.message = "Unsuccessful in adding department: " + deptName + 
					" to the database " + ConstantClass.errorMessage;
				session.setAttribute("message", ConstantClass.message);
				response.sendRedirect("1_admin_module/adminPortal.jsp");
			}
			/*
			 * 
			 * IF THE REQUEST IS TO DELETE A USER FROM THE DATABASE
			 * 
			 */
			if(requestType.equals("deleteUser")){
				String userType = request.getParameter("userType");
				String userId = request.getParameter("userId");
				
				if(userType.equals("student")){
					
					StudentPersonalModel studObj = new StudentPersonalModel();
					studObj.setStudentId(userId);
					
					if(adminProcess.deleteStudent(studObj))
						ConstantClass.message = "Successfully deleted student having id: " + 
						studObj.getStudentId() + " from database";
					else
						ConstantClass.message = "Unsuccessful in deleting student from database" +
								 ConstantClass.errorMessage;
				}
				else if(userType.equals("faculty")){
					
					FacultyPersonalModel facultyObj = new FacultyPersonalModel();
					facultyObj.setFacultyId(userId);
					
					if(adminProcess.deleteFaculty(facultyObj))
						ConstantClass.message = "Successfully deleted faculty having id: " + 
						facultyObj.getFacultyId() + " from database";
					else
						ConstantClass.message = "Unsuccessful in deleting faculty from database" +
								 ConstantClass.errorMessage;					
				}
				
				session.setAttribute("message", ConstantClass.message);
				response.sendRedirect("1_admin_module/adminPortal.jsp");
			}
			/*
			 * 
			 * 	IF THE REQUEST IS TO UPDATE FACULTY PROFESSIONAL DETAILS
			 * 
			 */
			if(requestType.equals("updfacprofdet")){
				
				String facultyId = request.getParameter("facultyId");
				
				//Checking if the faculty exists in the database before proceeding further
				LoginModel login = new LoginModel();
				LoginProcess loginProcess = new LoginProcess();
				login.setUserId(facultyId);
				login.setPrivilege_level(faculty_privilege);
				
				//If the faculty exists
				if(loginProcess.checkUserExistence(login)){
				
					FacultyProfModel facultyObj = new FacultyProfModel();
					facultyObj.setFacultyId(facultyId);
					
					Enumeration en=request.getParameterNames();
					List<Integer> choiceList = new ArrayList<Integer>();
					
					while(en.hasMoreElements()){
						
			            Object objOri=en.nextElement();            
			            String param=(String)objOri;            
			            String value = request.getParameter(param);
			        
			            
			            if(value!=null && !("".equals(value)))
			            {	
			            	//System.out.println("parameter: " + param + "value: " + value);
			            	if(param.equals("gradInstit")){
			            		choiceList.add(1);
			            		facultyObj.setGradInstit(value);
			            	}			            	
			            	else if(param.equals("gradMarks")){
			            		choiceList.add(2);
			            		try {
									facultyObj.setGradMarks(Float.parseFloat(value));
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
								}
			            	}		            		
			            	else if(param.equals("postGradInstit")){
			            		choiceList.add(3);
			            		facultyObj.setPostGradInstit(value);
			            	}
			            	else if(param.equals("postGradMarks")){
			            		choiceList.add(4);
			            		try {
									facultyObj.setPostGradMarks(Float.parseFloat(value));
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
								}
			            	}
			            	else if(param.equals("currentDesign")){
			            		choiceList.add(5);
			            		facultyObj.setCurrentDesign(value);
			            	}	            		
			            	
			            }			            
			            
					}	
					
					if(adminProcess.updateFacultyProfDet(facultyObj, choiceList))
						ConstantClass.message = "Successfully Updated faculty details of faculty id: "
							+ facultyObj.getFacultyId();
					else
						ConstantClass.message = "Unsuccessful in updating: " + 
						ConstantClass.errorMessage;					
				}
				else
					ConstantClass.message = "Faculty with id: " + facultyId + " does not exist in " +
							"the database";
				
				session.setAttribute("message", ConstantClass.message);
				response.sendRedirect("1_admin_module/adminPortal.jsp");			
			}
			/*
			 * 
			 * IF REQUEST IS TO UPDATE STUDENT PREVIOUS ACADEMIC DETAILS
			 * 			 * 
			 */
			if(requestType.equals("updstupreacadet")){
				
				System.out.println("Entered servlet to update student previous academic details");
				String studentId = request.getParameter("studentId");
				
				//Checking if the student exists in the database before proceeding further
				LoginModel login = new LoginModel();
				LoginProcess loginProcess = new LoginProcess();
				login.setUserId(studentId);
				login.setPrivilege_level(student_privilege);
				
				//If the student exists
				if(loginProcess.checkUserExistence(login)){
					
					StudentPrevAcademicModel studentObj = new StudentPrevAcademicModel();
					studentObj.setStudentId(studentId);
					
					Enumeration en=request.getParameterNames();
					List<Integer> choiceList = new ArrayList<Integer>();
					
					while(en.hasMoreElements()){
						
			            Object objOri=en.nextElement();            
			            String param=(String)objOri;            
			            String value = request.getParameter(param);
			        
			            
			            if(value!=null && !("".equals(value)))
			            {	
			            	//System.out.println("parameter: " + param + "value: " + value);
			            	if(param.equals("tenInstit")){
			            		choiceList.add(1);
			            		studentObj.setTenInstit(value);
			            	}			            	
			            	else if(param.equals("tenMarks")){
			            		choiceList.add(2);
			            		try {
			            			studentObj.setTenMarks(Float.parseFloat(value));
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
								}
			            	}		            		
			            	else if(param.equals("twelveInstit")){
			            		choiceList.add(3);
			            		studentObj.setTwelveInstit(value);
			            	}
			            	else if(param.equals("twelveMarks")){
			            		choiceList.add(4);
			            		try {
									studentObj.setTwelveMarks(Float.parseFloat(value));
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
								}
			            	}
			            	else if(param.equals("prevProjects")){
			            		choiceList.add(5);
			            		studentObj.setPrevProjects(value);
			            	}	            		
			            	
			            }			            
			            
					}	
					
					if(adminProcess.updateStudentPrevAcademicDet(studentObj, choiceList))
						ConstantClass.message = "Successfully Updated student details of student id: "
							+ studentObj.getStudentId();
					else
						ConstantClass.message = "Unsuccessful in updating: " + 
						ConstantClass.errorMessage;	
				}
				else
					ConstantClass.message = "Student with id: " + studentId + " does not exist in " +
							"the database";
				
				session.setAttribute("message", ConstantClass.message);
				response.sendRedirect("1_admin_module/adminPortal.jsp");
			}
		}
	}

}
