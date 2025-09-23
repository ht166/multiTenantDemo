<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
</head>
<body>
    <h2>ログイン</h2>
    <c:if test="${not empty message}">
        <p style="color:red">${message}</p>
    </c:if>
   
    <!-- <c:set var="jump_url" value="/login/auth"/> -->
    <form:form modelAttribute="loginForm" action="/login/auth" method="post">
        <div>
            <form:label path="userId">ユーザーID：</form:label>
            <form:input path="userId"/>
        </div>
        <div>
             <form:label path="password">パスワード：</form:label>
            <form:input path="password"/>
        </div>
        <div>
            <input type="submit" value="ログイン"/>
        </div>
        <div>
        	<form:errors path="*" style="color: red;"/>
        </div>
    </form:form>
</body>
</html>