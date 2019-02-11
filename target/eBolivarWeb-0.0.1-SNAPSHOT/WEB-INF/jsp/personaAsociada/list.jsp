<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title>Personas asociadas</title>

    <style>

        #formulario{
            padding-bottom:0px;
            padding-right:0px;
        }

        #formulario td{
            text-align:center;
        }

    </style>

</head>
<body>
<div class="page-header">
    <span class="titulo-descripcion" style="text-align: center"><h2>Lista de Personas Asociadas</h2></span>
</div>

<div id="formulario">

    <div class="row">


        <div class="col-md-12">

            <table class="table">
                <thead>
                <tr>
                    <th>CUIT</th>
                    <th>Nombre</th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <c:if test='${empty personasAsociadas}'>
                    <tr>
                        <td colspan="3"><br>
                            <br>
                            <h3>No se encontraron CUITs Asociados</h3>
                        </td>
                    </tr>
                </c:if>
                    <c:forEach items="${personasAsociadas}" var="bo">
                        <tr>
                            <td>${bo.persona.idPersona}</td>
                            <td>${bo.persona.nombre} ${bo.persona.apellido}</td>
                            <%--<td><a href="<c:url value='/webapp/ddjj/declaracionJurada/byPersona?cuit=${bo.persona.idPersona}'/>">Ver</a></td>--%>
                            <td><a class="btn btn-info" href="<c:url value='/webapp/padron/byPersona?idPersona=${bo.persona.id}'/>">Padrones</a></td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

        </div>

    </div>
</div>

<div id='botonera'>
    <a href="javascript:history.back()" class="btn btn-default">Volver</a>
</div>

<jsp:include page="../bottom.jsp"/>
</body>
</html>
