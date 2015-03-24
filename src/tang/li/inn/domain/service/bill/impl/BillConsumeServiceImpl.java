/**   
 * projectName: InnDomain
 *
 * fileName: BillConsumeServiceImpl.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-5-9 下午7:49:59 
 *
 * version : V1.0 
 */
package tang.li.inn.domain.service.bill.impl;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.domain.service.bill.BillConsumeService;
import tang.li.inn.entity.bill.BillConsume;
import tang.li.inn.entity.entered.EnteredInfo;
import tang.li.inn.infrastructure.dao.bill.BillConsumeDao;
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
 * <description>
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
public class BillConsumeServiceImpl implements BillConsumeService
{

	private BillConsumeDao billConsumeDao;

	private EnteredInfoDao enteredInfoDao;

	public EnteredInfoDao getEnteredInfoDao()
	{
		return enteredInfoDao;
	}

	public void setEnteredInfoDao(EnteredInfoDao enteredInfoDao)
	{
		this.enteredInfoDao = enteredInfoDao;
	}

	public BillConsumeDao getBillConsumeDao()
	{
		return billConsumeDao;
	}

	public void setBillConsumeDao(BillConsumeDao BillConsumeDao)
	{
		this.billConsumeDao = BillConsumeDao;
	}

	@Override
	public String save(BillConsume entity) throws InnException
	{
		try
		{
			if (entity.getEnteredInfo() == null || !StringUtil.checkNotEmpty(entity.getEnteredInfo().getId()))
			{
				return null;
			}
			else
			{
				EnteredInfo ei = enteredInfoDao.get(entity.getEnteredInfo().getId());
				if (ei == null || ei.getRoom() == null || !StringUtil.checkNotEmpty(ei.getRoom().getId()))
				{
					return null;
				}
				else
				{
					entity.setRoom(ei.getRoom());
				}
			}
			return (String) billConsumeDao.save(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.save"), e);
		}
	}

	@Override
	public boolean delete(String id) throws InnException
	{
		try
		{
			return billConsumeDao.delete(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.delete"), e);
		}
	}

	@Override
	public boolean modifyDescription(String id, String description) throws InnException
	{
		try
		{
			return billConsumeDao.executeUpdate(InnHQL.BILLCONSUME_MODIFY_DESCRIPTION, description, id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"), e);
		}
	}

	@Override
	public BillConsume getById(String id) throws InnException
	{
		try
		{
			return billConsumeDao.get(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"), e);
		}
	}

	@Override
	public JuiPaginationSupport<BillConsume> juiPageFind(JuiSortingCombineFilter jscf, String enteredId) throws InnException
	{

		try
		{
			List<Object> values = new ArrayList<Object>();
			if (StringUtil.checkNotEmpty(enteredId))
			{
				values.add(enteredId);
			}
			String filtersHql = JuiElementFilter.juiElementFiltersToHQL(jscf.getFilters(), values, BillConsume.class);
			String hql;

			// 如果指定了enteredId
			if (StringUtil.checkNotEmpty(enteredId))
			{
				if (StringUtil.checkNotEmpty(filtersHql))
				{

					hql = InnHQL.PAGINATION_BILLCONSUME_ENTERED_SPECIFIC + "and " + filtersHql + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				}
				else
				{
					hql = InnHQL.PAGINATION_BILLCONSUME_ENTERED_SPECIFIC + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				}
			}
			// 如果没有指定
			else
			{
				if (StringUtil.checkNotEmpty(filtersHql))
				{
					hql = InnHQL.PAGINATION_BILL_CONSUME + "where " + filtersHql + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				}
				else
				{
					hql = InnHQL.PAGINATION_BILL_CONSUME + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				}
			}
			return billConsumeDao.juiPageFind(jscf.getPageNum(), jscf.getRowsPerPage(), hql, values);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"), e);
		}
	}

}
