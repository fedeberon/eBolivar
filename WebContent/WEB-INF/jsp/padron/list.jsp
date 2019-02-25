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
                    <label for="padron.numero" style="margin-left: 5px;" class="col-sm-2 control-label">Nombre</label>
                </td>

                <td>
                    <form:input path="padron.numero" cssClass="form-control"/>
                </td>
                <td>
                    <button type="submit" class="btn btn-default">Buscar</button>
                </td>
                <td>
                    <a href="<c:url value='/webapp/padron/list'/>" class="btn btn-default">TODOS</a>
                </td>
            </tr>
        </table>

    </div>


</form:form>


<div id="scrollable" style="padding-right:0px;">

    <table class="table table-striped" border="0" cellpadding="0" cellspacing="1" style="text-align: center">
        <thead>
        <tr>
            <th scope="col">Padr&oacute;n</th>
            <th scope="col">Tipo</th>
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
                    <a href="<c:url value='/webapp/padron/showPadron?id=${bo.id}'/>">${bo.numero}</a>
                </td>
                <td>${bo.tipoImpuesto.nombre}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id='botonera'>

    <div class="col-xs-2">
        <a class="btn btn-default" href="javascript:history.back()">Volver </a>
    </div>

    <div class="col-xs-2">
        <a class="btn btn-default" href="<c:url value='/webapp/padron/create'/>">Nuevo </a>
    </div>

    <div class="col-xs-2">
        <a onclick='pagAnterior();' href="#" class="btn btn-block btn-primary">Atras</a>
    </div>

    <div class="col-xs-2">
        <a onclick="pagSiguiente()" href="#" class="btn btn-block btn-primary">Siguiente</a>
    </div>

</div>

<br clear="all">
<br>
<jsp:include page="../bottom.jsp" />

</body>
</html>
