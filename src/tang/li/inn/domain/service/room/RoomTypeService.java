 /**   
* projectName: InnDomain
*
* fileName: RoomTypeService.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午2:57:45 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.room;

import java.util.List;

import tang.li.inn.entity.room.RoomType;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public interface RoomTypeService
{
	/**
	 * @param entity
	 * @return 返回新增id。
	 * @throws InnException
	 */
	public String save(RoomType entity)throws InnException;
	
	
	/**
	 * 必须做是否还有房间的检查！
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
	public boolean modifyMerge(RoomType entity)throws InnException;

	
	/**
	 * @param id
	 * @return
	 * @throws InnException
	 */
	public RoomType getById(String id)throws InnException;
	
	
	
	public JuiPaginationSupport<RoomType> juiPageFind(JuiSortingCombineFilter jscf)throws InnException;
	
	/**
	 * 通过试着调用 findUniqueByProperty 测试名字是否已经存在。
	 * @param value
	 * @return  存在返回true,不存在返回 false
	 * @throws InnException
	 */
	public boolean judgeExistByName(String value)throws InnException;
	
	public List<RoomType> getByName(String value)throws InnException;
	
	
}
