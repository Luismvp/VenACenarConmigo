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
    <title>Nuevo convite</title>
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
        form{
        	margin-top:70px;
        	text-align: center;
        }
    </style>
</head>
<body>
<%@include file="templates/navBarLoged.jsp" %>
<form id="loginForm" action="NuevoConviteServlet">
        <div class="form-group" id="user-group">
            <label for="nombre">Nombre del convite: </label>
            <br>
            <input type="text" name="nombre" class="form-control" id="nombre" placeholder="introduce el nombre del convite">
        </div>
        <div class="form-group" id="date-group">
            <label for="fecha">Fecha del convite: </label>
            <br>
            <input type="text" name="fecha" class="form-control" id="fecha" placeholder="introduce la fecha del convite">
        </div>
        <div class="form-group" id="time-group">
            <label for="horaComienzo">Hora de inicio del convite: </label>
            <br>
            <input type="text" name="horaComienzo" class="form-control" id="horaComienzo" placeholder="introduce la hora de inicio">
        </div>
        <div class="form-group" id="timeEnd-group">
            <label for="horaFin">Hora de finalización del convite: </label>
            <br>
            <input type="text" name="horaFin" class="form-control" id="horaFin" placeholder="introduce la hora de finalización">
        </div>
        <div class="form-group" id="rest-group">
            <label for="restaurante">Nombre del restaurante: </label>
            <br>
            <input type="text" name="restaurante" class="form-control" id="restaurante" placeholder="introduce el nombre del restaurante (si procede)">
        </div>
        <div id="menu">
            <div class="form-group" id="menu-group">
                <label for="menu">Menú Propuesto: </label>
                <br>
                <textarea cols="152" rows="7" placeholder="Escribe aquí el menu propuesto" name="menu" id="menu"></textarea>
            </div>
        </div>
        <br>
        <div class="form-group" id="invitados-group">
            <label for="numInvitados">Número de invitados:</label>
            <br>
            <input type="text" name="numInvitados"  max="15" min="0" class="form-control" id="numInvitados" placeholder="introduce el número de invitados">
        </div>
        <div class="form-group" id="precio-group">
            <label for="precioInvitado">Precio por invitado:</label>
            <br>
            <input type="text" name="precioInvitado"  min="0" class="form-control" id="precioInvitado" placeholder="introduce el precio de la comida por invitado">
        </div>
        <div class="form-group" id="descripcion-group">
            <label for="descripcion">Descripción de la temática del convite:</label>
            <br>
            <textarea cols="152" rows="7" placeholder="Introduce una descripción de la temática del convite" id="descripcion" name="descripcion"></textarea>
        </div>
        <div class="form-group" id="ciudad-group">
            <label for="ciudad">Ciudad:</label>
            <br>
            <input type="text" name="ciudad" class="form-control" id="ciudad" placeholder="introduce la ciudad donde se celebrará el convite">
        </div>
        <div class="form-group" id="distrito-group">
            <label for="area">Distrito:</label>
            <br>
            <input type="text" name="area" class="form-control" id="area" placeholder="introduce el distrito donde se celebrará el convite">
        </div>
        <button type="submit" class="btn btn-success" id="btnSubmit">Enviar <span class="glyphicon glyphicon-arrow-right"></span></button>
        </fieldset>
    </form>
</body>
</html>