<?php
/**
 * Template Name: Template modify user
 */

wp_head();
get_template_part('/lib/svg-image.php', null );

//$login_page = 'http://sd.localhost:8989/login';
$login_page = get_field('link_login');
?>

<?php echo $icon_images;

if (isset($_COOKIE['accessToken'])){
    $token = $_COOKIE['accessToken'];
  }else{
    $token = '';
  }

if ($token != '') {

?>

<script>
function getUrlParameter(name) {
  name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
  var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
  var results = regex.exec(location.search);
  return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};
</script>

<div id="page-user">

    <div class="container-fluid head-user">
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4 image-container">
                <div class="img-profile" >
                <!--<img src="<?php //echo $user_img; ?>" class="rounded-circle" alt="immagine profilo" height="90" width="90">-->

                <label id="img-profile" class="label" data-toggle="tooltip" title="Change your avatar">
                <!-- <svg class="icon icon-add-icon-hover"><use xlink:href="#icon-add-icon-hover"></use></svg> -->
                <img id="avatar" src="#" alt="avatar" class="rounded-circle" alt="immagine profilo" height="90" width="90">
                <input type="file" class="sr-only" id="input" name="image" accept="image/*">

                </label>
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
                                <button type="button" class="btn btn-primary" id="crop">Salva</button>
                            </div>
                        </div>
                    </div>
                </div>


                </div>
                <p class="user-name"></p>
            </div>
            <div class="col-sm-4"></div>
        </div>
    </div>
<div id="update-user" class="container max-width" style="margin-top:80px;">
    <div class="row">
        <div class="col-sm-12">
            <form id="user-form" method="get">
                <div class="form-group">
                    <label for="InputNome">Nome</label>
                    <input id="nome" name="nome" type="nome" class="form-control" id="InputNome" aria-describedby="nomeHelp" placeholder="" value="">

                </div>
                <div class="form-group">
                    <label for="InputCognome">Cognome</label>
                    <input name="cognome" id="cognome" type="cognome" class="form-control" placeholder="" value="">
                </div>
                <div class="form-group">
                    <label for="InputEmail">Email</label>
                    <input name="email" id="email" type="email" class="form-control" placeholder="" value="">
                </div>

                <div class="form-group">
                  <label for="InputSesso">Sesso</label>
                    <select name="gender" id="gender" class="form-control custom-select custom-select-lg mb-3">
                            <option id="male" value="male" default>Uomo</option>
                            <option id="female" value="female">Donna</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="companyName">Nome Azienda</label>
                    <input name="companyName" id="companyName" type="text" class="form-control" data-validation="required">
                </div>
                <div class="form-group">
                    <label for="companyVatId">P.I. Azienda</label>
                    <input name="companyVatId" id="companyVatId" type="text" class="form-control" data-validation="required">
                </div>
                <div class="form-group">
                    <label for="companyAddress">Indirizzo Azienda</label>
                    <input name="companyAddress" id="companyAddress" type="text" class="form-control" data-validation="required">
                </div>
                <div class="form-group">
                    <label for="companyZipcode">CAP Azienda</label>
                    <input name="companyZipcode" id="companyZipcode" type="text" class="form-control" data-validation="required">
                </div>
                <div class="form-group">
                    <label for="companyRef">Contatto di riferimento</label>
                    <input name="companyRef" id="companyRef" type="text" class="form-control" data-validation="required" placeholder="Nome del contatto">
                </div>
                <div class="form-group">
                    <label for="companyCity">Città Azienda</label>
                    <input name="companyCity" id="companyCity" type="text" class="form-control" data-validation="required">
                </div>
                <div class="form-group">
                    <label for="companyCountry">Nazione Azienda</label>
                    <input name="companyCountry" id="companyCountry" type="text" class="form-control" data-validation="required">
                </div>

                <div class="cont_swithc">
                    <button id="mostra" type="button" class="btn btn-lg btn-toggle" data-toggle="button" aria-pressed="false" autocomplete="off">
                        <div class="handle"></div>

                    </button>
                </div>
                <div class="cont_swithc">
                    <button id="offerte" type="button" class="btn btn-lg btn-toggle" data-toggle="button" aria-pressed="false" autocomplete="off">
                        <div class="handle"></div>

                    </button>
                </div>
                <div class="cont_button">
                    <!-- Alert -->
                    <div id="alert-save" style ="margin-top:100px;" class="alert alert-success alert-dismissible hidden">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            Modifiche salvate con <strong>Successo</strong>
                    </div>

                    <button id="salva" type="button" class="btn btn-danger button-beta">SALVA MODIFICHE</button>
                </div>
            </form>
        </div> <!-- row -->
    </div><!-- col -->
</div><!--container-->

<?php } else { ?>

<div class="container-fluid container-modify-user">

  <div class="center-box">
    <h1 style="text-align:center;">Ops...</h1>
    <div class="col-xs-12" style="text-align:center;padding:0;">
        <a href="<?php echo $login_page; ?>">
          <button type="button" class="btn btn-danger button-beta">
            ACCEDI
          </button>
        </a>
    </div>
  </div>

</div>

<?php
} ?>


