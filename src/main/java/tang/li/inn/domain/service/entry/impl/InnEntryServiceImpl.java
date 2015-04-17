 /**   
* projectName: InnDomain
*
* fileName: InnEntryServiceImpl.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-24 上午7:55:29 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.entry.impl;

import java.util.List;

import tang.li.inn.domain.service.entry.InnEntryService;
import tang.li.inn.entity.entry.InnEntry;
import tang.li.inn.infrastructure.dao.entry.InnEntryDao;
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
public class InnEntryServiceImpl implements InnEntryService
{
	private InnEntryDao innEntryDao;
	
	public InnEntryDao getInnEntryDao()
	{
		return innEntryDao;
	}
	
	public void setInnEntryDao(InnEntryDao innEntryDao)
	{
		this.innEntryDao = innEntryDao;
	}


	@Override
	public String save(InnEntry entity)throws InnException
	{
		try
		{
			return (String) innEntryDao.save(entity);
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
			return innEntryDao.delete(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.delete"),e);
		}
	}

	@Override
	public boolean modify(InnEntry entity) throws InnException
	{
		try
		{
			return innEntryDao.update(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}
	
	
	@Override
	public InnEntry getById(String id)throws InnException
	{
		try
		{
			return innEntryDao.get(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	@Override
	public List<InnEntry> getAll() throws InnException
	{
		
		try
		{
			return innEntryDao.findAll();
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	@Override
	public InnEntry findUniqueByKey(String key) throws InnException
	{
		try
		{
			return innEntryDao.findUniqueByProperty(InnConstant.T_INNCONTAINER_KEY,key);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

}
