package neu.edu.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;

import neu.edu.dao.PostDao;

/**
 * Servlet implementation class AdminDeletePost
 */
@WebServlet(urlPatterns = { "/AdminDeletePost", "/adminDeletePost" })
public class AdminDeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeletePost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("_id");
		

		MongoClient mongoClient = (MongoClient) request.getServletContext().getAttribute("mongodbClient");
	    PostDao postDao = new PostDao(mongoClient);
	    
	    postDao.delete(id);
	    
		
		response.sendRedirect("controller");
		System.out.println("delete");
	}

}
