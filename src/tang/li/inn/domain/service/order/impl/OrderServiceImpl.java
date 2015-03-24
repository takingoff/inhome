package tang.li.inn.domain.service.order.impl;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.domain.service.order.OrderService;
import tang.li.inn.entity.order.Order;
import tang.li.inn.infrastructure.dao.order.OrderDao;
import tang.li.inn.infrastructure.exception.InnErrorsUtil;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiElementFilter;
import tang.li.inn.infrastructure.jui.JuiElementSorting;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnHQL;
import tang.li.inn.infrastructure.util.StringUtil;

public class OrderServiceImpl implements OrderService
{

	private OrderDao orderDao;
	
	public OrderDao getOrderDao()
	{
		return orderDao;
	}
	
	public void setOrderDao(OrderDao orderDao)
	{
		this.orderDao = orderDao;
	}


	@Override
	public String save(Order entity)throws InnException
	{
		try
		{
			return (String) orderDao.save(entity);
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
			return orderDao.delete(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.delete"),e);
		}
	}

	@Override
	public boolean executeModify(Order entity) throws InnException
	{
		try
		{
			return orderDao.update(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}
	
	
	@Override
	public Order getById(String id)throws InnException
	{
		try
		{
			return orderDao.get(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	@Override
	public List<Order> getAll() throws InnException
	{
		
		try
		{
			return orderDao.findAll();
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}
	
	@Override
	public JuiPaginationSupport<Order> juiPageFind(JuiSortingCombineFilter jscf) throws InnException
	{
		try
		{
			List<Object> values = new ArrayList<Object>();
			String filtersHql = JuiElementFilter.juiElementFiltersToHQL(jscf.getFilters(),values,Order.class);
			String hql ;
			if(StringUtil.checkNotEmpty(filtersHql))
			{
				
				hql =  InnHQL.PAGINATION_ORDER + "where " + filtersHql  + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
			}
			else
			{
				hql =  InnHQL.PAGINATION_ORDER + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				
			}
			
			return orderDao.juiPageFind(jscf.getPageNum(),jscf.getRowsPerPage(),hql,values);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	
	
	
}

