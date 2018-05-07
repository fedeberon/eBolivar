<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Nueva Guia de Tramite</title>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/img/icons/logo.ico'/>">
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" />

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
  <span>Nueva Guia de Tr&aacute;mites</span>
  <hr>
</div>
  
<c:if test="${! empty errorMessage}">
  <div class="errorBox">
  <ul>
    <li>${errorMessage}</li>
  </ul>
  </div>
</c:if> <spring:hasBindErrors name="guiaTramite">
  <div class="errorBox">
  <ul>
    <c:forEach items="${errors.allErrors}" var="error">
      <li>${error.defaultMessage}</li>
    </c:forEach>
  </ul>
  </div>
</spring:hasBindErrors> 

<div class="formulario-tramite">
<form:form name="form" method="post" commandName="guiaTramite"
            cssStyle="padding: 10px;">
	  <p class="odd" style="border-bottom: 1px solid #c1c1c1;">
		  <span class="spanClass">
			Tipo tr&aacute;mite
		  </span> 
      <form:select path="tipo.codigo" >
      <form:options items="${tiposGuias }"  itemValue="codigo" itemLabel="nombre"/>
      </form:select>
      </p>
	  
	  <p class="odd" style="border-bottom: 1px solid #c1c1c1;">
		  <span class="spanClass">
		  T&iacute;tulo
		  </span>
      <form:input path="titulo"/>
      </p>
      
      <p class="odd" style="border-bottom: 1px solid #c1c1c1;">
		  <span class="spanClass">
		  En que consiste
		  </span>
      <form:textarea path="descripcion" cssStyle="height: 100px;"/>
      </p>
  
	<div id="pasos">

	<c:set var="index" value="${1}"/> 
	
	<p class="odd" style="border-bottom: none;">
		<span class="spanClass">
		  <span style="font-size: 35px;color: #39B5C3;">${index}</span> 
		</span>
        <input name="PASO${index}"  style="border: 1px solid #cccccc;height: 34px;" />
    </p>
    
	<p class="odd" style="border-bottom: 1px solid #c1c1c1;">
		<span class="spanClass">
		  Descripci&oacute;n
		</span>
        <textarea name="DESCRIPCION${index}" style="height: 100px;"></textarea>
        <input name="urlImg${index}" type="hidden">
        <img src="/img/default-256.png" onclick="cargarImagenPaso(${index});" style="width: 100px; cursor: hand;">
    </p>
  </div>

<input type="hidden" id="pasosIndex" name="pasosIndex" value="${index}"/>
 <div id="botonera" style="padding-left: 100px;">
<%--         <sec:authorize ifAllGranted="ROLE_WRITE_GuiaTramite"> --%>
		<a class="button" href="#" onclick="addPaso();" style="width: 200px;">
            Agregar Paso
        </a>
        <a class="button" onclick="document.form.submit();" style="width: 200px;">
            Guardar
        </a>
<%--         </sec:authorize> --%>
		<a class="button" href="javascript:history.back()" style="width: 200px;">
            Cancelar
        </a>
    </div>
    <DIV style="clear:both;"></DIV>
</form:form>

</div>
        
  <jsp:include page="../bottom.jsp" />
<SCRIPT type="text/javascript">
$('#descripcion').wysiwyg();
</SCRIPT>

<div id="pasoTramiteTemplate" style="display:none;">
	<p class="odd" style="border-bottom: none;">
		<span id="nroPasoTemplate" class="spanClass" style="font-size: 35px;color: #39B5C3;">
		  
		</span>
        <input id="nombrePasoTemplate" name=""  style="border: 1px solid #cccccc;height: 34px;" />
    </p>
    
	<p class="odd" style="border-bottom: 1px solid #c1c1c1;">
		<span class="spanClass">
		  Descripci&oacute;n
		</span>
        <textarea id="descripcionPasoTemplate" name="" style="height: 100px;"></textarea>
        <input name="urlImgTemplate" type="hidden">
        <img src="/img/default-256.png" style="width: 100px; cursor: hand;">
    </p>
</div>

</body>
</html>

