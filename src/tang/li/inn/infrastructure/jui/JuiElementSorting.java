 /**   
* projectName: InnMIS
*
* fileName: JuiSortingElement.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-26 下午8:50:17 
*
* version : V1.0 
*/
package tang.li.inn.infrastructure.jui;

import java.util.List;

import tang.li.inn.infrastructure.util.InnConstant;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class JuiElementSorting
{
//	field: "customer_id"
//	order: "none"
//	sortingName: "id排序"

	private String field;
	private String order;
	private String sortingName;
	public String getField()
	{
		return field;
	}
	public void setField(String field)
	{
		this.field = field;
	}
	public String getOrder()
	{
		return order;
	}
	public void setOrder(String order)
	{
		this.order = order;
	}
	public String getSortingName()
	{
		return sortingName;
	}
	public void setSortingName(String sortingName)
	{
		this.sortingName = sortingName;
	}
	
	/**
	 * @param sortings
	 * @return order by xx des,xx asc,xx des 如果sortings中没有数据返回""
	 */
	public static String juiSortingsToHQL(List<JuiElementSorting> sortings)
	{
		
		
		if(sortings == null)
		{
			return "";
		}
		String result=""; 
		
		int validNum = 0;
		for(int i = 0 ;i<sortings.size();i ++)
		{
			//没指定排序
			if(sortings.get(i).getOrder().equals("none"))
			{
				continue;
			}
			//第一个有用排序
			if(validNum ==0)
			{
				result += InnConstant.FILTER_ORDER_BY+sortings.get(i).getField()+" " + sortings.get(i).getOrder();
			}
			//非第一个有用排序
			else
			{
				result +=  ","+sortings.get(i).getField()+" " + sortings.get(i).getOrder();
			}
			validNum += 1;
		}
		return result;
	}
	
	
}
