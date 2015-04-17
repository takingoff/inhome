/**
 * projectName: InnInfrastructure
 * 
 * fileName: ReflectUtil.java
 * 
 * author : tangli <tanglidehaizi@gamil.com>
 * 
 * createTime :2014 2014-4-11 下午8:30:01
 * 
 * version : V1.0
 * 
 */
package tang.li.inn.infrastructure.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
*/
public class ReflectUtil
{
	/**
	 * 将map类型列表转化为指定对象列表
	 * 
	 * @param list
	 * @param T
	 * @return
	 */
	public static <T> List<T> fromMapToObject(List<Map<String, Object>> list, Class<T> T)
	{
		try
		{
			List<T> retList = new ArrayList<T>();
			Field[] fields = T.getDeclaredFields();
			for (Map<String, Object> map : list)
			{
				Set<String> set = map.keySet();
				T object = T.newInstance();
				for (String key : set)
				{
					for (Field field : fields)
					{
						if (key.equals(field.getName()))
						{
							Method method = T.getMethod("set" + field.getName().substring(0, 1).toUpperCase()
									+ field.getName().substring(1), field.getType());
							Object value = map.get(key);
							method.invoke(object, value);
						}
					}
				}
				retList.add(object);
			}
			return retList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将数组类型列表，转化为指定对象类型列表
	 * 
	 * @param list
	 * @param T
	 * @param fields
	 * @return
	 */
	public static <T> List<T> fromArrayToObject(List<Object[]> list, Class<T> T, String[] fields)
	{
		try
		{
			List<T> retList = new ArrayList<T>();
			for (Object[] values : list)
			{
				T object = T.newInstance();
				int lenght = fields.length;
				for (int i = 0; i < lenght; i++)
				{
					String field = fields[i];
					Field _field = T.getDeclaredField(field);
					Method method = T.getDeclaredMethod(
							"set" + field.substring(0, 1).toUpperCase() + field.substring(1), _field.getType());
					if (values[i] != null)
						method.invoke(object, values[i]);
				}
				retList.add(object);
			}
			return retList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
