<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
 <%@include  file="../../header.html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../style/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h3>Agenda</h3>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>

	<table border="1" width="50%">
		<tbody>
			<tr>
				<th>Nom</th>
                <th>DATE</th>
                <th>Preference</th>
                
			</tr>
			<c:forEach items="${petitDejs}" var="petitDej">
				<tr>
					<td>${petitDej.name}</td>
					<td>${petitDej.date}</td>
					<td>${petitDej.type}</td>
					<td><a href="../petitdej/listparticipant.html?id=${petitDej.id}">Participants</a></td>

					<td>
					<a href="detail.html?id=${petitDej.id}">Detail</a> 
				
					<c:if test="${petitDej.organisateur.id==login.id}">
					<a
						href="edit.html?id=${petitDej.id}">Edit</a>
					<a
						href="delete.html?id=${petitDej.id}">Annuler</a></c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="new.html">Ajout Petit DEJ</a>
</div>
</body>
</html>