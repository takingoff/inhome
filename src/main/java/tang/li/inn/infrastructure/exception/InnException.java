/**   
 * projectName: InnInfrastructure
 *
 * fileName: MyDaoException.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-4-9 下午6:29:25 
 *
 * version : V1.0 
 */
package tang.li.inn.infrastructure.exception;

import org.apache.log4j.Logger;

/**
 * 自定义异常类
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
public class InnException extends Exception
{
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(InnException.class);

	private InnError innError;

	private String message;

	private void logException(Throwable exception)
	{
		StackTraceElement element = getStackTrace()[0];
		logger.error(element.getClassName()+"."+element.getMethodName()+element.getLineNumber()+
				"-{ [code:" + innError.getCode() + "] [info:" + innError.getInfo() +
				"] [description:" + innError.getDes()+"] [message:"+message +"] }");
		
		if(exception != null)
		{
			exception.printStackTrace();
		}
	}

	
	public InnException(InnError error,Throwable exception)
	{
		this.innError = error;
		logException(exception);
	}
	public InnException(InnError error,String message,Throwable exception)
	{
		this.innError = error;
		this.message = message;
		logException(exception);
	}

	
	public InnError getInnError()
	{
		return innError;
	}

	public void setInnError(InnError innError)
	{
		this.innError = innError;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
