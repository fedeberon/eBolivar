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
            <c:forEach items="${ddjj}" var="bo">
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

        </div>

    </div>
</div>
<jsp:include page="../bottom.jsp"/>
</body>
</html>