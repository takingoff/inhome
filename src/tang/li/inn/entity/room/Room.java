 /**   
* projectName: InnEntity
*
* fileName: Room.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午12:09:28 
*
* version : V1.0 
*/
package tang.li.inn.entity.room;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import tang.li.inn.entity.BaseEntity;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Entity
@Table(name = "T_ROOM")
public class Room extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String genTime;
	private RoomType roomType;
	private String name;
	private String description;
	private int state;
	private String enteredId;
	
	
	@Column(name="gen_Time")
	public String getGenTime()
	{
		return genTime;
	}
	public void setGenTime(String genTime)
	{
		this.genTime = genTime;
	}
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="room_Type")
	@NotFound(action=NotFoundAction.IGNORE)
	public RoomType getRoomType()
	{
		return roomType;
	}
	public void setRoomType(RoomType roomType)
	{
		this.roomType = roomType;
	}
	@Column(name="description")
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	@Column(name="state")
	public int getState()
	{
		return state;
	}
	public void setState(int state)
	{
		this.state = state;
	}
	@Column(name="entered_Id")
	public String getEnteredId()
	{
		return enteredId;
	}
	public void setEnteredId(String enteredId)
	{
		this.enteredId = enteredId;
	}
	@Column(name="name")
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	
}