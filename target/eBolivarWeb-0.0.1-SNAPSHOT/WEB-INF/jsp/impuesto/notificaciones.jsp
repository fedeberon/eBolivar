<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Notificaciones</title>
<%-- <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>" type="text/css" media="print, projection, screen" />  --%>

<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.tablesorter.min.js'/>"></script>
 <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>"></script>
</head>
<body>

 <jsp:include page="../header.jsp" />

	<div class="titulo-general">
  <span>Notificaciones de Registro</span>
  </div>
 
<br><br><br>
  
  <div class="titulo-component">
	${ mensaje } 
</div>
	 
<br><br><br><br><br><br><br><br><br><br>
	<div id="botonera">
		<a class="button" href="<c:url value='/webapp/'/>"> Inicio</a>
	</div>

	<jsp:include page="../bottom.jsp" />
</body>
</html>
