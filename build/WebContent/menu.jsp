<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Block de Notas</title>
<link rel="stylesheet" href="./css/estiloVerde.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="./css/js/bootstrap.min.js"></script>
<style type="text/css">
	.bs-example{
    	margin: 20px;
    }
</style>
<script type="text/javascript">


$(document).on("ready",function(){
	
	
	obtenerEstilo("-1");
	
	
	$("#lookFeel li").click(function() {
	    
		var estiloS= $(this).attr('id');
		//alert(estilo); // jQuery's .attr() method, same but more verbose
		
		obtenerEstilo(estiloS);
	    
	});

	 
});


function obtenerEstilo(estiloS)
{
	$.ajax({
		     url: "Estilos",
		     data:{estiloSeleccionado: estiloS},
		     success:function(datos,status,jqXHR){
		    	 
		    	 console.log("datos:"+datos);
		    	 var oldlink = document.getElementsByTagName("link").item("./css/estilo");
		    	 
		         var newlink = document.createElement("link");
		         newlink.setAttribute("rel", "stylesheet");
		         newlink.setAttribute("type", "text/css");
		         newlink.setAttribute("href", "./css/estilo"+datos+".css");

		         document.getElementsByTagName("head").item(0).replaceChild(newlink, oldlink);
		     }
	     });
}



</script>
</head>
<body>
<div class="bs-example">
  
            <ul role="navigation" class="nav" id="listaLookFeel">
               
                <li class="dropdown">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">Look & Feel <b class="caret"></b></a>
                    <ul class="dropdown-menu" id="lookFeel">
                        <li id="Rojo"><a>Rojo</a></li>
                        <li id="Verde"><a>Verde</a></li>
                        <li id="Amarillo"><a>Amarillo</a></li>
                        <li id="Naranja"><a>Naranja</a></li>
                        <li id="Blanco"><a>Blanco</a></li>
                        <li id="Negro"><a>Negro</a></li>
                        <li id="Violeta"><a>Violeta</a></li>
                    </ul>
                </li>
            </ul>
          
        </div>

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