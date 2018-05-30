<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        .gly-spin {
            -webkit-animation: spin 2s infinite linear;
            -moz-animation: spin 2s infinite linear;
            -o-animation: spin 2s infinite linear;
            animation: spin 2s infinite linear;
        }
        @-moz-keyframes spin {
            0% {
                -moz-transform: rotate(0deg);
            }
            100% {
                -moz-transform: rotate(359deg);
            }
        }
        @-webkit-keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
            }
            100% {
                -webkit-transform: rotate(359deg);
            }
        }
        @-o-keyframes spin {
            0% {
                -o-transform: rotate(0deg);
            }
            100% {
                -o-transform: rotate(359deg);
            }
        }
        @keyframes spin {
            0% {
                -webkit-transform: rotate(0deg);
                transform: rotate(0deg);
            }
            100% {
                -webkit-transform: rotate(359deg);
                transform: rotate(359deg);
            }
        }
    </style>


</head>
<body>

<div class="col-md-4"></div>

<div class="col-md-4">
    <div class="input-group">
        <div class="input-group-btn">
            <button  id="buscarPadron" type="button" class="btn btn-default">
                <span id="btn-search-buscar" class="glyphicon glyphicon-search"></span>
                <span id="btn-loading-buscar" class="glyphicon glyphicon-repeat gly-spin" style="display: none;"></span>
                Buscar
            </button>
        </div>
        <input id="inp-tipo-tasa" type="hidden" value="${tipo.codigo}">
        <input id="inpPadron" type="text" class="form-control tool process" title="Ingrese Padron o Patente" placeholder="${tipo.patronBusqueda} - Ej 000012345">
    </div><!-- /input-group -->
</div>

<div class="col-md-4">
    <img onclick="verInfo();" width="20" height="20" style="cursor: help;" src="<c:url value='/img/icons/information.png'/>"/>
</div>

</body>
</html>
