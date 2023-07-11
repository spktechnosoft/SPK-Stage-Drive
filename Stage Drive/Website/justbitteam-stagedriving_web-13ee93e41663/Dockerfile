FROM ubuntu:16.04
LABEL Simone Notargiacomo <simone.notargiacomo@justbit.it>

# Keep upstart from complaining
RUN dpkg-divert --local --rename --add /sbin/initctl
RUN ln -sf /bin/true /sbin/initctl

# Let the conatiner know that there is no tty
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get -y update
RUN apt-get -y install software-properties-common python-software-properties
#RUN echo "deb http://ppa.launchpad.net/ondrej/php/ubuntu trusty main" >> /etc/apt/sources.list && \
#    apt-key adv --keyserver keyserver.ubuntu.com --recv-key E5267A6C && \
#    apt-get update

#RUN apt-get -y upgrade

# Basic Requirements
RUN apt-get -y update && apt-get -y install nginx php-fpm php-mysql php-mysqli php7.0-opcache php-apcu php-xdebug pwgen \
    python-setuptools curl git unzip automake autotools-dev g++ \
    libcurl4-gnutls-dev libfuse-dev libssl-dev libxml2-dev make pkg-config

# Wordpress Requirements
RUN apt-get -y update && apt-get -y install php-curl php-gd php-intl php-pear php-cli \
    php-imagick php-imap php-mcrypt php-memcache php-pspell \
    php-recode php-sqlite3 php-tidy php-xmlrpc php-xsl \
    php-soap php-zip php-mbstring

RUN apt-get clean
RUN rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Composer
RUN curl -sS https://getcomposer.org/installer | php
RUN mv composer.phar /usr/local/bin/composer

# Tweaks to allow to write to host's volume
RUN usermod -u 1000 www-data
RUN usermod -G staff www-data

# nginx config
RUN sed -i -e"s/keepalive_timeout\s*65/keepalive_timeout 2/" /etc/nginx/nginx.conf
RUN sed -i -e"s/keepalive_timeout 2/keepalive_timeout 2;\n\tclient_max_body_size 100m/" /etc/nginx/nginx.conf
#RUN sed -i -e"s/access_log \/var\/log\/nginx\/access.log;/access_log \/dev\/stdout\//" /etc/nginx/nginx.conf
#RUN sed -i -e"s/error_log \/var\/log\/nginx\/error.log;/error_log \/dev\/stderr\//" /etc/nginx/nginx.conf
RUN echo "daemon off;" >> /etc/nginx/nginx.conf
RUN ln -sf /dev/stdout /var/log/nginx/access.log && ln -sf /dev/stderr /var/log/nginx/error.log

# php-fpm config
RUN sed -i -e "s/;cgi.fix_pathinfo=1/cgi.fix_pathinfo=0/g" /etc/php/7.0/fpm/php.ini
RUN sed -i -e "s/upload_max_filesize\s*=\s*2M/upload_max_filesize = 100M/g" /etc/php/7.0/fpm/php.ini
RUN sed -i -e "s/post_max_size\s*=\s*8M/post_max_size = 100M/g" /etc/php/7.0/fpm/php.ini
RUN sed -i -e "s/;daemonize\s*=\s*yes/daemonize = no/g" /etc/php/7.0/fpm/php-fpm.conf
RUN sed -i -e "s/;catch_workers_output\s*=\s*yes/catch_workers_output = yes/g" /etc/php/7.0/fpm/pool.d/www.conf
RUN sed -i -e "s/;pm.max_requests\s*=\s*500/pm.max_requests = 500/g" /etc/php/7.0/fpm/pool.d/www.conf
RUN sed -i -e "s/pm.max_children = 5/pm.max_children = 30/g" /etc/php/7.0/fpm/pool.d/www.conf
RUN sed -i -e "s/pm.max_spare_servers = 3/pm.max_spare_servers = 30/g" /etc/php/7.0/fpm/pool.d/www.conf
RUN sed -i '/error_log /c error_log = /proc/self/fd/2' /etc/php/7.0/fpm/php-fpm.conf
RUN find /etc/php/7.0/cli/conf.d/ -name "*.ini" -exec sed -i -re 's/^(\s*)#(.*)/\1;\2/g' {} \;
RUN mkdir -p /run/php

# nginx site conf
COPY ./nginx.conf /etc/nginx/sites-available/default

# Healthcheck
COPY ./health.sh /health.sh
RUN chmod 755 /health.sh

# cron
COPY ./cron /etc/cron.d/wp

# Supervisor Config
RUN /usr/bin/easy_install supervisor
RUN /usr/bin/easy_install supervisor-stdout
COPY ./supervisord.conf /etc/supervisord.conf

# Wordpress Initialization and Startup Script
#COPY ./gcs /tmp/gcs
COPY ./start.sh /start.sh
RUN chmod 755 /start.sh

# private expose
EXPOSE 80

# volume for wordpress install
#VOLUME ["/usr/share/nginx/www/wp-content/uploads"]
# nginx site override conf
COPY ./nginx.conf /etc/nginx/sites-available/default

# Copying web content
COPY . /usr/share/nginx/www
RUN chown -R www-data:www-data /usr/share/nginx/www

WORKDIR /usr/share/nginx/www
RUN composer update

CMD ["/bin/bash", "/start.sh"]
