<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>welcome</title>
</head>
<body>
   <% out.println("welcome"); %>
   <p>ようこそ、${sessionUser.userName} さん！</p>
   <p>メニューレベル：${sessionUser.menuLevel}です</p>
   <c:if test="${sessionUser.menuLevel ==0}" >
  		 <p>あなたには管理者権限があります</p>
   </c:if>
    <c:if test="${sessionUser}== null" >
  		 <p>sessionUserが空です</p>
   </c:if>
   
   
</body>
</html>