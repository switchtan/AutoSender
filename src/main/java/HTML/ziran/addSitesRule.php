<?php
include_once "config.php";

//R::ext('setEnforceUTF8encoding');
//R::setEnforceUTF8encoding(true);
$post = R::dispense( 'siterule' );
$post->siteName			= "iteye";
$post->loginUrl			= "https://www.iteye.com/login";
$post->loginSuccesUrl	= "https://www.iteye.com/";
$post->loginUserTag     = "user_name";
$post->loginPassTag	    = "password";
$post->loginBtnTag		= "button";
//$post->LoginUserName	= "";
//$post->loginPassword	= "";

//$post->PostTitle		= "";
//$post->PostContent		= "";
$post->PostContentTag	= "editor_body";
$post->PostTitleTag		= "blog_title";
$post->PostSelectTag	= "blog_whole_category_id";
//$post->PostSelectIndex	= "";
$post->PostButtonTag	= "submit_button";

$post->baseUrlTag		= ".login-fr a";
$post->endUrl			= "/admin/blogs/new";
$id	=	R::store($post);
