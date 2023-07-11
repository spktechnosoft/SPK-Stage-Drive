<?php

use Roots\Sage\Setup;
use Roots\Sage\Wrapper;

?>

<!doctype html>
<html <?php language_attributes(); ?>>
	<?php get_template_part('templates/head'); ?>
	<body <?php body_class(); ?>>


		<!-- Google Tag Manager (noscript) -->
		<!--
		<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=<?php //echo get_field("google_tag_manager_id", "option"); ?>"
		height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
		-->
		<!-- End Google Tag Manager (noscript) -->

		<?php
			do_action('get_header');
			get_template_part('templates/header');
		?>

		<div id="loading" class=""></div>
		<?php include Wrapper\template_path(); ?>

		<?php
			do_action('get_footer');
			get_template_part('templates/footer');
			wp_footer();

		?>
	</body>
</html>
