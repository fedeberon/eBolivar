<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author JoseAlv * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Editar GuiaTramite</title>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/img/icons/logo.ico'/>">
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />
</head>
<body>

	<jsp:include page="../header.jsp" />

	<div class="titulo-general">
  	<span>Editar Guia de Tramites</span>
  	</div>
  
	<c:if test="${! empty errorMessage}">
		<div class="errorBox">
			<ul>
				<li>${errorMessage}</li>
			</ul>
		</div>
	</c:if> 
	
	<spring:hasBindErrors name="guiaTramite">
		<div class="errorBox">
			<ul>
				<c:forEach items="${errors.allErrors}" var="error">
					<li>${error.defaultMessage}</li>
				</c:forEach>
			</ul>
		</div>
	</spring:hasBindErrors>

	<div id="formulario">
	<form:form name="form" method="post" commandName="guiaTramite">

		
		<p>
			<label for="codigo" class="campo">codigo:</label>
			<form:input readonly="true" path="codigo" />
		</p>
			
		<p class="odd">
			<label for="titulo" class="campo">titulo:</label>
			<form:input path="titulo" />
		</p>
			

	</form:form>
	</div>
		
	<div id="botonera">
		<a class="button" href="javascript:history.back()">
			<img src="<c:url value='/images/icons/cancel.png'/>"/>Cancelar
		</a>
<%-- 		<sec:authorize ifAllGranted="ROLE_WRITE_GuiaTramite"> --%>
		<a class="button" onclick="document.form.submit();">
			<img src="<c:url value='/images/icons/database_save.png'/>"/>Guardar
		</a>
<%-- 		</sec:authorize> --%>
	</div>

	<jsp:include page="../bottom.jsp" />

</body>
</html>
