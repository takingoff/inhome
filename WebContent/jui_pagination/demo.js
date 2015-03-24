$(function()
{
	initialPagination();
	ajaxRequestJsonData(0,10);
});

function ajaxRequestJsonData(index,rowsPerPage)
{
	$.ajax(
	{
		url : '/InnMIS/roomType/listRoomType.do?rowsPerPage=' + rowsPerPage + '&index=' + index,
		type : 'POST',// 这个地方如果不用post ie浏览器中登录退出后不会访问url直接进入success中导致无法登录。
		dataType:"json", //产生效果  Accept:application/json, text/javascript, */*;  但并不是必要的。因为spring会默认使用json
		success : function(data)
		{
			
			var totalPages = Math.floor(data.totalRows/data.rowsPerPage);
			if((data.totalRows%data.rowsPerPage)!=0 || totalPages == 0)
			{
				totalPages += 1;
			}
			var currentPage = Math.floor(data.startIndex/data.rowsPerPage) +1;
			$("#paginationDiv").jui_pagination({
				
				totalPages : totalPages,
				rowsPerPage: data.rowsPerPage,
				currentPage: currentPage,
				
			});
			
			//前缀 + 当前容器的id 就为容器中的某个元素的Id了。 
			 var prefix = $("#paginationDiv").jui_pagination('getOption', 'nav_rows_info_id_prefix');
//			 alert(prefix + $("#paginationDiv").attr("id"));
		     $("#" + prefix + $("#paginationDiv").attr("id")).text('总数:'+data.totalRows);
			
		}
	});
	
}

function initialPagination()
{
	$("#paginationDiv").jui_pagination(
	{
		totalPages : 1,
		currentPage : 1,
		rowsPerPage : 10,
		visiblePageLinks : 3, //至少有4页才会出现slider，，pageLnk的最小值是5.
		
		containerClass : 'container1', //css文件中设置
		useSlider : true,
		sliderClass : 'slider1',
		sliderInsidePane : false,
		useSliderWithPagesCount : 0, // show slider over specified number of pages
		sliderOrientation : 'horizontal',
		sliderAnimation : 'fast', // true (or 'slow' or 'fast' or duration in milliseconds) or false
		disableSelectionNavPane : false,// disable text selection and double click (jquery >= 1.8)
		showGoToPage : true,
		//showLabelCurrentPage: true,
		//showCurrentPage: true,
		//showNavPages: true,
		navPagesMode : 'first-last-always-visible', // alternative mode is 'continuous'
		//showLabelTotalPages: true,
		//showTotalPages: true,
		showRowsPerPage : true,
		showRowsInfo : true,
		showPreferences : true,
		navRowsPerPageClass : 'rows-per-page1  ui-state-default ui-corner-all',
		navGoToPageClass : 'goto-page1 ui-state-default ui-corner-all',
		//当前页面号改变事件 参数为新的页码
		onChangePage : function(event,page_num)
		{
			if (isNaN(page_num) || page_num <= 0)
			{
				page_num = 1;
			}
			
			//算出index
			var rowsPerPage = $(this).jui_pagination('getOption', 'rowsPerPage');
			var index = (page_num - 1) *rowsPerPage ;
			ajaxRequestJsonData(index,rowsPerPage);
			
		},
		//每页显示数目改变事件 参数为当前设置的页面显示数
		//一旦每页显示数目改变，都跳到第一页去。避免请求不到数据。
		onSetRowsPerPage : function(event,rpp)
		{
			if (isNaN(rpp) || rpp <= 0)
			{
				rpp=20;
			}
			
			$(this).jui_pagination(
			{
				rowsPerPage : rpp
			});
			ajaxRequestJsonData(0,rpp);
		},
		// 第一次显示时的事件
		onDisplay : function()
		{
			
		}
	});
}