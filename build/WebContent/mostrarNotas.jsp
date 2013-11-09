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



<style type="text/css">
body{
text-align:center;
}
textarea {
	font-family: Verdana;
	display: block;
}
</style>


<script type="text/javascript">
	$(document).on("ready",function(){
		
		
		
		var not = ${notas};
		//alert (persons);
		$.each(not, function(indice,json){
			
			$("body").last().append("<textarea id="+json.id+"> "+json.value+"</textarea >");
			console.log(json.id);
			console.log(json.value);
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
		$("div").click(function(){
			var a=$(this).text();
			alert(a);
		});
		
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
		
		$("textarea").blur(function() {
			var notaCamb=$(this).val();
			var notaId=$(this).attr("id");
			console.log("toco el area= "+$(this).val());
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
	});
</script>


</head>
<body>
	<div align="center" id="scope"
		style="display: list-item; color: red; border-style: solid;">
		${sessionScope.notas}
	</div>
	<div>
	<input type="checkbox" style="display:inline ;"/>
	<textarea rows="" cols="" style="display: inline;">area </textarea>
	</div>
	<input id="agregar" type="button" value="Agregar Nota"/>
	
</body>
</html>