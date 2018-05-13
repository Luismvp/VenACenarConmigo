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
    <title>Buscar convite</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            padding-left: 100px;
            padding-right: 100px;
            margin-top:70px;
        }
        #ordenar_buscar {
        	text-align: right;
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
    <c:choose>
    	<c:when test="${empty convite_list}">
			<h2>No hay convites disponibles en este momento</h2>
		</c:when>
		<c:otherwise>
			<div id="ordenar_buscar">
			    <form action="FiltrarConvitesServlet">
					<select name="selectFiltro">
						<c:choose>
							<c:when test="${selectedFilter == 2}">
								<option value="nombre"> Buscar por nombre </option>
								<option value="ciudad" selected> Buscar por ciudad </option>
								<option value="area"> Buscar por área </option>
							</c:when>
							<c:when test="${selectedFilter == 3}">
								<option value="nombre"> Buscar por nombre </option>
								<option value="ciudad"> Buscar por ciudad </option>
								<option value="area" selected> Buscar por área </option>
							</c:when>
							<c:otherwise>
								<option value="nombre" selected> Buscar por nombre </option>
								<option value="ciudad"> Buscar por ciudad </option>
								<option value="area"> Buscar por área </option>
							</c:otherwise>
						</c:choose>
					</select>
					<input type="text" name="filtro" id="filtro" placeholder="Busca tu convite">
					<button type="submit">Buscar</button>
				</form>
				<form action="OrdenarConvitesServlet">
					<select name="selectOrden">
						<c:choose>
							<c:when test="${selectedOrder == 2}">
								<option value="fecha"> Ordenar por fecha </option>
								<option value="precio_asc" selected> Ordenar por precio (ascendente) </option>
								<option value="precio_desc"> Ordenar por precio (descendente) </option>
							</c:when>
							<c:when test="${selectedOrder == 3}">
								<option value="fecha"> Ordenar por fecha </option>
								<option value="precio_asc"> Ordenar por precio (ascendente) </option>
								<option value="precio_desc" selected> Ordenar por precio (descendente) </option>
							</c:when>
							<c:otherwise>
								<option value="fecha" selected> Ordenar por fecha </option>
								<option value="precio_asc"> Ordenar por precio (ascendente) </option>
								<option value="precio_desc"> Ordenar por precio (descendente) </option>
							</c:otherwise>
						</c:choose>
					</select>
					<button type="submit">Ordenar</button>
				</form>
			</div>
		</c:otherwise>
	</c:choose>
	
	<c:forEach items="${convite_list}" var="convitei">
        <div>
		<h2>${convitei.nombre}</h2>
		<h4>Fecha: ${convitei.fecha}</h4>
		<h4>Ciudad: ${convitei.ciudad}</h4>
		<h4>Área: ${convitei.area}</h4>
		<h4>Precio por invitado: ${convitei.precioInvitado} €</h4>
		<h4>Descripción: ${convitei.descripcion}</h4>
	    </div>
	    <form action="MuestraConviteServlet">
	    	<input type="hidden" value="${convitei.idConvite}" name="idConvite">
        	<button type="submit" class="btn btn-success" id="btnSubmit">Ver detalles</button>
        </form>
    </c:forEach>