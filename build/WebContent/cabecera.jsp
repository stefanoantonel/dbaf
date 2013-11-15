<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/estiloVerde.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/estilos.css" type="text/css" />
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
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
<div class="cabeceraPrincipal">
</br>
<div class="tituloArriba">
Mis notas
</div>
<div class="bs-example" align="left" style="width: 200px">
            <ul role="navigation" class="nav" id="listaLookFeel">
                <li class="dropdown">
                    <a href="#" data-toggle="dropdown" class=" dropdown-toggle">Look & Feel <b class="caret"></b></a>
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
 </div>
</body>
</html>