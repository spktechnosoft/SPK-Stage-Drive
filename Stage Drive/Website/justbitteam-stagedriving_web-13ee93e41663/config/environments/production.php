<?php
/* Production */
ini_set('display_errors', 0);
define('WP_DEBUG_DISPLAY', false);
define('SCRIPT_DEBUG', false);
define('DISALLOW_FILE_MODS', true); // this disables all file modifications including updates and update notifications
define('DISABLE_WP_CRON', true);
define( 'ALLOW_UNFILTERED_UPLOADS', true );

define('FORCE_SSL_ADMIN', true);
if (strpos($_SERVER['HTTP_X_FORWARDED_PROTO'],'https') !== false) {
  $_SERVER['HTTPS']='on';
}

define('SD_BASE_URI', 'https://api.stagedriving.com/');
