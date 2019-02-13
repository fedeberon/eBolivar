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

    <c:if test='${not empty persona}'>

        <div class="panel-body">
            CUIT: ${persona.idPersona} - Titular ${persona.nombre}  ${persona.apellido}
        </div>

    </c:if>
</div>


<div id="formulario">

    <div class="row">

            <form:form action="../ddjj/buscar" modelAttribute="ddjj" method="post">
                <div class="col-md-6">
                    <input name="valor" value="${valor}" class="form-control" placeholder="Ingrese su b&uacute;squeda"/>
                </div>

                <div class="col-md-3">
                    <button type="submit" class="btn btn-block btn-primary">Buscar</button>
                </div>
            </form:form>

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

                <c:if test='${empty ddjjs}'>
                    <tr>
                        <td colspan="6"><br>
                            <br>
                            <h3>No se encontraron Declaraciones Juradas</h3>
                        </td>
                    </tr>
                </c:if>

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

            <sec:authorize access="hasRole('ROLE_MODULO_RENTAS')">

                <c:choose>
                    <c:when test="${page > 1}">
                        <div class="col-xs-2">
                            <a href="<c:url value='/webapp/ddjj/buscar?page=${page - 1}&valor=${valor}'/>" class="btn btn-block btn-primary">Atras</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-xs-2">
                            <a class="btn btn-block btn-primary disabled" aria-disabled="true">Atras</a>
                        </div>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${not empty ddjjs && ddjjs.size() == 5}">
                        <div class="col-xs-2">
                            <a href="<c:url value='/webapp/ddjj/buscar?page=${page + 1}&valor=${valor}'/>" class="btn btn-block btn-primary">Siguiente</a>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="col-xs-2">
                            <a class="btn btn-block btn-primary disabled" aria-disabled="true">Siguiente</a>
                        </div>
                    </c:otherwise>
                </c:choose>


            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_MODULO_CONTRIBUYENTE')">

                <c:choose>
                    <c:when test="${page > 1}">
                        <div class="col-xs-2">
                            <a href="<c:url value='/webapp/ddjj/declaracionJurada/byPadronAsociado?idPadronAsociado=${padronAsociado.id}&page=${page - 1}&valor=${valor}'/>" class="btn btn-block btn-primary">Atras</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-xs-2">
                            <a class="btn btn-block btn-primary disabled" aria-disabled="true" >Atras</a>
                        </div>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${not empty ddjjs && ddjjs.size() == 5}">
                        <div class="col-xs-2">
                            <a href="<c:url value='/webapp/ddjj/declaracionJurada/byPadronAsociado?idPadronAsociado=${padronAsociado.id}&page=${page + 1}&valor=${valor}'/>" class="btn btn-block btn-primary">Siguiente</a>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="col-xs-2">
                            <a class="btn btn-block btn-primary disabled" aria-disabled="true">Siguiente</a>
                        </div>
                    </c:otherwise>
                </c:choose>

            </sec:authorize>

                <c:if test='${not empty ddjjs}'>
                    <div class="col-xs-2">
                        <!-- Single button -->
                        <div class="btn-group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                DDJJ Anteriores<span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/create/byPersona?idPersona=${ddjjs[0].persona.idPersona}&anio=2017&idPadron=${ddjjs[0].padron.id}'/>">DDJJ 2017</a></li>
                                <li role="separator" class="divider"></li>
                                <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/create/byPersona?idPersona=${ddjjs[0].persona.idPersona}&anio=2016&idPadron=${ddjjs[0].padron.id}'/>">DDJJ 2016</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-xs-2">
                        <!-- Single button -->
                        <div class="btn-group">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Nueva Declaraci&oacute;n<span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/bimestral?idDDJJ=${ddjjs[0].id}&idPersona=${ddjjs[0].persona.idPersona}&anio=2018&idPadron=${ddjjs[0].padron.id}'/>">Bimestral</a></li>
                                <li role="separator" class="divider"></li>
                                <li> <a href="<c:url value='/webapp/ddjj/declaracionJurada/anual?idDDJJ=${ddjjs[0].id}&idPersona=${ddjjs[0].persona.idPersona}&anio=2018&idPadron=${ddjjs[0].padron.id}'/>">Anual</a></li>
                            </ul>
                        </div>
                    </div>
                </c:if>



        </div>

    </div>
</div>

<div id='botonera'>
    <a href="javascript:history.back()" class="btn btn-default">Volver</a>
</div>

<jsp:include page="../bottom.jsp"/>
</body>
</html>