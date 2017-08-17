<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${not empty someUser}">
		<c:set var="username" value="${someUser.username}"/>
		<c:set var="password" value="${someUser.password}"/>
	</c:when>
	<c:otherwise>
		<c:set var="username" value="New User"/>
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">

		<title>User edit</title>

		<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
	</head>
	<body>
      	<form action="controller" method="post">
			<c:if test="${not empty someUser}">
				<input type="hidden" name="id" value="${someUser.id}">
			</c:if>
			
			<input type="hidden" name="command" value="user_save" /> 

			<label for="username">Username:</label>
			<input type="text" id="username" name="username" value="${someUser.username}">

			<label for="password">Password:</label>
			<input type="text" id="password" name="password" value="${someUser.password}">

			<button type="submit" class="btn btn-lg btn-primary">Save</button>

			<c:if test="${not empty someUser}">
				<button type="button" onclick="submitFormById('form-delete')">Delete</button>
			</c:if>
			<button type="reset">Reset</button>
		</form>
	</body>
</html>