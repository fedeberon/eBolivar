<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<jsp:include page="../header.jsp" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        });
    </script>

    <style>
    #scrollable{
        padding-right: 0%;
    }

    #paginador{
        margin-left: 85px;
    }

    #botonesBottom{
        margin-left: 15px;
    }


    </style>


</head>
<body>
  <div class="titulo-general">
  <span>Notificaciones</span>
  </div>

  <div id="container">
      <div class="row">

               <form:form action="../notificacionPadron/list" method="post" name="formBuscar">
                    <input type="hidden" name="page" value="${page}"/>
                        <div class="col-md-6">
                            <input type="text" name="value" value="${value}" class="form-control" placeholder="Ingrese su b&uacute;squeda"/>
                        </div>

                        <div class="col-md-2">
                            <button type="submit" class="btn btn-sm btn-primary" onclick="cleanPageNumber()">Buscar Valor</button>
                        </div>


                        <div class="col-md-1">
                       <a href="../notificacionPadron/list" class="btn btn-sm btn-primary">Borrar Filtro</a>
                   </div>
               </form:form>
          <br/>

<div id="scrollable">
    <table id="listado" id="myTable" class="table table-striped" border="0" cellpadding="0" cellspacing="1">
    <thead>
       <tr>
            <th scope="col">Id</th>
            <th scope="col">mail</th>
            <th scope="col">padron</th>
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
                <a href="show?idNotificacionPadron=${bo.id}"> ${bo.id} </a>
              </td>

             <td>
                ${bo.direccionEnvio}
             </td>

             <td>
                ${bo.padron}
             </td>

          </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

      <div id="paginador">

          <c:choose>
              <c:when test="${page > 1}">
                  <div class="col-xs-2">
                      <a onclick="pagAnterior()" href="#" class="btn btn-block btn-primary">Atras</a>
                  </div>
              </c:when>
              <c:otherwise>
                  <div class="col-xs-2">
                      <a class="btn btn-block btn-primary disabled" aria-disabled="true" >Atras</a>
                  </div>
              </c:otherwise>
          </c:choose>

          <c:choose>
              <c:when test="${not empty notificacionPadrons && notificacionPadrons.size() == 5}">
                  <div class="col-xs-2">
                      <a onclick="pagSiguiente()" href="#" class="btn btn-block btn-primary">Siguiente</a>
                  </div>
              </c:when>

              <c:otherwise>
                  <div class="col-xs-2">
                      <a class="btn btn-block btn-primary disabled" aria-disabled="true">Siguiente</a>
                  </div>
              </c:otherwise>
          </c:choose>

        <br>
        <br>


    <div id="botonesBottom">

        <a class="button" href="create"><img src="/eBolivar/img/icons/add.png"/>Nueva Notificacion</a>
        <a class="button" href="<c:url value='/webapp/rentas/menu'/>">Volver</a>
    </div>
      </div>


  </div>
  <br clear="all">
  <br>
  <jsp:include page="../bottom.jsp" />
                </div>
            </div>
        </div>
    </div>
</body>
</html>
