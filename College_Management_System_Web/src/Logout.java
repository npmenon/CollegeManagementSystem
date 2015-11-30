

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.management.college.common_module.login.LoginModel;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		LoginModel loginModel = (LoginModel)session.getAttribute("user");
		
		if(loginModel != null){
			session.invalidate();
			System.out.println( loginModel.getUserId() +" successfully logged out");
		}
		
		response.sendRedirect("MainView.jsp");
	}

}
