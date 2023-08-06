<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form by Sorwar</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<input type="hidden" id="title" value="<%= request.getAttribute("title") %>">
<input type="hidden" id="message" value="<%= request.getAttribute("message") %>">
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
					
						<form method="post" action="Registration" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="name" id="name" placeholder="Username" />
							</div>
							<div class="form-group">
								<label for="name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="fname" id="fname" placeholder="Full Name" />
							</div>
							<div class="form-group">
								<label for="email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="email" id="email" placeholder="Your Email" />
							</div>
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="pass" id="pass" placeholder="Password" />
							</div>
							<div class="form-group">
								<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
								<input type="password" name="re_pass" id="re_pass"
									placeholder="Confirm password" />
							</div>
							<div class="form-group">
								<label for="contact">User Role:</label>&emsp;&emsp;&emsp;&emsp;&emsp;
								<select id="cars" name="role" style="width:78%;height:20px;">
  <option value="student">Student</option>
  <option value="teacher">Teacher</option>
  <option value="admin">Admin</option>
</select>
							</div>
							<div class="form-group">
							<a href="login.jsp" class="signup-image-link" style="float:right">Already
							have an account? Login here!</a>
								<!-- <input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> <label for="agree-term"
									class="label-agree-term"><span><span></span></span>I
									agree all statements in <a href="#" class="term-service">Terms
										of service</a></label>-->
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<!-- <div class="signup-image">
						<!-  <figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>->
						<a href="login.jsp" class="signup-image-link">I am already
							member</a>
					</div>-->
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<!-- <link rel="stylesheet" href="alert/dist/sweetalert.css">-->
	<script type="text/javascript">
	var title=document.getElementById("title").value;
	var message=document.getElementById("message").value;
	var status=document.getElementById("status").value;
	//swal(title,message,status);
	if(message!="null")
		swal(title,message,status);
	/*var status=document.getElementById("status").value;
	if(status == "success"){
		swal("Congrats","Account Created Successfully","success");
	}
	else if(status == "passwordNotMatch"){
		swal("Sorry","Password not matching.","error");
	}*/
	</script>

</body>
<!-- This templates was made by Sorwar (https://fb.com/Hussain98) -->
</html>