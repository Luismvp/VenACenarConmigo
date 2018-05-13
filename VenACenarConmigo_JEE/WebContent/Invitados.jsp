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
<title>Invitados</title>
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

#Registro {
	margin-top: 70px;
	text-align: center;
}

label {
	text-align: left;
}

form {
	margin-top: 70px;
	text-align: center;
}
</style>
</head>
<body>
	<%@include file="templates/navBarLoged.jsp"%>
	<form action="InvitadosServlet">
		<c:choose>
			<c:when test="${convite.maxInvitados == 1}">
				<%@include file="formularios/Invitados1.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados == 2}">
				<%@include file="formularios/Invitados2.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==3}">
				<%@include file="formularios/Invitados3.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==4}">
				<%@include file="formularios/Invitados4.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==5}">
				<%@include file="formularios/Invitados5.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==6}">
				<%@include file="formularios/Invitados6.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==7}">
				<%@include file="formularios/Invitados7.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==8}">
				<%@include file="formularios/Invitados8.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==9}">
				<%@include file="formularios/Invitados9.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==10}">
				<%@include file="formularios/Invitados10.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==11}">
				<%@include file="formularios/Invitados11.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==12} ">
				<%@include file="formularios/Invitados12.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==13}">
				<%@include file="formularios/Invitados13.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==14}">
				<%@include file="formularios/Invitados14.jsp"%>
			</c:when>
			<c:when test="${convite.maxInvitados ==15}">
				<%@include file="formularios/Invitados15.jsp"%>
			</c:when>
		</c:choose>
		<button type="submit" class="btn btn-success col-xs-12" id="btnSubmit">
			Enviar <span class="glyphicon glyphicon-arrow-right"></span>
		</button>

	</form>
</body>
</html>