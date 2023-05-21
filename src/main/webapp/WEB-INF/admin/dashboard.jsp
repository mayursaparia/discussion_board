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

<script type="text/javascript" defer>
function loaded() {
		//get all reply buttons
		const replyBtns = document.querySelectorAll('.reply-btn');
		
		// loop through reply buttons and add click event listener
		replyBtns.forEach(replyBtn => {
		  replyBtn.addEventListener('click', function() {
		    // get parent post element
		    const post = replyBtn.parentElement;
		
		    // get reply form element
		    const replyForm = post.querySelector('.reply-div');
		
		    // toggle display of reply form element
		    if (replyForm.style.display === 'none') {
		      replyForm.style.display = 'block';
		    } else {
		      replyForm.style.display = 'none';
		    }
		  });
		});
		
		const modifyBtns = document.querySelectorAll('.modify-btn');
		
		// loop through reply buttons and add click event listener
		modifyBtns.forEach(modifyBtn => {
			modifyBtn.addEventListener('click', function() {
		    // get parent post element
		    const post = modifyBtn.parentElement;
		
		    // get reply form element
		    const modifyForm = post.querySelector('.modify-div');
		
		    // toggle display of reply form element
		    if (modifyForm.style.display === 'none') {
		    	modifyForm.style.display = 'block';
		    } else {
		    	modifyForm.style.display = 'none';
		    }
		  });
		});
}	
		function showCreate() {
			   document.getElementById('create-div').style.display = "block";
			}
			
		function cancelPost() {
			   document.getElementById('create-div').style.display = "none";
			}
		
		
		function submitPost() {
				alert("Posted Successfully!!");
			}
		
		function submitModifiedPost() {
			alert("Post Modified Successfully!!");
		}
		
		function deletePost() {
			alert("Post Deleted Successfully!!");
		}
		
		function submitReply() {
			alert("Replyed on Post Successfully!!");
		}
</script>
</head>
<body onload="loaded();">

	<ul>
		<li class="logo"><img src="icons8-logo-90.png" alt="Logo"></li>
     	 <li class="center">Discussion Forum</li>
		
		<li><a href="logout">Logout</a></li>
		<li><a href="controller?action=viewUsers">View Users</a></li>
		
	</ul>

	<!-- Add your content below -->
	<div style="padding:10px">
	<%
		UserSession userSession = (UserSession)session.getAttribute("userSession");	
	%>
      
      <h1>Welcome <%=userSession.getFirstname()%>, </h1>
      
    </div>
    
    <button class="create-btn" onclick="showCreate()">Create Post</button>
	<div id="create-div" class="forum-container"> 
	 <form action="AdminDashboard" method="post">
		<div class="forum-post">
			  <label for="title">Title</label>
			  <input type="text" id="title" name="title">
			  
			  <label for="description">Description</label>
			  <textarea id="description" name="description"></textarea>
			  
			   <input type="hidden" id="username" name="username" value="<%=userSession.getUsername()%>">
			  
			  <div style="text-align:right;">
				<input type="submit" value="Submit" onclick="submitPost()" class="submit-btn"> 
				<input type="button" value="Cancel" class="cancel-btn" onclick="cancelPost()">
			  </div>
			  
		</div>
		</form>
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
			    <button class="reply-btn">Reply</button>
			 
			    <form action="AdminDeletePost" method="post" style="display:inline;">
			    <input type="hidden" id="_id" name="_id" value="<%=post.getId()%>">
			    
			    <button class="delete-btn" onclick="deletePost()">Delete</button>
			    </form>
			    
			    <button class="modify-btn" style="display:inline; margin-left: 0px;">Modify</button>
			    
			<div class="modify-div"> 
			    
			    <form action="AdminUpdatePost" method="post" style="display:inline;">
			    
			     	<label for="title">Title</label>
					<input type="text" id="title" name="title">
					  
					<label for="description">Description</label>
					<textarea id="description" name="description"></textarea>
					
				    <input type="hidden" id="_id" name="_id" value="<%=post.getId()%>">
				    <input type="hidden" id="username" name="username" value="<%=post.getUsername()%>">
				    <input type="hidden" id="date" name="date" value="<%=post.getDate()%>">
				    <input type="hidden" id="replyid" name="replyid" value="<%=post.getReplyid()%>">
				    
				    <div style="text-align:right;">
						<input type="submit" value="Submit" onclick="submitModifiedPost()" class="submit-btn"> 
					  </div>
			    
			    </form>
			  
			 </div>
		   
		  <div class="reply-div"> 
			 <form action="AdminDashboard" method="post" >
				<div class="forum-reply">
					  <label for="title">Title</label>
					  <input type="text" id="title" name="title">
					  
					  <label for="description">Description</label>
					  <textarea id="description" name="description"></textarea>
					  
					  <input type="hidden" id="replyid" name="replyid" value="<%=post.getId()%>">
					  
					  <input type="hidden" id="username" name="username" value="<%=userSession.getUsername()%>">
					  
					  <div style="text-align:right;">
						<input type="submit" value="Submit" onclick="submitReply()" class="submit-btn"> 
					  </div>
					  
				</div>
				</form>
			</div>
			
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
						<form action="AdminDeletePost" method="post" style="display:inline;">
						    <input type="hidden" id="_id" name="_id" value="<%=reply.getId()%>">
						
						    <button class="delete-btn" onclick="deletePost()">Delete</button>
						    
						 </form>
						 
						 <button class="modify-btn" style="display:inline; margin-left: 0px;">Modify</button>
			    
						<div class="modify-div"> 
						    
						    <form action="AdminUpdatePost" method="post" style="display:inline;">
						    
						     	<label for="title">Title</label>
								<input type="text" id="title" name="title">
								  
								<label for="description">Description</label>
								<textarea id="description" name="description"></textarea>
								
							    <input type="hidden" id="_id" name="_id" value="<%=reply.getId()%>">
							    <input type="hidden" id="username" name="username" value="<%=reply.getUsername()%>">
							    <input type="hidden" id="date" name="date" value="<%=reply.getDate()%>">
							    <input type="hidden" id="replyid" name="replyid" value="<%=reply.getReplyid()%>">
							    
							    <div style="text-align:right;">
									<input type="submit" value="Submit" onclick="submitModifiedPost()" class="submit-btn"> 
								  </div>
						    
						    </form>
						  
						 </div>
						 
						 </div>
						 
						    
					    
						
						<% 
					}
				}
			
			
		}
			
		%> 
		  
		  
	  	 
		</div>
		
    

</body>
</html>