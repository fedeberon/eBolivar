function mostrarAyudaTramite(object) {
	setOpcionesAyuda(object);
	$.blockUI({ 
        message: $('#ayudaTramite'), 
        css: { 
            top: '80px', 
            left: '200px',
            cursor:'pointer',
            width:'950',
            border: '1px solid #555',
            background:'#E5E6DA',
    		height: '500;'
         },
        onOverlayClick: $.unblockUI 
    }); 
}

function setOpcionesAyuda(object) {
	$('#tituloAyuda').text(object.text);
}

function addPaso() {
	event.preventDefault();
	var ultimoPaso = $('#pasosIndex').val();
	var index = parseInt($('#pasosIndex').val());
	index ++;
	var divTemplate = $('#pasoTramiteTemplate').clone();
	$(divTemplate).attr('id','pasoTramiteTemplate' + index);
	$(divTemplate).css('display','inline');
	$(divTemplate).find('#nroPasoTemplate').text(index);
	$(divTemplate).find('#nombrePasoTemplate').attr('name', 'PASO' + index);
	$(divTemplate).find('#descripcionPasoTemplate').attr('name', 'DESCRIPCION' + index);
	$(divTemplate).find('#urlImgTemplate').attr('name', 'urlImg' + index);
	$(divTemplate).find('#ImgTemplate').attr('onclick', 'cargarImagenPaso('+index+');');
	
	$(divTemplate).css('backgroundColor', 'green');
	$(divTemplate).appendTo('#pasos');
	$(divTemplate).animate({backgroundColor: '#F8F8F8'}, 2000);
	
	$('#pasosIndex').val(index);
}

function scrollToStep(event) {
	$(event.srcElement).attr('href');
	var element = $(event.srcElement).attr('href');
	var target = $(element);
	var offsetElement = target.offset().top - 10;
	    if( target.length ) {
	        event.preventDefault();
	        $('html, body').animate({
	            scrollTop: offsetElement
	        }, 1000);
	    }
}

function showStep(event, index) {
	$('#span'+index).click();
}

function setActiveButton(element) {
	$('.button-menu').removeClass('button-menu-active');
	var id = element.id;
	
	$('#menuButton' + id.replace('span','')).addClass('button-menu-active');
}