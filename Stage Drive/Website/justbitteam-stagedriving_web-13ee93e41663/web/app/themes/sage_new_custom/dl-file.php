<?php
/*
 * dl-file.php
 *
 * Protect uploaded files with login.
 *
 * @link http://wordpress.stackexchange.com/questions/37144/protect-wordpress-uploads-if-user-is-not-logged-in
 *
 * @author hakre <http://hakre.wordpress.com/>
 * @license GPL-3.0+
 * @registry SPDX
*/
require( dirname(__FILE__) . '/wp/wp-load.php' );
require( dirname( __FILE__ ) . '/wp/wp-blog-header.php' );


if (!is_user_logged_in()) {
  auth_redirect();
  return;
}

$post_id = $_GET[ 'id' ];
if (empty($post_id)) {
  error_log('Error');
  die();
}

// get post by id $post_id
$post = get_post($post_id);
if (!isset($post_id)) {
  error_log('Error2');
  die();
}
error_log('post id '.$post->ID);
// check for permission on meta value

$media_id = get_field('file_card', $post_id);
error_log('media id '.$media_id);
// if true
header('X-Accel-Redirect: '.$media_id);
return;

// if false
//wp_redirect('/forbidden');
?>
