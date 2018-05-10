<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- <div class="container"> -->


<jsp:include page="mensajes.jsp" />

<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid" style="padding-left: 5%;">

     <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
        <span class="sr-only">Desplegar navegacion</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#"> <span class="glyphicon glyphicon-globe"></span></a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


   <c:choose>

   <c:when test="${isScreenTouch}">

   <div class="marquee">

   Trabajamos para seguir siendo un Municipio transparente con opciones f?ciles, r?pidas y accesibles a todos los vecinos.
	Creemos que el tiempo es muy valioso y con el objetivo de optimizarlo hemos creado ?ste espacio para consulta de de tr?mites, informes y reclamos. Desde aqu? se podr?n realizar impresiones de boletas, obtener informaci?n sobre los tr?mites, ordenanzas, mapas de obras y m?s.
	Proyecto municipal Gobierno Digital Abierto. Porque queremos estar m?s cerca y ser un Municipio eficiente para todos los vecinos de Bol?var.

   </div>


   </c:when>


  <c:otherwise>


       <ul class="nav navbar-nav">
           <li id="homeWeb" >
               <a href="<c:url value='/webapp/'/>">INICIO</a>
           </li>

           <li id="cronogramaVencimientos" >
               <a href="<c:url value='/webapp/informacion/cronograma'/>">CRONOGRAMA DE VENCIMIENTO</a>
           </li>

           <li id="mediosDePago" >
               <a href="<c:url value='/webapp/informacion/mediosDePago'/>">MEDIOS DE PAGO</a>
           </li>
           <%--<li id="tramites" >--%>
               <%--<a href="<c:url value='/webapp/guiaTramite/list'/>">--%>
                   <%--GUIA DE TRAMITES</a>--%>
           <%--</li>--%>
           <li id="ddjj" >
               <a href="#" class="dropdown-toggle" data-toggle="dropdown">DECLARACION JURADA&nbsp;<span class="caret"></span></a>
               <ul class="dropdown-menu" role="menu">
                   <li><a href="<c:url value='/webapp/ddjj/create'/>">Nueva</a></li>
                   <li class="divider"></li>
                   <li><a href="<c:url value='/webapp//ddjj/formularioBuscar/'/>">Reimprimir</a></li>
                   <li class="divider"></li>
                   <li><a href="<c:url value='/webapp//ddjj/declaracionJuradaAnteriores?anio=2016'/>">DDJJ 2016</a></li>
                   <li class="divider"></li>
                   <li><a href="<c:url value='/webapp//ddjj/declaracionJuradaAnteriores?anio=2017'/>">DDJJ 2017</a></li>

               </ul>
           </li>
           <li id="padronAsociado" >
               <a href="<c:url value='/webapp//padron/asociarPadronACUIT'/>">
                    ASOCIAR CUIT  PADRON</a>
           </li>
           <li id="reclamos" >
               <a href="<c:url value='/webapp/contacto/nuevo'/>">CONTACTENOS</a>
           </li>
       </ul>

       <ul class="nav navbar-nav navbar-right">


           <sec:authorize access="!isAuthenticated()">
                   <li>
                       <a href="<c:url value='/webapp/login'/>">
                           Ingresar</a>
                   </li>
           </sec:authorize>

           <sec:authorize access="isAuthenticated()">


               <sec:authorize access="hasRole('ROLE_MODULO_RENTAS')">
                    <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                           <sec:authentication property="principal" />
                           <span class="caret"></span>
                       </a>
                       <ul class="dropdown-menu" role="menu">
                           <li><a href="../banner/list">Banners</a></li>
                           <li class="divider"></li>
                           <li><a href="<c:url value='/webapp/impuesto/actualizaciones'/>">Cargar Tasas</a></li>
                           <li class="divider"></li>
                           <li><a style="color: #555" href="<c:url value='/webapp/notificacionPadron/list'/>">Notificaciones por mail<label id="padronesParaActualiazar"/> </a></li>
                           <li class="divider"></li>
                           <li><a style="color: #555" href="<c:url value='/webapp/espacio/list'/>">Espacios <label id="espacios"/> </a></li>
                           <li class="divider"></li>
                           <li><a style="color: #555" href="<c:url value='/webapp/espacio/maps'/>">Mapa <label id="mapa"/> </a></li>
                           <li class="divider"></li>
                           <li> <a href="<c:url value='/webapp/rentas/menu'/>"> ADMINISTRACION</a> </li>
                           <li class="divider"></li>
                           <li><a style="color: #555" href="<c:url value='/webapp/ddjj/list'/>">DDJJ<label/> </a></li>
                           <li class="divider"></li>
                           <sec:authorize access="hasRole('ROLE_CARGAR_USUARIOS')">
                               <li><a style="color: #555" href="<c:url value='/webapp/usuario/create'/>">Crear Usuario<label/> </a></li>
                               <li class="divider"></li>
                               <li><a style="color: #555" href="<c:url value='/webapp/usuario/list'/>">Lista de Usuarios<label/> </a></li>
                               <li class="divider"></li>
                           </sec:authorize>
                           <li><a href="<c:url value='/webapp/logout'/>"> Logout</a></li>
                       </ul>
                    </li>
               </sec:authorize>


               <sec:authorize access="hasRole('ROLE_MODULO_CONTRIBUYENTE')">
                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                           <sec:authentication property="principal" />
                           <span class="caret"></span>
                       </a>
                       <ul class="dropdown-menu" role="menu">
                           <li class="divider"></li>
                           <li><a href="<c:url value='/webapp/logout'/>"> Logout</a></li>
                       </ul>
                   </li>
               </sec:authorize>

           </sec:authorize>
        </ul>
   </c:otherwise>

   		</c:choose>


    </div><!-- /.navbar-collapse -->


  </div><!-- /.container-fluid -->
</nav>
<!-- </div> -->
