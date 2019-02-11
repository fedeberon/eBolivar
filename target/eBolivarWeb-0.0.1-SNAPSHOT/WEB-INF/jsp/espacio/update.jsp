<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author JoseAlv * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Editar Espacio</title>
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />
</head>
<body>

   <jsp:include page="../header.jsp" />


<div class="titulo-general">
  <span>Editar Espacio</span>
  <hr>
</div>

	<c:if test="${! empty errorMessage}">
		<div class="errorBox">
			<ul>
				<li>${errorMessage}</li>
			</ul>
		</div>
	</c:if> 
	
	<spring:hasBindErrors name="espacio">
		<div class="errorBox">
			<ul>
				<c:forEach items="${errors.allErrors}" var="error">
					<li>${error.defaultMessage}</li>
				</c:forEach>
			</ul>
		</div>
	</spring:hasBindErrors>

	<div id="formulario">
	<form:form name="form" method="post" commandName="espacio" enctype="multipart/form-data">

		
		<p>
			<label for="id" class="campo">id:</label>
			<form:input readonly="true" path="id" />
		</p>
			
		<p class="odd">
			<label for="titulo" class="campo">titulo:</label>
			<form:input path="titulo" />
		</p>
			
		<p class="odd">
			<label for="latitud" class="campo">latitud:</label>
			<form:input path="latitud" />
		</p>
			
		<p class="odd">
			<label for="longitud" class="campo">longitud:</label>
			<form:input path="longitud" />
		</p>
			
		<p class="odd">
			<label for="tipoEspacio" class="campo">tipoEspacio:</label>
			<form:select path="tipoEspacio.id" items="${tipoEspacio}" itemLabel="nombre" itemValue="id"/>
		</p>


		<p class="odd">
			<label for="imagen" class="campo">Imagen:</label>
			<input type="file" class="btn btn-default" name="file" style="width:300px;"/> 
		</p>
		
		
		
		<p>
		<c:forEach items="${ espacio.imagenes }" var="imagen">
		<a href="/img/${ imagen.path }" data-toggle="lightbox" data-gallery="multiimages">
		<img style="width: 100px;" src="${ imagen.path }" /></a>
		
		<a onclick="return confirm('Esta seguro que desea eliminar?')" href="deleteImage?idEspacio=${espacio.id}&idImagen=${imagen.id}">
		<img class="minibutton" alt="Eliminar" src="<c:url value='/images/icons/delete_icon.png'/>"/></a>
		
	    </c:forEach>
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
