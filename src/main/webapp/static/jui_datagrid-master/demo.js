$(function()
{
	// demo grid1 --------------------------------------------------------------
	$("#demo_grid1").jui_datagrid(
	{
		columns :
		[
				{
					field : "customer_id",
					visible : "no",
					"header" : 'Code',
					"headerClass" : "th_code",
					"dataClass" : "td_code"
				},
				{
					field : "lastname",
					visible : "yes",
					"header" : 'Lastname',
					"headerClass" : "th_lastname",
					"dataClass" : "td_lastname"
				},
				{
					field : "firstname",
					visible : "yes",
					"header" : 'Firstname',
					"headerClass" : "th_firstname",
					"dataClass" : "td_firstname"
				},
				{
					field : "email",
					visible : "no",
					"header" : 'Email',
					"headerClass" : "th_email",
					"dataClass" : "td_email"
				},
				{
					field : "gender",
					visible : "yes",
					"header" : 'Gender',
					"headerClass" : "th_gender",
					"dataClass" : "td_gender"
				},
				{
					field : "date_of_birth",
					visible : "yes",
					"header" : 'Date of birth',
					"headerClass" : "th_date_of_birth",
					"dataClass" : "th_date_of_birth",
					column_value_conversion :
					{
						function_name : "date_decode",
						args :
						[
								{
									"col_index" : 5
								},
								{
									"value" : "DD/MM/YYYY"
								}
						]
					}
				},
				{
					field : "date_updated",
					visible : "yes",
					"header" : 'Date updated',
					"headerClass" : "th_date_updated",
					"dataClass" : "th_date_updated",
					column_value_conversion_server_side :
					{
						function_name : "UTC_timestamp_to_local_datetime",
						args :
						[
								{
									"col_index" : 6
								},
								{
									"value" : "Europe/Athens"
								},
								{
									"value" : "d/m/Y H:m:s"
								}
						]
					}
				}
		],
		sorting :
		[
				{
					sortingName : "Code",
					field : "customer_id",
					order : "none"
				},
				{
					sortingName : "Lastname",
					field : "lastname",
					order : "ascending"
				},
				{
					sortingName : "Firstname",
					field : "firstname",
					order : "ascending"
				},
				{
					sortingName : "Date of birth",
					field : "date_of_birth",
					order : "none"
				},
				{
					sortingName : "Date updated",
					field : "date_updated",
					order : "none"
				}
		],
		filterOptions :
		{
			filters :
			[
					{
						filterName : "Lastname",
						"filterType" : "text",
						field : "lastname",
						filterLabel : "Last name",
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
						filterName : "Firstname",
						"filterType" : "text",
						field : "firstname",
						filterLabel : "First name",
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
						filterName : "Gender",
						"filterType" : "number",
						"numberType" : "integer",
						field : "lk_genders_id",
						filterLabel : "Gender",
						excluded_operators :
						[
								"equal", "not_equal", "less", "less_or_equal", "greater", "greater_or_equal"
						],
						filter_interface :
						[
							{
								filter_element : "input",
								filter_element_attributes :
								{
									type : "checkbox"
								}
							}
						],
						lookup_values :
						[
								{
									lk_option : "Male",
									lk_value : "1"
								},
								{
									lk_option : "Female",
									lk_value : "2",
									lk_selected : "yes"
								}
						]
					},
					{
						filterName : "DateOfBirth",
						"filterType" : "date",
						field : "date_of_birth",
						filterLabel : "Date of birth",
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
									title : "Set the date using format: dd/mm/yyyy"
								},
								filter_widget : "datepicker",
								filter_widget_properties :
								{
									dateFormat : "dd/mm/yy",
									changeMonth : true,
									changeYear : true
								}
							}
						],
						validate_dateformat :
						[
							"DD/MM/YYYY"
						],
						filter_value_conversion_server_side :
						{
							function_name : "date_encode",
							args :
							[
									{
										"filter_value" : "yes"
									},
									{
										"value" : "d/m/Y"
									}
							]
						}
					},
					{
						filterName : "DateUpdated",
						"filterType" : "date",
						field : "date_updated",
						filterLabel : "Datetime updated",
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
									dateFormat : "dd/mm/yy",
									timeFormat : "HH:mm:ss",
									changeMonth : true,
									changeYear : true,
									showSecond : true
								}
							}
						],
						validate_dateformat :
						[
							"DD/MM/YYYY HH:mm:ss"
						],
						filter_value_conversion :
						{
							function_name : "local_datetime_to_UTC_timestamp",
							args :
							[
									{
										"filter_value" : "yes"
									},
									{
										"value" : "DD/MM/YYYY HH:mm:ss"
									}
							]
						}
					}
			],
			decimal_separator : "."
		},
		dlgFiltersClass : 'grid1_filters',
		ajaxFetchDataURL : 'ajax/ajax_fetch_data1.php',
		row_primary_key : 'customer_id',
		containerClass : 'grid1_container ui-state-default ui-corner-all',
		datagridClass : 'grid1_data ui-widget-content',
		caption : 'Customers',
		showExportButton : false,
		showPrintButton : false,
		debug_mode : "yes",
		onDelete : function()
		{
			var a_sel = $(this).jui_datagrid("getSelectedIDs"), sel = a_sel.length;
			if (sel == 0)
			{
				log = 'Nothing selected...';
				create_log(elem_dlg_log1, log);
			}
			else
			{
				log = sel + ' Row(s) with ID: ' + a_sel + ' will be deleted.';
				create_log(elem_dlg_log1, log);
			}
		},
		onCellClick : function(event,data)
		{
			log = 'Click on cell: col ' + data.col + ' row ' + data.row + '.';
			create_log(elem_dlg_log1, log);
		},
		onRowClick : function(event,data)
		{
			log = 'Row with ID ' + data.row_id + ' ' + data.row_status + '.';
			create_log(elem_dlg_log1, log);
		},
		onDatagridError : function(event,data)
		{
			log = 'ERROR: ' + data.err_description + ' (' + data.err_code + ').';
			create_log(elem_dlg_log1, log, true);
			if (data.hasOwnProperty("elem_filter"))
			{
				data.elem_filter.focus();
			}
		},
		onDebug : function(event,data)
		{
			create_log(elem_dlg_log1, data.debug_message, false, true);
		},
		onChangeSelectedRows : function()
		{
			// jQueryUI-bootstrap CSS fix
			var elem_jui_dropdown_launcher_span = $('#' + $("#demo_grid1").jui_datagrid('getDropdownLauncherID') + ' span');
			elem_jui_dropdown_launcher_span.addClass('juib_fix');
		},
		onDisplay : function()
		{
			// jQueryUI-bootstrap CSS fix
			var elem_jui_dropdown_launcher_span = $('#' + $("#demo_grid1").jui_datagrid('getDropdownLauncherID') + ' span');
			elem_jui_dropdown_launcher_span.addClass('juib_fix');
			log = 'Datagrid created.';
			create_log(elem_dlg_log1, log);
		}
	});
	$("#selection_multiple").click(function()
	{
		$("#demo_grid1").jui_datagrid(
		{
			rowSelectionMode : 'multiple'
		})
	});
	$("#selection_single").click(function()
	{
		$("#demo_grid1").jui_datagrid(
		{
			rowSelectionMode : 'single'
		})
	});
	$("#selection_false").click(function()
	{
		$("#demo_grid1").jui_datagrid(
		{
			rowSelectionMode : false
		})
	});
	elem_dlg_log1.dialog(
	{
		autoOpen : true,
		width : 250,
		height : 200,
		position :
		{
			my : "left",
			at : "right",
			of : '#demo_grid1'
		},
		title : "Log demo_grid1"
	});
	$("#log_show").click(function()
	{
		elem_dlg_log1.dialog("open");
		return false;
	});
	$("#log_hide").click(function()
	{
		elem_dlg_log1.dialog("close");
		return false;
	});
	$("#log_clear").click(function()
	{
		elem_dlg_log1.html('');
	});
	// demo grid2 --------------------------------------------------------------
	$("#dlg_demo_grid2").dialog(
	{
		autoOpen : false,
		title : "Customers dialog",
		width : 750,
		height : 390,
		hide : "fade",
		zIndex : 500,
		open : function()
		{
			// just to fix column widths
			$("#demo_grid2").jui_datagrid('refresh');
		}
	});
	$("#demo_grid2").jui_datagrid(
	{
		columns :
		[
				{
					field : "id",
					visible : "no",
					"header" : 'Code',
					"headerClass" : "",
					"dataClass" : ""
				},
				{
					field : "lastname",
					visible : "yes",
					"header" : 'Lastname',
					"headerClass" : "",
					"dataClass" : ""
				},
				{
					field : "firstname",
					visible : "yes",
					"header" : 'Firstname',
					"headerClass" : "",
					"dataClass" : ""
				},
				{
					field : "gender",
					visible : "yes",
					"header" : 'Gender',
					"headerClass" : "",
					"dataClass" : ""
				}
		],
		sorting :
		[
				{
					"sortingName" : "Code",
					field : "id",
					order : "ascending"
				},
				{
					"sortingName" : "Lastname",
					field : "lastname",
					order : "none"
				},
				{
					"sortingName" : "Firstname",
					field : "firstname",
					order : "none"
				}
		],
		ajaxFetchDataURL : 'ajax/ajax_fetch_data2.php',
		row_primary_key : 'id',
		containerClass : 'grid2_container ui-state-default ui-corner-all',
		datagridClass : 'grid2_data ui-widget-content',
		paginationOptions :
		{
			sliderAnimation : false
		},
		onChangeSelectedRows : function()
		{
			// jQueryUI-bootstrap CSS fix
			var elem_jui_dropdown_launcher_span = $('#' + $("#demo_grid2").jui_datagrid('getDropdownLauncherID') + ' span');
			elem_jui_dropdown_launcher_span.addClass('juib_fix');
		},
		onDisplay : function()
		{
			// jQueryUI-bootstrap CSS fix
			var elem_jui_dropdown_launcher_span = $('#' + $("#demo_grid2").jui_datagrid('getDropdownLauncherID') + ' span');
			elem_jui_dropdown_launcher_span.addClass('juib_fix');
		}
	});
	elem_dlg_demo_grid2_opener.button(
	{
		icons :
		{
			primary : 'ui-icon-newwin'
		}
	});
	elem_dlg_demo_grid2_opener.click(function()
	{
		$("#dlg_demo_grid2").dialog("open");
		return false;
	});
});