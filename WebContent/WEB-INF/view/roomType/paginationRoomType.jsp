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

#paginationDIV table {  
     table-layout:fixed;  
}  

#paginationDIV td{
     white-space: nowrap;
     text-align: left;
     overflow: hidden;
 }
.grid_td_row_number {
	text-align: left;
	width: 2%;
}
.grid_th_row_number {
	text-align: left;
	width: 2%;
}

.th_idClass {
	width: 5%;
}

.th_nameClass {
	width: 15%;
}

.th_dayPriceClass {
	width: 8%;
}

.th_hourPriceClass {
	width: 8%;
}

.th_descriptionClass {
	width: 49%;
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

.td_dayPriceClass {
	width: 8%;
}

.td_hourPriceClass {
	width: 8%;
}

.td_descriptionClass {
	width: 49%;
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
		$("#paginationDIV").jui_datagrid(
		{
		
			dlgFiltersClass : 'grid1_filters',  //过滤器对话框的风格
			ajaxFetchDataURL : '/InnMIS/roomType/roomTypePage.do', //数据请求地址
			row_primary_key: 'id',//列主键 必须为不重复的整数 。。。。太尼玛坑爹了。
			containerClass : 'grid1_container ui-state-default ui-corner-all', //整个表格的风格
			datagridClass : 'grid1_data ui-widget-content',//数据区的风格
			caption : '房间类型列表',//表格显示名字
			rowsPerPage: 15,
			rowSelectionMode: 'multiple', // 'multiple', 'single', 'false'
			
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
						field : "name",
						visible : "yes",
						"header" : '房间类型',
						"headerClass" : "th_nameClass",
						"dataClass" : "td_nameClass"
					},
					{
						field : "dayPrice",
						visible : "yes",
						"header" : '全天价格',
						"headerClass" : "th_dayPriceClass",
						"dataClass" : "td_dayPriceClass"
					},
					{
						field : "hourPrice",
						visible : "yes",
						"header" : '小时价格',
						"headerClass" : "th_hourPriceClass",
						"dataClass" : "td_hourPriceClass"
					},
					{
						field : "description",
						visible : "yes",
						"header" : '说明信息',
						"headerClass" : "th_descriptionClass",
						"dataClass" : "td_descriptionClass"
					},
					{
						field : "genTime",
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
						sortingName : "全天价格排序",
						field : "dayPrice",
						order : "none"
					},
					{
						sortingName : "小时价格排序",
						field : "hourPrice",
						order : "none"
					},
					{
						sortingName : "名称排序",
						field : "name",
						order : "none"
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
 
			onCellClick : function(event,data)
			{
			},
			onRowClick : function(event,data)
			{
			},
			
			onDatagridError : function(event,data)
			{
			},
			onChangeSelectedRows : function()
			{
				var elem_jui_dropdown_launcher_span = $('#' + $("#paginationDIV").jui_datagrid('getDropdownLauncherID') + ' span');
				elem_jui_dropdown_launcher_span.addClass('juib_fix');
			},
			onDisplay : function()
			{
				$("button#tools_paginationDIV_add").click(function()
				{
					//触发校验
					$('#addForm')[0].reset();
					
					$("#addDialog").dialog(
					{
						"width" : 360,
						"height" : 330,
					});
				});
				var elem_jui_dropdown_launcher_span = $('#' + $("#paginationDIV").jui_datagrid('getDropdownLauncherID') + ' span');
				elem_jui_dropdown_launcher_span.addClass('juib_fix');
			},
			onDBClick : function(event,data)
			{
				$.ajax(
					{
						url : '/InnMIS/roomType/roomTypeGet.do',
						data : JSON.stringify(data.row_id),
						contentType : "application/json",
						type : 'POST', 
						success : function(data)
						{
							//触发校验
					        $('#entityForm')[0].reset();
					        
					        $("#entityDialog").dialog({width:360,height:330});
					        $("#entityForm input[name=id]").val(data.id);
					        $("#entityForm input[name=genTime]").val(data.genTime);
					        $("#entityForm input[name=name]").val(data.name);
					        $("#entityForm input[name=dayPrice]").val(data.dayPrice);
					        $("#entityForm input[name=hourPrice]").val(data.hourPrice);
					        $("#entityForm textarea[name=description]").val(data.description);
						}
					});
				
			},
			onDelete : function()
			{
				var ids = $(this).jui_datagrid("getSelectedIDs"), num = ids.length;
				if(num == 0)
				{
					//首先清除验证信息 居然无效。
					//$("#addForm").validator("cleanUp");
					openMessageDialog("没有数据被选中，请标记后再删除","提示");
				}
				else
				{
					deleteRoomType(JSON.stringify(ids));				
				}
				
			},
		});
		
		$("button").button();
		
		
		
		//添加表单验证 ajax 提交
		$('#addForm').bind('valid.form', function()
		{
			$.ajax(
			{
				url : '/InnMIS/roomType/roomTypeAdd.do',
				type : 'POST',
				data : $(this).serialize(),
				success : function(data)
				{
					openMessageDialog(data,"添加提示");
					destroyDialog("addDialog");
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
				url : '/InnMIS/roomType/roomTypeModify.do',
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
	});
	
	
////////////////////////////////////function
	function deleteARoomType()
	{
		deleteAEntity("entityForm","id","entityDialog","paginationDIV","/InnMIS/roomType/roomTypeDelete.do");
	
	}
	
	function deleteRoomType(idJson)
	{
		deleteEntity("paginationDIV","/InnMIS/roomType/roomTypeDelete.do",idJson);
	}

	
</script>




</head>
<body>
	<div id="paginationDIV"></div>

	<div id="addDialog"  title="添加" style="display: none;">
		<form id="addForm" >
		<table>
			<tr><td>类型名称：</td><td><input type="text" maxlength="20"  name="name" 
				data-rule="类型名:required;name;remote[/InnMIS/roomType/roomTypeAddValidateName.do]" placeholder="类型名" /></td></tr>
			<tr><td>全天价格：</td><td><input type="text" maxlength="10" name="dayPrice" 
				data-rule="全天价格:required;dayPrice;integerAndDecimal" 
	        	data-rule-integerAndDecimal="[/^([1-9]{1}[0-9]*([.]{1}[0-9]+){0,1}|(0(.[0-9]+){0,1}))$/, '输入小数或整数']" /></td></tr>
			<tr><td>小时价格：</td><td><input type="text"  maxlength="10" name="hourPrice"
				data-rule="小时价格:required;hourPrice;integerAndDecimal" 
	        	data-rule-integerAndDecimal="[/^([1-9]{1}[0-9]*([.]{1}[0-9]+){0,1}|(0(.[0-9]+){0,1}))$/, '输入小数或整数']"/></td></tr>
			<tr><td>说明信息：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
			<tr><td><button type="submit">确定</button></td><td style="text-align: center;"><button onclick="closeDialog('addDialog')" type="button">取消</button></td></tr>
		</table>
		</form>
	</div>
	
	<div id="entityDialog" title="详情" style="display: none;">
		<form id="entityForm" >
		<table>
			<tr><td><input type="text" name="id" style="display: none;"/></td></tr>
			<tr><td>添加时间：</td><td><input type="text"  readonly="readonly" name="genTime" /></td></tr>
			<tr><td>类型名称：</td><td><input type="text" maxlength="20"  name="name" 
				data-rule="类型名:required;name;remote[/InnMIS/roomType/roomTypeModifyValidateName.do, id]"  /></td></tr>  <!-- 逗号后面一定要有一个空格 -->
			<tr><td>全天价格：</td><td><input type="text" maxlength="10" name="dayPrice" 
				data-rule="全天价格:required;dayPrice;integerAndDecimal" 
	        	data-rule-integerAndDecimal="[/^([1-9]{1}[0-9]*([.]{1}[0-9]+){0,1}|(0(.[0-9]+){0,1}))$/, '输入小数或整数']" /></td></tr>
			<tr><td>小时价格：</td><td><input type="text"  maxlength="10" name="hourPrice"
				data-rule="小时价格:required;hourPrice;integerAndDecimal" 
	        	data-rule-integerAndDecimal="[/^([1-9]{1}[0-9]*([.]{1}[0-9]+){0,1}|(0(.[0-9]+){0,1}))$/, '输入小数或整数']"/></td></tr>
			<tr><td>说明信息：</td><td><textarea  rows="5" cols="23" maxlength="400" name="description"  ></textarea></td></tr>
			<tr><td><button type="submit">修改</button></td><td style="text-align: center;"><button  onclick="deleteARoomType()" type="button">删除</button></td>
			<td><button onclick="destroyDialog('entityDialog')" type="button">关闭</button></td></tr>
		</table>
		</form>
	</div>

	
</body>

</html>

