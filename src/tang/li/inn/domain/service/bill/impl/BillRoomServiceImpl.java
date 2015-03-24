 /**   
* projectName: InnDomain
*
* fileName: BillRoom.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-9 下午7:54:05 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.bill.impl;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.domain.service.bill.BillRoomService;
import tang.li.inn.entity.bill.BillRoom;
import tang.li.inn.infrastructure.dao.bill.BillRoomDao;
import tang.li.inn.infrastructure.exception.InnErrorsUtil;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiElementFilter;
import tang.li.inn.infrastructure.jui.JuiElementSorting;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnHQL;
import tang.li.inn.infrastructure.util.StringUtil;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class BillRoomServiceImpl implements BillRoomService
{

private BillRoomDao billRoomDao;
	

	public BillRoomDao getBillRoomDao()
	{
		return billRoomDao;
	}

	public void setBillRoomDao(BillRoomDao BillRoomDao)
	{
		this.billRoomDao = BillRoomDao;
	}
	
	@Override
	public String save(BillRoom entity) throws InnException
	{
		try
		{
			return (String) billRoomDao.save(entity);
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
			return billRoomDao.delete(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.delete"),e);
		}
	}

	@Override
	public boolean modifyUpdate(BillRoom entity) throws InnException
	{
		try
		{
			return billRoomDao.update(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}

	@Override
	public BillRoom getById(String id) throws InnException
	{
		try
		{
			return billRoomDao.get(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	@Override
	public JuiPaginationSupport<BillRoom> juiPageFind(JuiSortingCombineFilter jscf,String enteredId) throws InnException
	{
		try
		{
			List<Object> values = new ArrayList<Object>();
			if(StringUtil.checkNotEmpty(enteredId))
			{
				values.add(enteredId);
			}
			String filtersHql = JuiElementFilter.juiElementFiltersToHQL(jscf.getFilters(),values,BillRoom.class);
			String hql ;
			
			//如果指定了enteredId
			if(StringUtil.checkNotEmpty(enteredId))
			{
				if(StringUtil.checkNotEmpty(filtersHql))
				{
					
					hql =  InnHQL.PAGINATION_BILLROOOM_ENTERED_SPECIFIC+ "and " + filtersHql  + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				}
				else
				{
					hql =  InnHQL.PAGINATION_BILLROOOM_ENTERED_SPECIFIC + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
					
				}
			}
			//如果没有指定
			else
			{
				if(StringUtil.checkNotEmpty(filtersHql))
				{
					
					hql =  InnHQL.PAGINATION_BILL_ROOM+ "where " + filtersHql  + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				}
				else
				{
					hql =  InnHQL.PAGINATION_BILL_ROOM + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
					
				}
			}
			
			
			return billRoomDao.juiPageFind(jscf.getPageNum(),jscf.getRowsPerPage(),hql,values);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
		
	}

}
