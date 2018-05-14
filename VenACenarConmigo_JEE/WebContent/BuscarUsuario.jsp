<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Buscar Usuario</title>
	<!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
    </style>
</head>
<body>
<%@include file="templates/navBarLoged.jsp" %>
	<c:forEach items="${usuario_list}" var="usuarioi">
        <div>
		<h2>${usuarioi.nombre} ${usuarioi.apellidos}</h2>
		<h4>Ciudad: ${usuarioi.ciudad}</h4>
		<h4>Descripción: ${usuarioi.descripcion}</h4>
	    </div>
	    <form action="MuestraUsuarioServlet">
	    	<input type="hidden" value="${usuarioi.email}" name="email">
        	<button type="submit" class="btn btn-success">Ver perfil</button>
        </form>
    </c:forEach>
</body>
</html>