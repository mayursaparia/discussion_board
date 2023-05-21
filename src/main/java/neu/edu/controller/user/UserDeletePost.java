package neu.edu.controller.user;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.mongodb.MongoClient;

import neu.edu.dao.PostDao;
import neu.edu.data.Post;

/**
 * Servlet implementation class UserDeletePost
 */
@WebServlet(urlPatterns = {"/UserDeletePost","/userDeletePost"})
public class UserDeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeletePost() {
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
