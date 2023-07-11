<?php

// Bundle
$bundle = get_transient('bundle');
if ($bundle === false || isset($_GET['clearBundle'])) {

  error_log('Clearing bundle cache');
  // Read JSON file
  $bundle = file_get_contents('https://s3.amazonaws.com/stagedriving.events/bundle.json');
  set_transient('bundle', $bundle);
} else {
  error_log('Found bundle in cache');
}

$reload = 'false';

if (isset($_COOKIE['accessToken'])){
  $token = $_COOKIE['accessToken'];
} else {
  $token = '';
}

if (isset($_GET['t'])){
  $token = $_GET['t'];
  $reload = 'true';
}

$SD_TOKEN = $token;
$GLOBALS['SD_TOKEN'] = $SD_TOKEN;

?>

<script>
var SD_BASE_URI = '<?php echo $GLOBALS['SD_BASE_URI'] ?>';
var SD_API_LIMIT = <?php echo $GLOBALS['SD_API_LIMIT'] ?>;
var SD_API_VERSION = '<?php echo $GLOBALS['SD_API_VERSION'] ?>';
var SD_API_AUTH = '<?php echo $GLOBALS['SD_API_AUTH'] ?>';
var SD_API_EVENTS = '<?php echo $GLOBALS['SD_API_EVENTS'] ?>';
var SD_API_RIDES = '<?php echo $GLOBALS['SD_API_RIDES'] ?>';
var SD_API_ACCOUNTS = '<?php echo $GLOBALS['SD_API_ACCOUNTS'] ?>';
var SD_API_OBJECTS = '<?php echo $GLOBALS['SD_API_OBJECTS'] ?>';

var SD_TOKEN = '<?php echo $SD_TOKEN ?>';

var SD_BUNDLE = <?php echo $bundle ?>;

var forceReload = <?php echo $reload ?>;


// jQuery( document ).ready(function() {
  console.log('Setup jquery ajax');

  if (forceReload) {
    console.log('Force reloading');
    document.cookie = "accessToken="+SD_TOKEN+"; expires=null; path=/";
    setTimeout(() => {
      console.log('Location to '+window.location.href.split('?')[0]);
      window.location.href = window.location.href.split('?')[0];
    }, 800);
  }

  if (SD_TOKEN !== '') {
    console.log('Token exists');
    jQuery.ajaxSetup({
      headers : {
      'Authorization' : 'Bearer ' + SD_TOKEN
      },
      error:function(dataUser) {
        var getMessage = new JsonMessage(dataUser);
        getMessage.responseMessage();
      }
    });

    jQuery.ajax({
      type: "GET",
      dataType: "json",
      url: SD_API_ACCOUNTS+'/me',
      success: function(response, status, xhr) {
        localStorage.setItem("user", JSON.stringify(response));
      }
    });
  }
// });

</script>
