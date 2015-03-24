/**   
 * projectName: InnMIS
 *
 * fileName: StringUtil.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-4-19 下午7:33:46 
 *
 * version : V1.0 
 */
package tang.li.inn.infrastructure.util;

import java.util.Date;

/**
 * <description>
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
public class StringUtil
{
	static public boolean validateAuthCode(String s1, String s2)
	{
		if(s1==null||s2==null)
			return false;
		
		if (s1.compareToIgnoreCase(s2) == 0)
			return true;
		return false;
	}

	public static boolean isLong(String str)
	{
		if (str.isEmpty())
		{
			return false;
		}
		try
		{
			Long.parseLong(str);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static boolean isInt(String str)
	{
		if (str.isEmpty())
		{
			return false;
		}
		try
		{
			Integer.parseInt(str);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}

	}

	@SuppressWarnings("deprecation")
	public static boolean isDate(String str)
	{
		if (str.isEmpty())
		{
			return false;
		}
		try
		{
			Date.parse(str);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/** 检查是否为空{null||""} 空返回false，非空返回true */
	public static boolean checkNotEmpty(String str)
	{
		return (str != null) && (!str.isEmpty());
	}

	/** 检查是否为空{null||""} 空返回true，非空返回false */
	public static boolean checkNull(String str)
	{
		return (str == null) || (str.isEmpty());
	}

}
