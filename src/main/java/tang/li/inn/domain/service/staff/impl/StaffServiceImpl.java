/**
 * projectName: InnDomain
 * 
 * fileName: StaffServiceImpl.java
 * 
 * author : tangli <tanglidehaizi@gamil.com>
 * 
 * createTime :2014 2014-4-8 下午10:55:43
 * 
 * version : V1.0
 * 
 */
package tang.li.inn.domain.service.staff.impl;

import java.util.List;

import tang.li.inn.domain.service.staff.StaffService;
import tang.li.inn.entity.staff.Staff;
import tang.li.inn.infrastructure.dao.staff.StaffDao;
import tang.li.inn.infrastructure.exception.InnErrorsUtil;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.util.InnConstant;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
*/
public class StaffServiceImpl implements StaffService
{

	private StaffDao staffDao;
	
	public StaffDao getStaffDao()
	{
		return staffDao;
	}

	public void setStaffDao(StaffDao staffDao)
	{
		this.staffDao = staffDao;
	}

	@Override
	public String save(Staff entity)throws InnException
	{
		try
		{
			return (String) staffDao.save(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.save"),e);
		}
	}


	@Override
	public boolean delete(String id) throws InnException
	{
		try
		{
			return staffDao.delete(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.delete"),e);
		}
	}

	@Override
	public boolean modify(Staff entity) throws InnException
	{
		try
		{
			return staffDao.update(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}
	
	
	@Override
	public Staff getById(String id)throws InnException
	{
		try
		{
			return staffDao.get(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}
	
	
	@Override
	public List<Staff> getAll() throws InnException
	{
		try
		{
			return staffDao.findAll();
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.common"),e);
		}
	}

	

	@Override
	public Staff findUniqueByName(String name) throws InnException
	{
		try
		{
			return staffDao.findUniqueByProperty(InnConstant.T_STAFF_NAME,name);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.common"),e);
		}
	}

	@Override
	public Staff findStaffByName(String name) throws InnException
	{
		try
		{
			List<Staff> list=  (List<Staff>) staffDao.findByProperty(InnConstant.T_STAFF_NAME,name);
			if(list.size()==0)
			{
				return null;
			}
			else
			{
				return list.get(0);
			}
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.common"),e);
		}
	}

	@Override
	public void testTrasaction() throws InnException
	{
		try
		{
		
			Staff staff = new Staff();
			staff.setName("name");
			staff.setLevel(1);
			staff.setPassword("pas");
			staffDao.save(staff);
//			throw new InnException(null,null);
			
			try
			{
				Thread.sleep(10000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			Staff staff2 = new Staff();
			staff2.setLevel(1);
			staff2.setPassword("pas");
			staff2.setName("new Name");
			staffDao.save(staff2);
			
			
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.save"),e);
		}	
	}

	
	

}
