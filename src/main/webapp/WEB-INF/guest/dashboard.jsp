<%@page import="neu.edu.data.UserSession"%>
<%@page import="neu.edu.data.Post"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Discussion forum</title>
<link rel="stylesheet" href="css/styles.css" >
</head>
<script type="text/javascript">

</script>
<body>

	<ul>
		<li class="logo"><img src="icons8-logo-90.png" alt="Logo"></li>
      <li class="center">Discussion Forum</li>
      
      <li><a href="registration">Register</a></li>
      <li><a href="login">Login</a></li>
		
	</ul>

	<!-- Add your content below -->
	<div style="padding:10px">
      
      <h1>Welcome Guest User, </h1>
      
    </div>
    
	<div class="forum-container">
		<%
		ArrayList<Post> postreplies = (ArrayList<Post>) request.getAttribute("posts");
		ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("posts");
		for (Post post : posts) {
			ArrayList<Post> replies = new ArrayList<Post>();
			
			if(post.getReplyid() == null)
			{
		%>
		
		
		  <div class="forum-post">
		    <h2 class="post-title"><%=post.getTitle()%></h2>
		    <p class="post-content"><%=post.getContent()%></p>
		    <p class="post-details">Posted by <strong> <%=post.getUsername()%>  </strong> on <strong> <%=post.getDate()%> </strong></p>	
		</div>
		 	<%
			}
			
				for (Post postreply : postreplies) {
					if(postreply.getReplyid() != null){
						if((post.getId().contentEquals(postreply.getReplyid().toString())) )
						{
							replies.add(postreply);		
						}
						else
						{
	
							continue;
						}
					}
					else 
					{
						continue;
					}
				}
				
				if(replies.isEmpty())
				{
					continue;
				}
				else 
				{
					for (Post reply : replies)
					{
					
						%>
						<div class="forum-reply">
						    <h3 class="reply-title"><%=reply.getTitle()%></h3>
						    <p class="reply-content"><%=reply.getContent()%></p>
						    <p class="post-details">Posted by <strong> <%=reply.getUsername()%>  </strong> on <strong> <%=reply.getDate()%> </strong></p>
						 </div>
						 
						
						<% 
					}
				}
			
			
		}
			
		%> 
		  
		</div>
	
</body>
</html>