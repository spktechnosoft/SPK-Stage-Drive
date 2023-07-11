<?php
/**
 * Template Name: Template Ride
 */

 get_template_part('/lib/svg-image.php', null );

 global $wp_query;
 if (isset($wp_query->query_vars['id_ride'])){
  $id_ride =$wp_query->query_vars['id_ride'];
}else{
  $id_ride = '';
}


$current_string = $GLOBALS['SD_API_RIDES'].'/'.urlencode($id_ride).'';
$current_url = htmlentities($current_string);

if (isset($_COOKIE['accessToken'])){
  $token = $_COOKIE['accessToken'];
}else{
  $token = '';
}

echo $icon_images;
?>
<div id="page-user">

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
  
  <div class="wrap-detail-event">
    <div class="result-detail-event">

      <div class="download text-center">
        <h1>Scarica subito l'app per partecipare al passaggio</h1>
        <a href="https://play.google.com/store/apps/details?id=com.stagedriving" target="_blank"><img src="<?php echo get_template_directory_uri()?>/dist/images/appstore/google-play-badge.png"></a>
        <a href="https://itunes.apple.com/us/app/stagedriving/id1467871344?mt=8" target="_blank"><img src="<?php echo get_template_directory_uri()?>/dist/images/appstore/app-store-badge.png"></a>
      </div>

    </div>
  </div>

  <div class="row">
    <div class="col-sm-12 text-center">
      <!-- <a class="btn btn-danger button-beta" href="#">APRI NELL'APP</a> -->
    </div>
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
    url: url,
      success: function(response, status, xhr) {
        
        console.log('Ride', response);
      
        if (response.id) {
            //jQuery('.result-detail-event').append('<h1 style="margin-top:100px;text-align:center;"> '+response.id+' <h1>');
            jQuery('.result-detail-event .download').show();
        }
         
        spinnerInit('stop');
      }, // end success
      error: function(xhr) {
        console.log('error');
        jQuery('.result-detail-event').append('<h1 style="margin-top:100px;text-align:center;"> Passaggio non trovato <h1>');
        jQuery('.imgs-carousel').css('display', 'none');
          //spinjs
          spinnerInit('stop');
      },
  });
});

</script>

<?php ?>
