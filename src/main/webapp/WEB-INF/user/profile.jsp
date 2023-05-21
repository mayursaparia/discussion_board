<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css" >
<style type="text/css">
 /* Add your registration form styling here */
      .registration-form {
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
      
      .error {
        color: red;
        font-weight: bold;
        text-align: center;
        padding: 10px;
      }
      
      .errorPass {
        color: red;
        font-weight: bold;
        text-align: center;
        padding: 10px;
      }
</style>

<script>
function loaded(){
	function validatePassword() {
		  var password = document.getElementById("password").value;
		  var confirm_password = document.getElementById("confirm_password").value;
		
		  if (password != confirm_password) {
			document.getElementById('errorPass').innerHTML = "Error: Passwords do not match";
		    return false;
		  }
		  return true;
		}
		
		document.getElementById("updatePassword").addEventListener("submit", function(event){
		    if (!validatePassword()) {
		      event.preventDefault();
		    }
		});
}
	
</script>

</head>
<body onload="loaded();">

	<ul>
		<li class="logo"><img src="icons8-logo-90.png" alt="Logo"></li>
      <li class="center">Discussion Forum</li>
		

		<li><a href="controller?action=dashboard">Dashboard</a></li>
		<li><a href="logout">Logout</a></li>
	</ul>

	<!-- Add your registration form below -->
    <div class="registration-form">
      <h2>User Profile</h2>
      <%
      
      	if(request.getAttribute("errorMsg")!= null){
      		%>
      		
      <p class="error"><%=request.getAttribute("errorMsg")%></p>
      		
      		<% 
      	}
      %>
      <form action="userProfile" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" class="form-control" value="${requestScope.username}" placeholder="Enter your Username" required>
        
        <label for="firstname">Firstname</label>
        <input type="text" id="firstname" name="firstname" class="form-control" value="${requestScope.firstname}" placeholder="Enter your First name" required>

        <label for="lastname">Lastname</label>
        <input type="text" id="lastname" name="lastname" class="form-control" value ="${requestScope.lastname}" placeholder="Enter your Last name" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" class="form-control" value ="${requestScope['email']}" placeholder="Enter your email" required>

    	<input type="hidden" name="page" value="editProfile"/>

        <input type="submit" value="Submit">
      </form>
    </div>
    
        <div class="registration-form">
      <h2>Update Password</h2>
      <form id="updatePassword" action="userProfile" method="post">
      <p class="errorPass" id="errorPass"></p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" class="form-control" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!,@,#,$,%,^,&,*]).{6,}" title="Should contain one uppercase and lowercase letter, numbers , special characters [!@#$%^&*] , minimum 6 characters " placeholder="Enter your password" required>

        <label for="confirm_password">Confirm Password</label>
        <input type="password" id="confirm_password" name="confirm_password" class="form-control" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!,@,#,$,%,^,&,*]).{6,}" title="Should contain one uppercase and lowercase letter, numbers , special characters [!@#$%^&*] , minimum 6 characters " placeholder="Enter your password" required>

        	<input type="hidden" name="page" value="editProfilePassword"/>
    

        <input type="submit" value="Submit">
      </form>
    </div>

</body>
</html>