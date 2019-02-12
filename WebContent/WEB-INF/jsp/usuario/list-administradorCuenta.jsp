<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
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

    </style>

</head>
<body>

<div class="page-header">
    <span class="titulo-descripcion" style="text-align: center"><h2>Administradores de Cuentas</h2></span>
</div>

<div id="formulario">

    <form:form action="../buscar" method="post">
        <input name="page" value="${page}" type="hidden">
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
                            <td><a class="btn btn-info" href="<c:url value='/webapp/personaAsociada/create?username=${bo.username}'/>"/>Asociar CUIT</td>
                            <td><a class="btn btn-success" href="<c:url value='/webapp/personaAsociada/list?username=${bo.username}'/>"/>CUIT's Asociados</td>
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
