<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>${initParam['AppName']}</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/img/icons/logo.ico'/>">
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value='/css/shadowbox.css'/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css"/>

    <script type="text/javascript" src="<c:url value='/webapp/webjarslocator/jquery/jquery.min.js'/>"></script>

    <script type="text/javascript" src="<c:url value='/webapp/webjarslocator/datatables/js/dataTables.bootstrap.min.js'/>"></script>

    <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js'/>"></script>

    <script type="text/javascript" src="<c:url value='/js/jquery.nicescroll.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/dwr/interface/ajaxServiceBuscador.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/dwrProyecto.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.blockUI.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.marquee.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/ayuda.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/shadowbox.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/listings.js'/>"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCDPNopTNnEV9_TcXFjNgsnMTfQB2JviFY&sensor=false"></script>
    <script type="text/javascript" src="http://dev.virtualearth.net/mapcontrol/mapcontrol.ashx?v=7.0"></script>

    <script src="//www.socialintents.com/api/socialintents.1.3.js#2c9fa56368e8519b0168f10b06450372" async="async"></script>
</head>
<div class="divHeader" id="divHeader">
    <table class="table-header">
        <tbody>
        <tr valign="middle">
            <td align="center"><img style="float: left;" src="<c:url value='/img/institucional/logo-degradado-header.png'/>">

            </td>
            <td height="20px" align="center" style="width: 30%;">
            </td>
            <%--<td style="text-align: center;">--%>
                <%--<img class="imgHeaderRight" style="height: 90px;" src="<c:url value='/img/institucional/modernizacion.png'/>"/>--%>
                <%--<h4 style="margin-top: -10px;color: #fff;margin-left: 30px;"> Tr&aacute;mites on-line </h4>--%>
            <%--</td>--%>
        </tr>
        </tbody>
    </table>

    <jsp:include page="componentes/menu.jsp"/>

    <div class="subtitulo-descripcion">
        <p style="transition: 1s; display: none;" id="subTituloDescripcion"> Gesti&oacute;n de tr&aacute;mites
            on-line</p>
    </div>
</div>git
<body>
<div class="divPrincipal">
</body>
