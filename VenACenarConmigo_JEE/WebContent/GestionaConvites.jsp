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
<title>Gestionar convites</title>
<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style>
 	body{
            padding-left: 100px;
            padding-right: 100px;
            margin-top:70px;
        }
    a{
            color: whitesmoke;
    }   
    label{
            text-align: left;
    }
.convites {
	margin-top: 70px;
}
</style>
</head>
<body>
	<%@include file="templates/navBarLoged.jsp"%>
	<div class="convites">
	<c:if test="${empty convites_anfitrion}">
		<h2>No tienes convites creados en este momento</h2>
	</c:if>
		<c:forEach items="${convites_anfitrion}" var="convitea">
			<h2>${convitea.nombre}</h2>
			<h4>Fecha: ${convitea.fecha}</h4>
			<h4>Ciudad: ${convitea.ciudad}</h4>
			<h4>Área: ${convitea.area}</h4>
			<h4>Precio por invitado: ${convitea.precioInvitado} €</h4>
			<h4>Descripción: ${convitea.descripcion}</h4>
			<form action="MuestraConviteServlet">
				<button type="submit" class="btn btn-success" id="btnSubmit">Ver detalles</button>
				<br>
				<input type="text" style="visibility: hidden"
					value="${convitea.idConvite}" name="idConvite" id="idConvite">
			</form>
		</c:forEach>
	</div>
</body>
</html>
