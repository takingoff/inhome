 /**   
* projectName: InnDomain
*
* fileName: OrderService.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午2:56:35 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.order;

import java.util.List;

import tang.li.inn.entity.order.Order;
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
public interface OrderService
{

	/**
	 * @param entity 每个字段都要指定，除了genTIme由数据库生成。
	 * @return 返回新增id。
	 * @throws InnException
	 */
	public String save(Order entity)throws InnException;
	
	/**
	 * @param id
	 * @return 是否删除成功
	 * @throws InnException
	 */
	public boolean delete(String id)throws InnException;
	
	/**
	 * @param entity ...更新字段包括 ，入住天数、入住日期、房间类型、姓名、电话号码、延迟小时数、说明信息、状态。
	 * @return 是否修改成功
	 * @throws InnException
	 */
	public boolean executeModify(Order entity)throws InnException;
	
	/**
	 * @param id
	 * @return
	 * @throws InnException
	 */
	public Order getById(String id)throws InnException;
	
	/**
	 * @return
	 * @throws InnException
	 */
	public List<Order> getAll()throws InnException;
	
	
	public JuiPaginationSupport<Order> juiPageFind(JuiSortingCombineFilter jscf) throws InnException;
	
	
}
