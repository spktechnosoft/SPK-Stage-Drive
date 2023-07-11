=== MyMail AmazonSES Integration ===
Contributors: revaxarts
Tags: amazon, ses, mymail, delivery, deliverymethod, newsletter, email, revaxarts, mymailesp
Requires at least: 3.7
Tested up to: 4.4
Stable tag: 1.1.6
License: GPLv2 or later

== Description ==

> This Plugin requires [MyMail Newsletter Plugin for WordPress](http://rxa.li/mymail?utm_source=AmazonSES+integration+for+MyMail)

Uses Amazon's Simple Email Service (SES) to deliver emails for the [MyMail Newsletter Plugin for WordPress](http://rxa.li/mymail?utm_source=AmazonSES+integration+for+MyMail).

== Installation ==

1. Upload the entire `mymail-amazon-ses-integration` folder to the `/wp-content/plugins/` directory
2. Activate the plugin through the 'Plugins' menu in WordPress
3. Go to Settings => Newsletter => Delivery and select the `AmazonSES` tab
4. Enter your credentials
5. Send a testmail

== Changelog ==

= 1.1.6 =
* added dropdown for better understanding which API is used

= 1.1.5 =
* fixed: PHP Warning when fsockopen failed

= 1.1.4 =
* SVN problems, sorry

= 1.1.3 =
* fixed issue with double Return-Path on older PHPMailer versions

= 1.1.2 =
* fixed missing Return-Path via WEB API

= 1.1.1 =
* fixed issue with secure connection on some servers

= 1.1 =
* add option to view and verify email addresses

= 1.0.5 =
* sending via SMTP is now faster

= 1.0.4 =
* small fix for MyMail 2

= 1.0.3 =
* set 'sslverify' to false to prevent some SSLRead() errors
* sending now automatically retries after a server timeout

= 1.0.2 =
* added missing function

= 1.0.1 =
* increased timeout to prevent errors
* fixed a bug where mails are not send at an early stage of the page load
* fixed an unknown error on save in the settings

= 1.0.0 =
* changed the whole plugin to a class based one for less global functions
* added some default values upon plugin activation

= 0.7.0 =
* added option to define the endpoint: http://docs.aws.amazon.com/ses/latest/DeveloperGuide/smtp-connect.html

= 0.6.0 =
* added port check for SMTP connection

= 0.5.8 =
* scripts and styles only get loaded on the settings page now

= 0.5.7 =
* update to work with attachments

= 0.5.6 =
* small update for MyMail 1.4

= 0.5.5 =
* small bug fixes and text corrections

= 0.5.4 =
* small bug fixes and readme update

= 0.5.3 =
* send delay gets rounded

= 0.5.2 =
* added verfication check
* small bug fix

= 0.5.1 =
* cron job bug fixed

= 0.5 =
* altered plugin path

= 0.4 =
* added version constant

= 0.3 =
* small folder structure updates

= 0.2 =
* added Javascript

= 0.1 =
* initial release

== Upgrade Notice ==

== Additional Info One ==

This Plugin requires [MyMail Newsletter Plugin for WordPress](http://rxa.li/mymail?utm_source=AmazonSES+integration+for+MyMail)

