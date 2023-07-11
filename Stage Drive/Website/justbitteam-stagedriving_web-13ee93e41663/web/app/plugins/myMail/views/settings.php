<?php
		global $wpdb, $mymail_options, $current_user, $wp_post_statuses, $wp_roles, $locale;

		if(!$mymail_options) :
		?>
			<div class="wrap">

			<h2>Ooops, seems your settings are missing or broken :(</h2>

			<p><a href="options-general.php?page=newsletter-settings&reset-settings=1&_wpnonce=<?php echo wp_create_nonce('mymail-reset-settings') ?>" class="button button-primary button-large">Reset all settings now</a></p>
			</div>

		<?php wp_die();

		endif;

	$customfields = mymail()->get_custom_fields();
	$roles = $wp_roles->get_names();
	$translations = get_transient( '_mymail_translation' );

?>
<form method="post" action="options.php" autocomplete="off" enctype="multipart/form-data">
<input style="display:none"><input type="password" style="display:none">
<div class="wrap">
	<p class="alignright">
		<input type="submit" class="submit-form button-primary" value="<?php _e('Save Changes' ,'mymail') ?>" disabled />
	</p>
<div class="icon32" id="icon-options-general"><br></div>
<h2><?php _e('Newsletter Settings', 'mymail')?></h2>
<?php

	$active = count(mymail_get_active_campaigns());

	$templatefiles = mymail('templates')->get_files(mymail_option('default_template'));
	$timeformat = get_option('date_format').' '.get_option('time_format');
	$timeoffset = mymail('helper')->gmt_offset(true);

	if($active) echo '<div class="error inline"><p>'.sprintf(_n('%d campaign is active. You should pause it before you change the settings!', '%d campaigns are active. You should pause them before you change the settings!', $active, 'mymail'), $active).'</p></div>';

?>
	<?php wp_nonce_field( 'mymail_nonce', 'mymail_nonce', false ); ?>
	<?php settings_fields( 'newsletter_settings' ); ?>
	<?php do_settings_sections( 'newsletter_settings' ); ?>

	<?php
	$sections = apply_filters('mymail_setting_sections', array(
		'general' => __('General' ,'mymail'),
		'frontend' => __('Frontend' ,'mymail'),
		'subscribers' => __('Subscribers' ,'mymail'),
		'wordpress-users' => __('WordPress Users' ,'mymail'),
		'texts' => __('Texts' ,'mymail').($translations ? ' <span class="update-translation-available wp-ui-highlight" title="'.__('update available', 'mymail').'"><span>!</span></span>' : ''),
		'tags' => __('Tags' ,'mymail'),
		'delivery' => __('Delivery' ,'mymail'),
		'cron' => __('Cron' ,'mymail'),
		'capabilities' => __('Capabilities' ,'mymail'),
		'bounce' => __('Bouncing' ,'mymail'),
		'authentication' => __('Authentication' ,'mymail'),
		'purchasecode' => __('Purchasecode' ,'mymail'),
		'advanced' => __('Advanced' ,'mymail'),
		'system_info' => __('System Info' ,'mymail'),
	));

	if(!current_user_can('mymail_manage_capabilities') && !current_user_can('manage_options')) unset($sections['capabilities']);
	if(get_option('mymail_purchasecode_disabled') && mymail_option('purchasecode')) unset($sections['purchasecode']);

	?>
	<div class="settings-wrap">
	<div class="settings-nav">
		<div class="mainnav contextual-help-tabs hide-if-no-js">
		<ul>
		<?php
			foreach($sections as $id => $name){
		?>
			<li><a href="#<?php echo $id; ?>" class="nav-<?php echo $id; ?>"><?php echo $name; ?></a></li>
		<?php }?>
			<?php do_action('mymail_settings_tabs') ?>
		</ul>
		</div>
	</div>

	<div class="settings-tabs"> <div class="tab"><h3>&nbsp;</h3></div>

	<div id="tab-general" class="tab">
		<h3><?php _e('General', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_general') ?>

	<table class="form-table">

		<tr valign="top">
			<th scope="row"><?php _e('From Name' ,'mymail') ?> *</th>
			<td><input type="text" name="mymail_options[from_name]" value="<?php echo esc_attr(mymail_option('from_name')); ?>" class="regular-text"> <span class="description"><?php _e('The sender name which is displayed in the from field' ,'mymail') ?></span></td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('From Address' ,'mymail') ?> *</th>
			<td><input type="text" name="mymail_options[from]" value="<?php echo esc_attr(mymail_option('from')); ?>" class="regular-text"> <span class="description"><?php _e('The sender email address. Force your recievers to whitelabel this email address.' ,'mymail') ?></span></td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Reply To Address' ,'mymail') ?> *</th>
			<td><input type="text" name="mymail_options[reply_to]" value="<?php echo esc_attr(mymail_option('reply_to')); ?>" class="regular-text"> <span class="description"><?php _e('The address users can reply to' ,'mymail') ?></span></td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Default Template' ,'mymail') ?> *</th>
			<td><p><select name="mymail_options[default_template]" class="postform">
			<?php
				$templates = mymail('templates')->get_templates();
				$selected = mymail_option('default_template');
				foreach($templates as $slug => $data){
			?>
				<option value="<?php echo $slug ?>"<?php if($slug == $selected) echo " selected";?>><?php echo esc_attr($data['name'])?></option>
			<?php
				}
			?>
			</select> <a href="edit.php?post_type=newsletter&page=mymail_templates"><?php _e('show Templates', 'mymail'); ?></a> | <a href="edit.php?post_type=newsletter&page=mymail_templates&more"><?php _e('get more' ,'mymail') ?></a>
			</p></td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Send delay' ,'mymail') ?> *</th>
			<td><input type="text" name="mymail_options[send_offset]" value="<?php echo esc_attr(mymail_option('send_offset')); ?>" class="small-text"> <span class="description"><?php _e('The default delay in minutes for sending campaigns.' ,'mymail') ?></span></td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Delivery by Time Zone' ,'mymail') ?> *</th>
			<td><input type="checkbox" name="mymail_options[timezone]" value="1" <?php checked(mymail_option('timezone')); ?>> <?php _e('Send Campaigns based on the subscribers timezone if known' ,'mymail') ?>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Embed Images' ,'mymail') ?> *</th>
			<td><label><input type="checkbox" name="mymail_options[embed_images]" value="1" <?php checked(mymail_option('embed_images')); ?>> <?php _e('Embed images in the mail' ,'mymail') ?></label>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Module Thumbnails' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[module_thumbnails]" value="1" <?php checked(mymail_option('module_thumbnails')); ?>> <?php _e('Show thumbails of modules in the editor if available' ,'mymail') ?></label>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Post List Count' ,'mymail') ?></th>
			<td><input type="text" name="mymail_options[post_count]" value="<?php echo esc_attr(mymail_option('post_count')); ?>" class="small-text"> <span class="description"><?php _e('Number of posts or images displayed at once in the editbar.' ,'mymail') ?></span></td>
		</tr>
		<?php if(function_exists('find_core_auto_update')) : //only 3.7+ ?>
		<tr valign="top">
			<th scope="row"><?php _e('Auto Update' ,'mymail') ?></th>
			<td>
			<?php
			$is = mymail_option('autoupdate', 'minor');
			$types = array(
				'1' => __('enabled', 'mymail'),
				'0' => __('disabled', 'mymail'),
				'minor' => __('only minor updates', 'mymail'),
			);
			?>
			<select name="mymail_options[autoupdate]">
				<?php foreach( $types as $value => $name ){ ?>
				<option value="<?php echo $value; ?>" <?php selected($is == $value)?>><?php echo $name; ?></option>
				<?php }?>
			</select>
			<p class="description"><?php _e('auto updates are recommended for important fixes.', 'mymail'); ?></p>
			</td>
		</tr>
		<?php endif; ?>
		<tr valign="top">
			<th scope="row"><?php _e('System Mails' ,'mymail') ?>
			<p class="description"><?php _e( 'decide how MyMail uses the wp_mail function', 'mymail' ); ?></p></th>
			<td>
			<p><label><input type="radio" name="mymail_options[system_mail]" class="system_mail" value="0" <?php checked(!mymail_option('system_mail')) ?>> <?php _e('Do not use MyMail for outgoing WordPress mails' ,'mymail') ?></label></p>
			<p><label><input type="radio" name="mymail_options[system_mail]" class="system_mail" value="1" <?php checked(mymail_option('system_mail') == 1) ?>> <?php _e('Use MyMail for all outgoing WordPress mails' ,'mymail') ?></label><br>
				<label><input type="radio" name="mymail_options[system_mail]" class="system_mail" value="template" <?php checked(mymail_option('system_mail') == 'template') ?>> <?php _e('Use only the template for all outgoing WordPress mails' ,'mymail') ?></label></p>
			<p>&nbsp;&nbsp;<?php _e('use', 'mymail'); ?><select name="mymail_options[system_mail_template]" class="system_mail_template" <?php echo (!mymail_option('system_mail') ? 'disabled' : '') ?>>
			<?php
				$selected = mymail_option('system_mail_template', 'notification.html');
				foreach($templatefiles as $slug => $filedata){
					if($slug == 'index.html') continue;
			?>
				<option value="<?php echo $slug ?>"<?php selected($slug == $selected) ?>><?php echo esc_attr($filedata['label'])?> (<?php echo $slug ?>)</option>
			<?php
				}
			?>
			</select></p>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('CharSet' ,'mymail') ?> / <?php _e('Encoding' ,'mymail') ?></th>
			<td>
			<?php
			$is = mymail_option('charset', 'UTF-8');
			$charsets = array(
				'UTF-8' => 'Unicode 8',
				'ISO-8859-1' => 'Western European',
				'ISO-8859-2' => 'Central European',
				'ISO-8859-3' => 'South European',
				'ISO-8859-4' => 'North European',
				'ISO-8859-5' => 'Latin/Cyrillic',
				'ISO-8859-6' => 'Latin/Arabic',
				'ISO-8859-7' => 'Latin/Greek',
				'ISO-8859-8' => 'Latin/Hebrew',
				'ISO-8859-9' => 'Turkish',
				'ISO-8859-10' => 'Nordic',
				'ISO-8859-11' => 'Latin/Thai',
				'ISO-8859-13' => 'Baltic Rim',
				'ISO-8859-14' => 'Celtic',
				'ISO-8859-15' => 'Western European revision',
				'ISO-8859-16' => 'South-Eastern European',
			)?>
			<select name="mymail_options[charset]">
				<?php foreach( $charsets as $code => $region ){ ?>
				<option value="<?php echo $code; ?>" <?php selected($is == $code)?>><?php echo $code; ?> - <?php echo $region; ?></option>
				<?php }?>
			</select>
			<?php
			$is = mymail_option('encoding', '8bit');
			$encoding = array(
				'8bit' => '8bit',
				'7bit' => '7bit',
				'binary' => 'binary',
				'base64' => 'base64',
				'quoted-printable' => 'quoted-printable',
			)?> /
			<select name="mymail_options[encoding]">
				<?php foreach( $encoding as $code ){ ?>
				<option value="<?php echo $code; ?>" <?php selected($is == $code)?>><?php echo $code; ?></option>
				<?php }?>
			</select>
			<p class="description"><?php _e('change Charset and encoding of your mails if you have problems with some characters', 'mymail'); ?></p>
			</td>
		</tr>
		<?php
			$geoip = mymail_option('trackcountries');
			$geoipcity = mymail_option('trackcities');
			if(isset($_GET['nogeo'])) $geoip = $geoipcity = false;
		?>
		<tr valign="top">
			<th scope="row"><?php _e('Track Geolocation' ,'mymail') ?>
			<div class="loading geo-ajax-loading"></div></th>
			<td>
			<p><label><input type="checkbox" id="mymail_geoip" name="mymail_options[trackcountries]" value="1" <?php checked($geoip); ?>> <?php _e('Track Countries in Campaigns' ,'mymail') ?></label></p>
			<p><button id="load_country_db" class="button-primary" data-type="country" <?php disabled(!$geoip); ?>><?php (is_file(mymail_option('countries_db'))) ? _e('Update Country Database', 'mymail') : _e('Load Country Database', 'mymail'); ?></button> <?php _e('or', 'mymail'); ?> <a id="upload_country_db_btn" href="#"><?php _e('upload file', 'mymail'); ?></a>
			</p>
			<p id="upload_country_db" class="hidden">
				<input type="file" name="country_db_file"> <input type="submit" class="button" value="<?php _e('Upload' ,'mymail') ?>" />
				<br><span class="description"><?php _e('upload the GeoIPv6.dat you can find in the package here:', 'mymail'); ?> <a href="http://geolite.maxmind.com/download/geoip/database/GeoIPv6.dat.gz">http://geolite.maxmind.com/download/geoip/database/GeoIPv6.dat.gz</a></span>
			</p>

			<input id="country_db_path" type="text" name="mymail_options[countries_db]" class="widefat" value="<?php echo mymail_option('countries_db') ?>" placeholder="<?php echo MYMAIL_UPLOAD_DIR.'/GeoIPv6.dat'?>">
			<p><label><input type="checkbox" id="mymail_geoipcity" name="mymail_options[trackcities]" value="1" <?php checked($geoipcity); ?><?php disabled(!$geoip); ?>> <?php _e('Track Cities in Campaigns' ,'mymail') ?></label></p>
			<p><button id="load_city_db" class="button-primary" data-type="city" <?php disabled(!$geoipcity); ?>><?php (is_file(mymail_option('cities_db'))) ? _e('Update City Database', 'mymail') : _e('Load City Database', 'mymail'); ?></button> <?php _e('or', 'mymail'); ?> <a id="upload_city_db_btn" href="#"><?php _e('upload file', 'mymail'); ?></a>
			</p>
			<p id="upload_city_db" class="hidden">
				<input type="file" name="city_db_file"> <input type="submit" class="button" value="<?php _e('Upload' ,'mymail') ?>" />
				<br><span class="description"><?php _e('upload the GeoLiteCity.dat you can find in the package here:', 'mymail'); ?> <a href="http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz">http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz</a></span>
			</p>
			<p class="description"><?php _e('The city DB is about 12 MB. It can take a while to load it', 'mymail'); ?></p>
			<input id="city_db_path" type="text" name="mymail_options[cities_db]" class="widefat" value="<?php echo mymail_option('cities_db') ?>" placeholder="<?php echo MYMAIL_UPLOAD_DIR.'/GeoIPCity.dat'?>">

			</td>
		</tr>
		<?php if($geoip && is_file(mymail_option('countries_db'))) : ?>
		<tr valign="top">
			<th scope="row"></th>
			<td>
			<p class="description"><?php _e('If you don\'t find your country down below the geo database is missing or corrupt', 'mymail') ?></p>
			<p>
			<strong><?php _e('Your IP', 'mymail') ?>:</strong> <?php echo mymail_get_ip()?><br>
			<strong><?php _e('Your country', 'mymail') ?>:</strong> <?php echo mymail_ip2Country('', 'name')?><br>&nbsp;&nbsp;<strong><?php _e('Last update', 'mymail') ?>: <?php echo date(get_option('date_format').' '.get_option('time_format'), filemtime(mymail_option('countries_db'))+$timeoffset)?> </strong><br>
		<?php if($geoipcity && is_file(mymail_option('cities_db'))) : ?>
			<strong><?php _e('Your city', 'mymail') ?>:</strong> <?php echo mymail_ip2City('', 'city')?><br>&nbsp;&nbsp;<strong><?php _e('Last update', 'mymail') ?>: <?php echo date(get_option('date_format').' '.get_option('time_format'), filemtime(mymail_option('cities_db'))+$timeoffset)?></strong>
		<?php endif; ?>
			</p>
			<p class="description">This product includes GeoLite data created by MaxMind, available from <a href="http://www.maxmind.com">http://www.maxmind.com</a></p>
			</td>
		</tr>
		<?php
			endif;
		?>
	</table>
	<input type="hidden" name="mymail_options[countries]" value="<?php echo mymail_option('countries') ?>">
	<input type="hidden" name="mymail_options[cities]" value="<?php echo mymail_option('cities') ?>">
	<p class="description">* <?php _e('can be changed in each campaign' ,'mymail') ?></p>
		<?php if (get_bloginfo('language') != 'en-US' && 'translator string' !== strip_tags(_x( 'translator string', 'Translators: put your personal info here to display it on the settings page. Leave blank for no info', 'mymail' ), '<a>' ) ) : ?>
	<p class="alignright tiny"><?php echo sprintf(__('This plugin has been translated by %s', 'mymail'), _x( 'translator string', 'Translators: put your personal info here to display it on the settings page. Leave blank for no info', 'mymail' )); ?><div class="clearfix"></div></p>
		<?php endif; ?>
	</div>

	<div id="tab-frontend" class="tab">
		<h3><?php _e('Frontend', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_frontend') ?>

	<table class="form-table">

		<tr valign="top">
			<th scope="row"><?php _e('Newsletter Homepage' ,'mymail') ?></th>
			<td><select name="mymail_options[homepage]" class="postform">
				<option value="0"><?php _e('Choose' ,'mymail') ?></option>
			<?php
				$pages = get_pages(array('post_status' => 'publish,private,draft'));
				$newsletter_homepage = mymail_option('homepage');
				foreach($pages as $page){
			?>
				<option value="<?php echo $page->ID ?>"<?php if($page->ID == $newsletter_homepage) echo " selected";?>><?php echo esc_attr($page->post_title); if($page->post_status != 'publish') echo ' ('.$wp_post_statuses[$page->post_status]->label.')'; ?></option>
			<?php
				}
			?>
			</select>
			<?php if($newsletter_homepage) : ?>
			<span class="description">
				<a href="post.php?post=<?php echo $newsletter_homepage; ?>&action=edit"><?php _e('edit', 'mymail'); ?></a>
				<?php _e('or', 'mymail') ?>
				<a href="<?php echo get_permalink($newsletter_homepage); ?>" class="external"><?php _e('visit', 'mymail'); ?></a>

				</span>
			<?php else : ?>
			<span class="description"><a href="?mymail_create_homepage=1"><?php _e('create it right now', 'mymail'); ?></a></span>
			<?php endif; ?>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Search Engine Visibility' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[frontpage_public]" value="1" <?php checked(mymail_option('frontpage_public')); ?>> <?php _e('Discourage search engines from indexing your campaigns' ,'mymail') ?></label>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Pagination' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[frontpage_pagination]" value="1" <?php checked(mymail_option('frontpage_pagination')); ?>> <?php _e('Allow users to view the next/last newsletters' ,'mymail') ?></label>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Share Button' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[share_button]" value="1" <?php checked(mymail_option('share_button')) ?>> <?php _e('Offer share option for your customers' ,'mymail') ?></label>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Services' ,'mymail') ?></th>
			<td><ul class="social_services"><?php

				$social_services = mymail('helper')->social_services();

				$services = mymail_option('share_services', array());

				foreach($social_services as $service => $data){
			?>
				<li class="<?php echo $service ?>"><label><input type="checkbox" name="mymail_options[share_services][]" value="<?php echo $service ?>" <?php checked(in_array($service, $services)); ?>> <?php echo $data['name']; ?></label></li>
			<?php
				}
			?>
			</ul></td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Campaign slug' ,'mymail') ?></th>
			<td><p>
			<?php if(mymail('helper')->using_permalinks()) : ?>
			<span class="description"><?php echo get_bloginfo('url') ?>/</span><input type="text" name="mymail_options[slug]" value="<?php echo esc_attr(mymail_option('slug', 'newsletter')); ?>" class="small-text" style="width:80px"><span class="description">/my-campaign</span><br><span class="description"><?php _e('changing the slug may cause broken links in previous sent campaigns!' ,'mymail') ?></span>
			<?php else :?>
			<span class="description"><?php echo sprintf(_x('Define a %s to enable custom slugs', 'Campaign slug', 'mymail'), '<a href="options-permalink.php">'.__('Permalink Structure', 'mymail').'</a>') ?></span>
			<input type="hidden" name="mymail_options[slug]" value="<?php echo esc_attr(mymail_option('slug', 'newsletter')); ?>">
			<?php endif;?>
			</p>
			</td>
		</tr>
		<?php
			$slugs = mymail_option('slugs', array(
				'confirm' => 'confirm',
				'subscribe' => 'subscribe',
				'unsubscribe' => 'unsubscribe',
				'profile' => 'profile'
			));

		if(mymail('helper')->using_permalinks() && mymail_option('homepage')) :
			$homepage = get_permalink( mymail_option('homepage') );
		?>
		<tr valign="top">
			<th scope="row"><?php _e('Homepage slugs' ,'mymail') ?></th>
			<td class="homepage-slugs">
			<p>
			<label><?php _e('Confirm Slug', 'mymail') ?>:</label><br>
				<span>
					<?php echo $homepage ?><strong><?php echo $slugs['confirm'] ?></strong>/
					<a class="button button-small hide-if-no-js edit-slug"><?php echo __('Edit', 'mymail') ?></a>
				</span>
				<span class="edit-slug-area">
				<?php echo $homepage ?><input type="text" name="mymail_options[slugs][confirm]" value="<?php echo esc_attr($slugs['confirm']); ?>" class="small-text">/
				</span>
			</p>
			<p>
			<label><?php _e('Subscribe Slug', 'mymail') ?>:</label><br>
				<span>
					<?php echo $homepage ?><strong><?php echo $slugs['subscribe'] ?></strong>/
					<a class="button button-small hide-if-no-js edit-slug"><?php echo __('Edit', 'mymail') ?></a>
				</span>
				<span class="edit-slug-area">
				<?php echo $homepage ?><input type="text" name="mymail_options[slugs][subscribe]" value="<?php echo esc_attr($slugs['subscribe']); ?>" class="small-text">/
				</span>
			</p>
			<p>
			<label><?php _e('Unsubscribe Slug', 'mymail') ?>:</label><br>
				<span>
					<a href="<?php echo $homepage.esc_attr($slugs['unsubscribe']) ?>" class="external"><?php echo $homepage ?><strong><?php echo $slugs['unsubscribe'] ?></strong>/</a>
					<a class="button button-small hide-if-no-js edit-slug"><?php echo __('Edit', 'mymail') ?></a>
				</span>
				<span class="edit-slug-area">
				<?php echo $homepage ?><input type="text" name="mymail_options[slugs][unsubscribe]" value="<?php echo esc_attr($slugs['unsubscribe']); ?>" class="small-text">/
				</span>
			</p>
			<p>
			<label><?php _e('Profile Slug', 'mymail') ?>:</label><br>
				<span>
					<a href="<?php echo $homepage.esc_attr($slugs['profile']) ?>" class="external"><?php echo $homepage ?><strong><?php echo $slugs['profile'] ?></strong>/</a>
					<a class="button button-small hide-if-no-js edit-slug"><?php echo __('Edit', 'mymail') ?></a>
				</span>
				<span class="edit-slug-area">
				<?php echo $homepage ?><input type="text" name="mymail_options[slugs][profile]" value="<?php echo esc_attr($slugs['profile']); ?>" class="small-text">/
				</span>
			</p>
			</td>
		</tr>
		<?php else : ?>
			<input type="hidden" name="mymail_options[slugs][confirm]" value="<?php echo esc_attr($slugs['confirm']); ?>">
			<input type="hidden" name="mymail_options[slugs][subscribe]" value="<?php echo esc_attr($slugs['subscribe']); ?>">
			<input type="hidden" name="mymail_options[slugs][unsubscribe]" value="<?php echo esc_attr($slugs['unsubscribe']); ?>">
			<input type="hidden" name="mymail_options[slugs][profile]" value="<?php echo esc_attr($slugs['profile']); ?>">

		<?php endif;?>

		<?php if(mymail('helper')->using_permalinks()) : ?>
		<tr valign="top">
			<th scope="row"><?php _e('Archive' ,'mymail') ?></th>
			<td class="homepage-slugs"><p><label><input type="hidden" name="mymail_options[hasarchive]" value="0"><input type="checkbox" name="mymail_options[hasarchive]" class="has-archive-check" value="1" <?php checked(mymail_option('hasarchive')); ?>> <?php _e('enable archive function to display your newsletters in a reverse chronological order' ,'mymail') ?></label>
				</p>
			<div class="archive-slug" <?php if(!mymail_option('hasarchive')) echo ' style="display:none"' ?>>
			<p>
			<label><?php _e('Archive Slug', 'mymail') ?>:</label><br>
				<?php
					$homepage = home_url('/');
					$slug = mymail_option('archive_slug', 'newsletter');
				?>
				<span>
					<a href="<?php echo $homepage.esc_attr($slug) ?>" class="external"><?php echo $homepage ?><strong><?php echo $slug ?></strong>/</a>
					<a class="button button-small hide-if-no-js edit-slug"><?php echo __('Edit', 'mymail') ?></a>
				</span>
				<span class="edit-slug-area">
				<?php echo $homepage ?><input type="text" name="mymail_options[archive_slug]" value="<?php echo esc_attr($slug); ?>" class="small-text">/
				</span>
			</p>
			<p><label><?php _e('show only', 'mymail' ); $archive_types = mymail_option('archive_types', array('finished', 'active')); ?>: </label>
			<label> <input type="checkbox" name="mymail_options[archive_types][]" value="finished" <?php checked( in_array('finished', $archive_types) ); ?>> <?php _e('finished', 'mymail' ); ?> </label>
			<label> <input type="checkbox" name="mymail_options[archive_types][]" value="active" <?php checked( in_array('active', $archive_types) ); ?>> <?php _e('active', 'mymail' ); ?> </label>
			<label> <input type="checkbox" name="mymail_options[archive_types][]" value="paused" <?php checked( in_array('paused', $archive_types) ); ?>> <?php _e('paused', 'mymail' ); ?> </label>
			<label> <input type="checkbox" name="mymail_options[archive_types][]" value="queued" <?php checked( in_array('queued', $archive_types) ); ?>> <?php _e('queued', 'mymail' ); ?> </label>
			</p>
		</div>
			</td>
		</tr>
		<?php else : ?>
			<input type="hidden" name="mymail_options[hasarchive]" value="<?php echo esc_attr(mymail_option('hasarchive')); ?>">
			<input type="hidden" name="mymail_options[archive_slug]" value="<?php echo esc_attr(mymail_option('archive_slug', 'newsletter')); ?>">
		<?php endif;?>

	</table>
	</div>

	<div id="tab-subscribers" class="tab">
		<h3><?php _e('Subscribers', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_subscribers') ?>

	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Notification' ,'mymail') ?></th>
			<td>
			<p><label><input type="checkbox" name="mymail_options[subscriber_notification]" value="1" <?php checked(mymail_option('subscriber_notification')); ?>> <?php _e('Send a notification of new subscribers to following receivers (comma separated)' ,'mymail') ?> <input type="text" name="mymail_options[subscriber_notification_receviers]" value="<?php echo esc_attr(mymail_option('subscriber_notification_receviers')); ?>" class="regular-text"></label>
			<br>&nbsp;&nbsp;<?php _e('use', 'mymail'); ?><select name="mymail_options[subscriber_notification_template]">
			<?php
				$selected = mymail_option('subscriber_notification_template', 'notification.html');
				foreach($templatefiles as $slug => $filedata){
					if($slug == 'index.html') continue;
			?>
				<option value="<?php echo $slug ?>"<?php selected($slug == $selected) ?>><?php echo esc_attr($filedata['label'])?> (<?php echo $slug ?>)</option>
			<?php
				}
			?>
			</select>
			</p><p>
			<label><input type="checkbox" name="mymail_options[unsubscribe_notification]" value="1" <?php checked(mymail_option('unsubscribe_notification')); ?>> <?php _e('Send a notification if subscribers cancel their subscription to following receivers (comma separated)' ,'mymail') ?> <input type="text" name="mymail_options[unsubscribe_notification_receviers]" value="<?php echo esc_attr(mymail_option('unsubscribe_notification_receviers')); ?>" class="regular-text"></label>
			<br>&nbsp;&nbsp;<?php _e('use', 'mymail'); ?><select name="mymail_options[unsubscribe_notification_template]">
			<?php
				$selected = mymail_option('unsubscribe_notification_template', 'notification.html');
				foreach($templatefiles as $slug => $filedata){
					if($slug == 'index.html') continue;
			?>
				<option value="<?php echo $slug ?>"<?php selected($slug == $selected) ?>><?php echo esc_attr($filedata['label'])?> (<?php echo $slug ?>)</option>
			<?php
				}
			?>
			</select>
			</p>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Save Subscriber IP' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[track_users]" value="1" <?php checked(mymail_option('track_users')) ?>> <?php _e('Save IP address and time of new subscribers' ,'mymail') ?></label>
			<p class="description"><?php _e('In some countries it\'s required to save the IP address and the sign up time for legal reasons. Please add a note in your privacy policy if you save users data' ,'mymail') ?></p>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row">Do Not Track</th>
			<td><label><input type="checkbox" name="mymail_options[do_not_track]" value="1" <?php checked(mymail_option('do_not_track')) ?>> <?php _e('Respect users "Do Not Track" option' ,'mymail') ?></label>
			<p class="description"><?php echo sprintf(__('If enabled MyMail will respect users option for not getting tracked. Read more on the %s' ,'mymail'), '<a href="http://donottrack.us/" class="external">'.__('official website', 'mymail').'</a>') ?></p>
			</td>
		</tr>
		<tr>
			<th scope="row"><?php _e('Single-Opt-Out' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[single_opt_out]" value="1" <?php checked(mymail_option('single_opt_out')) ?>> <?php _e('Subscribers instantly signed out after clicking the unsubscribe link in mails' ,'mymail') ?></label>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Custom Fields' ,'mymail') ?>:
				<p class="description"><?php _e('Custom field tags are individual tags for each subscriber. You can ask for them on subscription and/or make it a required field.', 'mymail'); ?></p>
				<p class="description"><?php _e('You have to enable Custom fields for each form:', 'mymail'); ?> <a href="#forms"><?php _e('Forms', 'mymail'); ?></a></p>
			</th>
			<td>
			<input type="hidden" name="mymail_options[custom_field][0]" value="empty">
				<div class="customfields">
			<?php
				if($customfields){
					$types = array(
						'textfield' => __('Textfield', 'mymail'),
						'textarea' => __('Textarea', 'mymail'),
						'dropdown' => __('Dropdown Menu', 'mymail'),
						'radio' => __('Radio Buttons', 'mymail'),
						'checkbox' => __('Checkbox', 'mymail'),
						'date' => __('Date', 'mymail'),
					);
					foreach($customfields as $id => $data){
					 ?>
					<div class="customfield">
					<a class="customfield-move-up" title="<?php _e('move up', 'mymail'); ?>">&#9650;</a>
					<a class="customfield-move-down" title="<?php _e('move down', 'mymail'); ?>">&#9660;</a>
					<div><span class="label"><?php _e('Field Name', 'mymail'); ?>:</span><label><input type="text" name="mymail_options[custom_field][<?php echo $id?>][name]" value="<?php echo esc_attr($data['name'])?>" class="regular-text customfield-name"></label></div>
					<div><span class="label"><?php _e('Tag', 'mymail'); ?>:</span><span><code>{</code><input type="text" name="mymail_options[custom_field][<?php echo $id ?>][id]" value="<?php echo sanitize_key($id); ?>" class="code"><code>}</code></span></div>
					<div><span class="label"><?php _e('Type', 'mymail'); ?>:</span><select class="customfield-type" name="mymail_options[custom_field][<?php echo $id ?>][type]">
					<?php
						foreach($types as $value => $name){
							echo '<option value="'.$value.'" '.selected($data['type'], $value, false).'>'.$name.'</option>';

						}

					?>
					</select></div>
						<ul class="customfield-additional customfield-dropdown customfield-radio" <?php if(in_array($data['type'], array('dropdown', 'radio'))) echo ' style="display:block"';?>>
							<li>
								<ul class="customfield-values">
							<?php
								$values = !empty($data['values']) ? $data['values'] : array('');
								foreach($values as $value){
							?>
								<li><span>&nbsp;</span> <span class="customfield-value-box"><input type="text" name="mymail_options[custom_field][<?php echo $id?>][values][]" class="regular-text customfield-value" value="<?php echo $value; ?>"> <label><input type="radio" name="mymail_options[custom_field][<?php echo $id?>][default]" value="<?php echo $value ?>" title="<?php _e('this field is selected by default', 'mymail'); ?>" <?php if(isset($data['default'])) checked($data['default'], $value)?><?php if(!in_array($data['type'], array('dropdown', 'radio'))) echo ' disabled'?>> <?php _e('default', 'mymail'); ?></label> &nbsp; <a class="customfield-value-remove" title="<?php _e('remove field', 'mymail'); ?>">&#10005;</a></span></li>
							<?php } ?>
								</ul>
							<span>&nbsp;</span> <a class="customfield-value-add"><?php _e('add field', 'mymail'); ?></a>
							</li>
						</ul>
						<div class="customfield-additional customfield-checkbox" <?php if(in_array($data['type'], array('checkbox'))) echo ' style="display:block"';?>>
							<span>&nbsp;</span> <label><input type="checkbox" name="mymail_options[custom_field][<?php echo $id?>][default]" value="1" title="<?php _e('this field is selected by default', 'mymail'); ?>" <?php if(isset($data['default'])) checked($data['default'], true)?> <?php if(!in_array($data['type'], array('checkbox'))) echo ' disabled'?>> <?php _e('checked by default', 'mymail'); ?></label>
						</div>
						<a class="customfield-remove"><?php _e('remove field', 'mymail'); ?></a>
						<br>
					</div>
					 <?php
					}
				}
			?>
				</div>
				<input type="button" value="<?php _e('add' ,'mymail') ?>" class="button" id="mymail_add_field">
			</td>
		</tr>
	</table>

	</div>
	<div id="tab-wordpress-users" class="tab">
		<h3><?php _e('WordPress Users', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_wordpress-users') ?>

	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Sync WordPress Users' ,'mymail') ?></th>
			<td>
			<label><input type="checkbox" name="mymail_options[sync]" value="1" <?php checked(mymail_option('sync')) ?> id="sync_list_check"> <?php _e('Sync WordPress Users with Subscribers' ,'mymail') ?></label>
			<p class="description"><?php _e('keep WordPress User data and Subscriber data synchronized. Only affects existing Subscribers' ,'mymail');?></p>
			</td>
		</tr>
	</table>
	<div id="sync_list" <?php if(!mymail_option('sync')) echo 'style="display:none"'?>>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Meta Data List' ,'mymail') ?><p class="description"><?php _e('select the custom field which should sync with a certain meta field' ,'mymail') ?></p></th>
			<td>

			<?php
				$synclist = mymail_option('synclist', array());
				$synclist = array('_' => '_') + $synclist;
				$meta_values = wp_parse_args(mymail('helper')->get_wpuser_meta_fields(), array('user_login', 'user_nicename', 'user_email','user_url', 'display_name', 'first_name', 'last_name', 'nickname'));
				$i = 0;
				foreach($synclist as $field => $metavalue){
					$customfield_dropdown = '<option value="-1">--</option><optgroup label="'.__('Custom Fields', 'mymail').'">';
					foreach (array('email' => __('Email', 'mymail'), 'firstname' => __('Firstname', 'mymail'), 'lastname' => __('Lastname', 'mymail')) as $key => $name) {
						$customfield_dropdown .= '<option value="'.$key.'" '.selected( $field, $key, false ).'>'.$name.'</option>';
					}
					foreach ($customfields as $key => $data) {
						$customfield_dropdown .= '<option value="'.$key.'" '.selected( $field, $key, false ).'>'.$data['name'].'</option>';
					}
					$customfield_dropdown .= '</optgroup>';
					$meta_value_dropdown = '<option value="-1">--</option><optgroup label="'.__('Meta Fields', 'mymail').'">';
					foreach ($meta_values as $key) {
						$meta_value_dropdown .= '<option value="'.$key.'" '.selected( $metavalue, $key, false ).'>'.$key.'</option>';
					}
					$meta_value_dropdown .= '</optgroup>';
				?>
				<div class="mymail_syncitem" title="<?php echo sprintf(__('%s syncs with %s', 'mymail'), $field, $metavalue) ?>">
					<select name="mymail_options[synclist][<?php echo $i ?>][meta]"><?php echo $meta_value_dropdown ?>:</select> &#10234;
					<select name="mymail_options[synclist][<?php echo $i ?>][field]"><?php echo $customfield_dropdown ?>:</select>
					<a class="remove-sync-item">&#10005;</a>
				</div>
				<?php $i++; } ?>
				<a class="button" id="add_sync_item"><?php _e('add', 'mymail') ?></a>
			</td>
		</tr>
	</table>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><p class="description"><?php _e('manually sync all existing users based on the above settings. (save required)' ,'mymail');?></p></th>
			<td>

			<p>
			<button class="button sync-button" id="sync_subscribers_wp"><?php echo __('Subscribers' ,'mymail') .' ⇒ '.__('WordPress Users' ,'mymail')  ?></button>
			<button class="button sync-button" id="sync_wp_subscribers"><?php echo __('WordPress Users' ,'mymail').' ⇒ '.__('Subscribers' ,'mymail')  ?></button>
			<span class="loading sync-ajax-loading"></span>
			</p>
			</td>
		</tr>
	</table>
	</div>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Delete Subscriber' ,'mymail') ?></th>
			<td>
			<label><input type="checkbox" name="mymail_options[delete_wp_subscriber]" value="1" <?php checked(mymail_option('delete_wp_subscriber')) ?>> <?php _e('Delete Subscriber if the WordPress User gets deleted' ,'mymail') ?></label>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Delete WordPress User' ,'mymail') ?></th>
			<td>
			<label>
			<?php if(!current_user_can('delete_users')) : ?>
			<input type="hidden" name="mymail_options[delete_wp_user]" value="<?php echo !!mymail_option('delete_wp_user') ?>">
			<input type="checkbox" name="mymail_options[delete_wp_user]" value="1" <?php checked(mymail_option('delete_wp_user')) ?> disabled readonly>
			<?php else : ?>
			<input type="checkbox" name="mymail_options[delete_wp_user]" value="1" <?php checked(mymail_option('delete_wp_user')) ?>>
			<?php endif; ?>

			<?php _e('Delete WordPress User if the Subscriber gets deleted' ,'mymail') ?></label>
				<p class="description"><?php _e('Attention! This option will remove assigned WordPress Users without further notice. You must have the capability to delete WordPress Users. Administrators and the current user can not get deleted with this option', 'mymail') ?></p>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Registered Users' ,'mymail') ?></th>
			<td>
			<?php if(get_option('users_can_register')) : ?>
			<label><input type="checkbox" name="mymail_options[register_signup]" value="1" <?php checked(mymail_option('register_signup')) ?> class="users-register" data-section="users-register_signup"> <?php _e('new WordPress users can choose to sign up on the register page' ,'mymail') ?></label>
			<?php else : ?>
			<p class="description"><?php echo sprintf(__('allow %s to your blog to enable this option' ,'mymail'), '<a href="options-general.php">'.__('users to subscribe' ,'mymail').'</a>');?></p>
			<?php endif; ?>
			</td>
		</tr>
	</table>
	<div id="users-register_signup" <?php if(!get_option('users_can_register') || !mymail_option('register_signup')) echo ' style="display:none"'; ?>>
		<table class="form-table">
			<tr valign="top">
				<th scope="row"></th>
				<td>
				<label><input type="checkbox" name="mymail_options[register_signup_checked]" value="1" <?php checked(mymail_option('register_signup_checked')) ?>> <?php _e('checked by default' ,'mymail') ?></label>
				<br><label><input type="checkbox" name="mymail_options[register_signup_confirmation]" value="1" <?php checked(mymail_option('register_signup_confirmation')) ?>> <?php _e('send confirmation (double-opt-in)', 'mymail'); ?></label>
				<p class="description"><?php _e('Subscribe them to these lists:', 'mymail'); ?></p>
				<?php

				mymail('lists')->print_it(NULL, NULL, 'mymail_options[register_signup_lists]', false, mymail_option('register_signup_lists'));

				?>
				</td>
			</tr>
		</table>
	</div>

	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('New Comments' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[register_comment_form]" value="1" <?php checked(mymail_option('register_comment_form')) ?> class="users-register" data-section="users-register_comment_form"> <?php _e('allow users to signup on the comment form if they are currently not subscribed to any list', 'mymail'); ?></label>
			</td>
		</tr>
	</table>
	<div id="users-register_comment_form" <?php if(!mymail_option('register_comment_form')) echo ' style="display:none"'; ?>>
		<table class="form-table">
			<tr valign="top">
				<th scope="row"></th>
				<td>
				<p><label><input type="checkbox" name="mymail_options[register_comment_form_checked]" value="1" <?php checked(mymail_option('register_comment_form_checked')) ?>> <?php _e('checked by default' ,'mymail') ?></label></p>
				<p><?php _e('sign up only if comment is', 'mymail'); ?><br>&nbsp;&nbsp;

				<label><input type="checkbox" name="mymail_options[register_comment_form_status][]" value="1" <?php checked(in_array('1', mymail_option('register_comment_form_status', array())), true) ?>> <?php _e('approved', 'mymail'); ?></label>
				<label><input type="checkbox" name="mymail_options[register_comment_form_status][]" value="0" <?php checked(in_array('0', mymail_option('register_comment_form_status', array())), true) ?>> <?php _e('not approved', 'mymail'); ?></label>
				<label><input type="checkbox" name="mymail_options[register_comment_form_status][]" value="spam" <?php checked(in_array('spam', mymail_option('register_comment_form_status', array())), true) ?>> <?php _e('spam', 'mymail'); ?></label>
				</p>
				<br><label><input type="checkbox" name="mymail_options[register_comment_form_confirmation]" value="1" <?php checked(mymail_option('register_comment_form_confirmation')) ?>> <?php _e('send confirmation (double-opt-in)', 'mymail'); ?></label>
				<p class="description"><?php _e('Subscribe them to these lists:', 'mymail'); ?></p>
				<?php

				mymail('lists')->print_it(NULL, NULL, 'mymail_options[register_comment_form_lists]', false, mymail_option('register_comment_form_lists'));

				?></td>
			</tr>
		</table>
	</div>

	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Others' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[register_other]" value="1" <?php checked(mymail_option('register_other')) ?> class="users-register" data-section="users-register_other"> <?php _e('add people who are added via the backend or any third party plugin', 'mymail'); ?></label>
			</td>
		</tr>
	</table>
	<div id="users-register_other" <?php if(!mymail_option('register_other')) echo ' style="display:none"'; ?>>
		<table class="form-table">
			<tr valign="top">
				<th scope="row"></th>
				<td>
				<p><label><input type="checkbox" name="mymail_options[register_other_confirmation]" value="1" <?php checked(mymail_option('register_other_confirmation')) ?>> <?php _e('send confirmation (double-opt-in)', 'mymail'); ?></label></p>
				<p class="description"><?php _e('Subscribe them to these lists:', 'mymail'); ?></p>
				<?php

				mymail('lists')->print_it(NULL, NULL, 'mymail_options[register_other_lists]', false, mymail_option('register_other_lists'));

				?>
				<p class="description"><?php _e('only with these user roles:', 'mymail'); ?></p>
				<ul><?php

				$set = mymail_option('register_other_roles', array());

			foreach($roles as $role => $name){
				echo '<li><input type="checkbox" name="mymail_options[register_other_roles][]" value="'.$role.'" '.checked( in_array($role, $set), true, false ).'> '.$name.'</li>';

			}?></ul></td>
			</tr>
		</table>
	</div>

	</div>

	<div id="tab-texts" class="tab">
		<h3><?php _e('Texts', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_texts') ?>

	<?php
	if ( $translations ) {
		$url = add_query_arg(array(
			'action' => 'do-translation-upgrade',
			'_wpnonce' => wp_create_nonce( 'upgrade-translations' ),
		), network_admin_url( 'update-core.php' ));
		?>
		<div class="error inline"><p><strong><?php _e('an update to your language is available!', 'mymail') ?></strong> <a class="button button-primary button-small" href="<?php echo esc_url($url) ?>"><?php _e('update now', 'mymail'); ?></a></p></div>
		<?php
	}
	?>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Subscription Form' ,'mymail') ?><p class="description"><?php _e('Define messages for the subscription form' ,'mymail'); ?>.<br><?php if(mymail_option('homepage')) echo sprintf(__('Some text can get defined on the %s as well' ,'mymail'), '<a href="post.php?post='.mymail_option('homepage').'&action=edit">Newsletter Homepage</a>'); ?></p></th>
			<td>
			<div class="mymail_text"><label><?php _e('Confirmation', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[confirmation]" value="<?php echo esc_attr(mymail_text('confirmation')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Successful', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[success]" value="<?php echo esc_attr(mymail_text('success')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Error Message', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[error]" value="<?php echo esc_attr(mymail_text('error')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Unsubscribe', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[unsubscribe]" value="<?php echo esc_attr(mymail_text('unsubscribe')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Unsubscribe Error', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[unsubscribeerror]" value="<?php echo esc_attr(mymail_text('unsubscribeerror')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Profile Update', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[profile_update]" value="<?php echo esc_attr(mymail_text('profile_update')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Newsletter Sign up', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[newsletter_signup]" value="<?php echo esc_attr(mymail_text('newsletter_signup')); ?>" class="regular-text"></div>
			</td>
		</tr>
	</table>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Field Labels' ,'mymail') ?><p class="description"><?php _e('Define texts for the labels of forms. Custom field labels can be defined on the Subscribers tab' ,'mymail'); ?></p></th>
			<td>
			<div class="mymail_text"><label><?php _e('Email', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[email]" value="<?php echo esc_attr(mymail_text('email')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('First Name', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[firstname]" value="<?php echo esc_attr(mymail_text('firstname')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Last Name', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[lastname]" value="<?php echo esc_attr(mymail_text('lastname')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Lists', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[lists]" value="<?php echo esc_attr(mymail_text('lists')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Submit Button', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[submitbutton]" value="<?php echo esc_attr(mymail_text('submitbutton')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Profile Button', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[profilebutton]" value="<?php echo esc_attr(mymail_text('profilebutton')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Unsubscribe Button', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[unsubscribebutton]" value="<?php echo esc_attr(mymail_text('unsubscribebutton')); ?>" class="regular-text"></div>
			</td>
		</tr>
	</table>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Mail' ,'mymail') ?><p class="description"><?php _e('Define texts for the mails' ,'mymail'); ?></p></th>
			<td>
			<div class="mymail_text"><label><?php _e('Unsubscribe Link', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[unsubscribelink]" value="<?php echo esc_attr(mymail_text('unsubscribelink')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Webversion Link', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[webversion]" value="<?php echo esc_attr(mymail_text('webversion')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Forward Link', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[forward]" value="<?php echo esc_attr(mymail_text('forward')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('Profile Link', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[profile]" value="<?php echo esc_attr(mymail_text('profile')); ?>" class="regular-text"></div>
			</td>
		</tr>
	</table>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Other' ,'mymail') ?></th>
			<td>
			<div class="mymail_text"><label><?php _e('already registered', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[already_registered]" value="<?php echo esc_attr(mymail_text('already_registered')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('new confirmation message', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[new_confirmation_sent]" value="<?php echo esc_attr(mymail_text('new_confirmation_sent')); ?>" class="regular-text"></div>
			<div class="mymail_text"><label><?php _e('enter your email', 'mymail'); ?>:</label> <input type="text" name="mymail_texts[enter_email]" value="<?php echo esc_attr(mymail_text('enter_email')); ?>" class="regular-text"></div>
			</td>
		</tr>
	</table>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Change Language' ,'mymail') ?></th>
			<td>
				<p class="description">
				<?php _e('change language of texts if available to' ,'mymail'); ?>
						<?php

						$dir = defined('WP_LANG_DIR') ? WP_LANG_DIR : MYMAIL_DIR.'/languages/';

						$files = list_files($dir);
						$files = preg_grep('/mymail-(.*)\.po$/', $files);

						?>
						<select name="language-file">
								<option<?php selected(preg_match('#^en_#', $locale)); ?> value="en_US"><?php _e('English', 'mymail'); ?> (en_US)</option>
						<?php
							foreach($files as $file){
								$lang = str_replace(array('.po', 'mymail-'), '', basename($file));
								?>
								<option<?php selected($lang == $locale); ?> value="<?php echo $lang; ?>"><?php echo $lang; ?></option>
								<?php
							}

						?>
						</select>
				<button name="change-language" class="button"><?php _e('change language', 'mymail'); ?></button>
				<br class="clearfix">
				</p>
			</td>
		</tr>
	</table>
	</div>

	<div id="tab-tags" class="tab">
		<h3><?php _e('Tags', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_tags') ?>

		<p class="description"><?php echo sprintf(__('Tags are placeholder for your newsletter. You can set them anywhere in your newsletter template with the format %s. Custom field tags are individual for each subscriber.', 'mymail'), '<code>{tagname}</code>'); ?></p>
		<p class="description"><?php echo sprintf(__('You can set alternative content with %1$s which will be uses if %2$s is not defined. All unused tags will get removed in the final message', 'mymail'), '<code>{tagname|alternative content}</code>', '[tagname]'); ?></p>
			<?php $reserved = array('unsub', 'unsublink', 'webversion', 'webversionlink', 'forward', 'forwardlink', 'subject', 'preheader', 'profile', 'profilelink', 'headline', 'content', 'link', 'email', 'emailaddress', 'firstname', 'lastname', 'fullname', 'year', 'month', 'day', 'share', 'tweet')?>
		<p id="reserved-tags" data-tags='["<?php echo implode('","', $reserved)?>"]'><?php _e('reserved tags', 'mymail'); ?>: <code>{<?php echo implode('}</code>, <code>{', $reserved)?>}</code></p>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Permanent Tags' ,'mymail') ?>:</th>
			<td class="tags">
			<p class="description"><?php _e('These are permanent tags which cannot get deleted. The CAN-SPAM tag is required in many countries.', 'mymail'); ?> <a href="http://en.wikipedia.org/wiki/CAN-SPAM_Act_of_2003" class="external"><?php _e('Read more', 'mymail'); ?></a></p>
			<?php
				if($tags = mymail_option('tags')){
					foreach($tags as $tag => $content){
					 ?>
				<div class="tag"><span><code>{<?php echo $tag?>}</code></span> &#10152; <input type="text" name="mymail_options[tags][<?php echo $tag?>]" value="<?php echo esc_attr($content)?>" class="regular-text tag-value"></div>
					 <?php
					}
				}
			?>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Custom Tags' ,'mymail') ?>:</th>
			<td class="tags">
			<p class="description"><?php _e('Add your custom tags here. They work like permanent tags', 'mymail'); ?></p>
			<?php
				if($tags = mymail_option('custom_tags')){
					foreach($tags as $tag => $content){
					 ?>
				<div class="tag"><span><code>{<?php echo $tag?>}</code></span> &#10152; <input type="text" name="mymail_options[custom_tags][<?php echo $tag?>]" value="<?php echo esc_attr($content)?>" class="regular-text tag-value"> <a class="tag-remove">&#10005;</a></div>
					 <?php
					}
				}
			?>
			<input type="button" value="<?php _e('add' ,'mymail') ?>" class="button" id="mymail_add_tag">
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Special Tags' ,'mymail') ?>:</th>
			<td class="customfields">
			<p class="description"><?php _e('Special tags display dynamic content and are equally for all subscribers', 'mymail'); ?></p>
			<div class="customfield"><span><code>{tweet:username}</code></span> &#10152; <?php echo sprintf(__('displays the last tweet from Twitter user [username] (cache it for %s minutes)', 'mymail'), '<input type="text" name="mymail_options[tweet_cache_time]" value="'.esc_attr(mymail_option('tweet_cache_time')).'" class="small-text">'); ?></div>
			<p class="description">
				<?php echo sprintf(__('To enable the tweet feature you have to create a new %s and insert your credentials', 'mymail'), '<a href="https://dev.twitter.com/apps/new" class="external">Twitter App</a>'); ?>
			</p>
			<p>
			<div class="mymail_text">&nbsp;<label><?php _e('Access token', 'mymail'); ?>:</label> <input type="text" name="mymail_options[twitter_token]" value="<?php echo esc_attr(mymail_option('twitter_token')); ?>" class="regular-text" autocomplete="off"></div>
			<div class="mymail_text">&nbsp;<label><?php _e('Access token Secret', 'mymail'); ?>:</label> <input type="password" name="mymail_options[twitter_token_secret]" value="<?php echo esc_attr(mymail_option('twitter_token_secret')); ?>" class="regular-text" autocomplete="off"></div>
			<div class="mymail_text">&nbsp;<label><?php _e('Consumer key', 'mymail'); ?>:</label> <input type="text" name="mymail_options[twitter_consumer_key]" value="<?php echo esc_attr(mymail_option('twitter_consumer_key')); ?>" class="regular-text" autocomplete="off"></div>
			<div class="mymail_text">&nbsp;<label><?php _e('Consumer secret', 'mymail'); ?>:</label> <input type="password" name="mymail_options[twitter_consumer_secret]" value="<?php echo esc_attr(mymail_option('twitter_consumer_secret')); ?>" class="regular-text" autocomplete="off"></div>
			</p>
			<br>
			<div class="customfield"><span><code>{share:twitter}</code></span> &#10152; <?php echo sprintf(__('displays %1$s to share the newsletter via %2$s', 'mymail'), '<img src="' . MYMAIL_URI. '/assets/img/share/share_twitter.png">', 'Twitter'); ?></div>
			<div class="customfield"><span><code>{share:facebook}</code></span> &#10152; <?php echo sprintf(__('displays %1$s to share the newsletter via %2$s', 'mymail'), '<img src="' . MYMAIL_URI. '/assets/img/share/share_facebook.png">', 'Facebook'); ?></div>
			<div class="customfield"><span><code>{share:google}</code></span> &#10152; <?php echo sprintf(__('displays %1$s to share the newsletter via %2$s', 'mymail'), '<img src="' . MYMAIL_URI. '/assets/img/share/share_google.png">', 'Google+'); ?></div>
			<div class="customfield"><span><code>{share:linkedin}</code></span> &#10152; <?php echo sprintf(__('displays %1$s to share the newsletter via %2$s', 'mymail'), '<img src="' . MYMAIL_URI. '/assets/img/share/share_linkedin.png">', 'LinkedIn'); ?></div>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Dynamic Tags' ,'mymail') ?></th>
			<td><p class="description"><?php _e('Dynamic tags let you display your posts or pages in a reverse chronicle order. Some examples:', 'mymail'); ?></p>
			<div class="customfield"><span><code>{post_title:-1}</code></span> &#10152; <?php _e('displays the latest post title', 'mymail'); ?></div>
			<div class="customfield"><span><code>{page_title:-4}</code></span> &#10152; <?php _e('displays the fourth latest page title', 'mymail'); ?></div>
			<div class="customfield"><span><code>{post_image:-1}</code></span> &#10152; <?php _e('displays the feature image of the latest posts', 'mymail'); ?></div>
			<div class="customfield"><span><code>{post_image:-4|23}</code></span> &#10152; <?php _e('displays the feature image of the fourth latest posts. Uses the image with ID 23 if the post doesn\'t have a feature image', 'mymail'); ?></div>
			<div class="customfield"><span><code>{post_content:-1}</code></span> &#10152; <?php _e('displays the latest posts content', 'mymail'); ?></div>
			<div class="customfield"><span><code>{post_excerpt:-1}</code></span> &#10152; <?php _e('displays the latest posts excerpt or content if no excerpt is defined', 'mymail'); ?></div>
			<div class="customfield"><span><code>{post_date:-1}</code></span> &#10152; <?php _e('displays the latest posts date', 'mymail'); ?></div>
			<p class="description"><?php _e('you can also use absolute values', 'mymail'); ?></p>
			<div class="customfield"><span><code>{post_title:23}</code></span> &#10152; <?php _e('displays the post title of post ID 23', 'mymail'); ?></div>
			<div class="customfield"><span><code>{post_link:15}</code></span> &#10152; <?php _e('displays the permalink of post ID 15', 'mymail'); ?></div>
			<p class="description"><?php _e('Instead of "post_" and "page_" you can use custom post types too', 'mymail'); ?></p>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Image Fallback' ,'mymail') ?></th>
			<td><label><?php _e('image ID', 'mymail' ); ?>:
			<input type="text" name="mymail_options[fallback_image]" value="<?php echo intval(mymail_option('fallback_image', 0)) ?>" class="small-text"></label>
			<p class="description"><?php _e('Fallback image for dynamic image tags', 'mymail'); ?></p>
			</td>
		</tr>
	</table>
	</div>



	<div id="tab-delivery" class="tab">
		<h3><?php _e('Delivery', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_delivery') ?>

	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Number of mails sent' ,'mymail') ?></th>
			<td><p><?php echo sprintf(__('Send max %1$s emails at once and max %2$s within %3$s hours' ,'mymail'),'<input type="text" name="mymail_options[send_at_once]" value="'.mymail_option('send_at_once').'" class="small-text">', '<input type="text" name="mymail_options[send_limit]" value="'.mymail_option('send_limit').'" class="small-text">', '<input type="text" name="mymail_options[send_period]" value="'.mymail_option('send_period').'" class="small-text">')?></p>
			<p class="description"><?php _e('Depending on your hosting provider you can increase these values' ,'mymail') ?></p>
			<?php
				$sent_this_period = get_transient('_mymail_send_period', 0);
				$mails_left = max(0, mymail_option('send_limit')-$sent_this_period);
				$next_reset = get_option('_transient_timeout__mymail_send_period_timeout');

				if(!$next_reset || $next_reset < time()){
					$next_reset = time()+mymail_option('send_period')*3600;
					$mails_left = mymail_option('send_limit');
				}
			?>
			<p class="description"><?php echo sprintf(__('You can still send %1$s mails within the next %2$s', 'mymail'), '<strong>'.number_format_i18n($mails_left).'</strong>' , '<strong title="'.date_i18n(get_option('date_format').' '.get_option('time_format'), $next_reset+$timeoffset, true).'">'.human_time_diff($next_reset).'</strong>'); ?> &ndash; <a href="<?php echo add_query_arg('reset-limits', 1) ?>"><?php _e('reset these limits', 'mymail'); ?></a></p>

		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Split campaigns' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[split_campaigns]" value="1" <?php checked(mymail_option('split_campaigns')) ?>> <?php _e('send campaigns simultaneously instead of one after the other' ,'mymail') ?></label> </td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Pause campaigns' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[pause_campaigns]" value="1" <?php checked(mymail_option('pause_campaigns')); ?>> <?php _e('pause campaigns if an error occurs' ,'mymail') ?></label><p class="description"><?php _e('MyMail will change the status to "pause" if an error occur otherwise it tries to finish the campaign', 'mymail'); ?></p></td>
		</tr>
		<tr valign="top">
			<tr valign="top">
				<th scope="row"><?php _e('Time between mails' ,'mymail') ?></th>
				<td><p><input type="text" name="mymail_options[send_delay]" value="<?php echo mymail_option('send_delay'); ?>" class="small-text"> <?php _e('milliseconds', 'mymail'); ?></p><p class="description"><?php _e('define a delay between mails in milliseconds if you have problems with sending two many mails at once', 'mymail'); ?></p>
			</td>
		</tr>
		<tr valign="top">
			<tr valign="top">
				<th scope="row"><?php _e('Max. Execution Time' ,'mymail') ?></th>
				<td><p><input type="text" name="mymail_options[max_execution_time]" value="<?php echo mymail_option('max_execution_time', 0); ?>" class="small-text"> <?php _e('seconds', 'mymail'); ?></p><p class="description"><?php _e('define a maximum execution time to prevent server timeouts. If set to zero, no time limit is imposed.', 'mymail'); ?></p>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Send test email with current settings' ,'mymail') ?></th>
			<td>
			<input type="text" value="<?php echo $current_user->user_email ?>" autocomplete="off" class="form-input-tip" id="mymail_testmail">
			<input type="button" value="<?php _e('Send Test' ,'mymail') ?>" class="button mymail_sendtest" data-role="basic">
			<div class="loading test-ajax-loading"></div>
			<p class="description"><?php _e('You have to save your settings before you can test them!', 'mymail'); ?></p>
			</td>
		</tr>
	</table>

	<?php

		$deliverymethods = apply_filters('mymail_delivery_methods', array(
			'simple' => __('Simple' ,'mymail'),
			'smtp' => 'SMTP',
			'gmail' => 'Gmail',
		));

		$method = mymail_option('deliverymethod', 'simple');

	?>

	<h3><?php _e('Delivery Method', 'mymail'); ?></h3>
	<div class="updated inline"><p><?php echo sprintf(__('You are currently sending with the %s delivery method', 'mymail'), '<strong>'.$deliverymethods[$method].'</strong>') ?></p></div>
	<div id="deliverynav" class="nav-tab-wrapper hide-if-no-js">
		<?php foreach($deliverymethods as $id => $name){?>
		<a class="nav-tab <?php if($method == $id) echo 'nav-tab-active' ?>" href="#<?php echo $id?>"><?php echo $name ?></a>
		<?php }?>
		<a href="plugin-install.php?tab=search&s=mymail+revaxarts&plugin-search-input=Search+Plugins" class="alignright"><?php _e('search for more delivery methods', 'mymail'); ?></a>
	</div>

	<input type="hidden" name="mymail_options[deliverymethod]" id="deliverymethod" value="<?php echo esc_attr($method); ?>" class="regular-text">

	<div class="subtab" id="subtab-simple" <?php if($method == 'simple') echo 'style="display:block"'; ?>>
		<p class="description">
		<?php _e('use this option if you don\'t have access to a SMTP server or any other provided options', 'mymail'); ?>
		</p>
		<?php $basicmethod = mymail_option('simplemethod', 'sendmail'); ?>
		<table class="form-table">
			<tr valign="top">
				<td><label><input type="radio" name="mymail_options[simplemethod]" value="sendmail" <?php checked($basicmethod, 'sendmail') ?> id="sendmail"> Sendmail</label>
				<div class="sendmailpath">
					<label>Sendmail Path: <input type="text" value="<?php echo mymail_option('sendmail_path'); ?>" class="form-input-tip" name="mymail_options[sendmail_path]"></label>
				</div>
				</td>
			</tr>
			<tr valign="top">
				<td><label><input type="radio" name="mymail_options[simplemethod]" value="mail" <?php checked($basicmethod, 'mail') ?>> PHPs mail() function</label></td>
			</tr>
			<tr valign="top">
				<td><label><input type="radio" name="mymail_options[simplemethod]" value="qmail" <?php checked($basicmethod, 'qmail') ?>> QMail</label></td>
			</tr>
		</table>
	</div>
	<div class="subtab" id="subtab-smtp" <?php if($method == 'smtp') echo 'style="display:block"'; ?>>
		<table class="form-table">
			<tr valign="top">
				<th scope="row">SMTP Host : Port</th>
				<td><input type="text" name="mymail_options[smtp_host]" value="<?php echo esc_attr(mymail_option('smtp_host')); ?>" class="regular-text ">:<input type="text" name="mymail_options[smtp_port]" id="mymail_smtp_port" value="<?php echo intval(mymail_option('smtp_port')); ?>" class="small-text smtp"></td>
			</tr>
			<tr valign="top">
				<th scope="row">Timeout</th>
				<td><span><input type="text" name="mymail_options[smtp_timeout]" value="<?php echo mymail_option('smtp_timeout'); ?>" class="small-text"> <?php _e('seconds', 'mymail'); ?></span></td>
			</tr>
			<tr valign="top">
				<th scope="row"><?php _e('Secure connection' ,'mymail') ?></th>
				<?php
					$secure = mymail_option('smtp_secure');
				?>
				<td>
				<label><input type="radio" name="mymail_options[smtp_secure]" value="" <?php checked(!$secure) ?> class="smtp secure" data-port="25"> <?php _e('none' ,'mymail') ?></label>
				<label><input type="radio" name="mymail_options[smtp_secure]" value="ssl" <?php checked($secure == 'ssl') ?> class="smtp secure" data-port="465"> SSL</label>
				<label><input type="radio" name="mymail_options[smtp_secure]" value="tls" <?php checked($secure == 'tls') ?> class="smtp secure" data-port="465"> TLS</label>
				 </td>
			</tr>
			<tr valign="top">
				<th scope="row">SMTPAuth</th>
				<td><label><input type="checkbox" name="mymail_options[smtp_auth]" value="1" <?php checked(mymail_option('smtp_auth')) ?>> <?php _e('If checked username and password are required' ,'mymail') ?></label> </td>
			</tr>
			<tr valign="top">
				<th scope="row"><?php _e('Username' ,'mymail') ?></th>
				<td><input type="text" name="mymail_options[smtp_user]" value="<?php echo esc_attr(mymail_option('smtp_user')); ?>" class="regular-text"></td>
			</tr>
			<tr valign="top">
				<th scope="row"><?php _e('Password' ,'mymail') ?></th>
				<td><input type="password" name="mymail_options[smtp_pwd]" value="<?php echo esc_attr(mymail_option('smtp_pwd')); ?>" class="regular-text"></td>
			</tr>
		</table>
	</div>
	<div class="subtab" id="subtab-gmail" <?php if($method == 'gmail') echo 'style="display:block"'; ?>>
		<p class="description">
		<?php _e('Gmail has a limit of 500 mails within 24 hours! Also sending a mail can take up to one second which is quite long. This options is only recommended for few subscribers. DKIM works only if set the from address to your Gmail address.', 'mymail'); ?>
		</p>
		<table class="form-table">
			<tr valign="top">
				<th scope="row"><?php _e('Username' ,'mymail') ?></th>
				<td><input type="text" name="mymail_options[gmail_user]" value="<?php echo esc_attr(mymail_option('gmail_user')); ?>" class="regular-text" placeholder="@gmail.com"></td>
			</tr>
			<tr valign="top">
				<th scope="row"><?php _e('Password' ,'mymail') ?></th>
				<td><input type="password" name="mymail_options[gmail_pwd]" value="<?php echo esc_attr(mymail_option('gmail_pwd')); ?>" class="regular-text"></td>
			</tr>
		</table>
	</div>
	<?php foreach($deliverymethods as $id => $name){
		if(in_array($id, array('simple', 'smtp', 'gmail'))) continue;

	?>
	<div class="subtab" id="subtab-<?php echo $id ?>" <?php if($method == $id) echo 'style="display:block"'; ?>>
		<?php do_action('mymail_deliverymethod_tab') ?>
		<?php do_action('mymail_deliverymethod_tab_'.$id) ?>
	</div>
	<?php } ?>
	</div>



	<div id="tab-cron" class="tab">
		<h3><?php _e('Cron', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_cron') ?>

	<table class="form-table">
		<tr valign="top" class="wp_cron">
			<th scope="row"><?php _e('Interval for sending emails' ,'mymail') ?></th>
			<td><p><?php echo sprintf(__('Send emails at most every %1$s minutes' ,'mymail'),'<input type="text" name="mymail_options[interval]" value="'.mymail_option('interval').'" class="small-text">')?></p><p class="description"><?php _e('Optional if a real cron service is used', 'mymail'); ?></p></td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Cron Service' ,'mymail') ?></th>
			<td>
				<?php  $cron = mymail_option('cron_service'); ?>
				<label><input type="radio" class="cron_radio" name="mymail_options[cron_service]" value="wp_cron" <?php checked($cron == 'wp_cron'); ?> > <?php _e('Use the wp_cron function to send newsletters' ,'mymail') ?></label><br>
				<?php if((defined('DISABLE_WP_CRON') && DISABLE_WP_CRON)) : ?>
				<div class="error inline"><p><strong><?php echo sprintf(__('WordPress cron is disabled! Uncomment the %s constant in the wp-config.php or use a real cron instead', 'mymail'), '<code>DISABLE_WP_CRON</code>'); ?></strong></p></div>
				<?php endif;?>
				<label><input type="radio" class="cron_radio" name="mymail_options[cron_service]" value="cron" <?php checked($cron == 'cron') ?> > <?php _e('Use a real cron to send newsletters' ,'mymail') ?></label> <span class="description"><?php _e('reccomended for many subscribers' ,'mymail') ?></span>
				<?php if(file_exists(MYMAIL_UPLOAD_DIR.'/CRON_LOCK') && (time()-filemtime(MYMAIL_UPLOAD_DIR.'/CRON_LOCK')) < 10) : ?>
				<div class="error inline"><p><strong><?php _e('Cron is currently running!', 'mymail'); ?></strong></p></div>
				<?php endif;?>
			</td>
		</tr>
		<tr valign="top" class="cron_opts cron" <?php if($cron != 'cron') echo ' style="display:none"';?>>
			<th scope="row"><?php _e('Cron Settings' ,'mymail') ?></th>
			<td>
				<p>
				<input type="text" name="mymail_options[cron_secret]" value="<?php echo esc_attr(mymail_option('cron_secret')); ?>" class="regular-text"> <span class="description"><?php _e('a secret hash which is required to execute the cron' ,'mymail') ?></span>
				</p>
				<?php $cron_url = defined('MYMAIL_MU_CRON')
							? add_query_arg(array('action' => 'mymail_cron_worker', 'secret' => mymail_option('cron_secret')), admin_url('admin-ajax.php'))
							: MYMAIL_URI . 'cron.php?'.mymail_option('cron_secret');
				?>
				<p><?php _e('You can keep a browser window open with following URL' ,'mymail') ?><br>
				<a href="<?php echo $cron_url ?>" class="external"><code><?php echo $cron_url ?></code></a><br>
				<?php _e('call it directly' ,'mymail') ?><br>
				<code>curl --silent <?php echo $cron_url ?></code><br>
				<?php _e('or set up a cron' ,'mymail') ?><br>
				<code>*/<?php echo mymail_option('interval')?> * * * * GET <?php echo $cron_url ?> > /dev/null</code></p>
				<p class="description"><?php _e('You can setup an interval as low as one minute, but should consider a reasonable value of 5-15 minutes as well.', 'mymail'); ?></p>
				<p class="description"><?php _e('If you need help setting up a cron job please refer to the documentation that your provider offers.', 'mymail'); ?></p>
				<p class="description"><?php echo sprintf(__('Anyway, chances are high that either %1$s, %2$s or %3$s  documentation will help you.', 'mymail'),'<a href="http://docs.cpanel.net/twiki/bin/view/AllDocumentation/CpanelDocs/CronJobs#Adding a cron job" class="external">the CPanel</a>', '<a href="http://download1.parallels.com/Plesk/PP10/10.3.1/Doc/en-US/online/plesk-administrator-guide/plesk-control-panel-user-guide/index.htm?fileName=65208.htm" class="external">Plesk</a>', '<a href="http://www.thegeekstuff.com/2011/07/php-cron-job/" class="external">the crontab</a>'); ?></p>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('Last hit' ,'mymail') ?></th>
			<td>
				<div class="highlight"><span><?php $lasthit = get_option('mymail_cron_lasthit'); echo $lasthit ? ' '.sprintf(__('from %s', 'mymail'), $lasthit['ip']).', '.date(get_option('date_format').' '.get_option('time_format'), $lasthit['timestamp']+$timeoffset).', '.sprintf(__('%s ago', 'mymail'), human_time_diff($lasthit['timestamp'])).' '.(($interv = round(($lasthit['timestamp']-$lasthit['oldtimestamp'])/60)) ? '('.__('Interval','mymail').': '.$interv.' '._x('min', 'short for minute', 'mymail').' / '.round($lasthit['time'],3).' sec, max '.round($lasthit['timemax'],3).' sec)' : '') : __('never' ,'mymail'); ?></span></div>
			</td>
		</tr>
	</table>
	</div>

	<?php if(current_user_can('mymail_manage_capabilities') || current_user_can('manage_options')) : ?>

	<div id="tab-capabilities" class="tab">
		<h3><?php _e('Capabilities', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_capabilities') ?>

		<p class="description"><?php _e('Define capabilities for each user role. To add new roles you can use a third party plugin. Administrator has always all privileges', 'mymail'); ?></p>
		<div id="current-cap"></div>

	<table class="form-table"><?php

			unset($roles['administrator']);

	?>
		<tr valign="top">
			<td>
				<table id="capabilities-table">
					<thead>
						<tr>
						<th>&nbsp;</th><?php
			foreach($roles as $role => $name){
				echo '<th><input type="hidden" name="mymail_options[roles]['.$role.'][]" value="">'.$name.' <input type="checkbox" class="selectall" value="'.$role.'" title="'.__('toggle all', 'mymail').'"></th>';

			}?>			</tr>
					</thead>
					<tbody>
			<?php
				include(MYMAIL_DIR . 'includes/capability.php');

				foreach($mymail_capabilities as $capability => $data){

			?>
					<tr><th><?php echo $data['title'] ?></th><?php
					foreach($roles as $role => $name){
						$r = get_role($role);
						echo '<td><label title="'.sprintf(__('%1$s can %2$s', 'mymail'), $name, $data['title']).'"><input name="mymail_options[roles]['.$role.'][]" type="checkbox" class="cap-check-'.$role.'" value="'.$capability.'" '.checked(!empty($r->capabilities[$capability]), 1, false).' '.($role == 'administrator' ? 'readonly' : '').'></label></td>';

					}?>
					</tr>
			<?php } ?>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<p>
	<a onclick='return confirm("<?php _e('Do you really like to reset all capabilities? This cannot be undone!', 'mymail'); ?>");' href="options-general.php?page=newsletter-settings&reset-capabilities=1&_wpnonce=<?php echo wp_create_nonce('mymail-reset-capabilities') ?>"><?php _e('Reset all capabilities', 'mymail'); ?></a>
	</p>
	</div>

	<?php endif;?>

	<div id="tab-bounce" class="tab">
	<h3><?php _e('Bounces', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_bounce') ?>

	<table class="form-table">

		<tr valign="top">
			<th scope="row"><?php _e('Bounce Address' ,'mymail') ?></th>
			<td><input type="text" name="mymail_options[bounce]" value="<?php echo esc_attr(mymail_option('bounce')); ?>" class="regular-text"> <span class="description"><?php _e('Undeliverable emails will return to this address', 'mymail'); ?></span></td>
		</tr>
		<tr valign="top">
			<th scope="row">&nbsp;</th>
			<td><label><input type="checkbox" name="mymail_options[bounce_active]" id="bounce_active" value="1" <?php checked(mymail_option('bounce_active')); ?>> <?php _e('enable automatic bounce handling' ,'mymail') ?></label>
			</td>
		</tr>

	</table>
		<div id="bounce-options" <?php if(!mymail_option('bounce_active')) echo 'style="display:none"';?>>
		<table class="form-table">
			<tr valign="top">
				<th scope="row">&nbsp;</th>
				<td><p class="description"><?php _e('If you would like to enable bouncing you have to setup a separate POP3 mail account', 'mymail'); ?></p></td>
			</tr>
			<tr valign="top">
				<th scope="row"><?php _e('Server Address : Port' ,'mymail') ?></th>
				<td><input type="text" name="mymail_options[bounce_server]" value="<?php echo esc_attr(mymail_option('bounce_server')); ?>" class="regular-text">:<input type="text" name="mymail_options[bounce_port]" id="bounce_port" value="<?php echo mymail_option('bounce_port'); ?>" class="small-text"></td>
			</tr>
			<tr valign="top">
				<th scope="row">SSL</th>
				<td><label><input type="checkbox" name="mymail_options[bounce_ssl]" id="bounce_ssl" value="1" <?php checked(mymail_option('bounce_ssl')); ?>> <?php _e('Use SSL. Default port is 995' ,'mymail') ?></label>
				</td>
			</tr>
			<tr valign="top">
				<th scope="row"><?php _e('Username' ,'mymail') ?></th>
				<td><input type="text" name="mymail_options[bounce_user]" value="<?php echo esc_attr(mymail_option('bounce_user')); ?>" class="regular-text"></td>
			</tr>
			<tr valign="top">
				<th scope="row"><?php _e('Password' ,'mymail') ?></th>
				<td><input type="password" name="mymail_options[bounce_pwd]" value="<?php echo esc_attr(mymail_option('bounce_pwd')); ?>" class="regular-text"></td>
			</tr>
			<tr valign="top" class="wp_cron">
				<th scope="row"></th>
				<td><p><?php echo sprintf(__('Check bounce server every %s minutes for new messages' ,'mymail'),'<input type="text" name="mymail_options[bounce_check]" value="'.mymail_option('bounce_check').'" class="small-text">')?></p></td>
			</tr>
			<tr valign="top">
				<th scope="row"><?php _e('Delete messages', 'mymail'); ?></th>
				<td><label><input type="checkbox" name="mymail_options[bounce_delete]" value="1" <?php checked(mymail_option('bounce_delete')) ?>> <?php _e('Delete messages without tracking code to keep postbox clear (recommended)' ,'mymail') ?></label>
				</td>
			</tr>
			<tr valign="top" class="wp_cron">
				<th scope="row"><?php _e('Soft Bounces' ,'mymail') ?></th>
				<td><p><?php echo sprintf(__('Resend soft bounced mails after %s minutes' ,'mymail'),'<input type="text" name="mymail_options[bounce_delay]" value="'.mymail_option('bounce_delay').'" class="small-text">')?></p>
				<p><?php
					$dropdown = '<select name="mymail_options[bounce_attempts]" class="postform">';
					$value = mymail_option('bounce_attempts');
					for($i = 1; $i <= 10; $i++){
						$selected = ($value == $i) ? ' selected' : '';
					$dropdown .= '<option value="'.$i.'" '.$selected.'>'.$i.'</option>';
					}
					$dropdown .= '</select>';

				echo sprintf( __('%s attempts to deliver message until hardbounce' ,'mymail'), $dropdown);

				?></p></td>
			</tr>
		</table>
		<table class="form-table">
			<tr valign="top">
				<th scope="row"></th>
				<td>
				<input type="button" value="<?php _e('Test bounce settings' ,'mymail') ?>" class="button mymail_bouncetest">
				<div class="loading bounce-ajax-loading"></div>
				<span class="bouncetest_status"><?php _e('sending message...', 'mymail'); ?></span>
				<p class="description"><?php _e('You have to save your settings before you can test them!', 'mymail'); ?></p>
				</td>
			</tr>
		</table>
		</div>
	</div>



	<div id="tab-authentication" class="tab">
		<h3><?php _e('Authentication', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_authentication') ?>

		<?php
			$spf = mymail_option('spf');
			$dkim = mymail_option('dkim');
		?>
	<p class="description"><?php _e('You need to change the namespace records of your domain if you would like to use one of these methods. Ask your provider how to add "TXT namespace records". Changes take some time to get published on all DNS worldwide.', 'mymail'); ?></p>

	<table class="form-table">

		<tr valign="top">
			<th scope="row"><h4>SPF</h4></th>
			<td><label><input type="checkbox" name="mymail_options[spf]" id="mymail_spf" value="1" <?php checked($spf) ?>> <?php _e('Use Sender Policy Framework' ,'mymail') ?>. <a href="http://en.wikipedia.org/wiki/Sender_Policy_Framework" class="external"><?php _e('read more', 'mymail') ?></a></label> </td>
		</tr>
	</table>
	<div class="spf-info" <?php if(!$spf) echo ' style="display:none"';?>>
	<table class="form-table no-margin">
		<?php if(mymail_option('spf_domain') && $spf) :?>
		<tr valign="top">
			<th scope="row">&nbsp;</th>
			<td>
				<?php
				$host = mymail_option('spf_domain');
				if ( false === ( $records = get_transient( 'mymail_spf_records' ) ) ) {

					require_once MYMAIL_DIR . 'classes/libs/dns.class.php';

					$dns_query = new DNSQuery('8.8.8.8');
					$result = $dns_query->Query($host, 'TXT');

					if($result->count){
						$records = json_decode(json_encode($result->results), true);

						set_transient( 'mymail_spf_records', $records, 900 );
					}

				}

				$found = false;
				if($records){
					foreach($records as $r){
						if($r['typeid'] === 'TXT' && preg_match('#v=spf1 #', $r['data'])){
							$found = $r;
							break;
						}
					}
				}

				if($found) :?>

				<div class="verified"><p><?php echo sprintf(__('Domain %s', 'mymail'), '<strong>'.mymail_option('spf_domain').'</strong>').': '.__('TXT record found', 'mymail'); ?>: <code><?php echo $r['data']?></code></p>
				<?php else : ?>
				<div class="not-verified"><p><?php echo sprintf(__('Domain %s', 'mymail'), '<strong>'.mymail_option('spf_domain').'</strong>').': '.__('no TXT record found', 'mymail'); ?></p>
				<p><?php echo sprintf(__('No or wrong record found for %s. Please adjust the namespace records and add these lines:', 'mymail'), '<strong>'.$host.'</strong>'); ?>
				</p>
				<?php
					require_once MYMAIL_DIR . 'classes/libs/dns.class.php';

					//use googles DNS
					$dns_query = new DNSQuery('8.8.8.8');
					$result = $dns_query->Query($host, 'A');
					$result = wp_list_pluck($result->results, 'data');
				?>
				<dl>
					<dt><strong><?php echo mymail_option('spf_domain'); ?></strong> IN TXT</dt>
						<dd><textarea class="widefat" rows="1" id="spf-record">v=spf1 mx a ip4:<?php echo implode(' ip4:', $result)?> ~all</textarea></dd>
				</dl>
				<?php
				endif;
			?>
				</div>
				<p class="description"><?php echo sprintf(__('SPF doesn\'t require any configuration on this settings page. This should give you some help to set it up correctly. If this SPF configuration doesn\'t work or your mails returned as spam you should ask your provider for help or change your delivery method or try %s', 'mymail'), '<a href="http://www.microsoft.com/mscorp/safety/content/technologies/senderid/wizard/default.aspx" class="external">'.__('this wizard', 'mymail').'</a>'); ?></p>
			</td>
		</tr>
		<?php endif; ?>
		<tr valign="top">
			<th scope="row">Domain</th>
			<td><input type="text" name="mymail_options[spf_domain]" id="spf-domain" value="<?php echo esc_attr(mymail_option('spf_domain')); ?>" class="regular-text dkim">
			<span class="description"><?php _e('The domain you would like to add a SPF record', 'mymail'); ?></span>
			</td>
		</tr>
	</table>
	</div>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><h4>DKIM</h4></th>
			<td><label><input type="checkbox" name="mymail_options[dkim]" id="mymail_dkim" value="1" <?php checked($dkim) ?>> <?php _e('Use DomainKeys Identified Mail' ,'mymail') ?>. <a href="http://en.wikipedia.org/wiki/DomainKeys_Identified_Mail" class="external"><?php _e('read more', 'mymail') ?></a></label> </td>
		</tr>
	</table>
	<div class="dkim-info" <?php if(!$dkim) echo ' style="display:none"';?>>
	<table class="form-table no-margin">
	<?php if($dkim && mymail_option('dkim_private_key') && mymail_option('dkim_public_key')) : ?>
		<tr valign="top">
			<th scope="row">&nbsp;</th>
			<td>
				<?php

				$pubkey = trim(str_replace(array('-----BEGIN PUBLIC KEY-----', '-----END PUBLIC KEY-----', "\n", "\r"), '', mymail_option('dkim_public_key')));
				$record = 'k=rsa;t=y;'."\n".'p='.$pubkey;

				$host = mymail_option('dkim_selector').'._domainkey.'.mymail_option('dkim_domain');

				if ( false === ( $records = get_transient( 'mymail_dkim_records' ) ) ) {

					require_once MYMAIL_DIR . 'classes/libs/dns.class.php';

					$dns_query = new DNSQuery('8.8.8.8');
					$result = $dns_query->Query($host, 'ANY');

					if($result->count){
						$records = (array) unserialize(utf8_decode(serialize($result->results)));
						$records = array();
						foreach($result->results as $r){
							$r->data = utf8_decode($r->data);
							$r->string = utf8_decode($r->string);
							$records[] = (array) $r;
						}
						set_transient( 'mymail_dkim_records', $records, 900 );
					}

				}

				$found = false;
				if(is_array($records)){
					foreach((array) $records as $r){
						if($r['typeid'] === 'TXT' && preg_replace('#[^a-zA-Z0-9]#', '', $r['data']) == preg_replace('#[^a-zA-Z0-9]#', '', $record)){
							$found = $r;
							break;
						}
					}
				}
				if($found) : ?>

				<div class="verified"><p><?php echo sprintf(__('Domain %s', 'mymail'), '<strong>'.mymail_option('dkim_domain').'</strong>').', Selector: <strong>'.mymail_option('dkim_selector').'</strong>: '.__('verified', 'mymail'); ?></p>
				<?php else : ?>
				<div class="not-verified"><p><?php echo sprintf(__('Domain %s', 'mymail'), '<strong>'.mymail_option('dkim_domain').'</strong>').': '.__('not verified', 'mymail'); ?></p>
				<p><?php echo sprintf(__('No or wrong record found for %s. Please adjust the namespace records and add these lines:', 'mymail'), '<strong>'.$host.'</strong>'); ?>
				</p>
				<dl>
					<dt><strong><?php echo '_domainkey.'.mymail_option('dkim_domain'); ?></strong> IN TXT</dt>
						<dd><textarea class="widefat" rows="1" disabled>t=y;0=~</textarea></dd>
					<dt><strong><?php echo mymail_option('dkim_selector').'._domainkey.'.mymail_option('dkim_domain') ?></strong> IN TXT</dt>
						<dd><textarea class="widefat" rows="4" disabled><?php echo $record ?></textarea></dd>
				</dl>
				<?php endif; ?>
				</div>
			</td>
		</tr>
	<?php endif; ?>
		<tr valign="top">
			<th scope="row">DKIM Domain</th>
			<td><input type="text" name="mymail_options[dkim_domain]" value="<?php echo esc_attr(mymail_option('dkim_domain')); ?>" class="regular-text dkim">
			<span class="description"><?php _e('The domain you have set the TXT namespace records', 'mymail'); ?></span>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row">DKIM Selector</th>
			<td><input type="text" name="mymail_options[dkim_selector]" value="<?php echo esc_attr(mymail_option('dkim_selector')); ?>" class="regular-text dkim">
			<span class="description"><?php _e('The selector is used to identify the keys used to attach a token to the email', 'mymail'); ?></span>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row">DKIM Identity</th>
			<td><input type="text" name="mymail_options[dkim_identity]" value="<?php echo esc_attr(mymail_option('dkim_identity')); ?>" class="regular-text dkim">
			<span class="description"><?php _e('You can leave this field blank unless you know what you do', 'mymail'); ?></span>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row">DKIM Pass Phrase</th>
			<td><input type="text" name="mymail_options[dkim_passphrase]" value="<?php echo esc_attr(mymail_option('dkim_passphrase')); ?>" class="regular-text dkim">
			<span class="description"><?php _e('You can leave this field blank unless you know what you do', 'mymail'); ?></span>
			</td>
		</tr>
	</table>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><h4><?php _e('Keys', 'mymail'); ?></h4></th>
			<td>
			<p class="description">
			<?php _e('If you have defined the domain and a selector you have to generate a public and a private key. Once created you have to add some TXT namespace records at your mail provider', 'mymail'); ?>.
			<?php _e("DKIM often doesn't work out of the box. You may have to contact your email provider to get more information", 'mymail'); ?>.
			<?php _e("Changing namespace entries can take up to 48 hours to take affect around the world.", 'mymail'); ?>.
			<?php _e("It's recommend to change the keys occasionally", 'mymail'); ?>.
			<?php _e("If you change one of the settings above new keys are required", 'mymail'); ?>.
			<?php _e("Some providers don't allow TXT records with a specific size. Choose less bits in this case", 'mymail'); ?>.
			</p>
			</td>
		</tr>
	</table>
	<?php if($dkim && mymail_option('dkim_private_key') && mymail_option('dkim_public_key')) : ?>
	<table class="form-table" id="dkim_keys_active">
		<tr valign="top">
			<th scope="row">DKIM Public Key</th>
			<td><textarea name="mymail_options[dkim_public_key]" rows="10" cols="40" class="large-text code"><?php echo esc_attr(mymail_option('dkim_public_key')); ?></textarea>
		</tr>
		<tr valign="top">
			<th scope="row">DKIM Private Key
				<p class="description">
			<?php _e('Private keys should be kept private. Don\'t share them or post it somewhere', 'mymail'); ?>
			</p>
			</th>
			<td><textarea name="mymail_options[dkim_private_key]" rows="10" cols="40" class="large-text code"><?php echo esc_attr(mymail_option('dkim_private_key')); ?></textarea>
			<input type="hidden" name="mymail_options[dkim_private_hash]" value="<?php echo esc_attr(mymail_option('dkim_private_hash')); ?>" class="regular-text dkim"></td>
		</tr>
	</table>
	<?php endif; ?>
	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('new Keys', 'mymail'); ?></th>
			<td>
			<p class="dkim-create-keys">
				<?php $bitsize = mymail_option('dkim_bitsize', 512); ?>
				<?php _e('Bit Size', 'mymail'); ?>:
				<label> <input type="radio" name="mymail_options[dkim_bitsize]" value="512" <?php checked($bitsize, 512)?>> 512</label>&nbsp;
				<label> <input type="radio" name="mymail_options[dkim_bitsize]" value="768" <?php checked($bitsize, 768)?>> 768</label>&nbsp;
				<label> <input type="radio" name="mymail_options[dkim_bitsize]" value="1024" <?php checked($bitsize, 1024)?>> 1024</label>&nbsp;
				<label> <input type="radio" name="mymail_options[dkim_bitsize]" value="2048" <?php checked($bitsize, 2048)?>> 2048</label>&nbsp;
				<input type="submit" class="button-primary" value="<?php _e('generate Keys' ,'mymail') ?>" name="mymail_generate_dkim_keys" id="mymail_generate_dkim_keys" />
			</p>
			</td>
		</tr>
	</table>
	</div>
	</div>

	<?php if(!get_option('mymail_purchasecode_disabled') || !mymail_option('purchasecode')) : ?>

	<div id="tab-purchasecode" class="tab">
		<h3><?php _e('Purchasecode', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_purchasecode') ?>

	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Purchasecode', 'mymail'); ?></th>
			<td><input type="text" name="mymail_options[purchasecode]" value="<?php echo esc_attr(mymail_option('purchasecode')); ?>" class="regular-text" placeholder="XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX">
			<p class="description"><?php _e('Enter your purchasecode to enable automatic updates', 'mymail'); ?></p>
			</td>
		</tr>
		<?php do_action('mymail_purchasecode_tab'); ?>
		<tr valign="top">
			<th scope="row"><?php _e('Disable this tab' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_purchasecode_disabled" value="1"> <?php _e('Hide this tab if purchasecode has been entered', 'mymail') ?> <p class="description"><?php echo sprintf(__('If you disable this tab you cannot access it anymore from the settings page. If you would like to bring it back delete the %1$s option from the %2$s table', 'mymail'), '"mymail_purchasecode_disabled"', '"'.$wpdb->options.'"'); ?></p></label>
			</td>
		</tr>
	</table>
	</div>

	<?php else :?>

	<input type="hidden" name="mymail_options[purchasecode]" value="<?php echo esc_attr(mymail_option('purchasecode')); ?>">

	<?php endif;?>

	<div id="tab-advanced" class="tab">
		<h3><?php _e('Advanced Settings', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_advanced') ?>

	<p class="description"><?php _e('Some of these settings may affect your website. In normal circumstance it is not required to change anything on this page.', 'mymail') ?></p>

	<table class="form-table">
		<tr valign="top">
			<th scope="row"><?php _e('Disable Cache' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[disable_cache]" value="1" <?php checked(mymail_option('disable_cache'));?>> <?php _e('Disable Object Cache for MyMail', 'mymail') ?> <p class="description"><?php echo __('If enabled MyMail doesn\'t use cache anymore. This causes an increase in page load time! This option is not recommended!', 'mymail'); ?></p></label>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('remove Data' ,'mymail') ?></th>
			<td><label><input type="checkbox" name="mymail_options[remove_data]" value="1" <?php checked(mymail_option('remove_data'));?>> <?php _e('remove all data on plugin deletion', 'mymail') ?> <p class="description"><?php echo __('MyMail will remove all it\'s data if you delete the plugin via the plugin page.', 'mymail'); ?></p></label>
			</td>
		</tr>
		<tr valign="top">
			<th scope="row"><?php _e('PHP Mailer' ,'mymail') ?></th>
			<td>
			<?php $phpmailerversion = mymail_option('php_mailer'); ?>
			<label><?php _e('use version', 'mymail') ?>
			<select name="mymail_options[php_mailer]">
				<option value="0" <?php selected( !$phpmailerversion ); ?>><?php _e('included in WordPress', 'mymail') ?></option>
				<option value="5.2.7" <?php selected( '5.2.7', $phpmailerversion ); ?>>5.2.7</option>
				<option value="5.2.10" <?php selected( '5.2.10', $phpmailerversion ); ?>>5.2.10</option>
				<option value="5.2.14" <?php selected( '5.2.14', $phpmailerversion ); ?>>5.2.14</option>
			</select></label>
			</td>
		</tr>
	</table>
	</div>

	<div id="tab-system_info" class="tab">
		<h3><?php _e('System Info page', 'mymail'); ?></h3>

		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_system_info') ?>

		<p class="description"><?php echo sprintf(__( 'Please check out %s if you have problems with the plugin', 'mymail' ), '<a href="http://rxa.li/support?utm_source=MyMail+System+Info+Page" class="external">'.__('Support Page', 'mymail').'</a>'); ?></p>
		<textarea id="system_info_content" readonly class="code">
		</textarea>
		<p class="description"><?php _e( 'To copy the system info, click below then press Ctrl + C (PC) or Cmd + C (Mac).', 'mymail' ); ?></p>
	</div>

	<?php
	$extra_sections = apply_filters('mymail_setting_sections', array());

	foreach($extra_sections as $id => $name){ ?>
	<div id="tab-<?php echo $id; ?>" class="tab">
		<h3><?php echo $name ?></h3>
		<?php do_action('mymail_section_tab') ?>
		<?php do_action('mymail_section_tab_'.$id) ?>
	</div>
	<?php }?>
	<?php if(is_super_admin()) :?>
		<p class="resetbutton">
			<a onclick='return confirm("<?php _e('Do you really like to reset the options? This cannot be undone!', 'mymail'); ?>");' href="options-general.php?page=newsletter-settings&reset-settings=1&_wpnonce=<?php echo wp_create_nonce('mymail-reset-settings') ?>"><?php _e('Reset all settings', 'mymail'); ?></a>
		</p>
	<?php endif;?>
		<p class="submitbutton">
			<input type="submit" class="submit-form button-primary" value="<?php _e('Save Changes' ,'mymail') ?>" disabled />
		</p>
	</div>

	</div>

	<?php do_action('mymail_settings') ?>

	<input type="password" class="hidden" name="mymail_foo" value="bar"><input class="hidden" type="password" name="mymail_bar" value="foo">

	<input type="text" class="hidden" name="mymail_options[got_url_rewrite]" value="<?php echo esc_attr(mymail_option('got_url_rewrite')); ?>">
	<input type="text" class="hidden" name="mymail_options[profile_form]" value="<?php echo esc_attr(mymail_option('profile_form', 0)); ?>">
	<input type="text" class="hidden" name="mymail_options[ID]" value="<?php echo esc_attr(mymail_option('ID')); ?>">

	<br class="clearfix">
<span id="settingsloaded"></span>
</div>
</form>
