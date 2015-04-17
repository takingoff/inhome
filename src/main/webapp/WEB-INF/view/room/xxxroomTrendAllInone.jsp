<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>控制中心</title>
	<script type="text/javascript" src="${dynamicRes}/js/room/room.js"></script>
<script type="text/javascript" src="${dynamicRes}/js/common/jui.js"></script>
<link rel="stylesheet" type="text/css"
	href="${dynamicRes}/css/roomType/miniPaginationRoomType.css" />
<link rel="stylesheet" type="text/css"
	href="${dynamicRes}/css/room/miniPaginationRoom.css" />
	
	<script type="text/javascript" src="${dynamicRes}/js/common/common.js"></script>

<link rel="stylesheet" href="${staticRes}/jqueryUi/css/${sessionScope.theme }/jquery-ui.css">
<script src="${staticRes}/jqueryUi/js/jquery-1.10.2.js"></script>
<script src="${staticRes}/jqueryUi/js/jquery-ui-1.10.4.custom.js"></script>


<script type="text/javascript"
	src="${staticRes}/timer/jquery-ui-timepicker-addon.min.js"></script>
<script type="text/javascript"
	src="${staticRes}/timer/locaion.js"></script>
<link rel="stylesheet" type="text/css"
	href="${staticRes}/timer/jquery-ui-timepicker-addon.min.css" />


<link rel="stylesheet" type="text/css"
	href="${staticRes}/jui_dropdown-master/jui_dropdown-master/jquery.jui_dropdown.css" />
<script type="text/javascript"
	src="${staticRes}/jui_dropdown-master/jui_dropdown-master/jquery.jui_dropdown.js"></script>


<link rel="stylesheet" type="text/css"
	href="${staticRes}/jui_filter_rules-master/jui_filter_rules-master/jquery.jui_filter_rules.css" />
<script type="text/javascript"
	src="${staticRes}/jui_filter_rules-master/jui_filter_rules-master/jquery.jui_filter_rules.js"></script>
<script type="text/javascript"
	src="${staticRes}/jui_filter_rules-master/jui_filter_rules-master/localization/en.js"></script>

<link rel="stylesheet" type="text/css"
	href="${staticRes}/jui_pagination/jquery.jui_pagination.css" />
<script type="text/javascript"
	src="${staticRes}/jui_pagination/localization/en.js"></script>
<script type="text/javascript"
	src="${staticRes}/jui_pagination/jquery.jui_pagination.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${staticRes}/jui_datagrid-master/jui_datagrid-master/jquery.jui_datagrid.css" />
<script type="text/javascript"
	src="${staticRes}/jui_datagrid-master/jui_datagrid-master/localization/en.js"></script>
<script type="text/javascript"
	src="${staticRes}/jui_datagrid-master/jui_datagrid-master/jquery.jui_datagrid.js"></script>


<script type="text/javascript" src="${staticRes}/bowser-master/bowser.js"></script>
<script type="text/javascript" src="${staticRes}/moment/moment.js"></script>


<link rel="stylesheet" type="text/css"
	href="${staticRes}/validator-0.7.0/jquery.validator.css" />
<script type="text/javascript" src="${staticRes}/validator-0.7.0/jquery.validator.js"></script>
<script type="text/javascript" src="${staticRes}/validator-0.7.0/local/zh_CN.js"></script>
	

<style type="text/css">
body{
		font-size: 10px;
	}
	

#miniRoomTypePageId table {  
     table-layout:fixed;  
}  

#miniRoomTypePageId td{
     white-space: nowrap;
     text-align: left;
     overflow: hidden;
 }
#miniRoomPageId table {  
     table-layout:fixed;  
}  

#miniRoomPageId td{
     white-space: nowrap;
     text-align: left;
     overflow: hidden;
 }

</style>
	
