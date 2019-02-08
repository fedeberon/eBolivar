<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Ver Guia Tramite</title>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/img/icons/logo.ico'/>">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/base.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/guia-tramite.css'/>"/>
<script type="text/javascript" src="<c:url value='/js/jquery-latest.js'/>"></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/slider/css/demo.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/slider/css/style2.css'/>" />
<link href='http://fonts.googleapis.com/css?family=Economica:700,400italic' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="<c:url value='/slider/css/buttons.css'/>" />
</head>
<body>

<jsp:include page="../header.jsp" />
<c:set var="totalPasos" value="${fn:length(guiaTramite.pasos)}"/>	



<c:if test="${isScreenTouch }">

<script type="text/javascript" src="<c:url value='/js/touch/touch.js'/>"></script>

 <script type="text/javascript">
  
 $(document).ready(function() {
	 
	 marquesina();
 
 	});

 
 </script>
 
 <jsp:include page="../touch/headTouch.jsp"/>

</c:if> 

<div class="titulo-general" style="border-bottom: 1px solid #f1f1f1;">
	<p style="text-transform: capitalize; color: #A59E9E;">${fn:toLowerCase(guiaTramite.titulo)}</p>
	<p class="sub-titulo"></p>
	<p class="descripcion-titulo"></p>
</div>

<div style="width: 20%; height: 60%; float: left;">
	<a id="menuButton0" class="button-menu button-menu-active" onclick="$(span0).click();">¿En qu&eacute; consiste?</a>
		<c:forEach items="${guiaTramite.pasos}" var="paso" varStatus="status">
			<a id="menuButton${status.index + 1}" class="button-menu" onclick="showStep(event, ${status.index + 1});">${fn:toLowerCase(paso.nombre)}</a>
		</c:forEach>
</div>

<div style="width: 79%; height: 60%; float: left;">
	<jsp:include page="showPasosTramite.jsp"/>
</div>

<jsp:include page="../bottom.jsp" />
</body>
</html>
