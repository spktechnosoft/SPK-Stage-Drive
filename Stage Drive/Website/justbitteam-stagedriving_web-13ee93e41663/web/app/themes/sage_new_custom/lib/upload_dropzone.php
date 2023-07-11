<?php
/*
*  IMPORT IMAGE IN DIRECTORY AND ADD IN REPEATER
*/
// BISOGNA VEDERE PERCHE NON PRENDE LE FUNZIONI DI WORDPRESS
// riabilititare template nel function //
ERROR_LOG('INSIDE THE UPLOAD DROPZONE'. print_r($_FILES, true) );
//array test directory
$upload_dir = array(
  'path' => '/usr/share/nginx/www/web/app/uploads/2018/05',
  'url'  => 'http://mondoconv.localhost:8989/app/uploads/2018/05'
);
//$test_function = get_the_title(6207);
//error_log('check function ', $test_function);
if (!empty($_FILES)) {
    $data = base64_encode(file_get_contents( $_FILES["file"]["tmp_name"] ));
    //ERROR_LOG('INSIDE THE UPLOAD TRY TO CONVER IN BASE 64'. print_r($data, true) );

    $base64_img = $data;
    $title = 'pic_mondoconv_dropzone';

    //$upload_dir  = wp_upload_dir();
    ERROR_LOG('INSIDE THE UPLOAD TRY TO CONVER IN BASE 64'. print_r($upload_dir, true) );
    $upload_path = str_replace( '/', DIRECTORY_SEPARATOR, $upload_dir['path'] ) . DIRECTORY_SEPARATOR;

    $img             = str_replace( 'data:image/jpeg;base64,', '', $base64_img );
    $img             = str_replace( ' ', '+', $img );
    $decoded         = base64_decode( $img );
    $filename        = $title . '.jpeg';
    $file_type       = 'image/jpeg';
    $hashed_filename = md5( $filename . microtime() ) . '_' . $filename;

    // Salva l img e la memorizza nella directory //
    $upload_file = file_put_contents( $upload_path . $hashed_filename, $decoded );

    $attachment = array(
      'post_mime_type' => $file_type,
      'post_title'     => preg_replace( '/\.[^.]+$/', '', basename( $hashed_filename ) ),
      'post_content'   => '',
      'post_status'    => 'inherit',
      'guid'           => $upload_dir['url'] . '/' . basename( $hashed_filename )
    );

    $path_file = $upload_dir['path'] . '/' . $hashed_filename;
    $attach_id = wp_insert_attachment( $attachment, $path_file );
    require_once( ABSPATH . 'wp-admin/includes/image.php' );
    $attach_data = wp_generate_attachment_metadata( $attach_id, $path_file);
    wp_update_attachment_metadata( $attach_id,  $attach_data );
}

?>
