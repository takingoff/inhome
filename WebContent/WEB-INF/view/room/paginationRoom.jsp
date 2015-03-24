	
<%@include file="/jsp/room.jsp"  %>	

<!DOCTYPE html>
<html>
<head>



<style type="text/css">

#roomPaginationDIV table {  
     table-layout:fixed;  
}  

#roomPaginationDIV td{
     white-space: nowrap;
     text-align: left;
     overflow: hidden;
 }
/* roomPaginationDIV --------------------------------------------------------------- */
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

.grid_td_row_number {
	text-align: left;
	width: 2%;
}
.grid_th_row_number {
	text-align: left;
	width: 2%;
}

.th_idClass,.th_stateClass,.th_roomTypeClass,.th_descriptionClass,.th_genTimeClass
	{
	text-align: left;
}

.th_idClass {
	width: 5%;
}

.th_nameClass {
	width: 15%;
}

.th_stateClass {
	width: 10%;
}

.th_roomTypeClass {
	width: 15%;
}

.th_descriptionClass {
	width: 40%;
}

.th_genTimeClass {
	width: 15%;
}



.td_idClass {
	width: 5%;
	font-family: "courier new";
}

.td_nameClass {
	width: 15%;
	font-family: "courier new";
}

.td_stateClass {
	width: 10%;
}

.td_roomTypeClass {
	width: 15%;
}

.td_descriptionClass {
	width: 40%;
	font-family: "courier new";
}

