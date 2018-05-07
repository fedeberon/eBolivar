<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<jsp:include page="../header.jsp" />
<html>
<head>
<title>${initParam['AppName']} - Notificaciones</title>
    <script>
        var urlArchivoLenguaje = '<c:url value="/css/Spanish.json" />'
        $(document).ready(function()  {
            $('#listado').DataTable({
                language:{ "url": urlArchivoLenguaje },
                "dom": '<"top"if>t<"bottom"p><"clear">'
            });
        } );

    </script>

</head>
<body>
  <div class="titulo-general">
  <span>Notificaciones</span>
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
      <c:if test='${empty notificacionPadrons}'>
        <tr>
          <td colspan="7"><br>
          <br>
          <h3>No se encontraron Notificaciones</h3>
          </td>
        </tr>
      </c:if>
      <c:forEach items="${notificacionPadrons}" varStatus="status" var="bo">

          <tr ${bo.confirmado eq null ? 'style="font-style: italic; font-weight: bold;cursor: pointer;"' : 'style="cursor: pointer;"'} onclick="window.location.href='show?idNotificacionPadron=${bo.id}'">
          
            <td>
            <a href="show?idNotificacionPadron=${bo.id}">
            ${bo.id}
            </a>
            </th>
            <c:forEach items="${mostrar}" varStatus="status" var="campo">
				<c:if test="${'Padron' eq campo}">
            <td>${bo.padron}</td> 
            </c:if>
		  <c:if test="${'Email' eq campo}">
            <td>${bo.direccionEnvio}</td>
            </c:if>
		  <c:if test="${'Estado' eq campo}">
            <td>${bo.estado}</td>
            </c:if>
		  <c:if test="${'Fecha_Alta' eq campo}">
            <td>${bo.fechaAlta}</td>
            </c:if>
		  <c:if test="${'Confirmado' eq campo}">
            <td>${bo.confirmado}</td>
            </c:if>
		  <c:if test="${'Tasa' eq campo}">
            <td>${bo.tasa}</td>
            </c:if>
		  <c:if test="${'Nombre_Apellido' eq campo}">
            <td>${bo.nombreApellido}</td>
            </c:if>
		  
            </c:forEach>
            <td>
            <sec:authorize ifAllGranted="ROLE_WRITE_NotificacionPadron">
            <a onclick="return confirm('Esta seguro que desea eliminar?')" href="delete?idNotificacionPadron=${bo.id}"><img class="minibutton" alt="Eliminar" src="<c:url value='/images/icons/cross.png'/>"/></a>
            </sec:authorize>
            </td>
          </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<div id='botonera'>
      <a class="button" href="create"><img src="/eBolivar/img/icons/add.png"/>Nueva Notificacion</a>
</div>

  <br clear="all">
  <br>
  <jsp:include page="../bottom.jsp" />

</body>
</html>
