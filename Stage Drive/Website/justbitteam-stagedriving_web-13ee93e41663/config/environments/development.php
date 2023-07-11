<?php
// ini_set('xdebug.collect_vars', 'on');
// ini_set('xdebug.collect_params', '4');
// ini_set('xdebug.dump_globals', 'on');
// ini_set('xdebug.dump.SERVER', 'REQUEST_URI');
// ini_set('xdebug.show_local_vars', 'on');
ini_set('show_exception_trace', '0');
ini_set('show_error_trace', '0');
ini_set('xdebug.remote_autostart', '0');
ini_set('xdebug.remote_enable', '0');
/* Development */
define('SAVEQUERIES', true);
define('WP_DEBUG', true);
define('SCRIPT_DEBUG', true);
define('DISABLE_WP_CRON', false);
define('ALTERNATE_WP_CRON', false);
define('ALLOW_UNFILTERED_UPLOADS', true );

define('SD_BASE_URI', 'https://api.stagedriving.com/');
//define('SD_BASE_URI', 'http://localhost:8080/');
