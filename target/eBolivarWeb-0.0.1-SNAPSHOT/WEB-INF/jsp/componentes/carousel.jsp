<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row">
    <div class="col-md-3">

        <div class="titulo-component">
            Seleccione su Tasa.
        </div>
        <div id="boxscroll">
            <div class="mush">
                <table class="table table-hover" >
                    <c:forEach items="${ tipos }" var="tipo">
                        <tr><td>
                            <a class="fontLeft" href="impuestos?tipoImpuesto=${tipo.codigo}">
                                <font>${tipo.nombre}</font>
                            </a>
                        </td></tr>
                    </c:forEach>
                </table>
            </div>
        </div>



    </div>

    <div class="col-md-9">

        <div class="bs-example">
            <div id="myCarousel" class="carousel slide" data-interval="3000" data-ride="carousel" >
                <c:set var="cant" value="0"/>
                <!-- Carousel indicators -->
                <ol class="carousel-indicators">
                    <c:forEach items="${banners}" varStatus="status" var="baner">
                        <li data-target="#myCarousel" data-slide-to="${cant}" class="${cant == 0 ? 'active' :'' }"></li>
                        <c:set var="cant" value="${cant+1}"/>
                    </c:forEach>
                </ol>

                <!-- Carousel items -->
                <div class="carousel-inner">
                    <c:set var="cant" value="0"/>
                    <c:forEach items="${banners}" varStatus="status" var="baner">
                        <div class="${cant == 0 ? 'active item' :'item' }">
                            <img class="imagen-carousel" align="middle"
                                 src="<c:url value='/..${baner.direccion_img}'/>" style="max-height: 400px;"/>
                            <h2>${baner.nombre_principal}</h2>
                            <div class="carousel-caption">
                                <h3>${baner.nombre_secundario}</h3>
                                <p>${baner.observaciones}</p>
                            </div>
                        </div>
                        <c:set var="cant" value="${cant+1}"/>
                    </c:forEach>
                </div>
                <!-- Carousel nav -->
                <a class="carousel-control left" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="carousel-control right" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
        </div>


    </div>


</div>