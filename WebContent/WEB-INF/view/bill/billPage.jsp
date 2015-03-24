<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="/jsp/juiNeed.jsp" %>

<link rel="stylesheet" type="text/css"
	href="/InnMIS/css/bill/bill.css" />
<script type="text/javascript" src="/InnMIS/js/bill/bill.js"></script>

<style type="text/css">
</style>

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
			active : false,
		}).sortable(
		{
			axis : "y",
			handle : "h3",
			stop : function(event,ui)
			{
				ui.item.children("h3").triggerHandler("focusout");
			}
		});
		
		initialBillPay("billPayPaginationDIV",'/InnMIS/billPay/billPayPage.do',function(id){
			$.ajax(
			{
				url : '/InnMIS/billPay/billPayGet.do',
				data : JSON.stringify(id),
				contentType : "application/json",
				type : 'POST',
				success : function(data)
				{
					entityDialogReset();
					$(".billDetailClass").html(data.bill.bill + "(" + data.payWayDescription + ")" + "(" + data.bill.genTime + ")");
					$(".billDescriptionClass").html(data.bill.description);
					entityDialogSet(data);
				}
			});
		});
		initialBillConsume("billConsumePaginationDIV",'/InnMIS/billConsume/billConsumePage.do',function(id){
			$.ajax(
			{
				url : '/InnMIS/billConsume/billConsumeGet.do',
				data : JSON.stringify(id),
				contentType : "application/json",
				type : 'POST',
				success : function(data)
				{
					entityDialogReset();
					$(".billDetailClass").html(data.bill.bill + " (" + data.bill.genTime + ")");
					$(".billDescriptionClass").html(data.bill.description);
					entityDialogSet(data);
				}
			});
		});
		initialBillRoom("billRoomPaginationDIV",'/InnMIS/billRoom/billRoomPage.do',function(id){
			$.ajax(
			{
				url : '/InnMIS/billRoom/billRoomGet.do',
				data : JSON.stringify(id),
				contentType : "application/json",
				type : 'POST',
				success : function(data)
				{
					entityDialogReset();
					$(".billDetailClass").html(data.bill.bill + " (" + data.bill.genDate + ")");
					$(".billDescriptionClass").html(data.roomExpenseTypeDescription);
					entityDialogSet(data);
				}
			});
		});
		
		
		$("#billPayPaginationDIV").jui_datagrid(
		{
		
			columns :
			[
				{
					field : "bill.bill",
					visible : "yes",
					"header" : '入账金额',
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
					field : "bill.enteredInfo.name",
					visible : "yes",
					"header" : '联系人',
					"headerClass" : "th_nameClass",
					"dataClass" : "td_nameClass"
				},
				{
					field : "bill.enteredInfo.phoneNumber",
					visible : "yes",
					"header" : '电话号码',
					"headerClass" : "th_phoneNumberClass",
					"dataClass" : "td_phoneNumberClass"
				},
				{
					field : "bill.description",
					visible : "yes",
					"header" : '入账说明',
					"headerClass" : "th_descriptionClass",
					"dataClass" : "td_descriptionClass"
				},
				{
					field : "bill.enteredInfo.description",
					visible : "no",
					"header" : '入住说明',
					"headerClass" : "th_descriptionClass",
					"dataClass" : "td_descriptionClass"
				},
				{
					field : "bill.enteredInfo.enteredTime",
					visible : "no",
					"header" : '入住时间',
					"headerClass" : "th_enteredTimeClass",
					"dataClass" : "td_enteredTimeClass"
				},
				{
					field : "bill.enteredInfo.outTime",
					visible : "no",
					"header" : '退房日期/时间',
					"headerClass" : "th_outTimeClass",
					"dataClass" : "td_outTimeClass"
				}
			],
		});
		
		$("#billConsumePaginationDIV").jui_datagrid(
		{
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
					field : "bill.enteredInfo.name",
					visible : "yes",
					"header" : '联系人',
					"headerClass" : "th_nameClass",
					"dataClass" : "td_nameClass"
				},
				{
					field : "bill.enteredInfo.phoneNumber",
					visible : "yes",
					"header" : '电话号码',
					"headerClass" : "th_phoneNumberClass",
					"dataClass" : "td_phoneNumberClass"
				},
				{
					field : "bill.description",
					visible : "yes",
					"header" : '消费说明',
					"headerClass" : "th_descriptionClass",
					"dataClass" : "td_descriptionClass"
				},
				{
					field : "bill.enteredInfo.description",
					visible : "no",
					"header" : '入住说明',
					"headerClass" : "th_descriptionClass",
					"dataClass" : "td_descriptionClass"
				},
				{
					field : "bill.enteredInfo.enteredTime",
					visible : "no",
					"header" : '入住时间',
					"headerClass" : "th_enteredTimeClass",
					"dataClass" : "td_enteredTimeClass"
				},
				{
					field : "bill.enteredInfo.outTime",
					visible : "no",
					"header" : '退房日期/时间',
					"headerClass" : "th_outTimeClass",
					"dataClass" : "td_outTimeClass"
				}
			],
		
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
				{
					field : "bill.enteredInfo.name",
					visible : "yes",
					"header" : '联系人',
					"headerClass" : "th_nameClass",
					"dataClass" : "td_nameClass"
				},
				{
					field : "bill.enteredInfo.phoneNumber",
					visible : "yes",
					"header" : '电话号码',
					"headerClass" : "th_phoneNumberClass",
					"dataClass" : "td_phoneNumberClass"
				},
				{
					field : "bill.enteredInfo.description",
					visible : "no",
					"header" : '入住说明',
					"headerClass" : "th_descriptionClass",
					"dataClass" : "td_descriptionClass"
				},
				{
					field : "bill.enteredInfo.enteredTime",
					visible : "no",
					"header" : '入住时间',
					"headerClass" : "th_enteredTimeClass",
					"dataClass" : "td_enteredTimeClass"
				},
				{
					field : "bill.enteredInfo.outTime",
					visible : "no",
					"header" : '退房日期/时间',
					"headerClass" : "th_outTimeClass",
					"dataClass" : "td_outTimeClass"
				}
			],
		
		});
		
		
	});
	
	
