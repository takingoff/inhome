<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/room.jsp"  %>	
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>控制中心</title>
	
<style type="text/css">
	@font-face { 
    font-family: "myFont";	 
    src: url("/InnMIS/image/SNOWREN.ttf");
}



.room {  
	float:left;
	width: 17%;
    height: 160px;
    margin-bottom:5px;
    margin-top:5px;
    margin-left:18px;
    margin-right:18px;
	border: thin;   
	overflow: hidden;
}

.room div { 
	font-size:18px;
	font-family: myFont; 
	margin: 0px;
	text-align: center;
	padding: 5px;
 }


.room p
{
	font-size: 12px;
	overflow: hidden;
	margin: 4px;
}

.roomStateIcon ,.enteredStateIcon1,.enteredStateIcon2,.enteredStateIcon3
{
	float:right;
	padding-right: 2px;
}




</style>
		
<script type="text/javascript">
		
	$(function()
	{
		requestRoomTrend();
		//定义修改后的函数
		freshFunctionAfterModify = function(selector)
		{
			requestRoomTrend(selector);
		};
		//定义删除后的刷新函数
		freshFunctionAfterDelete = function()
		{
			requestRoomTrend();
		};
		
		//每一分钟更新一次房态！！！
		setInterval("requestRoomTrend('.room')",60000);
	});
	
	
	function requestRoomTrend(selector)
	{
		$.ajax(
		{
			url : '/InnMIS/room/roomTrend.do',
			type : 'POST',
			data : $(this).serialize(),
			success : function(data)
			{
				//回话超时监测。
				if(typeof data == "string")
				{
					openMessageDialog(data,"回话超时");
					return ;
				}
				
				$("#trendContainerId div").remove();
				for(var index in data)
				{
					
					if(data[index].eVo != null)
					{
						//服务端在设置的时候将 enteredInfo的Room字段设置成了null，目的是减少传输量，因此这个地方不能够使用eVo.enteredInfo.room,而直接使用rVo.room
						var billResult = (data[index].eVo.enteredInfo.billPay-data[index].eVo.enteredInfo.billRoom-data[index].eVo.enteredInfo.billConsume).toFixed(2);
						$("#trendContainerId").append("<div class='room ${sessionScope.fore_Ground} ui-corner-all' id=\"roomId_"+data[index].rVo.id+"\" ondblclick='requestARoom(\""+data[index].rVo.id+"\")'>"+
										"<div class='ui-widget-header '>"+data[index].rVo.room.name+"("+data[index].rVo.room.roomType.name+")"+"</div>"+
										"<p >入住时间："+data[index].eVo.enteredInfo.enteredTime+"<span class='roomStateIcon ui-icon '></span>"+"</p>"+
										"<p >退房日期："+data[index].eVo.enteredInfo.outTime+"<span class='enteredStateIcon1 '></span>"+"</p>"+
										"<p>联系人："+data[index].eVo.enteredInfo.name+"("+data[index].eVo.enteredInfo.numberPeople+"人)"+"<span class='enteredStateIcon2 '></span>"+"</p>"+
										"<p>费用结算："+billResult+"<span class='enteredStateIcon3 '></span>"+"</p>"+
										"<p>电话："+data[index].eVo.enteredInfo.phoneNumber+"</p>"+
										"<p >说明："+data[index].eVo.enteredInfo.description+"</p>"+
										"</div>");
										
						var stateIndex = 1;
						if(billResult <0)
						{
							$("#roomId_"+data[index].rVo.id+" .enteredStateIcon"+stateIndex).addClass("ui-icon-minusthick  ui-icon");
							stateIndex += 1;				
						}
						if(data[index].eVo.enteredInfo.isHourRoom)
						{
							$("#roomId_"+data[index].rVo.id+" .enteredStateIcon"+stateIndex).addClass("ui-icon-clock  ui-icon");
							stateIndex += 1;	
						}
						if(data[index].eVo.checkOutWarn)
						{
							$("#roomId_"+data[index].rVo.id+" .enteredStateIcon"+stateIndex).addClass("ui-icon-alert  ui-icon");
						}
						
					}	
					else
					{
						$("#trendContainerId").append("<div class='room ${sessionScope.fore_Ground} ui-corner-all' id=\"roomId_"+data[index].rVo.id+"\" ondblclick='requestARoom(\""+data[index].rVo.id+"\")'>"+
										"<div class='ui-widget-header '>"+data[index].rVo.room.name+"("+data[index].rVo.room.roomType.name+")"+"</div>"+
										"<p>状态："+data[index].rVo.stateName+"<span class='roomStateIcon ui-icon '></span>"+"</p>"+
										"<p>说明："+data[index].rVo.room.description+"</p>"+
										"</div>");
					
					}
					setRoomStateIcon("#roomId_"+data[index].rVo.id+" .roomStateIcon",data[index].rVo.room.state,data[index].rVo.stateName);
				}
				
				if(typeof selector == "undefined")
				{
					return;
				}
          		$(selector ).toggle("highlight", 360 );
          		$(selector ).toggle("highlight", 360 );
			}
		});
		
	}
	
	var icons = new Array("ui-icon-flag","ui-icon-star", "ui-icon-person", "ui-icon-trash", "ui-icon-close"); 
	function setRoomStateIcon(selector,roomState,stateDescription)
	{
		$(selector).addClass(icons[roomState]);	
	}
	
	
	function refresh()
	{
		requestRoomTrend(".room");
	}
	
		
</script>
	
</head>
<body>
	<!-- 状态（1干净、2在住、3脏房、4不可用房） -->
	<div id="trendContainerId">
	</div>	
	
	
</body>
</html>
