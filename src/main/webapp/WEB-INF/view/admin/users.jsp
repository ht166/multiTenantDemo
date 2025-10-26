<%@ page language="java" contentType="text/html; charset=MS932"
    pageEncoding="MS932"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>���[�U�[�ꗗ</h2>
<table border="1">
	<tr>
		<th>ID</th>
		<th>���[�U�[��</th>
		<th>�o�^�N����</th>
		<th>���j���[����</th>
		<th>�e�i���g�p�R�[�h</th>
		<th>����</th>
	</tr>
	
	<c:forEach var="user" items="${users}">
		<tr>
			<td>${user.id}</td>
			<td>${user.userName}</td>
			<td>${user.created_at}</td>
			<td>${user.level}</td>
			<td>${user.code}</td>
			<td><a
				href="${pageContext.request.contextPath}/admin/editUser/${user.id}">�ҏW</a>
				<form
					action="${pageContext.request.contextPath}/admin/deleteUser/${user.id}"
					method="post" style="display: inline;">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button type="submit">�폜</button>
				</form></td>
		</tr>
	</c:forEach>
</table>
