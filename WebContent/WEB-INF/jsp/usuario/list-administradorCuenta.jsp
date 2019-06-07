<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
<%--<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>--%>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title>Lista de Usuarios</title>

    <style>

        #formulario{
            padding-bottom:0px;
            padding-right:0px;
        }

        #formulario td{
            text-align:center;
        }

        .col-md-6 {
            margin-top: 5px;
            margin-bottom: 5px;
        }

        .col-md-3 {
            margin-top: 5px;
            margin-bottom: 5px;
        }

        .col-xs-2 {
            margin-bottom: 5px;
        }

    </style>

</head>
<body>

<div class="page-header">
    <span class="titulo-descripcion" style="text-align: center"><h2>Administradores de Cuentas</h2></span>
</div>

<div id="formulario">

    <form:form action="../administrador/buscar" method="post">
        <div class="col-md-6">
            <input class="form-control" name="valor" value="${valor}" placeholder="Ingrese su b&uacute;squeda"/>
        </div>

        <div class="col-md-3">
            <button type="submit" class="btn btn-block btn-primary">Buscar</button>
        </div>
    </form:form>


    <div class="row">


        <div class="col-md-12">

            <table class="table">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Rol</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${usuarios}" var="bo">
                        <tr>
                            <td>${bo.username}</td>
                            <td>${bo.nombre}</td>
                            <td>${bo.apellido}</td>
                            <td>${bo.rol.nombre}</td>
                            <td><a class="btn btn-secondary" href="<c:url value='/webapp/personaAsociada/create?username=${bo.username}'/>"/>Asociar CUIT</td>
                            <td><a class="btn btn-success" href="<c:url value='/webapp/personaAsociada/list?username=${bo.username}'/>"/>CUIT's Asociados</td>
                            <td><a class="btn btn-primary" href="<c:url value='/webapp/usuario/add-location?username=${bo.username}'/>"/>Asociar Localidad</td>
                        </tr>
                    </c:forEach>

                    <c:if test='${empty usuarios}'>
                        <tr>
                            <td colspan="6"><br>
                                <h3>No se han encontrado usuarios</h3>
                            </td>
                        </tr>
                    </c:if>


                </tbody>

            </table>

            <c:choose>
                <c:when test="${page > 1}">
                    <div class="col-xs-2">
                        <a href="<c:url value='/webapp/usuario/administrador/buscar?&page=${page - 1}&valor=${valor}'/>" class="btn btn-block btn-primary">Atras</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="col-xs-2">
                        <a class="btn btn-block btn-primary disabled" aria-disabled="true" >Atras</a>
                    </div>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${not empty usuarios && usuarios.size() == 5}">
                    <div class="col-xs-2">
                        <a href="<c:url value='/webapp/usuario/administrador/buscar?&page=${page + 1}&valor=${valor}'/>" class="btn btn-block btn-primary">Siguiente</a>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="col-xs-2">
                        <a class="btn btn-block btn-primary disabled" aria-disabled="true">Siguiente</a>
                    </div>
                </c:otherwise>
            </c:choose>


        </div>

    </div>

</div>

<div id='botonera'>
    <a href="../list/administradorDeCuenta" class="btn btn-default">Volver</a>
</div>

<jsp:include page="../bottom.jsp"/>
</body>
</html>
