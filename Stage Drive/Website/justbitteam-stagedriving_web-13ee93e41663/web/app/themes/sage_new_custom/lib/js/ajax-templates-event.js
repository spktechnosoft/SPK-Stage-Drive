
//
//  * AJAX LOAD PAGE  *
//

function get_last_char(string){
  var curl = string;
  var last_character = curl.substr(curl.length - 1);
  return last_character
}

function event_load_page(page_val, limit_val, start_date , fin_date, filter_case, user_id, callback) {

  var base_url = SD_API_EVENTS;
  var curl = base_url;

  if (page_val === undefined || page_val === '') {
    page_val = 0;
  }

  // start_date = "2020-05-01T00:00:00+02:00";
  // fin_date = "2020-09-05T00:00:00+02:00";

  console.log ('Events re: ', start_date , 'findate_chiamata : ', fin_date, 'filter: ',filter_case);
  //limit_val = 3;
  jQuery.ajax({
    dataType: 'json',
    url: curl,
    data: {
      page: page_val,
      limit: limit_val,
      sdate: start_date,
      fdate : fin_date,
      filter: filter_case
    },
    contentType: 'application/json; charset=utf-8',
    success: function(response, status, xhr) {
      var link_icon = get_image_svg('like_img');
      var link_icon_hover = get_image_svg('like_img_hover');
      var participate_icon = get_image_svg('participate_img');
      var participate_icon_hover = get_image_svg('participate_img_hover');
      var participant_icon = get_image_svg('participant_img');
      var participant_icon_hover = get_image_svg('participant_img_hover');

      if (callback) {
        callback(response);
      }

      if (response.length === 0 && page_val === 0) {
        console.log("no post ");
        jQuery(".no-post").css({"display":"block"});
      }
      if (response && response.length != 0) {
        jQuery(".no-post").css({"display":"none"});
        console.log('Response', response.length);

        var arrayElem=[];
        jQuery.each( response, function( key, value ) {
          console.dir(value);

          var id_event = value.id;
          var name_event = value.name;
          var description_event = value.description;
          var image_event = value.images[0];
          var total_participants = '';//get_participants_by_id(id_event);
          var date = value.start;
          var city = value.city;

          var dateAr = date.split('-');
          var newDate = moment(date, 'YYYY-MM-DDTHH:mm:ss.SSSZ').format('DD/MM/YYYY HH:mm');
          console.log('data evento --->', newDate);

          // detail image
          var image_event_id = '';
          var image_event_url = 'https://i1.wp.com/thefrontline.org.uk/wp-content/uploads/2018/10/placeholder.jpg?ssl=1';
          console.log('id event -> ' , id_event);
          if (image_event !== undefined) {
            image_event_id = image_event.id;
            image_event_url = image_event.uri;
          }

          // generate card //

          //console.log ('token -> ',token);

          var url_evento = '/events/';
          if (filter_case === 'created') {
            var url_evento = '/modifica-evento/?id_evento=';
          }
          var box_prod = "";
          box_prod += '<div class="card-event">';
          box_prod += '<div class="content-card-event">';
          box_prod += '<div class="box-card-description">';
          box_prod += '<a href="'+ url_evento +''+ id_event +'">';
          box_prod += '<p class="title-card "> '+ name_event +' </p>';
          box_prod += '<p class="subtitle-card "> '+ city +' </p>';
          box_prod += '<p class="date-card ">'+ newDate +'</p>';
          box_prod += '</a>';
          box_prod += '</div>';
          box_prod += '<div class="box-card-img">';
          box_prod += '<div class="img-card" style="background-image: url('+ image_event_url +');">';
          box_prod += '</div>';
          box_prod += '</div>';
          // box_prod += '<div class="box-card-icons">';
          // box_prod += '<div class="box-icons">';
          // box_prod += '<div class="img-icons">';
          // box_prod += '<div class="normal-image"> '+ link_icon +' </div>';
          // box_prod += '<div class="hover-image"> '+ link_icon_hover +' </div>';
          // box_prod += '</div>';
          // box_prod += '<p class="description-icons">Mi piace</p>';
          // box_prod += '</div>';
          // box_prod += '<div class="box-icons">';
          // box_prod += '<div class="img-icons">';
          // box_prod += '<div class="normal-image">' + participate_icon + ' </div>';
          // box_prod += '<div class="hover-image">'+ participate_icon_hover + ' </div>';
          // box_prod += '</div>';
          // box_prod += '<p class="description-icons">Parteciperò</p>';
          // box_prod += '</div>';
          // box_prod += '<div class="box-icons">';
          // box_prod += '<div class="img-icons">';
          // box_prod += '<div class="normal-image">' + participant_icon + '</div>';
          // box_prod += '<div class="hover-image">' + participant_icon_hover + '</div>';
          // box_prod += '</div>';
          // box_prod += '<p class="description-icons">'+ total_participants +'</p>';
          // box_prod += '</div>';
          box_prod += '</div>';
          box_prod += '</div>';
          box_prod += '</div>';

          arrayElem.push(jQuery(box_prod)[0]);
        });
        // append cards
        if (start != '' || end != '' || filter != ''){
          jQuery('.all-cards').html('');
        }

        jQuery('.post-infinite-scroll').append(arrayElem);
        window.call_finish_global = true;

      } else {
        // nascondiamo il div hook //

        jQuery('.hook-height').css('display', 'none');
      }
      //spinjs
      spinnerInit('stop');
    },
    error: function(xhr) {
      console.log('error');
      window.call_finish_global = true;
      //call_finish = true;
      //spinjs
      spinnerInit('stop');
    },

  });

};

//
//  * END AJAX LOAD PAGE *
//

// ----------------------------------------------------------------------- //

//
//  * GEE PASSAGE BY EVENT ID *
//

function get_passage_by_event_id(id_event) {
  var id_event = id_event;
  var base_url = 'https://api.staging.stagedriving.com/v1/rides?eventId=';
  var url = base_url + id_event;
  //console.log('base_url --> ' , url);
  var value = '';
  jQuery.ajax({
    dataType: 'json',
    async: false,
    url: url,
    success: function(response, status, xhr) {
      //console.log('----- inside passage -----');
      value = 'Ci sono N passaggi';
    },
    error: function(xhr) {
      //console.log('----- inside passage error -----');
      value = 'Non ci sono passaggi';
    },
  });// end ajax
  return value
} // end function


/********************** */
/** Prendi User_ID *****/
/******************** */
