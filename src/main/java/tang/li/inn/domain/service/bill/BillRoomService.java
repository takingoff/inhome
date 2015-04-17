 /**   
* projectName: InnDomain
*
* fileName: BillRoom.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午2:55:43 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.bill;

import tang.li.inn.entity.bill.BillRoom;
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
public interface BillRoomService
{
	/**
	 * @param entity  要求 genDate字段也要自己指定
	 * @return 返回新增id。
	 * @throws InnException
	 */
	public String save(BillRoom entity)throws InnException;
	
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
	public boolean modifyUpdate(BillRoom entity)throws InnException;
	
	/**
	 * @param id
	 * @return
	 * @throws InnException
	 */
	public BillRoom getById(String id)throws InnException;
	
	
	public JuiPaginationSupport<BillRoom> juiPageFind(JuiSortingCombineFilter jscf,String enteredId) throws InnException;
	
	
	
}
