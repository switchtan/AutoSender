<?php
include_once "config.php";

//R::ext('setEnforceUTF8encoding');
//R::setEnforceUTF8encoding(true);
$post = R::dispense( 'post' );
$post->title	= $_GET['title'];
$gcontent=$_GET['content'];
echo $gcontent;
$post->gcontent = $gcontent;
$post->posttime = date("Y-m-d H:i:s");
$id	=	R::store($post);
