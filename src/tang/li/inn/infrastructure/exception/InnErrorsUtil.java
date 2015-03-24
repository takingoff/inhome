 /**   
* projectName: InnInfrastructure
*
* fileName: ErrorsUtil.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-11 上午11:26:15 
*
* version : V1.0 
*/
package tang.li.inn.infrastructure.exception;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class InnErrorsUtil
{
	
	private static InnErrors errors;

	
	
	public static InnErrors getErrors()
	{
		return errors;
	}

	public static void setErrors(InnErrors errors)
	{
		InnErrorsUtil.errors = errors;
	}
	
	
	public static InnError getInnError(String key)
	{
		return errors.getError(key); 
	}
	
	
}
