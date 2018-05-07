<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../header.jsp"/>
<html>
<head>
    <title>${initParam['AppName']} - Personas</title>
</head>
<body>
<div class="titulo-general">
    <span>Padrones Asociados</span>
</div>


<div id="scrollable">
    <table class="table table-striped" style="text-align: center;">
        <thead>
        <tr>
            <th scope="col">Padron</th>
            <th scope="col">Tasa</th>
            <th scope="col">DNI</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
            <th scope="col">Razon Social</th>
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
                <td>${bo.padron}</td>
                <td>${bo.leyendaDelTributo}</td>
                <td>
                    <a href="<c:url value='/webapp/personas/get?id=${bo.id}'/>">${bo.persona.numeroDocumento}</a>
                </td>
                <td>${bo.persona.nombre}</td>
                <td>${bo.persona.apellido}</td>
                <td>${bo.persona.razonSocial}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id='botonera'>
    <a href="<c:url value='/webapp//rentas/menu'/>" class="btn btn-default">Volver</a>
</div>

<br clear="all">
<br>
<jsp:include page="../../bottom.jsp"/>

</body>
</html>
