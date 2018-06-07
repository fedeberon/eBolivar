<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author Fede Beron * @version 1.0 */ -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>  
<script type="text/javascript" src="<c:url value='/js/jquery.blockUI.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap.js'/>"></script>  

                
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

 <jsp:include page="../header.jsp" />

 <script type="text/javascript">
 
 $(document).ready(function() {
	 $('#reclamos').addClass( 'active' );
	}); 
 
 </script>
 
  <script type="text/javascript">
  var RecaptchaOptions = {
		    custom_translations : {
		    	instructions_visual :  'Escriba las palabras:',
		    	instructions_audio :   'Escribir lo que escucha:',
		    	play_again :           'Volver a reproducir el audio.',
		    	cant_hear_this :       'Descarga el audio en formato MP3.',
		    	visual_challenge :     'Modo Visual',
		    	audio_challenge :      'Escuchar la clave de seguridad.',
		    	refresh_btn :          'Volver a generar una clave de seguridad.',
		    	help_btn :             'Ayuda',
		    	incorrect_try_again :  'Incorrecto. Por favor, inténtelo de nuevo.'
			},
		    theme : 'clean'
		};
 </script>
 <style>
.recaptchatable .recaptcha_image_cell, #recaptcha_table
{
    background-color: #FFFFFF !important; /* reCaptcha widget background color */
}
#recaptcha_table
{
    border-color: #DFDFDF !important; /* reCaptcha widget border color */
}
#recaptcha_response_field
{
    border: 1px solid #CCCCCC !important; /* Text input field border color */
    background-color:#FFFFFF !important; /* Text input field background color */
}
</style>


<c:if test="${isScreenTouch }">

 <jsp:include page="../touch/libreriasTouch.jsp" />

 <div class="titulo-general">
<div style="float: right;">
	    <img class="imgTouch" style="width: 100px; margin-left: -100px; margin-right: 30px;" src="/img/touch2.png" >
    </div>
  <span>Seleccione el tipo de tramite o consulta que desea realizar.</span>
<p class="sub-titulo">Una gu&iacute;a paso a paso de los procedimientos administrativos en Bolivar</p>
<p class="descripcion-titulo"> es una base de datos en línea concebida para aportar una total transparencia en los procedimientos administrativos en Bolivar</p>

 
<div style="clear:both;">
    </div>
</div>

</c:if>

<ul class="nav nav-tabs" style="margin-left: 80px;margin-right: 80px;">
  <li ><a href="#home" data-toggle="tab">Buscar Reclamos</a></li>
  <li class="active" ><a  href="#profile" data-toggle="tab">Enviar un Reclamos o Sugerencia</a></li>
  <li><a  href="#map" data-toggle="tab">Maps</a></li> <!-- En proceso de desarrollo.  -->
</ul>


<div class="tab-content">
<div class="tab-pane" id="home" style="background-color: white;min-height: 200px;text-align: center;margin-left: 80px;margin-right: 80px;">

<div align="center">
<hr>
<h3 style="margin-top:0px;">
<br>
<span style="cursor: pointer;" class="titulo-descripcion" 
	  data-toggle="collapse" data-target="#demo">Sistema de Reclamos</span></h3>
<br/>
<p class="bg-warning" id="btnMensaje" style="display: none;background-color: #f0ad4e;">Warning</p>

</div>

<hr>
<div class="row collapse in" id="demo" style="margin-left:350px;margin-right:50px">
  <div class="col-lg-6">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default"  onclick="obtenerReclamo();" type="button">Buscar !</button>
      </span>
      <input id="idReclamo" type="text" class="form-control" placeholder="Ingrese número de Reclamo">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->


	<hr>
	
	<div class="modal fade" id="modalReclamo">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Datos del Reclamo</h4>
      </div>
      <div class="modal-body">

	<table  id="myTable"  class="table table-bordered">
	<tbody>
	
	<tr>
	<td><p>Codigo</p></td>
	<td id="codigoReclamo">No se encontraron Registros</td>
	</tr>
	
	<tr>
	<td><p>Nombre</p></td>
	<td id="nombreReclamo">No se encontraron Registros</td>
	</tr>
	
	<tr>
	<td><p>Apellido</p></td>
	<td id="apellidoReclamo">No se encontraron Registros</td>
	</tr>
	
	<tr>
	<td><p>Tipo</p></td>
	<td id="tituloReclamo">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Reclamo</p></td>
	<td id="descripcionReclamo">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Fecha Ingreso</p></td>
	<td id="ingresoReclamo">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Calle</p></td>
	<td id="calleReclamo">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Estado</p></td>
	<td id="estadoReclamo">No se encontraron Registros</td>
	</tr>


	
	</tbody>
	</table>
	
	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<br class="all">
   
