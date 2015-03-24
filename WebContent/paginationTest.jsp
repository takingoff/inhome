<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<%@include file="/jsp/juiNeed.jsp" %>

<script type="text/javascript" src="/InnMIS/js/common/jui.js"></script>
<link rel="stylesheet" type="text/css"
	href="/InnMIS/css/roomType/miniPaginationRoomType.css" />
<link rel="stylesheet" type="text/css"
	href="/InnMIS/css/room/miniPaginationRoom.css" />

<style type="text/css">

body{
	font-size: 10px;
}

#miniRoomTypePageId table,#paginationDIV table {  
     table-layout:fixed;  
}  

#miniRoomTypePageId td,#paginationDIV td{
     white-space: nowrap;
     text-align: left;
     overflow: hidden;
 }


/* paginationDIV --------------------------------------------------------------- */
.grid1_container {
	width: 90%;
	padding: 5px;
	background: none !important;
	font-size:18px;
	margin-left: auto;
	margin-right: auto;
}

.grid1_data {
	font-size:16px;
	padding: 0;
	margin: 0 0 5px 0;
	border: none !important;
	overflow-y: hidden;
	overflow-x: hidden ;
}

.grid1_filters {
	font-size: .9em !important;
	width: 700px !important;
}


.th_nameClass {
	width: 6%;
}
.td_nameClass {
	width: 6%;
}

.th_phoneNumberClass {
	width: 10%;
}
.td_phoneNumberClass {
	width: 10%;
}

.th_roomTypeNameClass {
	width: 10%;
}
.td_roomTypeNameClass {
	width: 10%;
}

.th_genTimeClass {
	width: 15%;
}
.td_genTimeClass {
	width: 15%;
}

.th_willEnterDateClass {
	width: 10%;
}
.td_willEnterDateClass {
	width: 10%;
}

.th_willEnterDaysClass {
	width: 5%;
}
.td_willEnterDaysClass {
	width: 5%;
}

.th_extendHoursClass {
	width: 5%;
}
.td_extendHoursClass {
	width: 5%;
}

.th_stateDescriptionClass {
	width: 8%;
}
.td_stateDescriptionClass {
	width: 8%;
}





/* demo_rules1 widgets ------------------------------------------------------ */
.ui-datepicker {
	font-size: 12px;
}

.ui-autocomplete {
	font-size: 14px;
}

/* jQuery-UI-bootstrap theme CSS fix ---------------------------------------- */
.juib_fix {
	display: inline-block !important;
}



.selectedRowClass{
	color:#000000;
	background: #ff9b08;
}
</style>

