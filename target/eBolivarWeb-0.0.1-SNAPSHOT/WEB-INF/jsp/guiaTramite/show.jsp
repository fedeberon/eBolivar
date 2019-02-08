<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Ver Guia Tramite</title>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/img/icons/logo.ico'/>">
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
</STYLE>
</head>
<body>

	<jsp:include page="../header.jsp" />

	<div class="titulo-general">
  		<span>Detalle de tr&aacute;mite</span>
  	</div>
	<hr>
<div class="formulario-tramite">
	<form:form name="form" method="post"  commandName="guiaTramite"
            cssStyle="padding: 10px;">
            
	<form:hidden path="codigo"/>
	
	
	 <p class="odd">
			  <span class="spanClass">
			  T&iacute;tulo
			  </span>
	      <form:input path="titulo"/>
	</p>
	
	<p class="odd">
		  <span class="spanClass">
		  En que consiste
		  </span>
      <form:textarea path="descripcion" cssStyle="height: 100px;"/>
    </p>
    
    <c:forEach items="${guiaTramite.pasos}" var="paso" varStatus="status">
    <div id="pasoGuiaTramite${status.index}">
		<p class="odd" style="border-bottom: none;">
			<span  class="spanClass">
			  	<span style="font-size: 35px;color: #39B5C3;padding: 0 5 0 5;">${status.index + 1}</span>
			</span>
			<form:input path="pasos[${status.index}].nombre"/>
	    </p>
	    
		<p class="odd" style="border-bottom: 1px solid #c1c1c1;">
			<span class="spanClass">
			  Descripci&oacute;n
			</span>
	        <form:textarea path="pasos[${status.index}].descripcion" cssStyle="height: 100px;"/>
	        <form:hidden path="pasos[${status.index}].urlImg" />
        	<img src="/img/${paso.urlImg == null ? 'default-256.png' : paso.urlImg }" onclick="cargarImagenPaso(${status.index});" style="width: 100px; cursor: hand;">
	    </p>
	</div>
    </c:forEach>

	<div id="botonera">
		<a class="button" href="javascript:history.back()" style="width: 200px;">Cancelar</a>
<%-- 		<sec:authorize ifAllGranted="ROLE_WRITE_GuiaTramite"> --%>
		<a class="button" href="update?idGuiaTramite=${guiaTramite.codigo}" style="width: 200px;">Guardar</a>
<%-- 		</sec:authorize> --%>
	</div>
	</form:form>
	 <DIV style="clear:both;"></DIV>
	 </div>
	<jsp:include page="../bottom.jsp" />
</body>
</html>
