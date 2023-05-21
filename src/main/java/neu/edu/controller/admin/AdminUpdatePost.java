package neu.edu.controller.admin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
 * Servlet implementation class AdminUpdatePost
 */
@WebServlet(urlPatterns = { "/AdminUpdatePost", "/adminUpdatePost"})
public class AdminUpdatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdatePost() {
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
		String username = request.getParameter("username");
		String title = request.getParameter("title");
		String content = request.getParameter("description");
		String date = request.getParameter("date");
		String replyid = request.getParameter("replyid");
		String id = request.getParameter("_id");
		ObjectId replyidObj = null;
		
		System.out.println(replyid);
		
		if(replyid.equals("null"))
		{
			replyidObj = null;
		}
		else 
		{
			replyidObj = new ObjectId(replyid);
		}

		MongoClient mongoClient = (MongoClient) request.getServletContext().getAttribute("mongodbClient");
	    PostDao postDao = new PostDao(mongoClient);
	    
	    Post post = new Post();
	    post.setId(id);
	    post.setUsername(username);
	    post.setTitle(title);
	    post.setContent(content);
	    post.setDate(date);
	    post.setReplyid(replyidObj);
	    
	    postDao.update(post);
	    
	    response.sendRedirect("controller");
	}

}
