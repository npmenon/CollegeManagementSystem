



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.system.management.college.common_module.login.LoginModel;
import com.system.management.college.common_module.login.LoginProcess;


public class StudentFacultyLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int student_privilegeLevel = 1; 
	private static final int faculty_privilegeLevel = 2; 
	
    public StudentFacultyLogin() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public boolean signIn(LoginModel login,String userId,String pwd,int privilegeLevel){
		
		boolean flag = false;
		login.setUserId(userId);
		login.setPwd(pwd);
		login.setPrivilege_level(privilegeLevel);
		// pass the object of LoginModel to LoginProcess and validate the user
		LoginProcess process = new LoginProcess();
		process.validateUser(login);
		
		if(login.isStatus())
			flag = true;
		
		return flag;
	}
	
	public boolean signUp(LoginModel login,String userId,String pwd,int privilegeLevel){
		
		boolean flag = false;
		login.setUserId(userId);
		login.setPwd(pwd);
		login.setPrivilege_level(privilegeLevel);
		// pass the object of LoginModel to LoginProcess and validate the user
		LoginProcess process = new LoginProcess();
		process.insertUser(login);
		
		if(login.isStatus())
			flag = true;
		
		return flag;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String loginType = request.getParameter("loginType");
		String privilege_level = request.getParameter("userType");
		
		//Getting LoginModel object
		LoginModel login = new LoginModel();	
		
		if(loginType.equals("signIn")){	//Sign In code
			
			String userId = request.getParameter("username");
			String password = request.getParameter("password");

			//check  if the user exists
			if(privilege_level.equals("student")){
				
				if(signIn(login,userId,password,student_privilegeLevel)){
					
					session.setAttribute("user",login);	
					response.sendRedirect("3_student_module/studentPortal.jsp");
				}
				else{
					request.setAttribute("messageSignIn", "invalid login credentials!!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("3_student_module/studentLogin.jsp");
					dispatcher.forward(request, response);
				}
			}
			else if(privilege_level.equals("faculty")){
			
				if(signIn(login,userId,password,faculty_privilegeLevel)){
					
					session.setAttribute("user",login);	
					response.sendRedirect("2_faculty_module/facultyPortal.jsp");
				}
				else{
					request.setAttribute("messageSignIn", "invalid login credentials!!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("2_faculty_module/FacultyLogin.jsp");
					dispatcher.forward(request, response);
				}
			}
		}	
		else if(loginType.equals("signUp")){	//Sign Up code
			
			boolean signUpStatus = false;
			String username = request.getParameter("username");
			String oldPwd = request.getParameter("oldpassword");
			String newPwd = request.getParameter("newpassword");
			
			if(privilege_level.equals("student")){
				
				signUpStatus = signIn(login,username, oldPwd, student_privilegeLevel);
				if(!signUpStatus){
					request.setAttribute("messageSignUp", "invalid login credentials!");
					request.getRequestDispatcher("3_student_module/studentLogin.jsp").forward(request, response);
				}
				else{
					if(signUp(login, username, newPwd, student_privilegeLevel)){
						//redirecting to student main page
						session.setAttribute("user",login);	
						response.sendRedirect("3_student_module/studentPortal.jsp");
					}
				}
			}
			else if(privilege_level.equals("faculty")){
				
				signUpStatus = signIn(login,username, oldPwd, faculty_privilegeLevel);
				if(!signUpStatus){
					request.setAttribute("messageSignUp", "invalid login credentials!");
					request.getRequestDispatcher("2_faculty_module/FacultyLogin.jsp").forward(request, response);
				}
				else{
					if(signUp(login, username, newPwd, faculty_privilegeLevel)){
						//redirecting to Faculty main page
						session.setAttribute("user",login);	
						response.sendRedirect("2_faculty_module/facultyPortal.jsp");
					}
				}
			}
			
		}
	}

}
