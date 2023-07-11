window.showMessage = function (title, message, type) {
  jQuery.fancybox.open('<div class="message"><h3>'+title+'</h3><p>'+message+'</p></div>');
};

window.showMessageDeleteEvent = function (title, message, eventId, token) {
  var content = '<div class="message">'+
  '<h2>'+title+'</h2>'+
  '<p>'+message+'</p>'+
  '<p class="delete-event value-delete-event" style="float:left;">SI</p>'+
  '<p class="value-delete-event" data-value="0" data-fancybox-close style="float:right;">NO</p>'+
  '</div>';
  // Open fancybox
  jQuery.fancybox.open(content);

  jQuery( ".delete-event" ).click(function() {
    console.log(' click delete event with id --> ', eventId);
    // chiudo fancybox
    jQuery.fancybox.close( true );
    // faccio partire la chiamata
    DeleteEventByid(eventId, token);
  });
};

window.showAdvMessage = function(title, message, callback) {
  jQuery.fancybox.close( true );

  opts  = jQuery.extend( true, {
    title     : title,
    message   : message,
    okButton  : 'OK',
    noButton  : 'Annulla',
    callback  : callback
  }, {} );

  jQuery.fancybox.open({
    type : 'html',
    src  :
    '<div class="fc-content">' +
    '<h3>' + opts.title   + '</h3>' +
    '<p>'  + opts.message + '</p>' +
    '<p class="tright">' +
    '<a class="detail-button" data-fancybox-close data-value="1" style="cursor: pointer;">' + opts.okButton + '</a>' +
    // '<a data-value="0" class="" data-fancybox-close>' + opts.noButton + '</a>' +
    '</p>' +
    '</div>',
    opts : {
      animationDuration : 350,
      animationEffect   : 'material',
      infobar: false,
      toolbar: false,
      modal : false,
      hash : false,
      baseTpl :
      '<div class="fancybox-container fc-container" role="dialog" tabindex="-1">' +
      '<div class="fancybox-bg"></div>' +
      '<div class="fancybox-inner">' +
      '<div class="fancybox-stage"></div>' +
      '</div>' +
      '</div>',
      afterClose : function( instance, current, e ) {
        var button = e ? e.target || e.currentTarget : null;
        var value  = button ? jQuery(button).data('value') : 0;

        if (opts.callback) {
          opts.callback( value );
        }
      }
    }
  });
};

window.showConfirm = function(opts) {
  jQuery.fancybox.close( true );

    opts  = jQuery.extend( true, {
      title     : 'Non sei registrato',
      message   : '',
      okButton  : 'OK',
      noButton  : 'Non mi interessa',
      callback  : jQuery.noop
    }, opts || {} );

    jQuery.fancybox.open({
      type : 'html',
      src  :
      '<div class="fc-content">' +
      '<h3>' + opts.title   + '</h3>' +
      '<p>'  + opts.message + '</p>' +
      '<p class="tright">' +
      '<a data-value="1" class="detail-button" data-fancybox-close>' + opts.okButton + '</a>' +
      '<a data-value="0" class="detail-button" data-fancybox-close>' + opts.noButton + '</a>' +
      '</p>' +
      '</div>',
      opts : {
        animationDuration : 350,
        animationEffect   : 'material',
        infobar: false,
        toolbar: false,
        modal : false,
        hash : false,
        baseTpl :
        '<div class="fancybox-container fc-container" role="dialog" tabindex="-1">' +
        '<div class="fancybox-bg"></div>' +
        '<div class="fancybox-inner">' +
        '<div class="fancybox-stage"></div>' +
        '</div>' +
        '</div>',
        afterClose : function( instance, current, e ) {
          var button = e ? e.target || e.currentTarget : null;
          var value  = button ? jQuery(button).data('value') : 0;

          opts.callback( value );
        }
      }
    });
}

/********************** */
/** Delete event by ID **/
function DeleteEventByid(eventId, token) {
  // attivo lo spinner
  spinnerInit('start');
  var url = SD_API_EVENTS + '/' + eventId;

  jQuery.ajax({
      dataType: 'json',
      headers: { 'Authorization' : 'Bearer ' + token },
      url: url,
      type: "DELETE",
      async: true,
      contentType: "application/json",
      success: function(response, status, xhr) {
          // console.log('success status --> ' , status);
          // console.log('success response --> ' , response);
          spinnerInit('stop');
          window.showAdvMessage('Evento eliminato', 'Il tuo evento è stato eliminato!', function() {
              location.href = '/my-events';
          });
      },
      error: function(response) {
          console.log('Error --> ' , response);
          spinnerInit('stop');
          window.showAdvMessage('Ops..', 'Il tuo evento non è stato eliminato, prova più tardi!', function() {
              location.href = '/my-events';
          });
      }
  });

}
/********************** */
