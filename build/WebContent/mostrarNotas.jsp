<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Block de Notas</title>

 

<!--

<script src="css/js/jquery-1.9.1.js"></script>

 script src="css/js/jquery-1.9.1.js"></script>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="./css/estilos.css" type="text/css" />
<script src="dist/js/bootstrap.min.js"></script>
 -->

 <jsp:include page="cabecera.jsp" />
 
<script type="text/javascript">

var not = ${sessionScope.notas}
var notaTemplate;

$(document).on("ready",function(){

	notaOriginal=$("#nota");
	notaTemplate=notaOriginal.clone();
	//console.log("inicializado");
//	 var jq = $.noConflict();
	// $("#datetimepicker4").Datepicker();

	 //$('.datepicker').datepicker()

	 
/* 
	$(function() {
	    $( "#radio" ).buttonset();
	  });
	  */
	armarNota(not);
	asignarAgregar();
	asignarFiltro();
	asignarTodo();
	
	var idElem= Math.ceil(Math.random()*1000000);
	autocompletar("#texto","getNotas",2,function(item){	armarNota(item); } ,idElem); 
	
	$("#lookFeel li").click(function() {
		    
			var estilo= $(this).attr('id');
		    var oldlink = document.getElementsByTagName("link").item("./css/estilo");
	        var newlink = document.createElement("link");
	        newlink.setAttribute("rel", "stylesheet");
	        newlink.setAttribute("type", "text/css");
	        newlink.setAttribute("href", "./css/estilo"+estilo+".css");
	        document.getElementsByTagName("head").item(0).replaceChild(newlink, oldlink);
		    
	});
	
	$( "#datepicker" ).change(function(){
		  console.log($(this).val());
	});
	$(function() {
	    $( "#datepicker" ).datepicker();
	});
});
	
	


	function esLista(lista){
		if(lista!=null){
			if(lista=="1"){
				return true;
			}
			else
				return false;
		}
	}
	
	function asignarTodo(){
		asignarTextarea();
		asignarCheckbox();
		//asignarAgregar();
		asignarEliminar();
		ordenarListas();
		
	}
	
	function asignarTextarea(){
		$(".textareaTitulo").blur(function() {
			var notaCamb=$(this).text();
			var notaId=$(this).closest(".nota").attr("id");
			//console.log("cambio: "+notaCamb+" id:"+notaId);
			$("#cambio").val($(this).val());
			$.ajax({
	   		     url: "GuardaNota?titulo="+notaCamb+"&id="+notaId+"",
	   		     success:function(datos,status,jqXHR){
						console.log("Nota guardada");
	   		     }
			});
		});
		
		$(".textareaCuerpo").blur(function() {
			var notaCamb=$(this).text();
			var notaId=$(this).closest(".nota").attr("id");
			//console.log("cambio: "+notaCamb+" id:"+notaId);
			$("#cambio").val($(this).val());
			$.ajax({
	   		     url: "GuardaNota?cuerpo="+notaCamb+"&id="+notaId+"",
	   		     success:function(datos,status,jqXHR){
						console.log("Nota guardada");
	   		     }
			});
		});
	}
	
	function asignarCheckbox(){
		$("input:checkbox").click(function(){
			var nota=$(this).closest(".nota");
			var estado=$(this).is(":checked");
			var id=$(this).closest(".nota").attr("id");
			//console.log("cambio: "+estado+" id:"+id);
			estado = (estado==true) ? "1" : "0";
			//console.log("GuardarNotaLista?lista="+estado+"&id="+id+"");
			$.ajax({
	   		     url: "GuardarNotaLista?lista="+estado+"&id="+id+"",
	   		     success:function(datos,status,jqXHR){
						console.log("Nota realizada");
	   		     }
			});
			//location.reload();
			if(estado==true){
				$(nota).find("h4").css("color","black" );
				$(nota).find("h4").css("text-decoration", "line-through");	
			}
			else{
				$(nota).find("h4").css("color","white" );
				$(nota).find("h4").css("text-decoration", "none");	
			}
			 
			ordenarListas();
			
			
		});
	}
	
	function asignarAgregar(){
		$("#agregar").click(function(){
			$.ajax({
	   		    url: "CrearNota",
	   		    success:function(datos,status,jqXHR){
   		    		console.log("Nota creada");
   		    		//armarNota(datos);
   		    		armarNotaPrimero(datos);
   		    		//console.log("datos dsp agregar"+datos.cuerpo);
   		    		asignarTodo();
	   		    }
			});
		});
	}
	
	function ordenarListas(){
		
		var todasNotas=$(".nota");
		//console.log(todasNotas);
		$.each(todasNotas, function(indice,data){
			notaSimple=$(data);
			
			var estado=notaSimple.find("input:checkbox").is(":checked");
			
			if(estado==true){
				$("body").last().append(notaSimple);
			}
		});	
	}
	
	function asignarFiltro(){
		$(".filtro").click(function(){
			var id=$(this).attr("id");
			//console.log("id filtro: "+id);
			if(id=="todas"){
				$.ajax({
		   		    url: "FiltrarNotas",
		   		    data: {filtro:"todas"},
		   		    success:function(datos,status,jqXHR){
	   		    		//console.log("Filtro todas");
	   		    		$(".nota").remove();
	   		    		armarNota(datos);
	   		    		//console.log("datos dsp agregar"+datos.cuerpo);
	   		    		asignarTodo();
		   		    }
				});
				
			}
			if(id=="hacer"){
				$.ajax({
		   		    url: "FiltrarNotas",
		   		    data: {filtro:"hacer"},
		   		    success:function(datos,status,jqXHR){
	   		    		//console.log("Filtro hacer");
	   		    		$(".nota").remove();
	   		    		armarNota(datos);
	   		    		//console.log("datos dsp agregar"+datos.cuerpo);
	   		    		asignarTodo();
		   		    }
				});
			}
			if(id=="listas"){
				$.ajax({
		   		    url: "FiltrarNotas",
		   		    data: {filtro:"listas"},
		   		    success:function(datos,status,jqXHR){
	   		    		//console.log("Filtro listas");
	   		    		$(".nota").remove();
	   		    		armarNota(datos);
	   		    		//console.log("datos dsp agregar"+datos.cuerpo);
	   		    		asignarTodo();
		   		    }
				});
			}
			
		});
	}
	function asignarEliminar(){
		$(".botonEliminar").click(function(){
			var id=$(this).closest(".nota").attr("id");
			$('#'+id).remove();
			$.ajax({
	   		     url: "EliminarNota?id="+id+"",
	   		     success:function(datos,status,jqXHR){
						console.log("Nota eliminada");
	   		     }
			});
		});
	}
	

	
	
	function armarNota(not){
		 var notaAnt=notaTemplate;
		 
		 $.each(not, function(indice,json){	
			 var a1=notaAnt.clone();
				a1.attr("hidden",false);
				a1.attr("id",json.id);
				a1.attr("title","Creada: "+json.agregada+" Modificada: "+json.modificada+"");
				
				$("body").last().append(a1);
				$("#"+json.id).find("p").text(json.cuerpo);
				$("#"+json.id).find("h4").text(json.titulo);
				
				$("#"+json.id).find(".checkbox").attr("checked",esLista(json.lista) );
				if(esLista(json.lista)==true){ //Si esta realizada que la tache y la madne al fondo 
					$("#"+json.id).find("h4").addClass("NotaTachada");
					//$("#items p")
					/*  */
				}
		});
	 }
	 
	 function armarNotaPrimero(not)
	 {
		 var notaAnt=notaTemplate;
		 $.each(not, function(indice,json){
				var a1=notaAnt.clone();
				a1.attr("hidden",false);
				a1.attr("id",json.id);
				a1.attr("title","Creada: "+json.agregada+" Modificada: "+json.modificada+"");
				//$("body").append(a1);
				$("#antesNota").prepend(a1);
				$("#"+json.id).find("p").text(json.cuerpo);
				$("#"+json.id).find("h4").text(json.titulo);
				$("#"+json.id).find(".checkbox").attr("checked",esLista(json.lista) );
			});
	 }
	 function autocompletar (elemento, url, minLetras, clickCallback, idElem){
		
		var elem = $(elemento);
		elem.keyup(function(evt){
			if(elem.val().length>=minLetras){
				$.ajax({
					url:url,
					data:{ texto:elem.val() }, //texto que escribimos
					success: function (datos,status,jqXHR){
						$(".nota").remove();
								if(clickCallback && typeof clickCallback !== "undefined"){
									clickCallback(datos);
									asignarEliminar();
									asignarTodo();
								}	
					}
				});
				
			}
			else{
				$.ajax({
					url:url,
					data:{texto:""},
					success: function (datos,status,jqXHR){
						$(".nota").remove();						
								if(clickCallback && typeof clickCallback !== "undefined"){	
									clickCallback(datos);
									asignarEliminar();
									asignarTodo();
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
		
		<!--  input id="agregar" class="btnPrincipal" type="button" value="Agregar Nota" style="display:block;" align="middle" name="11" />-->
		<a id="agregar" class="btnPrincipal" >Agregar Nota</a><br/><br/>
		<br/>
	</div>
	
	<form>
  		<div id="radio">
	    	<input type="radio" id="todas" name="radio" checked="checked"  class="filtro"/>
	    	<label for="todas">Todas</label>
		    <input type="radio" id="listas" name="radio" class="filtro"/>
		    <label for="listas">Listas</label>
		    <input type="radio" id="hacer" name="radio" class="filtro" />
		    <label for="hacer">Hacer</label>
  		</div>
	</form>
	

	
	
	<div>   <!-- AUTOCOMPLETAR              -->
		<input type="text" id="texto" />
		<div id="salida"></div>
	<br>
	</div>
	
	
	<div id="antesNota">
	<div  class ="centrar nota" id="nota" hidden="true" >

	
		    <div class="modal-content">
		     
		      <div class="modal-header notaHeader">
		      	<input type="checkbox" class="checkbox" aria-hidden="true" />
		        <button type="button"  class="cruz close botonEliminar " data-dismiss="modal" aria-hidden="true">&times;</button>

		        <h4 class="textareaTitulo modal-title titulo" class="" name="aa" id="titulo" contenteditable="true" >Modal title</h4>
		      </div>
		      
		      <div class="modal-body notaBody" id="cuerpo">
		        <p class="textareaCuerpo"  contenteditable="true" >One fine body&hellip;</p>
				       <!--  CALENDAR 
				        <div class="well">
							  <div id="dTP" class="input-append datepicker">
							    <input data-format="yyyy-MM-dd" type="text"></input>
							    <span class="add-on">
							      <i class="icon-calendar" data-time-icon="icon-time" data-date-icon="icon-calendar">
							      </i>
							    </span>
							  </div>
					    </div>
						CALENDAR	 -->
		      </div>
		    </div><!-- /.modal-content -->
   
	</div><!-- /.modal-dialog -->
	 </div>
	<p>Date: <input type="text" id="datepicker" /></p>
 
 
		
</body>
</html>

