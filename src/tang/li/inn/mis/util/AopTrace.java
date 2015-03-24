/**
 * projectName: InnMIS
 * 
 * fileName: AopTrace.java
 * 
 * author : tangli <tanglidehaizi@gamil.com>
 * 
 * createTime :2014 2014-4-8 下午10:58:11
 * 
 * version : V1.0
 * 
 */
package tang.li.inn.mis.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
*/
public class AopTrace
{
	public AopTrace()
	{
		System.out.println("/***************AopTraceStartup***************/");
	}
	

	/**
	 * This is the method which I would like to execute before a selected method
	 * execution.
	 */
	public void beforeAdvice(JoinPoint jp)
	{
		String message = "**********执行：" + jp.getTarget().getClass().getSimpleName() + "."
//				String message = "---------------\n**********执行：" + jp.getTarget().getClass().getSimpleName() + "."
				+ jp.getSignature().getName() + "(";

		Object[] args = jp.getArgs();
		for (int i = 0; i < args.length; i++)
		{
			message += args[i];
			if (i < args.length - 1)
				message += ",";
		}
//		System.out.println(message + ")\n---------------");
		System.out.println(message + ")");
	}

	/**
	 * This is the method which I would like to execute after a selected method
	 * execution.
	 */
	public void afterAdvice()
	{
	}

	/**
	 * This is the method which I would like to execute when any method returns.
	 */
	public void afterReturningAdvice(Object retVal)
	{
//		System.out.println(retVal);
	}

	/**
	 * This is the method which I would like to execute if there is an exception
	 * raised.
	 */
	public void AfterThrowingAdvice(IllegalArgumentException ex)
	{
		
	}
	
	
	public void aroundAdvice(ProceedingJoinPoint pjp)
	{
		try
		{
			pjp.proceed();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			System.out.println("AopTrace exception ---aroundAdvice");
		}
	}


}
