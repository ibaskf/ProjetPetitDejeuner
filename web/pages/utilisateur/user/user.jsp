<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${action} un utilisateur</title>
<link href="../../style/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


	<header>
		<h1>${action} un utilisateur</h1>
	</header>

	<article>


		<c:if test="${not empty error}">
			<label style="color: red;"><c:out value="${error}" /></label>
		</c:if>
		<form:form action="save.html" commandName="userMaker" method="POST" class="form-horizontal">
			<form:hidden path="id" />

			<div class="form-group">
				<label class="control-label col-sm-2" for="login">Login :</label>
				<div class="col-sm-10">
					<form:input id="login" path="login" class="form-control"
						placeholder="Login" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="password">Mot de passe :</label>
				<div class="col-sm-10">
					<form:input id="password" path="password" type="password" class="form-control"
						placeholder="Mot de passe" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="passwordMatch">Confirmation :</label>
				<div class="col-sm-10">
					<form:input id="passwordMatch" path="passwordMatch" type="password"
						class="form-control" placeholder="Mot de passe" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label><form:checkbox id="enabled" path="enabled" />Activé</label>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="role">Role : </label>
				<div class="col-sm-10">
					<form:select id="role" class="form-control" path="role">
						<form:option value="">Sélectionner un role</form:option>
						<form:option value="ROLE_USER">User</form:option>
						<form:option value="ROLE_ADMIN">Admin</form:option>
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-default" value="valider" />
				</div>
			</div>
		</form:form>




	</article>






</body>
</html>