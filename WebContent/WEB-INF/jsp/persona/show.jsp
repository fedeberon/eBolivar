<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <script type="text/javascript" src="<c:url value='/webapp/webjarslocator/jquery-ui/jquery-ui.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/impuesto/impuesto.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webapp/webjarslocator/jquery-ui/jquery-ui.css'/>" type="text/css"/>
</head>
<body>
<div class="titulo-general">
    <span>Datos de la Persona</span>
</div>

<div class="container">
    <div class="row">

        <div class="col-md-12">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#home">Datos del Contribuyente</a></li>
                <li><a data-toggle="tab" href="#menu1">Padrones Asociados</a></li>
                <li><a data-toggle="tab" href="#menu2">Domicilios</a></li>
            </ul>

            <div class="tab-content">
                <div id="home" class="tab-pane fade in active">

                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th colspan="2">Contribuyente</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">Nombre</th>
                            <td>${persona.nombre}</td>
                        </tr>
                        <tr>
                            <th scope="row">Apellido</th>
                            <td>${persona.apellido}</td>
                        </tr>
                        <tr>
                            <th scope="row">Dependencia</th>
                            <td>${persona.descripcionActividadPrincipal}</td>
                        </tr>
                        <tr>
                            <th scope="row">CUIT</th>
                            <td>${persona.idPersona}</td>
                        </tr>
                        <tr>
                            <th scope="row">D.N.I.</th>
                            <td>${persona.numeroDocumento}</td>
                        </tr>


                        </tbody>
                    </table>

                </div>

                <div id="menu2" class="tab-pane fade">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th colspan="2">Domicilios del Contribuyente</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${persona.domicilio}" var="domicilio">

                            <tr>
                                <th scope="row">Direccion</th>
                                <td>${domicilio.direccion}</td>
                            </tr>

                            <tr>
                                <th scope="row">Localidad</th>
                                <td>${domicilio.localidad}</td>
                            </tr>

                            <tr>
                                <th scope="row">Provincia</th>
                                <td>${domicilio.descripcionProvincia}</td>
                            </tr>

                            <tr>
                                <th scope="row">Tipo de Domicilio</th>
                                <td>${domicilio.tipoDomicilio}</td>
                            </tr>

                            <tr>
                                <td colspan="2">
                                    <hr>
                                </td>
                            </tr>

                        </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>


        </div>


    </div>

</div>

<div id='botonera'>
    <a href="javascript:history.back()" class="btn btn-default">Volver</a>
    <a href="#" class="verificarPadron btn btn-default">Agregar Padron</a>
</div>


<div id="modal-verificar-numero-padron" title="Ingrese el numero de Padron.">
    <form action="/rentas/webapp/personas/agregarPadronAPersona" id="form-asociar-padron-persona">
        <input type="hidden" name="idPersona" value="${persona.id}">
        <input type="text" name="padron" placeholder="Ingrese el Padron">
    </form>
</div>

<jsp:include page="../impuesto/alertas/alertas.jsp"/>

<jsp:include page="../bottom.jsp"/>


</body>
</html>


