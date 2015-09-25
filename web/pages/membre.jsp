<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>
	<form:form action="save.do" commandName="membre" method="POST">
		<form:hidden path="id" />
		<label>Nom:</label>
		<form:input path="name" id="name" />
		<br />
		<!--  <label>Team:</label>
		<form:select path="team" id="teams">
	<c:forEach var="team" items="${teams}">
		<option value='<c:out value="${team.id}"/>'><c:out value="${team.name}"/></option>
	</c:forEach>
		</form:select>
		-->
		<br />
		<input type="submit" value="valider" />
	</form:form>
</body>
</html>