<?php
include_once "rb-mysql.php";
R::setup( 'mysql:host=localhost;dbname=top6681;CharSet=utf8', 'top6681', 'guavaguava00' );
//R::ext('setEnforceUTF8encoding');
//R::setEnforceUTF8encoding(true);
$post = R::dispense( 'post' );
$post->title	= $_POST['title'];
$gcontent=$_POST['content'];
echo $gcontent;
$post->gcontent = $gcontent;
$post->posttime = date("Y-m-d H:i:s");
$id	=	R::store($post);
