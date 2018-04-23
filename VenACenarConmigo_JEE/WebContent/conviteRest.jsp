<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Crear Convite</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            text-align: center;
            padding-left: 100px;
            padding-right: 100px;
            padding-top: 70px;
        }
        a{
            color: whitesmoke;
        }
    </style>
</head>
<body>
<%@include file="templates/navBarLoged.jsp" %>
<div id="Domicilio">
    <h1>Convite en restaurante</h1>
    <form action="NuevoConviteServlet">
        <div class="form-group" id="user-group">
            <label for="name">Nombre del convite: </label>
            <br>
            <input type="text" name="nombre" class="form-control" id="name" placeholder="introduce el nombre del convite">
        </div>
        <div class="form-group" id="date-group">
            <label for="date">Fecha del convite: </label>
            <br>
            <input type="date" name="fecha" class="form-control" id="date">
        </div>
        <div class="form-group" id="time-group">
            <label for="time">Hora de inicio del convite: </label>
            <br>
            <input type="time" name="horaInicio" class="form-control" id="time">
        </div>
        <div class="form-group" id="timeEnd-group">
            <label for="timeEnd">Hora de finalización del convite: </label>
            <br>
            <input type="time" name="horaFin" class="form-control" id="timeEnd">
        </div>
        <div class="form-group" id="rest-group">
            <label for="rest">Nombre del restaurante: </label>
            <br>
            <input type="text" name="rest" class="form-control" id="rest" placeholder="introduce el nombre del restaurante">
        </div>
        <div id="menu">
            <div class="form-group" id="menu-group">
                <label for="menu">Menú Propuesto: </label>
                <br>
                <textarea cols="152" rows="7" name="menu" placeholder="Escribe aquÃ­ el menu propuesto"></textarea>
            </div>
        </div>
        <br>
        <div class="form-group" id="interes-group">
            <label>Gustos relacionados con la temática del convite: </label>
            <p>
                <label for="musica">MÃºsica</label>
                <input type="checkbox" name="interes" id="musica">
                <label for="deporte">Deportes</label>
                <input type="checkbox" name="interes" id="deporte">
                <label for="arte">Arte</label>
                <input type="checkbox" name="interes" id="arte">
                <label for="politica">PolÃ­tica</label>
                <input type="checkbox" name="interes" id="politica">
                <br>
                <label for="cine">Cine</label>
                <input type="checkbox" name="interes" id="cine">
                <label for="literatura">Literatura</label>
                <input type="checkbox" name="interes" id="literatura">
                <label for="gastronomia">GastronomÃ­a</label>
                <input type="checkbox" name="interes" id="gastronomia">
                <label for="tecnologia">TecnologÃ­a</label>
                <input type="checkbox" name="interes" id="tecnologia">
                <br>
            </p>
        </div>
        <div class="form-group" id="invitados-group">
            <label for="invitados">Número de invitados:</label>
            <br>
            <input type="number" name="invitados"  max="15" min="0" class="form-control" id="invitados" placeholder="introduce el nÃºmero de invitados">
        </div>
        <div class="form-group" id="precio-group">
            <label for="precio">Precio por invitado:</label>
            <br>
            <input type="text" name="precio"  min="0" class="form-control" id="precio" placeholder="introduce el precio de la comida">
        </div>
        <div class="form-group" id="descripcion-group">
            <label for="descripcion">Descripción de la temática del convite:</label>
            <br>
            <textarea cols="152" rows="7" placeholder="Introduce una descripciÃ³n de la temÃ¡tica del convite" id="descripcion"></textarea>
        </div>
        <div class="form-group" id="ciudad-group">
            <label for="ciudad">Ciudad:</label>
            <br>
            <input type="text" name="ciudad" class="form-control" id="ciudad" placeholder="introduce la ciudad donde se celebrarÃ¡ el convite">
        </div>
        <div class="form-group" id="distrito-group">
            <label for="distrito">Distrito:</label>
            <br>
            <input type="text" name="distrito" class="form-control" id="distrito" placeholder="introduce el distrito donde se celebrarÃ¡ el convite">
        </div>
        <button type="submit" class="btn btn-success" id="btnSubmit">Enviar <span class="glyphicon glyphicon-arrow-right"></span></button>
        </fieldset>
    </form>
</div>
</body>
</html>
