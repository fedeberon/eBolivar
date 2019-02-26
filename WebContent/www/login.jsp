<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/img/icons/logo.ico'/>">
<title>Login</title>
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/js/bootstrap.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/login.css'/>" type="text/css" media="print, projection, screen"/>
	<!-- Stylesheets -->
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script>

<script type="text/javascript">

$(document).ready(function() {
	
	document.getElementById("j_username").focus();

});


</script>

</head>



<body>

	<!-- TOP BAR -->
<div id="header" align="right">
<img src="/images/logo-bolivar.png" class="logoImagen">
   
<table style="width:100%;border-top-width:5px;border-top-color: black;">
	<tbody>
	<tr valign="middle">
    
    <td style="text-align: left;">
		<font class="logoNombre1"> Centro de Atenci&oacute;n al </font><font class="logoNombre2">Ciudadano</font><br>
	</td>
	
	</tr>
	
</tbody>
</table>
<img style="height:105px; " src="<c:url value='/img/institucional/bolivar-vivir-mejor.png'/>/>
</div>

<hr>
<!-- 	end top-bar -->
	
	
	
	<!-- HEADER -->
	<div id="header">
		
		<div class="page-full-width cf">
	
			<div id="login-intro" class="fl">
			
				<h3>Inicio de Sesion</h3>
				<h5>Ingrese de sus datos Personales.</h5>
			
			</div> <!-- login-intro -->

		</div> <!-- end full-width -->	

	</div> <!-- end header -->
	
	
	
	<!-- MAIN CONTENT -->
	<div id="contentLog">
		<form id="login-form" action="j_spring_security_check'/>"  >

				<p>
					<label for="login-username">USUARIO</label>
					<input type="text" name="username" id="username"  class="round full-width-input"   />
				</p>

				<p>
					<label for="login-password">CONTRASE�A</label>
					<input type="password" name="password" id="password"  class="round full-width-input" />
				</p>
				
				<p class="form-error"> 
				<c:if test="${not empty param.login_error}">
				<em><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.</em>
				</c:if>
				</p>
                  <csrf disabled="true"/>

				<input type="submit" class="button round blue image-right ic-right-arrow" value="Login"/>
		</form>
		
	</div> <!-- end content -->
	
	
	
	<!-- FOOTER -->
	<div id="footer">

		<p>&copy; Copyright 2013 <a href="http://www.bolivar.gob.ar/">Municipalidad de Bolivar</a></p>
		<p><strong>Area Sistemas </strong> by <a href="#">Sistemas</a></p>
	
	</div> <!-- end footer -->

</body>
</html>