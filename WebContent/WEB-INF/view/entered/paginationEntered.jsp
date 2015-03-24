<%@ page language="java" contentType="text/html;charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<%@include file="/jsp/juiNeed.jsp" %>

<style type="text/css">

body{
	font-size: 10px;
}

/* paginationDIV --------------------------------------------------------------- */
.grid1_container {
	width: 100%;
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

#paginationDIV table {  
     table-layout:fixed;  
}  

#paginationDIV td{
     white-space: nowrap;
     text-align: left;
     overflow: hidden;
}
 
 #entityDialog
 {
     table-layout:auto;  
 }
#entityDialog td
{
	white-space: normal;
    text-align: left;
    overflow: visible;
}
#entityDialog tr td:first-child
{
	width: 80px;
}
.grid_td_row_number {
	text-align: left;
	width: 2%;
}
.grid_th_row_number {
	text-align: left;
	width: 2%;
}

.th_nameClass {
	width: 6%;
}
.td_nameClass {
	width: 6%;
}

.th_roomNameClass {
	width: 6%;
}
.td_roomNameClass {
	width: 6%;
}
.th_roomTypeNameClass {
	width: 6%;
}
.td_roomTypeNameClass {
	width: 6%;
}
.th_settleClass {
	width: 5%;
}
.td_settleClass {
	width: 5%;
}


.th_enteredTimeClass {
	width: 13%;
}
.td_enteredTimeClass {
	width: 13%;
}

.th_outTimeClass {
	width: 13%;
}
.td_outTimeClass {
	width: 13%;
}

.th_hourRoomDescriptionClass {
	width: 8%;
}
.td_hourRoomDescriptionClass {
	width: 8%;
}

.th_checkOutDescriptionClass {
	width: 5%;
}
.td_checkOutDescriptionClass {
	width: 5%;
}

.th_phoneNumberClass {
	width: 10%;
}
.td_phoneNumberClass {
	width: 10%;
}

.th_numberPeopleClass {
	width: 5%;
}
.td_numberPeopleClass {
	width: 5%;
}

