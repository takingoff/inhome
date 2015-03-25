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
		/* 		$('#ff').form('submit',
		 {
		 success : function(data)
		 {
		 if(data==null||data=='')
		 {
		 window.location.href = 'staff/controlCenter.do';
		 }
		 else
		 {
		 $.messager.alert('登录失败', data);
		 authCodeClick();
		 }
		 }
		 }); */
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
	<h2>${staticRes }</h2>
	<h2>${dynamicRes}</h2>
	<h2>${basePath }</h2>
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<table class="table">
				<thead>
					<tr>
						<th>
							编号
						</th>
						<th>
							产品
						</th>
						<th>
							交付时间
						</th>
						<th>
							状态
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							1
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							01/04/2012
						</td>
						<td>
							Default
						</td>
					</tr>
					<tr class="success">
						<td>
							1
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							01/04/2012
						</td>
						<td>
							Approved
						</td>
					</tr>
					<tr class="error">
						<td>
							2
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							02/04/2012
						</td>
						<td>
							Declined
						</td>
					</tr>
					<tr class="warning">
						<td>
							3
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							03/04/2012
						</td>
						<td>
							Pending
						</td>
					</tr>
					<tr class="info">
						<td>
							4
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							04/04/2012
						</td>
						<td>
							Call in to confirm
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
	<h1>管理员登录</h1>
	<div  title="管理员登录" style="width:400px">
		<div id="loginPanelId" >
			<form id="ff" method="post" action="common/login.do">
				<table cellpadding="5"></table>
				<blockquote>
					<br>
				</blockquote>
				<table cellpadding="5">
					<tr>
						<td>用户名:</td>
						<td><input  autocomplete="off" class="easyui-validatebox textbox" name="name" data-options="required:true" ></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input  autocomplete="off" class="easyui-validatebox" type="password" name="password"  data-options="required:true"  ></td>
					</tr>
					<tr>
						<td></td>
						<td ><img id="authImag" style="padding-left:20px;" src="common/authCode.do" onclick="authCodeClick()"></img></td>
					</tr>
					<tr>
						<td>验证码:</td>
						<td><input class="textbox" name="authCode"></td>
					</tr>
					<tr>
						<td></td>
						<td ><a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style=" position: relative; float: left">确定</a> 
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()"style=" position: relative; float: right;">清除</a></td>
					</tr>
				</table>
			</form>
			</div>
		</div>
	</div>
</center>

	<div id="dialog"></div>

</body>
</html>
