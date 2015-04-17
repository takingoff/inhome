 /**   
* projectName: InnDomain
*
* fileName: EnteredInfoService.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午2:57:07 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.entered;

import tang.li.inn.entity.entered.EnteredInfo;
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
public interface EnteredInfoService
{
	/**
	 * @param entity
	 * @return 返回新增id。
	 * @throws InnException
	 */
	public String save(EnteredInfo entity)throws InnException;
	
	
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
	public boolean update(EnteredInfo entity)throws InnException;

	
	/**
	 *不仅获取实体，还要查询出 房费，消费，以及入账
	 * @param id
	 * @return
	 * @throws InnException
	 */
	public EnteredInfo getById(String id)throws InnException;
	
	
	public JuiPaginationSupport<EnteredInfo> juiPageFind(JuiSortingCombineFilter jscf) throws InnException;
	
	
	/**
	 * 入住操作 保存入住，设置房间状态为已入住，房间enteredId
	 * @param entity
	 * @return
	 */
	public boolean enterIn(EnteredInfo entity,int payWay ,double money,int enterDays)throws InnException; 
	
	/**
	 * @param entity 入住后的修改， 需要字段： id,name,phoneNumber,numberPeople,description
	 * @return
	 * @throws InnException
	 */
	public boolean enteredModify(EnteredInfo entity)throws InnException;
	
	/**
	 * 只修改一个字段。
	 * @param id
	 * @param description
	 * @return
	 * @throws InnException
	 */
	public boolean enteredModifyDescription(String id,String description)throws InnException;
	
	/**
	 * 换房操作
	 * @param newRoomId
	 * @param oldRoomId
	 * @param enterInfoId
	 * @return
	 * @throws InnException
	 */
	public boolean enteredSwitchRoom(String newRoomId,String oldRoomId,String enterInfoId)throws InnException;
	
	
	/**
	 * money为结算结果，允许和数据库中计算的结果有1的误差（因为入账有不精确的小数，但情况很少。）。在此范围内可以退房。
	 * @param id
	 * @param payWay
	 * @param money 结算结果的负数（比如结算完成后还欠费12，那么money应该是12，如果剩余12 money因为-12）
	 * @return
	 * @throws InnException
	 */
	public boolean enteredCheckOut(String id,int payWay,double money)throws InnException;
	
	/**
	 * 续住操作。修改outTime字段
	 * @param enterInfoId
	 * @param days
	 * @return
	 * @throws InnException
	 */
	public boolean enteredContinue(String enterInfoId,int days)throws InnException;
	
	
	public void genBillRoom(EnteredInfo ei) throws InnException;
	
	
}
