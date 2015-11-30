
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.management.college.common_module.ConstantClass;
import com.system.management.college.common_module.login.LoginModel;
import com.system.management.college.faculty_module.FacultyPersonalModel;
import com.system.management.college.faculty_module.FacultyProcess;
import com.system.management.college.student_module.StudentCurrAcademicModel;

public class FacultyFunctions extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FacultyFunctions() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String formatDate(String givenDate)
    {
		StringTokenizer tokens = new StringTokenizer(givenDate,"/");
		
		int month = Integer.parseInt(tokens.nextToken());
		int date = Integer.parseInt(tokens.nextToken());
		int year = Integer.parseInt(tokens.nextToken());
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = formatter.format(cal.getTime()).toString();
		return date1;
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("facultyFunc")!=null){
			
			HttpSession session = request.getSession();
			LoginModel loginModel = (LoginModel)session.getAttribute("user");
			String facultyId = loginModel.getUserId();
			
			String requestType = request.getParameter("requestType").toString();
			FacultyProcess facultyProcess = new FacultyProcess();
			
			/*
			 * IF THE REQUEST IS View PERSONAL AND PROF DETAILS
			 * 
			 */
			if(requestType.equals("modifyperdet")){
			
				
				FacultyPersonalModel facultyObj = new FacultyPersonalModel();
				facultyObj.setFacultyId(facultyId);
				
				Enumeration en=request.getParameterNames();
				List<Integer> choiceList = new ArrayList<Integer>();
				
				String date;
				while(en.hasMoreElements()){
					
		            Object objOri=en.nextElement();            
		            String param=(String)objOri;            
		            String value = request.getParameter(param);
		        
		            
		            if(value!=null && !("".equals(value)))
		            {	
		            	//System.out.println("parameter: " + param + "value: " + value);
		            	if(param.equals("firstName")){
		            		choiceList.add(1);
		            		facultyObj.setFirstName(value);
		            	}			            	
		            	else if(param.equals("lastName")){
		            		choiceList.add(2);
		            		facultyObj.setLastName(value);
		            	}		            		
		            	else if(param.equals("emailId")){
		            		choiceList.add(3);
		            		facultyObj.setEmailId(value);
		            	}
		            	else if(param.equals("contact")){
		            		choiceList.add(4);
		            		facultyObj.setContact(value);
		            	}
		            	else if(param.equals("hire_date")){
		            		choiceList.add(5);
		            		date = formatDate(value);
		            		facultyObj.setHire_date(date);
		            	}
		            	else if(param.equals("dateOfBirth")){
		            		choiceList.add(6);
		            		date = formatDate(value);
		            		facultyObj.setDateOfBirth(date);
		            	}	
		            	
		            }			            
		            
				}	
				
				if(facultyProcess.updatePersonalProfile(facultyObj, choiceList))
					ConstantClass.message = "Successfully Updated faculty details of faculty id: "
						+ facultyObj.getFacultyId();
				else
					ConstantClass.message = "Unsuccessful in updating: " + ConstantClass.errorMessage;					
				
		
				session.setAttribute("message", ConstantClass.message);
				response.sendRedirect("2_faculty_module/facultyPortal.jsp");
				
			}
			if(requestType.equals("updstucurracadet")){
				
				String studentId = request.getParameter("studentId");
				
				StudentCurrAcademicModel studentObj = new StudentCurrAcademicModel();
				
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
		            	if(param.equals("sem1Marks")){
		            		choiceList.add(1);
		            		try {
								studentObj.setSem1Marks(Float.parseFloat(value));
							} catch (NumberFormatException e) {
								ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
								break;
							}
		            	}			            	
		            	else if(param.equals("sem2Marks")){
		            		choiceList.add(2);
		            		try {
								studentObj.setSem2Marks(Float.parseFloat(value));
							} catch (NumberFormatException e) {								
								ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
								break;
							}
		            	}		            		
		            	else if(param.equals("sem3Marks")){
		            		choiceList.add(3);
		            		try {
								studentObj.setSem3Marks(Float.parseFloat(value));
							} catch (NumberFormatException e) {								
								ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
								break;
							}
		            	}
		            	else if(param.equals("sem4Marks")){
		            		choiceList.add(4);
		            		try {
								studentObj.setSem4Marks(Float.parseFloat(value));
							} catch (NumberFormatException e) {								
								ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
								break;
							}
		            	}
		            	else if(param.equals("currentSem")){
		            		choiceList.add(5);
		            		try {
								studentObj.setCurrentSem(Integer.parseInt(value));
							} catch (NumberFormatException e) {								
								ConstantClass.errorMessage = "invalid: " + e.getMessage() + " .";
								break;
							}
		            	}
		            	
		            }			            
		            
				}	
				
				if(facultyProcess.updateStudentAcademicProfile(studentObj, choiceList))
					ConstantClass.message = "Successfully Updated faculty details of faculty id: "
						+ studentObj.getStudentId();
				else
					ConstantClass.message = "Unsuccessful in updating: " + ConstantClass.errorMessage;					
				
		
				session.setAttribute("message", ConstantClass.message);
				response.sendRedirect("2_faculty_module/facultyPortal.jsp");
				
			}
			
		}
		
	}

}
