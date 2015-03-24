//最初显示的是iframeRooom
$(function()
{
	displayAiframe("iframeRoom");
});
// 显示某个iframe
function displayAiframe(id)
{
	$("iframe").hide();
	$("#" + id).show();
	if (id == "iframeRoom" || id == "iframeRoomPage")
	{
		$("#neckId").show();
	}
	else
	{
		$("#neckId").hide();
	}
}

// 设置iframe的高度(自适应)
function iframeHeight(id)
{
	$('#' + id).height($(window).height() - $('#tabs').height() - 40);
}
// 请求URL1 返回刷行URL2
function urlRequestAndBack(url1,data,url2)
{
	$.ajax(
	{
		url : url1,
		type : 'POST',
		data : data,
		success : function(data)
		{
			window.location.href = url2;
		}
	});
}
// 改变背景
function changeInnEntry(key,id)
{
	var url = '/InnMIS/common/changeInnEntry.do';
	var data =
	{
		key : key,
		value : $("#" + id).val()
	};
	urlRequestAndBack(url, data, '/InnMIS/common/controlCenter.do');
}
// 刷新按钮
function freshClick()
{
	window.location.href = '/InnMIS/common/controlCenter.do';
}
// 退出登录
function staffLogout()
{
	window.location.href = '/InnMIS/common/logout.do';
}
// 刷新房态
function refreshRoomTrend()
{
//	document.getElementById('iframeRoom').contentWindow.refresh();
	frames["iframeRoom"].refresh();
}

var showRoomTrend = true;
var roomPageHasRequest = false;
function changeRoomView()
{
	if (showRoomTrend)
	{
		displayAiframe("iframeRoomPage");
		showRoomTrend = false;
		if (roomPageHasRequest)
		{
			return;
		}
		else
		{
			$("#iframeRoomPage").attr("src", "/InnMIS/room/roomPageView.do");
			roomPageHasRequest = true;
		}
	}
	else
	{
		displayAiframe("iframeRoom");
		frames["iframeRoom"].refresh();
		showRoomTrend = true;
	}
}
function displayRoomiframe()
{
	if (showRoomTrend)
	{
		displayAiframe("iframeRoom");
	}
	else
	{
		displayAiframe("iframeRoomPage");
	}
}

//房间类型的iframe 操作
var roomTypeHasBeRequest = false;
function displayRoomTypeiframe()
{
	displayAiframe("iframeRoomType");
	if (roomTypeHasBeRequest)
		return;
	$("#iframeRoomType").attr("src", "/InnMIS/roomType/roomType.do");
	roomTypeHasBeRequest = true;
}

//房间类型的iframe 操作
var enteredInfoHasBeRequest = false;
function displayEnteredInfoiframe()
{
	displayAiframe("iframeEnteredInfo");
	if (enteredInfoHasBeRequest)
		return;
	$("#iframeEnteredInfo").attr("src", "/InnMIS/entered/enteredPageView.do");
	enteredInfoHasBeRequest = true;
}

//房间类型的iframe 操作
var billHasBeRequest = false;
function displayBilliframe()
{
	displayAiframe("iframeBill");
	if (billHasBeRequest)
		return;
	$("#iframeBill").attr("src", "/InnMIS/common/billPage.do");
	billHasBeRequest = true;
}

