/**   
 * projectName: InHome
 *
 * fileName: JuiFilterCondition.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-4-26 下午9:40:24 
 *
 * version : V1.0 
 */
package tang.li.inn.infrastructure.jui;

import java.util.List;

/**
 * <description>
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
public class JuiFilterCondition
{

	// equal field = value
	// not_equal field != value
	// begins_with field like "value%"
	// not_begins_with field not like "value%"
	// contains field like "%value%"
	// not_contains field not like "%value%"
	// ends_with field like "%value"
	// not_ends_with field not like "%value"
	// is_empty field = ''
	// is_not_empty field != ''
	// in field in ('value','value','value')
	// not_in field in ('value','value','value')
	// less field < value
	// less_or_equal field <= value
	// greater field > value
	// greater_or_equal field >= value

	public static final String JUI_FILTER_OPERATOR_EQUAL = "equal";
	public static final String JUI_FILTER_OPERATOR_NOT_EQUAL = "not_equal";
	public static final String JUI_FILTER_OPERATOR_BEGIN_WITH = "begins_with";
	public static final String JUI_FILTER_OPERATOR_NOT_BEGIN_WITH = "not_begins_with";
	public static final String JUI_FILTER_OPERATOR_CONTAINS = "contains";
	public static final String JUI_FILTER_OPERATOR_NOT_CONTAINS = "not_contains";
	public static final String JUI_FILTER_OPERATOR_ENDS_WITH = "ends_with";
	public static final String JUI_FILTER_OPERATOR_NOT_ENDS_WITH = "not_ends_with";
	public static final String JUI_FILTER_OPERATOR_IS_EMPTY = "is_empty";
	public static final String JUI_FILTER_OPERATOR_IS_NOT_EMPTY = "is_not_empty";
	public static final String JUI_FILTER_OPERATOR_IN = "in";
	public static final String JUI_FILTER_OPERATOR_NOT_IN = "not_in";
	public static final String JUI_FILTER_OPERATOR_LESS = "less";
	public static final String JUI_FILTER_OPERATOR_LESS_OR_EQUAL = "less_or_equal";
	public static final String JUI_FILTER_OPERATOR_GREATER = "greater";
	public static final String JUI_FILTER_OPERATOR_GREATER_OR_EQUAL = "greater_or_equal";

	// "filterType": "date",
	// "field": "genTime",
	// "operator": "equal",
	// "filterValue": [
	// "20140425160000"
	// ]

	private String filterType;
	private String field;
	private String operator;
	private List<String> filterValue;

	public String getFilterType()
	{
		return filterType;
	}

	public void setFilterType(String filterType)
	{
		this.filterType = filterType;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public List<String> getFilterValue()
	{
		return filterValue;
	}

	public void setFilterValue(List<String> filterValue)
	{
		this.filterValue = filterValue;
	}
	
	
	/**
	 * 如果字段是int 或者 Integer等非string类型的，那么 Hibernate在设置查询参数的时候（调用query.list()时）试图一个强制转换。因此会发生 String cannot cast to Integer 的异常！
	 * 项目中entity只涉及到 String int double boolean 因此只对这些值做监测。 
	 * 最后发现 本来condition就有一个fieldType字段，可以在客户端设置类型,但也有其局限的地方
	 * filterType 只有三种形式 "filterType": "number", "numberType": "double",   "filterType": "text", "filterType": "date"
	 * 
	 * 而此函数支持 filterType像 room.name 这样的形式
	 * @param values
	 * @param fieldName
	 * @param value
	 * @param clazz
	 */
	private static <T> void checkTypeThenAddValue(List<Object> values,String fieldName,String value,Class<T> clazz)
	{
		try
		{
			String[] names = fieldName.split("\\.");
			@SuppressWarnings("rawtypes")
			Class C = clazz;
			for(int i = 0 ;i <names.length ;i ++)
			{
				C = C.getDeclaredField(names[i]).getType();
			}
			String type = C.toString();

			
			if(type.equals("int") || 
					type.equals("Integer") )
			{
				values.add(Integer.parseInt(value));
			}
			else if(type.equals("double"))
			{
				values.add(Double.parseDouble(value));
			}
			else if(type.equals("boolean"))
			{
				
				if(value.equals("0"))
				{
					values.add(new Boolean(false));
				}
				else
				{
					values.add(new Boolean(true));
				}
			}
			else
			{
				values.add(value);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			values.add(value);
		}
	}
	
	
//	private static void checkTypeThenAddValue(List<Object> values,String type, String value )
//	{
//		if (type.equals("text")||type.equals("date"))
//		{
//			values.add(value);
//
//		}
//		else if (type.equals("integer"))
//		{
//			values.add(Integer.parseInt(value));
//		}
//		else if (type.equals("double"))
//		{
//			values.add(Double.parseDouble(value));
//		}
//		else if (type.equals("boolean"))
//		{
//			if (value.equals("0"))
//			{
//				values.add(new Boolean(false));
//			}
//			else
//			{
//				values.add(new Boolean(true));
//			}
//		}
//		else
//		{
//			values.add(value);
//		}
//
//	}
	

	/**
	 * @param condition
	 * @return 返回左右无空隙的条件
	 */
	public static <T> String conditionToHQL(JuiFilterCondition condition, List<Object> values,Class<T> clazz)
	{

		String operator = condition.getOperator();

		if (operator.equals(JUI_FILTER_OPERATOR_EQUAL))
		{
//			values.add(condition.getFilterValue().get(0));
			checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(0),clazz);
//			checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(0));
			return condition.getField() + " = " + "?";
		}
		else if (operator.equals(JUI_FILTER_OPERATOR_NOT_EQUAL))
		{
//			values.add(condition.getFilterValue().get(0));
			checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(0),clazz);
//			checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(0));
			return condition.getField() + " != " + "?";

		}
		else if (operator.equals(JUI_FILTER_OPERATOR_BEGIN_WITH))
		{
			values.add(condition.getFilterValue().get(0) + "%");
			return condition.getField() + " like ?";

		}
		else if (operator.equals(JUI_FILTER_OPERATOR_NOT_BEGIN_WITH))
		{
			values.add(condition.getFilterValue().get(0) + "%");
			return condition.getField() + " not like ?";
		}
		else if (operator.equals(JUI_FILTER_OPERATOR_CONTAINS))
		{
			values.add("%" + condition.getFilterValue().get(0) + "%");
			return condition.getField() + " like ?";

		}
		else if (operator.equals(JUI_FILTER_OPERATOR_NOT_CONTAINS))
		{
			values.add("%" + condition.getFilterValue().get(0) + "%");
			return condition.getField() + " not like ?";

		}
		else if (operator.equals(JUI_FILTER_OPERATOR_ENDS_WITH))
		{
			values.add("%" + condition.getFilterValue().get(0));
			return condition.getField() + " like ?";

		}
		else if (operator.equals(JUI_FILTER_OPERATOR_NOT_ENDS_WITH))
		{
			values.add("%" + condition.getFilterValue().get(0));
			return condition.getField() + " not like ?";

		}
		else if (operator.equals(JUI_FILTER_OPERATOR_IS_EMPTY))
		{
			return condition.getField() + " = ''";

		}
		else if (operator.equals(JUI_FILTER_OPERATOR_IS_NOT_EMPTY))
		{
			return condition.getField() + " != ''";

		}
		else if (operator.equals(JUI_FILTER_OPERATOR_IN))
		{

			String result = condition.getField() + " in (";

			int inNum = condition.getFilterValue().size();
			for (int i = 0; i < inNum - 1; i++)
			{
				result += "?,";
//				values.add(condition.getFilterValue().get(i));
				checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(i),clazz);
//				checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(i));
			}
			result += "?)";
