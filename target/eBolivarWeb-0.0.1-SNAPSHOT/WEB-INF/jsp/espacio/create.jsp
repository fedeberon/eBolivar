<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Nuevo Espacio</title>
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />  
<jsp:include page="../header.jsp" />
<script type="text/javascript" src="<c:url value='/js/maps.js'/>"></script>
<SCRIPT type="text/javascript">

$(document).ready(function() {
	 
	crearMapa();
	
	 $('.maps').click(function(){
			$('#myModal').modal('show');
		});
	 
	});

 
</SCRIPT>

</head>
<body>
   


<div class="titulo-general">
  <span>Nuevo Espacio</span>
  <hr>
</div>


<c:if test="${! empty errorMessage}">
  <div class="errorBox">
  <ul>
    <li>${errorMessage}</li>
  </ul>
  </div>
</c:if> <spring:hasBindErrors name="espacio">
  <div class="errorBox">
  <ul>
    <c:forEach items="${errors.allErrors}" var="error">
      <li>${error.defaultMessage}</li>
    </c:forEach>
  </ul>
  </div>
</spring:hasBindErrors> 

<div id="formulario">
<form:form name="form" method="post" commandName="espacio">

	
	<p class="odd">
		<label for="titulo" class="campo">titulo:</label>
      <form:input path="titulo" />
    </p>
	  
	<div style="display: none;">
	  <p class="odd">
		<label for="latitud" class="campo">latitud:</label>
      <form:input path="latitud"/>
    </p>
	  <p class="odd">
		<label for="longitud" class="campo">longitud:</label>
      <form:input path="longitud"/>
    </p>
    </div>
	  
	 <p class="odd">
		<label for="direccion" class="campo">direccion:</label>
      	<form:input path="direccion" cssClass="maps" cssStyle="width:400px;"/>
     </p>
	 
	  <p class="odd">
		<label for="area" class="campo">area:</label>
      <form:input path="area"/>
	</p>
	  <p class="odd">
		<label for="monto" class="campo">monto:</label>
      <form:input path="monto"/>
	</p>
	  <p class="odd">
		<label for="fondos" class="campo">fondos:</label>
      <form:input path="fondos"/>
	</p>
	  <p class="odd">
		<label for="dias" class="campo">dias:</label>
      <form:input path="dias"/>
	</p>
	  <p class="odd">
		<label for="horarios" class="campo">horarios:</label>
      <form:input path="horarios"/>
	</p>
	<p class="odd">
		<label for="tipoEspacio" class="campo">tipoEspacio:</label>
        <form:select path="tipoEspacio.id" items="${tipoEspacio}" itemLabel="nombre" itemValue="id"/>
    </p>
	  <p class="odd">
		<label for="informacion" class="campo">informacion:</label>
      <form:textarea cols="5" rows="15" path="informacion"/>
   </p>
</form:form>
</div>





<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Mapa - Seleccione ubicacion</h4>
        </div>
        <div class="modal-body">
			<span id="mode">Dragging stopped (dragend event).</span>
			<div id='mapDiv' style="width:500px; height:500px;"></div>

        </div>
        <div class="modal-footer" style="padding-bottom: 10px;">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

        
    <div id="botonera">
 
 <sec:authorize ifAllGranted="ROLE_WRITE_RECLAMOS">
        <a class="button" onclick="document.form.submit();">Guardar</a>
        </sec:authorize>
    </div>
  <jsp:include page="../bottom.jsp" />
</body>
</html>
