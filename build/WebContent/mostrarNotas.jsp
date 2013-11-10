<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
body {
	color: orange;
	text-align: justify;
}
</style>

<title>Block de Notas</title>
<script src="css/js/jquery-1.9.1.js"></script>
<script src="css/js/bootstrap.min.js"></script>
 <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  



<style type="text/css">
body{
text-align:center;
}
textarea {
	font-family: Verdana;
	display: block;
}
div.textarea{
	border-width:thin;
	border-style: solid;
	border-color: black;
	width: 20em;
	list-style: inherit;
}
</style>


<script type="text/javascript">

	
	function esLista(lista){
		//console.log("listaFunc= "+ lista);
		var lis="";
		if(lista!=null){
			if(lista=="1"){
				lis=" checked=\"checked\" ";
			}
			else
				lis="";
			
		}
		return lis;
	}
	

	$(document).on("ready",function(){
		
		
		
		var not = ${notas};
		//alert (persons);
		$.each(not, function(indice,json){
			
			console.log("value: "+json.value+" lista: "+json.lista);
			var comienzoDiv="<div style=\"vertical-align: top; color: light-red; border-style: dotted;\" >";
			var check="<input type=\"checkbox\" style=\"display:inline ;\" "+esLista(json.lista)+"/>";
			console.log("esLista: "+esLista(json.lista));
			var area="<textarea class=\"textarea\" id="+json.id+" wrap=\"soft\" title=\"Creada: "+json.agregada+" Modificada: "+json.modificada+"\" style=\"display:inline\"> "+json.value+"</textarea>";
			//var area="<div contenteditable=\"true\" class=\"textarea\" id="+json.id+" wrap=\"hard\" title=\"Creada: "+json.agregada+" Modificada: "+json.modificada+"\" style=\"display:inline\"> "+json.value+"</div>";
			var boton="<input class=\"botonEliminar\" value=\"Elliminar\" type=\"button\" />";
			var enter="<div style=\"display:block;\"/>";
			var finDiv="</div><br/>";
			
			
			$("body").last().append(comienzoDiv+check+area+boton+enter+finDiv);
			//console.log(json.id);
			//console.log(json.value);
		});
		
		$("textarear").tooltip({
			show: {
		        effect: "slideDown",
		        delay: 2500,
			}
		});
		/* var cost = ${notas};
	    var costarr = $.parseJSON(cost);
	    $.each(cost, function(i, item) {
	       console.log(item.id);
	    }); */
		//var notasCompletas=$("#scope").text();
		//var notasCompletas=$.getJSON($("#scope").text());
		//alert(notasCompletas);
		/* alert(len=notasCompletas.size());
		for (var i = 0; i < len; i++) {
			  element = notasCompletas[i];
		} */
		//$.each(notasCompletas, function(i,notas) { //i es la posiscion pero del arreglo :(
			//alert(notas);
			/* $.each(notas, function(k,v){
				alert(k);
			}); */
       	//});  
		
       	
       	
       	
		
		/* $.each(notasCompletas,function(notas) {
			alert(notas);
			$.each(notas,function(d){
				alert(d);
				$("body").last().append("<textarea> "+d+"</textarea >");
			});
			//$("body").last().append("<textarea> "+data+"</textarea >");
			//alert( index + ": " + value );
		}); */ 
		 
		
		//$("body").last().append("<div style='display:inblock;'>estoy penultimo!</div>");
		//$("body").last().append("<div style='display:block;;color: blue;'>estoy al ultimo!</div>");
		
		 
		//alert($("#scope").html());	
		/* $("div").click(function(){
			var a=$(this).text();
			alert(a);
		}); */
		
		$("#agregar").click(function(){
			$.ajax({
	   		     url: "CrearNota",
	   		     success:function(datos,status,jqXHR){
						//cuadno no le envian nada funciona como get y sino como set.
						console.log("Nota creada");
						$.get('GetNotas', function(data) {
					        $("body").html(data);
					    });
						
						/* var a=$("#contenido").html();
						$("#contenido").html(a+datos); */
	   		    	
	   		     }
  		     });
		});
		
		
		$("input.botonEliminar").click(function(){
			//alert($(this).prev().val());
			var id=$(this).prev().attr("id");
			console.log("id nota prev"+id);
			 $.ajax({
	   		     url: "EliminarNota?id="+id+"",
	   		     success:function(datos,status,jqXHR){
						//cuadno no le envian nada funciona como get y sino como set.
						console.log("Nota eliminada");
						
						
						//var a=$("#contenido").html();
						//$("#contenido").html(a+datos);
	   		    	
	   		     }
 		     });
 		     
			
		});
		
		$("textarea").mousedown(function(event) {
			if(event.which==3){ //click derecho
				 
			}
		});
		
		$(".textarea").blur(function() {
			var notaCamb=$(this).val();
			var notaId=$(this).attr("id");
			//console.log("toco el area= "+$(this).val());
			//HttpSession session = request.getSession();
			//session.setAttribute("object", object);
			//session.setAttribute("cambio", $(this).val());
			$("#cambio").val($(this).val());
			$.ajax({
	   		     url: "GuardaNota?nota="+notaCamb+"&id="+notaId+"",
	   		     success:function(datos,status,jqXHR){
						//cuadno no le envian nada funciona como get y sino como set.
						console.log("Nota guardada");
						/* var a=$("#contenido").html();
						$("#contenido").html(a+datos); */
	   		    	
	   		     }
   		     });
		});
		$(document).css("text-align", "center");//???
				
				
				
		$("input:checkbox").click(function(){
			var estado=$(this).is(":checked");
			var id=$(this).next().attr("id");
			console.log("cambio: "+estado+" id:"+id);
			estado = (estado==true) ? "1" : "0";
			console.log("dsp if estado:"+estado);
			$.ajax({
	   		     url: "GuardarNotaLista?lista="+estado+"&id="+id+"",
	   		     success:function(datos,status,jqXHR){
						//cuadno no le envian nada funciona como get y sino como set.
						console.log("Nota realizada");
						/* var a=$("#contenido").html();
						$("#contenido").html(a+datos); */
	   		    	
	   		     }
   		     });
			
		});
	});
</script>


</head>
<body>
	<div align="center" id="scope" style="display: none; color: red; border-style: solid;">
		${sessionScope.notas}
	</div>
	

	<div align="center" style="vertical-align:top;  "> 
		
		<input id="agregar" type="button" value="Agregar Nota" style="display:block;" align="middle"/>
		<br/>
	</div>
	
	
	
	
</body>
</html>