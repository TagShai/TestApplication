<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fmt:setBundle basename="i18n.registration.index_${locale}" />

<c:choose>
    <c:when test="${not empty someUser}">
        <c:set var="username" value="${someUser.username}"/>
        <c:set var="password" value="${someUser.password}"/>
        <c:set var="confirmPassword" value="${someUser.confirmPassword}"/>
    </c:when>
    <c:otherwise>
        <c:set var="username" value="New User"/>
    </c:otherwise>
</c:choose>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Create an account</title>

        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <form action="controller" method="post" class="form-signin">

                <h2 class="form-signin-heading"><fmt:message key="createYourAccount"/></h2>
                <c:if test="${not empty someUser}">
                    <input type="hidden"  name="id" value="${someUser.id}">
                </c:if>
                <input type="hidden" name="command" value="user_save" />

                <input type="text" id="username" name="username" value="${someUser.username}" class="form-control" placeholder="<fmt:message key="enterUsername"/>" autofocus="true" required>

                <input type="password" id="password" name="password" value="${someUser.password}" class="form-control" placeholder="<fmt:message key="enterPassword"/>" required>

                <input type="password" id="confirmPassword" name="confirmPassword" value="${someUser.confirmPassword}" class="form-control" placeholder="<fmt:message key="confirmPassword"/>" required>

                <br>
                <c:if test="${not empty duplicateUserFormUsername}">
                    <div class="alert alert-danger"><fmt:message key="message.duplicate.userForm.username"/></div>
                </c:if>
                <c:if test="${not empty sizeUserFormUsername}">
                    <div class="alert alert-danger"><fmt:message key="message.size.userForm.username"/></div>
                </c:if>
                <c:if test="${not empty sizeUserFormPassword}">
                    <div class="alert alert-danger"><fmt:message key="message.size.userForm.password"/></div>
                </c:if>
                <c:if test="${not empty differentUserFormPassword}">
                    <div class="alert alert-danger"><fmt:message key="message.different.userForm.password"/></div>
                </c:if>

                <button type="submit" class="btn btn-lg btn-success"><fmt:message key="submit"/></button>
                <button type="reset" class="btn btn-lg btn-danger"><fmt:message key="reset"/></button>
            </form>
        </div>
    </body>
</html>