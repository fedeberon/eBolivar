<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="div-subcomponent">
    <div class="titulo-component">
        Impuestos
    </div>
    <div id="boxscroll">
        <div class="mush">
            <table class="table table-hover">
                <c:forEach items="${ tipos }" var="tipo">
                    <tr>
                        <td>
                            <a class="fontLeft" href="impuestos?tipoImpuesto=${tipo.codigo}">
                                <font>${tipo.nombre}</font>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>