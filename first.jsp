<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "javax.servlet.http.HttpSession"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/javascript; charset=ISO-8859-1">

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<title>Application</title>
</head>
<body style="background-color:#E5E4E2;">
<h1 style="color:#0000A0;" align="center">JAVA ENCRYPTOR/DECRYPTOR</h1>
<div class="container">
<div class="row justify-content-center">
	 <form class="form-horizontal" method="post" action="secondController">
		<div class="form-group col-md-8">
		  	<label>Choose Application</label>
		  	<select name="app" class="form-control">
		      	<option value="1">AESKEY</option>
		      	<option value="2">SECOND</option>
		      	<option value="3">THIRD</option>
		  	</select>
		</div>
		<div class="form-group col-md-8">
		  	<label>Choose Algorithm</label>
		  	<select name="algo" class="form-control">
		      	<option value="1"<%if(session.getAttribute("alInt").toString().equals("1")){%>selected<%}%> >AES</option>
		      	<option value="2"<%if(session.getAttribute("alInt").toString().equals("2")){%>selected<%}%> >Triple DES</option>
		      	<option value="3"<%if(session.getAttribute("alInt").toString().equals("3")){%>selected<%}%> >RC4</option>
		      	<option value="4"<%if(session.getAttribute("alInt").toString().equals("4")){%>selected<%}%> >MD5</option>
		  	</select>
		  	   <input type="hidden" value='<%=session.getAttribute("alInt")%>'>
		</div>
		<div class="form-group col-md-8">
			<label>Enter the String</label>
			<input type="text" name="plaintext" class="form-control" value='<%=session.getAttribute("plaintext")%>'>
		</div> 
		<div class="form-group col-md-8">
			<label>Result</label>
			<div class="btn-group">
        		<input type="text" name="result" id="result" class="form-control col-md-8" value='<%=session.getAttribute("result")%>'>&nbsp;
        		<button type="button" name="copy" class="btn btn-primary btn-md center-block" onclick="myFunction()">Copy</button>
        	</div>
        </div>
        <div class="form-group col-md-8">
			<div class="btn-group">
			        	<button type="submit" name="encrypt" class="btn btn-primary btn-md center-block">Encrypt</button>&nbsp;
			     		<button type="submit" name="decrypt" class="btn btn-primary btn-md center-block">Decrypt</button>
	  		</div> 
	  	</div>	
  	</form>     	    
</div>
</div>
<script type="text/javascript">
function myFunction() {
	  var copytext = document.getElementById("result");
	if(copytext==""){
		alert("Empty field");
	}
	else{
	  copytext.select();
	  document.execCommand("copy");
	}
}
</script>
</body>
</html>
