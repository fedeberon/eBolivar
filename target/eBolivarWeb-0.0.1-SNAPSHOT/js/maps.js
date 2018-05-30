var map;

function crearMapaEspacios() {
	var mapOptions = {
		credentials : "Ak7SwTSpYjHK3Id2X_-_7AhPw6QeAxC1yPYmK3qV18wUvTA5hiVeu1oTkE6JMAAc",
		mapTypeId : Microsoft.Maps.MapTypeId.road,
		center : new Microsoft.Maps.Location(-36.2312808, -61.113402),
		zoom : 15
	}
	map = new Microsoft.Maps.Map(document.getElementById("mapDiv"), mapOptions);
}

function crearMapa() {
	var mapOptions = {
		credentials : "Ak7SwTSpYjHK3Id2X_-_7AhPw6QeAxC1yPYmK3qV18wUvTA5hiVeu1oTkE6JMAAc",
		mapTypeId : Microsoft.Maps.MapTypeId.road,
		center : new Microsoft.Maps.Location(-36.2312808, -61.113402),
		zoom : 15
	}
	map = new Microsoft.Maps.Map(document.getElementById("mapDiv"), mapOptions);

	var pin = new Microsoft.Maps.Pushpin(new Microsoft.Maps.Location(
			-36.2292778, -61.1151814), {
		draggable : true
	});
	map.entities.push(pin);

	// Wire events for dragging
	Microsoft.Maps.Events.addHandler(pin, 'dragstart', StartDragHandler);
	Microsoft.Maps.Events.addHandler(pin, 'drag', DragHandler);
	Microsoft.Maps.Events.addHandler(pin, 'dragend', EndDragHandler);
}

var addNavButton = function(tipoEspacio, funcion) {
	$(map).find('.NavBar_modeSelectorControlContainer').append(
			$('<span>').addClass('NavBar_separator')).css('height', '50').css(
			'width', '1000').append(
			$('<button>').attr('onclick', onclick).attr('value',
					tipoEspacio.nombre).attr('onclick', funcion).addClass(
					'btn btn-default'));
};

function crearInfoBox(pin) {
	var pinInfobox = null;
	pinInfobox = new Microsoft.Maps.Infobox(new Microsoft.Maps.Location(0, 0),
			{
				title : pin.getText,
				visible : true
			});
	Microsoft.Maps.Events.addHandler(pin, 'click', displayInfobox);
	pinInfobox.setHtmlContent("pin");
	map.entities.push(pinInfobox);
}

function StartDragHandler(e) {
	document.getElementById("mode").innerHTML = "Dragging started (dragstart event)."
}

function DragHandler(e) {
	document.getElementById("mode").innerHTML = "Dragging in process (drag event).."
	var loc = e.entity.getLocation();
	$("input[name*='latitud']").val(loc.latitude.toFixed(7));
	$("input[name*='longitud']").val(loc.longitude.toFixed(7));
}

function EndDragHandler(e) {
	// document.getElementById("mode").innerHTML = "Dragging stopped (dragend
	// event)."
	var loc = e.entity.getLocation();
	obtenerCallesPorCordenadas(loc.latitude.toFixed(7), loc.longitude
			.toFixed(7));

}

var infobox, dataLayer;

function displayInfobox(e) {
	if (e.targetType == 'pushpin') {

		var infoboxLayer = new Microsoft.Maps.EntityCollection();
		infobox = new Microsoft.Maps.Infobox(new Microsoft.Maps.Location(0, 0),
				{
					visible : false,
					offset : new Microsoft.Maps.Point(0, 20)
				});
		infobox.setLocation(e.target.getLocation());
		infobox.setOptions({
			visible : true,
			height : 200,
			width : 400,
			zIndex : 3,
			label : e.target.Label,
			title : e.target.Title,
			description : e.target.Descripcion
		});
	}
	infoboxLayer.push(infobox);
	map.entities.push(infoboxLayer);
}

function hideInfobox(e) {
	if (e.targetType == 'pushpin') {
		infobox.setLocation(e.target.getLocation());
		infobox.setOptions({
			visible : false
		});
	}
}

function removeLayer(indexLayer, tipo) {
	$('.ocultar_' + tipo).hide('slide', 700);
	$('.mostrar_' + tipo).removeAttr("disabled");
	var layer = map.entities.get(indexLayer);
	layer.setOptions({
		visible : false
	});
	$(".titulo" + tipo).empty();
}

function obtenerCallesPorCordenadas(latitud, longitud) {

	$.ajax({
				type : 'POST',
				url : 'http://dev.virtualearth.net/REST/v1/Locations/'
						+ latitud
						+ ','
						+ longitud
						+ '?output=json&jsonp=MyCallbackFunction&key=Ak7SwTSpYjHK3Id2X_-_7AhPw6QeAxC1yPYmK3qV18wUvTA5hiVeu1oTkE6JMAAc',
				data : {},
				contentType : "application/json; charset=utf-8",
				dataType : "jsonp",
			});

}

function MyCallbackFunction(e) {
	var resourceSets = e.resourceSets;
	var calles = resourceSets[0].resources;
	$("input[name*='direccion']").val(
			resourceSets[0].resources[0].address.formattedAddress);
	console.log(resourceSets[0].resources[0].address.formattedAddress);
}









 
