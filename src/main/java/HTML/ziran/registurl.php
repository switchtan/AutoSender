<html>
<head>
	<meta charset="UTF-8">
</head>
<body>


<?php
include_once "config.php";
//R::ext('setEnforceUTF8encoding');
//R::setEnforceUTF8encoding(true);
$posts = R::find( 'siterule' );
foreach ($posts as $item) {
	# code...
	echo '<a href=page.php?id='.$item->loginUrl.'>'.$item->site_name."</a><br>";
}
?>
</body>
</html>

