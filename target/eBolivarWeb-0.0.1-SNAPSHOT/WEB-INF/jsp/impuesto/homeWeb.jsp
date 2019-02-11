<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <script type="text/javascript" src="<c:url value='/webapp/webjarslocator/jquery/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/home.js'/>"></script>
</head>
<body>
<c:set var="baners" value="${banners}" scope="request"/>

<jsp:include page="../componentes/carousel.jsp"/>

<div class="subtitulo-second">
    <jsp:include page="../componentes/social.jsp"/>
</div>

<div>
    <jsp:include page="../componentes/institucional.jsp"/>
    <%--<jsp:include page="../componentes/clima.jsp"/>--%>
    <div style="clear: both;"></div>
</div>

<div>
    <%--<jsp:include page="../componentes/tasasImpuestos.jsp"/> --%>

    <div class="row">
        <div class="col-sm-3">
            <jsp:include page="../componentes/preguntasFrecuentes.jsp"/>
        </div>
        <div class="col-sm-3">
            <jsp:include page="../componentes/tasas.jsp"/>
        </div>
        <div class="col-sm-3">
            <jsp:include page="../componentes/datosDeContacto.jsp"/>
        </div>
        <div class="col-sm-3">
            <jsp:include page="../componentes/descargaDeDocumentos.jsp"/>
        </div>
    </div>

    <div style="clear: both;"></div>


</div>

<jsp:include page="../componentes/modals.jsp"/>
<jsp:include page="../bottom.jsp"/>


</body>
</html>


