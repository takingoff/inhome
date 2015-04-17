package tang.li.inn.domain.service.room.impl;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.domain.service.room.RoomTypeService;
import tang.li.inn.entity.room.Room;
import tang.li.inn.entity.room.RoomType;
import tang.li.inn.infrastructure.dao.room.RoomDao;
import tang.li.inn.infrastructure.dao.room.RoomTypeDao;
import tang.li.inn.infrastructure.exception.InnErrorsUtil;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiElementFilter;
import tang.li.inn.infrastructure.jui.JuiElementSorting;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.infrastructure.util.InnHQL;
import tang.li.inn.infrastructure.util.StringUtil;

public class RoomTypeServiceImpl implements RoomTypeService
{

	private RoomTypeDao roomTypeDao;
	
	private RoomDao roomDao;
	
	public RoomDao getRoomDao()
	{
		return roomDao;
	}

	public void setRoomDao(RoomDao roomDao)
	{
		this.roomDao = roomDao;
	}

	public RoomTypeDao getRoomTypeDao()
	{
		return roomTypeDao;
	}

	public void setRoomTypeDao(RoomTypeDao roomTypeDao)
	{
		this.roomTypeDao = roomTypeDao;
	}
	
	@Override
	public String save(RoomType entity) throws InnException
	{
		try
		{
			return (String) roomTypeDao.save(entity);
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
			List<Room> rooms =  roomDao.findByProperty(InnConstant.T_ROOM_ROOMTYPEID,id);
			if( rooms != null && rooms.size() == 0)
			{
				return roomTypeDao.delete(id);
			}
			else
			{
				return false;
			}
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.delete"),e);
		}
	}

	@Override
	public boolean modifyMerge(RoomType entity) throws InnException
	{
		try
		{
			return roomTypeDao.merge(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}

	@Override
	public RoomType getById(String id) throws InnException
	{
		try
		{
			return roomTypeDao.get(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	@Override
	public JuiPaginationSupport<RoomType> juiPageFind(JuiSortingCombineFilter jscf) throws InnException
	{
		try
		{
			List<Object> values = new ArrayList<Object>();
			String filtersHql = JuiElementFilter.juiElementFiltersToHQL(jscf.getFilters(),values,RoomType.class);
			String hql ;
			if(StringUtil.checkNotEmpty(filtersHql))
			{
				
				hql =  InnHQL.PAGINATION_ROOMTYPE + "where " + filtersHql  + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
			}
			else
			{
				hql =  InnHQL.PAGINATION_ROOMTYPE + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				
			}
			
			return roomTypeDao.juiPageFind(jscf.getPageNum(),jscf.getRowsPerPage(),hql,values);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}
	
	@Override
	public boolean judgeExistByName(String value) throws InnException
	{
		try
		{
			 if(roomTypeDao.findUniqueByProperty(InnConstant.T_ROOMTYPE_NAME,value) == null)
			 {
				 return false;
			 }
		}
		catch (InnException e)
		{
			return true;
		}
		return true;
	}

	@Override
	public List<RoomType> getByName(String value) throws InnException
	{
		try
		{
			return roomTypeDao.findByProperty(InnConstant.T_ROOMTYPE_NAME,value);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	
		

}
