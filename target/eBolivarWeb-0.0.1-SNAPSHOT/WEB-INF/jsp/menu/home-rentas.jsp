<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../header.jsp" />


<style>


    .thumbnail img{
        width:  50%;
    }

</style>


<div class="container" >

    <div class="row">

        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/img/touch/impuesto.png" alt="Seleccione">
                <div class="caption">
                    <h3>Impuestos Municipales</h3>
                    <p>Ver Impuestos on line</p>
                    <!--         <p><a href="#" class="btn btn-primary" role="button">Ver</a>  -->
                    <p><a href="<c:url value='/webapp/touch/menuImpuestos'/>" class="btn btn-default process"  role="button">Ver</a></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/img/touch/formulario.jpg" alt="Seleccione">
                <div class="caption">
                    <h3>Formulario On Line</h3>
                    <p>Consulte el estado del reclamo.</p>
                    <!--         <p><a href="#" class="btn btn-primary" role="button">Ver</a>  -->
                    <a href="<c:url value='/webapp/touch/consultaReclamo'/>" class="btn btn-default process" role="button">Ver</a></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/img/touch/reclamo.png" alt="Seleccione">
                <div class="caption">
                    <h3>Gesti&oacute;n de solicitudes</h3>
                    <p>Dejenos su inquietud, sugerencia o reclamo.</p>
                    <!--         <p><a href="#" class="btn btn-primary" role="button">Ver</a>  -->
                    <a href="<c:url value='/webapp/touch/nuevoReclamo'/>" class="btn btn-default process" role="button">Ver</a></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/img/touch/info.png" alt="Seleccione">
                <div class="caption">
                    <h3>Guia de Tramites Municipales</h3>
                    <p>Ver Impuesto on line</p>
                    <!--         <p><a href="#" class="btn btn-primary" role="button">Ver</a>  -->
                    <a href="<c:url value='/webapp/touch/menuGuiaDeTramites'/>" class="btn btn-default process" role="button">Ver</a></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/img/touch/phone-256.png" alt="Seleccione">
                <div class="caption">
                    <h3>Telefonos Municipales</h3>
                    <p>Ver Impuesto on line</p>
                    <!--         <p><a href="#" class="btn btn-primary" role="button">Ver</a>  -->
                    <a href="#" class="btn btn-default" role="button">Ver</a></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="/img/touch/maps.png" alt="Seleccione">
                <div class="caption">
                    <h3>Mapa de Contrucciones</h3>
                    <p>Ver Impuesto on line</p>
                    <p><a href="#" class="btn btn-primary" role="button">Ver</a>
                        <a href="<c:url value='/webapp/espacio/maps'/>" class="btn btn-default" role="button">Ver</a></p>
                </div>
            </div>
        </div>



    </div>


</div>


</body>
</html>


