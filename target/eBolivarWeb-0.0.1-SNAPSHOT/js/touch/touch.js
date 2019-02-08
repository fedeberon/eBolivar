/**
 *  @author FEDE BERON
 */




function carruselDobleTipoImpuestos() {

    var sync1 = $("#sync1");
    var sync2 = $("#sync2");

    sync1.owlCarousel({
        items: 5,
        slideSpeed: 1000,
        navigation: true,
        navigationText: [
            "<i class='icon-chevron-left'>Anterior</i>",
            "<i class='icon-chevron-right'>Siguiente</i>"
        ],
        pagination: true,
        afterAction: syncPosition,
        responsiveRefreshRate: 200,
        margin: 20,
        singleItem: false,
        itemsScaleUp: false,
        mouseDrag: true,
        //Basic Speeds
        paginationSpeed: 800,
        rewindSpeed: 1000,
        video: true,
        autoplay: true,

    });

    sync2.owlCarousel({
        items: 10, //10 items above 1000px browser width
        itemsDesktop: [1000, 5], //5 items between 1000px and 901px
        itemsDesktopSmall: [900, 3], // betweem 900px and 601px
        itemsTablet: [600, 2], //2 items between 600 and 0
        itemsMobile: false, // itemsMobile disabled - inherit from itemsTablet option
        pagination: false,
        responsiveRefreshRate: 100,
        afterInit: function (el) {
            el.find(".owl-item").eq(0).addClass("synced");
        }
    });

    function syncPosition(el) {
        var current = this.currentItem;
        $("#sync2")
            .find(".owl-item")
            .removeClass("synced")
            .eq(current)
            .addClass("synced")
        if ($("#sync2").data("owlCarousel") !== undefined) {
            center(current)
        }
    }

    $("#sync2").on("click", ".owl-item", function (e) {
        e.preventDefault();
        var number = $(this).data("owlItem");
        sync1.trigger("owl.goTo", number);
    });

    function center(number) {
        var sync2visible = sync2.data("owlCarousel").owl.visibleItems;
        var num = number;
        var found = false;
        for (var i in sync2visible) {
            if (num === sync2visible[i]) {
                var found = true;
            }
        }

        if (found === false) {
            if (num > sync2visible[sync2visible.length - 1]) {
                sync2.trigger("owl.goTo", num - sync2visible.length + 3)
            } else {
                if (num - 1 === -1) {
                    num = 0;
                }
                sync2.trigger("owl.goTo", num);
            }
        } else if (num === sync2visible[sync2visible.length - 1]) {
            sync2.trigger("owl.goTo", sync2visible[2])
        } else if (num === sync2visible[0]) {
            sync2.trigger("owl.goTo", num)
        }

    }

}

function marquesina() {

    $('.marquee').marquee({
        //speed in milliseconds of the marquee
        speed: 20000,
        //gap in pixels between the tickers
        gap: 50,
        //gap in pixels between the tickers
        delayBeforeStart: 0,
        //'left' or 'right'
        direction: 'left',
        //true or false - should the marquee be duplicated to show an effect of continues flow
        duplicated: true,
        //on hover pause the marquee - using jQuery plugin https://github.com/tobia/Pause
        pauseOnHover: true
    });
    //$("#boxscroll").niceScroll();

}

