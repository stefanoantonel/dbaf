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
	function settear() {

		var a = $("#r").val();
		alert(a);
		//request.setAttribute("resp","3");
		//request.setAttribute("successfulRegistration", "true");
		//requestScope.s

	}

	/* 
	$(document).on("ready",function(){
		$("#r").value="14";
		var a =$("#r");
		alert(a);
	}); */
</script>
<title>Crear Cuenta</title>


</head>

<div id="respuesta">${requestScope.req}</div>
<body>
	<div align="center">
		<form action=""></form>
		Ingrese Nombre: <input id="nombre" name="nombre" type="text" value="" style="display: list-item;" />
		Ingrese Clave: <input id="clave1" name="clave1" type="text" value="" style="display: list-item;" />
		Repita Clave: <input id="clave2" name="clave2" type="text" value="" style="display: list-item;"/>
		Ingrese Mail: <input id="mail" name="mail" type="text" value="" style="display: list-item;"/>
		  
		<!-- Tenemos que hacer un campo que sea para que el usuario tenga que cambiar la cuenta  -->
		
		<input type="button" onclick="settear()" />
	</div>
</body>
</html>