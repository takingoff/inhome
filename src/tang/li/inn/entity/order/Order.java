 /**   
* projectName: InnEntity
*
* fileName: Order.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午1:49:13 
*
* version : V1.0 
*/
package tang.li.inn.entity.order;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import tang.li.inn.entity.BaseEntity;
import tang.li.inn.entity.entered.EnteredInfo;
import tang.li.inn.entity.room.RoomType;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Entity
@Table(name = "T_ORDER")
public class Order extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String genTime;
	private EnteredInfo enteredInfo;
	private RoomType roomType;
	private String willEnterDate;
	private int willEnterDays;
	private String name;
	private String phoneNumber;
	private String description;
	private int extendHours;
	private int state;
	
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
	@JoinColumn(name="entered_Id")
	@NotFound(action=NotFoundAction.IGNORE)
	public EnteredInfo getEnteredInfo()
	{
		return enteredInfo;
	}
	public void setEnteredInfo(EnteredInfo enteredInfo)
	{
		this.enteredInfo = enteredInfo;
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
	@Column(name="will_Enter_Date")
	public String getWillEnterDate()
	{
		return willEnterDate;
	}
	public void setWillEnterDate(String willEnterDate)
	{
		this.willEnterDate = willEnterDate;
	}
	@Column(name="will_Enter_Days")
	public int getWillEnterDays()
	{
		return willEnterDays;
	}
	public void setWillEnterDays(int willEnterDays)
	{
		this.willEnterDays = willEnterDays;
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
	@Column(name="phone_Number")
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
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
	@Column(name="extend_Hours")
	public int getExtendHours()
	{
		return extendHours;
	}
	public void setExtendHours(int extendHours)
	{
		this.extendHours = extendHours;
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
	
}