//			values.add(condition.getFilterValue().get(inNum - 1));
			checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(inNum-1),clazz);
//			checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(inNum-1));
			return result;

		}
		else if (operator.equals(JUI_FILTER_OPERATOR_NOT_IN))
		{
			String result = condition.getField() + " not in (";

			int inNum = condition.getFilterValue().size();
			for (int i = 0; i < inNum - 1; i++)
			{
				result += "?,";
//				values.add(condition.getFilterValue().get(i));
				checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(i),clazz);
//				checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(i));
				
			}
			result += "?)";
//			values.add(condition.getFilterValue().get(inNum - 1));
			checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(inNum-1),clazz);
//			checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(inNum-1));
			return result;
		}
		else if (operator.equals(JUI_FILTER_OPERATOR_LESS))
		{
//			values.add(condition.getFilterValue().get(0));
			checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(0),clazz);
//			checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(0));
			return condition.getField() + " < ?";
		}
		else if (operator.equals(JUI_FILTER_OPERATOR_LESS_OR_EQUAL))
		{
//			values.add(condition.getFilterValue().get(0));
			checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(0),clazz);
//			checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(0));
			return condition.getField() + " <= ?";
		}
		else if (operator.equals(JUI_FILTER_OPERATOR_GREATER))
		{
//			values.add(condition.getFilterValue().get(0));
			checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(0),clazz);
//			checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(0));
			return condition.getField() + " > ?";
		}
		else if (operator.equals(JUI_FILTER_OPERATOR_GREATER_OR_EQUAL))
		{
//			values.add(condition.getFilterValue().get(0));
			checkTypeThenAddValue(values,condition.getField(),condition.getFilterValue().get(0),clazz);
//			checkTypeThenAddValue(values,condition.getFilterType(),condition.getFilterValue().get(0));
			return condition.getField() + " >= ?";
		}
		return "1=1";
	}
}
