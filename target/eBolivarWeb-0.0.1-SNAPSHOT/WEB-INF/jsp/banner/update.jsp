<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author JoseAlv * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Editar Banner</title>
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />
</head>
<body>

	<jsp:include page="../header.jsp" />

	<div class="titulo-general">
   <span>Editar datos del banner</span>
   </div>

	<c:if test="${! empty errorMessage}">
		<div class="errorBox">
			<ul>
				<li>${errorMessage}</li>
			</ul>
		</div>
	</c:if> 
	
	<spring:hasBindErrors name="banner">
		<div class="errorBox">
			<ul>
				<c:forEach items="${errors.allErrors}" var="error">
					<li>${error.defaultMessage}</li>
				</c:forEach>
			</ul>
		</div>
	</spring:hasBindErrors>

	<div id="formulario">
	<form:form name="form" method="post" commandName="banner">

		
		<p>
			<label for="id" class="campo">id:</label>
			<form:input readonly="true" path="id" />
		</p>
			
		<p class="odd">
			<label for="nombre_principal" class="campo">nombre_principal:</label>
			<form:input path="nombre_principal" />
		</p>
			
		<p class="odd">
			<label for="nombre_secundario" class="campo">nombre_secundario:</label>
			<form:input path="nombre_secundario" />
		</p>
			
		<!-- 	
		<p class="odd">
			<label for="direccion_img" class="campo">direccion_img:</label>
			<form:input path="direccion_img" />
		</p>  -->
			
		<p class="odd">
			<label for="observaciones" class="campo">observaciones:</label>
			<form:input path="observaciones" />
		</p>
				  <p class="odd">
		<label for="estado" class="campo">Estado:</label>
      <form:select path="estado">
      <form:option value="ACTIVO" label="Publicar"/>
      <form:option value="INACTIVO" label="No Publicar"/>
      </form:select>
    </p>
			

	</form:form>
	</div>
		
	<div id="botonera">
		<a class="button" href="javascript:history.back()">
			<img src="<c:url value='/images/icons/cancel.png'/>"/>Cancelar
		</a>
		<a class="button" onclick="document.form.submit();">
			<img src="<c:url value='/images/icons/database_save.png'/>"/>Guardar
		</a>

	</div>

	<jsp:include page="../bottom.jsp" />

</body>
</html>
