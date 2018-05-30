<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <jsp:include page="../header.jsp" />
<link rel="stylesheet" href="<c:url value='/css/owl.carousel.css'/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value='/css/owl.theme.css'/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value='/css/owl.transitions.css'/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value='/css/touch.css'/>" type="text/css"/>

<jsp:include page="../touch/libreriasTouch.jsp"/>
<script type="text/javascript" src="<c:url value='/js/touch/touch.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/touch/owl.carousel.js'/>"></script>
 <script type="text/javascript">
 
  
 $(document).ready(function() {
 	 
	 marquesina();
	 
	 carruselDobleTipoImpuestos();
 
 });
 
 </script>
  
    <style>
  
  
 .thumbnail img{
	width:  50%;
 }
  
  </style>
 

 
<jsp:include page="headTouch.jsp"/>

<div class="container"> 

 <div style="display: none;">
  <div id="sync1" class="owl-carousel owl-theme">
  
  <div class="item-video" data-merge="1"><a class="owl-video" href="https://www.youtube.com/watch?v=JpxsRwnRwCQ"></a></div>
  
  <c:forEach items="${tipos }" var="tipo">
   
  	  <div class="item" style="background-color: ${tipo.color != null ? tipo.color : '#555' }">

	<a href="../impuesto/impuestos?tipoImpuesto=${tipo.codigo}">   
		   	<img src="${tipo.urlImg != null ? tipo.urlImg : '/img/impuestoDefault.png' }" style="height: 256px;width: 256px; margin:0 auto" /></a>
		   	<h1>${tipo.nombre }</h1> 
  	  </div>
   
   </c:forEach>
   
</div>

 
 <div id="sync2" class="customNavigation ">
  <c:forEach items="${tipos }" var="tipo">
   
  	  <div class="item" style="background-color: ${tipo.color != null ? tipo.color : '#555' }">

		   	<img src="${tipo.urlImg != null ? tipo.urlImg : '/img/touch/impuesto.png' }" style="height: 56px;width: 56px; margin:0 auto" />
		   	<h6>${tipo.nombre }</h6> 
	   
  	  </div>
   
   </c:forEach>
</div>
</div>




<div class="row">

   <c:forEach items="${tipos }" var="tipo">

  <div class="col-sm-6 col-md-4">
    <div class="thumbnail">
      <img src="/img/touch/impuesto.png" style="width: 50%" alt="Seleccione">
      <div class="caption">
        <h3>${tipo.nombre }</h3>
        <p>...</p>
<!--         <p><a href="#" class="btn btn-primary" role="button">Ver</a>  -->
        <a href="<c:url value='/webapp/touch/impuestos?tipoImpuesto=${tipo.codigo}&vista=isScreenTouch'/>" class="btn btn-default process" role="button">Ver</a></p>
      </div>
    </div>
  </div>
  </c:forEach>
  
  </div>
 
  </div>
  
  
  



<jsp:include page="../componentes/modals.jsp"/>
<%-- <jsp:include page="../bottom.jsp" /> --%>




</body>
</html>

 
 