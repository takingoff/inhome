 /**   
* projectName: InnDomain
*
* fileName: RoomService.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午2:57:33 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.room;

import java.util.List;

import tang.li.inn.entity.room.Room;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;

/**
 *状态（1干净、2在住、3脏房、4不可用房）
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public interface RoomService
{

	/**
	 * @param entity
	 * @return 返回新增id。
	 * @throws InnException
	 */
	public String save(Room entity)throws InnException;
	
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
	public boolean modifyUpdate(Room entity)throws InnException;
	
	/**
	 * @param id
	 * @return
	 * @throws InnException
	 */
	public Room getById(String id)throws InnException;
	
	/**
	 * @return
	 * @throws InnException
	 */
	public List<Room> getAll()throws InnException;
	
	
	public JuiPaginationSupport<Room> juiPageFind(JuiSortingCombineFilter jscf) throws InnException;
	
	/**
	 * 通过试着调用 findUniqueByProperty 测试名字是否已经存在。
	 * @param value
	 * @return  存在返回true,不存在返回 false
	 * @throws InnException
	 */
	public boolean judgeExistByName(String value)throws InnException;
	
	/**
	 * @param roomTypeId
	 * @return
	 * @throws InnException
	 */
	public List<Room> getByRoomType(String roomTypeId)throws InnException;
	
	public List<Room> getByRoomName(String value)throws InnException;
	
	public JuiPaginationSupport<Room> switchRoomJuiPageFind(JuiSortingCombineFilter jscf,String roomId) throws InnException;
	
}
