<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<div align="center">
<div id="scope" align="center">${requestScope.msj}</div>
	<form action="updateUsuario">
		<br/><br/><br/>
		<div>
			Escriba la clave antigua:
			<input type="password" placeholder="Escriba clave antigua"  name="claveV" />
		</div>
		<div>
			Escriba su clave nueva
			<input type="password" placeholder="Escriba su nueva clave"  name="claveN1" />
			<input type="password" placeholder="Repita clave nueva"  name="claveN2" />
			<input type="submit" value="Cambiar clave" />
		</div>
	</form>
</div>

	
</body>
</html>