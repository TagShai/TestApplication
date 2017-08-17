<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:choose>
	<c:when test="${not empty someFilm}">
		<c:set var="name" value="${someFilm.name}"/>
		<c:set var="description" value="${someFilm.description}"/>
		<c:set var="genres" value="${someFilm.genres}"/>
		<c:set var="country" value="${someFilm.country}"/>
		<c:set var="quality" value="${someFilm.quality}"/>
		<c:set var="image" value="${someFilm.image}"/>
	</c:when>
	<c:otherwise>
		<c:set var="username" value="New Film"/>
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

		<title>Film edit</title>
	</head>
	<body>
		      	<form action="controller" method="post">
	      		<c:if test="${not empty someFilm}">
					<input type="hidden"  name="id" value="${someFilm.id}">
				</c:if>
				<input type="hidden" name="command" value="film_save" />

				<label for="name">Name:</label>
				<input type="text" id="name" name="name" value="${someFilm.name}">

				<label for="genres">Genres:</label>
				<c:forEach items="${someFilm.genres}" var="genre">
					<input type="text" id="genre" name="genre" value="${genre.name}">
				</c:forEach>

				<label for="description">Description:</label>
				<input type="text" id="description" name="description" value="${someFilm.description}">

				<label for="country">Country:</label>
				<input type="text" id="country" name="country" value="${someFilm.country}">

				<label for="quality">Quality:</label>
				<input type="text" id="quality" name="quality" value="${someFilm.quality}">

				<label for="image">Image:</label>
				<input type="text" id="image" name="image" value="${someFilm.image}">

				<button type="submit" class="btn btn-lg btn-primary">Save</button>
				<%--<c:if test="${not empty someUser}">
					<button type="btn btn-lg btn-primary" type="submit">Delete</button>
				</c:if>--%>
				<button type="reset">Reset</button>
			</form>
			<c:if test="${not empty someFilm}">
				<%--<c:url value="/user/delete.html" var="userDeleteUrl"/>--%>
				<form action="controller" method="post">
					<input type="hidden" name="command" value="film_delete" />
					<input type="hidden" name="id" value="${someFilm.id}">
					<button type="btn btn-lg btn-primary" type="submit">Delete</button>
				</form>
			</c:if>
	</body>
</html>