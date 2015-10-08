<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Membres</title>
</head>
<body>
<div align="center">
	<h3>Listes des Membres</h3>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>

	<table border="1" width="50%">
		<tbody>
			<tr>
				<th>Nom</th>
                <th>Prénom</th>
                <th>Preference</th>
                
			</tr>
			<c:forEach items="${membres}" var="membre">
				<tr>
					<td>${membre.name}</td>
					<td>${membre.firstname}</td>
					<td>${membre.preference}</td>

					<td><a
						href="delete.html?id=${membre.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="new.html">Ajout Membre</a>
</div>
</body>
</html>