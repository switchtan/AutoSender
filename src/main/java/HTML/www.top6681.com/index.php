<html>
<head>
	<meta charset="UTF-8">
</head>
<body>


<?php
include_once "config.php";
//R::ext('setEnforceUTF8encoding');
//R::setEnforceUTF8encoding(true);
$posts = R::find( 'post' );
foreach ($posts as $item) {
	# code...
	echo '<a href=page.php?id='.$item->id.'>'.$item->title."</a><br>";
}
?>
</body>
</html>

