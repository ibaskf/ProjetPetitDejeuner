<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../../header.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript">
	function showmembre() {
		document.getElementById('mbs').style.display = "inline";
		document.getElementById('teams').style.display = "none";
		document.getElementById("membres").setAttribute("required", true);
		document.getElementById("teams").removeAttribute("required");
	}

	function showteam() {
		document.getElementById('teams').style.display = "inline";
		document.getElementById('mbs').style.display = "none";
		document.getElementById("teams").setAttribute("required", true); 
		document.getElementById("membres").removeAttribute("required");
	}


</script>
 <script>
$(function() {
$( "#datepicker" ).datepicker({dateFormat:'dd-mm-yy', minDate: 'today'});
});
</script>
</head>
<body>
	<div align="center">
		<c:if test="${not empty error}">
			<label style="color: red;"><c:out value="${error}" /></label>
		</c:if>
		<form:form action="save.html" commandName="petitDej"
			method="POST" >
			<form:hidden path="id" />
			<label>Nom:</label>
			<form:input path="name" id="name" required="true" />
			<p style="color:red;"><form:errors path="name" cssclass="error"></form:errors></p>
			 
			<br />
			<label id="date_label" for="date" title="Date"> </label>

			<label>Date:</label>
			<input id="datepicker" name="date" type="text" required/>
		<!--	<input id="datepicker" name="date" type="date"
				value="<fmt:formatDate value="${date}" 
        type="date" pattern="dd-MM-yyyy" />" 
				required /> -->
			<br />
			<label>Invitations:</label>
			Membres
			<INPUT type="radio" name="invitation" value="mb" id="mb"
				onclick="showmembre()" />
				Equipe
			<INPUT type="radio" name="invitation" value="team" id="team"  onclick="showteam()" />
			<div id="mbs" style="display: none;">
			<br/>
				<label>Participant:</label>
				<form:select path="membres" id="membres" multiple="true"
					>
					<c:forEach var="membre" items="${membres}">
						<option value='<c:out value="${membre.id}"/>'><c:out
								value="${membre.name}" /></option>
					</c:forEach>
					<form:input path="organisateur" type="hidden" id="organisateur"
						value="${membreloger.id}" />
				</form:select>
				<br />
			</div>
			<div id="teams" style="display: none;">
				<label>Equipe:</label>
				<form:select path="team" id="team">
					<c:forEach var="team" items="${teams}">
						<option value='<c:out value="${team.id}"/>'><c:out
								value="${team.name}" /></option>
					</c:forEach>
                   <option value="0" selected></option> 
				</form:select>
			</div>




			<br />
			<label>Type</label>
			<form:radiobuttons items="${typedej}" path="type" />
			<form:errors path="type" cssClass="error" />


			<br />
			<input type="submit" value="valider" />
		</form:form>
	</div>
</body>
</html>