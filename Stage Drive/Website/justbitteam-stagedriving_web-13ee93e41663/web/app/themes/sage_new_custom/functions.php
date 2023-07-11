<?php
/**
 * Sage includes
 *
 * The $sage_includes array determines the code library included in your theme.
 * Add or remove files to the array as needed. Supports child theme overrides.
 *
 * Please note that missing files will produce a fatal error.
 *
 * @link https://github.com/roots/sage/pull/1042
 */
$sage_includes = [
    'lib/assets.php',    // Scripts and stylesheets
    'lib/extras.php',    // Custom functions
    'lib/setup.php',     // Theme setup
    'lib/titles.php',    // Page titles
    'lib/wrapper.php',   // Theme wrapper class
    'lib/customizer.php', // Theme customizer
    // SHORTCODE SECTION //
    'lib/svg-image.php',
    // import  template event
    'lib/content-event.php',
    'lib/user-class.php',
    'lib/acf-fields.php'
];

foreach ($sage_includes as $file) {
    if (!$filepath = locate_template($file)) {
        trigger_error(sprintf(__('Error locating %s for inclusion', 'sage'), $file), E_USER_ERROR);
    }

    require_once $filepath;
}
unset($file, $filepath);

$GLOBALS['SD_BASE_URI'] = constant('SD_BASE_URI');
$GLOBALS['SD_API_LIMIT'] = 20;
$GLOBALS['SD_API_VERSION'] = 'v1/';
$GLOBALS['SD_API_AUTH'] = $GLOBALS['SD_BASE_URI'].'auth';
$GLOBALS['SD_API_EVENTS'] = $GLOBALS['SD_BASE_URI'].$GLOBALS['SD_API_VERSION'].'events';
$GLOBALS['SD_API_RIDES'] = $GLOBALS['SD_BASE_URI'].$GLOBALS['SD_API_VERSION'].'rides';
$GLOBALS['SD_API_ACCOUNTS'] = $GLOBALS['SD_BASE_URI'].$GLOBALS['SD_API_VERSION'].'accounts';
$GLOBALS['SD_API_OBJECTS'] = $GLOBALS['SD_BASE_URI'].$GLOBALS['SD_API_VERSION'].'objects';

/*
 * Matches each symbol of PHP date format standard
 * with jQuery equivalent codeword
 * @author Tristan Jahier
 */
function dateformat_PHP_to_jQueryUI($php_format)
{
    $SYMBOLS_MATCHING = array(
        // Day
        'd' => 'dd',
        'D' => 'D',
        'j' => 'd',
        'l' => 'DD',
        'N' => '',
        'S' => '',
        'w' => '',
        'z' => 'o',
        // Week
        'W' => '',
        // Month
        'F' => 'MM',
        'm' => 'mm',
        'M' => 'M',
        'n' => 'm',
        't' => '',
        // Year
        'L' => '',
        'o' => '',
        'Y' => 'yy',
        'y' => 'y',
        // Time
        'a' => '',
        'A' => '',
        'B' => '',
        'g' => '',
        'G' => '',
        'h' => '',
        'H' => '',
        'i' => '',
        's' => '',
        'u' => ''
    );
    $jqueryui_format = "";
    $escaping = false;
    for ($i = 0; $i < strlen($php_format); $i++) {
        $char = $php_format[$i];
        if ($char === '\\') // PHP date format escaping character
        {
            $i++;
            if ($escaping) $jqueryui_format .= $php_format[$i];
            else $jqueryui_format .= '\'' . $php_format[$i];
            $escaping = true;
        } else {
            if ($escaping) {
                $jqueryui_format .= "'";
                $escaping = false;
            }
            if (isset($SYMBOLS_MATCHING[$char]))
                $jqueryui_format .= $SYMBOLS_MATCHING[$char];
            else
                $jqueryui_format .= $char;
        }
    }
    return $jqueryui_format;
}

add_filter('the_content', 'do_shortcode');

/*
*  ---- Filter search   ----
*/
function searchfilter($query) {

    if ($query->is_search && !is_admin() ) {
      $query->set('post_type',array('events','gadgets','giochi' ));
    }

return $query;
}

