<?php
/**
 * Template Name: Template eventi Utente
 */

// include image //
get_template_part('/lib/svg-image.php', null );

if (isset($_COOKIE['accessToken'])){
  $token = $_COOKIE['accessToken'];
} else{
  $token = '';
}

if ($token != '') {

?>

<style>
a.button-beta:visited {
    color: white !important;
}
</style>


<div class="max-width" style="margin-top:40px;">
   <div class="row" style="margin-left:0;margin-right:0;">
     <div class="col-sm-12 text-center">
       <a class="btn btn-danger button-beta button-beta-little" href="/create-event">CREA NUOVO EVENTO</a>
     </div>
   </div>

  <!-- content cards events -->
  <div class="wrap-all-event">
    <div class="all-cards">
    </div>
    <div class="post-infinite-scroll">
       <!-- qui inseriamo i post dell infinite scroll -->
    </div>
    <div class="hook-height" style="height:40px; display:block; transform: translateY(-98vh);">
      <!-- hook scroll -->
    </div>
  </div>
    <!-- end content crd events -->

    <div id="empty-msg" class="row" style="display: none">
      <div class="col-sm-12 text-center">
        <p>Non hai ancora creato nessun evento, <a style="text-decoration: underline;" href="/create-event">fallo subito!</a></p>
      </div>
    </div>
</div>

<script type="text/javascript">
  var token = '<?php echo isset($_COOKIE['accessToken']) ? $_COOKIE['accessToken'] : "" ?>';
  var today = '';

  var filter = '';
  var user_id = '';
  var start = '';
  var end = '';


  var page_val = 0;
  var limit_val = SD_API_LIMIT;
  var page_val_scroll = 0;
  var limit_val_scroll = SD_API_LIMIT;



  jQuery( document ).ready(function() {

    // resize card //
    var current_width = jQuery(window).width();
    setTimeout(function(){
      console.log("interval");
      resizeCard(current_width);
    }, 1000);
    // resize mobile //
    jQuery(window).resize(function() {
      var current_width = jQuery(window).width();
      resizeCard(current_width);
    });

    filter = 'created';
    page_call_event('me', function (data) {
      console.log('Data recv', data);

      if (page_val === 0) {
        if (data.length === 0) {
          jQuery('#empty-msg').show();
        } else {
          jQuery('#empty-msg').hide();
        }
      }
    });

  });

</script>

<style>
    /* ---- hidden footer ---- */
    .footer-container {
      display: none;
    }
    /*--- end hidden footer --- */
</style>

<?php } else { ?>

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

<?php } ?>
