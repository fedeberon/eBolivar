<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<html>
<head>
    <title>${initParam['AppName']} - Personas</title>


</head>
<body>
<div class="titulo-general">
    <span>Personas</span>
</div>


<form:form commandName="searchObject" action="search" method="post" id="formBuscar">
    <input type="hidden" value="${searchObject.page}" name="page"/>
    <div class="container">


        <table class="table-bordered">
            <tr>
                <td>
                    <label for="persona.nombre" style="margin-left: 5px;" class="col-sm-2 control-label">Nombre</label>
                </td>
                <td>
                    <form:input path="persona.nombre" cssClass="form-control"/>
                </td>
                <td>
                    <label for="persona.nombre" style="margin-left: 5px;"  class="col-sm-2 control-label">Apellido</label>
                </td>
                <td>
                    <form:input path="persona.apellido" cssClass="form-control"/>
                </td>
                <td>
                    <label for="persona.nombre" style="margin-left: 5px;"  class="col-sm-2 control-label">CUIT</label>
                </td>
                <td>
                    <form:input path="persona.idPersona" cssClass="form-control"/>
                </td>
                <td>
                    <button type="submit" class="btn btn-default">Buscar</button>
                </td>
                <td>
                    <a href="<c:url value='/webapp/personas/list'/>" class="btn btn-default">TODOS</a>
                </td>

            </tr>
        </table>

    </div>


</form:form>


<div id="scrollable" style="padding-right:0px;">

    <table class="table table-striped" border="0" cellpadding="0" cellspacing="1" style="text-align: center">
        <thead>
        <tr>
            <th scope="col">DNI</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
            <th scope="col">Actividad</th>
            <%--<th scope="col">Tipo</th>--%>
            <%--<th scope="col">Razon Social</th>--%>
        </tr>
        </thead>


        <tbody>
        <c:if test='${empty personas}'>
            <tr>
                <td colspan="5"><br>
                    <br>
                    <h3>No se encontraron Datos</h3>
                </td>
            </tr>
        </c:if>
        <c:forEach items="${personas}" varStatus="status" var="bo">
            <tr>
                <td>
                    <a href="<c:url value='/webapp/personas/get?id=${bo.id}'/>">${bo.idPersona}</a>
                </td>
                <td>${bo.nombre}</td>
                <td>${bo.apellido}</td>
                <td>${bo.descripcionActividadPrincipal}</td>
                    <%--<td>${bo.tipoPersona}</td>--%>
                    <%--<td>${bo.razonSocial}</td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id='botonera'>
    <a class="btn btn-default" href="javascript:history.back()">Volver </a>

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
        <c:when test="${not empty personas && personas.size() == 5}">
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


</div>

<br clear="all">
<br>
<jsp:include page="../bottom.jsp" />

</body>
</html>
