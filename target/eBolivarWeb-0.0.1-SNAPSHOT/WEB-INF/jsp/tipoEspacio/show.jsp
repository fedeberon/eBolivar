<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Ver TipoEspacio</title>
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css"  media="print, projection, screen" />
<script type="text/javascript" src="<c:url value='/js/jquery-latest.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.tablesorter.min.js'/>"></script>
<script type="text/javascript">
  $(document).ready(function() {
		$("#listado")
		.tablesorter({widgets: ['zebra']});
	} );
</script>
</head>
<body>

	<jsp:include page="../top.jsp" />

	<h1>Ver TipoEspacio</h1>

	<table id="listado" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
		<thead></thead>
		<tbody>

			<tr>
			<tr>
		<td><b>id:</b></td>
		<td>${tipoEspacio.id}</td>
	</tr><tr>
		<td><b>nombre:</b></td>
		<td>${tipoEspacio.nombre}</td>
	</tr>

		</tbody>
	</table>

	<div id="botonera">
		<a class="button" href="javascript:history.back()">
			<img src="<c:url value='/images/icons/arrow_left.png'/>"/> Volver </a>
		<sec:authorize ifAllGranted="ROLE_WRITE_TipoEspacio">
		<a class="button" href="update?idTipoEspacio=${tipoEspacio.id}">
			<img src="<c:url value='/images/icons/application_form_edit.png'/>"/> Editar </a>
		</sec:authorize>
	</div>

	<jsp:include page="../bottom.jsp" />
</body>
</html>
