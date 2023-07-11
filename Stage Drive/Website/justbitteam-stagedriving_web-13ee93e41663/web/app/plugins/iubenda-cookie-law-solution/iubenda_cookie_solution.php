<?php
/*
Plugin Name: iubenda Cookie Solution for GDPR
Plugin URI: https://www.iubenda.com
Description: iubenda Cookie Solution allows you to make your website GDPR compliant and manage all aspects of cookie law on WP.
Version: 1.15.8
Author: iubenda
Author URI: https://www.iubenda.com
License: MIT License
License URI: http://opensource.org/licenses/MIT
Text Domain: iubenda-cookie-law-solution
Domain Path: /languages

ibenda Cookie Solution
Copyright (C) 2018-2019, iubenda s.r.l

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

// exit if accessed directly
if ( ! defined( 'ABSPATH' ) )
	exit;

// define contants
define( 'IUB_DEBUG', false );

// set plugin instance
$iubenda_cookie_law_solution = new iubenda_Cookie_Law_Solution();

/**
 * iubenda_Cookie_Law_Solution final class.
 *
 * @class iubenda_Cookie_Law_Solution
 * @version	1.15.7
 */
class iubenda_Cookie_Law_Solution {

	public $options;
	public $defaults = array(
		'parse'			 => false, // iubenda_parse
		'skip_parsing'	 => true, // skip_parsing
		'ctype'			 => true, // iubenda_ctype
		'parse'			 => false, // iubenda_parse
		'parser_engine'	 => 'new', // parser_engine
		'output_feed'	 => true, // iubenda_output_feed
		'code_default'	 => false, // iubenda-code-default,
		'menu_position'	 => 'topmenu',
		'custom_scripts' => array(),
		'custom_iframes' => array(),
		'deactivation'	 => false
	);
	public $version = '1.15.8';
	public $no_html = false;
	public $links = array();
	public $multilang = false;
	public $languages = array();
	public $lang_default = '';

	/**
	 * Class constructor.
	 */
	public function __construct() {
		register_activation_hook( __FILE__, array( $this, 'activation' ) );
		register_deactivation_hook( __FILE__, array( $this, 'deactivation' ) );

		// settings
		$this->options = array_merge( $this->defaults, (array) get_option( 'iubenda_cookie_law_solution', $this->defaults ) );

		// actions
		add_action( 'admin_init', array( $this, 'register_options' ) );
		add_action( 'admin_init', array( $this, 'update_plugin' ), 9 );
		add_action( 'admin_init', array( $this, 'admin_page_redirect' ), 20 );
		add_action( 'admin_menu', array( $this, 'admin_menu_options' ) );
		add_action( 'admin_notices', array( $this, 'settings_errors' ) );
		add_action( 'plugins_loaded', array( $this, 'load_textdomain' ) );
		add_action( 'plugins_loaded', array( $this, 'init' ) );
		add_action( 'after_setup_theme', array( $this, 'register_shortcode' ) );
		add_action( 'admin_enqueue_scripts', array( $this, 'admin_enqueue_scripts' ) );
		add_action( 'admin_print_styles', array( $this, 'admin_print_styles' ) );
		add_action( 'wp_head', array( $this, 'wp_head' ), 99 );
		add_action( 'template_redirect', array( $this, 'output_start' ), 0 );
		add_action( 'shutdown', array( $this, 'output_end' ), 100 );
	}

	/**
	 * Initialize plugin.
	 *
	 * @return void
	 */
	public function init() {
		// check if WPML or Polylang is active
		include_once( ABSPATH . 'wp-admin/includes/plugin.php' );

		// Polylang support
		if ( ( is_plugin_active( 'polylang/polylang.php' ) || is_plugin_active( 'polylang-pro/polylang.php' ) ) && function_exists( 'PLL' ) ) {
			$this->multilang = true;

			// get registered languages
			$registered_languages = PLL()->model->get_languages_list();

			if ( ! empty( $registered_languages ) ) {
				foreach ( $registered_languages as $language )
					$this->languages[$language->slug] = $language->name;
			}

			// get default language
			$this->lang_default = pll_default_language();

		// WPML support
		} elseif ( is_plugin_active( 'sitepress-multilingual-cms/sitepress.php' ) && class_exists( 'SitePress' ) ) {
			$this->multilang = true;

			global $sitepress;

			// get registered languages
			$registered_languages = icl_get_languages();

			if ( ! empty( $registered_languages ) ) {
				foreach ( $registered_languages as $language )
					$this->languages[$language['code']] = $language['display_name'];
			}

			// get default language
			$this->lang_default = $sitepress->get_default_language();
		}

		// load iubenda parser
		require_once( dirname( __FILE__ ) . '/iubenda-cookie-class/iubenda.class.php' );

		$links = array(
			'en' => array(
				'guide'	=> 'https://www.iubenda.com/en/iubenda-cookie-law-solution',
				'plugin_page' => 'https://www.iubenda.com/en/help/posts/1215',
				'generating_code' => 'https://www.iubenda.com/en/help/posts/1177',
				'support_forum' => 'https://support.iubenda.com/support/home',
				'documentation' => 'https://www.iubenda.com/en/help/posts/1215'
			),
			'it' => array(
				'guide'	=> 'https://www.iubenda.com/it/soluzione-cookie-law',
				'plugin_page' => 'https://www.iubenda.com/it/help/posts/810',
				'generating_code' => 'https://www.iubenda.com/it/help/posts/680',
				'support_forum' => 'https://support.iubenda.com/support/home',
				'documentation' => 'https://www.iubenda.com/it/help/posts/810',
			)
		);

		$locale = explode( '_', get_locale() );
		$locale_code = $locale[0];

		// assign links
		$this->links = in_array( $locale_code, array_keys( $links ) ) ? $links[$locale_code] : $links['en'];
	}

	/**
	 * Plugin activation.
	 *
	 * @return void
	 */
	public function activation() {
		add_option( 'iubenda_cookie_law_solution', $this->options, '', 'no' );
		add_option( 'iubenda_cookie_law_version', $this->version, '', 'no' );
	}

	/**
	 * Plugin deactivation.
	 *
	 * @return void
	 */
	public function deactivation() {
		// remove options from database?
		if ( $this->options['deactivation'] ) {
			delete_option( 'iubenda_cookie_law_solution' );
			delete_option( 'iubenda_cookie_law_version' );
		}
	}

