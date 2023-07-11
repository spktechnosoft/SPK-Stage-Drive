<?php /* Template: Login 2 */ ?>

<?php

	//echo '<pre>';
	//print_r( $this);
	//echo '</pre>';
function upload_img_ultimate_member($atts, $content = null)
{
		extract( shortcode_atts(
		array(
		'post_id' => 1,
		), $atts ) );
		ob_start();
 ?>

<?php

		$contents = ob_get_contents();
	  ob_end_clean();
	  return $contents;

}

add_shortcode('upload_img', 'upload_img_ultimate_member');
 ?>

  <div class="um <?php echo $this->get_class( $mode ); ?> um-<?php echo $form_id; ?> um-role-<?php echo um_user('role'); ?> ">

  	<div class="um-form">

  		<?php do_action('um_profile_before_header', $args ); ?>

  		<?php if ( um_is_on_edit_profile() ) { ?><form method="post" action=""><?php } ?>

  			<?php do_action('um_profile_header_cover_area', $args ); ?>

  			<?php do_action('um_profile_header', $args ); ?>

  			<?php do_action('um_profile_navbar', $args ); ?>

  			<?php

  			$nav = $ultimatemember->profile->active_tab;
  			$subnav = ( get_query_var('subnav') ) ? get_query_var('subnav') : 'default';

  			print "<div class='um-profile-body $nav $nav-$subnav'>";

  				// Custom hook to display tabbed content
  				do_action("um_profile_content_{$nav}", $args);
  				do_action("um_profile_content_{$nav}_{$subnav}", $args);

  			print "</div>";

  			?>

  		<?php if ( um_is_on_edit_profile() ) { ?></form><?php } ?>

  	</div>

  </div>
