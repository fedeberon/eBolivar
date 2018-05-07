<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="<c:url value='/slider/js/modernizr.custom.28468.js'/>"></script>
<script type="text/javascript" src="<c:url value='/slider/js/jquery.cslider.js'/>"></script>

  
        <div class="slider-container">
			<div id="da-slider" class="da-slider" style="margin-top: -80px;margin: 0px;">
				
				<div class="da-slide" id="pasoTramite0">
					<h2>¿En que consiste?</h2>
					<p>${guiaTramite.descripcion}</p>
<!-- 					<a href="#" class="da-link"></a> -->
					<div class="da-img"><img style="width: 170px;margin-left: 40%;" src="<c:url value='/slider/images/description-256.png'/>"  /></div>
				</div>
				
				<c:forEach items="${guiaTramite.pasos}" var="paso" varStatus="status">
					<div class="da-slide" id="pasoTramite${status.index+1}">
					<h2 style="text-transform: capitalize;">${fn:toLowerCase(paso.nombre)}</h2>
					<p>${paso.descripcion}</p>
<!-- 					<a href="#" class="da-link"></a> -->
					<div class="da-img">
						<img style="width: 180px;margin-left: 40%;" src="/img/${paso.urlImg == null ? 'default-256.png' : paso.urlImg}"  />
					</div>
				</div>
				
				</c:forEach>
				
				<nav class="da-arrows">
					<span class="da-arrows-prev"></span>
					<span class="da-arrows-next"></span>
				</nav>
				
			</div>
        </div>
		
		<script type="text/javascript">
			$(function() {
			
				$('#da-slider').cslider({
					current		: 0, 	// index of current slide
					bgincrement	: 50,	// increment the bg position (parallax effect) when sliding
					autoplay	: false,// slideshow on / off
					interval	: 4000
				});
			
			});
		</script>	
