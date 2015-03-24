<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="/jsp/juiNeed.jsp" %>

<title>入住信息详情</title>

<style type="text/css">
h2{
	padding: 0px;
	margin: 0px;
}
#descriptionHeadId span{
	font-size:12px;
	font:bold;
	font-family: cursive;
	margin-right: 30px;
}
#descriptionHeadId button span{
	font-size:14px;
	margin-right: 0px;
	font-family: STHupo;
}
#descriptionHeadId p{
	padding: 0;
	margin: 3px;
	margin-left: 30px;
}

#addBillPayDialog td {
	overflow: visible;
}
#addBillConsumeDialog td {
	overflow: visible;
}
	
</style>

<link rel="stylesheet" type="text/css"
	href="/InnMIS/css/bill/bill.css" />
<script type="text/javascript" src="/InnMIS/js/bill/bill.js"></script>



<script type="text/javascript">
	
	$(function(){
	
	
		$(".accordion").accordion(
		{
			header : "> div > h3",
			heightStyle : "content",
			collapsible : true,
			active : false,		//关闭状态，不能设置成true 只能是false或者int类型的值。
		});
		$("#payAccordionId").accordion(
		{
			header : "> div > h3",
			heightStyle : "content",
			collapsible : true,
			active : 0,	//第一个打开
		});
		$(".accordionGroup").accordion(
		{
			header : "> div > h3",
			heightStyle : "content",
			collapsible : true,
		}).sortable(
		{
			axis : "y",
			handle : "h3",
			stop : function(event,ui)
			{
				ui.item.children("h3").triggerHandler("focusout");
			}
		});
		
		initialBillRoom("billRoomPaginationDIV",'/InnMIS/billRoom/billRoomPage.do?enteredId=${enteredId}',function(id){});
		
		initialBillPay("billPayPaginationDIV",'/InnMIS/billPay/billPayPage.do?enteredId=${enteredId}',function(id){
			$.ajax(
			{
				url : '/InnMIS/billPay/billPayGet.do',
				data : JSON.stringify(id),
				contentType : "application/json",
				type : 'POST',
				success : function(data)
				{
					//reset
					$("#billPayForm input[name=id]").val("");	
					$("#billPayForm textarea[name=description]").val("");
					$("#billPayForm .roomDescriptionClass").html("");
					$("#billPayForm .contactDescriptionClass").html("");
					$("#billPayForm .billDetailClass").html("");
					
					$("#billPayDialog").dialog(
					{
						width : 360,
						height : 280
					});
					//set 
					$("#billPayForm input[name=id]").val(data.bill.id);	//必须放在第一行 因为 data.room.name等操作可能导致出错中断，这样就获取不了bill.id了
					$("#billPayForm textarea[name=description]").val(data.bill.description);
					$("#billPayForm .billDetailClass").html(data.bill.bill + "(" + data.payWayDescription + ")" + "(" + data.bill.genTime + ")");
					$("#billPayForm .roomDescriptionClass").html(data.bill.room.name + "(" + data.bill.room.roomType.name + ")");
					$("#billPayForm .contactDescriptionClass").html(data.bill.enteredInfo.name + "(" + data.bill.enteredInfo.phoneNumber + ")");					
					
				}
			});
		});
		
		initialBillConsume("billConsumePaginationDIV",'/InnMIS/billConsume/billConsumePage.do?enteredId=${enteredId}',function(id){
			$.ajax(
			{
				url : '/InnMIS/billConsume/billConsumeGet.do',
				data : JSON.stringify(id),
				contentType : "application/json",
				type : 'POST',
				success : function(data)
				{
					//reset
					$("#billConsumeForm input[name=id]").val("");	
					$("#billConsumeForm textarea[name=description]").val("");
					$("#billConsumeForm .roomDescriptionClass").html("");
					$("#billConsumeForm .contactDescriptionClass").html("");
					$("#billConsumeForm .billDetailClass").html("");
					
					$("#billConsumeDialog").dialog(
					{
						width : 360,
						height : 280
					});
					//set 
					$("#billConsumeForm input[name=id]").val(data.bill.id);	//必须放在第一行 因为 data.room.name等操作可能导致出错中断，这样就获取不了bill.id了
					$("#billConsumeForm textarea[name=description]").val(data.bill.description);
					$("#billConsumeForm .billDetailClass").html(data.bill.bill  + "(" + data.bill.genTime + ")");
					$("#billConsumeForm .roomDescriptionClass").html(data.bill.room.name + "(" + data.bill.room.roomType.name + ")");
					$("#billConsumeForm .contactDescriptionClass").html(data.bill.enteredInfo.name + "(" + data.bill.enteredInfo.phoneNumber + ")");
				}
			});
		});
		
		
		$("#billRoomPaginationDIV").jui_datagrid(
		{
			columns :
			[
				{
					field : "bill.bill",
					visible : "yes",
					"header" : '房费',
					"headerClass" : "th_billClass",
					"dataClass" : "td_billClass"
				},
				{
					field : "roomExpenseTypeDescription",
					visible : "yes",
					"header" : '房费类型',
					"headerClass" : "th_roomExpenseTypeDescriptionClass",
					"dataClass" : "td_roomExpenseTypeDescriptionClass"
				},
				{
					field : "bill.room.name",
					visible : "yes",
					"header" : '产生房间',
					"headerClass" : "th_roomNameClass",
					"dataClass" : "td_roomNameClass"
				},
				{
					field : "bill.room.roomType.name",
					visible : "yes",
					"header" : '房间类型',
					"headerClass" : "th_roomTypeNameClass",
					"dataClass" : "td_roomTypeNameClass"
				},
				{
					field : "bill.genDate",
					visible : "yes",
					"header" : '生成日期',
					"headerClass" : "th_genDateClass",
					"dataClass" : "td_genDateClass"
				},
			],
		});
		$("#billConsumePaginationDIV").jui_datagrid(
		{
			showAddButtonText : true,
			showAddButton : true,
			columns :
			[
				{
					field : "bill.bill",
					visible : "yes",
					"header" : '消费额',
					"headerClass" : "th_billClass",
					"dataClass" : "td_billClass"
				},
				{
					field : "bill.room.name",
					visible : "yes",
					"header" : '产生房间',
					"headerClass" : "th_roomNameClass",
					"dataClass" : "td_roomNameClass"
				},
				{
					field : "bill.room.roomType.name",
					visible : "yes",
					"header" : '房间类型',
					"headerClass" : "th_roomTypeNameClass",
					"dataClass" : "td_roomTypeNameClass"
				},
				{
					field : "bill.genTime",
					visible : "yes",
					"header" : '消费时间',
					"headerClass" : "th_genTimeClass",
					"dataClass" : "td_genTimeClass"
				},
				{
					field : "bill.description",
					visible : "yes",
					"header" : '消费说明',
					"headerClass" : "th_descriptionClass",
					"dataClass" : "td_descriptionClass"
				},
			],
			onDisplay : function()
			{
				$("button#tools_billConsumePaginationDIV_add").click(function()
				{
					//触发校验
					resetAddBillConsumeForm();
					$("#addBillConsumeDialog").dialog(
					{
						"width" : 360,
						"height" : 250,
					});
				});
			},
		});
		$("#billPayPaginationDIV").jui_datagrid(
		{
			
			showAddButtonText : true,
			showAddButton : true,
			columns :
			[
				{
					field : "bill.bill",
					visible : "yes",
					"header" : '入账额',
					"headerClass" : "th_billClass",
					"dataClass" : "td_billClass"
				},
				{
					field : "payWayDescription",
					visible : "yes",
					"header" : '方式',
					"headerClass" : "th_payWayClass",
					"dataClass" : "td_payWayClass"
				},
				{
					field : "bill.room.name",
					visible : "yes",
					"header" : '产生房间',
					"headerClass" : "th_roomNameClass",
					"dataClass" : "td_roomNameClass"
				},
				{
					field : "bill.room.roomType.name",
					visible : "yes",
					"header" : '房间类型',
					"headerClass" : "th_roomTypeNameClass",
					"dataClass" : "td_roomTypeNameClass"
				},
				{
					field : "bill.genTime",
					visible : "yes",
					"header" : '入账时间',
					"headerClass" : "th_genTimeClass",
					"dataClass" : "td_genTimeClass"
				},
				{
					field : "bill.description",
					visible : "yes",
					"header" : '入账说明',
					"headerClass" : "th_descriptionClass",
					"dataClass" : "td_descriptionClass"
				},
			],
			onDisplay : function()
			{
				$("button#tools_billPayPaginationDIV_add").click(function()
				{
					//触发校验
					resetAddBillPayForm();
					$("#addBillPayDialog").dialog(
					{
						"width" : 360,
						"height" : 270,
					});
				});
			},
			
		});
		
		
		//入账操作验证
		$('#addBillPayForm').bind('valid.form', function()
		{
			$.ajax(
			{
				url : '/InnMIS/billPay/billPayAdd.do',
				type : 'POST',
				data : $(this).serialize(),
				success : function(data)
				{
					openMessageDialog(data, "入账提醒");
					destroyDialog("addBillPayDialog");
					//刷新
					$("#billPayPaginationDIV").jui_datagrid("refresh");
					requestEnteredInfoDetail("${enteredId}");
				}
			});
		});
		//消费操作验证
		$('#addBillConsumeForm').bind('valid.form', function()
		{
			$.ajax(
			{
				url : '/InnMIS/billConsume/billConsumeAdd.do',
				type : 'POST',
				data : $(this).serialize(),
				success : function(data)
				{
					openMessageDialog(data, "入账提醒");
					destroyDialog("addBillConsumeDialog");
					//刷新
					$("#billConsumePaginationDIV").jui_datagrid("refresh");
					requestEnteredInfoDetail("${enteredId}");
				}
			});
		});
		
		
		//请求入住信息
		requestEnteredInfoDetail("${enteredId}");
		
	});
	
