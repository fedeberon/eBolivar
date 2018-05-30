<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../header.jsp" />
<html>
<head>
<title>${initParam['AppName']} - Nuevo Imagen</title>
</head>
<body>
  <div class="titulo-general">
  <span>Nuevo banner</span>
  </div>
 
<c:if test="${! empty errorMessage}">
  <div class="errorBox">
  <ul>
    <li>${errorMessage}</li>
  </ul>
  </div>
</c:if> <spring:hasBindErrors name="banner">
  <div class="errorBox">
  <ul>
    <c:forEach items="${errors.allErrors}" var="error">
      <li>${error.defaultMessage}</li>
    </c:forEach>
  </ul>
  </div>
</spring:hasBindErrors> 

<div id="formulario">
<form:form name="form" method="post" commandName="banner"  enctype="multipart/form-data">

	
	  <p class="odd">
		<label for="nombre_principal" class="campo">Leyenda Principal:</label>
      <form:input path="nombre_principal" />
    </p>
	  <p class="odd">
		<label for="nombre_secundario" class="campo">Leyenda Secundario:</label>
      <form:input path="nombre_secundario" />
    </p>
	  <p class="odd">
		<label for="direccion_img" class="campo">Archivo:</label>
      	 <input type="file" class="btn btn-default" name="file" style="width:300px;"/> 
    </p>
	  <p class="odd">
		<label for="observaciones" class="campo">Observaciones:</label>
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

    <div id='botonera'>
        <a href="<c:url value='/webapp/banner/list'/>" class="btn btn-primary">Volver</a>
        <a onclick="document.forms['form'].submit();"   class="btn btn-primary">Guardar</a>
    </div>






</div>
        

  <jsp:include page="../bottom.jsp" />
</body>
</html>
