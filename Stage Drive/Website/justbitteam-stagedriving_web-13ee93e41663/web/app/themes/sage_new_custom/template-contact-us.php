<?php
/**
 * Template Name: Template Contact-us
 */
//$ContactForm = get_field('shortcode');
global $wp_query;
$post_id = get_the_ID();
$content_post = get_post($post_id);
$content = $content_post->post_content;
$content = apply_filters('the_content', $content);
$content = str_replace(']]>', ']]&gt;', $content);
//$ContactForm = '[contact-form-7 id="206" title="Contact-us"]';
$ContactForm = get_field('shortcode_form');
 ?>

 <div class="container cont-forum max-width">
   <div class="row">
     <div class="col-sm-12">
       <h1 class="title-form">
         <?php echo $content_post->post_title; ?>
       </h1>

       <?php if ($content != '') { ?>
         <div class="description-form">
           <?php echo $content; ?>
         </div>
       <?php } ?>
       <?php
       if ($ContactForm != '') {
           echo do_shortcode($ContactForm);
       } ?>
     </div>
   </div>
 </div>


<script type="text/javascript">
  /** gestione form **/
  jQuery( document ).ready(function() {
    jQuery('.wpcf7-submit').addClass('button-beta');
    jQuery('input[type=submit]').parent().closest('p').css({'text-align':'center'});
  });
</script>
