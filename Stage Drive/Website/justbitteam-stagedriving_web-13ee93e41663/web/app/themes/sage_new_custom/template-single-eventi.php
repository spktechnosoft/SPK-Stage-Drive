<?php
/**
 * Template Name: Template singoli eventi
 */

 get_template_part('/lib/svg-image.php', null );

 global $wp_query;
 if (isset($wp_query->query_vars['id_event'])){
  $id_event =$wp_query->query_vars['id_event'];
}else{
  $id_event = '';
}


$current_string = $GLOBALS['SD_API_EVENTS'].'/'.urlencode($id_event).'';
//$current_string = "https://api.stagedriving.com/v1/events/15871204844764883731";
$current_url = htmlentities($current_string);


if (isset($_COOKIE['accessToken'])){
  $token = $_COOKIE['accessToken'];
}else{
  $token = '';
}

echo $icon_images;
?>

<div id="page-user" class="cstm-single-event">
<div class="max-width">
  <div class=" content-card-event margin-box-event">
    <div class="imgs-carousel imgs-carousel-resized">

    </div>
  </div>
</div>

<div class="wrap-detail-event wrap-detail-event-custom">
  <!-- detail event -->
  <div class="result-detail-event">

  </div>
  <!-- wrap detail event -->
  <div class="wrap-more-info">
    <div class="box-more-info">
      <p class="description-info  ">Altre informazioni</p>
    </div>
  </div>

  <!-- Altre informazioni -->
  <div class="altre-informazioni">

  </div>
  <!-- wrap description event -->
  <div class="wrap-more-info">
    <div class="box-more-info">
      <p class="description-info">Descrizione</p>
    </div>
  </div>
  <div class="description">

  </div>

  <?php
  //Detect special conditions devices
  $iPod    = stripos($_SERVER['HTTP_USER_AGENT'],"iPod");
  $iPhone  = stripos($_SERVER['HTTP_USER_AGENT'],"iPhone");
  $iPad    = stripos($_SERVER['HTTP_USER_AGENT'],"iPad");
  $Android = stripos($_SERVER['HTTP_USER_AGENT'],"Android");
  $Macintosh = stripos($_SERVER['HTTP_USER_AGENT'],"Macintosh");
  $webOS   = stripos($_SERVER['HTTP_USER_AGENT'],"webOS");
  $Windows   = stripos($_SERVER['HTTP_USER_AGENT'],"Windows");
  //do something with this information
  $message = 'PRENOTA UN PASSAGGIO';
  if( $iPod || $iPhone || $iPad){
    $deep_url = '';

  }else if($Android){
    $deep_url = 'https://appurl.io/Z-mZadUdI';

  }else if($webOS || $Macintosh){
    $deep_url = '';

  }else if($Windows ){
    $deep_url = '';

  }
  ?>
  <div class="col-xs-12" style="text-align:center;padding:0;">
      <!-- <a href="<?php //echo $deep_url ; ?>">
        <button type="button" class="btn btn-danger button-beta">
          <?php //echo $message ; ?>
        </button>
      </a> -->
  </div>

</div>

<script type="text/javascript">

