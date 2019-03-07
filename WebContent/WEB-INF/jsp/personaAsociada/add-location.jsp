<%--
  Created by IntelliJ IDEA.
  User: Lucas Cortés
  Date: 22/02/2019
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
<jsp:include page="../header.jsp"/>
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


     <div class="col-xs12">
        <form:form action="saveLocation" modelAttribute="usuarioLocalidad" method="post">
            <input type="hidden" name="usuario.username" value="${usuario.username}"/>

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
                <label class="campo" id="loc-style">Localidad:</label>
                <label class="campo">
                    <form:select  path="localidadAsociada.id" cssClass="form-control form-control-sm">
                        <form:options items="${localidades}" itemValue="id" itemLabel="nombre"/>
                    </form:select>
                </label>
            </p>
            <br/>
            <div class="col-xs-4">
                <button type="submit" class="btn btn-lg btn-primary btn-block">Agregar</button>
            </div>
        </form:form>
    </div>

</div>

<div id='botonera'>
    <a href="../usuario/list" class="btn btn-default">Volver</a>
</div>

<jsp:include page="../bottom.jsp"/>

</body>

</html>
