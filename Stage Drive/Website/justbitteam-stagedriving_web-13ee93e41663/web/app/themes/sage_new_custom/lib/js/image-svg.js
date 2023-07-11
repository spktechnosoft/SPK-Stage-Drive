/**
* GET IMAGE SVG
**/

var get_image_svg = function(name_svg) {
  //console.log('name svg --> ' , name_svg );
  var all_image = {
     like_img: '<span class="icon-mipiace_icon icon-font font-25"> </span>', // like_icon_global
     like_img_hover: '<span class="icon-mipiace_icon-hover icon-font font-25"> </span>', // $like_icon_hover_global
     participate_img: '<span class="icon-partecipero_icon icon-font font-25"> </span>', // $participate_icon_global
     participate_img_hover: '<span class="icon-partecipero_icon_hover icon-font font-25"> </span>', // $participate_icon_hover_global
     participant_img: '<span class="icon-partecipanti-icon icon-font font-25"> </span>', // $participant_icon_global
     participant_img_hover: '<span class="icon-partecipanti-hover icon-font font-25"> </span>', // $participant_icon_hover_global
     // template single event
     calendar_img : '<span class="icon-questa_sett_icon icon-font font-35"> </span>',
     geotag_img : '<span class="icon-geotag_intorno_icon icon-font font-35"> </span>',
     ticket_img : '<span class="icon-stage-icon icon-font font-35"></span>',
     ticket_img_hover : '<span class="icon-stage-icon-hover icon-font font-35"></span>',
     invite_img : '<span class="icon-aggiungi-partecipante-icon icon-font font-35"></span>',
     car_img : '<span class="icon-car-grande-icon icon-font font-35"></span>',
     car_img_hover : '<span class="icon-car-grande-icon-hover icon-font font-35"></span>',
     comment_img : '<span class="icon-comment-icon icon-font font-35"></span>',
     web_icon : '<svg class="icon icon-web-icon icon-font font-35"><use xlink:href="#icon-web-icon"></use></svg>',
     parcheggio_icon : '<svg class="icon icon-parcheggio-icon icon-font font-35"><use xlink:href="#icon-parcheggio-icon"></use></svg>',
     partecipante_icon : '<svg class="icon icon-partecipante_icon icon-font font-35"><use xlink:href="#icon-partecipante_icon"></use></svg>',
  };
  return all_image[name_svg]
}
