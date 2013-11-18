<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="css/js/jquery-1.9.1.js"></script>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="./css/estilos.css" type="text/css" />
<script src="dist/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <jsp:include page="cabecera.jsp" />
</head>

<body>



<div align="center">
<div id="scope" align="center">${requestScope.msj}</div>
	<form action="updateUsuario">
	<div class="alert alert-error"> <a href="#" class="close" data-dismiss="alert">&times;</a>
		<strong>Su cuenta ha expirado. Por favor cambie su contraseña</strong> 
	</div>
		<br>
		<b>Cambio de Contraseña</b>
		<br/><br/><br/>
		<div>
			
			<input type="password"  class="btn btn-default" placeholder="Escriba su clave actual"  name="claveV" />
		</div>
		<br>
		<div>
			
			<input type="password" class="btn btn-default" placeholder="Escriba su nueva clave"  name="claveN1" />
			<br>
			<input type="password" class="btn btn-default" placeholder="Repita nueva clave"  name="claveN2" />
			
			
		</div>
		<br/>
		<input type="submit" class="btn btn-default" style="background:#63d2f8" value="Cambiar clave" />
	</form>
</div>

	
</body>
</html>