function tecladoAlfanumerico() {


    $('.form-control.texto').keyboard({
        layout: 'custom',
        // only use !! in the action key layout
        customLayout: {
            'default': [
                'Q W E R T Y U I O P',
                ' A S D F G H J K L ',
                ' Z X C V B N M . @   ',
                '{meta1} {space} {bksp} {accept}'
            ],
            'meta1': [
                'q w e r t y u i o p',
                'a s d f g h j k l  ',
                'z x c v b n m . @  ',
                '{default} {space} {bksp} {accept}'
            ],
        },
        display: {
            'meta1': 'abc',
            'default': 'ABC',
            'a': '\u2714:Accept (Shift-Enter)', // check mark - same action as accept
            'accept': 'Listo:Accept (Shift-Enter)',
            'alt': 'AltGr:Alternate Graphemes',
            'b': '\u2190:Backspace',    // Left arrow (same as &larr;)
            'bksp': 'Borrar:Backspace',
            'c': '\u2716:Cancel (Esc)', // big X, close - same action as cancel
            'cancel': 'Cancel:Cancel (Esc)',
            'clear': 'C:Clear',             // clear num pad
            'combo': '\u00f6:Toggle Combo Keys',
            'dec': '.:Decimal',           // decimal point for num pad (optional), change '.' to ',' for European format
            'e': '\u21b5:Enter',        // down, then left arrow - enter symbol
            'enter': 'Enter:Enter',
            'left': '\u2190',              // left arrow (move caret)
            'lock': '\u21ea Lock:Caps Lock', // caps lock
            'next': 'Next',
            'prev': 'Prev',
            'right': '\u2192',              // right arrow (move caret)
            's': '\u21e7:Shift',        // thick hollow up arrow
            'shift': 'Shift:Shift',
            'sign': '\u00b1:Change Sign',  // +/- sign for num pad
            'space': 'Espacio:Space',
            't': '\u21e5:Tab',          // right arrow to bar (used since this virtual keyboard works with one directional tabs)
            'tab': '\u21e5 Tab:Tab',      // \u21b9 is the true tab symbol (left & right arrows)
            'toggle': ' ',                   // replaced by an image
        },
        css: {
            buttonAction: 'ui-state-active'
        },
    });

}

function tecladoNumerico() {


    $('.form-control.numerico').keyboard({
        layout: 'custom',
        // only use !! in the action key layout
        customLayout: {
            'default': [
                '7 8 9',
                '4 5 6',
                '1 2 3',
                '# 0 {clear}',
                '. {a} {c}'
            ]
        },
        display: {
            'clear': 'C:Clear',             // clear num pad
        },

        restrictInput: true, // Prevent keys not in the displayed keyboard from being typed in
        preventPaste: true,  // prevent ctrl-v and right click
        autoAccept: true,
        css: {
            // input & preview
            input: 'form-control input-sm',
            // keyboard container
            container: 'center-block dropdown-menu', // jumbotron
            // default state
            buttonDefault: 'btn btn-default',
            // hovered button
            buttonHover: 'btn-primary',
            // Action keys (e.g. Accept, Cancel, Tab, etc);
            // this replaces "actionClass" option
            buttonAction: 'active',
            // used when disabling the decimal button {dec}
            // when a decimal exists in the input area
            buttonDisabled: 'disabled'
        }
    });


}

function scrollCiudades() {

    $('#selectUbicacion').mobiscroll().treelist({
        theme: 'ios',
        mode: 'scroller',
        showInput: false,
        display: 'modal',
        fixedWidth: [300, 250, 250],
        showLabel: true,
        minWidth: 900,
        lang: 'es',
        headerText: 'Seleccione la ubicacion .. ',
        labels: ['LOCALIDAD', 'BARRIO', 'CALLE'],
        onSelect: function (valueText, inst) {
            // var selectedDate = inst.getVal(); // Call the getVal method
            var array = inst.getArrayVal();
            $('#ubicacionDeCiudad').val('');
            $('#ubicacionDeCiudad').val('LOCALIDAD: ' + array[0] + ' >>  BARRIO: ' + array[1] + ' >>  CALLE: ' + array[2]);
        }
    });

    $('#ubicacionDeCiudad').click(function () {
        $('#selectUbicacion').mobiscroll('show');
        return false;
    });


    $('#clear').click(function () {
        $('#selectUbicacion').mobiscroll('clear');
        return false;
    });

}


$(function () {
    $('.process').click(function () {
        $.blockUI({
            css: {
                border: 'none',
                padding: '15px',
                backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                'font-size': '10px',
                opacity: .5,
                color: '#fff',
                border: '3px solid #333333'
            },
            message: '<img style=\"width:100px;\" src=\'/img/touch/loading.gif\'/><h1>Espere Por Favor !</h1>',
        });
    });
});
	
	 
	 
	
	 
	 
	 
 