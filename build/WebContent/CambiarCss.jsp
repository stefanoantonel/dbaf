<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/estiloRojo.css" type="text/css" />
<script src="css/js/jquery-1.9.1.js"></script>

<script type="text/javascript">


$(document).on("ready",function(){
	$("#btn_verde").click(function(){
		console.log("boton verde");
		 var oldlink = document.getElementsByTagName("link").item("./css/estilo");
		 
	        var newlink = document.createElement("link");
	        newlink.setAttribute("rel", "stylesheet");
	        newlink.setAttribute("type", "text/css");
	        newlink.setAttribute("href", "./css/estiloVerde.css");
	 
	        document.getElementsByTagName("head").item(0).replaceChild(newlink, oldlink);
	});

});
</script>
</head>

<body>

<div class="TituloPrincipal">HOLAAAAAAAAAAAAAA </div>
<button id="btn_verde" name="btn_verde">Verde</button>

</body>
</html>