<script type="text/javascript">
	$(function()
	{
		$("#paginationDIV").jui_datagrid(
		{
		
			dlgFiltersClass : 'grid1_filters',  //过滤器对话框的风格
			ajaxFetchDataURL : '/InnMIS/order/orderPage.do', //数据请求地址
			row_primary_key: 'id',//列主键 必须为不重复的整数 。。。。太尼玛坑爹了。
			containerClass : 'grid1_container ui-state-default ui-corner-all', //整个表格的风格
			datagridClass : 'grid1_data ui-widget-content',//数据区的风格
			caption : '订单查询',//表格显示名字
			rowsPerPage: 15,
			rowSelectionMode: 'multiple', // 'multiple', 'single', 'false'
			
			showExportButton : false,//不显示导出
			showPrintButton : false,//不显示打印
			showRowNumbers: true,
			showPrefButtonText: false,
		    showRefreshButtonText: true,
		    showDeleteButtonText: true,
		    showPrintButtonText: true,
		    showExportButtonText: true,
		    showSortingButtonText: true,
		    showFiltersButtonText: true,
		    
		    showAddButtonText: true,
		    showAddButton: true,
			
			
			selectedTrTrClass: 'ui-widget-header',
    		//selectedTrTdClass: 'selectedRowClass',
    		selectedTrTdClass: 'ui-widget-header',
			
			 // select dropdown options
			dropdownSelectOptions :
			{
				menuClass : 'dropdown_select_menu',
				launcherClass : 'dropdown_select_launcher',
				launchOnMouseEnter: false
			}, // 'launcher_id', 'launcher_container_id', 'menu_id', 'onSelect' will be ignored
			
			// pagination options
			usePagination : true,
			paginationOptions :
			{
				visiblePageLinks : 5,
				useSlider : true,
				showGoToPage : true,
				showRowsPerPage : true,
				showRowsInfo : true,
				showPreferences : false,
				disableSelectionNavPane : true,
			}, // 'currentPage', 'rowsPerPage', 'totalPages', 'containerClass', 'onSetRowsPerPage', 'onChangePage', 'onDisplay' will be ignored
			columns :
			[
					{
						field : "order.name",
						visible : "yes",
						"header" : '联系人',
						"headerClass" : "th_nameClass",
						"dataClass" : "td_nameClass"
					},
					{
						field : "order.phoneNumber",
						visible : "yes",
						"header" : '电话号码',
						"headerClass" : "th_phoneNumberClass",
						"dataClass" : "td_phoneNumberClass"
					},
					{
						field : "order.roomType.name",
						visible : "yes",
						"header" : '房间类型',
						"headerClass" : "th_roomTypeNameClass",
						"dataClass" : "td_roomTypeNameClass"
					},
					{
						field : "order.genTime",
						visible : "yes",
						"header" : '下单时间',
						"headerClass" : "th_genTimeClass",
						"dataClass" : "td_genTimeClass"
					},
					{
						field : "order.willEnterDate",
						visible : "yes",
						"header" : '入住日期',
						"headerClass" : "th_willEnterDateClass",
						"dataClass" : "td_willEnterDateClass"
					},
					{
						field : "order.willEnterDays",
						visible : "yes",
						"header" : '天数',
						"headerClass" : "th_willEnterDaysClass",
						"dataClass" : "td_willEnterDaysClass"
					},
					{
						field : "stateDescription",
						visible : "yes",
						"header" : '状态',
						"headerClass" : "th_stateDescriptionClass",
						"dataClass" : "td_stateDescriptionClass"
					},
					{
						field : "order.extendHours",
						visible : "yes",
						"header" : '延迟',
						"headerClass" : "th_extendHoursClass",
						"dataClass" : "td_extendHoursClass"
					},
					{
						field : "order.description",
						visible : "yes",
						"header" : '说明信息',
						"headerClass" : "",
						"dataClass" : ""
					}
			],
			sorting :
			[
					{
						sortingName : "产生时间排序",
						field : "genTime",
						order : "DESC"
					}
					
			],
			filterOptions :
			{
				filters :
				[
						{
							filterName : "nameFilter",
							"filterType" : "text",
							field : "name",
							filterLabel : "类型名称",
							excluded_operators :
							[
									"in", "not_in"
							],
							filter_interface :
							[
								{
									filter_element : "input",
									filter_element_attributes :
									{
										"type" : "text"
									}
								}
							]
						},
						{
							filterName : "genTimeFilter",
							"filterType" : "date",
							field : "genTime",
							filterLabel : "生成时间",
							excluded_operators :
							[
									"in", "not_in"
							],
							filter_interface :
							[
								{
									filter_element : "input",
									filter_element_attributes :
									{
										type : "text",
										title : "Set the date and time using format: dd/mm/yyyy hh:mm:ss"
									},
									filter_widget : "datetimepicker",
									filter_widget_properties :
									{
										dateFormat : "yy-mm-dd",
										timeFormat : "HH:mm:ss",
										changeMonth : true,
										changeYear : true,
										showSecond : true
									}
								}
							],
							validate_dateformat :
							[
								"YYYY-MM-DD HH:mm:ss"
							],
						},
				],
				decimal_separator : "."
			},
 			
			onChangeSelectedRows : function()
			{
				var elem_jui_dropdown_launcher_span = $('#' + $("#paginationDIV").jui_datagrid('getDropdownLauncherID') + ' span');
				elem_jui_dropdown_launcher_span.addClass('juib_fix');
			},
			onDisplay : function()
			{
				var elem_jui_dropdown_launcher_span = $('#' + $("#paginationDIV").jui_datagrid('getDropdownLauncherID') + ' span');
				elem_jui_dropdown_launcher_span.addClass('juib_fix');
				
				$("button#tools_paginationDIV_add").click(function()
				{
					//添加后要将input 清空，否则 validate框架不会验证。
					$('#addForm')[0].reset();
					
					$("#addOrderDialog").dialog(
					{
						"width" : 360,
						"height" : 400,
					});
				});
				
			},
			onDBClick : function(event,data)
			{
				$.ajax(
					{
						url : '/InnMIS/order/orderGet.do',
						data : JSON.stringify(data.row_id),
						contentType : "application/json",
						type : 'POST', 
						success : function(data)
						{
							//触发校验
					        $('#entityForm')[0].reset();
					        
					        $("#entityDialog").dialog({width:360,height:410});
					        $("#entityForm input[name=id]").val(data.id);
					        $(".genTimeDescription").html(data.order.genTime);
					        $("#entityForm input[name=willEnterDate]").val(data.order.willEnterDate);
					        $("#entityForm input[name=willEnterDays]").val(data.order.willEnterDays);
					        $("#entityForm input[name=extendHours]").val(data.order.extendHours);
					        $("#entityForm textarea[name=description]").val(data.order.description);
					        $("#entityForm input[name=name]").val(data.order.name);
					        $("#entityForm input[name=phoneNumber]").val(data.order.phoneNumber);
					        $("#entityForm input[name='roomTypeName']").val(data.order.roomType.name);
						}
					});
			},
			onDelete : function()
			{
				var ids = $(this).jui_datagrid("getSelectedIDs"), num = ids.length;
				if(num == 0)
				{
					//首先清除验证信息 居然无效。
					openMessageDialog("没有数据被选中，请标记后再删除","提示");
				}
				else
				{
					deleteOrder(JSON.stringify(ids));				
				}
				
			},
		});
		
		
		
		//添加房间表单验证 ajax 提交
		$('#addForm').bind('valid.form', function()
		{
			$.ajax(
			{
				url : '/InnMIS/order/orderAdd.do',
				type : 'POST',
				data : $(this).serialize(),
				success : function(data)
				{
					openMessageDialog(data, "添加提示");
					destroyDialog("addOrderDialog");
					//刷新
					$("#paginationDIV").jui_datagrid('refresh');
				}
			});
		});
		//修改表单验证 ajax 提交
		$('#entityForm').bind('valid.form', function()
		{
			$.ajax(
			{
				url : '/InnMIS/order/orderModify.do',
				type : 'POST',
				data : $(this).serialize(),
				success : function(data)
				{
					openMessageDialog(data,"修改提示");
					destroyDialog("entityDialog");
					//刷新
					$("#paginationDIV").jui_datagrid('refresh');
				}
			});
		});
		
		
		
		$("button").button();
		$("#willEntereDateId").datepicker();
		$("#modifyWillEntereDateId").datepicker();
		
		
	});
	
	
