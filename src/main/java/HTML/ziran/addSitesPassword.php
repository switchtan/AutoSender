<?php
include_once "config.php";
$book  = R::findAll( 'siterule');
if(!count($book)) die("The cellar is empty!\n");
?>
<html>
<head>
<meta charset="UTF-8">
<title>添加站点密码</title>
</head>
<body>

	<div>
		<form class="form-horizontal" action="addSitePassword_action.php" method="post">


			<fieldset>
				<div id="legend" class="">
					<input type="hidden" name="form_name" value="" class="leipiplugins" leipiplugins="form_name">
					<legend class="leipiplugins-orgvalue"><h3>添加用户密码</h3></legend>
				</div>
				<div class="control-group">
					<!-- Select -->
					<label class="control-label leipiplugins-orgname">选择站点</label>
					<div class="controls">
						
							<?php
								foreach($book as $b){
								    echo '<label><input name="sitename" type="radio" value="'.$b->site_name.'" />'.$b->site_name.' </label> ';
								    echo '<a href='.$b->loginUrl.' target="_blank">注册地址 </a> <br>';
								}

							?>
							
					</div>

				</div>

				<br>
				<div class="control-group">
					<!-- Text -->
					<label class="control-label leipiplugins-orgname">用户名</label>
					<div class="controls">
						<input name="username" type="text" placeholder="默认值" title="文本框" value="" class="leipiplugins" leipiplugins="text">
					</div>

				</div>

				<br>
				<div class="control-group">
					<!-- Text -->
					<label class="control-label leipiplugins-orgname">密码</label>
					<div class="controls">
						<input name="password" type="text" placeholder="默认值" title="文本框" value="" class="leipiplugins" leipiplugins="text">
					</div>

				</div>


				<br>
				<input type="submit" value="提交" />

			</fieldset>

		</form>
	</div>
</body>
</html>

