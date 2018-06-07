<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author Fede Beron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type='text/JavaScript' src="<c:url value='/js/scwNac.js'/>"></script>
<html>
<head>
<title>${initParam['AppName']} - Editar Notificacion de Padron</title>
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />
</head>
<body>

	 <jsp:include page="../header.jsp" />
	
	<div class="titulo-general">
  <span>Editar Notificacion de Padron</span>
  </div>
	<c:if test="${! empty errorMessage}">
		<div class="errorBox">
			<ul>
				<li>${errorMessage}</li>
			</ul>
		</div>
	</c:if> 
	
	<spring:hasBindErrors name="notificacionPadron">
		<div class="errorBox">
			<ul>
				<c:forEach items="${errors.allErrors}" var="error">
					<li>${error.defaultMessage}</li>
				</c:forEach>
			</ul>
		</div>
	</spring:hasBindErrors>

	<div id="formulario">
	<form:form name="form" method="post" commandName="notificacionPadron">

		
		<p class="odd">
			<label for="padron" class="campo">Padron:</label>
			<form:input path="padron" />
		</p>
			
		<p class="odd">
			<label for="direccionEnvio" class="campo">Direccion de Envio:</label>
			<form:input path="direccionEnvio" />
		</p>
			
		<p class="odd">
			<label for="estado" class="campo">Estado:</label>
			<form:select path="estado">
			<form:option value="ACTIVO" label="ACTIVO"/>
			<form:option value="INACTIVO" label="INACTIVO"/>
			</form:select>
			
		</p>
			
			
		<p class="odd">
			<label for="confirmado" class="campo">Confirmado:</label>
			<form:select path="confirmado">
			<form:option value="NO" label="NO"/>
			<form:option value="SI" label="SI"/>
			</form:select>
		</p>
			
		<p class="odd">
			<label for="tasa" class="campo">Tasa:</label>
			<form:input path="tasa" />
		</p>
			
		<p class="odd">
			<label for="nombreApellido" class="campo">Nombre y Apellido:</label>
			<form:input path="nombreApellido" />
		</p>
			

		<p class="odd">
			<label for="fechaAlta" class="campo">Alta:</label>
			<form:input path="fechaAlta" onkeypress="return onlyNumber(event);"/>
		</p>
			

	</form:form>
	</div>
		
	<div id="botonera">
		<a class="button" href="javascript:history.back()">
			<img src="<c:url value='/img/icons/cancel.png'/>"/>Cancelar
		</a>
		<a class="button" onclick="document.form.submit();">
			<img src="<c:url value='/img/icons/database_save.png'/>"/>Guardar
		</a>
	</div>

	<jsp:include page="../bottom.jsp" />

</body>
</html>
