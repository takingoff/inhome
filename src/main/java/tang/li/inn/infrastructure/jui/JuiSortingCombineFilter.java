 /**   
* projectName: InHome
*
* fileName: JuiSortingCombineFilter.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-26 下午9:16:26 
*
* version : V1.0 
*/
package tang.li.inn.infrastructure.jui;

import java.util.List;

/**
 *分页数据请求 。客户端请求过来的信息
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class JuiSortingCombineFilter
{
	
//	 var combineObj =
//         {
//         		pageNum:settings.pageNum,
//         		rowsPerPage:settings.rowsPerPage,
//         		sortings:settings.sorting,
//         		filters:toJuiElementFilters(filters),
//         };
	//当前请求页码
	private int pageNum;
	//页行数
	private int rowsPerPage;
	//过滤参数
	private List<JuiElementFilter> filters;
	//排序参数
	private List<JuiElementSorting> sortings;
	
	public List<JuiElementFilter> getFilters()
	{
		return filters;
	}
	public void setFilters(List<JuiElementFilter> filters)
	{
		this.filters = filters;
	}
	public List<JuiElementSorting> getSortings()
	{
		return sortings;
	}
	public void setSortings(List<JuiElementSorting> sortings)
	{
		this.sortings = sortings;
	}
	public int getPageNum()
	{
		return pageNum;
	}
	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}
	public int getRowsPerPage()
	{
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage)
	{
		this.rowsPerPage = rowsPerPage;
	}
	
}
