<?php


if (isset($_COOKIE['accessToken'])){
    $token = $_COOKIE['accessToken'];
} else if (isset($_GET['t'])){
    $token = $_GET['t'];
} else {
    $token = '';
}


