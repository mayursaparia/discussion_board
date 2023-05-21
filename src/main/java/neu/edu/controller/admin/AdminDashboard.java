package neu.edu.controller.admin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
 * Servlet implementation class Dashboard
 */
@WebServlet(urlPatterns = {"/adminDashboard","/AdminDashboard"})
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext application = request.getServletContext();
		MongoClient mongoClient = (MongoClient) application.getAttribute("mongodbClient");
	    PostDao postDao = new PostDao(mongoClient);
	    List<Post> posts = postDao.getList();
	    request.setAttribute("posts", posts);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/dashboard.jsp");
	    rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String title = request.getParameter("title");
		String content = request.getParameter("description");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    System.out.println(dtf.format(now)); 
		String date = dtf.format(now);
		String id = request.getParameter("replyid");
		ObjectId replyid = null;
		
		
		if(id != null)
		{
			replyid = new ObjectId(id);
		}
		else 
		{
			replyid = null;
		}
		

		MongoClient mongoClient = (MongoClient) request.getServletContext().getAttribute("mongodbClient");
	    PostDao postDao = new PostDao(mongoClient);
	    
	    Post post = new Post();
	    post.setUsername(username);
	    post.setTitle(title);
	    post.setContent(content);
	    post.setDate(date);
	    post.setReplyid(replyid);
	    
	    postDao.create(post);
	    
	    doGet(request, response);
	}

}