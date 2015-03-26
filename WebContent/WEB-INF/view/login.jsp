<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="decorator" content="default"/>
<title>管理员登录</title>
<style type="text/css">
	@font-face { 
	    font-family: "myFont";	 
	    src: url("dynamic/image/SNOWREN.ttf");
	}
	*{
		font-size:12px;
	}
	body {
	    padding:60px;
	    font-size:14px;
	    margin:0;
	    background-image: url("dynamic/image/login.png");
	    background-size:100% 100%;
	}
	h1
	{
		font-size:36px;
		color: #FFDDDD;
	}
	h4 ,.modal-body
	{
		font-size:26px;
		color: #000000;
	}
	body{
		font-family: myFont;
		color: #FFDDDD;
	}
</style>
<script>
	function submitForm()
	{
		$(".modal-body").html("正在登录。。。。");
		$("#modalAlert").modal({
			show:true,
		});
		
		$.ajax(
		{
			url : 'common/login.do',
			data : $('#ff').serialize(),
			type : 'POST',  
			success : function(data)
			{
				if (data == null || data == '')
				{
					window.location.href = 'common/controlCenter.do';
				}
				else
				{
					$(".modal-body").html(data);
					$("#modalAlert").modal({
						show:true,
					});
					authCodeClick();
				}
			}
		});
	}
	function clearForm()
	{
		$('#ff')[0].reset();
	}
	function authCodeClick()
	{
		$('#authImag').attr('src', 'common/authCode.do?' + Math.random());
	}
	
</script>

</head>

<body>
<center>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="modalAlert" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" 
	               aria-hidden="true">×
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	              <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> 信息
	            </h4>
	         </div>
	         <div class="modal-body">
	            按下 ESC 按钮退出。
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" 
	               data-dismiss="modal">关闭
	            </button>
	         </div>
	      </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<p>${ctx }</p>
	<p>${basePath }</p>
	<h1>管理员登录</h1>
	<div class="container" style="width:45%;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<form class="form-horizontal" role="form" id="ff" method="post" action="common/login.do">
				<div class="form-group">
					<div class="col-sm-8 input-group">
						 <span class="input-group-addon">用户名</span>
						<input type="text" name="name" class="form-control " id="name" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-8 input-group">
						<span class="input-group-addon">密@码</span>
						<input type="password" name="password" class="form-control " id="password" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-10">
						<img id="authImag" style="padding-left:20px;" src="common/authCode.do" onclick="authCodeClick()"></img>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-8 input-group">
						<span class="input-group-addon">验证码</span>
						<input type="text" name="authCode" class="form-control" id="auth" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<div class="checkbox">
							 <label><input type="checkbox" />记住我</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						 <button type="button" onclick="submitForm()" class="btn  btn-primary">登录</button>
						 <button type="button" onclick="clearForm()" class="btn  btn-danger">清空</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
		
</center>


</body>
</html>