////////////////////////////////////function

//初始化页面、添加账单后，删除账单后调用。
function requestEnteredInfoDetail(id)
{
	$.ajax(
	{
		url : '/InnMIS/entered/enteredGet.do',
		data : JSON.stringify(id),
		contentType : "application/json",
		type : 'POST',
		success : function(data)
		{
			afterRequestAEntered(data);
		}
	});
}	
	
function afterRequestAEntered(data)
{
	//设置信息头
	if(data.enteredInfo == null)
	{
		$(".nameDescriptionClass").html("**入住信息已删除**");
		return;
	}
	$(".nameDescriptionClass").html(data.enteredInfo.name);
	$(".numberPeopleDescriptionClass").html(data.enteredInfo.numberPeople);
	$(".phoneNumberDescriptionClass").html(data.enteredInfo.phoneNumber);
	$(".enteredTimeDescriptionClass").html(data.enteredInfo.enteredTime);
	$(".outTimeDescriptionClass").html(data.enteredInfo.outTime+" ( "+data.checkOutDescription+")");
	$(".descriptionClass").html(data.enteredInfo.description);
	var settleResult = (data.enteredInfo.billPay-data.enteredInfo.billConsume-data.enteredInfo.billRoom).toFixed(2);
	$(".settleDescriptionClass").html(settleResult);
	$(".billPayDescriptionClass").html(data.enteredInfo.billPay.toFixed(2));
	$(".billConsumeDescriptionClass").html(data.enteredInfo.billConsume.toFixed(2));
	$(".billRoomDescriptionClass").html(data.enteredInfo.billRoom.toFixed(2));
	$(".roomDescriptionClass").html(data.enteredInfo.room.name+"(" +data.enteredInfo.room.roomType.name+")");
	//设置说明修改表单
	$("#descriptionModifyForm textarea[name=description]").val(data.enteredInfo.description);
	
	//已退房隐藏退房按钮
	if(data.enteredInfo.isCheckOut)
	{
		$("#checkOutButton").hide();
		return;
	}
	//设置退房结算结果
	$(".settleResultClass").val(settleResult);
	if(settleResult < 0)
	{
		$(".settleResultDescriptionClass").html("结算为负请向对方收取差值");
		$("#checkOutForm #payWayTr").show();
		document.getElementById("cashRadioId").checked = true;
		
	}else if(settleResult >0)
	{
		$(".settleResultDescriptionClass").html("结算为正请返还给对方余额");
		$("#checkOutForm #payWayTr").hide();
		document.getElementById("otherRadioId").checked = true;
	}else
	{
		$(".settleResultDescriptionClass").html("房费正好");
		$("#checkOutForm #payWayTr").hide();
		document.getElementById("otherRadioId").checked = true;
	}
		
}	
//删除单个
function deleteABill(formId,url,paginationDIV,dialog)
{
	var idJson = "[\"" + $("#"+formId+" input[name=id]").val() + "\"]";
	deleteEntity(idJson, url, paginationDIV);
	destroyDialog(dialog); //单个删除时调用。
}

