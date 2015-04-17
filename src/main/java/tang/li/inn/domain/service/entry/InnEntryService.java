 /**   
* projectName: InnDomain
*
* fileName: InnContainerService.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-24 上午7:50:11 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.entry;

import java.util.List;

import tang.li.inn.entity.entry.InnEntry;
import tang.li.inn.infrastructure.exception.InnException;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public interface InnEntryService
{
	/**
	 * @param entity
	 * @return 返回新增id。
	 * @throws InnException
	 */
	public String save(InnEntry entity)throws InnException;
	
	/**
	 * @param id
	 * @return 是否删除成功
	 * @throws InnException
	 */
	public boolean delete(String id)throws InnException;
	
	/**
	 * @param entity
	 * @return 是否修改成功
	 * @throws InnException
	 */
	public boolean modify(InnEntry entity)throws InnException;
	
	/**
	 * @param id
	 * @return
	 * @throws InnException
	 */
	public InnEntry getById(String id)throws InnException;
	
	/**
	 * @return
	 * @throws InnException
	 */
	public List<InnEntry> getAll()throws InnException;
	
	
	/**
	 * @param keyName 获取唯一的key值对应的Entry对象
	 * @return 
	 * @throws InnException
	 */
	public InnEntry findUniqueByKey(String keyName)throws InnException;
	
}
