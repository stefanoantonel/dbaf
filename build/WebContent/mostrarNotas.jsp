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

$(document).on("ready",function(){

	//var not = ${notas};
	var notaAnt=$("#nota");
	
	$.each(not, function(indice,json){
		console.log(indice);

		var a1=notaAnt.clone();
		a1.attr("hidden",false);
		a1.attr("id",json.id);
		a1.attr("title","Creada: "+json.agregada+" Modificada: "+json.modificada+"");

		$("body").last().append(a1);			
		var titulo=$("#"+json.id).find("h4").text(json.value);//.filter("[class='modal-title']").text(json.value);
		//console.log("id json"+json.id);

	});
	

	
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
		console.log("item: "+item.value);
	$("#salida").html("Titulo: "+item.value);	
	},idElem); 
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
	
	
	

	 function autocompletar (elemento, url, minLetras, clickCallback, idElem){
		
		 var elem = $(elemento);
		console.log("elemento: "+elem.attr("id"));
		elem.keyup(function(evt){
			var idDiv="divAutocomplete"+idElem;
			if(elem.val().length>=minLetras){
				
				    if($("#"+idDiv))
				    	$("#"+idDiv).remove();

				var pos=elem.position();
				var top=pos.top+elem.outerHeight();
				var posDiv="width: "+elem.outerWidht+" px; top: "+top+" px; left: "+pos.left+" px";
				
				$.ajax({
					url:url,
					data:{ texto:elem.val() }, //texto que escribimos
					success: function (datos,status,jqXHR){
						console.log("datos ajax: "+datos);
					
						$("body").append('<div class="contenedorLista" style="display:none;'+posDiv+'" id="'+idDiv+'"><ul class="lista-autocompletar"></ul></div>');
						var ul=$("ul",$("#"+idDiv));
						
						
						$(datos).each(function(indice, objeto){
							//
							//$(datos).each(function(indice, objeto){
							var idItem=idDiv+indice+'item';
							ul.append('<li class="item_autocompletar" id="'+idItem+'">'+objeto.value+'</li>');
							
							$("#"+idItem).click(function(evento){
									
							
								if(clickCallback && typeof clickCallback !== "undefined")
									clickCallback(objeto);
								elem.val(objeto.value);
								$("#"+idDiv).hide(300);
								$("#"+idDiv).remove();
								evento.preventDefault();
								
							});
							
						});
						$("#"+idDiv).show();
						
					
					//console.log("datos "+ not);
					}
				});
				
			} else {

				if($("#"+idDiv))
			    	$("#"+idDiv).remove();
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