<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Block de Notas</title>
<script src="css/js/jquery-1.9.1.js"></script>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="./css/estilos.css" type="text/css" />
<script src="dist/js/bootstrap.min.js"></script>

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
		var notaAnt=$("#nota");
		var idd=30;
		$.each(not, function(indice,json){
			console.log(indice);
			/* console.log("value: "+json.value+" lista: "+json.lista);
			var comienzoDiv="<div style=\"vertical-align: top; color: light-red; border-style: dotted;\" >";
			var check="<input type=\"checkbox\" style=\"vertical-align: middle;\" "+esLista(json.lista)+"/>";
			console.log("esLista: "+esLista(json.lista));
			var area="<textarea class=\"textarea\" id="+$('#yourElementId').prop('title', 'your new title');+" title=\"Creada: "+json.agregada+" Modificada: "+json.modificada+"\" style=\"display:inline\"> "+json.value+"</textarea>";
			//var area="<div contenteditable=\"true\" class=\"tooltip\" class=\"textarea\" id="+json.id+" wrap=\"hard\" title=\"Creada: "+json.agregada+" Modificada: "+json.modificada+"\" style=\"display:inline\"> "+json.value+"</div>";
			//var boton="<input class=\"btn  btn-info btn-large\" class=\"botonEliminar\" value=\"Elliminar\" type=\"button\" />";
			var boton="<button title=\"Eliminar nota\"  class=\"btn\" type=\"button\" class=\"botonEliminar\" class=\"close\" >&times;</button>";
			
			var enter="<div style=\"display:block;\"/>";
			var finDiv="</div><br/>"; */
			
			//$("body").last().append(comienzoDiv+check+area+boton+enter+finDiv);<<<<<<< HEAD
			//$("#bloque").append($("#nota"));
			//console.log($("#agregar").attr("id"," "+indice+" "));
			//$("body").last().append($("#agregar").attr("id"," "+indice+" "));
			var a1=notaAnt.clone();
			a1.attr("hidden",false);
			a1.attr("id",json.id);
			a1.attr("title","Creada: "+json.agregada+" Modificada: "+json.modificada+"");
			//$(a1.modal-titl)
			
			/*  esLista(json.lista)*/
			
			//request.getServletContext().setAttribute("usuarioActualId", usuario.getId());
			$("body").last().append(a1);
			//notaAnt=$("body").last();
			//console.log(a1);
			
			var titulo=$("#"+json.id).find("h4").text(json.value);//.filter("[class='modal-title']").text(json.value);
			console.log("id json"+json.id);
			//$("#"+json.id).find("h4").attr("id",json.id);
			//console.log($("#"+json.id+"").text());
			
			//+esLista(json.lista)+"
			/* 
=======
			
			
			$("#bloque").append("<iframe src=\"./nota.jsp\"></iframe> </br>");
			
		//	$('#nota').attr('id', 'first6');
		//	$("#aa").text("text");
		
			$('#nota').each(function(){
				  if(this.id){
				    this.id = this.id+"1";
				  }
				});
>>>>>>> cdee02d51274b480d2c07993f21ba6866594c6bd */
		});
		

		
		$("#agregar").click(function(){
			
		//	$('#myModal').modal(options);
			
			$.ajax({
	   		     url: "CrearNota",
	   		     success:function(datos,status,jqXHR){
						//cuadno no le envian nada funciona como get y sino como set.
						console.log("Nota creada");
						$.get('GetNotas', function(data) {
							var a=$("#bloque").html();
		    		    	$("#bloque").html(a+datos);
					    });
	   		     }
  		     });
		});
		
		
		$(".botonEliminar").click(function(){
			//alert($(this).prev().val());
			var id=$(this).closest(".nota").attr("id");
			console.log("id nota prev"+id);
			$('#'+id).remove();
			 $.ajax({
	   		     url: "EliminarNota?id="+id+"",
	   		     success:function(datos,status,jqXHR){
						//cuadno no le envian nada funciona como get y sino como set.
						console.log("Nota eliminada");
	   		     }
 		     });
 		     
			
		});
		
		
		
		$(".textarea").blur(function() {
			var notaCamb=$(this).text();
			console.log("nota cambiada"+notaCamb);
			//var notaId=$(this).attr("id");
			var notaId=$(this).closest(".nota").attr("id");
			//$(this).parent(".nota");
			console.log("id nota "+notaId);
			$("#cambio").val($(this).val());
			$.ajax({
	   		     url: "GuardaNota?nota="+notaCamb+"&id="+notaId+"",
	   		     success:function(datos,status,jqXHR){
						console.log("Nota guardada");
	   		     }
   		     });
		});
				
		
		$("input:checkbox").click(function(){
		
			var estado=$(this).is(":checked");
			var id=$(this).next().attr("id");
			console.log("cambio: "+estado+" id:"+id);
			estado = (estado==true) ? "1" : "0";
			console.log("dsp if estado:"+estado);
			$.ajax({
	   		     url: "GuardarNotaLista?lista="+estado+"&id="+id+"",
	   		     success:function(datos,status,jqXHR){
						
						console.log("Nota realizada");
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
		
		<input id="agregar" class="btn" type="button" value="Agregar Nota" style="display:block;" align="middle" name="11" />
		<input id="agregar" class="btn" type="button" value="Agregar Nota" style="display:block;" align="middle" name="22"/>
		<br/>
	</div>


	<div  class ="nota j centrar " id="nota" hidden="true" >
	
		    <div class="modal-content">
		      <div class="modal-header">
		      	<input type="checkbox" class="check" aria-hidden="true" style="vertical-align: middle"; />
		        <button type="button"  class="close botonEliminar" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="textarea modal-title" class="" name="aa" id="titulo" contenteditable="true" >Modal title</h4>
		      </div>
		      <div class="modal-body" id="cuerpo" contenteditable="true">
		        <p>One fine body&hellip;</p>
		      </div>
		     
		    </div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
		
</body>
</html>