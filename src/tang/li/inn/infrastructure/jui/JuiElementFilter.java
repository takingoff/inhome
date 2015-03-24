 /**   
* projectName: InnMIS
*
* fileName: JuiElementFilter.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-26 下午8:56:57 
*
* version : V1.0 
*/
package tang.li.inn.infrastructure.jui;

import java.util.List;


/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class JuiElementFilter
{
	
//    {
//        "element_rule_id": "rule_flt_rules_demo_grid1_1",
//        "condition": {
//            "filterType": "date",
//            "field": "genTime",
//            "operator": "equal",
//            "filterValue": [
//                "20140425160000"
//            ]
//        },
//        "logical_operator": "OR"
//    }
	
	
//	  {
//          "condition": [
//              {
//                  "element_rule_id": "rule_flt_rules_demo_grid1_2",
//                  "condition": {
//                      "filterType": "text",
//                      "field": "name",
//                      "operator": "equal",
//                      "filterValue": [
//                          "asdf"
//                      ]
//                  },
//                  "logical_operator": "AND"
//              }
//          ],
//          "logical_operator": "OR"
//      },

	
	//OR 或者 AND 两个值可取
	private String logicalOperator;
	private JuiFilterCondition condition;
	private List<JuiElementFilter> group;
	
	public List<JuiElementFilter> getGroup()
	{
		return group;
	}
	public void setGroup(List<JuiElementFilter> group)
	{
		this.group = group;
	}
	public String getLogicalOperator()
	{
		return logicalOperator;
	}
	public void setLogicalOperator(String logicalOperator)
	{
		this.logicalOperator = logicalOperator;
	}
	public JuiFilterCondition getCondition()
	{
		return condition;
	}
	public void setCondition(JuiFilterCondition condition)
	{
		this.condition = condition;
	}
	
	
	/**
	 * @param filters
	 * @param values
	 * @return xx = xx and xx = xx <font color="red">前无间隙后有一个空格间隙</font> 如果filters没有数据 返回""
	 */
	public static <T>  String juiElementFiltersToHQL(List<JuiElementFilter> filters,List<Object> values,Class<T> clazz)
	{
		
		if(filters==null|| filters.size() ==0)
		{
			return "";
		}
		
		String hql="";
		
		int len = filters.size();
		for(int i=0 ;i <len-1;i ++)
		{
			if(filters.get(i).getGroup() != null)
			{
				hql += "(" +juiElementFiltersToHQL(filters.get(i).getGroup(),values,clazz) + ") " + filters.get(i).getLogicalOperator() +" ";
			}
			else
			{
				hql +=JuiFilterCondition.conditionToHQL(filters.get(i).getCondition(),values,clazz) +" " + filters.get(i).getLogicalOperator()+" ";
			}
		}
		
		//最后一个
		if(filters.get(len-1).getGroup() != null)
		{
			hql += "(" +juiElementFiltersToHQL(filters.get(len-1).getGroup(),values,clazz) + ") ";
		}
		else
		{
			hql +=JuiFilterCondition.conditionToHQL(filters.get(len-1).getCondition(),values,clazz) +" ";
		}
		
		return hql;
	}
	
}

