/**   
 * projectName: InnMIS
 *
 * fileName: RoomTypeController.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-4-24 下午6:36:34 
 *
 * version : V1.0 
 */
package tang.li.inn.mis.controller.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tang.li.inn.domain.service.room.RoomService;
import tang.li.inn.domain.service.room.RoomTypeService;
import tang.li.inn.entity.room.RoomType;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnConstant;

/**
 * <description>
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
@Controller
@RequestMapping("/roomType")
public class RoomTypeController
{

	@Autowired
	RoomTypeService roomTypeService;
	
	@Autowired
	RoomService roomService;

	@RequestMapping("/roomType")
	public String paginationRoomTypeView()
	{
		return "roomType/paginationRoomType";
	}

	@RequestMapping("/roomTypePage")
	@ResponseBody
	public JuiPaginationSupport<RoomType> roomTypePage(@RequestBody JuiSortingCombineFilter jscf)
	{

		try
		{
			return roomTypeService.juiPageFind(jscf);

		}
		catch (InnException e)
		{
			e.printStackTrace();
			return new JuiPaginationSupport<RoomType>(InnConstant.LIST_EXCEPTION, 0, null);
		}

	}

	
	@RequestMapping("/roomTypeAddValidateName")
	@ResponseBody
	public String roomTypeAddValidateName(String name)
	{
		try
		{
			if(roomTypeService.judgeExistByName(name))
			{
				return InnConstant.NAME_VALIDATE_ERROR;
			}
			return  InnConstant.NAME_VALIDATE_GOOD;
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.NAME_VALIDATE_EXCEPTION;
	}
	
	
	@RequestMapping("/roomTypeAdd")
	@ResponseBody
	public String roomTypeAdd(RoomType entity)
	{
		try
		{
			if (roomTypeService.judgeExistByName(entity.getName()))
			{
				return InnConstant.ADD_FAILED_NAME_EXISTED;
			}
			roomTypeService.save(entity);
			return InnConstant.ADD_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.ADD_FAILED;
	}

	
	@RequestMapping("/roomTypeModifyValidateName")
	@ResponseBody
	public String roomTypeModifyValidateName(String name,String id)
	{
		try
		{
			List<RoomType> list  = roomTypeService.getByName(name);
			if((list.size() >1) || (list.size()==1  && !list.get(0).getId().equals(id)))
			{
				return InnConstant.NAME_VALIDATE_ERROR;
			}
			return  InnConstant.NAME_VALIDATE_GOOD;
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.NAME_VALIDATE_EXCEPTION;
	}
	
	
	@RequestMapping("/roomTypeModify")
	@ResponseBody
	public String roomTypeModify(RoomType entity)
	{
		try
		{

			List<RoomType> list  = roomTypeService.getByName(entity.getName());
			if((list.size() >1) || (list.size()==1  && !list.get(0).getId().equals(entity.getId())))
			{
				return InnConstant.MODIFY_FAILED_NAME_EXISTED;
			}
			roomTypeService.modifyMerge(entity);
			return InnConstant.MODIFY_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.MODIFY_FAILED;
	}

	@RequestMapping("/roomTypeDelete")
	@ResponseBody
	public String roomTypeDelete(@RequestBody List<String> ids)
	{
		try
		{
			for (String id : ids)
			{
				roomTypeService.delete(id);
			}
			return InnConstant.DELETE_ROOMTYPE_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.DELETE_FAILED;
	}

	@RequestMapping("/roomTypeGet")
	@ResponseBody
	public RoomType roomTypeGet(@RequestBody String id)
	{
		try
		{
			return roomTypeService.getById(id);

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
