<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp" />
<html>
<head>
    <title>${initParam['AppName']} - Personas</title>
</head>
<body>
<div class="titulo-general">
    <span>Personas</span>
</div>


<div id="scrollable">
    <table class="table table-striped" border="0" cellpadding="0" cellspacing="1">
        <thead>
        <tr>
            <th scope="col">DNI</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
            <th scope="col">Actividad</th>
            <th scope="col">Tipo</th>
            <th scope="col">Razon Social</th>
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
                    <a href="<c:url value='/webapp/personas/get?id=${bo.id}'/>">${bo.numeroDocumento}</a>
                </td>
                <td>${bo.nombre}</td>
                <td>${bo.apellido}</td>
                <td>${bo.descripcionActividadPrincipal}</td>
                <td>${bo.tipoPersona}</td>
                <td>${bo.razonSocial}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id='botonera'>
    <a class="btn btn-default" href="javascript:history.back()">Volver </a>
</div>

<br clear="all">
<br>
<jsp:include page="../bottom.jsp" />

</body>
</html>