.td_genTimeClass {
	width: 15%;
	font-size: 11px;
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
		$("#roomPaginationDIV").jui_datagrid(
		{
		
			dlgFiltersClass : 'grid1_filters',  //过滤器对话框的风格
			ajaxFetchDataURL : '/InnMIS/room/roomPage.do', //数据请求地址
			row_primary_key: 'id',//列主键 必须为不重复的整数 。。。。太尼玛坑爹了。
			containerClass : 'grid1_container ui-state-default ui-corner-all', //整个表格的风格
			datagridClass : 'grid1_data ui-widget-content',//数据区的风格
			caption : '房间列表',//表格显示名字
			rowsPerPage: 15,
			showExportButton : false,//不显示导出
			showPrintButton : false,//不显示打印
			showRowNumbers: true,
			showPrefButtonText: false,
		    showRefreshButtonText: true,
		    showDeleteButtonText: true,
		    showPrintButtonText: true,
		    showAddButtonText: true,
		    showExportButtonText: true,
		    showSortingButtonText: true,
		    showFiltersButtonText: true,
			
			
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
						field : "room.name",
						visible : "yes",
						"header" : '房间名称',
						"headerClass" : "th_nameClass",
						"dataClass" : "td_nameClass"
					},
					{
						field : "room.roomType.name",
						visible : "yes",
						"header" : '房间类型',
						"headerClass" : "th_roomTypeClass",
						"dataClass" : "td_roomTypeClass"
					},
					{
						field : "stateName",
						visible : "yes",
						"header" : '状态',
						"headerClass" : "th_stateClass",
						"dataClass" : "td_stateClass"
					},
					{
						field : "room.description",
						visible : "yes",
						"header" : '说明信息',
						"headerClass" : "th_descriptionClass",
						"dataClass" : "td_descriptionClass"
					},
					{
						field : "room.genTime",
						visible : "yes",
						"header" : '生成时间',
						"headerClass" : "th_genTimeClass",
						"dataClass" : "td_genTimeClass",
					}
			],
			sorting :
			[
					{
						sortingName : "生成时间排序",
						field : "genTime",
						order : "DESC"
					},
					{
						sortingName : "房间类型排序",
						field : "roomType.name",
						order : "none"
					},
					{
						sortingName : "状态排序",
						field : "state",
						order : "none"
					},
					{
						sortingName : "名称排序",
						field : "name",
						order : "none"
					},
					{
						sortingName : "全天价格排序",
						field : "roomType.dayPrice",
						order : "none"
					},
					{
						sortingName : "小时价格排序",
						field : "roomType.hourPrice",
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
							filterLabel : "房间名",
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
							filterName : "stateFilter",
							"filterType" : "text",
							field : "state",
							filterLabel : "状态",
							
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
										type : "checkbox"
									},
									vertical_orientation : "yes"
								}
							],
							lookup_values :
							[
									//状态（1干净、2在住、3脏房、4不可用房）
									{
										lk_option : "干净房",
										lk_value : 1,
									},
									{
										lk_option : "在住房",
										lk_value : 2,
									},
									{
										lk_option : "脏房",
										lk_value : 3,
									},
									{
										lk_option : "不可用房",
										lk_value : 4,
									}
							]
						},
						{
							filterName : "roomTypeNameFilter",
							"filterType" : "text",
							field : "roomType.name",
							filterLabel : "类型名",
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
				],
				decimal_separator : "."
			},
			onChangeSelectedRows : function()
			{
				var elem_jui_dropdown_launcher_span = $('#' + $("#roomPaginationDIV").jui_datagrid('getDropdownLauncherID') + ' span');
				elem_jui_dropdown_launcher_span.addClass('juib_fix');
			},
			onDisplay : function()
			{
				$("button#tools_roomPaginationDIV_add").click(function()
				{
					//添加后要将input 清空，否则 validate框架不会验证。
					$('#addForm')[0].reset();
					
					$("#addRoomDialog").dialog(
					{
						"width" : 360,
						"height" : 280,
					});
				});
				var elem_jui_dropdown_launcher_span = $('#' + $("#roomPaginationDIV").jui_datagrid('getDropdownLauncherID') + ' span');
				elem_jui_dropdown_launcher_span.addClass('juib_fix');
			},
			onDBClick : function(event,data)
			{
				requestARoom(data.row_id);
			},
			onDelete : function()
			{
				var ids = $(this).jui_datagrid("getSelectedIDs"), num = ids.length;
				if (num == 0)
				{
					openMessageDialog("没有数据被选中，请标记后再删除", "提示");
				}
				else
				{
					deleteRoom(JSON.stringify(ids));
				}
			},
		});
		
		//添加房间表单验证 ajax 提交
		$('#addForm').bind('valid.form', function()
		{
			$.ajax(
			{
				url : '/InnMIS/room/roomAdd.do',
				type : 'POST',
				data : $(this).serialize(),
				success : function(data)
				{
					openMessageDialog(data, "添加提示");
					destroyDialog("addRoomDialog");
					//刷新
					$("#roomPaginationDIV").jui_datagrid('refresh');
				}
			});
		});
		
		//定义修改后的刷新函数
		freshFunctionAfterModify = function(data)
		{
			$("#roomPaginationDIV").jui_datagrid('refresh');
		};
		//定义删除后的刷新函数.
		freshFunctionAfterDelete = function()
		{
			paginationDIVRefresh("roomPaginationDIV");
		};
	});
	
</script>




</head>
<body>
	<div id="roomPaginationDIV"></div>

	<div id="addRoomDialog"  title="添加" style="display: none;">
		<form id="addForm" >
		<input type="text" maxlength="20"  id="addRoomRoomTypeIdInputId" name="roomType.id" style="display: none;" readonly="readonly"/>
		<table>
			<tr><td>房间名称：</td><td><input type="text" maxlength="20"  name="name" 
				data-rule="房间名称:required;name;remote[/InnMIS/room/roomAddValidateName.do]"  /></td></tr>
			<tr><td>房间类型：</td><td><input type="text" maxlength="20"  name="roomTypeName" id="addRoomRoomTypeNameInputId" readonly="readonly" 
				data-rule="房间类型:required;roomTypeName" /></td>
				<td><button type="button" onclick="chooseRoomTypeForAdd('miniRoomTypePageId','addRoomRoomTypeIdInputId','addRoomRoomTypeNameInputId')">..</button></td></tr>
			<tr><td>说明信息：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
			<tr><td><button type="submit" >确定</button></td><td style="text-align: center;"><button onclick="closeDialog('addRoomDialog')" type="button">取消</button></td></tr>
		</table>
		</form>
	</div>
	
</body>

</html>

