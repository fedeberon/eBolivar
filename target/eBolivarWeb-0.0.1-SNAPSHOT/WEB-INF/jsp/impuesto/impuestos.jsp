<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../header.jsp"/>
<script type="text/javascript" src="<c:url value='/webapp/webjarslocator/jquery-ui/jquery-ui.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/impuesto/impuesto.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/webapp/webjarslocator/jquery-ui/jquery-ui.css'/>" type="text/css"/>
<jsp:include page="../componentes/modals.jsp"/>
<jsp:include page="modales/tasa.jsp"/>
<jsp:include page="elementos-HTML/buttons.jsp"/>
<html>
<head>
    <style>
        .bordes {
            border:1pt solid black;
        }

    </style>

</head>
<body>

<div class="row" id="div-impuestos">

    <div class="col-md-12">
        <div class="page-header">
            <hr>
            <span style="cursor:  pointer;" class="titulo-descripcion" data-toggle="collapse" data-target="#VerInstrucciones">${tipo.nombre}</span>
        </div>
    </div>

    <hr>

    <div class="col-md-12">
        <jsp:include page="tablas/buscador.jsp"/>
    </div>

    <hr>

    <div class="col-md-12">
        <jsp:include page="tablas/tasas.jsp"/>
    </div>

</div>

<jsp:include page="../bottom.jsp"/>


</body>
</html>


