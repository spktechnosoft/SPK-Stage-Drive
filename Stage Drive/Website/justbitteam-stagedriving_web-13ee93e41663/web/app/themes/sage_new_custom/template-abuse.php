<?php
/**
 * Template Name: Template Abuse
 */

get_template_part('/lib/svg-image.php', null );

global $wp_query;

$type = $_GET['type'];
$commentId = $_GET['id'];
$accessToken = $_GET['at'];
$ContactForm = get_field('shortcode');
?>

<div class="container cont-forum max-width">
  <div class="row">
    <div class="col-sm-12">
      <?php
      if ($ContactForm != '') {
          echo do_shortcode($ContactForm);
      } ?>
    </div>
  </div>
</div>


<script type="text/javascript">
  // ----------- GESTIONE FORM ----------- //
  jQuery( document ).ready(function() {

    var type = '<?php echo $type; ?>';
    var commentId = '<?php echo $commentId; ?>';
    var accessToken = '<?php echo $accessToken; ?>';
    /** se sono presenti inserisco i valori in GET **/
    if(type != '') {
      jQuery('input.subject-form').val(type);
    }
    if(commentId != '') {
      jQuery('input.comment-id').val(commentId);
    }
    if(accessToken != '') {
      jQuery('.accessToken input').val(accessToken);
    }
    jQuery('.wpcf7-submit').addClass('button-beta');
    jQuery('input[type=submit]').parent().closest('p').css({'text-align':'center'});
  });

  // -------------- fix contact-form ---------------- //
  jQuery(function($) {
    wpcf7Elm.addEventListener( 'wpcf7mailsent', function( event ) {
      $('.submit-success').fadeIn();
       setTimeout( function() {
           $('.submit-success').fadeOut();
       },5000);
   }, false );
  });
 // ------------- end fix contact-form -------------- //
</script>
