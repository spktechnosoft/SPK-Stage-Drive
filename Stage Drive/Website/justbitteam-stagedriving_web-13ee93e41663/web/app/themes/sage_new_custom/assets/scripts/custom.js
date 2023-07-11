/* CUSTOM CHECK BROWSER JSBT */
function GetIEVersion() {
  var sAgent = window.navigator.userAgent;
  var Idx = sAgent.indexOf("MSIE");
  // If IE, return version number.
  if (Idx > 0)
    return parseInt(sAgent.substring(Idx+ 5, sAgent.indexOf(".", Idx)));

  // If IE 11 then look for Updated user agent string.
  else if (!!navigator.userAgent.match(/Trident\/7\./))
    return true;

  else
    return false; //It is not IE

}
var ver = false;
if ((GetIEVersion() > 0) || (navigator.userAgent.toLowerCase().indexOf('firefox') > -1)){
  //alert("This is IE " + GetIEVersion());
  ver = true;
}else {
  //alert("This no is IE ");
  ver = false;
}


/* CUSTOM JSBT */
window.getWindowBootstrapDevice = function () {
    var lg = 1200,
        md = 992,
        sm = 768;

    if (jQuery(window).width() >= lg) {
        return 'lg';
    } else if (jQuery(window).width() < lg && jQuery(window).width() >= md) {
        return 'md';
    } else if (jQuery(window).width() < md && jQuery(window).width() >= sm) {
        return 'sm';
    } else {
        return 'xs';
    }
};
jQuery(function () {
    jQuery(window).resize(function () {
        window.bootstrapDevice = window.getWindowBootstrapDevice();
    });
    window.bootstrapDevice = window.getWindowBootstrapDevice();

});

window.startPreloader = function () {
    jQuery('#status').show();
    jQuery('#preloader').show();
};

window.stopPreloader = function () {
    jQuery('#status').fadeOut(); // will first fade out the loading animation
    jQuery('#preloader').delay(5).fadeOut('slow'); // will fade out the white DIV that covers the website.
    jQuery('body').delay(5).css({'overflow': 'visible'});
};

window.sendAjaxPaginationRequest = function (page, cb) {
    // console.log('my_vars.ajaxurl: ', my_vars.ajaxurl);
    jQuery.post(
        my_vars.ajaxurl, {
            action: 'pagination_ajax',
            page: page
        },
        function (response) {
            cb && cb(response);
        }
    );
};

//mi serve per centrare gli item del slick , se sono pochi
//devono essere inseriti dalla dimmensione piu' grande alla piu' piccola
window.taxonomySlickBreakpoints = [
    {
        breakpoint: 1200,
        items: 3
    },
    {
        breakpoint: 992,
        items: 2
    },
    {
        breakpoint: 600,
        items: 1
    },
    {
        breakpoint: 0,
        items: 1
    }
];
window.eventsByTaxonomySlickArgs = function (isEnabledDots, isMobile, isSmall) {
  var fill = 0;
  if (isSmall) {
    fill = 2;
  }
    return {
        infinite: true,
        slidesToShow: 3-fill,
        slidesToScroll: 1,
        swipeToSlide: true,
        touchThreshold: 200,
        draggable: true,
        arrows: true,
        dots: true,
        centerMode: true,
        prevArrow: '<div class="slider-button container-fluid-custom slider-arrow prev-arrow"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span></div>',
        nextArrow: '<div class="slider-button container-fluid-custom slider-arrow next-arrow"><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></div>',
        centerPadding: "90px",
        //variableWidth: !isMobile,
        responsive: [
            {
                breakpoint: 2800,
                settings: {
                    arrows: false,
                    slidesToShow: 6-fill,
                }
            },
            {
                breakpoint: 2300,
                settings: {
                    arrows: false,
                    slidesToShow: 4-fill,
                }
            },
            {
                breakpoint: 2000,
                settings: {
                    arrows: true,
                    slidesToShow: 3-fill,
                }
            },
            {
                breakpoint: 1600,
                settings: {
                    slidesToShow: 3-fill,
                    centerPadding: "90px"
                }
            },
            {
                breakpoint: 1100,
                settings: {
                    slidesToShow: 2,
                    centerPadding: "90px"
                }
            },
            {
                breakpoint: 800,
                settings: {
                    dots: false,
                    slidesToShow: 1,
                    centerPadding: "60px"
                }
            },
            {
                breakpoint: 600,
                settings: {
                    dots: false,
                    centerPadding: "20px",
                    slidesToShow: 1,
                }
            }
        ]
    };
};

