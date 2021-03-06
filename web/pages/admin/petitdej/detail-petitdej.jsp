<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
				<th>nom</th>
                <th>date</th>
                <th>Type</th>
                 <th>Description</th>
                 <th>participant</th>
              <c:if test="${(petitDej.date gt datejour)}">   <th>presence</th>
               </c:if>  <th>Action</th>
                   <c:if test="${(petitDej.date lt datejour)}">   <th>prix</th>
               </c:if>
                
			</tr>
			
				<tr>
					<td><c:out value="${petitDej.name}"/></td>
					<td>${petitDej.date}</td>
					<td>${petitDej.type}</td>
					<td>${petitDej.description}</td>
					
				<td>
				<c:forEach items="${membres}" var="membre">
					<p>${membre.name}  ${membre.firstname}<p>
					</c:forEach>
					</td>
					
					
					<c:if test="${(petitDej.date gt datejour)}">
					<td valign="bottom">
					<c:forEach items="${membres}" var="membre">
				
					
					
					<c:set var="membreid" scope="session" value="${membre.id}"></c:set>
					  <p><a href="deletepart.html?id=${petitDej.id}&idm=${membre.id}">Delete</a><p>
					</p>
					
				
					</c:forEach>
					</td>
						</c:if>
						
					
			
					<td><a href="edit.html?id=${petitDej.id}">Edit</a> <a
						href="delete.html?id=${petitDej.id}">Delete</a></td>
						
					<c:if test="${petitDej.prix!=NULL && (petitDej.date lt datejour)}">
					<td>${petitDej.prix}</td>
					</c:if>	
						
							
	 <c:if test="${(petitDej.date lt datejour) && (petitDej.prix==NULL)}">   
					 <TD>
	     <c:set var="dateform" value="${fn:split(petitDej.date, '-')}" />
	    
					 <form:form action="save.html" commandName="petitDej" name="frm" method="POST" >
		<form:hidden path="id" />
		
		<form:input path="name" type="hidden" id="nom" value="${petitDej.name}"/>
		<form:input path="date" type="hidden" id="date" value="${dateform[2]}-${dateform[1]}-${dateform[0]}"/>
		<form:input path="type" type="hidden" id="type" value="${petitDej.type}"/>
		<form:input path="description" type="hidden" id="description" value="${petitDej.description}"/>
		<form:input path="membres" type="hidden" id="membres" value="${petitDej.membres}"/>
		
		<form:input id="prix" path="prix" type="text" />	<input type="submit" value="valider" /></form:form></TD>
               </c:if>
							
				</tr>
			
		
		</tbody>
	</table>
	<table>
	<tbody title="Commentaires">
	<c:forEach items="${appreciations}" var="appreciation">
		<tr>
				<th>Commentaire</th>
                <th>Note</th>
                <th>Nom</th>
       
                
			</tr>
	<tr>
	<td>${appreciation.commentaire}</td>
	<td>${appreciation.note}</td>
	<td>${appreciation.membre.name}</td>
	</tr>
	</c:forEach>
	</tbody></table>

	<c:if test="${(petitDej.date lt datejour)}">
	<form:form action="../appreciation/save.html" commandName="appreciation" method="POST">
		<c:if test="${appreciation.membre.id ==membreid}">
		<form:hidden path="id" />
		<form:input path="membre" type="hidden" id="membre.id" value="${membreid}"/>
			<form:input path="petitdej" type="hidden" id="petitdej.id" value="${petitDej.id}"/>
		<label>Note:</label>
		<form:input path="note" id="note" />
		<br />
			<label>Commentaire:</label>
		<form:input type="text" path="commentaire" id="commentaire" />
		<br />
		<input type="submit" value="valider" />
		</c:if>
	</form:form>
	</c:if>
	<a href="new.html">Ajout Petit DEJ</a>
	</div>
</body>
</html>