 /**   
* projectName: InnDomain
*
* fileName: BillPayServiceImpl.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-9 下午7:40:56 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.bill.impl;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.domain.service.bill.BillPayService;
import tang.li.inn.entity.bill.BillPay;
import tang.li.inn.entity.entered.EnteredInfo;
import tang.li.inn.infrastructure.dao.bill.BillPayDao;
import tang.li.inn.infrastructure.dao.entered.EnteredInfoDao;
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
public class BillPayServiceImpl implements BillPayService
{

	private BillPayDao billPayDao;
	
	private EnteredInfoDao enteredInfoDao;
	
	public EnteredInfoDao getEnteredInfoDao()
	{
		return enteredInfoDao;
	}

	public void setEnteredInfoDao(EnteredInfoDao enteredInfoDao)
	{
		this.enteredInfoDao = enteredInfoDao;
	}

	public BillPayDao getBillPayDao()
	{
		return billPayDao;
	}

	public void setBillPayDao(BillPayDao BillPayDao)
	{
		this.billPayDao = BillPayDao;
	}
	
	@Override
	public String save(BillPay entity) throws InnException
	{
		try
		{
			if(entity.getEnteredInfo()== null || !StringUtil.checkNotEmpty(entity.getEnteredInfo().getId()))
			{
				return null;
			}
			else
			{
				EnteredInfo ei = enteredInfoDao.get(entity.getEnteredInfo().getId());
				if(ei == null || ei.getRoom() == null || !StringUtil.checkNotEmpty(ei.getRoom().getId()))
				{
					return null;
				}
				else
				{
					entity.setRoom(ei.getRoom());
				}
			}
			
			return (String) billPayDao.save(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.save"),e);
		}
	}

	@Override
	public boolean delete(List<String> ids) throws InnException
	{
		try
		{
			for (String id : ids)
			{
				billPayDao.delete(id);
			}
			return true;
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.delete"),e);
		}
	}

	@Override
	public boolean modifyDescription(String id,String description) throws InnException
	{
		try
		{
			return billPayDao.executeUpdate(InnHQL.BILLPAY_MODIFY_DESCRIPTION,description,id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}

	@Override
	public BillPay getById(String id) throws InnException
	{
		try
		{
			return billPayDao.get(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	@Override
	public JuiPaginationSupport<BillPay> juiPageFind(JuiSortingCombineFilter jscf,String enteredId) throws InnException
	{
		try
		{
			List<Object> values = new ArrayList<Object>();
			if(StringUtil.checkNotEmpty(enteredId))
			{
				values.add(enteredId);
			}
			String filtersHql = JuiElementFilter.juiElementFiltersToHQL(jscf.getFilters(),values,BillPay.class);
			String hql ;
			
			//如果指定了enteredId
			if(StringUtil.checkNotEmpty(enteredId))
			{
				if(StringUtil.checkNotEmpty(filtersHql))
				{
					
					hql =  InnHQL.PAGINATION_BILLPAY_ENTERED_SPECIFIC+ "and " + filtersHql  + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				}
				else
				{
					hql =  InnHQL.PAGINATION_BILLPAY_ENTERED_SPECIFIC + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
					
				}
			}
			//如果没有指定
			else
			{
				if(StringUtil.checkNotEmpty(filtersHql))
				{
					
					hql =  InnHQL.PAGINATION_BILL_PAY+ "where " + filtersHql  + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				}
				else
				{
					hql =  InnHQL.PAGINATION_BILL_PAY + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
					
				}
			}
			
			
			return billPayDao.juiPageFind(jscf.getPageNum(),jscf.getRowsPerPage(),hql,values);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

		

}
