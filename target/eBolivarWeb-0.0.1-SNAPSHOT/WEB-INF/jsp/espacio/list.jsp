<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Espacios</title>
<link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />
<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.tablesorter.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/listings.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/shadowbox.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/shadowbox.css'/>" type="text/css" media="print, projection, screen" />
<script type="text/javascript">
  $(document).ready(function() {
        $("#listado")
        .tablesorter({widgets: ['zebra']});
    } );
</script>
<style>#mostradorEspacio {
  width:0px;
  height:0px;
  display:none;
}
</style>
</head>
<body>
   <jsp:include page="../header.jsp" />


<div class="titulo-general">
  <span>Espacios</span>
  <hr>
</div>

<div id="scrollable">
  <table id="listado" id="myTable" class="table table-striped" border="0" cellpadding="0" cellspacing="1">
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
      <c:if test='${empty espacios}'>
        <tr>
          <td colspan="5"><br>
          <br>
          <h3>No se encontraron espacios</h3>
          </td>
        </tr>
      </c:if>
      <c:forEach items="${espacios}" varStatus="status" var="bo">

          <tr>
            <td>
            <a href="show?idEspacio=${bo.id}">
            ${bo.id}
            </a>
            </th>
            <c:forEach items="${mostrar}" varStatus="status" var="campo">
				<c:if test="${'Titulo' eq campo}">
            <td>${bo.titulo}</td>
            </c:if>
		  <c:if test="${'Tipo' eq campo}">
            <td>${bo.tipoEspacio.nombre}</td>
            </c:if>
		  <c:if test="${'Area' eq campo}">
            <td>${bo.area}</td>
            </c:if>
		  <c:if test="${'Direccion' eq campo}">
            <td>${bo.direccion}</td>
            </c:if>
            </c:forEach>
            <td>
            <sec:authorize ifAllGranted="ROLE_WRITE_Espacio">
            <a onclick="return confirm('Esta seguro que desea eliminar?')" href="delete?idEspacio=${bo.id}"><img class="minibutton" alt="Eliminar" src="<c:url value='/images/icons/cross.png'/>"/></a>
            </sec:authorize>
            </td>
          </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<div id='botonera'>
  <tags:paginador noMorePages="${noMorePages}" page="${page}"/>
  
   <sec:authorize ifAllGranted="ROLE_WRITE_RECLAMOS">

      <a class="button" href="create">Nuevo Espacio</a>

  </sec:authorize>
</div>

  <br clear="all">
  <br>
  <jsp:include page="../bottom.jsp" />

</body>
</html>
