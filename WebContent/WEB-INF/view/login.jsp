<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理员登录</title>

<link rel="stylesheet" type="text/css" href="static/easyUi/default.css">
<link rel="stylesheet" type="text/css" href="static/easyUi/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="static/easyUi/themes/icon.css">
<script type="text/javascript" src="static/easyUi/jquery.min.js"></script>
<script type="text/javascript" src="static/easyUi/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyUi/locale/easyui-lang-zh_CN.js"></script>

<script>
	function submitForm()
	{
		if ($('#ff').form('validate') == false) { return; }
		$.ajax(
		{
			url : 'common/login.do',
			data : $('#ff').serialize(),
			type : 'POST',   //这个地方如果不用post ie浏览器中登录退出后不会访问url直接进入success中导致无法登录。
			success : function(data)
			{
				if (data == null || data == '')
				{
					window.location.href = 'common/controlCenter.do';
				}
				else
				{
					$.messager.alert('登录失败', data);
					//$( "#dialog" ).dialog();
					authCodeClick();
				}
			}
		});
	}
	function clearForm()
	{
		$('#ff').form('clear');
	}
	function authCodeClick()
	{
		$('#authImag').attr('src', 'common/authCode.do?' + Math.random());
	}
</script>

<style type="text/css">

@font-face { 
    font-family: "myFont";	 
    src: url("dynamic/image/SNOWREN.ttf");
}
*{
	font-size:12px;
}
body {
    padding:130px;
    font-size:12px;
    margin:0;
    background-image: url("dynamic/image/login.png");
    background-size:100% 100%;
}
h1
{
	font-size:36px;
	color: #FFDDDD;
}
h2 {
    font-size:18px;
    font-weight:bold;
    margin:0;
    margin-bottom:15px;
}
td{
	font-family: myFont;
	font-size: 16px;
}
body{
	font-family: myFont;
	color: #FFDDDD;
}

</style>

</head>

<body>
<center>
	<h2>${ctx }</h2>
	<h2>${basePath }</h2>
	<h1>管理员登录</h1>
	
<!-- 	<div  title="管理员登录" style="width:400px"> -->
<!-- 		<div id="loginPanelId" > -->
<!-- 			<form id="ff" method="post" action="common/login.do"> -->
<!-- 				<table cellpadding="5"></table> -->
<!-- 				<table cellpadding="5"> -->
<!-- 					<tr> -->
<!-- 						<td>用户名:</td> -->
<!-- 						<td><input  autocomplete="off" class="easyui-validatebox textbox" name="name" data-options="required:true" ></td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>密码:</td> -->
<!-- 						<td><input  autocomplete="off" class="easyui-validatebox" type="password" name="password"  data-options="required:true"  ></td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td></td> -->
<!-- 						<td ><img id="authImag" style="padding-left:20px;" src="common/authCode.do" onclick="authCodeClick()"></img></td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>验证码:</td> -->
<!-- 						<td><input class="textbox" name="authCode"></td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td></td> -->
<!-- 						<td ><a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style=" position: relative; float: left">确定</a>  -->
<!-- 						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()"style=" position: relative; float: right;">清除</a></td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</form> -->
<!-- 			</div> -->
<!-- 		</div> -->
	
	<div class="container" style="width:45%;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<form class="form-horizontal" role="form" id="ff" method="post" action="common/login.do">
				<div class="form-group">
					 <label for="name" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" name="name" class="form-control" id="name" />
					</div>
				</div>
				<div class="form-group">
					 <label for="password" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" name="password" class="form-control" id="password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-10">
						<img id="authImag" style="padding-left:20px;" src="common/authCode.do" onclick="authCodeClick()"></img>
					</div>
				</div>
				<div class="form-group">
					 <label for="auth" class="col-sm-2 control-label">验证码</label>
					<div class="col-sm-10">
						<input type="text" name="authCode" class="form-control" id="auth" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							 <label><input type="checkbox" />记住我</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <button type="submit" class="btn btn-default">登录</button>
						 <button onclick="clearForm()" class="btn btn-default">清空</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
		
		
</center>

	<div id="dialog"></div>

</body>
</html>
