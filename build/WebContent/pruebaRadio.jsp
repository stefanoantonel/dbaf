<!doctype html>
 
<!--  html lang="en">
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
</html>-->


<html>
  <head>
    <script type="text/javascript"
 src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
</script> 
<script type="text/javascript"
 src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
</script>
<script type="text/javascript"
 src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
</script>
     <script type="text/javascript">
     $(function () {
    	  $('#StartDate').datetimepicker({
    	     pickTime: false
    	   })

    	});
    </script>
  </head>
  <body>
           <div class="modal-body" align="">
            <label>City/place</label>
            <div class="input-append"><input type="text" id="" name=""><span class="add-on"><i class="icon-search"></i></span></div>

            <label>number of people</label>
            <div class="input-append"><input type="text" id="number" name="number"></div>

            </br>


           <div id="StartDate" class="input-append date">
			    <input type="text" />
			    <span class="add-on">
			    <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
			    </span>
			</div>
        </div>
        <div class="modal-footer" >
            <button class="btn btn-secondary" data-dismiss="modal">Cancel</button>
            <button class="btn btn-primary">Register</button>
        </div>

        </div>
        <div class=""> 
        <a class="btn btn-large btn-success" href="#search_modal" data-toggle="modal">Book a room ss</a>
        </div>
  </body>
<html>