	/**
	 * Plugin options migration for versions < 1.14.0
	 *
	 * @return void
	 */
	public function update_plugin() {
		if ( ! current_user_can( 'install_plugins' ) )
			return;

		$db_version = get_option( 'iubenda_cookie_law_version' );
		$db_version = ! $db_version ? '1.13.0' : $db_version;

		if ( $db_version != false ) {
			if ( version_compare( $db_version, '1.14.0', '<' ) ) {
				$options = array();

				$old_new = array(
					'iubenda_parse'			 => 'parse',
					'skip_parsing'			 => 'skip_parsing',
					'iubenda_ctype'			 => 'ctype',
					'iubenda_parse'			 => 'parse',
					'parser_engine'			 => 'parser_engine',
					'iubenda_output_feed'	 => 'output_feed',
					'iubenda-code-default'	 => 'code_default',
					'default_skip_parsing'	 => '',
					'default_iubendactype'	 => '',
					'default_iubendaparse'	 => '',
					'default_parser_engine'	 => '',
					'iub_code'				 => '',
				);

				foreach ( $old_new as $old => $new ) {
					if ( $new ) {
						$options[$new] = get_option( $old );
					}
					delete_option( $old );
				}

				// multilang support
				if ( ! empty( $this->languages ) ) {
					foreach ( $this->languages as $lang ) {
						$code = get_option( 'iubenda-code-' . $lang );

						if ( ! empty( $code ) ) {
							$options['code_' . $lang] = $code;

							delete_option( 'iubenda-code-' . $lang );
						}
					}
				}

				add_option( 'iubenda_cookie_law_solution', $options, '', 'no' );
				add_option( 'iubenda_cookie_law_version', $this->version, '', 'no' );
			}
		}
	}

	/**
	 * Register shortcode function.
	 *
	 * @return void
	 */
	public function register_shortcode() {
		add_shortcode( 'iub-cookie-policy', array( $this, 'block_shortcode' ) );
		add_shortcode( 'iub-cookie-block', array( $this, 'block_shortcode' ) );
		add_shortcode( 'iub-cookie-skip', array( $this, 'skip_shortcode' ) );
	}

	/**
	 * Handle block shortcode function.
	 *
	 * @param array $atts
	 * @param mixed $content
	 * @return mixed
	 */
	public function block_shortcode( $atts, $content = '' ) {
		return '<!--IUB-COOKIE-BLOCK-START-->' . do_shortcode( $content ) . '<!--IUB-COOKIE-BLOCK-END-->';
	}
	
	/**
	 * Handle skip shortcode function.
	 *
	 * @param array $atts
	 * @param mixed $content
	 * @return mixed
	 */
	public function skip_shortcode( $atts, $content = '' ) {
		return '<!--IUB-COOKIE-BLOCK-SKIP-START-->' . do_shortcode( $content ) . '<!--IUB-COOKIE-BLOCK-SKIP-END-->';
	}

	/**
	 * Add submenu.
	 *
	 * @return void
	 */
	public function admin_menu_options() {
		if ( $this->options['menu_position'] === 'submenu' ) {
			// sub menu
			add_submenu_page(
				'options-general.php', 'iubenda', 'iubenda', apply_filters( 'iubenda_cookie_law_cap', 'manage_options' ), 'iubenda-cookie-law-solution', array( $this, 'options_page' ), 'none'
			);
		} else {
			// top menu
			add_menu_page(
				'iubenda', 'iubenda', apply_filters( 'iubenda_cookie_law_cap', 'manage_options' ), 'iubenda-cookie-law-solution', array( $this, 'options_page' ), 'none'
			);
		}
	}

	/**
	 * Load textdomain.
	 *
	 * @return void
	 */
	public function load_textdomain() {
		load_plugin_textdomain( 'iubenda-cookie-law-solution', false, dirname( plugin_basename( __FILE__ ) ) . '/languages/' );
	}

	/**
	 * Load admin scripts and styles.
	 *
	 * @param string $page
	 * @return void
	 */
	public function admin_enqueue_scripts( $page ) {
		if ( ! in_array( $page, array( 'toplevel_page_iubenda-cookie-law-solution', 'settings_page_iubenda-cookie-law-solution' ) ) )
			return;

		wp_enqueue_script(
		'iubenda-admin', plugins_url( 'js/admin.js', __FILE__ ), array( 'jquery' )
		);

		wp_enqueue_style( 'iubenda-admin', plugins_url( 'css/admin.css', __FILE__ ) );
	}

