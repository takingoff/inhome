 /**   
* projectName: InnDomain
*
* fileName: RoomServiceImpl.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午4:05:24 
*
* version : V1.0 
*/
package tang.li.inn.domain.service.room.impl;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.domain.service.room.RoomService;
import tang.li.inn.entity.room.Room;
import tang.li.inn.infrastructure.dao.room.RoomDao;
import tang.li.inn.infrastructure.exception.InnErrorsUtil;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiElementFilter;
import tang.li.inn.infrastructure.jui.JuiElementSorting;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.infrastructure.util.InnHQL;
import tang.li.inn.infrastructure.util.StringUtil;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class RoomServiceImpl implements RoomService
{

	private RoomDao roomDao;
	
	public RoomDao getRoomDao()
	{
		return roomDao;
	}
	
	public void setRoomDao(RoomDao roomDao)
	{
		this.roomDao = roomDao;
	}


	@Override
	public String save(Room entity)throws InnException
	{
		try
		{
			return (String) roomDao.save(entity);
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
			Room room = roomDao.get(id);
			if(room==null||StringUtil.checkNotEmpty(room.getEnteredId()))
			{
				return false;
			}
			return roomDao.delete(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.delete"),e);
		}
	}

	@Override
	public boolean modifyUpdate(Room entity) throws InnException
	{
		try
		{
			return roomDao.update(entity);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.modify"),e);
		}
	}
	
	
	@Override
	public Room getById(String id)throws InnException
	{
		try
		{
			return roomDao.get(id);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	@Override
	public List<Room> getAll() throws InnException
	{
		
		try
		{
			return roomDao.findAll();
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}
	
	
	@Override
	public JuiPaginationSupport<Room> juiPageFind(JuiSortingCombineFilter jscf) throws InnException
	{
		try
		{
			List<Object> values = new ArrayList<Object>();
			String filtersHql = JuiElementFilter.juiElementFiltersToHQL(jscf.getFilters(),values,Room.class);
			String hql ;
			if(StringUtil.checkNotEmpty(filtersHql))
			{
				
				hql =  InnHQL.PAGINATION_ROOM + "where " + filtersHql  + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
			}
			else
			{
				hql =  InnHQL.PAGINATION_ROOM + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				
			}
			
			return roomDao.juiPageFind(jscf.getPageNum(),jscf.getRowsPerPage(),hql,values);
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
			 if(roomDao.findUniqueByProperty(InnConstant.T_ROOM_NAME,value) == null)
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
	public List<Room> getByRoomType(String roomTypeId) throws InnException
	{
		try
		{
			return roomDao.findByProperty(InnConstant.T_ROOM_ROOMTYPEID,roomTypeId);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	@Override
	public List<Room> getByRoomName(String value) throws InnException
	{
		try
		{
			return roomDao.findByProperty(InnConstant.T_ROOM_NAME,value);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}

	@Override
	public JuiPaginationSupport<Room> switchRoomJuiPageFind(JuiSortingCombineFilter jscf,
			String roomTypeId) throws InnException
	{
		try
		{
			List<Object> values = new ArrayList<Object>();
			values.add(roomTypeId);
			String filtersHql = JuiElementFilter.juiElementFiltersToHQL(jscf.getFilters(),values,Room.class);
			String hql ;
			if(StringUtil.checkNotEmpty(filtersHql))
			{
				
				hql =  InnHQL.PAGINATION_ROOM_SWITCHROOM +"and " + filtersHql  + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
			}
			else
			{
				hql =  InnHQL.PAGINATION_ROOM_SWITCHROOM + JuiElementSorting.juiSortingsToHQL(jscf.getSortings());
				
			}
			
			return roomDao.juiPageFind(jscf.getPageNum(),jscf.getRowsPerPage(),hql,values);
		}
		catch (InnException e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.service.get"),e);
		}
	}


}
