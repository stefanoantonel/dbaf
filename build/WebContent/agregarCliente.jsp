<%@page import="ar.edu.ucc.bda.web.servlets.ListaZona"%>
<%@page import="ar.edu.ucc.bda.web.modelo.Cliente"%>
<%@page import="ar.edu.ucc.bda.web.servlets.AgregarCliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script language="text/javascript" type="text/javascript">

function showSelected()
{
	var lista=document.getElementById("listaZona");
	var valorLista=lista.options[lista.selectedIndex].value;
	//alert("seleccion "+valorLista);
	document.getElementById("oculto").value=valorLista;
	return true;//para que se haga el action del form.
}

</script>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/ui-lightness/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" media="screen"> 
</head>

<body>

<!--  <form action="AgregarCliente"   method="post"> <!--a este filtro se lo queremos enviar a lista clientes, en action va la url -->
<form action="AgregarCliente"   method="post" class="navbar-form pull-center"> <!--a  -->
		<div align="center">
		<a  href="menu.jsp">Volver al Menu</a> 
		</div>
		<br/>
		<div>${requestScope.msj}</div>  <!--Es de donde saco la variable para mostrar. en la variable del request.  -->
		
		
		<div  align="center">Usuario Acual: ${sessionScope.usuario.nombre}</div>  <!-- es como que llamo al metodo get usuario -->
		<br/>
		<div align="center" style="size: 10em">
			<div style="border:10px solid black; background-color:orange; font-size: 2em; display: inline-block;" align="center" >
			<br/>
				<label for="parametroNombre">Nombre:</label>
				<input type="text" id="parametroNombre" name="parametroNombre" />
				<br/><br/>
				<label for="listaZona">Id Zona:</label>
				<!-- <input type="text" id="parametroIdZona" name="parametroIdZona" />
				 -->
				 ${sessionScope.zonas} <!-- para que me lo busque en unoo mas grande -->
			
				<br/>
				<input id="oculto" name="oculto" type="text" style="display: none" value=""/>
				<br/>
				<label for="parametroHabilitado">Habilitar Cuenta:</label>
				

				<input type="radio" id="parametroHabilitado" name="parametroHabilitado" value="1"/>Si
				<input type="radio" id="parametroHabilitado" name="parametroHabilitado" value="0"/>No
				<br/>
				
				<input type="submit" value="Guardar" onclick="showSelected(); "/>
			</div>
		</div>
	</form>
</body>
</html>