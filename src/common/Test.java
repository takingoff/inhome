package common;

import tang.li.inn.infrastructure.util.StringUtil;

public class Test
{

	public static void  main(String[] ar)
	{
		try
		{
			String md5Str = StringUtil.getMD5Str(StringUtil.getMD5Str("tangli")+"tangli");
			System.out.println(md5Str);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
