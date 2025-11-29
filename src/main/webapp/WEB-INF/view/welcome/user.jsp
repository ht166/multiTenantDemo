<%@ page language="java" contentType="text/html; charset=MS932"	pageEncoding="MS932"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="MS932">
	<title>welcome</title>
</head>
<body>
   <% out.println("welcome"); %>
   <p>ようこそ、${sessionUser.userName} さん！</p>
   <p>メニューレベル：${sessionUser.level}です</p>
    <c:if test="${sessionUser}== null" >
  		 <p>sessionUserが空です</p>
   </c:if>
</body>
</html>
