<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Añadir Invitado</title>
<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	padding-left: 100px;
	padding-right: 100px;
}

a {
	color: whitesmoke;
}

label {
	text-align: left;
}

h1 {
	text-align: center;
	margin-top:70px;
}
</style>
</head>
<body>
	<%@include file="templates/navBarLoged.jsp"%>
	<h1>Añade un invitado al convite:</h1>
	<form action="AnadeInvitadoServlet">
		<%@include file="formularios/Invitados1.jsp"%>
		<button class="btn btn-success" type="submit" id="btnSubmit2">Añadir
			invitado</button>
		<br> <input type="text" value="${idConvite}"
			name="idConvite" id="idConvite" style="visibility: hidden;">
	</form>
</body>
</html>