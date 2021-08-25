<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"	 uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet"/>
		<!-- Google Fonts -->
		<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
		<!-- MDB -->
		<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.6.0/mdb.min.css" rel="stylesheet"/>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<!-- MDB -->
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.6.0/mdb.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.0/jquery.js"></script> 
		
		<style>
			.gradient-custom {
				/* fallback for old browsers */
				background: #6a11cb;
				
				/* Chrome 10-25, Safari 5.1-6 */
				background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
				
				/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
				background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1)) ;
				
			}
		</style>
	</head>
	<body class="bg-primary">
		<section class="vh-100 gradient-custom">
			<div class="container py-5 h-100">
				<div class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-12 col-md-8 col-lg-6 col-xl-5">
						<div class="card bg-dark text-white" style="border-radius: 1rem;">
							<div class="card-body p-5 text-center">
								<div class="mb-md-5 mt-md-4 pb-5">
									<h2 class="fw-bold mb-2 text-uppercase">Login</h2>
									<p class="text-white-50 mb-5">Please enter your login and password!</p>
									<!--email 입력 input  -->
									<div class="form-outline form-white mb-4">
										<input class="form-control form-control-lg" id="email" type="email" placeholder="name@example.com" />
										<label class="form-label" for="email">Email</label>
									</div>
									<!--password 입력 input  -->
									<div class="form-outline form-white mb-4">
										<input class="form-control form-control-lg" id="password" type="password" placeholder="Password" />
										<label class="form-label" for="password">Password</label>
									</div>
									<!--password 찾기 버튼  -->
									<p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p>
									<!--로그인 버튼  -->
									<button class="btn btn-outline-light btn-lg px-5" type="button" onclick="fncLogin();">Login</button>
									<!--SNS 연동  -->
									<div class="d-flex justify-content-center text-center mt-4 pt-1">
										<a href="#!" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
										<a href="#!" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
										<a href="#!" class="text-white"><i class="fab fa-google fa-lg"></i></a>
									</div>
								</div>
								<!--회원가입  -->
								<div>
									<p class="mb-0">Don't have an account? <a href="#!" class="text-white-50 fw-bold">Sign Up</a></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<script>
			//로그인 함수
			function fncLogin() {
				let data = {
					'email': $('#email').val(),
					'password': $('#password').val()
				};
				
				$.ajax({
					url: '<c:url value="/auth/login.do"/>',
					type: 'post',
					data: data,
					success: function(response) {
						if(response.result.type == 'success') {
							location.href = '<c:url value="/program/list.do"/>';
						} else if(response.result.type == 'fail') {
							alert(response.result.message);
						}
					}
				});
			}
		</script>
	</body>
</html>