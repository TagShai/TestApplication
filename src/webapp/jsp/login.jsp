<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fmt:setBundle basename="i18n.login.index_${locale}" />
<%--<c:set var="locale"
	value="${not empty param.locale ? param.locale : not empty locale ? locale : 'EN'}"
	scope="session" />--%>

<!DOCTYPE html>
<html>
	<head>
		<fmt:setLocale value="${locale}" scope="session"/>
		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">

		<title>Log in with your account</title>

	    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<div class="">
			    <form name ="languageForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="RU" />
                            <button name="language" value="RU" type="submit" class="btn btn-lg btn-primary"><img src="${contextPath}/resources/img/internationalization/ru.png" width="30px" alt="">Ru</button>

                            <input type="hidden" name="command" value="EN" />
                            <button name="language" value="EN" type="submit" class="btn btn-lg btn-primary"><img src="${contextPath}/resources/img/internationalization/en.png" width="30px" alt="">En</button>
                </form>
            </div>
		</div>

		<div class="container">
			<form name="loginForm" method="POST" action="controller" class="form-signin">
				<h2 class="form-heading"><fmt:message key="login"/></h2>

				<div class="form-group ${errorLoginPassMessage != null ? 'has-error' : ''}">
			      	<input type="hidden" name="command" value="login" /> 
			      	<div class="form-group">
			      		<input type="text" name="login" value="" placeholder="<fmt:message key="enterUsername"/>" class="form-control" autofocus="true">
			      	</div>
			      	<div class="form-group">
			      		<input type="password" name="password" value="" placeholder="<fmt:message key="enterPassword"/>" class="form-control">
			      	</div>

			      	<span>${wrongAction} ${nullPage}</span>
			      	<c:if test="${not empty errorLoginPassMessage}">
                    	<div class="alert alert-danger"><fmt:message key="message.loginerror"/></div>
                	</c:if>

			      	<button type="submit" class="btn btn-lg btn-primary btn-block"><fmt:message key="submit"/></button>

					<c:if test="${not empty successRegistration}">
						<br>
						<div class="alert alert-success"><fmt:message key="message.success.registration"/></div>
	        		</c:if>

			    </div>
	      	</form>

	        <form name ="registerForm"  method="POST" action="controller">
	            <input type="hidden" name="command" value="REGISTRATION_REDIRECT"/> 
	            <h4 class="text-center"><button type="submit" class="btn btn-lg btn-primary"><fmt:message key="createAccount"/></button></h4>
	        </form>
	    </div>
	</body>
</html>