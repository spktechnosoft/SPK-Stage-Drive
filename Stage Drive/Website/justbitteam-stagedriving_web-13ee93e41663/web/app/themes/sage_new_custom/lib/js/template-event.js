
//
//  * FUNCTION RESIZER  *
//

function getPercentageChange(oldNumber, newNumber){
  var decreaseValue = oldNumber - newNumber;
  return (decreaseValue / oldNumber) * 100;
  //console.log('eredwdwqdqwdwqd');
}

function getPercentage(max_height, percentageToRemove){
  var num = max_height;
  var val = num - (num * percentageToRemove);
  console.log('val -> ' , val );
  return val;
}

function resizeCard(current_width) {

  var max_widht = 712;
  var max_height= 442;

  if (current_width < max_widht) {
    console.log('current width -> ' , current_width );
    // passiamo la larghezza massima ( che segna il 100% ) e la larghezza attuale in modo da riprendere la differenza
    var DifferenceToRemove = getPercentageChange(max_widht, current_width)
    console.log('percentuale mancante al complessivo -> ' , DifferenceToRemove );
    // arrotondo il risultato
    var round_difference = Math.round(DifferenceToRemove);
    console.log(' round difference --> ' , round_difference);
    //round_difference = 10;
    // passiamo l atezza massima ( che segna il 100% ) e il valore da rimuovere Es: 100 - 0,10% = 90
    if (round_difference < 10) {
      round_difference = parseFloat('0.0' + round_difference);
      console.log('round_difference  ->' ,  round_difference );
    } else {
      round_difference = parseFloat('0.' + round_difference);
    }
    var height = getPercentage(max_height, round_difference);

    console.log('percent -> ' , height );

    jQuery('.content-card-event').css('height' ,  height +'px');
  }
}

//
//  * END FUNCTION RESIZER  *
//

// ----------------------------------------------------------------------- //

//
//  * MODAL TIME  *
//

function modal_time() {
  jQuery( ".modal-period" ).click(function() {
    var value_period = jQuery(this).attr('value');
    if( value_period != '' ) {
      var img_svg = jQuery(this).find('.img-svg').html();
      var text_replace = jQuery('.text-replace-time');
      var image_replace = jQuery('.img-replace-time');
      var text_child = jQuery(this).find('.text').text();
      text_replace.text(text_child);
      image_replace.html(img_svg);
      jQuery("#modal-time").modal("hide");

      // inserire chimata AJAX per fare il replace nei post
        var data_start_switch ='';
        start = '';
        end = '';
      switch(text_child) {
        case 'QUESTA SETTIMANA':
            console.log('text_child settimana --> ' , text_child );
            jQuery('div#clear-filter').show();
              start = moment().startOf('week').format();
              end = moment().endOf('week').format();
          break;
        case 'QUESTO MESE':
              console.log('text_child mese corrente--> ' , text_child );
              jQuery('div#clear-filter').show();
              start = moment().startOf('month').format();
              end = moment().endOf('month').format();
          break;
        case 'PROSSIMI MESI':
              console.log('text_child prossimi mesi--> ' , text_child );
              jQuery('div#clear-filter').show();
              jQuery('.all-cards').html('');
              start = moment().startOf('month').add(1, 'months').format();
              end = '';
          break;
        default:
            start = '';
            end = '';
      }
      console.log("start -> ", start, " end -> ", end );
      jQuery('.post-infinite-scroll').empty();
      page_call_event();

    }
  });
};

//
//  * END MODAL TIME *
//

// ----------------------------------------------------------------------- //

//
//  * MODAL PLACE  *
//

