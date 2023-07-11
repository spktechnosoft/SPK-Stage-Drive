=== Plugin Name ===
Contributors: webheadllc
Donate Link: https://webheadcoder.com/donate-wp-cron-status-checker
Tags: cron, cron jobs, post missed schedule, scheduled posts, wp-cron, woocommerce, scheduled tasks, scheduler, no plugin updates, subscription, recurring, daily, weekly, monthly, billing, status, check, notify
Requires at least: 4.0
Tested up to: 4.7.3
Stable tag: 0.2
License: GPLv2 or later
License URI: http://www.gnu.org/licenses/gpl-2.0.html

If WP-Cron runs important things for you, you better make sure WP-Cron always runs!

== Description ==

**What happens if WP-Cron stops working?**

WordPress would never know if a new version is out.  Themes and plugins would never know if a new version is out.  You could login to your website everyday for months, and never see any notices about updates. If you're not careful you'll soon have an out of date WordPress installation susceptible to hackers.  Scheduled posts would never get published, auto drafts never deleted... you get the picture.  Your website would crumble up and die.

Use Woocommerce?  Subscriptions?  Customers would never get billed again!  Sessions would never be deleted.  Scheduled sales would never appear.  Your website would become bloated while giving away subscriptions for one time payments.

**WP-Cron is important.  So make sure it keeps working.**

Think of this plugin as insurance, but free.  WordPress, plugins, themes, and servers are all moving parts that can be updated at anytime.  You can't guarantee any of these changes won't break your website in some way.  While it's not likely WP-Cron will stop working, if it does, you better know about it.

Every 24 hours this plugin automatically checks to see if WP-Cron is still working.  Obviously, it does not depend on WP-Cron.  Instead it sets transients that expire over time.  If an unexpected error occurs the admin will get an email.

**What this plugin DOES NOT DO**

This plugin does not check if a job in the WP-Cron schedule fails.  It only checks that WordPress can run the WP-Cron system.


== Frequently Asked Questions ==

= I got an error saying "Unexpected HTTP response ..." =
This is an error you need to sort out with your web host or possibly other theme/plugin authors.  I got this error on my sites before (which is why I created this plugin) and I contacted my web host to resolve the issue.  

== Screenshots ==

1. WP-Cron is working
2. WP-Cron has an error.

== Changelog ==

= 0.2 =
changed getting current time to be more reliable when timezone is not set.  

= 0.1 =
Initial release.
