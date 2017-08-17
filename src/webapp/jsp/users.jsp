<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">

		<title>Users</title>

		<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<h1>List of users</h1>
			<table class="table table-bordered">
				<tr>
					<th>Username</th>
					<th>Password</th>
				</tr>
				<c:url value="${contextPath}/jsp/user/edit.jsp" var="user_find"/>
				<c:forEach items="${users}" var="user">
					<tr onclick="submitFormById('form-${user.id}')">
						<td>
							${user.username}
							<form id="form-${user.id}" action="${user_find}" method="post">
								<input type="hidden" name="id" value="${user.id}">
							</form>
						</td>
						<td>${user.password}</td>
					</tr>
				</c:forEach>
			</table>
			<form action="${user_find}" method="post">
				<button type="submit">Add</button>
			</form>
		</div>
	</body>
</html>