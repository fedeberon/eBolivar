<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
<jsp:include page="../header.jsp"/>
<html>
<body>
<div class="page-header">
    <span class="titulo-descripcion" style="text-align: center"><h3>Padrones Asociados</h3></span>
</div>

<div id="formulario">

    <div class="row">


        <table class="table table-bordered" style="margin-left: 15px">
            <tr>
                <th colspan="4">Datos del Padron Asociado</th>
            </tr>

            <tr><th>Cod Int</th><td>${padronAsociado.id}</td></tr>
            <tr><th>Tasa</th><td>${padronAsociado.leyendaDelTributo}</td></tr>
            <tr><th>Padron</th><td>${padronAsociado.padron}</td></tr>
            <tr><th>CUIT</th><td>${padronAsociado.persona.idPersona}</td></tr>
            <tr><th>Titular</th><td>${padronAsociado.persona.nombre} ${padronAsociado.persona.apellido}</td></tr>
            <tr><th>Activiadad</th><td>${padronAsociado.persona.descripcionActividadPrincipal}</td></tr>

        </table>

    </div>
</div>
<jsp:include page="../bottom.jsp"/>
</body>
</html>