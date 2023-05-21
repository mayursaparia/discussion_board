<%@page import="java.util.ArrayList"%>
<%@page import="neu.edu.data.UserRegistration"%>
<%@page import="neu.edu.data.UserSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css">
<style type="text/css">

/* Add your table styling below */
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
<script type="text/javascript">

function deleteUser() {
	alert("User Deleted Successfully!!");
}

</script>
</head>
<body>

	<ul>
		<li class="logo"><img src="icons8-logo-90.png" alt="Logo"></li>
		<li class="center">Discussion Forum</li>

		<li><a href="adminDashboard">Admin Dashboard</a></li>

		<li><a href="logout">Logout</a></li>
	</ul>



	<!-- Add your table of data below -->
	<div style="padding: 50px">
		<h1>Users Table</h1>
		<table>
			<tr>
				<th>Username</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Role</th>
				<th>Action</th>

			</tr>
			<%
			ArrayList<UserRegistration> userRegistrations = (ArrayList<UserRegistration>) request.getAttribute("userRegistrations");
			for (UserRegistration userRegistration : userRegistrations) {
			%>
			<!-- Add more rows of data as needed -->
			<tr>
				<td><%=userRegistration.getUsername()%></td>
				<td><%=userRegistration.getFirstname()%></td>
				<td><%=userRegistration.getLastname()%></td>
				<td><%=userRegistration.getEmail()%></td>
				<td><%=userRegistration.getRole()%></td>
				
				<form action="viewUsers" method="post" style="display:inline;">
			    <input type="hidden" id="username" name="username" value="<%=userRegistration.getUsername()%>">
			    <% if(userRegistration.getRole().toString().equals("user") || userRegistration.getRole().toString().equals("USER"))
			    {
			    	%>
			  
			    <td><button class="delete-btn" onclick="deleteUser()">Delete</button></td>
			    
			    <% 
			    }
			    %>
			    </form>


			</tr>

			<%
			}
			%>
		</table>
	</div>
	
	<script type="text/javascript">
	 function viewRow(button) {
	        var row = button.parentNode.parentNode;
	        var cells = row.getElementsByTagName("td");
	       	console.log(cells[0].innerHTML,cells[1].innerHTML,cells[2].innerHTML);
	 }
	
	</script>

</body>
</html>