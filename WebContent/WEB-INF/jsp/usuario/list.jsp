<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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

        #location{
            padding: 3px;
            color: #FFFFFF;
            background-color: #5bc0de;
            border-radius: 50%;
        }

        #location-icon{
            padding-left: 3px;
        }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){

            $('[data-toggle="tooltip"]').tooltip();

            $("#location").hover(function(){
                $("#on-hover-location").css("display", "block");
            }, function(){
                $("#on-hover-location").css("display", "none");
            });
        });
    </script>

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
                    <th></th>
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
                            <td><a class="btn btn-primary" href="<c:url value='/webapp/usuario/add-location?username=${bo.username}'/>"/>Asociar Localidad</td>
                            <td>

                                <c:forEach items="${bo.usuarioLocalidad}" var="usuLocalidad">
                                    <img id="location-icon" src="<c:url value="/img/icons/icon-location.png"/>" data-toggle="tooltip" title="${usuLocalidad.localidadAsociada.nombre}"/>
                                </c:forEach>

                                <br/>

                                <c:forEach items="${bo.usuarioLocalidad}" var="usuLocalidad">
                                    <c:set var = "string1" value = "${usuLocalidad.localidadAsociada.nombre}"/>
                                    <c:set var = "localidad" value = "${fn:substring(string1,0,2)}"/>
                                    <h id="location">${localidad}</h>
                                </c:forEach>

                            </td>
                            <td><a class="btn btn-success" href="<c:url value='/webapp/usuario/saveAdministradorCuenta?username=${bo.username}'/>"/>Pasar como Administrador de Cuenta</td>
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
