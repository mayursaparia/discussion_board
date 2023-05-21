package neu.edu.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import neu.edu.dao.LoginDAO;
import neu.edu.data.UserRegistration;
import neu.edu.data.UserSession;

/**
 * Servlet implementation class Login
 */
@WebServlet(
		urlPatterns =  {"/Login","/login"},
		initParams = {@WebInitParam(name = "admin",value = "pass")}
)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");

		if(userSession != null) {
			response.sendRedirect("controller");
			
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
			requestDispatcher.forward(request, response);


		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean loginStatus = false;
		String errorMsg = "Error: Invalid username or password";
		
		/*
		ServletContext application = request.getServletContext();
		
		ArrayList<UserRegistration> userRegistrations = (ArrayList<UserRegistration>) application.getAttribute("userRegistrations");
		

		
		if(userRegistrations!=null) {
			for(UserRegistration userRegistration:userRegistrations) {
				
				if(username.equals(userRegistration.getUsername()) && password.equals(userRegistration.getPassword())) {
					HttpSession session = request.getSession();
					UserSession userSession;
					
					if(UserRegistration.Role.ADMIN == userRegistration.getRole()) {
						userSession =  new UserSession(userRegistration.getUsername(), userRegistration.getEmail(), userRegistration.getRole(),"adminDashboard");
					}else {
						userSession =  new UserSession(userRegistration.getUsername(), userRegistration.getEmail(), userRegistration.getRole(),"userDashboard");
					}
					session.setAttribute("userSession", userSession);
					
					loginStatus = true;
					break;
				}
			}
		}else {
			errorMsg = "Error: Invalid username or password";
		}
		*/
		
		LoginDAO loginDAO = new LoginDAO();
		UserSession userSession = loginDAO.validateLogin(username, password);
		
		if(userSession != null) {
			HttpSession session = request.getSession();
			
			if(UserRegistration.Role.ADMIN == userSession.getRole()) {
				userSession.setCurrentPage("adminDashboard");
			}else {
				userSession.setCurrentPage("userDashboard");
			}
			session.setAttribute("userSession", userSession);
			
			loginStatus = true;
			
		}else {
			errorMsg = "Error: Invalid username or password";
		}
		
		if(loginStatus) {
			
			response.sendRedirect("controller");

		}else {
			
			request.setAttribute("errorMsg",errorMsg);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
			requestDispatcher.forward(request, response);
		}

		
	}

}