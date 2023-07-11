<?php
/**
 * Template Name: Template Login
 */

//------- creare field ---------//
//$link_page_sign_in = 'http://sd.localhost:8989/sign-in/';
$link_page_sign_in = get_field('page_signin');
//$recover_password = '/recovery-password';
$recover_password = get_field('page_recovery_pass');
//$registration_page = '/registration_page';
$registration_page = get_field('link_signup');
//$event_page = '/events';
$event_page  = get_field('link_event');
if ($event_page == '') {
  $event_page = get_home_url();
}

$cookie_token = '';
if (isset($_COOKIE['accessToken'])) {
  $cookie_token = $_COOKIE['accessToken'];
}

$redirect_to = '';
if (isset($_GET['redirect_to'])) {
  $redirect_to = $_GET['redirect_to'];
}
$token_get = '';
if (isset($_GET['t'])) {
  $token_get = $_GET['t'];
}
?>

<script>
function getUrlParameter(name) {
  name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
  var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
  var results = regex.exec(location.search);
  return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};
</script>

<div class="container">
    <div class="row">
      <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
        <div class="box-login">

          <div class="wrap-input">
            <div class="form-group">
              <label class="" for="usr">Email:</label>
              <input type="text" class=" form-control" id="usr">
            </div>
          </div>
          <div class="wrap-input">
            <div class="form-group">
              <label class="" for="pwd">Password:</label>
              <input type="password" class=" form-control" id="pwd">
            </div>
          </div>
          <!-- nota -->
           <div class="wrap-note">
             <p>Inserisci le credenziali configurate sull'app</p>
           </div>
          <!-- END nota -->
          <div class="wrap-link">
            <div class="row">
              <div class="col-xs-6">
                <div class="custom-hook-modal" data-target="#custom-popup" data-toggle="modal">
                  <p class="">Non sono <br class="br-custom">registrato</p>
                </div>
              </div>
              <div class="col-xs-6">
                <a href="<?php echo $recover_password; ?>">
                  <p class="">Password <br class="br-custom">dimenticata?</p>
                </a>
              </div>
            </div>
          </div>
          <div class="wrap-button" style="text-align:center;">
            <button type="button" class="btn btn-danger button-beta" id="login-button">
              ACCEDI
            </button>
          </div>
          <div class="message-error" style="text-align: center;">
            <p class="text-error" style="color:#ef726d;"></p>
          </div>
          <?php
          // echo $cookie_token;
          // echo 'val get --> ' . $redirect_to;
           ?>
        </div>
      </div>
    </div>
</div>

<!-- Modal -->
<div id="custom-popup" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title text-center" style="color:#fa8448;">Scarica l'app</h4>
      </div>
      <div class="modal-body text-center">
        <p>Inserisci le credenziali create con l'app</p>
        <?php if (preg_match("/iPhone|iPod|iPad/", $_SERVER['HTTP_USER_AGENT'])) { ?>
          <div class="button-download">
            <a href="https://itunes.apple.com/us/app/stagedriving/id1467871344?mt=8">
              <!-- <img src="https://www.stagedriving.com/app/themes/sage_new_custom/dist/images/app_store_btn.png" alt="App Store"> -->
              <button type="button" class="btn btn-danger button-beta">OK</button>
            </a>
          </div>

        <?php } elseif (preg_match("/Android/", $_SERVER["HTTP_USER_AGENT"])) { ?>
          <div class="button-download">
            <a href="https://play.google.com/store/apps/details?id=com.stagedriving">
              <!-- <img src="https://www.stagedriving.com/app/themes/sage_new_custom/dist/images/google_play_btn.png" alt="Google Play"> -->
              <button type="button" class="btn btn-danger button-beta">OK</button>
            </a>
          </div>
        <?php } else { ?>
          <div class="button-download">
            <div class="double">
              <a href="https://play.google.com/store/apps/details?id=com.stagedriving">
                <img src="https://www.stagedriving.com/app/themes/sage_new_custom/dist/images/google_play_btn.png" alt="Google Play">
              </a>
            </div>
            <div class="double">
              <a href="https://itunes.apple.com/us/app/stagedriving/id1467871344?mt=8">
                <img src="https://www.stagedriving.com/app/themes/sage_new_custom/dist/images/app_store_btn.png" alt="App Store">
              </a>
            </div>
          </div>
        <?php } ?>
      </div>
    </div>
  </div>
</div>

<style media="screen">
  body {
    visibility: hidden;
  }
</style>

<script type="text/javascript">

  jQuery( document ).ready(function() {
    var link_page_sign_in = '<?php echo $link_page_sign_in ?>';
    var current_token = '<?php echo $cookie_token; ?>';
    var redirect_to = '<?php echo $redirect_to; ?>';
    var token_get = '<?php echo $token_get; ?>';
    var link_home = '<?php echo get_home_url(); ?>';
    // se abbiamo il valore in get del redirect_to attiviamo il redirect
    if (redirect_to != '') {
      window.location = redirect_to;
    } else {
      // se abbiamo il token dell utente in get controlliamo se esite //
      if (token_get != '') {
        // controllo se esiste l utente e creo il cookie
        var url = SD_API_ACCOUNTS+'/me';

        jQuery.ajax({
          type: "GET",
          dataType: "json",
          url: url,
          success: function(response,status, xhr){
              console.log(' current user --> ' , response);
              //console.log(' id user response -->' , response['id']);
              if (response['id'] != '') {
                // creo il cookie
                document.cookie = "accessToken="+token_get+"; expires=null; path=/";
                current_token = token_get;
                window.location = link_page_sign_in;
              }
          },
          error: function(response) {
            window.location = link_home;
          },
        });
      }
      // se abbiamo il token mostro la pagina di sign-in
      if (current_token != '') {
        // console.log('row redirect sign-in ');
        window.location = link_page_sign_in;
      } else {
        // altrimenti mostro la login
        // console.log('row show body');
        jQuery('body').css({'visibility':'unset'});
      }
    }

  });



  function go_to_eventi() {
    var toParam = getUrlParameter('to');
    if (toParam != null && toParam !== '') {
      console.log('To', toParam);
      window.location.href = toParam;
    } else {
      window.location = '<?php echo $event_page; ?>';
    }
  }
  // click button login //
  jQuery('#login-button').click(function(){
    console.log('inside click');
    jQuery('.text-error').text('');

    var url = SD_API_AUTH;
    var user = jQuery('#usr').val();
    var pass = jQuery('#pwd').val();
    if (user != '' && pass != '') {
      console.log('valori popolati ');
      console.log('val user --> ' , user );
      console.log(' val pass --> ' , pass );
      var detail = {
        identifier : user,
        password : pass,
      }
      // test send
      jQuery.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(detail),

        success: function (data) {
          console.log(' all data --> ', data);
          console.log(' token --> ', data.accessToken);
          // create cookie with value of token
          var token = data.accessToken;

          document.cookie = "accessToken="+token+"; expires=null; path=/";
          jQuery('.box-login').empty();
          jQuery('.box-login').html('<h1 style="text-align:center;"> Benvenuto in Stage Driving <h1>');
          setTimeout(go_to_eventi, 2000);
        },

        error: function (data) {
          //console.log(' error ' , data );
          //jQuery('.text-error').text('Credenziali sbagliate');

          new Noty({
            type: 'error',
            text: 'Credenziali non valide',
          }).show();
        },
      });
    } // control value input
  })
</script>
