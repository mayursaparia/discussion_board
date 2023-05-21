package neu.edu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import neu.edu.data.UserRegistration;
import neu.edu.data.UserSession;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/controller")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
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

		String action = request.getParameter("action");

		RequestDispatcher rd = null;

		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
		UserRegistration.Role role = userSession.getRole();
		
		String currentPage = userSession.getCurrentPage();
		String newAction = null;

		String dispatcher = "/login";
		
		System.out.println();
		
		switch (role) {
		case ADMIN:
			if ("adminDashboard".equals(currentPage)) {

				if (action == null) {
					dispatcher = "adminDashboard";
				}else if ("viewUsers".equals(action)) {
					dispatcher = "viewUsers";
				}

			}

			rd = request.getRequestDispatcher(dispatcher);
			rd.forward(request, response);

			break;

		case USER:
			if ("userDashboard".equals(currentPage)) {

				if (action == null) {
					dispatcher = "userDashboard";

				} else if ("profile".equals(action)) {
					userSession.setCurrentPage(currentPage);
					dispatcher = "userProfile";
				} 

			}else if("userProfile".equals(currentPage)){
				
				if (action == null) {
					userSession.setCurrentPage("userDashboard");


				}else if ("profile".equals(action) || "editProfile".equals(action) || "editProfilePassword".equals(action)) {
					dispatcher = "userProfile";
				}
			}
			
			
			rd = request.getRequestDispatcher(dispatcher);
			rd.forward(request, response);
			break;

		default:
			session.invalidate();
			response.sendRedirect("index.html");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}