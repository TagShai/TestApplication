<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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

		<title>Main page</title>

		<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
			    <div class="navbar-header">
			    	<a class="navbar-brand" href="#">KinoSite</a>
			    	<div class="navbar-brand">${user}, hello!</div>
			    </div>
			    <ul class="nav navbar-nav">
			      	<%--<li class="active"><a href="#">Home</a></li>--%>
			      	<%--<li><a href="#">SomePage</a></li>--%>
			    </ul>
			    <ul class="nav navbar-nav navbar-right">
      				<form name ="logoutForm"  method="POST" action="controller">
      					<input type="hidden" name="command" value="logout"/> 
      					<li style="margin-right: 10px;">
      						<button type="submit" class="btn btn-primary btn-lg">Logout</button>
      					</li>
      				</form>
    			</ul>
			    <form name ="searchForm"  method="POST" action="controller" class="navbar-form navbar-left">
			        <input type="hidden" name="command" value="film_find_list_by_name"/>
        			<div class="form-group">
          				<input type="text" name="name"
                               value="" class="form-control" placeholder="Search"> 
        			</div>
        			<button type="submit" class="btn btn-default">Submit</button>
      			</form>
		  	</div>
		</nav>

      	<form name="loginForm" method="POST" action="controller"> 
		    <input type="hidden" name="command" value="user_find" /> 
		    Id of user:<br/> 
		    <input type="text" name="id" value=""/> 
			<input type="submit" value="Push me" class="w3-btn w3-yellow w3-border"/>
		    <br/> ${errorLoginPassMessage}
		    <br/> ${wrongAction} 
		    <br/> ${nullPage}
		    <br/>         
      	</form>

      	<form action="controller" method="post">
      		<c:if test="${not empty someUser}">
				<input type="hidden"  name="id" value="${someUser.id}">
			</c:if>
			<input type="hidden" name="command" value="user_save" />

			<label for="username">Username:</label>
			<input type="text" id="username" name="username" value="${someUser.username}">

			<label for="password">Password:</label>
			<input type="text" id="password" name="password" value="${someUser.password}">

			<button type="submit" class="btn btn-lg btn-primary">Save</button>
			<%--<c:if test="${not empty someUser}">
				<button type="btn btn-lg btn-primary" type="submit">Delete</button>
			</c:if>--%>
			<button type="reset">Reset</button>
		</form>
		<c:if test="${not empty someUser}">
			<%--<c:url value="/user/delete.html" var="userDeleteUrl"/>--%>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="user_delete" />
				<input type="hidden" name="id" value="${someUser.id}">
				<button type="btn btn-lg btn-primary" type="submit">Delete</button>
			</form>
		</c:if>

		<div class="container-fluid text-center">    
  			<div class="row content">
    			<div class="col-sm-2 sidenav">
    				<h1>Categories</h1>
    				<ul class="list-group">
    					<h4>
	    					<c:forEach items="${genres}" var="genre">
	    						<li class="list-group-item">${genre.name}</li>
							</c:forEach>
						</h4>
					</ul>
    			</div>
    		<div class="col-sm-8 text-left"> 
	            <div class="row text-center">
	                <c:forEach items="${films}" var="film">
	                	<div class="col-md-4">
			                <img class="img-circle" src="${contextPath}/resources/img/films/${film.image}" alt="picture" width="200px">
			                <b>
			                <h4 class="service-heading">${film.name}</h4>
			                <div>
			                   	Genres:
			                   	<c:forEach items="${film.genres}" var="genre">
									<span>${genre.name}</span>
								</c:forEach>
			                </div>
			                <div>Country: ${film.country}</div>
			                <div>Quality: ${film.quality}</div>
			                <p class="text-muted">${film.description}</p>
			                <c:if test="${accessType == 'ADMIN'}">
			                	<form name="FilmEditForm" method="POST" action="controller"> 
		    						<input type="hidden" name="command" value="film_find" />
		    						<input type="hidden" name="id" value="${film.id}"/> 
			               			<button type="btn btn-lg btn-primary" type="submit">Edit</button>
			               		</form>
			                </c:if>
		                </div>
	                </c:forEach>
	            </div>
	        </div>
    		<div class="col-sm-2 sidenav">
    			<h1>Last films</h1>
      			<div class="well">
        			<p>New film 1</p>
      			</div>
      			<div class="well">
        			<p>New film 2</p>
      			</div>
      			<div class="well">
        			<p>New film 3</p>
      			</div>
    		</div>
  		</div>
	</body>
</html>