/* .th_descriptionClass {
	width: 15%;
}
.td_descriptionClass {
	width: 15%;
}
 */


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
			ajaxFetchDataURL : '/InnMIS/entered/enteredPage.do', //数据请求地址
			row_primary_key: 'id',//列主键 必须为不重复的整数 。。。。太尼玛坑爹了。
			containerClass : 'grid1_container ui-state-default ui-corner-all', //整个表格的风格
			datagridClass : 'grid1_data ui-widget-content',//数据区的风格
			caption : '入住信息列表',//表格显示名字
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
		    
		    showAddButtonText: false,
		    showAddButton: false,
			
			
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
						field : "enteredInfo.name",
						visible : "yes",
						"header" : '联系人',
						"headerClass" : "th_nameClass",
						"dataClass" : "td_nameClass"
					},
					{
						field : "enteredInfo.room.name",
						visible : "yes",
						"header" : '房间',
						"headerClass" : "th_roomNameClass",
						"dataClass" : "td_roomNameClass"
					},
					{
						field : "enteredInfo.room.roomType.name",
						visible : "yes",
						"header" : '类型',
						"headerClass" : "th_roomTypeNameClass",
						"dataClass" : "td_roomTypeNameClass"
					},
					{
						field : "hourRoomDescription",
						visible : "yes",
						"header" : '入住方式',
						"headerClass" : "th_hourRoomDescriptionClass",
						"dataClass" : "td_hourRoomDescriptionClass"
					},
					{
						field : "enteredInfo.enteredTime",
						visible : "yes",
						"header" : '入住时间',
						"headerClass" : "th_enteredTimeClass",
						"dataClass" : "td_enteredTimeClass"
					},
					{
						field : "enteredInfo.outTime",
						visible : "yes",
						"header" : '退房日期/时间',
						"headerClass" : "th_outTimeClass",
						"dataClass" : "td_outTimeClass"
					},
					{
						field : "checkOutDescription",
						visible : "yes",
						"header" : '退房',
						"headerClass" : "th_checkOutDescriptionClass",
						"dataClass" : "td_checkOutDescriptionClass"
					},
					{
						field : "enteredInfo.phoneNumber",
						visible : "yes",
						"header" : '电话号码',
						"headerClass" : "th_phoneNumberClass",
						"dataClass" : "td_phoneNumberClass"
					},
					{
						field : "enteredInfo.numberPeople",
						visible : "yes",
						"header" : '人数',
						"headerClass" : "th_numberPeopleClass",
						"dataClass" : "td_numberPeopleClass"
					},
					{
						field : "enteredInfo.description",
						visible : "yes",
						"header" : '说明信息',
						"headerClass" : "th_descriptionClass",
						"dataClass" : "td_descriptionClass"
					}
			],
			sorting :
			[
					{
						sortingName : "入住时间",
						field : "enteredTime",
						order : "DESC"
					},
					{
						sortingName : "退房时间",
						field : "outTime",
						order : "none"
					},
					{
						sortingName : "房间名",
						field : "room.name",
						order : "none"
					},
					{
						sortingName : "房间类型",
						field : "room.roomType.name",
						order : "none"
					},
					{
						sortingName : "入住方式",
						field : "isHourRoom",
						order : "none"
					},
					{
						sortingName : "是否退房",
						field : "isCheckOut",
						order : "none"
					},
					
			],
			filterOptions :
			{
				filters :
				[
						{
							filterName : "nameFilter",
							"filterType" : "text",
							field : "name",
							filterLabel : "联系人",
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
							filterName : "phoneNumberFilter",
							"filterType" : "text",
							field : "phoneNumber",
							filterLabel : "电话号码",
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
							filterName : "roomFilter",
							"filterType" : "text",
							field : "room.name",
							filterLabel : "房间名称",
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
							filterName : "roomTypeFilter",
							"filterType" : "text",
							field : "room.roomType.name",
							filterLabel : "房间类型名称",
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
							filterName : "isCheckOutFilter",
							"filterType" : "text",
							field : "isCheckOut",
							filterLabel : "是否退房",
							
							excluded_operators :
							[
									"equal", "not_equal", "less", "less_or_equal", "greater", "greater_or_equal", "contains", "not_contains", 
									"ends_with", "not_ends_with","begins_with","not_begins_with","null","not_null","is_empty","is_not_empty"
							],
							filter_interface :
							[
								{
									filter_element : "input",
									filter_element_attributes :
									{
										type: "radio"
									},
									vertical_orientation : "yes"
								}
							],
							lookup_values :
							[
									//状态（1干净、2在住、3脏房、4不可用房）
									{
										lk_option : "已退",
										lk_value : 1,
									},
									{
										lk_option : "未退",
										lk_value : 0,
									}
							]
						},
						{
							filterName : "isHourRoomFilter",
							"filterType" : "text",
							field : "isHourRoom",
							filterLabel : "入住方式",
							
							excluded_operators :
							[
									"equal", "not_equal", "less", "less_or_equal", "greater", "greater_or_equal", "contains", "not_contains", 
									"ends_with", "not_ends_with","begins_with","not_begins_with","null","not_null","is_empty","is_not_empty"
							],
							filter_interface :
							[
								{
									filter_element : "input",
									filter_element_attributes :
									{
										type: "radio"
									},
									vertical_orientation : "yes"
								}
							],
							lookup_values :
							[
									//状态（1干净、2在住、3脏房、4不可用房）
									{
										lk_option : "小时房入住",
										lk_value : 1,
									},
									{
										lk_option : "普通入住",
										lk_value : 0,
									}
							]
						},
						{
							filterName : "descriptionFilter",
							"filterType" : "text",
							field : "description",
							filterLabel : "说明信息",
							excluded_operators :
							[
									"in", "not_in","equal", "not_equal"
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
							filterName : "enteredTimeFilter",
							"filterType" : "date",
							field : "enteredTime",
							filterLabel : "入住时间",
							excluded_operators :
							[
									"in", "not_in","equal", "not_equal"
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
						{
							filterName : "outTimeFilter",
							"filterType" : "date",
							field : "outTime",
							filterLabel : "退房日期",
							excluded_operators :
							[
									"in", "not_in","equal", "not_equal"
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
										changeMonth : true,
										changeYear : true,
										showSecond : true
									}
								}
							],
							validate_dateformat :
							[
								"YYYY-MM-DD"
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
			},
			onDBClick : function(event,data)
			{
				$.ajax(
					{
						url : '/InnMIS/entered/enteredGet.do',
						data : JSON.stringify(data.row_id),
						contentType : "application/json",
						type : 'POST', 
						success : function(data)
						{
			
					        $("#entityDialog").dialog({width:360,height:260});
					        $(".enteredTimeDescriptionClass").html(data.enteredInfo.enteredTime + "~~" + data.enteredInfo.outTime + "("+data.checkOutDescription+")");
					        $(".enteredWayDescriptionClass").html(data.hourRoomDescription);
					        $(".contactDescriptionClass").html(data.enteredInfo.name+"("+data.enteredInfo.numberPeople+"人)" + data.enteredInfo.phoneNumber);
					        $(".roomDescriptionClass").html(data.enteredInfo.room.name+"("+data.enteredInfo.room.roomType.name+")");
					        $(".descriptionClass").html(data.enteredInfo.description);
					        $(".settleDescriptionClass").html((data.enteredInfo.billPay-data.enteredInfo.billConsume-data.enteredInfo.billRoom).toFixed(2));
					        
					        $("#requestEnteredBillForm input[name=enteredInfoId]").val(data.id);
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
					deleteEnteredInfo(JSON.stringify(ids));				
				}
				
			},
		});
		
		$("button").button();
		
		
		
	});
	
	
////////////////////////////////////function

function deleteAEnteredInfo()
{
	deleteAEntity("requestEnteredBillForm","enteredInfoId","entityDialog","paginationDIV","/InnMIS/entered/enteredDelete.do");

}

function deleteEnteredInfo(idJson)
{
	deleteEntity("paginationDIV","/InnMIS/entered/enteredDelete.do",idJson);
}

	
function billDetailOpen()
{
	window.open("/InnMIS/common/enteredBillPage.do?enteredId="+ $("#requestEnteredBillForm input[name=enteredInfoId]").val());
}
</script>




</head>
<body>
	<div id="paginationDIV"></div>
	
	<div id="entityDialog" title="详情" style="display: none;">
		<form id="requestEnteredBillForm">
		<table>
			<tr><td>入住时间：</td><td><span class="enteredTimeDescriptionClass"></span></td></tr>
			<tr><td>入住方式：</td><td><span class="enteredWayDescriptionClass"></span></td></tr>
			<tr><td>结算：</td><td><span class="settleDescriptionClass"></span></td></tr>
			<tr><td>联系人：</td><td><span class="contactDescriptionClass"></span></td></tr>
			<tr><td>房间信息：</td><td><span class="roomDescriptionClass"></span></td></tr>
			<tr><td>说明信息：</td><td><span class="descriptionClass"></span></td></tr>
			
			<tr><td style="display:none;"><input name="enteredInfoId"></input> </td></tr>
			
			<tr><td></td>
				<td><button type="button" onclick="deleteAEnteredInfo()">删除</button>
				<button onclick="billDetailOpen()">入住信息详情</button><button onclick="destroyDialog('entityDialog')" type="button">关闭</button></td>
			</tr>
		</table>
		</form>
	</div>
	
	
</body>

</html>