<script>

var user_mem;
var validator;
var current_image;
jQuery( document ).ready(function() {
    //spinjs
    spinnerInit('start');

    var token = '<?php echo $token ?>';
    console.log(token);

    prendi_utente();

    jQuery.validate({
        modules: 'date, security',
        onModulesLoaded: function() {
            //alert('All modules loaded!');
        },
        borderColorOnError: '#F00',
        addValidClassOnAll: true,
        lang: 'it',
        form: '#user-form',
        onError: function($form) {
            alert('Validation of form ' + $form.attr('id') + ' failed!');
        },
        onSuccess: function($form) {
            alert('The form ' + $form.attr('id') + ' is valid!');
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


//     jQuery.validator.setDefaults({
//         debug: true,
//         success: "valid"
//     });
//     validator = jQuery( "#user-form" ).validate({
//     rules: {
//         email: {
//         required: true,
//         email: true
//         }
//     }
//    });
});

    var token = '<?php echo $_COOKIE['accessToken'] ?>';

    function call_to_update(user_obj) {

        spinnerInit('start');
        var accountId = user_obj.id;
        console.log('Saving account' , user_obj);
        var url_modify = SD_API_ACCOUNTS+'/'+accountId;
        jQuery.ajax({
            url: url_modify,
            type: "PUT",
            contentType: "application/json",
            data : JSON.stringify(user_obj),
            success: function(response,status, xhr){
                console.log('response -> ' , response);
                console.log('status --> ', status);
                //jQuery("#alert-save").removeClass('hidden');
                new Noty({
                    type: 'success',
                    text: 'Fatto',
                }).show();
                // jQuery("#alert-save").css({'display':'block'});
                prendi_utente();
            },
            error: function(response) {
                console.log(' response error -> ' ,response);
                window.call_finish_global = true;
                spinnerInit('stop');
            }
        });
    }

    function prendi_utente() {
        var url = SD_API_ACCOUNTS+'/me';

        jQuery.ajax({
        type: "GET",
        dataType: "json",
        url: url,
        success: function(response,status, xhr){
            current_image = response['images'][0]['normalUri'];

            if (response['images'][0]['uri'] != ''){
                var url_img = response['images'][0]['uri'];
                // console.log('url_img --> ' , url_img);
                //jQuery( "#img-profile" ).append('<img id="avatar" src="'+response['images'][0]['uri']+'" alt="avatar" class="rounded-circle" alt="immagine profilo" height="90" width="90">');
                jQuery( "#img-profile img#avatar" ).attr("src",url_img);
            }
            if (response['firstname'] != '' && response['lastname'] !=''){
                jQuery( ".user-name" ).empty();
                jQuery( ".user-name" ).append( '<span>'+response['firstname']+' '+ response['lastname']+'</span>');
                jQuery('#nome').attr('value',response['firstname']);
                jQuery('#cognome').attr('value',response['lastname']);
            }
            if (response['email'] != ''){
                jQuery('#email').attr('value',response['email']);
            }
            if (response['companyName'] != ''){
                jQuery('#companyName').attr('value',response['companyName']);
            }
            console.log(response['companyVatId']);
            if (response['companyVatId'] != ''){
                jQuery('#companyVatId').attr('value',response['companyVatId']);
            }
            if (response['companyAddress'] != ''){
                jQuery('#companyAddress').attr('value',response['companyAddress']);
            }
            if (response['companyZipcode'] != ''){
                jQuery('#companyZipcode').attr('value',response['companyZipcode']);
            }
            if (response['companyRef'] != ''){
                jQuery('#companyRef').attr('value',response['companyRef']);
            }
            if (response['companyCity'] != ''){
                jQuery('#companyCity').attr('value',response['companyCity']);
            }
            if (response['companyCountry'] != ''){
                jQuery('#companyCountry').attr('value',response['companyCountry']);
            }

            // if (response['favStyle'] != ''){
            //    jQuery ('select#Style').append('<option selected value="'+response['favStyle']+'">'+response['favStyle']+'</option>');
            // }
            // if (response['favCategory'] != ''){
            //    jQuery ('select#EventiPreferiti').append('<option selected value="'+response['favCategory']+'">'+response['favCategory']+'</option>');
            // }
            // if (response['vehicles'] != ''){
            //     vehicle=response['vehicles'];
            //    jQuery ('select#ModelloCasa').append('<option selected value="'+vehicle[0].name+'">'+vehicle[0].name+'</option>');
            //    jQuery ('select#Modello').append('<option selected value="'+vehicle[0].manufacturer+'">'+vehicle[0].manufacturer+'</option>');
            //    var features = vehicle[0].features;
            //    console.log (features);
            //   features.forEach(function(element) {
            //         if (element == 'no_smoking'){
            //             jQuery('label#no_smoking').addClass('active');
            //         }else if (element == 'musica'){
            //             jQuery('label#musica').addClass('active');
            //         }
            //     });

            // }
            if (response['gender'] == 'male'){
                jQuery('option#male').attr('selected', 'selected');
            }else if (response['gender'] == 'female'){
                jQuery('option#female').attr('selected', 'selected');
            }else{
                jQuery('option#scegli').attr('selected', 'selected');
            }
            // if (response['groups'][0]['name'] == 'driver'){
            //     jQuery("#scelta-veicolo").removeClass('hidden');
            // }

            //storage_user(response);
            user_mem = response;

            if (userHasRole(user_mem, 'organizer')) {
                console.log('user is organizer');

                var toParam = getUrlParameter('to');
                var firstParam = getUrlParameter('f');
                if (toParam != '' && firstParam != '') {
                    console.log('To', toParam);
                    window.showAdvMessage('Fatto', 'Adesso sei un organizzatore, crea subito il tuo primo evento!', function () {
                        window.location.href = toParam;
                    });
                }
            } else {
                var firstParam = getUrlParameter('f');
                if (firstParam != '') {
                    window.showAdvMessage('Attenzione', 'Inserisci le informazioni sulla tua azienda per diventare organizzatore', function () {

                    });
                }
            }

            spinnerInit('stop');
        },
        error: function(response) {
          console.log('error'+response);
          window.call_finish_global = true;
          //call_finish = true;
          spinnerInit('stop');
        },
     });
    }

    jQuery( "#salva" ).click(function() {

        var valid = jQuery('#user-form').isValid();
        if (!valid) {
            new Noty({
                type: 'warning',
                text: 'Ci sono dei campi non validi'
            }).show();
            return;
        }

         var nome = jQuery( "input#nome" ).val();
         var cognome = jQuery( "input#cognome" ).val();
         var email = jQuery('input#email').val();
         var gender = jQuery("select#gender option:selected").val();
         var companyName = jQuery( "input#companyName" ).val();
         var companyVatId = jQuery( "input#companyVatId" ).val();
         var companyAddress = jQuery( "input#companyAddress" ).val();
         var companyZipcode = jQuery( "input#companyZipcode" ).val();
         var companyRef = jQuery( "input#companyRef" ).val();
         var companyCity = jQuery( "input#companyCity" ).val();
         var companyCountry = jQuery( "input#companyCountry" ).val();
        //  var eventi = jQuery ('select#EventiPreferiti').val();
        //  var style = jQuery ('select#Style').val();
        //  var casa_veicolo = jQuery( "select#ModelloCasa" ).val();
        //  var modello_veicolo = jQuery( "select#Modello" ).val();
        //  var vehicle_name = modello_veicolo;
        //  var vehicle_manufacturer = casa_veicolo;

         if (jQuery('label#no_smoking').hasClass('active')){
            var no_smoking = 'no_smoking';
         }else{
            var no_smoking = '';
         }

         if (jQuery('label#musica').hasClass('active')){
            var musica = 'musica';
         }else{
            var musica = '';
         }

         //avatar
         var avatar_img = jQuery('img#avatar').attr('src');
         //console.log('avatar_img --> ' , avatar_img );

        //  var vehicle_feature = [no_smoking,musica];
        //  var vehicles = [];
        //  vehicles.push({"name" : vehicle_name,"manufacturer" : casa_veicolo,"features" : vehicle_feature});

        var user_obj = user_mem;

        if (nome != ''){
            user_obj.firstname = nome;
        }
        if (cognome != ''){
            user_obj.lastname = cognome;
        }
        if (email != ''){
            user_obj.email = email;
        }
        if (gender) {
            user_obj.gender = gender;
        }
        // if (eventi != ''){
        //     user_obj.favCategory = eventi;
        // }
        if (companyName != ''){
            user_obj.companyName = companyName;
        }
        if (avatar_img != ''){

            if (current_image == avatar_img) {
                // se l' immagine è la stessa non attiviamo la funzione upload_image
                //console.log('stessa img ');
                array_avatar = {'uri':avatar_img ,'normalUri':avatar_img};
                //console.log('array_avatar --> ' , array_avatar);
                user_obj.images["0"]=array_avatar;
            } else {
                //console.log('immagine diversa');
                var uri_avatar = upload_image(avatar_img);
                //console.log('uri_avatar -> ',uri_avatar, ' avatar_img --> ', avatar_img);
                array_avatar = {'uri':uri_avatar ,'normalUri':uri_avatar};
                user_obj.images["0"]=array_avatar;
            }
        }
        if (companyVatId != ''){
            user_obj.companyVatId = companyVatId;
        }
        if (companyAddress != ''){
            user_obj.companyAddress = companyAddress;
        }
        if (companyZipcode != ''){
            user_obj.companyZipcode = companyZipcode;
        }
        if (companyRef != ''){
            user_obj.companyRef = companyRef;
        }
        if (companyCity != ''){
            user_obj.companyCity = companyCity;
        }
        if (companyCountry != ''){
            user_obj.companyCountry = companyCountry;
        }
        // if (style != ''){
        //     user_obj.favStyle = style;
        // }
        // console.log(vehicles);
        // if (vehicles.length > 0){
        //     user_obj.vehicles[0] = vehicles[0] ;
        //     //user_obj.vehicles.push(vehicles[0]) ;
        // }
        console.log( 'user obj -> ' ,  user_obj);

        call_to_update(user_obj);


     });


        var  Fiat =['Panda','Punto','Qubo','Doblo','500X','500L Wagon'];
        var  Renault = ['modello 1'];
        var Alfa_Romeo = ['modello 1'];
        var BMW = ['modello 1'];
        var Audi = ['modello 1'];
        var Toyota = ['modello 1'];

      //select modello
      jQuery('select#ModelloCasa').change(function() {
            var sceltaveicolo =jQuery( "select#ModelloCasa" ).val();
            console.log(sceltaveicolo);
            jQuery( "select#Modello" ).html('');
            if ( sceltaveicolo == 'Fiat'){

                Fiat.forEach(function(element) {
                    jQuery( "select#Modello" ).append('<option >'+element+'</option>');
                });
            }else if(sceltaveicolo == 'Renault'){
                Renault.forEach(function(element) {
                    jQuery( "select#Modello" ).append('<option >'+element+'</option>');
                });
            }else if(sceltaveicolo == 'Alfa Romeo'){
                Alfa_Romeo.forEach(function(element) {
                    jQuery( "select#Modello" ).append('<option >'+element+'</option>');
                });
            }else if(sceltaveicolo == 'BMW'){
                BMW.forEach(function(element) {
                    jQuery( "select#Modello" ).append('<option >'+element+'</option>');
                });
            }else if(sceltaveicolo == 'Audi'){
                Audi.forEach(function(element) {
                    jQuery( "select#Modello" ).append('<option >'+element+'</option>');
                });
            }else if(sceltaveicolo == 'Toyota'){
                Toyota.forEach(function(element) {
                    jQuery( "select#Modello" ).append('<option >'+element+'</option>');
                });
            }
        });

        //CROP

    window.addEventListener('DOMContentLoaded', function () {
        var avatar = document.getElementById('avatar');
        var image = document.getElementById('image');
        var input = document.getElementById('input');
        var $progress = jQuery('.progress');
        var $progressBar = jQuery('.progress-bar');
        var $alert = jQuery('.alert');
        var $modal = jQuery('#modal');
        var cropper;
        //jQuery('[data-toggle="tooltip"]').tooltip();
        input.addEventListener('change', function (e) {
        var files = e.target.files;
        var done = function (url) {
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
            reader.onload = function (e) {
                done(reader.result);
            };
            reader.readAsDataURL(file);
            }
        }
    });
    $modal.on('shown.bs.modal', function () {
      cropper = new Cropper(image, {
        aspectRatio: 1,
        viewMode: 3,
      });
    }).on('hidden.bs.modal', function () {
      cropper.destroy();
      cropper = null;
    });
    document.getElementById('crop').addEventListener('click', function () {
      var initialAvatarURL;
      var canvas;
      $modal.modal('hide');
      if (cropper) {
        canvas = cropper.getCroppedCanvas({
          width: 160,
          height: 160,
        });
        avatar = document.getElementById('avatar');
        initialAvatarURL = avatar.src;
        avatar.src = canvas.toDataURL();
        $progress.show();
        $alert.removeClass('alert-success alert-warning');
        canvas.toBlob(function (blob) {
          var formData = new FormData();
          formData.append('avatar', blob, 'avatar.jpg');
          $progress.hide();
        //   jQuery.ajax('https://jsonplaceholder.typicode.com/posts', {
        //     method: 'POST',
        //     data: formData,
        //     processData: false,
        //     contentType: false,
        //     xhr: function () {
        //       var xhr = new XMLHttpRequest();
        //       xhr.upload.onprogress = function (e) {
        //         var percent = '0';
        //         var percentage = '0%';
        //         if (e.lengthComputable) {
        //           percent = Math.round((e.loaded / e.total) * 100);
        //           percentage = percent + '%';
        //           $progressBar.width(percentage).attr('aria-valuenow', percent).text(percentage);
        //         }
        //       };
        //       return xhr;
        //     },
        //     success: function () {
        //       $alert.show().addClass('alert-success').text('Upload success');
        //     },
        //     error: function () {
        //       avatar.src = initialAvatarURL;
        //       $alert.show().addClass('alert-warning').text('Upload error');
        //     },
        //     complete: function () {
        //       $progress.hide();
        //     },
        //   });
        });
      }
    });

    /***** autocoplete address *****/
    var address = document.getElementById('companyAddress');
    var autocomplete = new google.maps.places.Autocomplete(address, {
      // console.log("hereee");
        types: ['geocode', 'establishment']
    });

    //autocomplete.setComponentRestrictions({'country': ['it']});
    autocomplete.setFields(['address_component', 'geometry']);
    autocomplete.addListener('place_changed', fillInAddress);

    function fillInAddress() {
      jQuery("#companyZipcode").val("");
      jQuery("#companyCountry").val("");
      jQuery("#companyCity").val("");

      var place = autocomplete.getPlace();
      let num_values = place.address_components.length;

      console.log('Place', place);

      if (num_values > 0) {
        jQuery(place.address_components).each(function(){
          let current_type = this.types[0];
          let current_value = this.long_name;
          //---------- add cap ---------//
          if (current_type == "postal_code" || current_type == "postal_code_prefix") {
            console.log("current postalCode --> " , current_value);
            jQuery("#companyZipcode").val(current_value);
          }
          //---------- end add cap ---------//
          //--------- add country ---------//
          if (current_type == "country") {
            console.log("current country --> " , current_value);
            jQuery("#companyCountry").val(current_value);
          }
          //--------- end add country ---------//
          //--------- add city ---------//
          if (current_type == "locality" || current_type == "postal_town" ) {
            console.log("current_type --> " , current_value);
            jQuery("#companyCity").val(current_value);
          }
          //--------- end add city ---------//
        });
      }
    }

    /***** end autocoplete address *****/
  });

</script>
