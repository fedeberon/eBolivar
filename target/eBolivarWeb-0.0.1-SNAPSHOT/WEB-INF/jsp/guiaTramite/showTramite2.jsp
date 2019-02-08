<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Ver GuiaTramite</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/base.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
<script type="text/javascript" src="<c:url value='/js/jquery-latest.js'/>"></script>
<script type="text/javascript">
  $(document).ready(function() {
// 	  $('html, body').animate({
// 		    scrollTop: $("#target-element").offset().top
// 		}, 1000);
</script>
<STYLE type="text/css">
.spanClass {
	width: 200px;
	font-weight: normal;
	float: left;
	color: #555;
}

.odd {
	padding-bottom: 10px;
	border-bottom: 1px solid #DDD;
}

.formulario-tramite-show{
	padding: 10px;
	background-color: #fff;
	border: 5px solid #ddd;
	margin-top: 100px;
	margin-bottom: 80px;
	height: 400px;
	margin-left: 80px;
	margin-right: 80px;
	}
.titulo-paso {
	font-size: 32px;
	color: #FFF;
	font-weight: normal;
	text-transform: capitalize;
	text-align: center;
	background-color: #62A7BD;
	margin-top: 20px;
}
.div-descripcion-paso {
	float: left;
	width: 69%;
	height: 75%;
	margin-top: 20px;
}
.descripcion-paso {
	font-size: 14px;
	color: #555;
	font-weight: normal;
	margin-right: 80px;
	text-align: justify;
}
</STYLE>
</head>
<body>

<jsp:include page="../header.jsp" />
<c:set var="totalPasos" value="${fn:length(guiaTramite.pasos)}"/>	
<!-- 	<div class="titulo-general"> -->
<%--   		<span>${guiaTramite.titulo}</span> --%>
<!--   	</div> -->
<!-- <div class="titulo-component"></div> -->
<!-- 	<hr> -->
<div class="formulario-tramite" style="margin-top: 0px; border: none;padding: 0px;margin-left: 0px;margin-right: 0px;padding-top: 50px;">

<div id="pasoGuiaTramite0" class="formulario-tramite-show" style="margin-top: 0px;">
	 <p class="titulo-paso">
	     ${fn:toLowerCase(guiaTramite.titulo)} 
	 </p>
	
	<div style="float: left; width: 30%; height: 75%; text-align: center;">
		<p style="font-size: 22px; color:rgb(134, 139, 134); font-weight: bold;">
		¿En que consiste?
		</p>
	</div>
	<div class="div-descripcion-paso">
		<p class="descripcion-paso">${guiaTramite.descripcion}</p>
	</div>

	<c:if test='${!empty guiaTramite.pasos}'>
	    <a title="Siguiente" href="#pasoGuiaTramite1" onclick="scrollToStep(event);">
	    	<img href="#pasoGuiaTramite1" onclick="scrollToStep(event);" src="<c:url value='/img/icons/scroll-down.png'/>"></a>
	</c:if> 
</div>
    
<c:forEach items="${guiaTramite.pasos}" var="paso" varStatus="status">
    
    <div id="pasoGuiaTramite${status.index+1}" class="formulario-tramite-show">
    	
    	 <p class="titulo-paso">
	     ${fn:toLowerCase(paso.nombre)}
	 	</p>
		<div style="float: left; width: 30%; height: 75%; text-align: center;"></div>
		
		<div class="div-descripcion-paso">
			<p class="descripcion-paso">${paso.descripcion}</p>
		</div>
		
		<c:choose>
			<c:when test="${status.index < (totalPasos - 1)}">
				<a href="#pasoGuiaTramite${status.index+2}" onclick="scrollToStep(event);">
					<img href="#pasoGuiaTramite${status.index+2}" onclick="scrollToStep(event);" src="<c:url value='/img/icons/scroll-down.png'/>">
				</a>	
			</c:when>
			<c:when test="${status.index == (totalPasos - 1)}">
				<a href="#divHeader" title="Volver a inicio" onclick="scrollToStep(event);">
					<img href="#divHeader" 
			     		 title="inicio" 
			     		 onclick="scrollToStep(event);" 
			     		 src="<c:url value='/img/icons/scroll-up.png'/>">
				</a>
			</c:when>
		</c:choose>
		<a href="#pasoGuiaTramite${status.index}" title="anterior" onclick="scrollToStep(event);">
			<img href="#pasoGuiaTramite${status.index}" 
			     title="anterior" 
			     onclick="scrollToStep(event);" 
			     src="<c:url value='/img/icons/scroll-up.png'/>">
		</a>
	</div>
    </c:forEach>
</div>
	<jsp:include page="../bottom.jsp" />
</body>
</html>
