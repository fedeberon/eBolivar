<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <script type="text/javascript">

        $(document).ready(function () {
            $('#rentas').addClass('active')

        });

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

    </style>
</head>
<body>

<div class="page-header">
    <span>Administraci&oacute;n de padrones.</span>
</div>


<div class="container">
    <div class="row">

        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<c:url value='/img/afip.jpg'/>" class="mediana"/>
                <div class="caption">
                    <h3>Busqueda de Contribuyentes</h3>
                    <p> .. por N&uacute;mero de CUIT.</p>
                    <form action="<c:url value='/webapp/personas/busquedaPorCuit'/>">
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button type="submit" type="button" class="btn btn-default" tabindex="2">
                                    <span class="glyphicon glyphicon-search"></span>
                                    Buscar
                                </button>
                            </div>
                            <input type="text" name="cuit" class="form-control tool process" placeholder="Ingrese numero de CUIT" tabindex="1"/>
                        </div><!-- /input-group -->
                    </form>
                </div>
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
    </div>
</div>

</body>
</html>

<jsp:include page="../bottom.jsp"/>
