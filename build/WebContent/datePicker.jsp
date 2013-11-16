<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  
   <script>
   $(document).ready(function(){
	   	$(function() {
		    $( "#datepicker" ).datepicker();
		});
	   	
	   	console.log($( "#datepicker" ).val());
	   	
	   	
   });
   
	   
 	
	   

  </script>
  
  <body>
   
<p>Date: <input type="text" id="datepicker" /></p>
 
 
  </body>
<html>