//ajax 删除。参数为id的json形式
function deleteBill(idJson,url,paginationDIV)
{
	openConfirmDialog("确定要删除吗？将不可恢复", "确认", function()
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
				//刷新信息头
				requestEnteredInfoDetail("${enteredId}");
			}
		});
	});
}	

function modifyBillPay()
{
	$.ajax(
	{
		url : '/InnMIS/billPay/billPayModify.do',
		type : 'POST',
		data : $("#billPayForm").serialize(),
		success : function(data)
		{
			openMessageDialog(data, "修改提醒");
			destroyDialog("billPayDialog");
			//刷新
			$("#billPayPaginationDIV").jui_datagrid("refresh");
		}
	});
}

function modifyBillConsume()
{
	$.ajax(
	{
		url : '/InnMIS/billConsume/billConsumeModify.do',
		type : 'POST',
		data : $("#billConsumeForm").serialize(),
		success : function(data)
		{
			openMessageDialog(data, "修改提醒");
			destroyDialog("billConsumeDialog");
			//刷新
			$("#billConsumePaginationDIV").jui_datagrid("refresh");
		}
	});
}

function resetAddBillPayForm()
{
	$("#addBillPayForm")[0].reset();
	$("#addBillPayForm input[name='enteredInfo.id']").val('${enteredId}');
}
function resetAddBillConsumeForm()
{
	$("#addBillConsumeForm")[0].reset();
	$("#addBillConsumeForm input[name='enteredInfo.id']").val('${enteredId}');
}
function openDescriptionModifyDialog()
{

	$("#descriptionModifyDialog").dialog(
	{
		"width" : 320,
		"height" : 240,
	});
}
function modifyEnteredDescription()
{
	$.ajax(
	{
		url : '/InnMIS/entered/enteredModifyDescription.do',
		type : 'POST',
		data : $("#descriptionModifyForm").serialize(),
		success : function(data)
		{
			openMessageDialog(data, "修改提醒");
			destroyDialog("descriptionModifyDialog");
			//刷新
			requestEnteredInfoDetail("${enteredId}");
		}
	});
}

