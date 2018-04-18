<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Registro</title>
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
    </style>
</head>
<body>
<%@ include file = "templates/navBar.jsp" %>
<div id="Registro" style="padding-left: 100px; padding-right: 100px">
    <h1>Página de registro</h1>
    <h2>Introduce los datos en el formulario:</h2>
<%@ include file= "formularios/formularioRegistro.jsp" %>
</div>
</body>
</html>