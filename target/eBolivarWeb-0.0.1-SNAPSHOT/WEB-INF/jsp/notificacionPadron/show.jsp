<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Ver NotificacionPadron</title>

</head>
<body>

 <jsp:include page="../header.jsp" />

	  <div class="titulo-general">
  <span>Mail asociado a Padron</span>
  </div>
	
	
	<c:if test="${!empty mensaje }">
	
	 <div class="alert-message error" style="left: 40%;" >
    <a class="close" href="#">×</a>
    <p><strong>Alerta !</strong> ${ mensaje } </p>
  </div>
	
	</c:if>
<hr>
	<table id="listado" class="table table-striped" border="0" cellpadding="0" cellspacing="1">
		<thead></thead>
		<tbody>

			<tr>
			<tr>
		<td><b>Padron:</b></td>
		<td>${notificacionPadron.padron}</td>
	</tr><tr>
		<td><b>Direccion de Envio:</b></td>
		<td>${notificacionPadron.direccionEnvio}</td>
	</tr><tr>
		<td><b>Estado:</b></td>
		<td>${notificacionPadron.estado}</td>
	</tr><tr>
		<td><b>Fecha Alta:</b></td>
		<td>${notificacionPadron.fechaAlta}</td>
	</tr><tr>
		<td><b>Confirmado:</b></td>
		<td>${notificacionPadron.confirmado}</td>
	</tr><tr>
		<td><b>Tasa:</b></td>
		<td>${notificacionPadron.tasa}</td>
	</tr><tr>
		<td><b>Nombre Apellido:</b></td>
		<td>${notificacionPadron.nombreApellido}</td>
	</tr>

		</tbody>
	</table>

	<div id="botonera">
		<a class="button" href="javascript:history.back()">
			<img src="<c:url value='/img/icons/cancel.png'/>"/> Volver </a>
				<a class="button" href="update?idNotificacionPadron=${notificacionPadron.id}">
			<img src="<c:url value='/img/icons/application_form_edit.png'/>"/> Editar </a>
			 
	</div>

	<jsp:include page="../bottom.jsp" />
</body>
</html>
