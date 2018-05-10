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


    </style>

</head>
<body>
<div class="page-header">
    <span class="titulo-descripcion" style="text-align: center"><h2>Lista de Usuarios</h2></span>
</div>

<div id="formulario">

    <div class="row">


        <div class="col-md-12">

            <table class="table">
                <thead>
                <tr>
                    <th>Username</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Rol</th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach items="${usuarios}" var="bo">
                        <tr>
                            <td>${bo.username}</td>
                            <td>${bo.nombre}</td>
                            <td>${bo.apellido}</td>
                            <td>${bo.rol.nombre}</td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

        </div>

    </div>
</div>
<jsp:include page="../bottom.jsp"/>
</body>
</html>
