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
    <span class="titulo-descripcion" style="text-align: center"><h2>Declaraciones Juradas Presentadas</h2></span>
</div>

<div class="panel panel-default">
    <div class="panel-body">
        CUIT: ${persona.idPersona} - Titular ${persona.nombre}  ${persona.apellido}
    </div>
</div>


<div id="formulario">

    <div class="row">

        <form action="/rentas/webapp/ddjj/buscar" method="post">

            <div class="col-md-6">
                <input name="valor" class="form-control" placeholder="Ingrese el valor a buscar"/>
            </div>

            <div class="col-md-3">
                <button type="submit" class="btn btn-block btn-primary">Buscar</button>
            </div>

        </form>

        <br/>
        <hr>
        <br/>

        <div class="col-md-12">

                <table class="table">
            <thead>
            <tr>
                <th>Codigo Interno</th>
                <th>Nombre y Apellido</th>
                <th>Padron</th>
                <th>Tasa</th>
                <th>Fecha</th>
                <th>Periodo</th>

            </tr>
            </thead>

            <tbody>
                <c:forEach items="${ddjjs}" var="bo">
                    <tr>
                        <td><a href="<c:url value='/webapp/ddjj/show?id=${bo.id}'/>">${bo.id}</a></td>
                        <td>${bo.persona.nombre} ${bo.persona.apellido}</td>
                        <td>${bo.padron.numero}</td>
                        <td>${bo.padron.tipoImpuesto.nombre}</td>
                        <td>${bo.fecha}</td>
                        <td>${bo.periodo}</td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>

        </div>

        <div class="col-xs-12">
            <div class="col-xs-2">
                <a href="<c:url value='/webapp/ddjj/list?page=${page - 1}'/>" class="btn btn-block btn-primary">Atras</a>
            </div>
            <div class="col-xs-2">
                <a href="<c:url value='/webapp/ddjj/list?page=${page + 1}'/>" class="btn btn-block btn-primary">Siguiente</a>
            </div>


            <div class="col-xs-2">
                <!-- Single button -->
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Nueva Declaraci&oacute;n Jurada <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li> <a href="<c:url value='/webapp/declaracionJurada/create/byPersona?idPersona=${persona.idPersona}'/>">Bimestral</a></li>
                        <li role="separator" class="divider"></li>

                        <li> <a href="<c:url value='/webapp/declaracionJurada/create/byPersona?idPersona=${persona.idPersona}'/>">Anual</a></li>
                        <li role="separator" class="divider"></li>

                        <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/create/byPersona?idPersona=${persona.idPersona}&anio=2017'/>">Anual 2017</a></li>
                        <li role="separator" class="divider"></li>

                        <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/create/byPersona?idPersona=${persona.idPersona}&anio=2016'/>">Anual 2016</a></li>
                        <li role="separator" class="divider"></li>
                    </ul>
                </div>
            </div>

        </div>

    </div>
</div>
<jsp:include page="../bottom.jsp"/>
</body>
</html>