<?php
/**
 * Template Name: Template modifica evento
 */

 get_template_part('/lib/svg-image.php', null );

 global $wp_query;
 if (isset($_GET['id_evento'])){
   $id_event =$_GET['id_evento'];
 } else{
   $id_event = '';
 }

 $current_string = $GLOBALS['SD_API_EVENTS']+'/'.urlencode($id_event).'';
 $current_url = $current_string;

 if (isset($_COOKIE['accessToken'])){
   $token = $_COOKIE['accessToken'];
 } else{
   $token = '';
 }

  if ($token != '') {

 ?>



 <div id="update-event" style="padding-top: 60px;">
     <div class="container title-container">
       <?php if ($id_event !=''){ ?>
       <h1>Modifica Evento</h1>
       <?php } else {?>
         <h1>Crea Evento</h1>
       <?php } ?>
     </div>
     <div class="container max-width" >
         <div class="row">
                 <div class="col-sm-12">
                     <form id="event-form" name="event-form">
                         <div class="form-group">
                           <label for="nome">Nome</label>
                           <input id="nome" name="nome" type="nome" class="form-control" aria-describedby="nomeHelp" placeholder="" value="" data-validation-length="min4" data-validation="required">
                         </div>

                         <div class="form-group">
                           <label for="category">Categoria</label>
                           <select id="category" name="category" class="form-control" aria-describedby="category" data-validation="required">
                             <option value="" default></option>
                           </select>
                         </div>

                         <div class="form-group form-img">
                             <label  for="InputImg">Immagine</label>
                             <label class="label" data-toggle="tooltip" title="Immagine Evento" style="pointer-events:none;">
                               <!-- <img class="rounded" id="imgevento" src="<?php //echo get_template_directory_uri() ?>/assets/images/jovanotti-tour.jpg" alt="imgevento"> -->
                               <img class='rounded' id='imgevento' style="width: 100%" src='https://i1.wp.com/thefrontline.org.uk/wp-content/uploads/2018/10/placeholder.jpg?ssl=1' alt='imgevento' value="">
                               <input type="file" class="sr-only" id="inputimg" name="inputimg" accept="image/*">
                             </label>
                             <!--- multiple img --->
                             <div class="more-img">
                               <div class="description-slider">
                                 <p class="text-slider"> Inserisci le immagini </p>
                               </div>

                               <!-- insert image -->
                               <div class="wrap-add-image test">
                                 <div class="icons-img add-card">
                                   <i class="fas fa-plus-square fa-3x"></i>
                                 </div>
                               </div>
                               <!-- end insert image -->
                               <div class="secondary-img">

                               </div>
                             </div>

                             <style media="screen">

                               .wrap-add-image {
                                 position: relative;
                                 height: 30px;
                                 padding-top: 20px;
                                 padding-bottom: 20px;
                                 margin-bottom: 15px;
                               }
                               .wrap-add-image .icons-img {
                                 cursor: pointer;
                               }
                               .wrap-add-image .icons-img svg {
                                 font-size: 2.5em !important;
                               }
                               .text-slider {
                                 text-align: center;
                                 color: #555;
                                 margin-top:20px;
                                 margin-bottom: 5px;
                               }
                               /** fix icon **/
                               .icons-img {
                                 position: absolute;
                                 top: 50%;
                                 transform: translate(-50%, -50%);
                                 left: 50%;
                               }

                               .select-img, .delete-img {
                                 display: inline-block;
                                 padding: 5px;
                                 background: rgba(255, 255, 255, 0.7);
                                 border-radius: 10px;
                                 margin: 0 5px;
                                 transition: all 0.5s;
                               }
                               .select-img:hover, .delete-img:hover {
                                 background: #fff;
                               }

                               .icons-img svg {
                                 font-size: 22px !important;
                               }
                               /** end fix icon **/
                               .secondary-img {
                                 padding: .2em .8em .3em;
                                 text-align: center;
                               }
                               .secondary-img .image {
                                 position: relative;
                                 width: 150px;
                                 height: 100px;
                                 margin-left: 10px;
                                 margin-right: 10px;
                                 background-position: center center;
                                 display: inline-block;
                                 cursor: pointer;
                                 background-size: cover;
                               }

                               .secondary-img .image .icons-img {
                                 opacity: 0;
                                 transition: all 0.2s;
                               }

                               .secondary-img .image:hover .icons-img {
                                 opacity: 1;
                               }
                               /* add image */
                               .secondary-img .image-empty .icons-img {
                                 opacity: 1;
                                 transition: all 0.2s;
                               }

                               .secondary-img .image-empty:hover .icons-img path {
                                 fill: red;
                               }

                               @media (max-width: 1024px) {
                                 .secondary-img .image .icons-img {
                                   opacity: 1;
                                 }
                               }
                               /* custom arrow */
                               .arrow-custom-right.slick-arrow {
                                 position: absolute;
                                 top: 50%;
                                 transform: translateY(-50%);
                                 right: 0;
                                 cursor: pointer;
                               }
                               .arrow-custom-left.slick-arrow {
                                 position: absolute;
                                 top: 50%;
                                 transform: translateY(-50%);
                                 left: 0;
                                 cursor: pointer;
                               }

                               .arrow-custom-right.slick-arrow.slick-disabled svg path {
                                 opacity: 0.2;
                               }

                               .arrow-custom-left.slick-arrow.slick-disabled svg path {
                                 opacity: 0.2;
                               }

                               .more-img svg path {
                                 transition: all 0.5s;
                                 fill: #fa8348;
                               }

                               .more-img svg:hover path {
                                 fill: #ED3E5B;
                               }

                               @media (max-width: 1024px) {
                                 .secondary-img {
                                   padding: 0.2em 2rem .3em;
                                 }

                                 .secondary-img .image {
                                   height: 180px;
                                 }
                               }

                               .wrap-add-images {
                                 margin-top: 15px;
                                 margin-bottom: 15px;
                                 text-align: center;
                               }
                               .add-images svg {
                                 cursor: pointer;
                               }
                               .add-images svg path {
                                 fill: #fa8348;
                                 transition: all 0.3s;
                               }
                               .add-images svg:hover path {
                                 fill: #ED3E5B;
                               }

                             </style>
                             <!--- end multiple img --->

                             <!--  Form per caricare immagine -->
                             <div class="progress">
                               <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div>
                             </div>
                           <div class="alert" role="alert"></div>
                           <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                             <div class="modal-dialog" role="document">
                               <div class="modal-content">
                                 <div class="modal-header">
                                   <h5 class="modal-title" id="modalLabel">Taglia immagine</h5>
                                   <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                     <span aria-hidden="true">&times;</span>
                                   </button>
                                 </div>
                                 <div class="modal-body">
                                   <div class="img-container">
                                     <img id="image" src="https://avatars0.githubusercontent.com/u/3456749">
                                   </div>
                                 </div>
                                 <div class="modal-footer">
                                   <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                   <button type="button" class="btn btn-primary" id="crop">Ok</button>
                                 </div>
                               </div>
                             </div>
                           </div>
                             <!-- Carica immagine -->
                         </div>
                         <div class="form-group"> <!-- Date input -->
                             <label class="control-label" for="date">Data Evento Inizio</label>

                             <input name="date" type='text' class="form-control" placeholder="GG/MM/AAAA" id='date' data-validation="required"/>
                         </div>
                         <div class="form-group"> <!-- Date input -->
                             <label class="control-label" for="finish">Data Evento Fine</label>

                             <input name="finish" type='text' class="form-control" placeholder="GG/MM/AAAA" id='finish' data-validation="required"/>
                         </div>

                         <div class="form-group">
                             <label for="address">Indirizzo</label>
                             <input id="address" type="text" class="form-control" data-validation="required" placeholder="Inserisci un indirizzo">
                         </div>
                         <div class="form-group">
                             <label for="citta">Città</label>
                             <input id="citta" name="citta" type="text" class="form-control" data-gtype="locality" aria-describedby="imgHelp" placeholder="" data-validation="required" disabled>
                         </div>
                         <div class="form-group">
                             <label for="town">Provincia</label>
                             <input id="town" name="town" type="text" class="form-control" data-gtype="administrative_area_level_3" aria-describedby="imgHelp" placeholder="" data-validation="required" disabled>
                         </div>
                         <div class="form-group">
                             <label for="country">Nazione</label>
                             <input id="country" name="country" type="text" class="form-control" data-gtype="country" aria-describedby="country" placeholder="" data-validation="required" disabled>
                         </div>

                         <div class="form-group">
                             <label for="zipcode">CAP</label>
                             <input id="zipcode" name="zipcode" type="text" class="form-control" data-gtype="postal_code" aria-describedby="imgHelp" placeholder="" data-validation="required" disabled>
                         </div>

                         <div class="form-group">
                             <label for="organizer">Organizzatore</label>
                             <input id="organizer" name="organizer" type="text" class="form-control" aria-describedby="imgHelp" placeholder="" data-validation="required">
                         </div>
                         <div class="form-group">
                             <label for="parking">Parcheggio</label>
                             <select id="parking" name="parking" type="text" class="form-control" aria-describedby="parking" placeholder="" data-validation="required">
                               <option value="Si">Si</option>
                               <option value="No">No</option>
                             </select>
                         </div>
                         <div class="form-group">
                             <label for="website">Website</label>
                             <!--- if you need change input in required add this attribute data-validation="url" --->
                             <input id="website" name="website" type="text" class="form-control" aria-describedby="website" placeholder="" value="" >
                         </div>
                         <div class="form-group">
                           <label for="status">Stato pubblicazione</label>
                           <select id="status" name="status" class="form-control" aria-describedby="status" data-validation="required">
                             <option value="published">Pubblico</option>
                             <option value="draft">Bozza</option>
                           </select>
                         </div>

                         <div style="margin-top:50px" class="form-group form-description">
                            <label for="description">Descrizione</label>
                             <textarea id="description" name="descrizione" class="editor" class="form-control" rows="4" cols="50" data-validation="required">
                             </textarea>
                             <span class="help-block form-error form-error-custom" style="display:none;">
                               Campo obbligatorio
                             </span>
                         </div>

                     </form>
                 </div><!-- col-->
         </div>


     </div><!--container-->
     <div class="container">
         <div class="row">
             <div class="col-xs-12" style="text-align:center;padding:0;">
                 <!-- Alert -->
                     <div id="alert-save" style ="margin-top:100px;" class="alert alert-success alert-dismissible hidden">
                         <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                         Evento salvato con <strong>Successo</strong>
                     </div>

                     <?php if ($id_event !=''){ ?>
                       <!--- IF WE HAVE EVENT'S ID WE ADD DELETE BUTTON --->
                       <button id ="salva" type="submit" class="btn btn-danger button-beta align-button-beta">
                         SALVA
                       </button>
                       <!--- Delete event --->
                       <button id="delete" type="submit" class="btn btn-delete button-beta align-button-beta ">
                         ELIMINA
                       </button>
                       <!-- end Delete event -->

                     <?php } else {?>

                       <button id ="salva" type="submit" class="btn btn-danger button-beta">
                         SALVA
                       </button>

                     <?php } ?>

             </div>

         </div>
     </div>
  </div>

 <script>
 // ---------- change images order ----------//
 var images_event = [];
 var activate_slider = false;

 var event_obj = new Object();
 var token = '<?php echo $token; ?>';
 var id_evento = '<?php echo $id_event; ?>';
 var imageLoaded = false;

 jQuery(document).ready(function() {
    // add end date
    let input_date_start = jQuery(".form-control[name=date]");
    let input_date_end = jQuery(".form-control[name=finish]");
    // click data di inizio
    jQuery("#date").on("dp.hide",function (e) {
      if (input_date_end.val() == "" ) {
        jQuery(".form-control[name=finish").val(input_date_start.val());
      }
    });

     // setup editor
     jQuery('textarea.editor').ckeditor();
     CKEDITOR.on( 'instanceReady', function(  ) {

       SD_BUNDLE.data['event-category'].items.forEach(element => {
           jQuery('select#category').append(jQuery('<option>', {
               value: element.name,
               text: element.description
           }));
       });
       var latitude_event = 0;
       var longitude_event = 0;

       var address = document.getElementById('address');
       var autocomplete = new google.maps.places.Autocomplete(address, {
           types: ['geocode', 'establishment']
       });
       autocomplete.setComponentRestrictions({'country': ['it']});
       autocomplete.setFields(['address_component', 'geometry']);
       autocomplete.addListener('place_changed', fillInAddress);

       function geolocate() {
           if (navigator.geolocation) {
               navigator.geolocation.getCurrentPosition(function(position) {
                   var geolocation = {
                       lat: position.coords.latitude,
                       lng: position.coords.longitude
                   };
                   var circle = new google.maps.Circle({
                       center: geolocation,
                       radius: position.coords.accuracy
                   });
                   autocomplete.setBounds(circle.getBounds());
               });
           }
       }

       geolocate();

       function fillInAddress() {
           // Get the place details from the autocomplete object.
           var place = autocomplete.getPlace();
           console.log('Place', place);

           latitude_event = place.geometry.location.lat();
           longitude_event = place.geometry.location.lng();

           // for (var component in componentForm) {
           //   document.getElementById(component).value = '';
           //   document.getElementById(component).disabled = false;
           // }

           // Get each component of the address from the place details,
           // and then fill-in the corresponding field on the form.
           clean_inputs_autocomplete();
           for (var i = 0; i < place.address_components.length; i++) {
               var addressType = place.address_components[i].types[0];
               console.log('Address type', addressType);
               console.log('Address', place.address_components[i]);

               var field = jQuery('input[data-gtype="' + addressType + '"]');

               if (field) {
                   field.val(place.address_components[i]['long_name']);
               }
           }
           check_inputs_autocomplete();
       }

       var goToProfile = false;
       var user = localStorage.getItem("user");
       if (user) {
           user = JSON.parse(user);
           console.log('user', user);
           if (!userHasRole(user, 'organizer')) {
               console.log('user doesnt has permission');
               goToProfile = true;
           }
       } else {
           goToProfile = true;
       }

       if (goToProfile) {

           window.showAdvMessage('Attenzione', 'Devi essere un organizzatore per creare un nuovo evento', function() {
               window.location.href = '/profile?to=' + window.location.pathname + '&f=1';
           });
           return;
       }

       //setup datepicker
       jQuery(function() {
           var options = {
               locale: "it",
           };
           jQuery('#date').datetimepicker(options);
           jQuery('#finish').datetimepicker(options);
       });

       var url = SD_API_EVENTS + '/<?php echo $id_event;?>';
       if (id_evento != '') {
           spinnerInit('start');
           jQuery.ajax({
               dataType: 'json',
               url: url,
               type: "GET",
               async: true,
               data: '',
               success: function(response, status, xhr) {

                   var link_icon = get_image_svg('like_img');
                   var link_icon_hover = get_image_svg('like_img_hover');
                   var participate_icon = get_image_svg('participate_img');
                   var participate_icon_hover = get_image_svg('participate_img_hover');
                   var participant_icon = get_image_svg('participant_img');
                   var participant_icon_hover = get_image_svg('participant_img_hover');
                   //data_parsed = JSON.parse(response);
                   console.log('response -->', response);
                   event_obj = response;
                   if (response.length != 0) {

                       var id_event = response.id;
                       var name_event = response.name;
                       var description_event = response.description;
                       var image_event = response.images;
                       var start_event = response.start;
                       var finish_event = response.finish;
                       var city_event = response.city;
                       var town_event = response.town;
                       var address_event = response.address;
                       var country_event = response.country;
                       var zipcode_event = response.zipcode;
                       var coordinate = response.coordinate;
                       var organizer = response.organizer;
                       var parking = response.parking;
                       var category = response.category;
                       var website = response.website;
                       var img_evento = response.images;
                       var status = response.status;

                       var bookings_event = response.bookings;
                       if (bookings_event == undefined) {
                           bookings_event = 0;
                       }
                       var comments_event = response.comments;

                       var total_participants = ''; //get_participants_by_id(id_event);
                       // with token
                       var likes_event = response.likes
                       if (likes_event == undefined) {
                           likes_event = 0;
                       }

                       // append name event //
                       if (name_event != '') {
                           jQuery('#nome').val(name_event);
                           var title_event = '<h1><a href="/my-events"><span class="glyphicon glyphicon-menu-left"></a> ' + name_event + '</span></h1>';
                           // jQuery('#event-title').append(title_event);
                       }
                       // append date event //
                       if (start_event != '') {
                           var data = moment(start_event, 'YYYY-MM-DDTHH:mm:ss.SSSZ').format('DD/MM/YYYY HH:mm');
                           jQuery('#date').val(data);

                       }
                       if (finish_event != '') {
                           var finishDt = moment(finish_event, 'YYYY-MM-DDTHH:mm:ss.SSSZ');
                           var finish = finishDt.format('DD/MM/YYYY HH:mm');
                           jQuery('#finish').val(finish);

                       }
                       // append place event //

                       if (city_event != '' && address_event != '' && town_event != '' && country_event != '' && organizer != '') {
                           jQuery('#citta').val(city_event);
                           jQuery('#town').val(town_event);
                           jQuery('#country').val(country_event);
                           jQuery('#address').val(address_event);
                           jQuery('#zipcode').val(zipcode_event);
                           jQuery('#organizer').val(organizer);

                       }

                       jQuery('#parking').val(parking);
                       jQuery('#website').val(website);
                       jQuery('#category').val(category);
                       jQuery('#status').val(status);

                       // controllo se sono presenti le immagini nel post
                       if (img_evento !== 'undefined' && img_evento.length > 0) {
                         // la prima immagine la pusho nella card principale
                           jQuery("img#imgevento").replaceWith("<img class='rounded' style='width: 100%' id='imgevento' src=" + img_evento[0]['uri'] + " alt='imgevento'>");
                           imageLoaded = img_evento[0]['uri'];
                           // pusho la prima immagine presente
                           images_event.unshift(imageLoaded);
                           //-- mostro le seconde immagini --//
                           jQuery(img_evento).each(function( index ) {
                             // pusho dalla seconda immagine in poi //
                             //console.log(" tutte le immagini presenti ", img_evento[index]['uri'] );
                             if (index > 0) {
                               images_event.push(img_evento[index]['uri']);
                             }
                           });
                           order_img(images_event);
                       }

                       // append description eventi //
                       if (description_event != '') {
                           console.log(description_event);
                           jQuery('textarea.editor').val('' + description_event);
                       }

                       // add like //
                       if (likes_event.length != 0) {
                           jQuery('.like-event .text-detail').text(likes_event.length);
                       } else {
                           jQuery('.like-event .text-detail').text('0');
                       }

                       // count passage
                       var string_passage = ''; //get_passage_by_event_id(id_event);
                       var text_passage = jQuery('.description-comunity.num_passage');
                       if (string_passage != '') {
                           jQuery(text_passage).text(string_passage);
                       } else {
                           jQuery(text_passage).text('Passaggi non disponibili');
                       }
                       // get partecipant
                       var text_participants = jQuery('.description-comunity.num_participants');
                       var num_bookings = bookings_event.length;
                       if (num_bookings == undefined) {
                           num_bookings = 0;
                       }
                       if (num_bookings != 0) {
                           jQuery(text_participants).text('Parteciperanno ' + num_bookings + ' persone');
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
                           jQuery(text_comments).text('Ci sono ' + num_comments + ' commenti');
                       } else {
                           jQuery(text_comments).text('Non ci sono commenti');
                       }
                   } // check count
                   spinnerInit('stop');
               }, // end success
               error: function(xhr) {
                   console.log('error');
                   jQuery('.result-detail-event').append('<h1 style="margin-top:100px;text-align:center;"> Evento non trovato <h1>');
                   jQuery('.imgs-carousel').css('display', 'none');
                   spinnerInit('stop');
                   // evento non presente redirect to 404 //
                   var home_url = '<?php echo get_home_url(); ?>'+'/404';
                   console.log(' home_url --> ' , home_url);
                   window.location = home_url;

               }

           });
       }
       // controllo caricamento immagini multiple
       /******************* test slider caricamento multiplo *******************/

         jQuery('.add-card').on('click', function() {
           jQuery('#imgevento').trigger('click');
         });

         setInterval(function(){
           // controllo se esistono le immagini
           if (images_event !== 'undefined' && images_event.length > 0) {
             //console.log("ci sono nuove immagini caricate");
             // se viene caricata una seconda o più immagini pusho il contenuto
             if (activate_slider == true) {
               activate_slider = false;

               if (images_event.length > 1) {
                 // inserisco le card tranne la 1
                 for (j = 1; j <images_event.length; j++) {
                   //console.log('url img slider for --> ' , images_event[j]);
                   let card_slider = "<div class='image choose-img' style='background-image: url("+images_event[j]+")' value='"+images_event[j]+"'><div class='icons-img'><div class='select-img'><i class='far fa-check-square'></i></div><div class='delete-img'><i class='far fa-trash-alt'></i></div></div></div>";
                   jQuery('.secondary-img').slick('slickAdd',card_slider);
                 }
               }
               select_img_carousel();
               delete_img_carousel();
             }
           }
         }, 1000);

         activate_slick();
         function activate_slick() {
           jQuery('.secondary-img').slick({
             infinite: false,
             slidesToShow: 3,
             slidesToScroll: 1,
             arrows: true,
             dots: true,
             nextArrow: '<div class="arrow-custom-right"><i class="fas fa-arrow-right"></i></div>',
             prevArrow: '<div class="arrow-custom-left"><i class="fas fa-arrow-left"></i></div>',
             responsive: [
               {
                 breakpoint: 4096,
                 settings: { slidesToShow: 3 }
               },
               {
                 breakpoint: 1024,
                 settings: { slidesToShow: 2 }
               },
               {
                 breakpoint: 600,
                 settings: { slidesToShow: 1 }
               }
             ]
           });
         }
         //++++++ controllo card slider ++++++//

         // ----------- select img ----------//
         function select_img_carousel() {
           jQuery(".select-img").click(function(){
             let current_card_slider = this.closest('.choose-img')
             let current_card_img = jQuery(current_card_slider).attr("value");
             let primary_image = jQuery('#imgevento').attr("value");
              // cambio i parametri della card
              // replace della card slider
              jQuery(current_card_slider).css({'background-image': 'url('+primary_image+')' }).attr("value",primary_image);
              // replace delle card principale
              jQuery('#imgevento').attr("value",current_card_img).attr("src",current_card_img);
              // riformulo l ordinamento dell array //
              // riprendo l indice del immagine che viene selezionata come prima //
              let index_img = images_event.indexOf(current_card_img);
              // se l indice esiste la rimuovo dall array delle immagini
              if (index_img > -1) {
                images_event.splice(index_img, 1);
                // posiziono l immagine selezionata come prima nell array
                images_event.unshift(current_card_img);
                // mostro il messaggio
                window.showAdvMessage('Fatto', 'Immagine sostituita', function() {});
                console.log( "ordinare le immagini nello slider" );
                console.log("images_event -> ", images_event);
                //order img
                order_img(images_event);
              } else {
                window.showAdvMessage('Errore', 'Riprova più tardi', function() {});
              }
           });
         }
         // --------- end select img --------//

         //-------- delete select img --------//
         function delete_img_carousel() {
           jQuery(".delete-img").click(function(){
             let current_card_slider = this.closest('.choose-img');
             let current_card_img = jQuery(current_card_slider).attr("value");
             console.log(" array img -> " , images_event );
             // riprendo l indice dell immagine da rimuovere
             let index_img = images_event.indexOf(current_card_img);
             // se l indice esiste la rimuovo dall array delle immagini
             //console.log("indice img -> ", index_img);
             console.log("index image to remove -> ", index_img);
             console.log("array globale img -> ", images_event);
             if (index_img > -1) {
               images_event.splice(index_img, 1);
               console.log("array immagini less delete image -> ", images_event);
               window.showAdvMessage('Fatto', 'Immagine rimossa', function() {});
               // rimuovo le immagini dallo slider
               jQuery('.secondary-img').slick('removeSlide', null, null, true);

               order_img(images_event);

             } else {
               window.showAdvMessage('Errore', 'Riprova più tardi', function() {});
             }
           });
         }
         //------ end delete select img ------//
         //------- ORDER IMG --------//
         function order_img(images_event){
           let all_images = images_event;
           console.log(" images order_img --> ", all_images);
           // rimuovo le cards dentro slick //
           jQuery('.secondary-img').slick('removeSlide', null, null, true);

           jQuery(all_images).each(function( index ) {
             let image_slider = all_images[index];
             //console.log("image_slider to add in to slider -> ", image_slider);
             if (index > 0) {
               let card_slider = "<div class='image choose-img' style='background-image: url("+image_slider+")' value='"+image_slider+"'><div class='icons-img'><div class='select-img'><i class='far fa-check-square'></i></div><div class='delete-img'><i class='far fa-trash-alt'></i></div></div></div>";
               jQuery('.secondary-img').slick('slickAdd',card_slider);
             }
           });

           select_img_carousel();
           delete_img_carousel();
         }
         //------- END ORDER IMG  -------//

       /******************* test slider caricamento multiplo *******************/
       // end controllo caricamento immagini multiple //

       var isValid = false;
       jQuery.validate({
           modules: 'date, security',
           onModulesLoaded: function() {
               //alert('All modules loaded!');
           },
           borderColorOnError: '#F00',
           addValidClassOnAll: true,
           lang: 'it',
           form: '#event-form',
           onError: function($form) {
               isValid = false;
               alert('Validation of form ' + $form.attr('id') + ' failed!');
           },
           onSuccess: function($form) {
               alert('The form ' + $form.attr('id') + ' is valid!');
               isValid = true;
               return false; // Will stop the submission of the form
           },
           onValidate: function($form) {
               return {
                   element: $('#some-input'),
                   message: 'This input has an invalid value for some reason'
               }
           },
           onElementValidate: function(valid, $el, $form, errorMess) {
               console.log('Input ' + $el.attr('name') + ' is ' + (valid ? 'VALID' : 'NOT VALID'));

           }
       });

       // delete event //
       jQuery('#delete').click(function() {
         window.showMessageDeleteEvent('Attenzione', 'Sei sicuro di voler eliminare questo evento?', id_evento, token);
       });
       // end delete event //

       jQuery('#salva').click(function() {
           spinnerInit('start');

           var valid = jQuery('#event-form').isValid();

           var description = jQuery("#description").val();
           var nome = jQuery("input#nome").val();
           var address = jQuery('input#address').val();
           var country = jQuery('input#country').val();
           var city = jQuery('input#citta').val();
           var town = jQuery('input#town').val();
           var zipcode = jQuery('input#zipcode').val();
           var start_natural = jQuery('input#date').val();
           var start = moment(start_natural, 'DD/MM/YYYY HH:mm').format();
           var finish_natural = jQuery('input#finish').val();
           var finish = moment(finish_natural, 'DD/MM/YYYY HH:mm').format();
           var organizer = jQuery('input#organizer').val();
           var parking = jQuery('select#parking option:selected').val();
           var category = jQuery('select#category option:selected').val();
           var website = jQuery('input#website').val();
           var img_caricato = document.getElementById('imgevento').src;
           var img64 = jQuery('img#imgevento').attr('src');
           var status = jQuery('select#status option:selected').val();

           //console.log('img_caricato --> ', img_caricato );
           if (description === '') {
             jQuery('.form-description').addClass('has-error');
             // descrizione form custom //
             jQuery('.form-error-custom').css({'display':'block'});
             // custom editor error
             jQuery('.form-description #cke_description').css({'border':' 1px solid #a94442'});
             //valid = false;
           } else {
             jQuery('.form-description').removeClass('has-error');
             jQuery('.form-error-custom').css({'display':'none'});
             // cambio il colore alla label perche il controllo del form non attribuisce la classe has-success
             jQuery('.form-description').find('label').css({'color':'#000'});
             // custom editor
             jQuery('.form-description #cke_description').css({'border':' 1px solid #d1d1d1'});
           }
           // console.log('description -->' , description);
           // console.log('nome -->' , nome);
           // console.log('address -->' , address);
           // console.log('country -->' , country);
           // console.log('city -->' , city);
           // console.log('town -->' , town);
           // console.log('zipcode -->' , zipcode);
           // console.log('start_natural -->' , start_natural);
           // console.log('start -->' , start);
           // console.log('finish_natural -->' , finish_natural);
           // console.log('finish -->' , finish);
           // console.log('organizer -->' , organizer);
           // console.log('category -->' , category);
           // console.log('img_caricato -->' , img_caricato);
           // console.log('img64 -->' , img64);
           // console.log('status -->' , status);


           // controllo immagine //
           if (imageLoaded &&
             nome != '' &&
             address != '' &&
             country != '' &&
             city != '' &&
             town != '' &&
             zipcode != '' &&
             city != '' &&
             start_natural != '' &&
             finish_natural != '' &&
             organizer != '' &&
             category != '' &&
             img_caricato != '' &&
             img64 != '' &&
             status != '' &&
             description != ''
           ) {
               console.log(' CAMPI VALIDI');
               valid = true;
           } else {
               console.log(' CAMPI non validi ');
               valid = false;
           }
           console.log(' imageLoaded click salva --> ' , imageLoaded);
           // controllo se è presente l'immagine
           if (!imageLoaded) {
             jQuery('.form-img').addClass('has-error');
           } else {
             jQuery('.form-img').removeClass('has-error');
           }
           // controll error
           jQuery(".has-error").each(function(){
             jQuery(this).find('label').css({'color':'#a94442'});
           });
           // controll success
           jQuery(".has-success").each(function(){
             jQuery(this).find('label').css({'color':'#000'});
           });

           console.log('value valid --> ' ,  valid );

           //------block save------//

           if (valid == true) {

               if (id_evento != '') {
                   event_obj.name = nome;

                   event_obj.description = description;
                   event_obj.address = address;
                   event_obj.country = country;

                   // lasciamo il controllo attivo su imageLoaded successivamente controllo l array delle immagini //
                   console.log("tutte le immagini da salvare -> ", images_event);
                   if (imageLoaded != null) {
                       // cambio image nell evento
                       /************* NEW **************/
                       if (images_event.length >= 0) {
                         var index_images = 0;
                         event_obj.images = [];
                         jQuery(images_event).each(function( index ) {
                           let current_image = images_event[index];
                           array_event = {
                             'uri': current_image,
                             'normalUri': current_image
                           };
                           event_obj.images[index_images] = array_event;
                           index_images++;
                         });
                       }

                   } else {
                       event_obj.images = [];
                   }
                   event_obj.city = city;
                   event_obj.town = town;
                   event_obj.zipcode = zipcode;
                   event_obj.start = start;
                   event_obj.finish = finish;
                   event_obj.organizer = organizer;
                   event_obj.parking = parking;
                   event_obj.category = category;
                   event_obj.website = website;
                   var coordinate = {
                       "latitude": latitude_event,
                       "longitude": longitude_event
                   };
                   console.log(coordinate);
                   event_obj.coordinate = coordinate;
                   event_obj.status = status;

                   console.log('dopo modifica update --> ', event_obj);
                   update_event(event_obj);
               } else {

                   console.log('finish', finish);

                   event_obj = {
                       "name": nome,
                       "description": description,
                       "address": address,
                       "country": country,
                       "city": city,
                       "town": town,
                       "zipcode": zipcode,
                       "start": start,
                       "finish": finish,
                       "organizer": organizer,
                       "parking": parking,
                       "category": category,
                       "website": website,
                       "status": status,
                       "coordinate": {
                           "latitude": latitude_event,
                           "longitude": longitude_event
                       },
                       "images": []
                   }

                   if (imageLoaded != null) {
                       //var uri_imgevento = upload_image(img64);
                       //console.log('uri_imgevento -> ',uri_imgevento);
                       // salvataggio immagini multiple
                       if (images_event.length >= 0) {
                         var index_images = 0;
                         event_obj.images = [];
                         jQuery(images_event).each(function( index ) {
                           let current_image = images_event[index];
                           array_event = {
                             'uri': current_image,
                             'normalUri': current_image
                           };
                           event_obj.images[index_images] = array_event;
                           index_images++;
                         });
                       }
                   }

                   console.log('dopo modifica create --> ', event_obj);
                   create_event(event_obj);
               }
           } else {
               console.log('fail');
               spinnerInit('stop');
               window.showAdvMessage('Attenzione', 'Ci sono alcuni campi non validi');
           }

       });

       /** check field autocomplete **/
       function check_inputs_autocomplete() {
         console.log('check field ');
         let field_locality = jQuery('input[data-gtype="locality"]');
         let field_province = jQuery('input[data-gtype="administrative_area_level_3"]');
         let field_country = jQuery('input[data-gtype="country"]');
         let field_postal_code = jQuery('input[data-gtype="postal_code"]');

         console.log( 'value field_locality ->', field_locality );
         console.log( 'value field_province ->', field_province );
         console.log( 'value field_country ->', field_country );
         console.log( 'value field_postal_code ->', field_postal_code );

         if (!field_locality.val()) {
           jQuery(field_locality).prop("disabled", false);
         }
         if (!field_province.val()) {
           jQuery(field_province).prop("disabled", false);
         }
         if (!field_country.val()) {
           jQuery(field_country).prop("disabled", false);
         }
         if (!field_postal_code.val()) {
           jQuery(field_postal_code).prop("disabled", false);
         }
       }
       /** end check field autocomplete **/
       /** clean input autocomplete **/
       function clean_inputs_autocomplete() {
         let field_locality = jQuery('input[data-gtype="locality"]');
         let field_province = jQuery('input[data-gtype="administrative_area_level_3"]');
         let field_country = jQuery('input[data-gtype="country"]');
         let field_postal_code = jQuery('input[data-gtype="postal_code"]');

           jQuery(field_locality).val('').prop("disabled", true);
           jQuery(field_province).val('').prop("disabled", true);
           jQuery(field_country).val('').prop("disabled", true);
           jQuery(field_postal_code).val('').prop("disabled", true);
       }
       /** end clean input autocomplete **/

       function update_event(event_obj) {
           var eventId = "<?php echo $id_event ?>";
           var url_modify = SD_API_EVENTS + '/' + eventId;

           spinnerInit('start');

           jQuery.ajax({
               url: url_modify,
               type: "PUT",
               async: true,
               contentType: "application/json",
               data: JSON.stringify(event_obj),
               success: function(response, status, xhr) {
                   console.log(response);
                   //jQuery("#alert-save").removeClass('hidden');
                   new Noty({
                       type: 'success',
                       text: 'Evento salvato con successo',
                   }).show();
                   location.reload();

                   spinnerInit('stop');
               },
               error: function(response) {
                   console.log(response);
                   window.call_finish_global = true;
                   //call_finish = true;
                   spinnerInit('stop');
                   new Noty({
                       type: 'error',
                       text: 'Si è verificato un errore: ' + response.responseJSON.message,
                   }).show();
               }
           });
       }

       function create_event(event_obj) {
           var url_modify = SD_API_EVENTS;

           jQuery.ajax({
               url: url_modify,
               type: "POST",
               async: true,
               contentType: "application/json",
               data: JSON.stringify(event_obj),
               success: function(response, status, xhr) {
                   console.log(response);
                   console.log(status);
                   spinnerInit('stop');
                   jQuery("#alert-save").removeClass('hidden');
                   window.showAdvMessage('Fatto', 'Il tuo evento è stato pubblicato!', function() {
                       location.href = '/modifica-evento?id_evento=' + response.id;
                   });
               },
               error: function(response) {
                   console.log(response);
                   spinnerInit('stop');
                   window.call_finish_global = true;
                   new Noty({
                       type: 'error',
                       text: 'Si è verificato un errore: ' + response.responseJSON.message,
                   }).show();
                   //call_finish = true;
               }
           });
       }

       spinnerInit('stop');

       // your stuff here
   });
 });

 // Crop Immage
 window.addEventListener('DOMContentLoaded', function() {
       var imgevento = document.getElementById('imgevento');
       var image = document.getElementById('image');
       var input = document.getElementById('inputimg');
       var $progress = jQuery('.progress');
       var $progressBar = jQuery('.progress-bar');
       var $alert = jQuery('.alert');
       var $modal = jQuery('#modal');
       var cropper;

       //jQuery('[data-toggle="tooltip"]').tooltip();
       input.addEventListener('change', function(e) {
           var files = e.target.files;

           var done = function(url) {
               input.value = '';
               image.src = url;
               $alert.hide();
               $modal.modal('show');
           };
           var reader;
           var file;
           var url;
           if (files && files.length > 0) {
               file = files[0];

               if (URL) {

                   done(URL.createObjectURL(file));
               } else if (FileReader) {
                   reader = new FileReader();
                   reader.onload = function(e) {
                       done(reader.result);
                   };
                   reader.readAsDataURL(file);
               }
           }
       });
       $modal.on('shown.bs.modal', function() {
           cropper = new Cropper(image, {
               dragMode: 'move',
               aspectRatio: 1200 / 630,
               autoCropArea: 1,
               restore: false,
               guides: false,
               center: false,
               highlight: false,
               cropBoxMovable: false,
               cropBoxResizable: false,
               toggleDragModeOnDblclick: false,
           });
       }).on('hidden.bs.modal', function() {
           cropper.destroy();
           cropper = null;
       });
       document.getElementById('crop').addEventListener('click', function() {
           var initialImgeventoURL;
           var canvas;
           $modal.modal('hide');
           if (cropper) {
               canvas = cropper.getCroppedCanvas({
                   width: 1200,
                   height: 630,
               });
               initialImgeventoURL = imgevento.src;
               imgevento.src = canvas.toDataURL();
               //$progress.show();
               $alert.removeClass('alert-success alert-warning');

               jQuery("img#imgevento").replaceWith("<img class='rounded' style='width: 100%;' id='imgevento' src=" + imgevento.src + " alt='imgevento'>");

               spinnerInit('start');
               var imageUri = upload_image(imgevento.src, function(result) {
                   spinnerInit('stop');

                   if (result === 'fail') {
                       new Noty({
                           type: 'error',
                           text: 'Si è verificato un error durante il caricamento',
                       }).show();
                       return;
                   }
                   jQuery("img#imgevento").replaceWith("<img class='rounded' style='width: 100%;' id='imgevento' src=" + imgevento.src + " alt='imgevento' value=" + result + ">");
                   imageLoaded = result;
                   jQuery('.form-img label').css({'color':'#000'});
                   console.log('Image loaded', imageLoaded);
                   // rimuovo le cards dentro slick //
                   jQuery('.secondary-img').slick('removeSlide', null, null, true);
                   // pusho la nuova immagine
                   images_event.unshift(imageLoaded);
                   console.log('images_event -> ', images_event );
                   // nuova immagine caricata controllo lo slider
                   activate_slider = true;
                   /******* carico l immagine *******/
               });
           }
       });
   });
 </script>

 <?php
 } else { ?>

 <div class="container-fluid">
   <h1 style="margin-top:100px;text-align:center;"></h1>
   <div class="col-xs-12" style="text-align:center;padding:0;">
       <p>Questa pagina è riservata, devi accere per visualizzarla.</p>
       <a id="signin-button" href="/login">
         <button type="button" class="btn btn-danger button-beta">
           ACCEDI
         </button>
       </a>
   </div>
 </div>

 <script>
 jQuery('#signin-button').attr('href', '/login?to='+window.location.pathname);
 </script>

 <?php
 } ?>

 <style media="screen">
   .slick-dots {
     display: none !important;
   }
 </style>
