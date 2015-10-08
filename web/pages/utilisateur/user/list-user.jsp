<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<c:if test="${not empty error}">
	<label style="color:red;"><c:out value="${error}"/></label>
</c:if>
	
	<table>
		<tbody>
			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.login}</td>
					<td><a href="edit.html?id=${user.id}">éditer</a>
						<a href="delete.html?id=${user.id}">supprimer</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="new.html">Nouvelle personne</a>
</body>
</html>