<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="div-subcomponent"  style="border: 1px solid #C1C1C1;">
<div class="titulo-component">
	tasas
</div>
<div class="boxleft">
 	<a href="../../webapp/notificacionPadron/create"> 
 		<img style="width:100%;" src="<c:url value='/img/informacion-tasas/tusTasas.jpg'/>"/>
 	</a>
    <jsp:include page="../componentes/requisitosImpresion.jsp"/>
</div>
</div>