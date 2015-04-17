 /**   
* projectName: InnDomain
*
* fileName: StaffLogService.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-11 下午10:07:18 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.staff;

import tang.li.inn.entity.staff.StaffLog;
import tang.li.inn.infrastructure.exception.InnException;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public interface StaffLogService
{
	public void saveStaffLog(StaffLog entity)throws InnException;
	public StaffLog getStaffLog(String id)throws InnException;
	public boolean updateStaffLog(StaffLog entity)throws InnException;
}
