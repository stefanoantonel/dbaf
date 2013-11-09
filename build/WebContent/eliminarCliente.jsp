<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
		function elimina(selecc){
			//alert(selecc);
			
			//alert("El Cliente fue eliminado");
			document.getElementById("labelElec").value=selecc;
			//document.location.href='EliminarCliente
		}
</script>

</head>
<body>
	<div align="center">
	<!--  ${sessionScope.l}-->
	
	<%=request.getSession().getAttribute("lista")%>

	</div>
	
</body>
</html>