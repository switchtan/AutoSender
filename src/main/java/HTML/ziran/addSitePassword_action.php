<?php
include_once "config.php";

//R::ext('setEnforceUTF8encoding');
//R::setEnforceUTF8encoding(true);
$post = R::dispense( 'sitepassword' );
$post->sitename	= $_POST['sitename'];
$post->username	= $_POST['username'];
$post->password	= $_POST['password'];
$id	=	R::store($post);