//add_filter('pre_get_posts','searchfilter');


// filter template for the subcategory //
function wpd_subcategory_template( $template ) {
    $cat = get_queried_object();
    if( 0 < $cat->category_parent )
        $template = locate_template( 'subcategory.php' );
    return $template;
}
// abilita il template per i livelli delle sottocategorie //
//add_filter( 'category_template', 'wpd_subcategory_template' );

// ADD TYPE OF MENU //
function register_my_menu() {
  //register_nav_menu('Principal-menu',__( 'Principal Menu' ));
  register_nav_menus(
      array(
        'Principal-menu' => 'Principal Menu',   // main nav in header
      )
    );
}
add_action( 'init', 'register_my_menu' );

function add_theme_scripts() {
    wp_enqueue_script( 'modal-template-event', get_template_directory_uri() . '/lib/js/template-event.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'get_image_svg', get_template_directory_uri() . '/lib/js/image-svg.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'ajax-detail-events', get_template_directory_uri() . '/lib/js/ajax-templates-event.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'get-item-event', get_template_directory_uri() . '/lib/js/get-item-event.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'effect-menu-js', get_template_directory_uri() . '/lib/js/effect-menu.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'img-svg', get_template_directory_uri() . '/lib/js/import-img-svg.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'commonjs', get_template_directory_uri() . '/lib/js/common.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'sessionjs', get_template_directory_uri() . '/lib/js/session.js', array ( 'jquery' ), 1.1, true);

    wp_enqueue_script( 'ckeditor', 'https://cdnjs.cloudflare.com/ajax/libs/ckeditor/4.11.3/ckeditor.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'adapters', 'https://cdnjs.cloudflare.com/ajax/libs/ckeditor/4.11.3/adapters/jquery.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'bootstrap-datepicker', 'https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'cookie', 'https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'Cropper', 'https://cdnjs.cloudflare.com/ajax/libs/cropperjs/1.5.1/cropper.min.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'Jcrop', 'https://cdnjs.cloudflare.com/ajax/libs/jquery-jcrop/2.0.4/js/Jcrop.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'spinjs', 'https://cdnjs.cloudflare.com/ajax/libs/spin.js/2.3.2/spin.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script( 'localesjs', 'https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/it.js', array ( 'jquery' ), 1.1, true);
    wp_enqueue_script(' jquery-validator', '//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js');

    wp_enqueue_style('my_stylesheet');
}
add_action( 'wp_enqueue_scripts', 'add_theme_scripts' );

function add_stylesheet_to_head() {
    echo "<link href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css' rel='stylesheet' type='text/css'>";
}
add_action( 'wp_head', 'add_stylesheet_to_head' );

add_action( 'wp_ajax_create_token', 'create_token' );
add_action( 'wp_ajax_nopriv_create_token', 'create_token' );


function custom_rewrite_tag() {
    add_rewrite_tag('%id_event%', '([0-9]+)');
    add_rewrite_tag('%id_ride%', '([0-9]+)');
}
add_action('init', 'custom_rewrite_tag', 10, 0);

function custom_rewrite_rule() {
    add_rewrite_rule('^events/([^/]*)/?','index.php?page_id='.get_field('event_page_id', 'option').'&id_event=$matches[1]','top');
    add_rewrite_rule('^rides/([^/]*)/?','index.php?page_id='.get_field('ride_page_id', 'option').'&id_ride=$matches[1]','top');
}
add_action('init', 'custom_rewrite_rule', 10, 0);

add_filter( 'wpseo_twitter_image', '__return_false' );
add_filter( 'wpseo_title', '__return_false' );
add_filter( 'wpseo_opengraph_url', '__return_false' );
add_filter( 'wpseo_opengraph_image', '__return_false' );
add_filter( 'wpseo_description', '__return_false' );

function endsWith($string, $endString)
{
    $len = strlen($endString);
    if ($len == 0) {
        return true;
    }
    return (substr($string, -$len) === $endString);
}

// Yoast SEO override
function change_wpseo_fb_tags_categories() {

    $req = $_SERVER["REQUEST_URI"];

    if (strpos($req, '/events/') === 0 && !endsWith($req, '/events/')) {
        global $wpseo_og;
        global $post;
        $id_event = explode('/', $req)[2];
        error_log('ID event '.$id_event);
        $get_event_req = $GLOBALS['SD_API_EVENTS'].'/'.urlencode($id_event);

        $curl = curl_init();
        curl_setopt_array($curl, [
            CURLOPT_RETURNTRANSFER => 1,
            CURLOPT_URL => $get_event_req,
        ]);
        error_log('Fetching event '.print_r($get_event_req, true));
        $data = curl_exec($curl);
        if (!$data) {
            error_log('Error while fetching event: "' . curl_error($curl) . '" - Code: ' . curl_errno($curl));
        } else {
            $data = json_decode($data);
            //print_r($data);

            if (isset($data->code)) {
                error_log('Error while fetching event');
            } else {
                $image = '';
                if (isset($data->images) && isset($data->images[0]->uri)) {
                    $image = $data->images[0]->uri;
                }
                $desc = get_field('event_share_description', 'option');
                $title = $data->name;

                $wpseo_og->og_tag('twitter:image', $image);
                $wpseo_og->og_tag('og:title', $title);
                $wpseo_og->og_tag('og:description', $desc);
                $wpseo_og->og_tag('og:image', $image);
                $wpseo_og->og_tag('og:url', 'https://stagedriving.com/events/'.$id_event.'/');


                $instance = WPSEO_Frontend::get_instance();
                $instance->title($title);
            }
        }
        curl_close($curl);
    } else if (strpos($req, '/rides/') === 0 && !endsWith($req, '/rides/')) {
        global $wpseo_og;
        global $post;
        $id_ride = explode('/', $req)[2];
        error_log('ID ride '.$id_ride);
        $get_ride_req = $GLOBALS['SD_API_RIDES'].'/'.urlencode($id_ride);

        $curl = curl_init();
        curl_setopt_array($curl, [
            CURLOPT_RETURNTRANSFER => 1,
            CURLOPT_URL => $get_ride_req,
        ]);
        error_log('Fetching event '.print_r($get_ride_req, true));
        $data = curl_exec($curl);
        if (!$data) {
            error_log('Error while fetching ride: "' . curl_error($curl) . '" - Code: ' . curl_errno($curl));
        } else {
            $data = json_decode($data);
            //print_r($data);

            if (isset($data->code)) {
                error_log('Error while fetching ride');
            } else {
                $image = '';
                $event = null;
                if (isset($data->fromEvent)) {
                    $event = $data->fromEvent;
                } else if (isset($data->toEvent)) {
                    $event = $data->toEvent;
                }

                if (isset($event->images) && isset($event->images[0]->uri)) {
                    $image = $event->images[0]->uri;
                }
                $desc = get_field('ride_share_description', 'option');
                $title = $event->name;

                $wpseo_og->og_tag('twitter:image', $image);
                $wpseo_og->og_tag('og:title', $title);
                $wpseo_og->og_tag('og:description', $desc);
                $wpseo_og->og_tag('og:image', $image);
                $wpseo_og->og_tag('og:url', 'https://stagedriving.com/rides/'.$id_ride.'/');


                $instance = WPSEO_Frontend::get_instance();
                $instance->title($title);
            }
        }
        curl_close($curl);
    }

}
add_action('wpseo_opengraph', 'change_wpseo_fb_tags_categories', 9999);

//add_action ('wp_loaded', 'sd_auth_redirect');
function sd_auth_redirect() {
    if (isset($_GET['t'])){
        $t = $_GET['t'];
        setcookie("accessToken", $t, time()+320000, '/', $_SERVER['HTTP_HOST']);

        error_log('Setting accessToken '.$t);

        /*$p = $_SERVER['REQUEST_SCHEME'] .'://'. $_SERVER['HTTP_HOST']
         . explode('?', $_SERVER['REQUEST_URI'], 2)[0];

        wp_redirect($p);
        exit;*/
    }
}

/****/

?>
