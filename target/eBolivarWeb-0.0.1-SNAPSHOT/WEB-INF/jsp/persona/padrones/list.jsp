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
            <th scope="col">CUIT</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>


        <tbody>
        <c:if test='${empty padronAsociado}'>
            <tr>
                <td colspan="6"><br>
                    <br>
                    <h3>No se encontraron Datos</h3>
                </td>
            </tr>
        </c:if>
        <c:forEach items="${padronAsociado}" varStatus="status" var="bo">
            <tr>
                <td>${bo.padron.numero}</td>
                <td>${bo.padron.tipoImpuesto.nombre}</td>
                <td>
                    <a href="<c:url value='/webapp/personas/get?id=${bo.id}'/>">${bo.persona.idPersona}</a>
                </td>
                <td>${bo.persona.nombre}</td>
                <td>${bo.persona.apellido}</td>


                <td>
                    <a class="btn btn-success" href="<c:url value='/webapp/tasas/listByPadron?idPadron=${bo.padron.id}'/>">Ver Tasas</a>
                </td>


                <c:if test="${bo.padron.tipoImpuesto.codigo == 15}">
                    <td>
                        <a class="btn btn-success" href="<c:url value='/webapp/ddjj/declaracionJurada/byPadronAsociado?idPadronAsociado=${bo.id}'/>">DDJJs</a>
                    </td>
                    <td>
                        <div class="btn-group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                Nueva Declaraci&oacute;n<span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/webapp/ddjj/declaracionJurada/bimestralByPadronAsociado?idPadron=${bo.padron.id}&idPersona=${bo.persona.id}'/>">Bimestral</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="<c:url value='/webapp/ddjj/declaracionJurada/anualByPadronAsociado?idPadron=${bo.padron.id}&idPersona=${bo.persona.id}'/>">Anual</a></li>
                                <li role="separator" class="divider"></li>
                                <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/anteriorByPadronAsociado?idPersona=${bo.persona.id}&anio=2017&idPadron=${bo.padron.id}'/>">DDJJ 2017</a></li>
                                <li role="separator" class="divider"></li>
                                <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/anteriorByPadronAsociado?idPersona=${bo.persona.id}&anio=2016&idPadron=${bo.padron.id}'/>">DDJJ 2016</a></li>
                            </ul>
                        </div>
                    </td>
                </c:if>

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
<jsp:include page="../../bottom.jsp"/>

</body>
</html>
