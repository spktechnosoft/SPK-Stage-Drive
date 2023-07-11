<?php
/*
 * Template Name: Template Sign-in
 */

 // custom field //
 //$link_page_login = 'http://sd.localhost:8989/login';
 $link_page_login = get_field('page_login');

 if ($link_page_login == '') {
   $link_page_login = get_home_url();
 }
?>
<div class="container">
  <div class="row">
    <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
      <!----- detail user ----->
      <div class="detail-user max-width">
        <div class="box-detail-user">
          <!---- loader ---->
          <div class="wrap-loader">
            <div class="loader"></div>
          </div>
          <!-- end loader -->
        </div>
        <!--- test form grup --->
      </div>
      <!--- end detail user --->
    </div>
  </div>
</div>

<script type="text/javascript">
    jQuery( document ).ready(function() {
      var current_token = getCookie('accessToken');
      var link_login = '<?php echo $link_page_login; ?>';

      console.log('current token --> ', current_token );
      if (current_token == '') {
        // fare redirect nella login //
        console.log('redirect logins');
        window.location = link_login;

      } else {

        jQuery('.wrap-loader').css({'display':'block'});

        var url = SD_API_ACCOUNTS+'/me';
        jQuery.ajax({
          type: "GET",
          dataType: "json",
          url: url,
          beforeSend: function (xhr) {
              xhr.setRequestHeader('Authorization', 'Bearer '+current_token);
          },
          success: function(response,status, xhr){
              console.log('value response --> ' , response);
              // hidden  loader //
              jQuery('.wrap-loader').css({'display':'none'});
              // append all detail //
              var user_image = response['images'][0]['normalUri'];
              if (user_image != '') {
                jQuery('.box-detail-user').append('<div class="wrap-img-user"><img class="user-img" src="'+user_image+'" alt="User"</div>');
              }
              // creare immagine //
              // dettagli da analizzare //
              var user_fav_category = response['favCategory'];
              var user_fav_style = response['favStyle'];
              // end dettagli //
              var user_mail = response['email'];
              if (user_mail != '' && user_mail != undefined ) {
                jQuery('.box-detail-user').append('<div class="form-group form-group-custom"><label>Email</label><p class="detail-form">'+user_mail+'</p></div>');
              }
              var user_first_name = response['firstname'];
              if (user_first_name != '' && user_first_name != undefined ) {
                jQuery('.box-detail-user').append('<div class="form-group form-group-custom"><label>Nome</label><p class="detail-form">'+user_first_name+'</p></div>');
              }
              var user_last_name = response['lastname'];
              if (user_last_name != '' && user_last_name != undefined ) {
                jQuery('.box-detail-user').append('<div class="form-group form-group-custom"><label>Cognome</label><p class="detail-form">'+user_last_name+'</p></div>');
              }
              var user_gender = response['gender'];
              if (user_gender != '' && user_gender != undefined ) {
                jQuery('.box-detail-user').append('<div class="form-group form-group-custom"><label>Sesso</label><p class="detail-form">'+user_gender+'</p></div>');
              }
              var user_phone = response['telephone'];
              if (user_phone != '' && user_phone != undefined ) {
                jQuery('.box-detail-user').append('<div class="form-group form-group-custom"><label>Telefono</label><p class="detail-form">'+user_phone+'</p></div>');
              }


              var user_company_name = response['companyName'];
              if (user_company_name != '' && user_company_name != undefined ) {
                jQuery('.box-detail-user').append('<div class="form-group form-group-custom"><label>Nome azienda</label><p class="detail-form">'+user_company_name+'</p></div>');
              }

              var user_company_andress = response['companyAddress'];
              var user_company_city = response['companyCity'];
              var user_company_country = response['companyCountry'];
              var user_company_zipcode = response['companyZipcode'];

              if (user_company_andress != '' && user_company_andress != undefined || user_company_city != '' && user_company_city != undefined || user_company_country != '' && user_company_country != undefined || user_company_zipcode != '' && user_company_zipcode != undefined ) {
                var detail_company = '';
                if (user_company_andress != '') {
                  detail_company = user_company_andress;
                }
                if (user_company_city != '') {
                  detail_company = detail_company + ', ' +user_company_city;
                }
                if (user_company_country != '') {
                  detail_company = detail_company + ', ' +user_company_country;
                }
                if (user_company_zipcode != '') {
                  detail_company = detail_company + ', ' +user_company_zipcode;
                }

                jQuery('.box-detail-user').append('<div class="form-group form-group-custom"><label>Indirizzo azienda</label><p class="detail-form">'+detail_company+'</p></div>');
              }
              var user_vehicles = response['vehicles'];
              if (user_vehicles != '' && user_company_andress != undefined) {
                jQuery(user_vehicles).each(function (index, value) {
                  var name_car = this.name;
                  var manufacturer_car = this.manufacturer;
                  var detail_car = ''
                  if (name_car != '') {
                    detail_car = name_car;
                  }
                  if (manufacturer_car != '') {
                    detail_car = detail_car + ', '+ manufacturer_car;
                  }
                  jQuery('.box-detail-user').append('<div class="form-group form-group-custom"><label>Modello veicolo</label><p class="detail-form">'+detail_car+'</p></div>');
                });
              }
              // ---------- END --------- //

              //------ crea evento --------//
              // var user_groups = response['groups'];
              // // controlliamo se è un organizzatore //
              // var create_event = 'deactivate';
              // if (user_groups != '') {
              //   // console.log('user_groups ---> ' , user_groups);
              //   jQuery(user_groups).each(function (index, value) {
              //     var name_user = this.name;
              //     var description_user = this.description;
              //     if (name_user == "organizer" || description_user == "organizer" ) {
              //       create_event = 'active';
              //     }
              //   });
              // }
              //------ end crea evento --------//
          },
          error: function (data) {
            console.log(' error token' );
            // se ci sono errori faccio il redirect
            window.location = link_login;
          },

        });
      }

    });
</script>
