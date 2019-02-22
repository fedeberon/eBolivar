<%--
  Created by IntelliJ IDEA.
  User: Lucas Cortés
  Date: 22/02/2019
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Asociar Localidad</title>
</head>

<body>

<div style="margin-left: 80px; margin-right: 80px; padding-top:10px; background-color: white; ">
    <div class="page-header">
        <hr>
        <span class="titulo-descripcion" style="padding-left: 10px;">Asociar Localidad</span>
    </div>


    <form:form action="save" modelAttribute="personaAsociada" method="post">
        <input type="hidden" name="administradorCuenta.username" value="${usuario.username}" />
        <p class="odd">
            <label class="campo">Nombre:</label>
            <label class="campo">${usuario.username}</label>
        </p>
        <br/>
        <p class="odd">
            <label class="campo">Apellido:</label>
            <label class="campo">${usuario.apellido}</label>
        </p>
        <br/>
        <p class="odd">
            <label class="campo">Localidad:</label>

        <div class="col-xs-2">
            <!-- Single button -->
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    DDJJ Anteriores<span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/create/byPersona?idPersona=${ddjjs[0].persona.idPersona}&anio=2018&idPadron=${ddjjs[0].padron.id}'/>">DDJJ 2018</a></li>
                    <li role="separator" class="divider"></li>
                    <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/create/byPersona?idPersona=${ddjjs[0].persona.idPersona}&anio=2017&idPadron=${ddjjs[0].padron.id}'/>">DDJJ 2017</a></li>
                    <li role="separator" class="divider"></li>
                    <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/create/byPersona?idPersona=${ddjjs[0].persona.idPersona}&anio=2016&idPadron=${ddjjs[0].padron.id}'/>">DDJJ 2016</a></li>
                </ul>
            </div>
        </div>

        </p>
        <br/>

        <button type="submit" class="btn btn-lg btn-primary btn-block">Agregar</button>

    </form:form>

</div>

<jsp:include page="../bottom.jsp"/>

</body>

</html>