function checkOutClick()
{
	requestEnteredInfoDetail("${enteredId}");
	$("#checkOutDialog").dialog();
}

function checkOutRequest()
{
	destroyDialog("checkOutDialog");
	openConfirmDialog("确定要退房吗？此操作不可恢复", "确认", function()
	{
		$.ajax(
		{
			url : '/InnMIS/entered/enteredCheckOut.do',
			data : {id:"${enteredId}",payWay:$('#checkOutForm input:radio[name="payWay"]:checked').val(),
					money:(-($('#checkOutForm input[name="money"]').val()))},
			type : 'POST',
			success : function(data)
			{
				openMessageDialog(data, "退房提示");
				
				//更新列表
				$("#billPayPaginationDIV").jui_datagrid("refresh");
				$("#billConsumePaginationDIV").jui_datagrid("refresh");
				$("#billRoomPaginationDIV").jui_datagrid("refresh");
								
				//刷新信息头
				requestEnteredInfoDetail("${enteredId}");
			}
		});
	});
}


</script>


</head>
<body>
	
	 <h2 align="center"  class="ui-widget-header title ui-corner-all">入住信息详情</h2>
	 <div id="descriptionHeadId" class="ui-state-default ui-corner-all">
	 	<p>在住房间：<span class="roomDescriptionClass"></span>
	 	联系人：<span class="nameDescriptionClass"></span>入住人数：<span class="numberPeopleDescriptionClass"></span>
	 	联系电话：<span class="phoneNumberDescriptionClass"></span>入住时间：<span class="enteredTimeDescriptionClass"></span>
	 	退房时间/日期：<span class="outTimeDescriptionClass"></span></p>
	 	<p>总入账：<span class="billPayDescriptionClass"></span>总房费：<span class="billRoomDescriptionClass"></span>
	 	总消费：<span class="billConsumeDescriptionClass"></span>结算结果：<span class="settleDescriptionClass"></span>
	 	入住说明：<span class="descriptionClass"></span>
	 	<button onclick="openDescriptionModifyDialog()">修改</button>
	 	<button id="checkOutButton" onclick="checkOutClick()">退房</button></p>
	 	
	 </div>
	
	<div class="accordionGroup">
	<div class="accordion">
		<div class="group">
			<h3>房费</h3>
			<div id="billRoomPaginationDIV"></div>
		</div>
	</div>
	<div class="accordion">
		<div class="group">
			<h3>消费</h3>
			<div id="billConsumePaginationDIV"></div>
		</div>
	</div>
	<div class="accordion" id="payAccordionId">
		<div class="group">
			<h3>入账</h3>
			<div id="billPayPaginationDIV"></div>
		</div>
	</div>
	</div>
	
	
	<div id="checkOutDialog" title="退房结算" style="display: none;">
		<form id="checkOutForm">
		<table>
			<tr><td>结算结果：</td><td><input name="money" class="settleResultClass" readonly="readonly"></input></td></tr>
			<tr><td></td><td><span class="settleResultDescriptionClass"></span></td></tr>
			<tr id="payWayTr"><td>入账方式：</td><td>
										<input  type="radio"  name="payWay"  value="1" id="cashRadioId" />现金
										<input type="radio" name="payWay"  value="2" />银行卡
										<input  type="radio" name="payWay"  value="3" id="otherRadioId" />其他</td></tr>
			<tr><td></td>
				<td>
				<button onclick="checkOutRequest()" type="button">继续</button>
				<button onclick="destroyDialog('checkOutDialog')" type="button">取消</button></td>
			</tr>
		</table>
		</form>
	</div>
	
	<div id="descriptionModifyDialog" title="说明修改" style="display: none;" >
		<form id="descriptionModifyForm">
			<input name="id" value="${enteredId}" style="display:none"></input>
		<table>
			<tr><td>说明信息：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
			
			<tr><td></td>
				<td><button onclick="modifyEnteredDescription()" type="button">修改</button>
				<button onclick="destroyDialog('descriptionModifyDialog')" type="button">关闭</button></td>
			</tr>
		</table>
		</form>
	</div>
	
	<div id="billPayDialog" title="入账详情" style="display: none;">
		<form id="billPayForm">
		<table>
			<tr><td>账目信息：</td><td><span class="billDetailClass"></span></td></tr>
			<tr><td>产生房间：</td><td><span class="roomDescriptionClass"></span></td></tr>
			<tr><td>联系人：</td><td><span class="contactDescriptionClass"></span></td></tr>
			
			<tr><td>说明信息：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
			<tr><td style="display: none"><input name="id"></input> </td></tr>
			
			<tr><td></td>
				<td><button onclick="deleteABill('billPayForm','/InnMIS/billPay/billPayDelete.do','billPayPaginationDIV','billPayDialog')" type="button">删除</button>
				<button onclick="modifyBillPay()" type="button">修改</button>
				<button onclick="destroyDialog('billPayDialog')" type="button">关闭</button></td>
			</tr>
		</table>
		</form>
	</div>
	
	<div id="billConsumeDialog" title="消费详情" style="display: none;">
		<form id="billConsumeForm">
		<table>
			<tr><td>账目信息：</td><td><span class="billDetailClass"></span></td></tr>
			<tr><td>产生房间：</td><td><span class="roomDescriptionClass"></span></td></tr>
			<tr><td>联系人：</td><td><span class="contactDescriptionClass"></span></td></tr>
			
			<tr><td>说明信息：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
			<tr><td style="display: none"><input name="id"></input> </td></tr>
			
			<tr><td></td>
				<td><button onclick="deleteABill('billConsumeForm','/InnMIS/billConsume/billConsumeDelete.do','billConsumePaginationDIV','billConsumeDialog')" type="button">删除</button>
				<button onclick="modifyBillConsume()" type="button">修改</button>
				<button onclick="destroyDialog('billConsumeDialog')" type="button">关闭</button></td>
			</tr>
		</table>
		</form>
	</div>
	
	
	<div id="addBillPayDialog"  title="增加入账" style="display: none;">
		<form id="addBillPayForm">	
				<input type="text" name="enteredInfo.id" style="display: none;"/>
		<table>
				<tr><td>入账金额：</td><td><input type="text"  maxlength="10" name="bill"
					data-rule="入账金额:required;money;integerAndDecimal" 
		        	data-rule-integerAndDecimal="[/^(-){0,1}([1-9]{1}[0-9]*(.[0-9]+){0,1}|(0(.[0-9]+){0,1}))$/, '输入小数或整数']"/>
		        	</td><td></td></tr>
		        <tr><td>入账方式：</td><td><input type="radio"  name="payWay"  value="1" checked="checked"/>现金
										<input type="radio" name="payWay"  value="2" />银行卡
										<input type="radio" name="payWay"  value="3" />其他</td></tr>
				<tr><td>说明：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
				<tr><td><button type="button" onclick="resetAddBillPayForm()" >重置</button></td>
					<td style="text-align: center;"><button type="submit" >添加</button></td>
					<td><button onclick="destroyDialog('addBillPayDialog')" type="button">关闭</button></td></tr>
		</table>
		</form>
	</div>
	
	<div id="addBillConsumeDialog"  title="增加消费" style="display: none;">
		<form id="addBillConsumeForm">	
				<input type="text" name="enteredInfo.id" style="display: none;"/>
		<table>
				<tr><td>消费金额：</td><td><input type="text"  maxlength="10" name="bill"
					data-rule="入账金额:required;money;integerAndDecimal" 
		        	data-rule-integerAndDecimal="[/^(-){0,1}([1-9]{1}[0-9]*(.[0-9]+){0,1}|(0(.[0-9]+){0,1}))$/, '输入小数或整数']"/>
		        	</td><td></td></tr>
				<tr><td>说明：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
				<tr><td><button type="button" onclick="resetAddBillConsumeForm()" >重置</button></td>
					<td style="text-align: center;"><button type="submit" >添加</button></td>
					<td><button onclick="destroyDialog('addBillConsumeDialog')" type="button">关闭</button></td></tr>
		</table>
		</form>
	</div>
	
	

</body>

</html>

