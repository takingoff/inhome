
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////此文件函数要慎重修改，（多个页面使用文件中的函数）
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///****************************************************************begin pagination
//尽量使用destroyDialog

function closeDialog(id)
{
	$("#" + id).dialog("close");
}
function destroyDialog(id)
{
	$("#" + id).dialog("destroy");
}
function openMessageDialog(message,title)
{
	$("<div title='" + title + "'><p>" + message + "</p></div>").dialog(
	{
		width : 100,
		height : 160,
		buttons :
		{
			确定 : function()
			{
				$(this).dialog("destroy");
			}
		}
	});
}
// 验证码请求
function authCodeClick(id)
{
	$('#' + id).attr('src', '/InnMIS/common/authCode.do?' + Math.random());
}
// 验证码确认后执行
function openConfirmDialog(message,title,Func)
{
	$(
			"<div id=\"confirmDialog\" title='" + title + "'><p>" + message + "</p>" + "<table><tr><td><img id='authImag' style='padding-left:20px;' src='/InnMIS/common/authCode.do' " + "onclick='authCodeClick(\"authImag\")'></img></td></tr>"
					+ "<tr><td><input id=\"authCodeInput\" name=\"authCode\"/></td></tr></div>").dialog(
	{
		width : 220,
		height : 220,
		modal:true,
		buttons :
		{
			继续 : function()
			{
				$.ajax(
				{
					url : '/InnMIS/common/checkAuthCode.do',
					data : "authCode=" + $('#authCodeInput').val(),
					type : 'POST', // 这个地方如果不用post
					success : function(data)
					{
						if (data == null || data == '')
						{
							Func();
							$("#confirmDialog").dialog("destroy"); // 必须要destroy
							// 不能够close
						}
						else
						{
							authCodeClick('authImag');
						}
					}
				});
			},
			取消 : function()
			{
				$(this).dialog("destroy");// 必须要destroy 不能够close
			}
		}
	});
}

//删除单个
function deleteAEntity(entityFormId,id,entityDialogId,paginationDIV,url)
{

	var idJson = "[\""+$("#"+entityFormId+" input[name="+id+"]").val()+"\"]";
	deleteEntity(paginationDIV,url,idJson);
	destroyDialog(entityDialogId);	//单个删除后关闭对话框。
}


//ajax 删除。参数为id的json形式
function deleteEntity(paginationDIV,url,idJson)
{
	openConfirmDialog("确定要删除吗？", "确认", function()
	{
		$.ajax(
		{
			url : url,
			data : idJson,
			contentType : "application/json",
			type : 'POST',
			success : function(data)
			{
				openMessageDialog(data, "删除提示");
				paginationDIVRefresh(paginationDIV);
			}
		});
	});
}

function paginationDIVRefresh(paginationDIV)
{
	//更新标记
	$("#tools_"+paginationDIV+"_drop_select_launcher").button(
	{
		label : "标记:0",
	});
	//清除标记数据
	$("#"+paginationDIV).data('jui_datagrid_status')['a_selected_ids'] = [];
	$("#"+paginationDIV).data('jui_datagrid_status')['count_selected_ids'] = 0;
	//设置为第一页 会产生一次刷新
	$("#"+paginationDIV).jui_datagrid(
	{
		pageNum : 1,
	});

}


