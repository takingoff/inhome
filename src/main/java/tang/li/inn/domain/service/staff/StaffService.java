/**
 * projectName: InnDomain
 * 
 * fileName: StaffService.java
 * 
 * author : tangli <tanglidehaizi@gamil.com>
 * 
 * createTime :2014 2014-4-8 下午10:55:28
 * 
 * version : V1.0
 * 
 */
package tang.li.inn.domain.service.staff;

import java.util.List;

import tang.li.inn.entity.staff.Staff;
import tang.li.inn.infrastructure.exception.InnException;

/**
 *获取、添加、删除、查询所有。。分页查询
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
*/
public interface StaffService
{
	
	/**
	 * 用于新增账户
	 * @param entity
	 * @return 返回新增账户。
	 * @throws InnException
	 */
	public String save(Staff entity)throws InnException;
	
	
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
	public boolean modify(Staff entity)throws InnException;

	
	/**
	 * 获取账户，主要用于登录，查看用户级别等
	 * @param id
	 * @return
	 * @throws InnException
	 */
	public Staff getById(String id)throws InnException;
	
	
	/**
	 * @param name
	 * @return 如果获取成功返回该对象 如果有多余的将会导致异常
	 * @throws InnException
	 */
	public Staff findUniqueByName(String name)throws InnException;
	
	
	/**
	 * @param name
	 * @return 符合要求的Staff对象列表的第一个对象。（一般情况下只有一个对象。因为在添加用户时候限制了名字唯一）
	 * @throws InnException
	 */
	public Staff findStaffByName(String name)throws InnException;
	
	
	public List<Staff> getAll()throws InnException;
	
	
	
	public void testTrasaction()throws InnException;
}