function modal_place() {
  jQuery( ".modal-place" ).click(function() {
    var value_period = jQuery(this).attr('value');
    if( value_period != '' ) {
      var img_svg = jQuery(this).find('.img-svg').html();
      var text_replace = jQuery('.text-replace-place');
      var image_replace = jQuery('.img-replace-place');
      var text_child = jQuery(this).find('.text').text();
      text_replace.text(text_child);
      image_replace.html(img_svg);
      jQuery("#modal-place").modal("hide");
      console.log('text_child --> ' , text_child );
      // inserire chimata AJAX per fare il replace nei post
      filter = '';
      user_id = null;
      switch(text_child) {
        case 'SCELTI PER TE':

              filter = 'personal';
          break;
        case 'I PIÙ GRANDI':
              filter ='bigger';
          break;
        case 'DI TENDENZA':
              filter ='trending';
          break;
        // case 'INTORNO A TE':
        //       filter = '';
        //       jQuery('.all-cards').html('');
        // break;
        default:
              filter = '';
      }
     console.log("filter selected -> ", filter);
     jQuery('.post-infinite-scroll').empty();
     page_call_event();
    }
  });
}

//
//  * END MODAL PLACE *
//

// ----------------------------------------------------------------------- //


// ------------------------- Call to Event ----------------------------- //

function prepare_date(){
  today = moment().format('l');
  start = moment().startOf('month').format()
}

function page_call_event(user_id, callback) {
  console.log('evento 1 --> start :', start, 'end :', end, 'filter: ', filter);
  event_load_page( page_val, limit_val, start, end, filter, user_id, callback);

  // ------ infinite scroll ----- //

  //var call_finish = true;
  window.call_finish_global = true;
  //var page_val_scroll = 1;
  //var limit_val_scroll = 2;
  jQuery(window).scroll(function(event) {

   var hook_top = jQuery('.hook-height');
   // distanza elemento dal top-page
   var height_element_top = jQuery(hook_top).offset().top;
   // altezza view attuale
    var height_view = jQuery(window).height();
    // cordinate attuali dello scroll //
    var current_height = jQuery(window).scrollTop();
    // console.log('---------------');
    // console.log('distanza elemento top page --> ' , height_element_top);
    // console.log('altezza view attuale --> ' , height_view);
    // console.log('px attuali scroll --> ' , current_height);

    // se superiamo le cordinate dell hook div cariamo i post seguenti //

    if (current_height >= height_element_top && window.call_finish_global === true ) {
     console.log('evento 2 --> start :', start, 'end :', end, 'filter: ',filter);
     // console.log('activate function ');
      //console.log(' value call finish --> ' , call_finish_global );
       window.call_finish_global = false;
        page_val_scroll = page_val_scroll + 1;
        //console.log('page val --> ' , page_val_scroll , ' limit_val --> ' , limit_val_scroll );
        console.log(' call finish global --> ' ,  window.call_finish_global );
       event_load_page(page_val_scroll, limit_val_scroll, start, end, filter, callback);
    }
    //console.log('call finish --> ' , call_finish_global );
  });
}

//  spinjs

var spinner = null;
function spinnerInit(command) {

  if (command == 'start') {
    jQuery('#loading').addClass('loading');
  } else if (command == 'stop') {
    jQuery('#loading').removeClass('loading');
  }


  if (command == 'start' && spinner == null){
    var opts = {
      lines: 10, // The number of lines to draw
      length: 0, // The length of each line
      width: 4, // The line thickness
      radius: 45, // The radius of the inner circle
      scale: 1.6, // Scales overall size of the spinner
      corners: 1, // Corner roundness (0..1)
      color: '#ffffff', // CSS color or array of colors
      fadeColor: 'transparent', // CSS color or array of colors
      speed: 1, // Rounds per second
      rotate: 0, // The rotation offset
      animation: 'spinner-line-fade-quick', // The CSS animation name for the lines
      direction: 1, // 1: clockwise, -1: counterclockwise
      zIndex: 2e9, // The z-index (defaults to 2000000000)
      className: 'spinner', // The CSS class to assign to the spinner
      top: '50%', // Top position relative to parent
      left: '50%', // Left position relative to parent
      shadow: 'none', // Box-shadow for the lines
      position: 'absolute' // Element positioning
    };
    spinner = new Spinner(opts);
    console.log('spinner start');
    //jQuery('#spinnerContainer').after(spinner.spin().el);
  } else if (command == 'start' && spinner !== null) {
    console.log('spinner show');
    //jQuery('#spinnerContainer').after(spinner.spin().el);
  } else{
   console.log('spinner stop');
   //if (spinner !== null) spinner.stop();
  }

}
