<%@ page language="java" contentType="text/html; charset=MS932"	pageEncoding="MS932"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="MS932">
	<title>welcome/admin</title>
</head>
<body>
   <% out.println("welcome"); %>
   <p>ようこそ、${sessionUser.userName} さん！</p>
   <p>メニューレベル：${sessionUser.level}です</p>
   
   <p>あなたには管理者権限があります</p>
    <c:if test="${sessionUser}== null" >
  		 <p>sessionUserが空です</p>
   </c:if>
<a href="${pageContext.request.contextPath}/admin/users">
    <button type="button">ユーザー管理画面_一覧へ</button>
</a>
</body>
</html>
