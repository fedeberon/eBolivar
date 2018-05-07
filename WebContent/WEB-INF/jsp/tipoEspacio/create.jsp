<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Nuevo TipoEspacio</title>
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />  
</head>
<body>
  <jsp:include page="../top.jsp" />

<h1>Nuevo TipoEspacio</h1>
<c:if test="${! empty errorMessage}">
  <div class="errorBox">
  <ul>
    <li>${errorMessage}</li>
  </ul>
  </div>
</c:if> <spring:hasBindErrors name="tipoEspacio">
  <div class="errorBox">
  <ul>
    <c:forEach items="${errors.allErrors}" var="error">
      <li>${error.defaultMessage}</li>
    </c:forEach>
  </ul>
  </div>
</spring:hasBindErrors> 

<div id="formulario">
<form:form name="form" method="post" commandName="tipoEspacio">

	
	  <p class="odd">
		<label for="nombre" class="campo">nombre:</label>
      <form:input path="nombre" />
    </p>
  
</form:form>
</div>
        
    <div id="botonera">
        <a class="button" href="javascript:history.back()">
            <img src="<c:url value='/images/icons/cancel.png'/>"/>Cancelar
        </a>
        <sec:authorize ifAllGranted="ROLE_WRITE_TipoEspacio">
        <a class="button" onclick="document.form.submit();">
            <img src="<c:url value='/images/icons/database_save.png'/>"/>Guardar
        </a>
        </sec:authorize>
    </div>
  <jsp:include page="../bottom.jsp" />
</body>
</html>
