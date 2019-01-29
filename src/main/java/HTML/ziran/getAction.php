<?php
header('Content-Type:application/json');  //此声明非常重要
include_once "config.php";
//echo $_GET['sitename'];
//$posts = R::find( 'siterule',"site_name=?",[$_GET['sitename']   ]);
$posts = R::getAll( 'SELECT * FROM siterule WHERE site_name = :site_name',
        [':site_name' => $_GET['sitename'] ]
    );

$pass = R::getAll( 'SELECT * FROM sitepassword WHERE sitename = :site_name',
        [':site_name' => $_GET['sitename'] ]
    );
//print_r($posts[0]);
$arrayName = array('siterule' => $posts[0], 'password'=> $pass);
echo (json_encode($arrayName));



