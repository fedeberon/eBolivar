<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${initParam['AppName']} - GuiaTramites</title>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/img/icons/logo.ico'/>">
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />
<script type="text/javascript" src="<c:url value='/js/jquery-latest.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.tablesorter.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/listings.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css" media="print, projection, screen" />
<script type="text/javascript">
  $(document).ready(function() {
        $("#listado")
        .tablesorter({widgets: ['zebra']});
    } );
</script>

</head>
<body>
  <jsp:include page="../header.jsp" />

<div class="titulo-general">
<div style="float: right;">
	    <img style="width: 180px; margin-left: -100px; margin-right: 30px;" src="<c:url value='/img/tramites.png'/>" >
    </div>
  <span>Guia de Tr&aacute;mites</span>
<p class="sub-titulo">Una gu&iacute;a paso a paso de los procedimientos administrativos en Bolivar</p>
<p class="descripcion-titulo"> es una base de datos en línea concebida para aportar una total transparencia en los procedimientos administrativos en Bolivar</p>

<!-- <p class="odd" style="border-bottom: none;"> -->
<!-- 		<span class="spanClass"> -->
<!-- 		  Busc&aacute; tu tr&aacute;mite  -->
<!-- 		</span> -->
<!--         <input style="border: 1px solid #cccccc;height: 34px;" /> -->
<!--     </p> -->
<div style="clear:both;">
    </div>
</div>

<div id="scrollable" style="border-top: 1px #ccc solid;">
 
  
<c:forEach items="${tiposGuiaTramites}" var="tipoGuia">
<div class="div-tramites">
	<div class="titulo-component">
		${tipoGuia.nombre}
	</div>
	
	<div style="height: 200; overflow-x: auto;">
	<table style="width: 100%;" 
	         class="table table-hover">
	    <tbody>
	    	<c:forEach items="${guiaTramites}" varStatus="status" var="guia" >
	    	<c:if test="${guia.tipo.codigo == tipoGuia.codigo}">
	         	<tr style="border-bottom: 1px solid #F5F5F5;">
	         		<td><a href="showTramite?idGuiaTramite=${guia.codigo}">${guia.titulo}</a></td>
	         		  <sec:authorize ifAllGranted="ROLE_WRITE_RECLAMOS">
	         		<td style="width: 60px; text-align: center;">
	         			<a href="show?idGuiaTramite=${guia.codigo}"
	         			   title="Editar">
			            	<img class="minibutton" alt="Editar" src="<c:url value='/img/icons/edit_icon.png'/>"/>
			            </a>
			            <a onclick="return confirm('Esta seguro que desea eliminar?')" 
			               href="delete?idGuiaTramite=${guia.codigo}"
			               title="Eliminar">
			            	<img class="minibutton" alt="Eliminar" src="<c:url value='/img/icons/delete_icon.png'/>"/>
			            </a>
	         		</td>
	         		</sec:authorize>
	         	</tr>
	         </c:if>
	         </c:forEach>
	    </tbody>
	</table>
	</div>
</div>
</c:forEach>
  

</div>

<div id='botonera'>
<%--   <tags:paginador noMorePages="${noMorePages}" page="${page}"/> --%>
  <!-- <a class="button" onclick="document.forms[0].vista.value='PRINT';document.forms[0].submit();"  target="_blank">
      <img  src="<c:url value='/images/icons/report.png'/>" /> Vista Impresion</a>-->
     
       <sec:authorize ifAllGranted="ROLE_WRITE_RECLAMOS">
  <a class="button" onclick="document.forms[0].vista.value='EXCEL';document.forms[0].submit();"  target="_blank">
      <img  src="<c:url value='/images/icons/page_white_excel.png'/>" />Exportar</a>
<%--       </sec:authorize> --%>
<%--   <sec:authorize ifAllGranted="ROLE_WRITE_GuiaTramite"> --%>
      <a class="button" href="create" style="width: 200px; margin-left: 80px;">Nuevo Tr&aacute;mite</a>

  		</sec:authorize>
</div>

  <br clear="all">
  <br>
  <jsp:include page="../bottom.jsp" />

</body>
</html>
