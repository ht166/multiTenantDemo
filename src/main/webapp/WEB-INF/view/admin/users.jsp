<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>ユーザー一覧</h2>
<table border="1">
	<tr>
		<th>ID</th>
		<th>ユーザー名</th>
		<th>登録年月日</th>
		<th>メニュー権限</th>
		<th>テナント用コード</th>
		<th>操作</th>
	</tr>
	
	<c:forEach var="user" items="${users}">
		<tr>
			<td>${user.id}</td>
			<td>${user.userName}</td>
			<td>${user.created_at}</td>
			<td>${user.level}</td>
			<td>${user.code}</td>
			<td><a
				href="${pageContext.request.contextPath}/admin/editUser/${user.id}">編集</a>
				<form
					action="${pageContext.request.contextPath}/admin/deleteUser/${user.id}"
					method="post" style="display: inline;">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button type="submit">削除</button>
				</form></td>
		</tr>
	</c:forEach>
</table>
