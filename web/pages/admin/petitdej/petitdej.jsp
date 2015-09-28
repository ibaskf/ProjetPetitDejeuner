<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"> 
function validateForm()
{
	
	var re = new RegExp("^([0-9]{4})\-([0-9]{2})\-([0-9]{2})$");
if(!re.test(document.frm.date.value))
{
alert("format date :aaaa-mm-dd");
document.frm.date.focus();
return false;
}
}
</script>
</head>
<body>
	<c:if test="${not empty error}">
		<label style="color: red;"><c:out value="${error}" /></label>
	</c:if>
	<form:form action="save.html" commandName="petitDej" name="frm" method="POST" onsubmit="return validateForm()">
		<form:hidden path="id" />
		<label>Nom:</label>
		<form:input path="name" id="name" required="true"/>
		<br />
<label id="date_label" for="date" title="Date">

</label>

 <label>Date:</label>
<input id="date" name="date" type="text" 
        value="<fmt:formatDate value="${reg.date}" 
        type="date" pattern="dd-MM-yyyy" />" required="true"/>
	<br/>	
	 <label>Participant:</label>
		<form:select path="membres" id="membres" multiple="true" required="true">
	<c:forEach var="membre" items="${membres}">
		<option value='<c:out value="${membre.id}"/>'><c:out value="${membre.name}"/></option>
	</c:forEach>
		</form:select>
		
		<br />
		<input type="submit" value="valider" />
	</form:form>
</body>
</html>