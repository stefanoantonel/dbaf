<!doctype html>
 
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>jQuery UI Button - Radios</title>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css" />
  <script>
  $(function() {
    $( "#radio" ).buttonset();

  });
  </script>
</head>
<body>
 



	<form>
  		<div id="radio">
	    	<input type="radio" id="radio1" name="radio" checked="checked"  class="filtro"/>
	    	<label for="radio1">Choice 1</label>
		    <input type="radio" id="radio2" name="radio" class="filtro"/>
		    <label for="radio2">Listas</label>
		    <input type="radio" id="radio3" name="radio" class="filtro" />
		    <label for="radio3">Hacer</label>
  		</div>
	</form>
	
</body>
</html>