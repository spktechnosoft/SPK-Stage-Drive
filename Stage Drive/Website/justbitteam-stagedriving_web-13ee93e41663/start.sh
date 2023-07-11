#!/bin/bash

if [ -z "$GITREPO" ]
then
  echo "Without git repo"
else
  if [ -d "/usr/share/nginx/www/web" ]
  then
    echo "Checking local copy revision"
    cd /usr/share/nginx/www
    currentRev=$(git rev-parse HEAD)
    echo "Current revision: $currentRev"

    if [ "$currentRev" != "$REPOHASH" ]
    then
      echo "Found, pull from origin.."

      git pull origin master
      if [ $? -ne 0 ]; then
        echo "Unable to pull from origin"
        exit
      fi
      git checkout -f $REPOHASH
      if [ $? -ne 0 ]; then
        echo "Unable to checkout"
        exit
      fi
    else
      echo "Already built, skipping"
      #echo "$repo_name $commit_branch#$repo_hash already built!" | slacktee.sh -a "warning" -t "$repo_name" -e "Date and Time" "$(date)" -e "Git revision" "$commit_link" -e "Build log" "http://build.justbit.it/logs/build-$repo_sname-$timestamp.log" -e "Docker image" "eu.gcr.io/justbit-cloud/$repo_sname:$repo_hash"
    fi
  else
    echo "Not found, cloning git clone $GITREPO"

    git clone $GITREPO /usr/share/nginx/www
    if [ $? -ne 0 ]; then
      echo "Unable clone repository"
      exit
    fi
    cd /usr/share/nginx/www
    git pull origin master
    if [ $? -ne 0 ]; then
      echo "Unable to pull from origin"
      exit
    fi
    git checkout -f $REPOHASH
    if [ $? -ne 0 ]; then
      echo "Unable to checkout"
      exit
    fi
  fi

fi

echo "DB_NAME=$DB_NAME
DB_USER=$DB_USER
DB_PASSWORD=$DB_PASSWORD
DB_HOST=$DB_HOSTNAME
WP_ENV=$ENV_MODE
WP_HOME=$WP_DOMAIN
WP_SITEURL=$WP_DOMAIN/wp" > /usr/share/nginx/www/.env

# if [ "$BUCKET_PROVIDER" = "s3" ] && [ -z "$AWS_KEY" ] && [ -z "$AWS_SECRET" ]
# then
#   echo $AWS_KEY:$AWS_SECRET > /etc/passwd-s3fs
#   chmod 600 /etc/passwd-s3fs
#
#   echo "
# [program:s3fuse]
# command=/usr/bin/mount-s3.sh
# priority=1001
# stdout_events_enabled=true
# stderr_events_enabled=true
# stdout_logfile=/dev/stdout
# stdout_logfile_maxbytes=0
# stderr_logfile=/dev/stderr
# stderr_logfile_maxbytes=0" >> /etc/supervisord.conf
# fi
#
# if [ "$BUCKET_PROVIDER" = "gcs" ]
# then
#   echo"
# [program:gcsfuse]
# command=/usr/bin/mount-gcs.sh
# priority=1001
# stdout_events_enabled=true
# stderr_events_enabled=true
# stdout_logfile=/dev/stdout
# stdout_logfile_maxbytes=0
# stderr_logfile=/dev/stderr
# stderr_logfile_maxbytes=0" >> /etc/supervisord.conf
# fi

if [ "$BUCKET_PROVIDER" = "gcswp" ]
then
  echo "Copying gcs plugin"
  cp -rf /tmp/gcs /usr/share/nginx/www/web/app/mu-plugins/gcs

  echo "
  GOOGLE_APPLICATION_CREDENTIALS=$GOOGLE_APPLICATION_CREDENTIALS" >> /usr/share/nginx/www/.env
fi

if [ -z "$DEBUG_HOST" ]
then
  echo "Starting without debugger"
else
  echo "xdebug.show_error_trace=1" /etc/php/7.0/mods-available/xdebug.ini
  echo 'xdebug.remote_port=9000' >> /etc/php/7.0/mods-available/xdebug.ini
  echo 'xdebug.remote_enable=1' >> /etc/php/7.0/mods-available/xdebug.ini
  echo 'xdebug.remote_connect_back=0' >> /etc/php/7.0/mods-available/xdebug.ini
  echo 'xdebug.remote_host='$DEBUG_HOST >> /etc/php/7.0/mods-available/xdebug.ini
  echo 'xdebug.remote_enable=on' >> /etc/php/7.0/mods-available/xdebug.ini
  echo 'xdebug.remote_autostart=on' >> /etc/php/7.0/mods-available/xdebug.ini
fi

if [ -n "$RUN_COMPOSER" ]
then
cd /usr/share/nginx/www
composer install
chown -R www-data:www-data /usr/share/nginx/www/web
fi

# start all the services
/usr/local/bin/supervisord -n
