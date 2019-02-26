<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
<title>${initParam['AppName']} - Nuevo Notificacion de Padron</title>

<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />

<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/emailAddressInput.js'/>"></script>

<script type="text/javascript">
	$(document).ready(function() {
		   $('#emailAddress').addEmailAddress();
	  } );
</script>


 <style>
.multiple_emails-container { 
	border:1px #ffffff solid;
	border-radius: 4px; 
	box-shadow: inset 0 1px 1px rgba(0,0,0,.075); 
	padding:0; margin: 0; cursor:text; width:100%;
    margin-left: 162px;
    display: unset;
}

.multiple_emails-container input { 
	clear:both; 
	width:100%; 
	border:0; 
	outline: none; 
	margin-bottom:3px; 
	padding-left: 5px; 
	box-sizing: border-box;
}

.multiple_emails-container input{
	border: 0 !important;
}

.multiple_emails-container input.multiple_emails-error {	
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px red !important; 
	outline: thin auto red !important; 
}

.multiple_emails-container ul {	
	list-style-type:none; 
	padding-left: 0; 
}

.multiple_emails-email { 
	margin: 3px 5px 3px 5px; 
	padding: 3px 5px 3px 5px; 
	border:1px #BBD8FB solid;	
	border-radius: 3px; 
	background: #F3F7FD; 
}

.multiple_emails-close { 
	float:left; 
	margin:0 3px;
}
 </style>

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
		    	incorrect_try_again :  'Incorrecto. Por favor, int�ntelo de nuevo.'
			},
		    theme : 'clean'
		};
  
  
  function verInfo(){
      $.blockUI({ 
          message: $('#displayLeerBoleta'), 
          css: { 
              top: '80px', 
              left: '200px',
              cursor:'pointer',
              width:'950',
              border: '2px solid #000000',
              background:'#E5E6DA',
      		height: '500;'
           },
          onOverlayClick: $.unblockUI 
      }); 
  }
  
 </script>
   
</head>
<body>
  <jsp:include page="../header.jsp" />
  <jsp:include page="../componentes/modals.jsp" />

  <div class="titulo-general">
  <span>Notificacion a Padron</span>
  </div>
<c:if test="${! empty errorMessage}">
  <div class="errorBox">
  <ul>
    <li>${errorMessage}</li>
  </ul>
  </div>
</c:if> <spring:hasBindErrors name="notificacionPadron">
  <div class="errorBox">
  <ul>
    <c:forEach items="${errors.allErrors}" var="error">
      <li>${error.defaultMessage}</li>
    </c:forEach>
  </ul>
  </div>
</spring:hasBindErrors>

<div id="formulario">


<form:form name="form" action="save" method="post" commandName="notificacionPadron">

	  <p class="odd">
		<label for="padron" class="campo">Padron:</label>
      	 <img onclick="verInfo();" width="20" height="20" style="cursor: help; float: right; margin-right: 40%;"
              src="<c:url value='/img/icons/information.png'/>"/>
          <form:input path="padron" />
      </p>

	  <p class="odd">
		<label for="direccionEnvio" class="campo">Direccion Envio:</label>
     	 <form:input path="direccionEnvio" id="emailAddress"/>
     </p>

    	  <p class="odd">
		<label for="nombreApellido" class="campo">Nombre Apellido:</label>
      <form:input path="nombreApellido" />
    </p>
    
    	  <p class="odd">
		<label for="dni" class="campo">D.N.I.:</label>
      <form:input path="dni" />
    </p>
    
    
	  <p class="odd">
		<label for="telefono" class="campo">Telefono:</label>
      <form:input path="telefono" />
    </p>

    <!-- 
 	  <p class="odd">
		<label for="confirmado" class="campo">Confirmado:</label>
      <form:input path="confirmado" />
    </p>
	  <p class="odd">
		<label for="tasa" class="campo">Tasa:</label>
      <form:input path="tasa" />
    </p>
     -->
  
   
 <%  
//   ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LeVcPkSAAAAAGZbAG8wudQOyZqtqkldYg_YOjzG","6LeVcPkSAAAAAK8UKe-YlQSpRSRpnC-ywV0scNUs", true);   
//   out.print(c.createRecaptchaHtml(null, null));  
%>   
   
  
</form:form>

</div>
        
    <div style="margin-left: 90px" id="botonera">
        <a class="button" href="javascript:history.back()">
            <img src="<c:url value='/img/icons/cancel.png'/>"/>Cancelar
        </a>
        <a class="button" onclick="document.form.submit();">
            <img src="<c:url value='/img/icons/database_save.png'/>"/>Guardar
        </a>
        <a class="button" href="<c:url value='/webapp/notificacionPadron/list'/>">Volver</a>
    </div>
  <jsp:include page="../bottom.jsp" />
</body>
</html>
