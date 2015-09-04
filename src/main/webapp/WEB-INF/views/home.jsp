<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/main.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BunnyLand Contents</title>
</head>

<body>
	<div id='content'>
		<div class="inner-container">
			<form action="<c:url value ='login.do'/>" method="POST">
			<div class="box">
				<h1>Login</h1>
				<p>UserName</p>
				<input type="text" name="id" required/>
				<p>Password</p>
				<input type="password" name="password" required/>
				<input type="submit" value="ENTER">
			</div>
			</form>
		</div>
	</div>
</body>
</html>