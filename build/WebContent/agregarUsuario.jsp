<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="css/js/jquery-1.9.1.js"></script>
<script src="css/js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/ui-lightness/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" media="screen">


<script type="text/javascript">
	

</script>
<title>Crear Cuenta</title>


</head>

<div id="respuesta">${requestScope.req}</div>
<body>

	<div align="center">
		<div id="scope" align="center">${requestScope.msj}</div>
		<br />
		<br />
		<form action="agregarUsuario"  method="post">
			Nombre: <input id="nombre" name="nombre" type="text" value="" style="display: list-item;" />
			Clave: <input id="clave1" name="clave1" type="password" value="" style="display: list-item;" />
			Repita Clave: <input id="clave2" name="clave2" type="password" value="" style="display: list-item;"/>
			Mail: <input id="mail" name="mail" type="text" value="" style="display: list-item;"/>
			  
			<!-- Tenemos que hacer un campo que sea para que el usuario tenga que cambiar la cuenta  -->
			
			<input type="submit" value="Agregar" />
		</form>
	</div>
</body>
</html>