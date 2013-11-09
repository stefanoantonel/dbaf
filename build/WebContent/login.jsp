<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/ui-lightness/jquery-ui-1.10.3.custom.min.css"
	rel="stylesheet" media="screen">
<script src="css/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//$("#req").text(request.getAttribute(msj) );
		//alert($('#progreso').prop('name'));
		//$("#req").text("poo"); 
		//$("#req").ajaxStart(function(){ $(this).text("Ajax started"); });

		$("form").submit(function() {
			//alert("submit");
			$("#p").text("pepe");
			//alert($('#progreso').prop('name'));
			//$('#progreso').prop('name','juan');
			$('#progreso').css("display", "inline");
		});

		$("#registrarse").click(function() {
			$("#form").css("visibility", "visible");

		});
		//alert($("#scope").html());
	});
</script>

</head>
<body>

	<div id="scope" align="center">${requestScope.msj}</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<div align="center">
		<div>${requestScope.volver}</div>
		<br />
		<form action="login" method="post">
			<fieldset>
				<input type="text" placeholder="Escriba su nombre" name="usuario" /><br />
				<input type="password" placeholder="Escriba su clave" name="clave" />
				<br /> <input type="submit" value="Ingresar" />
			</fieldset>
		</form>
		<div id="req"></div>
		<div>
			<input id="registrarse" type="button" value="Crear una Cuenta">
		</div>

		<br /> <img id="progreso" src="css/img/lo.gif" name="pe"
			style="display: none">
		<div id="form" style="visibility: hidden;">
			<iframe src="ListaZona" width="400" height="400"></iframe>
		</div>
	</div>


</body>
</html>