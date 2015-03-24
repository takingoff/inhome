/**
 * projectName: InnDomain
 * 
 * fileName: StaffLogServiceImpl.java
 * 
 * author : tangli <tanglidehaizi@gamil.com>
 * 
 * createTime :2014 2014-4-11 下午10:09:25
 * 
 * version : V1.0
 * 
 */
package tang.li.inn.domain.service.staff.impl;

import tang.li.inn.domain.service.staff.StaffLogService;
import tang.li.inn.entity.staff.StaffLog;
import tang.li.inn.infrastructure.dao.staff.StaffLogDao;
import tang.li.inn.infrastructure.exception.InnErrorsUtil;
import tang.li.inn.infrastructure.exception.InnException;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see StaffLogService
 *@since
*/
public class StaffLogServiceImpl implements StaffLogService
{

	private StaffLogDao staffLogDao;
	
	public StaffLogDao getStaffLogDao()
	{
		return staffLogDao;
	}

	public void setStaffLogDao(StaffLogDao staffLogDao)
	{
		this.staffLogDao = staffLogDao;
	}

	@Override
	public void saveStaffLog(StaffLog staffLog) throws InnException
	{
		try
		{
			staffLogDao.save(staffLog);
		}
		catch(Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.common"),e);
		}
	}

	@Override
	public StaffLog getStaffLog(String id) throws InnException
	{
		try
		{
			return staffLogDao.get(id);
		}
		catch(Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.common"),e);
		}
	}
	
	@Override
	public boolean updateStaffLog(StaffLog entity) throws InnException
	{
		try
		{
			return staffLogDao.update(entity);
		}catch(InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.common"),e);
		}
	}


}
