<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <script type="text/javascript">

        $(document).ready(function () {
            $('#rentas').addClass('active')
        });
        function ocultarCuitErrorRedireccionar() {
            document.getElementById('errorCuit').style.display = 'none';
            document.location.replace("/eBolivar/webapp/personas/create");
        }
    </script>
    <style>
        form{
            padding: 0px 0px 0px 0px;
            margin: 0px;
        }
        .page-header{
            padding-top: 15px;
            margin: 0px 0px 0px 0px;
        }

        img.pequeña{
            width: 50px; height: 50px;
        }
        img.mediana{
            width: 100px; height: 100px;
        }
        img.grande{
            width: 200px; height: 200px;
        }
        .cuitError{
            text-align: center;
            color: #ff0000;
        }
        .cuitError:hover{
            color: black;
        }


    </style>
</head>
<body>

<div style="margin-bottom: 20px" class="page-header">
    <span>Administraci&oacute;n de padrones.</span>
</div>


<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<c:url value='/img/afip.jpg'/>" class="mediana"/>
                <div class="caption">
                    <h3>Busqueda de Contribuyentes</h3>
                    <p>por n&uacute;mero de CUIT.</p>
                    <form action="<c:url value='/webapp/personas/busquedaPorCuit'/>">
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button type="submit" type="button" class="btn btn-default" tabindex="2">
                                    <span class="glyphicon glyphicon-search"></span>
                                    Buscar
                                </button>
                            </div>
                            <input id="inputCuit" type="text" name="cuit" class="form-control tool process" placeholder="Ingrese numero de CUIT" tabindex="1" value="${cuit}"/>
                        </div><!-- /input-group -->
                    </form>
                </div>
                <c:if test="${not empty cuitError}">
                    <a class="cuitError" onclick="ocultarCuitErrorRedireccionar();" >
                        <div id="errorCuit" class="alert alert-danger">${cuitError}</div>
                    </a>
                </c:if>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<c:url value='/img/buscarPersonas.png'/>" class="mediana"/>
                <div class="caption">
                    <h3>Busqueda de Contribuyentes</h3>
                    <p>por nombre y apellido </p>
                    <form action="<c:url value='/webapp//personas/busquedaPorNombreYApellido'/>">
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-default" tabindex="4">
                                    <span class="glyphicon glyphicon-search"></span>
                                    Buscar
                                </button>
                            </div>
                            <input type="text" name="nombre" class="form-control tool process" placeholder="Ingrese Nombre" tabindex="3" style="width: 50%;"/>
                            <input type="text" name="apellido" class="form-control tool process" placeholder="Ingrese Apellido" tabindex="3" style="width: 50%;"/>
                        </div><!-- /input-group -->
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<c:url value='/img/busquedaPadron.png'/>" class="mediana"/>
                <div class="caption">
                    <h3>Busqueda de Padrones</h3>
                    <p>por numero de padron</p>
                    <form action="<c:url value='/webapp/personas/busquedaPorPadron'/>">
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-default" tabindex="6">
                                    <span class="glyphicon glyphicon-search"></span>
                                    Buscar
                                </button>
                            </div>
                            <input type="text" name="padron" class="form-control tool process" placeholder="Ingrese numero de padron" tabindex="5"/>
                        </div><!-- /input-group -->
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<c:url value='/img/notificaciones-logo.png'/>" class="mediana"/>
                <div class="caption">
                    <h3>Padrones Asociados a Email</h3>
                    <button type="submit" class="btn btn-default" tabindex="6"><a href="<c:url value='/webapp/notificacionPadron/list'/>"/>
                        <span class="glyphicon glyphicon-share-alt"></span>
                        ir al listado
                    </button>
                    <button type="submit" class="btn btn-default" tabindex="6"><a href="<c:url value='/webapp/notificacionPadron/create'/>"/>
                        <span class="glyphicon glyphicon-envelope"></span>
                        nueva notificaci&oacute;n
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<%--webapp/notificacionPadron/search?mostrar=Padron:true,Nombre_Apellido:true,Email:true,Fecha_Alta:true,Tasa:true,Confirmado:true--%>
</body>
</html>

<jsp:include page="../bottom.jsp"/>