<style type="text/css">
	@font-face { 
    font-family: "myFont";	 
    src: url("${ctx}/image/SNOWREN.ttf");
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
			url : '${ctx}/room/roomTrend.do',
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
	<div id="trendContainerId">
	</div>	
	
	<div id="entityRoomDialog" title="详情" style="display: none;">
		<form id="entityRoomForm" >
			<input type="text" maxlength="20"  id="modifyRoomRoomTypeIdInputId" name="roomType.id" style="display: none;" readonly="readonly"/>
			<input type="text" name="id" style="display: none;"/>
		<table>
			<tr><td>添加时间：</td><td><input type="text"  readonly="readonly" name="genTime" /></td></tr>
			<tr><td>房间名称：</td><td><input type="text" maxlength="20"  name="name" 
				data-rule="类型名:required;name;remote[${ctx}/room/roomModifyValidateName.do, id]"  /></td></tr> <!-- 逗号后面一定要有一个空格 -->
			<tr><td>房间类型：</td><td><input type="text" maxlength="20"  name="roomTypeName" id="modifyRoomRoomTypeNameInputId" readonly="readonly" 
				data-rule="房间类型:required;roomTypeName" />
				</td><td><button type="button" onclick="chooseRoomTypeForAdd('miniRoomTypePageId','modifyRoomRoomTypeIdInputId','modifyRoomRoomTypeNameInputId')">..</button></td></tr>
			<tr><td>房间状态：</td><td><select name="state">
										<option value="1">干净房</option>
										<option value="3">脏房</option>
										<option value="4">不可用房</option>
									</select></td></tr>
			<tr><td>说明信息：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
			<tr><td><button type="submit">修改</button></td>
				<td style="text-align: center;"><button  onclick="deleteARoom()" type="button">删除</button>
					<button id="enterInButtonId" type="button" onclick="openEnterInDialog()">入住</button>	</td>
				<td><button onclick="destroyDialog('entityRoomDialog')" type="button">关闭</button></td></tr>
		</table>
		</form>
	</div>

	<div id="miniRoomTypePageId"></div>
	
	<div id="enterInDialogId"  title="入住操作" style="display: none;">
		<form id="enterInFormId">	
			<input type="text" name="room.id" style="display: none;"/>
		<table>
				<tr><td>入住房间：</td><td><input type="text" name="roomName"	disabled="disabled"  readonly="readonly"/></td></tr>
				<tr><td>入住方式：</td><td><input type="radio"  name="isHourRoom" onchange="isHourRoomChange()" value="false" checked="checked"/>普通入住
										<input type="radio" name="isHourRoom" onchange="isHourRoomChange()" value="true" />小时房入住</td></tr>
				<tr id="enterDaysTrId"><td>入住天数：</td><td><input type="text"   maxlength="2" name="enterDays" value="1"
					data-rule="入住天数:required;enterDays;integer"  /></td></tr>
				<tr><td>联系人姓名：</td><td><input type="text"   maxlength="20" name="name" 
					data-rule="入住人姓名:required;name;"  /></td></tr>
				<tr><td>手机号码：</td><td><input type="text" maxlength="20"  name="phoneNumber" /></td></tr>
				<tr><td>入住人数：</td><td><input type="text" maxlength="2"  name="numberPeople"  
					data-rule="入住人数:required;numberPeople;integer"  /></td></tr>
				<tr><td>首次入账：</td><td><input type="text"  maxlength="10" name="money"
					data-rule="首次入账:required;money;integerAndDecimal" 
		        	data-rule-integerAndDecimal="[/^([1-9]{1}[0-9]*([.]{1}[0-9]+){0,1}|(0(.[0-9]+){0,1}))$/, '输入小数或整数']"/>
		        	</td><td></td></tr>
		        <tr><td>入账方式：</td><td><input type="radio"  name="payWay"  value="1" checked="checked"/>现金
										<input type="radio" name="payWay"  value="2" />银行卡</td></tr>
				<tr><td>说明：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
				<tr><td><button type="button" onclick="resetEnterInForm()" >重置</button></td>
					<td style="text-align: center;"><button type="submit" >入住</button></td>
					<td><button onclick="destroyDialog('enterInDialogId')" type="button">关闭</button></td></tr>
		</table>
		</form>
	</div>
	
	
	<div id="enteredDialogId" style="display: none;">
		<form id="enteredFormId">	
			<input type="text" name="roomName" disabled="disabled" style="display: none;"/>
			<input type="text" name="roomId" 	disabled="disabled" style="display: none;"/>
			<input type="text" name="roomTypeId" disabled="disabled" style="display: none;"/>
			<input type="text" name="isHourRoom" disabled="disabled" style="display: none;"/>
			<input type="text" name="outTime" disabled="disabled" style="display: none;"/>
			
			<input type="text" name="id" style="display: none;"/>
		<table>
				<tr><td>入住时间：</td><td><span class="enteredTimeDescriptionClass"></span></td></tr>
				<tr><td>入住方式：</td><td><span class="enteredWayDescriptionClass"></span></td></tr>
				<tr><td>退房日期：</td><td><span class="outTimeDescriptionClass"></span></td></tr>
				<tr><td>总入账：</td><td><span class="billPayDescriptionClass"></span></td></tr>
				<tr><td>总消费：</td><td><span class="billConsumeDescriptionClass"></span></td></tr>
				<tr><td>总房费：</td><td><span class="billRoomDescriptionClass"></span></td></tr>
				<tr><td>结算结果：</td><td><span class="balanceDescriptionClass"></span></td></tr>
				<tr><td>联系人姓名：</td><td><input type="text"   maxlength="20" name="name" 
					data-rule="入住人姓名:required;name;"  /></td></tr>
				<tr><td>手机号码：</td><td><input type="text" maxlength="20"  name="phoneNumber" /></td></tr>
				<tr><td>入住人数：</td><td><input type="text" maxlength="2"  name="numberPeople"  
					data-rule="入住人数:required;numberPeople;integer"  /></td></tr>
				<tr><td>说明：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
				<tr><td><button type="submit">修改</button></td>
					<td style="text-align: center;"><button type="button"  onclick="chooseRoomForSwitchRoom('miniRoomPageId')">换房</button>
					<button type="button" onclick="billDetailOpen()">账单</button><button type="button" id="continueButton" onclick="openContinueDialog()">续住</button></td>
					<td><button onclick="destroyDialog('enteredDialogId')" type="button">关闭</button></td></tr>
		</table>
		</form>
	</div>
	
	
	<div id="enteredContinueDialogId" style="display: none;">
		<form id="enteredConinueFormId">
			<input type="text" name="roomId" disabled="disabled" style="display: none;"/> 
			<input type="text" name="outTime" disabled="disabled" style="display: none;"/> 
			<input type="text" name="id" style="display: none;"/>
			<table>
			<tr><td>当前退房时间/日期:</td><td><input type="text" name="outTime" disabled="disabled" /></td>
			<tr><td>续住（天/次）数:</td><td><input type="text" name="days" value="1" maxlength="2"
					data-rule="续住数:required;days;integer"  /></td><td>可为负数</td></tr>
			<tr><td></td><td style="text-align: center;"><button type="submit">确定</button>
					<button onclick="destroyDialog('enteredContinueDialogId')" type="button">关闭</button></td></tr>		
			</table>
		</form>
	</div>
	
	
	<div id="miniRoomPageId"></div>
	
	
	
</body>
</html>
