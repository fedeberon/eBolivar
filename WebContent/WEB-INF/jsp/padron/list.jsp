<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<html>
<head>
    <div class="page-header">
        <span class="titulo-descripcion" style="text-align: center"><h2>Padrones</h2></span>
    </div>

    <script>

        function search() {
            $('#page').val(1);
            $('#formBuscar').submit();
        }

    </script>

</head>
<body>
<div class="container">
        <div class="row">
            <div class="col-md-10">

                <form:form commandName="searchObject" action="search" method="post" id="formBuscar">
                    <input type="hidden" value="${searchObject.page}" name="page" id="page"/>

                <div class="input-group">
                    <div class="input-group-btn">
                        <a href="<c:url value='/webapp/padron/list'/>" class="btn btn-default">
                            Todos
                        </a>
                        <button onclick="search()"  class="btn btn-default" tabindex="6">
                            Buscar
                        </button>
                    </div>
                    <input type="text" name="padron.numero" class="form-control tool process" value="${searchObject.padron.numero}" placeholder="Ingrese n&uacute;mero de padr&oacute;n" tabindex="5"/>
                </div><!-- /input-group -->
                </form:form>
            </div>
        </div>

        <hr>

 <div class="col-md-12">
    <table class="table table-striped" border="0" cellpadding="0" cellspacing="1" style="text-align: center">
        <thead>
        <tr>
            <th scope="col">Padr&oacute;n</th>
            <th scope="col">Tipo</th>
            <th scope="col">Localidad</th>
        </tr>
        </thead>


        <tbody>
        <c:if test='${empty padrones}'>
            <tr>
                <td colspan="5"><br>
                    <br>
                    <h3>No se encontraron Datos</h3>
                </td>
            </tr>
        </c:if>
        <c:forEach items="${padrones}" varStatus="status" var="bo">
            <tr>
                <td>
                    ${bo.numero}
                </td>
                <td>${bo.tipoImpuesto.nombre}</td>
                <td>
                    ${bo.localidad.nombre}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

 </div>

 <div class="row">
    <div class="col-md-12">

    <div class="col-xs-2">
        <a class="btn btn-default" href="javascript:history.back()">Volver </a>
    </div>

    <div class="col-xs-2">
        <a class="btn btn-default" href="<c:url value='/webapp/padron/create'/>">Nuevo </a>
    </div>

        <c:choose>
            <c:when test="${searchObject.page > 1}">
                <div class="col-xs-2">
                    <a onclick='pagAnterior();' href="#" class="btn btn-block btn-primary">Atras</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-xs-2">
                    <a class="btn btn-block btn-primary disabled" aria-disabled="true" >Atras</a>
                </div>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${not empty padrones && padrones.size() == 5}">
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

    <%--<div class="col-xs-2">--%>
            <%--<label>Pagina: ${searchObject.page}</label>--%>
    <%--</div>--%>

    </div>
 </div>

        <br clear="all">
<br>
<jsp:include page="../bottom.jsp" />

</div>
</body>
</html>