////////////////////////////////////function
	
	function entityDialogSet(data)
	{
		$("#entityDialog").dialog(
					{
						width : 360,
						height : 200
					});
		//set
		if(data.bill.room == null)
		{
			$(".roomDescriptionClass").html("**房间已删除**");
		}else
		{
			$(".roomDescriptionClass").html(data.bill.room.name + "(" + data.bill.room.roomType.name + ")");
		}
		if(data.bill.enteredInfo == null)
		{
			$(".contactDescriptionClass").html("**入住信息已删除**");
		}
		else
		{
			$(".contactDescriptionClass").html(data.bill.enteredInfo.name + "(" + data.bill.enteredInfo.phoneNumber + ")");
			$("#billDetailForm input[name=enteredId]").val(data.bill.enteredInfo.id);
		}
	}
	
	function entityDialogReset()
	{
		//reset
		$('#billDetailForm')[0].reset();
		$(".billDetailClass").html("");
		$(".billDescriptionClass").html("");
		$(".roomDescriptionClass").html("");
		$(".contactDescriptionClass").html("");
		$("#billDetailForm input[name=enteredId]").val("");
	}
	
	
	function billDetailOpen()
	{
		window.open("/InnMIS/common/enteredBillPage.do?enteredId="+ $("#billDetailForm input[name=enteredId]").val());
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
				}
			});
		});
	}
	
	
</script>




</head>
<body>
	
	<div id="entityDialog" title="详情" style="display: none;">
		<form id="billDetailForm">
		<table>
			<tr><td>账目信息：</td><td><span class="billDetailClass"></span></td></tr>
			<tr><td>说明：</td><td><span class="billDescriptionClass"></span></td></tr>
			<tr><td>产生房间：</td><td><span class="roomDescriptionClass"></span></td></tr>
			<tr><td>联系人：</td><td><span class="contactDescriptionClass"></span></td></tr>
			
			<tr><td style="display:none;"><input name="enteredId"></input> </td></tr>
			
			<tr><td></td>
				<td><Button type="submit" onclick="billDetailOpen()">入住详情</Button><button onclick="destroyDialog('entityDialog')" type="button">关闭</button></td>
			</tr>
		</table>
		</form>
	</div>

	
	<div class="accordionGroup">
	<div class="accordion">
		<div class="group">
			<h3>消费</h3>
			<div id="billConsumePaginationDIV"></div>
		</div>
	</div>
	<div class="accordion">
		<div class="group">
			<h3>房费</h3>
			<div id="billRoomPaginationDIV"></div>
		</div>
	</div>
	<div class="accordion" id="payAccordionId">
		<div class="group">
			<h3>入账</h3>
			<div id="billPayPaginationDIV"></div>
		</div>
	</div>
	</div>
	

</body>

</html>

