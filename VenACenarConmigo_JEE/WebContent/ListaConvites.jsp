<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ListaConvites</title>
</head>
<body>
<table border="1">
<tr>
<th>IDConvite</th>
<th>Nombre</th>
<th>Anfitrión</th>
<th>Fecha</th>
<th>Hora de comienzo</th>
<th>Hora de fin</th>
<th>Restaurante</th>
<th>Menú</th>
<th>Temas de conversación</th>
<th>Número máximo de invitados</th>
<th>Precio por invitado</th>
<th>Ciudad</th>
<th>Área</th>
</tr>

<c:forEach items="${convite_list}" var="convitei">
	<tr>
	<td> ${convitei.iDConvite} </td>
	<td> ${convitei.nombre} </td>
	<td> ${convitei.anfitrion.email} </td>
	<td> ${convitei.fecha}</td>
	<td> ${convitei.horaComienzo}</td>
	<td> ${convitei.horaFin}</td>
	<td> ${convitei.restaurante}</td>
	<td> ${convitei.menu}</td>
	<td> ${convitei.temasConversacion}</td>
	<td> ${convitei.maxInvitados}</td>
	<td> ${convitei.precioInvitado}</td>
	<td> ${convitei.descripcion}</td>
	<td> ${convitei.ciudad}</td>
	<td> ${convitei.area}</td>
	
	</tr>
</c:forEach>

</table>
</body>
</html>