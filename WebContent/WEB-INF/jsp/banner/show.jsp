<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Ver Banner</title>
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css"  media="print, projection, screen" />
<script type="text/javascript" src="<c:url value='/js/jquery-latest.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.tablesorter.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/shadowbox.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/shadowbox.css'/>" type="text/css" media="print, projection, screen" />


<script type="text/javascript">
  $(document).ready(function() {
// 		$("#listado").tablesorter({widgets: ['zebra']});
	} );
</script>


<script type="text/javascript">
Shadowbox.init();
</script>


</head>
<body>

	<jsp:include page="../header.jsp" />

 <div class="titulo-general">
  <span>Descripcion del banner</span>
  </div>

	<table id="listado" class="table table-striped" border="0" cellpadding="0" cellspacing="1">
		<thead></thead>
		<tbody>

			<tr>
			<tr>
		<td><b>Codigo:</b></td>
		<td>${banner.id}</td>
	</tr><tr>
		<td><b>Descripcion Principal:</b></td>
		<td>${banner.nombre_principal}</td>
	</tr><tr>
		<td><b>Descripcion Secundaria:</b></td>
		<td>${banner.nombre_secundario}</td>
	</tr><tr>
		<td><b>url Imagen:</b></td>
		<td>${banner.direccion_img}</td>
	</tr><tr>
		<td><b>Observaciones:</b></td>
		<td>${banner.observaciones}</td>
	</tr>

		</tbody>
	</table>

	<div id="botonera">
		<a class="button" href="javascript:history.back()">
			<img src="<c:url value='/images/icons/arrow_left.png'/>"/> Volver </a>
		<a class="button" href="update?idBanner=${banner.id}">
			<img src="<c:url value='/images/icons/application_form_edit.png'/>"/> Editar </a>
	
		<a class="button" href="${banner.direccion_img}" rel="shadowbox"><img style="width:15px;" src="<c:url value='/images/icons/kview.png'/>" />Ver Imagen</a>

	</div>

	<jsp:include page="../bottom.jsp" />
</body>
</html>
