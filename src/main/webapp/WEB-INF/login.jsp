<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css" >
<style type="text/css">
 /* Add your registration form styling here */
    .login-form {
        background-color: #f2f2f2;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0px 0px 10px 0px #ccc;
        margin: 50px auto;
        width: 500px;
      }

      .form-control {
        width: 100%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;
      }
      
      .error {
        color: red;
        font-weight: bold;
        text-align: center;
        padding: 10px;
      }

      input[type=submit] {
        background-color: #4CAF50;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
      }

      input[type=submit]:hover {
        background-color: #45a049;
      }
</style>
</head>
<body>

	<ul>
		<li class="logo"><img src="icons8-logo-90.png" alt="Logo"></li>
      <li class="center">Discussion Forum</li>
		

		<li><a href="index.html">Home</a></li>
		<li><a href="registration">Register</a></li>
	</ul>

	<!-- Add your registration form below -->
   <div class="login-form">
      <h2>Login Form</h2>
      <%
      
      	if(request.getAttribute("errorMsg")!= null){
      		%>
      		
      <p class="error"><%=request.getAttribute("errorMsg")%></p>
      		
      		<% 
      	}
      %>
      
      <form action="login" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" class="form-control" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" required>

        <input type="submit" value="Submit">
      </form>
    </div>

</body>
</html>