jQuery(document).ready(function(){

  //spinjs
  spinnerInit('start');

  var url = '<?php echo $current_url;?>';
  var token = '<?php echo $token; ?>';
  jQuery.ajax({
    dataType: 'json',
    headers: { 'Authorization' : 'Bearer ' + token },
    url: url,
    data : 'limit=0&page=1',
      success: function(response, status, xhr) {
        var link_icon = get_image_svg('like_img');
        var link_icon_hover = get_image_svg('like_img_hover');
        var participate_icon = get_image_svg('participate_img');
        var participate_icon_hover = get_image_svg('participate_img_hover');
        var participant_icon = get_image_svg('participant_img');
        var participant_icon_hover = get_image_svg('participant_img_hover');
           //data_parsed = JSON.parse(response);
           console.log('response -->' , response);
           if (response.length != 0) {

               var id_event = response.id;
               var name_event = response.name;
               var description_event = response.description;
               var image_event = response.images;
               var start_event = response.start;
               var andress_event = response.address;
               var city_event = response.city;
               var town_event = response.town;
               var bookings_event = response.bookings;
               var parking = response.parking;
               var organizer = response.organizer;
               var website = response.website;
               var description = response.description;

               if (bookings_event == undefined) {
                 bookings_event = 0;
               }
               var comments_event = response.comments;

               var total_participants = get_participants_by_id(id_event);
               // with token
               var likes_event = response.likes
               if (likes_event == undefined) {
                 likes_event = 0;
               }

               console.log('id event  --> ' , id_event );
               console.log('value size  --> ' , image_event.length );
               var arrayElem=[];
               if ( image_event.length != 0 ) {

                  jQuery(image_event).each(function(key, value) {
                    var img_id = value.id;
                    var img_url = value.uri;
                    //console.log('image detail ---> ' , img_id );

                    // generate card slick //
                    var box_prod = "";
                    box_prod += '<div class="card-slick-event">';
                    box_prod += '<div style="background-image: url('+img_url +');" class="image-card img-card-resized"></div>';
                    box_prod += '<div class="custom-margin"></div>';
                    box_prod += '</div>';

                    arrayElem.push(jQuery(box_prod)[0]);
                  });
                  // append cards
                  jQuery('.imgs-carousel').append(arrayElem);
                  //  ------  carousel  ---------- //
                  jQuery('.imgs-carousel').slick({
                    infinite: true,
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    dots: true,
                    prevArrow: jQuery(''),
                    nextArrow: jQuery(''),
                  });

                  // remove dots
                  if (image_event.length == 1) {
                    jQuery('.slick-dots').css('display','none');
                  }

               } else {
                 jQuery('.wrap-carouser-event').css('display', 'none');
               }

                 // append name event //
                 if (name_event != '') {
                   var custom_html = '<div class="detail-event"><div class="row-event"><div class="logo-row"><div class="normal-img">'+get_image_svg('ticket_img')+'</div><div class="hover-img">'+get_image_svg('ticket_img_hover')+'</div></div><div class="box-description-event"><p class="description-event  ">'+name_event+'</p></div></div></div>';
                   jQuery('.result-detail-event').append(custom_html);
                   var title_event = '<h1><a href="/events"><span class="glyphicon glyphicon-menu-left"></a> '+ name_event +'</span></h1>';
                   //jQuery('#event-title').append(title_event);
                 }
                 // append date event //
                 if (start_event != '') {
                   var custom_html = '<div class="detail-event"><div class="row-event"><div class="logo-row">'+get_image_svg('calendar_img')+'</div><div class="box-description-event"><p class="description-event  ">'+moment(start_event).format('L')+' '+ moment(start_event).format('LT') +'</p></div></div></div>';
                   //var custom_html = '<div class="detail-event"><div class="row-event"><div class="logo-row"><div class="normal-img">'+get_image_svg('calendar_img')+'</div><div class="hover-img">'+get_image_svg('car_img_hover')+'</div></div><div class="box-description-event"><p class="description-event  ">'+start_event+'</p></div></div></div>';
                   jQuery('.result-detail-event').append(custom_html);
                 }
                 // append place event //
                 if (andress_event != '') {
                   var custom_html = '<div class="detail-event"><div class="row-event"><div class="logo-row">'+get_image_svg('geotag_img')+'</div><div class="box-description-event"><p class="description-event  ">'+andress_event+' '+ city_event +' '+ town_event +'</p></div></div></div>';
                   jQuery('.result-detail-event').append(custom_html);
                 }
                 // append parking event //
                 if (parking != '') {
                   var custom_html = '<div class="detail-event"><div class="row-event"><div class="logo-row">'+get_image_svg('parcheggio_icon')+'</div><div class="box-description-event"><p class="description-event  ">Parcheggio disponibile</p></div></div></div>';
                   jQuery('.altre-informazioni').append(custom_html);
                 }
                 // append organizer event //
                 if (organizer != '') {
                   var custom_html = '<div class="detail-event"><div class="row-event"><div class="logo-row">'+get_image_svg('partecipante_icon')+'</div><div class="box-description-event"><p class="description-event  ">'+ organizer +'</p></div></div></div>';
                   jQuery('.altre-informazioni').append(custom_html);
                 }
                  // append website event //
                  if (website != '') {
                   var custom_html = '<div class="detail-event"><div class="row-event"><div class="logo-row">'+get_image_svg('web_icon')+'</div><div class="box-description-event"><p class="description-event  ">'+ website +'</p></div></div></div>';
                   jQuery('.altre-informazioni').append(custom_html);
                 }
                 // append description event //
                 if (description != '') {
                   var custom_html = '<div class="detail-event"><div style="height: 100%;" class="row-event"><div class="box-description-event full"><p class="description-event  ">'+ description +'</p></div></div></div>';
                   jQuery('.description').append(custom_html);
                 }
                 // add like //
                 if(likes_event.length != 0) {
                   jQuery('.like-event .text-detail').text(likes_event.length);
                 } else {
                   jQuery('.like-event .text-detail').text('0');
                 }
                 // count passage
                 /*var string_passage = get_passage_by_event_id(id_event);
                 var text_passage = jQuery('.description-comunity.num_passage');
                 if ( string_passage != '' ) {
                   jQuery(text_passage).text(string_passage);
                 } else {
                   jQuery(text_passage).text('Passaggi non disponibili');
                 }*/
                 // get partecipant
                 var text_participants = jQuery('.description-comunity.num_participants');
                 var num_bookings = bookings_event.length;
                 if (num_bookings == undefined) {
                   num_bookings = 0;
                 }
                 if (num_bookings != 0) {
                   jQuery(text_participants).text('Parteciperanno '+ num_bookings + ' persone');
                 } else {
                   jQuery(text_participants).text('Nessuna partecipazione');
                 }
                 // get comments
                 var text_comments = jQuery('.description-comunity.num_comments');
                 if (comments_event == undefined) {
                   comments_event = 0;
                 }
                 var num_comments = comments_event.length;
                 if (num_comments != 0) {
                   jQuery(text_comments).text('Ci sono '+ num_comments +' commenti');
                 } else {
                   jQuery(text_comments).text('Non ci sono commenti');
                 }
         } // check count
         //spinjs
         spinnerInit('stop');
      }, // end success
      error: function(xhr) {
        console.log('error');
        jQuery('.result-detail-event').append('<h1 style="margin-top:100px;text-align:center;"> Evento non trovato <h1>');
        jQuery('.imgs-carousel').css('display', 'none');
          //spinjs
          spinnerInit('stop');
      },
  });
});

</script>
<!-- resize --->
<script type="text/javascript">
  // resize image //
  jQuery( document ).ready(function() {
    var current_width = jQuery(window).width();

    setTimeout(function(){
      console.log("interval");
      //resizeCard(current_width);
    }, 1000);
    // resize mobile //
    jQuery(window).resize(function() {
      var current_width = jQuery(window).width();
      //resizeCard(current_width);
    });
  });
</script>

<?php ?>
