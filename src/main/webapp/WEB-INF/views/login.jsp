<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 --%><%@ page session= "false" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/login.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to BunnyLand Contents manager site</title>
</head>
<body>


	<div id='content'>
		<div class="inner-container">
			<form action="login.do" method="POST">
				<div class="box">
					<h1>Login</h1>
					<p>UserName</p>
					<input type="text" name="id" required/>
					<p>Password</p>
					<input type="password" name="password" required/>
					<td><input type="submit" value="ENTER"></td>
				</div>
			</form>
	</div>
	</div>


</body>
</html>