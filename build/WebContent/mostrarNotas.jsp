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

var not = ${sessionScope.notas}
var notaTemplate;

$(document).on("ready",function(){

	notaOriginal=$("#nota");
	notaTemplate=notaOriginal.clone();
	//var not = ${notas};
	
	armarNota(not);

	$("#agregar").click(function(){

		$.ajax({
   		     url: "CrearNota",
   		     success:function(datos,status,jqXHR){
					
   		    		console.log("Nota creada");
					$.get('getNotas', function(data) {
						//var a=$("body").html();
						console.log("agregar ajax"+data);
	    		    	$("body").html(data);
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
	
	 var idElem= Math.ceil(Math.random()*1000000);
	autocompletar("#texto","getNotas",2,function(item)
			{
				armarNota(item);
			}
	    ,idElem); 
});
	
	
	
	function esLista(lista){

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
	
	
	 function armarNota(not)
	 {
		 var notaAnt=notaTemplate;
		 console.log("nota Template: "+notaAnt);
		 $.each(not, function(indice,json){
				console.log(indice);
				
				var a1=notaAnt.clone();
				a1.attr("hidden",false);
				a1.attr("id",json.id);
				a1.attr("title","Creada: "+json.agregada+" Modificada: "+json.modificada+"");

				$("body").last().append(a1);
				
				$("#"+json.id).find("h4").text(json.value);
				console.log("jason id: "+json.id);
				console.log("jason value: "+json.value);
				//.filter("[class='modal-title']").text(json.value);
				//console.log("id json"+json.id);

			});
	 }
	
	

	 function autocompletar (elemento, url, minLetras, clickCallback, idElem){
		
		 var elem = $(elemento);
		//console.log("elemento: "+elem.val());
		elem.keyup(function(evt){
			//var idDiv="divAutocomplete"+idElem;
			console.log("elemento: "+elem.val());
			$(".nota").remove();
			if(elem.val().length>=minLetras){
				
				$.ajax({
					url:url,
					data:{ texto:elem.val() }, //texto que escribimos
					success: function (datos,status,jqXHR){
						console.log("datos ajax: "+datos);
					
						//$(".nota").empty();
						$(".nota").remove();

								if(clickCallback && typeof clickCallback !== "undefined")
								{	console.log("datos antes de la funcion: "+datos);
									clickCallback(datos);
								}	

					}
				});
				
			}
			else{
				$.ajax({
					url:url,
					data:{texto:null }, //texto que escribimos
					success: function (datos,status,jqXHR){
						console.log("datos ajax: "+datos);
					
						//$(".nota").empty();
						$(".nota").remove();

								if(clickCallback && typeof clickCallback !== "undefined")
								{	console.log("datos antes de la funcion: "+datos);
									clickCallback(datos);
								}	

					}
				});
			}

			
			
		}); 
	 

	 }
	
	
</script>


</head>
<body>
	<div align="center" id="scope" style="display: none; color: red; border-style: solid;">
		${sessionScope.notas}
	</div>
	

	<div align="center" style="vertical-align:top;  "> 
		
		<input id="agregar" class="btn" type="button" value="Agregar Nota" style="display:block;" align="middle" name="11" />
		<br/>
	</div>

	<div>   <!-- AUTOCOMPLETAR              -->
		<input type="text" id="texto" />
		<div id="salida"></div>
	</div>
	<div  class ="centrar nota" id="nota" hidden="true" >

	
		    <div class="modal-content">
		     
		      <div class="modal-header">
		      	<input type="checkbox" class="checkbox" aria-hidden="true" />
		        <button type="button"  class="cruz close botonEliminar " data-dismiss="modal" aria-hidden="true">&times;</button>

		        <h4 class="textarea modal-title" class="" name="aa" id="titulo" contenteditable="true" >Modal title</h4>
		      </div>
		      <div class="modal-body" id="cuerpo" contenteditable="true">
		        <p>One fine body&hellip;</p>
		      </div>
		     
		    </div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
		
</body>
</html>