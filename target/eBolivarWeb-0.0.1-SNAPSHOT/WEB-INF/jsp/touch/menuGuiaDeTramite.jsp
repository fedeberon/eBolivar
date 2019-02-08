<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <jsp:include page="../header.jsp" />
  
 <script type="text/javascript" src="<c:url value='/js/touch/touch.js'/>"></script>

 <script type="text/javascript">
  
 $(document).ready(function() {
	 
	 marquesina();
	 
 	});

 
 
 $(function() {
   $( "#accordion" ).accordion({
	   icons: {
		   "header": "ui-icon-circle-plus",
           "headerSelected": "ui-icon-circle-minus"
       }
   });
 
 
 $('.collapse').on('shown.bs.collapse', function(){
	 $(this).parent().find(".glyphicon-plus").removeClass("glyphicon-plus").addClass("glyphicon-minus");
	 }).on('hidden.bs.collapse', function(){
	 $(this).parent().find(".glyphicon-minus").removeClass("glyphicon-minus").addClass("glyphicon-plus");
	 });
 
 });
 
 
 </script>
  
  
  <style>
  
  
 .thumbnail img{
	width:  50%;
 }
  
  </style>
 
 
<jsp:include page="headTouch.jsp"/>
 
 
<div class="container" >
  
  
  <c:forEach items="${tiposGuiaTramites}" var="tipoGuia">
  
 	<div id="accordion" class="panel-group">
	    <div class="panel panel-default">
	        <div class="panel-heading">
	            <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#collapse${tipoGuia.codigo}"> <span class="glyphicon glyphicon-plus"></span>&nbsp; ${tipoGuia.nombre}</a></h4>
	        </div>
	        
	        <div id="collapse${tipoGuia.codigo}" class="panel-collapse collapse">
	            <div class="panel-body">
	                	<table class="table table-bordered table-hover">
					 		<c:forEach items="${guiaTramites}" varStatus="status" var="guia" >
						    		<c:if test="${guia.tipo.codigo == tipoGuia.codigo}">
	                				
	                				<tr><td>
	                				<a class="process" href="<c:url value='/webapp/guiaTramite/showTramite?idGuiaTramite=${guia.codigo}&vista=isScreenTouch'/>">${guia.titulo}</a>
						    		</td></tr>
						    	
						    		</c:if>
							</c:forEach>
						</table>
	            </div>
	        </div>
	    </div>
	</div>
 </c:forEach>


<div>

  

 



<jsp:include page="../componentes/modals.jsp"/>
<%-- <jsp:include page="../bottom.jsp" /> --%>




</body>
</html>

 
 