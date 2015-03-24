 /**   
* projectName: InnMIS
*
* fileName: JuiPagination.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-26 下午2:00:45 
*
* version : V1.0 
*/
package tang.li.inn.infrastructure.jui;

import java.util.List;

/**
 *分页数据请求 返回给客户端的信息
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class JuiPaginationSupport<T>
{
//	server_error = a_data['error'];
//	total_rows = a_data['totalRows'];
//  page_data = a_data['pageData'];
	
	
	private String error;
	private int totalRows;
	private List<T> pageData;
	public String getError()
	{
		return error;
	}
	public void setError(String error)
	{
		this.error = error;
	}
	public int getTotalRows()
	{
		return totalRows;
	}
	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}
	public List<T> getPageData()
	{
		return pageData;
	}
	public void setPageData(List<T> pageData)
	{
		this.pageData = pageData;
	}
	
	public JuiPaginationSupport(String error,int totalRows,List<T> pageData)
	{
		this.error = error;
		this.totalRows = totalRows;
		this.pageData = pageData;
	}
	
	
}