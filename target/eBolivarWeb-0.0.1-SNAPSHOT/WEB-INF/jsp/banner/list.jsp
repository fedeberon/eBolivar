<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title>${initParam['AppName']} - Banners</title>
    <link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen"/>
    <link rel="stylesheet" href="<c:url value='/css/shadowbox.css'/>" type="text/css"
          media="print, projection, screen"/>
    <script type="text/javascript" src="<c:url value='/js/shadowbox.js'/>"></script>
    <script type="text/javascript">
        Shadowbox.init();
    </script>

    <style>#mostradorBanner {
        width: 0px;
        height: 0px;
        display: none;
    }
    </style>
</head>
<body>
<div class="titulo-general">
    <span>Banners de la pantalla inicio</span>
</div>


<div id="scrollable">

    <table id="listado" id="myTable" class="table table-striped" border="0" cellpadding="0" cellspacing="1" style="text-align: center">
        <thead>
        <tr>
            <th scope="col">C&oacute;digo</th>
            <c:forEach items="${mostrar}" varStatus="status" var="campo">
                <th scope="col">${campo}</th>
            </c:forEach>
            <th scope="col">Ver</th>
            <th scope="col">Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <c:if test='${empty banners}'>
            <tr>
                <td colspan="5"><br>
                    <br>
                    <h3>No se encontraron banners</h3>
                </td>
            </tr>
        </c:if>
        <c:forEach items="${banners}" varStatus="status" var="bo">

            <tr>
                <td>
                    <a href="show?idBanner=${bo.id}">
                            ${bo.id}
                    </a>
                </td>
                <c:forEach items="${mostrar}" varStatus="status" var="campo">
                    <c:if test="${'Leyenda_Principal' eq campo}">
                        <td>${bo.nombre_principal}</td>
                    </c:if>
                    <c:if test="${'Leyenda_Secundaria' eq campo}">
                        <td>${bo.nombre_secundario}</td>
                    </c:if>
                    <c:if test="${'direccion_img' eq campo}">
                        <td>${bo.direccion_img}</td>
                    </c:if>
                    <c:if test="${'Observaciones' eq campo}">
                        <td>${bo.observaciones}</td>
                    </c:if>
                    <c:if test="${'Estado' eq campo}">
                        <td>${bo.estado}</td>
                    </c:if>

                </c:forEach>

                <td><a href="${bo.direccion_img}" rel="shadowbox"><img style="width:23px;"
                                                                       src="<c:url value='/img/1409289763_Picture.png'/>"/></a>
                </td>

                <td>
                    <a onclick="return confirm('Esta seguro que desea eliminar?')"
                       href="<c:url value='/webapp/banner/delete?idBanner=${bo.id}'/>">
                        <img class="minibutton" alt="Eliminar" src="<c:url value='/img/delete_icon.png'/>"/> </a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div id='botonera'>
        <a href="create" class="btn btn-primary">Cargar nueva imagen</a>
    </div>

</div>

<jsp:include page="../bottom.jsp"/>

</body>
</html>
