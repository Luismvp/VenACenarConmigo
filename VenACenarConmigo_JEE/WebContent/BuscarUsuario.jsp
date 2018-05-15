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
	<c:forEach items="${index_list}" var="indexi">
        <div>
		<h2>${usuario_list.get(indexi).nombre} ${usuario_list.get(indexi).apellidos}</h2>
		<h4>Ciudad: ${usuario_list.get(indexi).ciudad}</h4>
		<h4>Descripción: ${usuario_list.get(indexi).descripcion}</h4>
	    </div>
		<c:choose>
			<c:when test="${botones.get(indexi) == 1}">
				<form action="MuestraUsuarioServlet">
	    			<input type="hidden" value="${usuario_list.get(indexi).email}" name="email">
        			<button type="submit" class="btn btn-success">Ver perfil</button>
        		</form>
			</c:when>
			<c:when test="${botones.get(indexi) == 2}">
				<form action="SeguirUsuarioServlet">
	    			<input type="hidden" value="${usuario_list.get(indexi).email}" name="email">
	    			<input type="hidden" value="true" name="enBusqueda">
	    			<input type="hidden" value="${indexi}" name="index">
        			<button type="submit" class="btn btn-success">
						Seguir <span class="glyphicon glyphicon-ok"></span>
					</button>
        		</form>
			</c:when>
			<c:when test="${botones.get(indexi) == 3}">
				<form action="">
	    			<input type="hidden" value="${usuario_list.get(indexi).email}" name="email">
	    			<input type="hidden" value="true" name="enBusqueda">
	    			<input type="hidden" value="${indexi}" name="index">
        			<button type="submit" class="btn btn-success">
						Dejar de seguir <span class="glyphicon glyphicon-ok"></span>
					</button>
        		</form>
			</c:when>
		</c:choose>
    </c:forEach>
</body>
</html>