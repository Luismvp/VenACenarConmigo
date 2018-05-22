<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="EliminaConviteServlet">
<input type="text" name="id" id="id" placeholder="introduce el id del convite a eliminar">
<button type="submit" class="btn btn-success" id="btnSubmit">Enviar<span class="glyphicon glyphicon-arrow-right"></span></button>
</form>
</body>
</html>