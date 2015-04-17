 /**   
* projectName: InnEntity
*
* fileName: RoomVo.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-1 下午5:44:54 
*
* version : V1.0 
*/
package tang.li.inn.mis.vo;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.entity.room.Room;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.util.InnConstant;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class RoomVo
{
	private Room room;
	private String id;
	private String stateName;
	
	public static JuiPaginationSupport<RoomVo> paginationConvert(JuiPaginationSupport<Room> jps)
	{
		List<Room> lr =  jps.getPageData();
		List<RoomVo> lrv = new ArrayList<RoomVo>();
		for(Room r:lr)
		{
			lrv.add(new RoomVo(r));
		}
		return new JuiPaginationSupport<RoomVo>(jps.getError(),jps.getTotalRows(),lrv);
		
	}
	
	public RoomVo(Room room)
	{
		if(room == null)
		{
			return;
		}
		this.room = room;
		this.id = room.getId();
//		状态（1干净、2在住、3脏房、4不可用房）
		if(room.getState() == InnConstant.T_ROOM_STATE_CLEAN)
		{
			this.stateName = InnConstant.ROOMVO_STATE_CLEAN;
		}
		else if(room.getState() == InnConstant.T_ROOM_STATE_DIRTY)
		{
			this.stateName = InnConstant.ROOMVO_STATE_DIRTY;
			
		}
		else if(room.getState() == InnConstant.T_ROOM_STATE_ENTERED)
		{
			this.stateName = InnConstant.ROOMVO_STATE_ENTERED;
			
		}
		//不可用
		else
		{
			this.stateName = InnConstant.ROOMVO_STATE_UNVALIABLE;
		}
		
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Room getRoom()
	{
		return room;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

}