	/**
	 * Load admin style inline, for menu icon only.
	 *
	 * @return mixed
	 */
	public function admin_print_styles() {
		echo '
		<style>
			a.toplevel_page_iubenda-cookie-law-solution .wp-menu-image {
				background-image: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj48c3ZnIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIHZpZXdCb3g9IjAgMCAyMzIgNTAzIiB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHhtbDpzcGFjZT0icHJlc2VydmUiIHhtbG5zOnNlcmlmPSJodHRwOi8vd3d3LnNlcmlmLmNvbS8iIHN0eWxlPSJmaWxsLXJ1bGU6ZXZlbm9kZDtjbGlwLXJ1bGU6ZXZlbm9kZDtzdHJva2UtbGluZWpvaW46cm91bmQ7c3Ryb2tlLW1pdGVybGltaXQ6MS40MTQyMTsiPiAgICA8ZyB0cmFuc2Zvcm09Im1hdHJpeCgxLDAsMCwxLDEzNi4yNDcsMjY4LjgzMSkiPiAgICAgICAgPHBhdGggZD0iTTAsLTM1LjgxTC0zNi4zLDAuNDg5TC0zNi4zLDE0MC45NzhMMCwxNDAuOTc4TDAsLTM1LjgxWk0tMjAuOTM4LC0xMjkuODAyQy02LjI4NywtMTI5LjgwMiA1LjU4NywtMTQxLjU2NSA1LjU4NywtMTU2LjA2QzUuNTg3LC0xNzAuNTU2IC02LjI4NywtMTgyLjMwOCAtMjAuOTM4LC0xODIuMzA4Qy0zNS42LC0xODIuMzA4IC00Ny40NzQsLTE3MC41NTYgLTQ3LjQ3NCwtMTU2LjA2Qy00Ny40NzQsLTE0MS41NjUgLTM1LjYsLTEyOS44MDIgLTIwLjkzOCwtMTI5LjgwMk04OS4zNiwtMTU0LjQxNkM4OS4zNiwtMTI3LjgyNSA3OS41NzUsLTEwMy40OTkgNjMuMjY5LC04NC42NzJMODYuNjk0LDIyNi42MjhMLTEyMi43MjgsMjI2LjYyOEwtMTAwLjAyNCwtNzkuMjI5Qy0xMTkuMzUxLC05OC42NjggLTEzMS4yNDcsLTEyNS4xNTkgLTEzMS4yNDcsLTE1NC40MTZDLTEzMS4yNDcsLTIxNC4wODYgLTgxLjg3NCwtMjYyLjQzOCAtMjAuOTM4LC0yNjIuNDM4QzM5Ljk5OSwtMjYyLjQzOCA4OS4zNiwtMjE0LjA4NiA4OS4zNiwtMTU0LjQxNiIgc3R5bGU9ImZpbGw6d2hpdGU7ZmlsbC1ydWxlOm5vbnplcm87Ii8+ICAgIDwvZz48L3N2Zz4=);
				background-position: center center;
				background-repeat: no-repeat;
				background-size: 7px auto;
			}
		</style>
		';
	}

	/**
	 * Redirect to the correct urle after switching menu position.
	 *
	 * @global string $pagenow
	 * @return void
	 */
	public function admin_page_redirect() {
		if ( ! empty( $_GET['settings-updated'] ) && ! empty( $_GET['page'] ) && in_array( $_GET['page'], array( 'iubenda-cookie-law-solution' ) ) ) {
			global $pagenow;

			// no redirect by default
			$redirect_to = false;

			if ( $this->options['menu_position'] === 'submenu' && $pagenow === 'admin.php' ) {
				// sub menu
				$redirect_to = admin_url( 'options-general.php?page=iubenda-cookie-law-solution' );
			} elseif ( $this->options['menu_position'] === 'topmenu' && $pagenow === 'options-general.php' ) {
				// top menu
				$redirect_to = admin_url( 'admin.php?page=iubenda-cookie-law-solution' );
			}

			if ( $redirect_to ) {
				// make sure it's current host location
				wp_safe_redirect( add_query_arg( 'settings-updated', true, $redirect_to ) );
				exit;
			}
		}
	}

	/**
	 * Add wp_head content.
	 *
	 * @return void
	 */
	public function wp_head() {
		// break on admin side
		if ( is_admin() )
			return;

		// check content type
		if ( (bool) $this->options['ctype'] == true ) {
			$iub_headers = headers_list();
			$destroy = true;

			foreach ( $iub_headers as $header ) {
				if ( strpos( $header, "Content-Type: text/html" ) !== false || strpos( $header, "Content-type: text/html" ) !== false ) {
					$destroy = false;
					break;
				}
			}

			if ( $destroy )
				$this->no_html = true;
		}

		// is post or not html content type?
		if ( $_POST || $this->no_html )
			return;

		// initial head output
		$iubenda_code = "";

		if ( $this->multilang === true && defined( 'ICL_LANGUAGE_CODE' ) && isset( $this->options['code_' . ICL_LANGUAGE_CODE] ) ) {
			$iubenda_code .= $this->options['code_' . ICL_LANGUAGE_CODE];

			// no code for current language, use default
			if ( ! $iubenda_code )
				$iubenda_code .= $this->options['code_default'];
		} else
			$iubenda_code .= $this->options['code_default'];

		$iubenda_code = $this->parse_code( $iubenda_code, true );

		if ( $iubenda_code !== '' ) {
			$iubenda_code .= "\n
			<script>
				var iCallback = function() {};

				if ( typeof _iub.csConfiguration != 'undefined' ) {
					if ( 'callback' in _iub.csConfiguration ) {
						if ( 'onConsentGiven' in _iub.csConfiguration.callback )
							iCallback = _iub.csConfiguration.callback.onConsentGiven;

						_iub.csConfiguration.callback.onConsentGiven = function() {
							iCallback();

							/* separator */
							jQuery('noscript._no_script_iub').each(function (a, b) { var el = jQuery(b); el.after(el.html()); });
						}
					}
				}
			</script>";

			echo '<!--IUB-COOKIE-SKIP-START-->' . $iubenda_code . '<!--IUB-COOKIE-SKIP-END-->';
		}
	}

	/**
	 * Initialize html output.
	 *
	 * @return void
	 */
	public function output_start() {
		if ( ! is_admin() )
			ob_start( array( $this, 'output_callback' ) );
	}

	/**
	 * Finish html output.
	 *
	 * @return void
	 */
	public function output_end() {
		if ( ! is_admin() && ob_get_level() )
			ob_end_flush();
	}

	/**
	 * Handle final html output.
	 *
	 * @param mixed $output
	 * @return mixed
	 */
	public function output_callback( $output ) {
		// check whether to run parser or not
		
		// bail on ajax, xmlrpc or iub_no_parse request
		if (
			( defined( 'XMLRPC_REQUEST' ) && XMLRPC_REQUEST )
			|| ( defined( 'DOING_AJAX' ) && DOING_AJAX )
			|| isset( $_SERVER["HTTP_X_REQUESTED_WITH"] )
			|| isset( $_GET['iub_no_parse'] )
		)
			return $output;

		// bail on admin side
		if ( is_admin() )
			return $output;

		// bail on rss feed
		if ( is_feed() && $this->options['output_feed'] )
			return $output;

		if ( strpos( $output, "<html" ) === false )
			return $output;
		elseif ( strpos( $output, "<html" ) > 200 )
			return $output;

		// bail if skripts blocking disabled
		if ( ! $this->options['parse'] )
			return $output;

		// bail if consent given and skip parsing enabled
		if ( iubendaParser::consent_given() && $this->options['skip_parsing'] )
			return $output;

		// bail if bot detectd, no html in output or it's a post request
		if ( iubendaParser::bot_detected() || $_POST || $this->no_html )
			return $output;

		// google recaptcha v3 compatibility
		if ( class_exists( 'WPCF7' ) && (int) WPCF7::get_option( 'iqfix_recaptcha' ) === 0 && ! iubendaParser::consent_given() )
			$this->options['custom_scripts'][] = 'grecaptcha';

		$startime = microtime( true );
		$output = apply_filters( 'iubenda_initial_output', $output );

		// experimental class
		if ( $this->options['parser_engine'] == 'new' ) {
			$iubenda = new iubendaParser( mb_convert_encoding( $output, 'HTML-ENTITIES', 'UTF-8' ), array( 'type' => 'faster', 'scripts' => $this->options['custom_scripts'], 'iframes' => $this->options['custom_iframes'] ) );

			// render output
			$output = $iubenda->parse();

			// append signature
			$output .= '<!-- Parsed with iubenda experimental class in ' . round( microtime( true ) - $startime, 4 ) . ' sec. -->';
		// default class
		} else {
			$iubenda = new iubendaParser( $output, array( 'type' => 'page', 'scripts' => $this->options['custom_scripts'], 'iframes' => $this->options['custom_iframes'] ) );

			// render output
			$output = $iubenda->parse();

			// append signature
			$output .= '<!-- Parsed with iubenda default class in ' . round( microtime( true ) - $startime, 4 ) . ' sec. -->';
		}

		return apply_filters( 'iubenda_final_output', $output );
	}

	/**
	 * Register plugin options.
	 *
	 * @return void
	 */
	public function register_options() {
		register_setting( 'iubenda_cookie_law_solution', 'iubenda_cookie_law_solution', array( $this, 'save_options' ) );

		add_settings_section( 'iubenda_cookie_law_solution', '', '', 'iubenda_cookie_law_solution' );
		add_settings_field( 'iub_code', __( 'Code', 'iubenda-cookie-law-solution' ), array( $this, 'iub_code' ), 'iubenda_cookie_law_solution', 'iubenda_cookie_law_solution' );
		add_settings_field( 'iub_parse', __( 'Scripts blocking', 'iubenda-cookie-law-solution' ), array( $this, 'iub_parse' ), 'iubenda_cookie_law_solution', 'iubenda_cookie_law_solution' );
		add_settings_field( 'iub_custom_scripts', __( 'Custom scripts', 'iubenda-cookie-law-solution' ), array( $this, 'iub_custom_scripts' ), 'iubenda_cookie_law_solution', 'iubenda_cookie_law_solution' );
		add_settings_field( 'iub_ctype', __( 'Content type', 'iubenda-cookie-law-solution' ), array( $this, 'iub_ctype' ), 'iubenda_cookie_law_solution', 'iubenda_cookie_law_solution' );
		add_settings_field( 'iub_output_feed', __( 'RSS feed', 'iubenda-cookie-law-solution' ), array( $this, 'iub_output_feed' ), 'iubenda_cookie_law_solution', 'iubenda_cookie_law_solution' );
		add_settings_field( 'iub_menu_position', __( 'Menu position', 'iubenda-cookie-law-solution' ), array( $this, 'iub_menu_position' ), 'iubenda_cookie_law_solution', 'iubenda_cookie_law_solution' );
		add_settings_field( 'iub_deactivation', __( 'Deactivation', 'iubenda-cookie-law-solution' ), array( $this, 'iub_deactivation' ), 'iubenda_cookie_law_solution', 'iubenda_cookie_law_solution' );
	}

	/**
	 * Display errors and notices.
	 *
	 * @global string $pagenow
	 */
	public function settings_errors() {
		global $pagenow;

		// force display notices in top menu settings page
		if ( $pagenow != 'options-general.php' )
			settings_errors( 'iub_settings_errors' );
	}

	/**
	 * Code option.
	 *
	 * @return mixed
	 */
	public function iub_code() {
		// multilang support
		if ( $this->multilang && ! empty( $this->languages ) ) {
			echo '
			<div id="contextual-help-wrap" class="contextual-help-wrap hidden" tabindex="-1" style="display: block;">
				<div id="contextual-help-back" class="contextual-help-back"></div>
				<div id="contextual-help-columns" class="contextual-help-columns">
					<div class="contextual-help-tabs">
						<ul>';
			foreach ( $this->languages as $lang_id => $lang_name ) {
				echo '
							<li class="' . ( $this->lang_default == $lang_id ? 'active' : '' ) . '">
								<a href="#tab-panel-' . $lang_id . '" aria-controls="tab-panel-' . $lang_id . '">' . $lang_name . '</a>
							</li>';
			}
			echo '
						</ul>
					</div>

					<div id="contextual-help-tabs-wrap" class="contextual-help-tabs-wrap">';
						foreach ( $this->languages as $lang_id => $lang_name ) {
							// get code for the language
							$code = ! empty( $this->options['code_' . $lang_id] ) ? html_entity_decode( $this->parse_code( $this->options['code_' . $lang_id] ) ) : '';
							// handle default, if empty
							$code = empty( $code ) && $lang_id == $this->lang_default ? html_entity_decode( $this->parse_code( $this->options['code_default'] ) ) : $code;

							echo '
							<div id="tab-panel-' . $lang_id . '" class="help-tab-content' . ( $this->lang_default == $lang_id ? ' active' : '' ) . '">
								<textarea name="iubenda_cookie_law_solution[code_' . $lang_id . ']" class="large-text" cols="50" rows="10">' . $code . '</textarea>
								<p class="description">' . sprintf( __( 'Enter the iubenda code for %s.', 'iubenda-cookie-law-solution' ), $lang_name ) . '</p>
							</div>';
						}
			echo '
					</div>
				</div>
			</div>';
		} else {
			echo '
			<div id="iub_code_default">
				<textarea name="iubenda_cookie_law_solution[code_default]" class="large-text" cols="50" rows="10">' . html_entity_decode( $this->parse_code( $this->options['code_default'] ) ) . '</textarea>
				<p class="description">' . __( 'Enter the iubenda code.', 'iubenda-cookie-law-solution' ) . '</p>
			</div>';
		}
	}

	/**
	 * Custom scripts option.
	 *
	 * @return void
	 */
	public function iub_custom_scripts() {
		echo '
		<div id="contextual-help-wrap-2" class="contextual-help-wrap hidden" tabindex="-1" style="display: block;">
				<div id="contextual-help-back-2" class="contextual-help-back"></div>
				<div id="contextual-help-columns-2" class="contextual-help-columns">
				<div class="contextual-help-tabs">
					<ul>
						<li class="active">
							<a href="#tab-panel-scripts" aria-controls="tab-panel-scripts">' . esc_html__( 'Scripts', 'iubenda-cookie-law-solution' ) . '</a>
						</li>
						<li>
							<a href="#tab-panel-iframes" aria-controls="tab-panel-iframes">' . esc_html__( 'Iframes', 'iubenda-cookie-law-solution' ) . '</a>
						</li>
					</ul>
				</div>
				<div id="contextual-help-tabs-wrap-2" class="contextual-help-tabs-wrap">
					<div id="tab-panel-scripts" class="help-tab-content active">
						<textarea name="iubenda_cookie_law_solution[custom_scripts]" class="large-text" cols="50" rows="10">' . esc_textarea( implode( "\n", $this->options['custom_scripts'] ) ) . '</textarea>
						<p class="description">' . __( 'Enter a list of custom scripts (one per line).', 'iubenda-cookie-law-solution' ) . '</p>
					</div>
					<div id="tab-panel-iframes" class="help-tab-content">
						<textarea name="iubenda_cookie_law_solution[custom_iframes]" class="large-text" cols="50" rows="10">' . esc_textarea( implode( "\n", $this->options['custom_iframes'] ) ) . '</textarea>
						<p class="description">' . __( 'Enter a list of custom iframes (one per line).', 'iubenda-cookie-law-solution' ) . '</p>
					</div>
				</div>
			</div>
		</div>';
	}

	/**
	 * Parsing option.
	 *
	 * @return mixed
	 */
	public function iub_parse() {
		echo '
		<div id="iub_parse_container">
			<label><input id="iub_parse" type="checkbox" name="iubenda_cookie_law_solution[parse]" value="1" ' . checked( true, (bool) $this->options['parse'], false ) . '/>' . __( 'Automatically block scripts detected by the plugin.', 'iubenda-cookie-law-solution' ) . '</label>
			<p class="description">' . '(' . sprintf( __( "see <a href=\"%s\" target=\"_blank\">our documentation</a> for the list of detected scripts.", 'iubenda-cookie-law-solution' ), $this->links['documentation'] ) . ')' . '</p>
			<div id="iub_parser_engine_container"' . ( $this->options['parse'] === false ? ' style="display: none;"' : '' ) . '>
				<div>
					<label><input id="iub_parser_engine-new" type="radio" name="iubenda_cookie_law_solution[parser_engine]" value="new" ' . checked( 'new', $this->options['parser_engine'], false ) . ' />' . __( 'Primary', 'iubenda-cookie-law-solution' ) . '</label>
					<label><input id="iub_parser_engine-default" type="radio" name="iubenda_cookie_law_solution[parser_engine]" value="default" ' . checked( 'default', $this->options['parser_engine'], false ) . ' />' . __( 'Secondary', 'iubenda-cookie-law-solution' ) . '</label>
					<p class="description">' . __( 'Select parsing engine.', 'iubenda-cookie-law-solution' ) . '</p>
				</div>
				<div>
					<label><input id="iub_skip_parsing" type="checkbox" name="iubenda_cookie_law_solution[skip_parsing]" value="1" ' . checked( true, (bool) $this->options['skip_parsing'], false ) . '/>' . __( 'Leave scripts untouched on the page if the user has already given consent', 'iubenda-cookie-law-solution' ) . '</label>
					<p class="description">(' . __( "improves performance, highly recommended, to be deactivated only if your site uses a caching system", 'iubenda-cookie-law-solution' ) . ')</p>
				</div>
			</div>
		</div>';
	}

	/**
	 * Ctype option.
	 *
	 * @return mixed
	 */
	public function iub_ctype() {
		echo '
		<div id="iub_ctype_container">
			<label><input id="iub_ctype" type="checkbox" name="iubenda_cookie_law_solution[ctype]" value="1" ' . checked( true, (bool) $this->options['ctype'], false ) . '/>' . __( 'Restrict the plugin to run only for requests that have "Content-type: text / html" (recommended)', 'iubenda-cookie-law-solution' ) . '</label>
		</div>';
	}

	/**
	 * RSS feed option.
	 *
	 * @return mixed
	 */
	public function iub_output_feed() {
		echo '
		<div id="iub_output_feed_container">
			<label><input id="iub_ctype" type="checkbox" name="iubenda_cookie_law_solution[output_feed]" value="1" ' . checked( true, (bool) $this->options['output_feed'], false ) . '/>' . __( 'Do not run the plugin inside the RSS feed (recommended)', 'iubenda-cookie-law-solution' ) . '</label>
		</div>';
	}

	/**
	 * Menu option.
	 *
	 * @return mixed
	 */
	public function iub_menu_position() {
		echo '
		<div id="iub_menu_position_container">
			<label><input id="iub_menu_position-topmenu" type="radio" name="iubenda_cookie_law_solution[menu_position]" value="topmenu" ' . checked( 'topmenu', $this->options['menu_position'], false ) . ' />' . __( 'Top menu', 'iubenda-cookie-law-solution' ) . '</label>
			<label><input id="iub_menu_position-submenu" type="radio" name="iubenda_cookie_law_solution[menu_position]" value="submenu" ' . checked( 'submenu', $this->options['menu_position'], false ) . ' />' . __( 'Submenu', 'iubenda-cookie-law-solution' ) . '</label>
			<p class="description">' . __( 'Select whether to display iubenda in a top admin menu or the Settings submenu.', 'iubenda-cookie-law-solution' ) . '</p>
		</div>';
	}

	/**
	 * Deactivation option.
	 *
	 * @return mixed
	 */
	public function iub_deactivation() {
		echo '
		<div id="iub_deactivation_container">
			<label><input id="iub_deactivation" type="checkbox" name="iubenda_cookie_law_solution[deactivation]" value="1" ' . checked( true, (bool) $this->options['deactivation'], false ) . '/>' . __( 'Delete all plugin data upon deactivation?', 'iubenda-cookie-law-solution' ) . '</label>
		</div>';
	}

	/**
	 * Save options.
	 *
	 * @return void
	 */
	public function save_options( $input ) {
		if ( ! current_user_can( apply_filters( 'iubenda_cookie_law_cap', 'manage_options' ) ) )
			return $input;

		// save options
		if ( isset( $_POST['save_iubenda_options'] ) ) {
			$input['parse'] = (bool) isset( $input['parse'] );
			$input['parser_engine'] = isset( $input['parser_engine'] ) && in_array( $input['parser_engine'], array( 'default', 'new' ) ) ? $input['parser_engine'] : $this->defaults['parser_engine'];
			$input['skip_parsing'] = (bool) isset( $input['skip_parsing'] );
			$input['ctype'] = (bool) isset( $input['ctype'] );
			$input['output_feed'] = (bool) isset( $input['output_feed'] );
			$input['menu_position'] = isset( $input['menu_position'] ) && in_array( $input['menu_position'], array( 'topmenu', 'submenu' ) ) ? $input['menu_position'] : $this->defaults['menu_position'];
			$input['deactivation'] = (bool) isset( $input['deactivation'] );

			// multilang support
			if ( $this->multilang && ! empty( $this->languages ) ) {
				foreach ( $this->languages as $lang_id => $lang_name ) {
					$input['code_' . $lang_id] = ! empty( $input['code_' . $lang_id] ) ? $this->parse_code( $input['code_' . $lang_id] ) : '';

					// handle default lang too
					if ( $lang_id == $this->lang_default ) {
						$input['code_default'] = ! empty( $input['code_' . $lang_id] ) ? $this->parse_code( $input['code_' . $lang_id] ) : $this->options['code_default'];
					}
				}
			} else
				$input['code_default'] = ! empty( $input['code_default'] ) ? $this->parse_code( $input['code_default'] ) : '';

			// scripts
			if ( isset( $input['custom_scripts'] ) ) {
				$input['custom_scripts'] = trim( $input['custom_scripts'] );

				if ( ! empty( $input['custom_scripts'] ) )
					$input['custom_scripts'] = array_map( 'trim', explode( "\n", str_replace( "\r", '', $input['custom_scripts'] ) ) );
				else
					$input['custom_scripts'] = array();
			} else
				$input['custom_scripts'] = array();

			// iframes
			if ( isset( $input['custom_iframes'] ) ) {
				$input['custom_iframes'] = trim( $input['custom_iframes'] );

				if ( ! empty( $input['custom_iframes'] ) )
					$input['custom_iframes'] = array_map( 'trim', explode( "\n", str_replace( "\r", '', $input['custom_iframes'] ) ) );
				else
					$input['custom_iframes'] = array();
			} else
				$input['custom_iframes'] = array();

			add_settings_error( 'iub_settings_errors', 'iub_settings_updated', __( 'Settings saved.', 'iubenda-cookie-law-solution' ), 'updated' );
			// reset options
		} elseif ( isset( $_POST['reset_iubenda_options'] ) ) {
			$input = $this->defaults;

			// multilang support
			if ( $this->multilang && ! empty( $this->languages ) ) {
				foreach ( $this->languages as $lang_id => $lang_name ) {
					$input['code_' . $lang_id] = '';
				}
			}

			add_settings_error( 'iub_settings_errors', 'iub_settings_restored', __( 'Settings restored to defaults.', 'iubenda-cookie-law-solution' ), 'updated' );
		}

		return $input;
	}

	/**
	 * Parse iubenda code.
	 *
	 * @param string $source
	 * @param bool $display
	 * @return string
	 */
	public function parse_code( $source, $display = false ) {
		// return $source;
		$source = trim( $source );

		preg_match_all( '/(\"(?:html|content)\"(?:\s+)?\:(?:\s+)?)\"((?:.*?)(?:[^\\\\]))\"/s', $source, $matches );

		// found subgroup?
		if ( ! empty( $matches[1] ) && ! empty( $matches[2] ) ) {
			foreach ( $matches[2] as $no => $match ) {
				$source = str_replace( $matches[0][$no], $matches[1][$no] . '[[IUBENDA_TAG_START]]' . $match . '[[IUBENDA_TAG_END]]', $source );
			}

			// kses it
			$source = wp_kses( $source, $this->get_allowed_html() );

			preg_match_all( '/\[\[IUBENDA_TAG_START\]\](.*?)\[\[IUBENDA_TAG_END\]\]/s', $source, $matches_tags );

			if ( ! empty( $matches_tags[1] ) ) {
				foreach ( $matches_tags[1] as $no => $match ) {
					$source = str_replace( $matches_tags[0][$no], '"' . ( $display ? str_replace( '</', '<\/', $matches[2][$no] ) : $matches[2][$no] ) . '"', $source );
				}
			}
		}

		return $source;
	}

	/**
	 * Get allowed iubenda script HTML.
	 *
	 * @return array
	 */
	public function get_allowed_html() {
		// Jetpack fix
		remove_filter( 'pre_kses', array( 'Filter_Embedded_HTML_Objects', 'filter' ), 11 );

		$html = array_merge(
			wp_kses_allowed_html( 'post' ),
			array(
				'script' => array(
					'type' => array(),
					'src' => array(),
					'charset' => array(),
					'async' => array()
				),
				'noscript' => array(),
				'style' => array(
					'type' => array()
				),
				'iframe' => array(
					'src' => array(),
					'height' => array(),
					'width' => array(),
					'frameborder' => array(),
					'allowfullscreen' => array()
				)
			)
		);

		return apply_filters(	'iub_code_allowed_html', $html );
	}

	/**
	 * Load admin options page.
	 *
	 * @return void
	 */
	public function options_page() {
		if ( ! current_user_can( apply_filters( 'iubenda_cookie_law_cap', 'manage_options' ) ) ) {
			wp_die( __( "You don't have permission to access this page.", 'iubenda-cookie-law-solution' ) );
		}
		?>
		<div class="wrap">
			<div id="iubenda-view">
			<?php
			echo '
				<a class="iubenda-link" href="http://iubenda.com" title="iubenda" title="_blank">
					<img id="iubenda-logo" alt="iubenda logo" width="300" height="90" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48c3ZnIHZlcnNpb249IjEuMSIgaWQ9IkxheWVyXzEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4IiB2aWV3Qm94PSIwIDAgNjM3LjggMjgzLjUiIHN0eWxlPSJlbmFibGUtYmFja2dyb3VuZDpuZXcgMCAwIDYzNy44IDI4My41OyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSI+PHN0eWxlIHR5cGU9InRleHQvY3NzIj4uc3Qwe2ZpbGw6IzFDQzY5MTt9LnN0MXtmaWxsOiM1MTUyNTQ7fTwvc3R5bGU+PHBhdGggY2xhc3M9InN0MCIgZD0iTTE3OC45LDk5LjljMCw4LjItMywxNS42LTgsMjEuNGw3LjIsOTUuNGgtNjQuMmw3LTkzLjhjLTUuOS02LTkuNi0xNC4xLTkuNi0yMy4xYzAtMTguMywxNS4xLTMzLjEsMzMuOC0zMy4xUzE3OC45LDgxLjYsMTc4LjksOTkuOSBNMTQ1LjEsMTA3LjRjNC41LDAsOC4xLTMuNiw4LjEtOC4xcy0zLjYtOC04LjEtOGMtNC41LDAtOC4xLDMuNi04LjEsOFMxNDAuNiwxMDcuNCwxNDUuMSwxMDcuNCBNMTUxLjUsMTM2LjJsLTExLjEsMTEuMXY0My4xaDExLjFWMTM2LjJ6Ii8+PHBhdGggY2xhc3M9InN0MSIgZD0iTTI2NS40LDE3OC42Yy0yLjMsMS4yLTQuNywxLjgtNy4yLDEuOGMtMi44LDAtNS4zLTAuOC03LjQtMi40Yy0yLjEtMS42LTMuNS0zLjctNC4zLTYuMmMtMC44LTIuNS0xLjItNi4xLTEuMi0xMC44di0xOC4yYzAsMCwwLTAuOSwwLTIuOGMwLTAuNSwwLTIuOC0xLjUtMy41Yy0wLjItMC4xLTAuNi0wLjItMS4yLTAuM2MtMC40LDAtMC44LTAuNC0wLjgtMC44bDAtMi40YzAtMC41LDAuNC0wLjksMC44LTAuOWg5LjNjMS4zLDAsMi40LDEuMSwyLjQsMi40VjE0N2wwLDE1LjVjMCw0LjYsMC44LDcuNywyLjUsOS4xYzEuNiwxLjUsMy42LDIuMiw1LjksMi4yYzEuNiwwLDMuNC0wLjUsNS40LTEuNWMyLTEsNS4zLTIuOCw4LjEtNS42bDAtMi40bDAtMjIuMlYxNDBjMC0wLjUsMC0yLjgtMS40LTMuNWMtMC4yLTAuMS0wLjYtMC4yLTEuMS0wLjNjLTAuNC0wLjEtMC44LTAuNC0wLjgtMC44bDAtMi40YzAtMC41LDAuNC0wLjksMC45LTAuOWg5LjJjMS4zLDAsMi40LDEuMSwyLjQsMi40djhjMCwwLjEsMCwwLjEsMCwwLjJ2MTYuNmMwLDMuOCwwLDcuNCwwLDEwLjZjMCwwLjcsMCwxLjIsMCwxLjJjMCwwLjUtMC4xLDMuMSwxLjQsMy44YzAuMiwwLjEsMC42LDAuMiwxLjIsMC4zYzAuNCwwLDAuOCwwLjQsMC44LDAuOGwwLDIuNGMwLDAuNS0wLjQsMC44LTAuOSwwLjloLTkuMmMtMS4zLDAtMi40LTEuMS0yLjQtMi40bDAtNC41bDAtMi41QzI3MS45LDE3NC41LDI2Ny43LDE3Ny40LDI2NS40LDE3OC42Ii8+PHBhdGggY2xhc3M9InN0MSIgZD0iTTMwOS4yLDE3NmMxLjksMC45LDMuOSwxLjMsNiwxLjNjMy4yLDAsNi4zLTEuNyw5LTUuMmMyLjgtMy41LDQuMi04LjUsNC4yLTE1LjJjMC02LjEtMS40LTEwLjgtNC4yLTE0LjFjLTIuOC0zLjMtNi00LjktOS41LTQuOWMtMS45LDAtMy44LDAuNS01LjcsMS40Yy0xLjIsMC42LTIuOCwxLjgtNC43LDMuNGMtMC41LDAuNS0wLjgsMS4xLTAuOCwxLjhWMTcxYzAsMC43LDAuMywxLjQsMC44LDEuOEMzMDYsMTc0LjIsMzA3LjYsMTc1LjMsMzA5LjIsMTc2IE0yOTQuOCwxMThjMC0wLjUsMC0yLjctMS40LTMuNGMtMC4yLTAuMS0wLjMtMC4xLTAuNi0wLjJjLTAuNC0wLjEtMC42LTAuNC0wLjYtMC44bDAtMi4zYzAtMC41LDAuNC0wLjgsMC45LTAuOWgxLjhoNi41YzEuMywwLDIuMywxLDIuMywyLjNsMCwxMnYxNS40YzQuNy02LjQsOS44LTkuNiwxNS4zLTkuNmM1LDAsOS40LDIuMSwxMy4xLDYuM2MzLjcsNC4yLDUuNiw5LjksNS42LDE3LjJjMCw4LjUtMi45LDE1LjMtOC42LDIwLjVjLTQuOSw0LjQtMTAuNSw2LjctMTYuNSw2LjdjLTIuOCwwLTUuNy0wLjUtOC43LTEuNWMtMi41LTAuOS01LjEtMi4xLTcuNy0zLjdjLTAuOC0wLjUtMS4zLTEuNC0xLjMtMi40di00NmMwLTAuOSwwLTIsMC0zLjNMMjk0LjgsMTE4eiIvPjxwYXRoIGNsYXNzPSJzdDEiIGQ9Ik0zODkuMywxNzkuMWMtMC41LDAtMC45LTAuNC0wLjktMC45bDAtMi4zYzAtMC40LDAuMy0wLjgsMC43LTAuOGMwLjUtMC4xLDAuOC0wLjEsMS0wLjJjMS40LTAuNywxLjQtMi45LDEuNC0zLjR2LTIuN2MwLDAsMC01LjgsMC0xNy41YzAtMS45LDAtMy44LDAuMS01LjVsMC00LjZjMC0wLjUsMC0xLDAtMS41YzAtMC42LDAtMi44LTEuNC0zLjVjLTAuMS0wLjEtMC4zLTAuMS0wLjUtMC4yYy0wLjQtMC4xLTAuNi0wLjQtMC42LTAuOGwwLTIuM2MwLTAuNSwwLjQtMC44LDAuOS0wLjloOC4xYzEuMywwLDIuMywxLDIuMywyLjN2My40YzAsMCwwLDAuNiwwLDEuOGMwLDAuMywwLjIsMC42LDAuNiwwLjZjMC4yLDAsMC4zLTAuMSwwLjQtMC4yYzUuNC01LjksMTIuMy04LjgsMTcuMi04LjhjMi43LDAsNSwwLjYsNi45LDEuOWMxLjksMS4zLDMuNCwzLjQsNC42LDYuM2MwLjgsMi4xLDEuMiw1LjIsMS4yLDkuNHYxNi41bDAsNi4zYzAsMC41LDAsMi43LDEuNCwzLjRjMC4yLDAuMSwwLjQsMC4yLDAuOCwwLjJjMC40LDAuMSwwLjcsMC40LDAuNywwLjhsMCwyLjNjMCwwLjUtMC40LDAuOC0wLjgsMC45Yy0wLjgsMC0xLjUsMC0yLDBoLTYuNWMtMC4xLDAtMC4zLDAtMC40LDBoLTMuMWMtMS4xLDAtMi0wLjktMi0yYzAtMSwwLjctMS45LDEuNi0yLjJjMC4xLDAsMC4xLDAsMC4yLTAuMWMxLTAuNSwxLjMtMS44LDEuNC0yLjd2LTcuNGwwLTE1LjJjMC00LjMtMC42LTcuNC0xLjctOS4zYy0xLjItMS45LTMuMS0yLjktNS44LTIuOWMtNC4yLDAtMTAuMywyLjItMTQuNCw2Ljd2MjAuOHYwLjNsMCw2LjRjMCwwLjUsMCwyLjgsMS40LDMuNWMwLjEsMCwwLjEsMCwwLjIsMC4xYzEsMC4zLDEuNiwxLjIsMS42LDIuMmMwLDEuMS0wLjksMi0yLDJIMzk0QzM5NCwxNzkuMSwzOTIuNCwxNzkuMSwzODkuMywxNzkuMSIvPjxwYXRoIGNsYXNzPSJzdDEiIGQ9Ik01MTUsMTUzLjRjLTYuNiwxLjMtMTAuNCwyLjItMTEuNCwyLjVjLTMuNywxLjItNi42LDIuNi02LjgsOC43Yy0wLjEsMi41LDAuOCw0LjcsMi4zLDYuM2MxLjUsMS43LDMuMywyLjUsNS4zLDIuNWMyLjUsMCw1LjctMS44LDkuNi00LjhjMC43LTAuNSwxLjEtMS4zLDEuMS0yLjJWMTUzLjR6IE01MjMuOCwxNzAuOGMwLDAuMSwwLDAuMiwwLDAuMmMwLDAuMiwwLDAuMywwLDAuNGMwLjEsMS40LDAuNiwyLjQsMS40LDIuOGMwLjIsMC4xLDAuNCwwLjIsMC42LDAuMmMwLjQsMC4xLDAuNywwLjQsMC43LDAuOGwwLDIuNGMwLDAuNS0wLjQsMC44LTAuOSwwLjloLTguM2MtMS4zLDAtMi4zLTEtMi4zLTIuM3YtNC41Yy01LDMuOS04LjIsNi4xLTkuNSw2LjdjLTEuOSwwLjktNCwxLjMtNi4xLDEuM2MtMy40LDAtNi4yLTEuMi04LjQtMy40Yy0yLjItMi4zLTMuMy01LjMtMy4zLTkuMWMwLTIuNCwwLjUtNC40LDEuNi02LjJjMS41LTIuNCwzLjctNS4yLDcuNi02LjhjNC0xLjYsNy4zLTIsMTgtNC40YzAtMC4zLDAtNCwwLTQuM2MwLTQuNS0wLjgtOS4yLTguNC04LjdjLTUuMywwLjMtMTAuMiwyLTE0LjcsNWMtMC40LDAuMy0wLjksMC4yLTEuMi0wLjJjLTAuMS0wLjEtMC4xLTAuMy0wLjEtMC41di0yLjhjMC0xLjUsMC4zLTIuNCwwLjktMi45YzMuMS0yLjUsMTAuOS01LjUsMTguNS01YzguNCwwLjYsMTEuNCw0LDEyLjksOS4zYzAuNSwxLjYsMSwyLjgsMSw3LjV2MTQuNWMwLDIsMCw0LDAsNS44VjE3MC44eiIvPjxwYXRoIGNsYXNzPSJzdDEiIGQ9Ik00NjUuNCwxMzcuOGMtMS43LTEtMy40LTEuNS01LTEuNWMtMywwLTUuOCwxLjMtOC4zLDMuOGMtMi43LDIuOC00LjYsNi41LTQuNiwxMy41YzAsNywxLjUsMTIuNCw0LjYsMTYuMmMzLDMuNyw2LjQsNS42LDEwLjIsNS42YzIuNywwLDUuMy0xLjEsNy44LTMuNGMwLjktMC44LDEuNC0xLjksMS40LTN2LTIwLjhDNDcxLjQsMTQzLjksNDY5LjEsMTQwLDQ2NS40LDEzNy44IE00ODEuMywxNzQuNmMwLjEsMC4xLDAuMywwLjEsMC41LDAuMmMwLjQsMC4xLDAuNiwwLjQsMC42LDAuOGwwLDIuM2MwLDAuNS0wLjQsMC44LTAuOSwwLjloLThjLTEuMiwwLTIuMy0xLTIuMy0yLjJ2LTIuMmMtMi4zLDIuNC00LjYsNC4yLTYuOCw1LjNjLTIuMiwxLjEtNC42LDEuNi03LjEsMS42Yy01LjIsMC05LjctMi4yLTEzLjYtNi42Yy0zLjktNC40LTUuOC0xMC01LjgtMTYuOXMyLjEtMTMuMSw2LjQtMTguOGM0LjMtNS43LDkuOC04LjUsMTYuNS04LjVjNC4yLDAsNy42LDEuMywxMC40LDR2LTE2LjdjMC0wLjUsMC0yLjctMS40LTMuNGMtMC4xLTAuMS0wLjMtMC4xLTAuNS0wLjJjLTAuNC0wLjEtMC42LTAuNC0wLjYtMC44bDAtMi40YzAtMC41LDAuNC0wLjksMC45LTAuOWg3LjljMS4yLDAsMi4zLDEsMi4zLDIuMnY1OC45QzQ3OS45LDE3MS44LDQ3OS45LDE3My45LDQ4MS4zLDE3NC42Ii8+PHBhdGggY2xhc3M9InN0MSIgZD0iTTM1MCwxNTAuMmMwLDAuMSwwLDAuMywwLjEsMC4zYzAuMSwwLjEsMC4yLDAuMSwwLjQsMC4xaDIzLjljMC4zLDAsMC41LTAuMiwwLjUtMC40YzAuMy0yLjUsMC40LTctMy4zLTEwLjhjLTEuOC0xLjktNC4xLTIuOC04LjMtMi44QzM1NS43LDEzNi42LDM1MC44LDE0My45LDM1MCwxNTAuMiBNMzQ3LjUsMTc0LjljLTMuOS00LjMtNS45LTExLjMtNS45LTE4LjJjMC03LjMsMi4zLTEzLjksNi40LTE4LjdjNC4zLTUsMTAuMi03LjcsMTcuMS03LjdjNS44LDAsMTAuMiwyLDEzLjIsNS44YzIuNywzLjYsNC4xLDguOCw0LjEsMTUuNGMwLDAuOCwwLDEuOS0wLjEsMy4zYy0wLjEsMC45LTAuOCwxLjYtMS43LDEuNmgtMjkuOGMtMC4xLDAtMC4zLDAuMS0wLjQsMC4xYy0wLjEsMC4xLTAuMSwwLjItMC4xLDAuNGMwLjEsNS40LDEuOCwxMCw1LDEzLjJjMy4xLDMuMiw3LjQsNC45LDEyLjQsNC45YzMuNywwLDguNi0xLDEyLjQtMi4xYzAuNS0wLjIsMS4xLDAuMSwxLjMsMC43YzAuMSwwLjIsMC4xLDAuNCwwLDAuNWwtMC41LDEuOWMtMC4yLDAuNy0wLjcsMS4zLTEuMywxLjZjLTMuNywxLjgtOS4zLDMuNS0xNSwzLjVDMzU3LjYsMTgxLjIsMzUxLjYsMTc5LjUsMzQ3LjUsMTc0LjkiLz48cGF0aCBjbGFzcz0ic3QxIiBkPSJNMjMxLjEsMTM1Ljl2MzYuOWMwLDEsMC42LDEuOSwxLjQsMi4zYzAuOSwwLjQsMS40LDEuMywxLjQsMi4zdjAuN2MwLDAuOC0wLjYsMS40LTEuNCwxLjRIMjIwYy0wLjgsMC0xLjQtMC42LTEuNC0xLjR2LTAuN2MwLTEsMC42LTEuOSwxLjQtMi4zYzAuOS0wLjQsMS40LTEuMywxLjQtMi4zdi0zNS43YzAtMC41LTAuNC0wLjktMC45LTAuOXMtMC45LTAuNC0wLjktMC45di0xLjJjMC0xLjIsMC45LTIuMSwyLjEtMi4xaDUuNkMyMjkuNCwxMzIsMjMxLjEsMTMzLjcsMjMxLjEsMTM1LjkiLz48cGF0aCBjbGFzcz0ic3QxIiBkPSJNMjI2LjMsMTEwLjhjMy4zLDAsNiwyLjcsNiw1LjljMCwzLjMtMi43LDUuOS02LDUuOXMtNi0yLjctNi01LjlDMjIwLjMsMTEzLjUsMjIzLDExMC44LDIyNi4zLDExMC44Ii8+PC9zdmc+"/>
				</a>
				<p class="iubenda-text">
					' . __( "This plugin is the easiest and most comprehensive way to adapt your WordPress site to the European cookie law. Upon your user's first visit, the plugin will take care of collecting their consent, of blocking the most popular among the scripts that install cookies and subsequently reactivate these scripts as soon as consent is provided. The basic settings include obtaining consent by a simple scroll action (the most effective method) and script reactivation without refreshing the page.", 'iubenda-cookie-law-solution' ) . '
				</p>
				<p class="iubenda-text">
					<span class="iubenda-title">' . __( "Would you like to know more about the cookie law?", 'iubenda-cookie-law-solution' ) . '</span><br />
					' . sprintf( __( "Read our <a href=\"%s\" class=\"iubenda-url\" target=\"_blank\">complete guide to the cookie law.</a>", 'iubenda-cookie-law-solution' ), $this->links['guide'] ) . '
				</p>
				<p class="iubenda-text">
					<span class="iubenda-title">' . __( "What is the full functionality of the plugin?", 'iubenda-cookie-law-solution' ) . '</span><br />
					' . sprintf( __( "Visit our <a href=\"%s\" class=\"iubenda-url\" target=\"_blank\">plugin page.</a>", 'iubenda-cookie-law-solution' ), $this->links['plugin_page'] ) . '
				</p>
				<p class="iubenda-text">
					<span class="iubenda-title">' . __( "Enter the iubenda code for the Cookie Solution below.", 'iubenda-cookie-law-solution' ) . '</span><br />
					' . sprintf( __( "In order to run the plugin, you need to enter the iubenda code that activates the cookie law banner and the cookie policy in the form below. This code can be generated on www.iubenda.com, following <a href=\"%s\" class=\"iubenda-url\" target=\"_blank\">this guide.</a>", 'iubenda-cookie-law-solution' ), $this->links['generating_code'] ) . '
				</p>';
		?>
				<form id="iubenda-tabs" action="options.php" method="post">
				<?php
				settings_fields( 'iubenda_cookie_law_solution' );
				do_settings_sections( 'iubenda_cookie_law_solution' );

				echo '	<p class="submit">';
				submit_button( '', 'primary', 'save_iubenda_options', false );
				echo ' ';
				submit_button( __( 'Reset to defaults', 'iubenda-cookie-law-solution' ), 'secondary', 'reset_iubenda_options', false );
				echo '	</p>';
				?>
				</form>
					<?php echo '
				<p class="iubenda-text">
					<span class="iubenda-title">' . __( 'Need support for this plugin?', 'iubenda-cookie-law-solution' ) . '</span><br />
					' . sprintf( __( "Visit our <a href=\"%s\" class=\"iubenda-url\" target=\"_blank\">support forum.</a>", 'iubenda-cookie-law-solution' ), $this->links['support_forum'] ) . '
				</p>
				<p class="iubenda-text">
					<span class="iubenda-title">' . __( 'Want to try a beta version of this plugin with the latest features?', 'iubenda-cookie-law-solution' ) . '</span><br />
					' . sprintf( __( "Visit our <a href=\"%s\" class=\"iubenda-url\" target=\"_blank\">documentation pages</a> and follow the instructions to install a beta version.", 'iubenda-cookie-law-solution' ), $this->links['documentation'] ) . '
				</p>';
					?>
			</div>
			<div class="clear"></div>
		</div>
		<?php
	}

}
