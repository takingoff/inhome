<!DOCTYPE html>
<html>
<head>

<%@include file="/jsp/juiNeed.jsp" %>

<script type="text/javascript" src="/InnMIS/js/room/room.js"></script>
<script type="text/javascript" src="/InnMIS/js/common/jui.js"></script>
<link rel="stylesheet" type="text/css"
	href="/InnMIS/css/roomType/miniPaginationRoomType.css" />
<link rel="stylesheet" type="text/css"
	href="/InnMIS/css/room/miniPaginationRoom.css" />


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

</head>
<body>
	
	
	<div id="entityRoomDialog" title="详情" style="display: none;">
		<form id="entityRoomForm" >
			<input type="text" maxlength="20"  id="modifyRoomRoomTypeIdInputId" name="roomType.id" style="display: none;" readonly="readonly"/>
			<input type="text" name="id" style="display: none;"/>
		<table>
			<tr><td>添加时间：</td><td><input type="text"  readonly="readonly" name="genTime" /></td></tr>
			<tr><td>房间名称：</td><td><input type="text" maxlength="20"  name="name" 
				data-rule="类型名:required;name;remote[/InnMIS/room/roomModifyValidateName.do, id]"  /></td></tr> <!-- 逗号后面一定要有一个空格 -->
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