////////////////////////////////////function


function deleteAOrder()
{
	deleteAEntity("entityForm","id","entityDialog","paginationDIV","/InnMIS/order/orderDelete.do");

}

function deleteOrder(idJson)
{
	deleteEntity("paginationDIV","/InnMIS/order/orderDelete.do",idJson);
}

	
</script>

</head>
<body>
	<div id="paginationDIV"></div>
	<div id="miniRoomTypePageId"></div>
	
	
	<div id="entityDialog" title="订单详情" style="display: none;">
		<form id="entityForm" >
			<input type="text"  name="id" style="display: none;" />
		<table>
			<tr><td>添加时间：</td><td><span class="genTimeDescription"></span></td></tr>
			<tr><td>入住天数：</td><td><input type="text" maxlength="1"  name="willEnterDays" 
				data-rule="入住天数:required;integer;willEnterDays" /></td></tr>
			<tr><td>入住日期：</td><td><input type="text"  id="modifyWillEntereDateId" name="willEnterDate"  readonly="readonly"
				data-rule="入住日期:required;willEnterDate" /></td></tr>
			<tr><td>房间类型：</td><td><input type="text" name="roomTypeName" id="modifyOrderRoomTypeNameInputId" readonly="readonly" 
				data-rule="房间类型:required;roomTypeName" /></td>
				<td><button type="button" onclick="chooseRoomTypeForAdd('miniRoomTypePageId','modifyOrderRoomTypeIdInputId','modifyOrderRoomTypeNameInputId')">..</button></td></tr>
			<tr><td>联系人：</td><td><input type="text" maxlength="20"  name="name"
				data-rule="联系人:required;name" /></td></tr>
			<tr><td>电话号码：</td><td><input type="text" maxlength="20"  name="phoneNumber" /></td></tr>
			<tr><td>延迟小时：</td><td><input type="text" value="0" maxlength="1" name="extendHours"
				data-rule="延迟:required; integer; range[0~6] ;extendHours" /></td></tr>
			<tr><td>说明信息：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
			<tr><td><button type="submit" >确定</button></td><td style="text-align: center;">
				<button onclick="closeDialog('entityDialog')" type="button">取消</button></td></tr>
		</table>
		</form>
	</div>
	
	
	
	
	<div id="addOrderDialog"  title="添加订单" style="display: none;">
		<form id="addForm" >
		<input type="text" maxlength="20"  id="addOrderRoomTypeIdInputId" name="roomType.id" style="display: none;" readonly="readonly"/>
		<table>
			<tr><td>入住天数：</td><td><input type="text" maxlength="1"  name="willEnterDays" 
				data-rule="入住天数:required;integer;willEnterDays" /></td></tr>
			<tr><td>入住日期：</td><td><input type="text"  id="willEntereDateId" name="willEnterDate"  readonly="readonly"
				data-rule="入住日期:required;willEnterDate" /></td></tr>
			<tr><td>房间类型：</td><td><input type="text" name="roomTypeName" id="addOrderRoomTypeNameInputId" readonly="readonly" 
				data-rule="房间类型:required;roomTypeName" /></td>
				<td><button type="button" onclick="chooseRoomTypeForAdd('miniRoomTypePageId','addOrderRoomTypeIdInputId','addOrderRoomTypeNameInputId')">..</button></td></tr>
			<tr><td>联系人：</td><td><input type="text" maxlength="20"  name="name"
				data-rule="联系人:required;name" /></td></tr>
			<tr><td>电话号码：</td><td><input type="text" maxlength="20"  name="phoneNumber" /></td></tr>
			<tr><td>延迟小时：</td><td><input type="text" value="0" maxlength="1" name="extendHours"
				data-rule="延迟:required; integer; range[0~6] ;extendHours" /></td></tr>
			<tr><td>说明信息：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
			<tr><td><button type="submit" >确定</button></td><td style="text-align: center;"><button onclick="closeDialog('addOrderDialog')" type="button">取消</button></td></tr>
		</table>
		</form>
	</div>
	
	
</body>

</html>

