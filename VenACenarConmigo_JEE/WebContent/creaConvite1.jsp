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
<%@include file="templates/navBar.jsp" %>
    <div id="seleccion">
        <h1> ¿Dónde quieres que se celebre el convite?</h1>
        <p><a href="conviteDom.jsp" class="btn btn-success fontLobster" style="font-size: larger"  role="button">En mi domicilio</a></p>
        <p><a href="conviteRest.jsp" class="btn btn-success fontLobster" style="font-size: larger"  role="button">En un restaurante</a></p>
    </div>
</body>
</html>