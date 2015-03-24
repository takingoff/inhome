 /**   
* projectName: InnMIS
*
* fileName: SessionUtil.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-19 下午7:11:17 
*
* version : V1.0 
*/
package tang.li.inn.mis.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import tang.li.inn.entity.entry.InnEntry;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class SessionUtil
{
	private final static String AUTH_CODE = "AUTH_CODE";
	
	private final static String STAFF_NAME = "STAFF_NAME";
	
	private final static String STAFF_LEVEL = "STAFF_LEVEL";
	
	public static void clearLoginAttribute(HttpSession session)
	{
		session.removeAttribute(AUTH_CODE);
		session.removeAttribute(STAFF_NAME);
		session.removeAttribute(STAFF_LEVEL);
	}
	public static void clearAttribute(HttpSession session,String attributeName)
	{
		session.removeAttribute(attributeName);
	}

	
	public static String getInnEntryValue(HttpSession session,String key)
	{
		return (String) session.getAttribute(key);
	}
		

	public static String getStaffName(HttpSession session)
	{
		return (String) session.getAttribute(STAFF_NAME);
	}


	public static int getStaffLevel(HttpSession session)
	{
		return (Integer) session.getAttribute(STAFF_LEVEL);
	}

	public static String getAuthCode(HttpSession session)
	{
		return (String) session.getAttribute(AUTH_CODE);
	}

	public static void saveInnEntry(HttpSession session,List<InnEntry> list)
	{
		for(InnEntry entry:list)
		{
			session.setAttribute(entry.getKey(),entry.getValue());
		}
	}

	public static void saveAuthCode(HttpSession session ,String value)
	{
		session.setAttribute(AUTH_CODE,value);
	}
	
	public static void saveStaffName(HttpSession session ,String value)
	{
		session.setAttribute(STAFF_NAME,value);
	}
	
	public static void saveStaffLEVEL(HttpSession session ,int value)
	{
		session.setAttribute(STAFF_LEVEL,value);
	}
	
	
	
}
