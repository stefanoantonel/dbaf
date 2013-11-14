<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Block de Notas</title>
<jsp:include page="cabecera.jsp" />
</head>
<body>

<div id="bloquePrincipal" nombre="bloquePrincipal">
	<div>${requestScope.eliminado}</div><br/>
	<div align="center"><b>${requestScope.mnjActivado}</b></div><br/>
	<br/><br/>
	<div align="center" >
		<!-- <a href="agregarCliente.jsp">Agregar Cliente</a> -->
		<!--  <a href="AgregarCliente">Agregar Cliente</a><br/>-->
		<a href="ListaZona" class="btnPrincipal" >Agregar Cliente</a><br/><br/>
		<!-- <a style="color: red;" href="filtraListaCliente.html" class="btn  btn-info btn-large">Eliminar Cleinte </a><br/><br/> -->
		<a  href="ListaCliente" class="btnPrincipal">Eliminar Cleinte </a><br/><br/>
		<a  href="demoJQueryUI.html" class="btnPrincipal" >Buscar Cliente</a><br/><br/>
		<a  href="login.jsp" class="btnPrincipal">Login</a><br/><br/>
		<a  href="ListaClienteJNDI" class="btnPrincipal">Mostrar Lista Cliente</a><br/><br/>
		<a  href="getNotas" class="btnPrincipal">Notas Guardadas</a><br/><br/>
	</div>
</div>	
</body>
</html>