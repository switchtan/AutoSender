<html>
<head>
	<meta charset="UTF-8">
</head>
<body>


<?php
include_once "config.php";
//R::ext('setEnforceUTF8encoding');
//R::setEnforceUTF8encoding(true);
$posts = R::findOne( 'post',"id=".$_GET['id'] );
echo $posts->title."<br>";
echo $posts->gcontent;
?>
</body>
</html>

