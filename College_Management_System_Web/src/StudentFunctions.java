

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
import com.system.management.college.student_module.StudentPersonalModel;
import com.system.management.college.student_module.StudentProcess;

public class StudentFunctions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public StudentFunctions() {
        super();
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
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getParameter("studentFunc")!=null){
			
			HttpSession session = request.getSession();
			
			String requestType = request.getParameter("requestType").toString();
			StudentProcess studentProcess = new StudentProcess();
			
			/*
			 * 
			 * 	IF THE REQUEST IS TO MODIFY PERSONAL DETAILS
			 * 
			 */
			if(requestType.equals("updatePersonalDetails")){
				
					LoginModel login =(LoginModel)session.getAttribute("user");	
					String studentId=login.getUserId();
					
					StudentPersonalModel studentObj=new StudentPersonalModel();
					studentObj.setStudentId(studentId);
					
					Enumeration en=request.getParameterNames();
					List<Integer> choiceList = new ArrayList<Integer>();
					
					String date;
					while(en.hasMoreElements()){
						
			            Object objOri=en.nextElement();            
			            String param=(String)objOri;            
			            String value = request.getParameter(param);
			        
			            
			            if(value!=null && !("".equals(value)))
			            {	
			            	
			            	if(param.equals("firstName")){
			            		choiceList.add(1);
			            		studentObj.setFirstName(value);
			            	}			            	
			            	else if(param.equals("lastName")){
			            		choiceList.add(2);
		            			studentObj.setLastName(value);		
			            	}		            		
			            	else if(param.equals("emailId")){
			            		choiceList.add(3);
			            		studentObj.setEmailId(value);
			            	}
			            	else if(param.equals("contact")){
			            		choiceList.add(4);
			            	    studentObj.setContact(value);		
			            	}
			            	else if(param.equals("joinDate")){
			            		choiceList.add(5);
			            		date = formatDate(value);
			            		studentObj.setJoinDate(date);
			            	}	  
			            	else if(param.equals("dateOfBirth")){
			            		choiceList.add(6);
			            		date = formatDate(value);
			            		studentObj.setDateOfBirth(date);
			            	}	  
			            	
			            }			            
			            
					}	
					
					if(studentProcess.modifyPersonalDetails(studentObj, choiceList))
						ConstantClass.message = "Successfully Updated Student Personal details of " +
								"student id: "+ studentObj.getStudentId();
					else
						ConstantClass.message = "Unsuccessful in updating: " + 
						ConstantClass.errorMessage;					
				
				
					
				
				session.setAttribute("message", ConstantClass.message);
				response.sendRedirect("3_student_module/studentPortal.jsp");			
			
		}
	}
}
}
	
