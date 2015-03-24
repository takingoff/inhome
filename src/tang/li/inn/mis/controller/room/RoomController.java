 /**   
* projectName: InnMIS
*
* fileName: RoomController.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午5:20:18 
*
* version : V1.0 
*/
package tang.li.inn.mis.controller.room;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tang.li.inn.domain.service.entered.EnteredInfoService;
import tang.li.inn.domain.service.room.RoomService;
import tang.li.inn.entity.entered.EnteredInfo;
import tang.li.inn.entity.room.Room;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.infrastructure.util.StringUtil;
import tang.li.inn.mis.vo.EnteredInfoVo;
import tang.li.inn.mis.vo.RoomVo;
import tang.li.inn.mis.vo.RoomVoCombineEnteredInfoVo;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Controller
@RequestMapping("/room")
public class RoomController
{
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private EnteredInfoService enteredInfoService;

	
	
	@RequestMapping("/roomTrendView")
	public String roomTrendView(ModelMap mv)
	{
		return "/room/roomTrend";
	}
	
	
	@RequestMapping("/roomTrend")
	@ResponseBody
	public List<RoomVoCombineEnteredInfoVo> roomTrend(ModelMap mv)
	{
		List<RoomVoCombineEnteredInfoVo> combines = new ArrayList<RoomVoCombineEnteredInfoVo>();
		try
		{
			List<Room> roomList = roomService.getAll();
			for(Room room : roomList)
			{
				if(StringUtil.checkNotEmpty(room.getEnteredId()))
				{
					EnteredInfo ei = enteredInfoService.getById(room.getEnteredId());
					//监测房费。
					enteredInfoService.genBillRoom(ei);
					combines.add(new RoomVoCombineEnteredInfoVo(new RoomVo(room),new EnteredInfoVo(ei) ));
				}
				else
				{
					combines.add(new RoomVoCombineEnteredInfoVo(new RoomVo(room),null));
				}
			}
			
		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return combines;
	}
	
	
//	@RequestMapping("/roomTrend")
//	public String roomTrend(ModelMap mv)
//	{
//		try
//		{
//			List<Room> roomList = roomService.getAll();
//			List<RoomVoCombineEnteredInfoVo> combines = new ArrayList<RoomVoCombineEnteredInfoVo>();
//			for(Room room : roomList)
//			{
//				if(StringUtil.checkNotEmpty(room.getEnteredId()))
//				{
//					combines.add(new RoomVoCombineEnteredInfoVo(new RoomVo(room),
//							new EnteredInfoVo(enteredInfoService.getById(room.getEnteredId())) ));
//				}
//				else
//				{
//					combines.add(new RoomVoCombineEnteredInfoVo(new RoomVo(room),null));
//				}
//			}
//			mv.put("combines",combines);
//			
//		}
//		catch (InnException e)
//		{
//			e.printStackTrace();
//		}
//		return "/room/roomTrend";
//	}
//	
	
	
	@RequestMapping("/roomPageView")
	public String roomPageView(ModelMap mv)
	{
		return "/room/paginationRoom";
	}
	
	
	@RequestMapping("/roomPage")
	@ResponseBody
	public JuiPaginationSupport<RoomVo> roomPage(@RequestBody JuiSortingCombineFilter jscf)
	{
		
		try
		{
			return RoomVo.paginationConvert(roomService.juiPageFind(jscf));
			
		}catch(InnException e)
		{
			e.printStackTrace();
			return new JuiPaginationSupport<RoomVo>(InnConstant.LIST_EXCEPTION,0,null);
		}
		
	}
	
	@RequestMapping("/roomMiniPage")
	@ResponseBody
	public JuiPaginationSupport<RoomVo> roomMiniPage(@RequestBody JuiSortingCombineFilter jscf,String roomTypeId)
	{
		
		try
		{
			return RoomVo.paginationConvert(roomService.switchRoomJuiPageFind(jscf,roomTypeId));
			
		}catch(InnException e)
		{
			e.printStackTrace();
			return new JuiPaginationSupport<RoomVo>(InnConstant.LIST_EXCEPTION,0,null);
		}
		
	}
	
	@RequestMapping("/roomAddValidateName")
	@ResponseBody
	public String roomAddValidateName(String name)
	{
		try
		{
			if(roomService.judgeExistByName(name))
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
	
	
	@RequestMapping("/roomAdd")
	@ResponseBody
	public String roomAdd(Room entity)
	{
		
		try
		{
			if(roomService.judgeExistByName(entity.getName()))
			{
				return InnConstant.ADD_FAILED_NAME_EXISTED;
			}
			
			entity.setState(InnConstant.T_ROOM_STATE_UNVALIABLE);
			roomService.save(entity);
			return InnConstant.ADD_SUCCESS;
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.ADD_FAILED;
	}
	
	
	@RequestMapping("/roomModifyValidateName")
	@ResponseBody
	public String roomModifyValidateName(String name,String id)
	{
		try
		{
			List<Room> list  = roomService.getByRoomName(name);
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
	
	
	@RequestMapping("/roomModify")
	@ResponseBody
	public String roomModify(Room entity)
	{
		try
		{
			roomService.modifyUpdate(entity);
			return  InnConstant.MODIFY_SUCCESS;
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return  InnConstant.MODIFY_FAILED;
	}
	
	
	
	
	@RequestMapping("/roomDelete")
	@ResponseBody
	public String roomDelete(@RequestBody List<String> ids)
	{
		try
		{
			for(String id:ids)
			{
				roomService.delete(id);
			}
			return InnConstant.DELETE_ROOM_SUCCESS;
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.DELETE_FAILED;
	}
	
	@RequestMapping("/roomGet")
	@ResponseBody
	public RoomVoCombineEnteredInfoVo roomGet(@RequestBody String id)
	{
		try
		{
			RoomVo rVo = new RoomVo(roomService.getById(id));
			EnteredInfoVo eVo = null;
			if(StringUtil.checkNotEmpty(rVo.getRoom().getEnteredId()))
			{
				eVo = new EnteredInfoVo(enteredInfoService.getById(rVo.getRoom().getEnteredId()));
			}
			return new RoomVoCombineEnteredInfoVo(rVo,eVo);
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
}
