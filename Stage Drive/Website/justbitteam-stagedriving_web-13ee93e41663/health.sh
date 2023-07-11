#!/bin/bash

if ! pgrep "php-fpm" > /dev/null
then
    echo "php is not running"
    exit -2;
fi

if ! pgrep "nginx" > /dev/null
then
    echo "nginx is not running"
    exit -3;
fi

if ! curl -sSf http://localhost > /dev/null
then
    echo "site is not responding"
    exit -5;
fi

exit 0;
