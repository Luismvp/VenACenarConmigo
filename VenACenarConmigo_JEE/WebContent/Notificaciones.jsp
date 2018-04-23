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
    <title>Notificaciones</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            padding-left: 100px;
            padding-right: 100px;
        }
        a{
            color: whitesmoke;
        }
        #Registro{
            margin-top: 70px;
            text-align: center;
        }
        label{
            text-align: left;
        }
        .notificaciones{
        	margin-top:70px;
        	
        }
    </style>
</head>
<body>
<%@include file="templates/navBarLoged.jsp" %>
<div class="notificaciones">
<c:forEach items="${Lista_convites}" var="convitei">
	<div class="notificacion">
        <h3>${convitei.nombre}</h3>
        <h4>${convitei.fecha}</h4>
        <p>${convitei.temasConversacion }</p>
        <p>${convitei.restaurante }</p>
        <p>${convitei.menu}</p>
        <p>Hora de inicio: ${convitei.horaComienzo}</p>
        <p>Hora del fin: ${convitei.horaFin}</p>
        <p>Precio por invitado ${convitei.precioInvitado }</p>
        <p>Ciudad: ${convitei.ciudad }</p>
        <p>Area: ${convitei.area}</p>
        <form action="AceptaInvitacionServlet">
        <input type="text" value="${convitei.idConvite}" name="idConvite" id="idConvite" style="visibility: hidden;">
        <button type="submit" class="btn btn-success" id="btnSubmit" >Aceptar invitación</button>
        </form>
        <form action="AceptaInvitacionServlet">
        <input type="text" value="${convitei.idConvite}" name="idConvite" id="idConvite" style="visibility: hidden;">
        <button class="btn btn-danger" type="submit" id="btnSubmit2">Rechazar invitación</button>
        </form>
    </div>
    <br>
</c:forEach>
</div>
</body>
</html>