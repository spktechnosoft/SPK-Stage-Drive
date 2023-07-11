<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">

	<meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no" />
	<link rel="apple-touch-icon" sizes="180x180" href="/app/themes/sage_new_custom/dist/images/favicon/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="/app/themes/sage_new_custom/dist/images/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="/app/themes/sage_new_custom/dist/images/favicon/favicon-16x16.png">
	<link rel="manifest" href="/app/themes/sage_new_custom/dist/images/favicon/site.webmanifest">
	<link rel="mask-icon" href="/app/themes/sage_new_custom/dist/images/favicon/safari-pinned-tab.svg" color="#fa8448">
	<meta name="msapplication-TileColor" content="#da532c">
	<meta name="theme-color" content="#ffffff">

	<link  href="<?php echo get_template_directory_uri() ?>/bower_components/cropperjs/docs/css/cropper.css" rel="stylesheet">
	<link  href="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/1.3.3/cropper.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.3/css/swiper.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.3/css/swiper.min.css">
	<!--- DATE PICKER CSS --->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
	<!-- <meta name="msapplication-config" content="/app/themes/sage_new_custom/dist/images/favicon/browserconfig.xml">
	<meta name="theme-color" content="#ffffff"> -->
	<link  href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet">
	<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
	<!-- <script src="https://maps.google.com/maps/api/js?key=AIzaSyDDrXuSd5JRW7iQ4gZjD1zN-pnnMjt6ZRs&libraries=places" type="text/javascript"></script> -->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBk3v1BxHAxzVkoASXKurNZH-ZBAG_GD2Y&libraries=geometry,places"></script>
	<link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates:300,300i,400,400i,500,500i,700,700i&display=swap" rel="stylesheet">
	<?php wp_head(); ?>

	<?php
	$filepath = locate_template("lib/constants.php");
	include $filepath;
	?>
</head>
