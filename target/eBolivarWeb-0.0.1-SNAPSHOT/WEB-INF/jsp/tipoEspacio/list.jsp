<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${initParam['AppName']} - TipoEspacios</title>
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
<style>#mostradorTipoEspacio {
  width:0px;
  height:0px;
  display:none;
}
</style>
</head>
<body>
  <jsp:include page="../top.jsp" />
<h1>TipoEspacios</h1>

<form name="formBuscar" method="get" action="search" class="buscador">
  <input type="hidden" name="vista" value=""/>
  <input type="hidden" name="mostrar" value="${inputMostrar}"/>
  <input name="page" type="hidden" value="${page}"/>
  <div id="botonera">

  <select name="campo" class="buscador">
    <option value="TODOS"
      <c:if test="${campo == 'todos'}">
                  selected="true"
              </c:if>>Todos</option>
    <option value="Id"
      <c:if test="${campo == 'Id'}">
                  selected="true"
              </c:if>></option>
    <option value="id"
      <c:if test="${campo == 'id'}">
                  selected="true"
              </c:if>>id</option>
  </select>
  <select name="ubicacion" class="buscador">
    <option value="ANYWHERE"
      <c:if test="${ubicacion == 'ANYWHERE'}">
                  selected="true"
              </c:if>>que contenga</option>
    <option value="START"
      <c:if test="${ubicacion == 'START'}">
                  selected="true"
              </c:if>>que comience</option>
    <option value="EXACT"
      <c:if test="${ubicacion == 'EXACT'}">
                  selected="true"
              </c:if>>igual</option>
  </select> <input type="text" name="valor" value="${valor}" class="buscador" />

  <a class="searchbutton" onclick="document.forms[0].page.value='';document.forms[0].vista.value='';document.forms[0].submit();"> Buscar</a>
  <a class="searchbutton" href="list"> Todos </a>
  <br/><br/>
  <a class="searchbutton" onclick="displayMostrador('#mostradorTipoEspacio','#btnMostrarTipoEspacio','60px');" id="btnMostrarTipoEspacio">Datos TipoEspacio</a>
  <a class="searchbutton" onClick="document.forms[0].vista.value='';document.forms[0].submit();"> Mostrar </a>
  </p>

    <div id="mostradorTipoEspacio">
    <br/>
      <p><b>
       <a onclick="hideMostrador('#mostradorTipoEspacio','#btnMostrarTipoEspacio');" id="btnHideMostrar">
            <img alt="Ocultar Datos" width="12px" height="12px" src="<c:url value='/images/icons/cross.png'/>"/>
          </a>
        Datos TipoEspacio:</b>
         <tags:inputMostrar attrib="${'nombre'}"><c:if test="${ nombre eq 'on'}">checked="true"</c:if></tags:inputMostrar>
				  
        <br/>

  </div>

  </div>
  </form>
<div id="scrollable">
  <table id="listado" id="myTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
    <thead>
       <tr>
       <th scope="col">Id</th>
         <c:forEach items="${mostrar}" varStatus="status" var="campo">
         <th scope="col">${campo}</th>
         </c:forEach>
        <th scope="col"></th>
      </tr>
    </thead>
    <tbody>
      <c:if test='${empty tipoEspacios}'>
        <tr>
          <td colspan="5"><br>
          <br>
          <h3>No se encontraron tipoEspacios</h3>
          </td>
        </tr>
      </c:if>
      <c:forEach items="${tipoEspacios}" varStatus="status" var="bo">

          <tr>
            <td>
            <a href="show?idTipoEspacio=${bo.id}">
            ${bo.id}
            </a>
            </th>
            <c:forEach items="${mostrar}" varStatus="status" var="campo">
				<c:if test="${'nombre' eq campo}">
            <td>${bo.nombre}</td>
            </c:if>
		  
            </c:forEach>
            <td>
            <sec:authorize ifAllGranted="ROLE_WRITE_TipoEspacio">
            <a onclick="return confirm('Esta seguro que desea eliminar?')" href="delete?idTipoEspacio=${bo.id}"><img class="minibutton" alt="Eliminar" src="<c:url value='/images/icons/cross.png'/>"/></a>
            </sec:authorize>
            </td>
          </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<div id='botonera'>
  <tags:paginador noMorePages="${noMorePages}" page="${page}"/>
  <!-- <a class="button" onclick="document.forms[0].vista.value='PRINT';document.forms[0].submit();"  target="_blank">
      <img  src="<c:url value='/images/icons/report.png'/>" /> Vista Impresion</a>-->
      <sec:authorize ifAllGranted="ROLE_EXPORT_EXCEL">
  <a class="button" onclick="document.forms[0].vista.value='EXCEL';document.forms[0].submit();"  target="_blank">
      <img  src="<c:url value='/images/icons/page_white_excel.png'/>" />Exportar</a>
      </sec:authorize>
  <sec:authorize ifAllGranted="ROLE_WRITE_TipoEspacio">

      <a class="button" href="create"><img src="<c:url value='/images/icons/add.png'/>"/>Nuevo TipoEspacio</a>

  </sec:authorize>
</div>

  <br clear="all">
  <br>
  <jsp:include page="../bottom.jsp" />

</body>
</html>
