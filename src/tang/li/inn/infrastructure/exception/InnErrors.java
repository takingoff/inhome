/**   
 * projectName: InnInfrastructure
 *
 * fileName: InnInnErrors.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-4-11 上午9:28:55 
 *
 * version : V1.0 
 */
package tang.li.inn.infrastructure.exception;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <description>
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
public class InnErrors
{
	
	/** 异常列表 */
	private ConcurrentHashMap<String, InnError> errorMap = new ConcurrentHashMap<String, InnError>();

	public ConcurrentHashMap<String, InnError> getErrorMap() {
		return this.errorMap;
	}

	public void setErrorMap(ConcurrentHashMap<String, InnError> errorMap) {
		this.errorMap = errorMap;
	}

	/**
	 * 获取异常信息
	 * 
	 * @param key
	 * @return
	 */
	public InnError getError(String key) {
		return this.errorMap.get(key);
	}
	
}
