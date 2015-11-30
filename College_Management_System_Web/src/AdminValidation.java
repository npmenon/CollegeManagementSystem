

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.management.college.common_module.login.LoginModel;
import com.system.management.college.common_module.login.LoginProcess;

public class AdminValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int admin_privilegeLevel = 3;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("login")!=null){
			LoginModel login = new LoginModel();
			login.setUserId(request.getParameter("username"));
			login.setPwd(request.getParameter("password"));
			login.setPrivilege_level(admin_privilegeLevel);
			
			// pass the object of LoginModel to LoginProcess and validate the user
			LoginProcess process = new LoginProcess();
			process.validateUser(login);
			
			//check  if the user exists
			if(login.isStatus()){
				session.setAttribute("user",login);
				response.sendRedirect("1_admin_module/adminPortal.jsp");
			}//redirect to main page
			else{
				request.setAttribute("message","* Invalid login credentials!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("1_admin_module/adminLogin.jsp");
				dispatcher.forward(request,response);
			}//same page with error
		}
		
	}

}
