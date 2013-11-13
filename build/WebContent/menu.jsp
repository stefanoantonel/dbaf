<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MENU</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="css/ui-lightness/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" media="screen"> 
</head>
<body>
	<div>${requestScope.eliminado}</div><br/>
	<div align="center"><b>${requestScope.mnjActivado}</b></div><br/>
	<br/><br/>
	<div align="center" >
		<!-- <a href="agregarCliente.jsp">Agregar Cliente</a> -->
		<!--  <a href="AgregarCliente">Agregar Cliente</a><br/>-->
		<a style="color: red;" href="ListaZona" class="btn btn-info btn-large pink" >Agregar Cliente</a><br/><br/>
		<!-- <a style="color: red;" href="filtraListaCliente.html" class="btn  btn-info btn-large">Eliminar Cleinte </a><br/><br/> -->
		<a style="color: red;" href="ListaCliente" class="btn  btn-info btn-large">Eliminar Cleinte </a><br/><br/>
		<a style="color: red;" href="demoJQueryUI.html" class="btn btn-info btn-large pink" >Buscar Cliente</a><br/><br/>
		<a style="color: red;" href="login.jsp" class="btn btn-info  btn-large">Login</a><br/><br/>
		<a style="color: red;" href="ListaClienteJNDI" class="btn btn-info  btn-large">Mostrar Lista Cliente</a><br/><br/>
		<a style="color: red;" href="getNotas" class="btn btn-info  btn-large">Mostrar Notas Guardadas</a><br/><br/>
	</div>
</body>
</html>