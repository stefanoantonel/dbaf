<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<BODY>
    <FORM METHOD=POST ACTION="cuadrado.jsp">
    Ingrese un numero entero
    <INPUT TYPE=TEXT NAME=numero SIZE=5>
    <INPUT TYPE=SUBMIT>
    <%
        String cadena = request.getParameter("numero");
        int  valor;

        if (cadena != null) {
          try {
             valor = Integer.parseInt(cadena);
             out.println("El cuadrado de "+cadena+" es "+valor*valor);
          }
          catch ( NumberFormatException e) {
             out.println(cadena + " no es un entero");
          }
        }
        for(int i=0;i<10;i++){
        	%> <label>hehehe</label> <%
        }
        
    %>
    
</FORM>
</BODY>
</HTML>