//mi serve per centrare gli item del slick , se sono pochi
//devono essere inseriti dalla dimmensione piu' grande alla piu' piccola
window.gallerySlickBreakpoints = [
    {
        breakpoint: 1200,
        items: 4
    },
    {
        breakpoint: 992,
        items: 2
    },
    {
        breakpoint: 600,
        items: 2
    },
    {
        breakpoint: 0,
        items: 1
    }
];
window.gallerySlickArgs = function (isEnabledDots, isMobile) {
    return {
        infinite: true,
        slidesToShow: 4,
        slidesToScroll: 1,
        swipeToSlide: true,
        touchThreshold: 200,
        draggable: true,
        //arrows: true,
        dots: true,
        centerMode: true,
        prevArrow: '<div class="slider-button container-fluid-custom slider-arrow prev-arrow"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span></div>',
        nextArrow: '<div class="slider-button container-fluid-custom slider-arrow next-arrow"><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></div>',
        centerPadding: "90px",
        // variableWidth: !isMobile,
        responsive: [
            {
                breakpoint: 2000,
                settings: {
                    arrows: false,
                }
            },
            {
                breakpoint: 1200,
                settings: {
                    dots: false,
                    arrows: true,
                    slidesToShow: 2,
                    slidesToScroll: 1,
                    centerPadding: "90px"
                }
            },
            {
                breakpoint: 992,
                settings: {
                    dots: false,
                    arrows: true,
                    slidesToShow: 2,
                    slidesToScroll: 1,
                    centerPadding: "60px"
                }
            },
            {
                breakpoint: 600,
                settings: {
                    dots: false,
                    arrows: true,
                    centerPadding: "20px",
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    };
};

if (jQuery('.back-to-top').length) {
    var scrollTrigger = 100, // px
        backToTop = function () {
            var scrollTop = jQuery(window).scrollTop();
            if (scrollTop > scrollTrigger) {
                jQuery('.back-to-top').addClass('show');
            } else {
                jQuery('.back-to-top').removeClass('show');
            }
        };
    backToTop();
    jQuery(window).on('scroll', function () {
        backToTop();
    });
    jQuery('.back-to-top').on('click', function (e) {
        e.preventDefault();
        jQuery('html,body').animate({
            scrollTop: 0
        }, 700);
    });
}

jQuery(document).ready(function () {
    jQuery('html').animate({scrollTop: 0}, 1);
    jQuery('body').animate({scrollTop: 0}, 1);

    jQuery('.slick-dotted ul.slick-dots').filter(function (index) {
      if (jQuery(this).find('li').length <= 1) {
        jQuery(this).hide();
      }
    });

    //  FLURRY EFFECT (NEVE)
    //  if (!window.getCookie('snow2017b')) {
    //   jQuery('html').flurry({
    //       character: "❄",
    //       color: "white",
    //       height: jQuery(window).height()*1.4,
    //       frequency: 60,
    //       speed: 9000,
    //       small: 5,
    //       large: 20,
    //       wind: 0,
    //       windVariance: 0,
    //       rotation: 0,
    //       rotationVariance: 90
    //   });
    //
    //   window.setCookieSec('snow2017b', 'snow2017b', 900);
    // }

    //  FLURRY EFFECT (NEVE)

    // if (!window.getCookie('snow2017c')) {
    //   jQuery('html').flurry({
    //       character: "🌺",
    //       color: "#675391",
    //       height: jQuery(window).height()*1.4,
    //       frequency: 60,
    //       speed: 9000,
    //       small: 24,
    //       large: 52,
    //       wind: 0,
    //       windVariance: 0,
    //       rotation: 0,
    //       rotationVariance: 90
    //   });
    //   window.setCookieSec('snow2017c', 'snow2017c', 900);
    // }
});


//EVENTS PAGINATION
window.getEventsByPage = function (parentWrapper, paged, maxElementsPerPage, showGadgets, searchObj, oldEvents, cb, noShowEmptyList, slug_provincia, remove_category ) {

    var evetsListContainer = null;
    var category_to_remove = null;

    if (remove_category) {
        category_to_remove = remove_category;
    }
    if (slug_provincia) {
      var query_string = '?provincia=' + slug_provincia;
    } else {
      var query_string = '';
    }

    if (parentWrapper) {
        evetsListContainer = parentWrapper + ' .list-events-container';
    } else {
        evetsListContainer = '.list-events-container';
    }

    evetsListContainer = jQuery(evetsListContainer);

    jQuery.post(
        my_vars.ajaxurl, {
            action: 'get_press_release',
            search: searchObj,
            oldEvents: oldEvents,
            paged: paged,
            ppd: maxElementsPerPage,
            cat_to_remove: category_to_remove,
            post_type: 'events'
        },
        function (data) {
            var data_parsed = JSON.parse(data);

                console.log(data_parsed);
            if (!data_parsed) {
                return false;
            }
            var eventsData = data_parsed['data'];
            var isSearch = data_parsed['isSearch'];
            var currentPage = data_parsed['paged'];
            var darkStyle = false;

            var buttonElement = jQuery(parentWrapper + ' .list-events-button');
            if (isSearch) {
                buttonElement = jQuery(parentWrapper + ' .search-button');
            }

            if (eventsData.length == 0 && currentPage == 1 && !noShowEmptyList) {
                //EMPTY LIST
                jQuery(parentWrapper + ' .events-list-container .default-title').hide();
                jQuery(parentWrapper + ' .events-list-container .default-title.empty-list').show();
            } else {
                jQuery(parentWrapper + ' .events-list-container .default-title.title').show();
                jQuery(parentWrapper + ' .events-list-container .default-title.empty-list').hide();
            }

            if (maxElementsPerPage * (currentPage) < data_parsed.totItems) {
                buttonElement.show('');
            } else {
                buttonElement.hide('');
            }


            //box_prod += '<div class="masonry-container"></div>';
            //  var background_img = "http://eventi-st1.mondoconv.it/app/uploads/2017/09/Testata_Mercati-1024x480.jpg";
            jQuery.each(eventsData, function (key, value) {
              // + value ['srcImg'] +
                var box_prod = "";

                box_prod += '<!-- add sizing element for columnWidth -->';
                box_prod += '<div class="list-events-item animated fadeInDown ">';
                box_prod += '<div class="event-image " >';
                box_prod += '<div class="zoom_img" >'; //add background at div for do effect zoom
                box_prod += '<a style="display:inline-block" href="' + value ['permalink'] +query_string+'">';
                box_prod += '<img class="img_event_zoom" style="width: 100%;" src="'+ value ['srcImg'] +'" > ';
                box_prod += '<div class="information-container ">';
                box_prod += '<div class="categories-container pull-right" style="display:' + showGadgets + '">';
                box_prod += getGadgetsDom(value);
                box_prod += '</div>';//categories-container
                box_prod += getEventDetailDom(value);
                box_prod += '</div>';//information-container
                box_prod += '</a>';
                box_prod += '</div>';// DIV ZOOM
                box_prod += '</div>';//event-image
                box_prod += '</div>';//list-events-item


                var $moreBlocks = jQuery(box_prod);
                evetsListContainer.append($moreBlocks);


                /*arrayElem.push(jQuery(box_prod)[0]);*/
            });
            /*jQuery('.grid').append(arrayElem).fadeIn('slow');*/

            cb && cb(eventsData, currentPage);
        }
    );
};

function getGadgetsDom(event) {
    var box_prod = "";
    if (event['gadgets'].length > 0) {
        for (var i = 0; i < event['gadgets'].length; i++) {
            box_prod += '<img class="img-responsive icon-gadget" src="' + event['gadgets'][i] + '"/>';
        }
    }
    return box_prod;
}

function getEventDetailDom(event) {
    var box_prod = "";
    box_prod += '<div class="bottom-container">';
    if ((event['hide_title_in_card'] && event['hide_title_in_card'] === false) || !event['hide_title_in_card']) {
      box_prod += '<div class="event-title">' + event['title'] + '</div>';
      box_prod += '<div class="event-date">' + event['date'] + '</div>';
    }
    box_prod += '<div class="location">' + event['city'] + '</div>';
    box_prod += '</div>';//bottom-container

    return box_prod;
}

window.fixSlickCenterMode = function (slickElement, breakpoints, breakpoint, items, style) {
    //per qualche ragione lo slick di gallery e taxonomy, hanno comportamenti diversi, quindi bisogna mettere due css diversi
    if (!style) style = '';

    console.log('style:', style);
    for (var i = 0; i < breakpoints.length; i++) {

        if (breakpoint >= breakpoints[i].breakpoint) {

            if (items <= breakpoints[i].items) {

                //slick in certi momenti sbaglia a calcolare la width
                if (style === 'taxonomy') {
                    //va fatto solo quando la width di slick e' calcolata male da se stesso(non sempre sbaglia il calcolo, dipende da che modalita' e' l'ispeziona)
                    var itemWidth = slickElement.find('.slick-slide').outerWidth(true);
                    var slickWidth = slickElement.find('.slick-track').css('width');
                    var correctWidth = (itemWidth * items) + 'px';

                    if(slickWidth !== correctWidth){
                        slickElement.find('.slick-list').css('width', '' + itemWidth * items);
                        slickElement.addClass(style);
                    }
                }

                slickElement.addClass('custom-slick-center-mode');
            } else {
                slickElement.removeClass('custom-slick-center-mode');

                if (style === 'taxonomy') {
                    //resetto la custom fix
                    slickElement.find('.slick-list').css('width', '100%');
                    slickElement.removeClass(style);
                }
            }
            break;
        }
    }
};
