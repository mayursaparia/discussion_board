package neu.edu.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import neu.edu.dao.LoginDAO;
import neu.edu.dao.ProfileDAO;
import neu.edu.data.UserRegistration;
import neu.edu.data.UserSession;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/userProfile")
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		request.setAttribute("username", userSession.getUsername());
		request.setAttribute("firstname", userSession.getFirstname());
		request.setAttribute("lastname", userSession.getLastname());
		request.setAttribute("email", userSession.getEmail());
		
		request.getRequestDispatcher("/WEB-INF/user/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		boolean UpdateProfileStatus = false;
		boolean UpdateProfilePassStatus = false;
		String errorMsg = "Error: Something went wrong please try again later!!";
		
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastnamne = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(password == null)
		{
			ProfileDAO profileDAO = new ProfileDAO();
			userSession = profileDAO.UpdateUser(username, firstname, lastnamne, email, userSession.getUsername());
			
			if(userSession != null) {
				
				userSession.setCurrentPage("userDashboard");
				
				session.setAttribute("userSession", userSession);
				
				UpdateProfileStatus = true;
				
			}else {
				errorMsg = "Error: Something Went Wrong!!";
			}
			
			if(UpdateProfileStatus) {
				
				response.sendRedirect("controller");

			}else {
				
				request.setAttribute("errorMsg",errorMsg);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/user/profile.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else 
		{
			ProfileDAO profileDAO = new ProfileDAO();
			UpdateProfilePassStatus = profileDAO.UpdateUserPassword(password, userSession.getUsername());
			System.out.println(UpdateProfilePassStatus);
			System.out.println(username);
			if(UpdateProfilePassStatus) {
				
				request.getSession().invalidate();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
				requestDispatcher.forward(request, response);
				

			}
			else {
				
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/user/profile.jsp");
				requestDispatcher.forward(request, response);
			
			}
		
		
		}

	}
}