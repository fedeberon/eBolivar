<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<html>
<head>
    <title>${initParam['AppName']} - Personas</title>
    <style><%@include file="../../../css/personaList.css"%></style>

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
            <th scope="col">Tipo de persona</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
            <th scope="col">Actividad</th>
            <th scope="col">IVA</th>
            <th scope="col">Monotributo</th>

            <th scope="col">Editar</th>
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
                <td>${bo.tipoDePersona}</td>
                <td>${bo.nombre}</td>
                <td>${bo.apellido}</td>
                <td>${bo.descripcionActividadPrincipal}</td>
                <td>${bo.iva}</td>
                <td>${bo.monotributo}</td>


                    <%--<td>${bo.tipoPersona}</td>--%>
                    <%--<td>${bo.razonSocial}</td>--%>
                <td>
                    <a  href="<c:url value='/webapp/personas/update?id=${bo.id}'/>"><img src="<c:url value='/img/icons/icon-edit-persona.png'/>"/></a>
                </td>
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
            <div class="col-xs-2">
                <a class="btn btn-block btn-primary" data-toggle="modal" data-target="#exampleModal">Nueva Persona<label/> </a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <%--<h3 class="modal-title text-center" id="exampleModalLabel">Notificacion para <label class="cliente"></label></h3>--%>
                <h4 class="notice notice-danger modal-title text-center" id="exampleModalLabel">
                    <strong>Crear nueva persona</strong>
                </h4>
            </div>
            <div class="modal-body">
                <form:form name="form" method="post" commandName="persona" action="save">
                    <div class="row">
                        <div class="col-md-12 form-group campo">
                            <p>
                                <label for="nombre" class="campo">Nombre:</label>
                                <form:input path="nombre" />
                                <form:errors cssClass="form-text text-muted red" path="nombre"/>
                            </p>
                        </div>
                        <div class="col-md-12 form-group campo">
                            <p class="odd">
                                <label for="apellido" class="campo">Apellido:</label>
                                <form:input path="apellido" />
                                <form:errors cssClass="form-text text-muted red" path="apellido"/>
                            </p>
                        </div>
                        <div class="col-md-12 form-group campo">
                            <p>
                                <label for="idPersona" class="campo" id="cuit">Tipo de persona:</label>
                                <form:select path="tipoDePersona" items="${tipoPersonaEnum}"/>
                                <form:errors cssClass="form-text text-muted red" path="tipoDePersona"/>
                            </p>
                        </div>
                        <div class="col-md-12 form-group campo">
                            <p>
                                <label for="idPersona" class="campo" id="cuit">C.U.I.T.:</label>
                                <form:input  id="cuit2" path="idPersona"/>
                                <form:errors cssClass="form-text text-muted red" path="idPersona"/>
                            </p>
                        </div>
                        <div class="col-md-12 form-group campo">
                            <p>
                                <label for="nombre" class="campo">Monotributo:</label>
                                <form:input path="monotributo" />
                            </p>
                        </div>
                        <div class="col-md-12 form-group campo">
                            <p>
                                <label for="nombre" class="campo">IVA:</label>
                                <form:input path="iva" />
                            </p>
                        </div>
                        <div class="col-md-12 form-group campo">
                            <div id='botonera'>
                                <a class="btn btn-default" href="javascript:history.back()">Volver </a>

                                <a onclick="document.forms['form'].submit();" class="btn btn-primary">Guardar</a>
                            </div>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
<br clear="all">
<br>
<jsp:include page="../bottom.jsp" />

</body>

</html>