</div>



<div class="tab-pane active" id="profile"  style="margin-left: 80px;margin-right: 80px;margin-top:0px;">


<c:if test="${empty mensaje }">
 <form style="background-color: white ;" id="formReclamo" action="guardarReclamos" method="post" enctype="multipart/form-data">



	<c:if test="${ ! empty mensaje  }">
	
	 <div class="alert-message error" style="left: 40%;" >
    <p><strong>${ mensaje } </strong> </p>
    </div>
	</c:if>



	<c:if test="${ ! empty errors  }">
	<ol>
	<c:forEach items="${ errors }" var="e">    
    <li><p><strong>${e}</strong></p></li>
    </c:forEach>
    </ol>
	</c:if>


<hr>
  
  <div class="form-group">
    <label for="exampleInputEmail1">Nombre</label>
     <input type="text" name="nombre" value="${nombre }" class="form-control"  placeholder="Ingrese su Nombre">
  </div>

  <div class="form-group">
    <label for="exampleInputEmail1">Apellido</label>
    <input type="text" name="apellido" value="${apellido }" class="form-control" id="exampleInputEmail1" placeholder="Ingrese su Apellido">
  </div>

  <div class="form-group">
    <label for="exampleInputEmail1">D.N.I.</label>
    <input type="text" name="dni" value="${dni }" class="form-control" id="exampleInputEmail1" placeholder="Ingrese su DNI">
  </div>

  <div class="form-group">
    <label for="exampleInputEmail1">Telefono</label>
    <input type="text" name="telefono"  value="${telefono }" class="form-control" id="exampleInputEmail1" placeholder="Ingrese su Telefono">
  </div>


  <div class="form-group">
    <label for="exampleInputEmail1">su Email</label>
    <input type="text" name="email" value="${email }" class="form-control" id="exampleInputEmail1" placeholder="Ingrese un e-mail">
  </div>
  
   <div class="form-group">
    <label for="exampleInputEmail1">Localidad</label>
    <input type="text" name="localidad" value="${localidad}" class="form-control" id="exampleInputEmail1" placeholder="Ingrese la Localidad">
  </div>
  
   <div class="form-group">
    <label for="exampleInputEmail1">Calle</label>
    <input type="text" name="calle" value="${calle}" class="form-control" id="exampleInputEmail1" placeholder="Ingrese la Calle">
  </div>
   <div class="form-group">
    <label for="exampleInputEmail1">Numero</label>
    <input type="text" name="numero" value="${numero}" class="form-control" id="exampleInputEmail1" placeholder="Ingrese un Numero">
  </div>
  
  <div class="form-group">
    <label for="descripcion">Escriba su reclamo</label>
   <textarea name="descripcionReclamo"  class="form-control" rows="10"  placeholder="Ingrese su comentario .."></textarea>
  </div>
  
 
<!--  Captcha -->
<%  
//   ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LeVcPkSAAAAAGZbAG8wudQOyZqtqkldYg_YOjzG","6LeVcPkSAAAAAK8UKe-YlQSpRSRpnC-ywV0scNUs", true);   
//   out.print(c.createRecaptchaHtml(null, null));  
%>   

 
</form>


<div id='botonera'>
<button  onclick="document.forms['formReclamo'].submit();" class="btn btn-default">Enviar</button>
</div>

 </c:if>


</div>

<br/><br/><br/><br/><br/>

<div class="tab-pane" id="map"  style="margin-left: 80px;margin-right: 80px;margin-top:0px;">
  <div id='mapDiv' style="position:absolute; width:1050px; height:700px;"></div>
</div>
  
  </div>
   <hr>
 
  


 


  <jsp:include page="../bottom.jsp" />

</body>
</html>


