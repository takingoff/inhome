 /**   
* projectName: InnEntity
*
* fileName: EnteredInfo.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午1:17:36 
*
* version : V1.0 
*/
package tang.li.inn.entity.entered;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import tang.li.inn.entity.BaseEntity;
import tang.li.inn.entity.room.Room;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Entity
@Table(name = "T_ENTERED_INFO")
public class EnteredInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String enteredTime;
	private Room room;
	private String name;
	private String phoneNumber;
	private int numberPeople;
	private String description;
	private boolean isHourRoom;
	private boolean isCheckOut;
	private String outTime;
	
	/////////以下字段若有必要在service中设置。
	//所有入账
	private double billPay;
	//所有房费
	private double billRoom;
	//所有消费
	private double billConsume;
	
	@Transient
	public double getBillPay()
	{
		return billPay;
	}
	public void setBillPay(double billPay)
	{
		this.billPay = billPay;
	}
	@Transient
	public double getBillRoom()
	{
		return billRoom;
	}
	public void setBillRoom(double billRoom)
	{
		this.billRoom = billRoom;
	}
	@Transient
	public double getBillConsume()
	{
		return billConsume;
	}
	public void setBillConsume(double billConsume)
	{
		this.billConsume = billConsume;
	}
	
	@Column(name="entered_Time")
	public String getEnteredTime()
	{
		return enteredTime;
	}
	public void setEnteredTime(String enteredTime)
	{
		this.enteredTime = enteredTime;
	}
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="room_Id" ,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	public Room getRoom()
	{
		return room;
	}
	public void setRoom(Room room)
	{
		this.room = room;
	}
	@Column(name="name",updatable=false)
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
	@Column(name="numberPeople")
	public int getNumberPeople()
	{
		return numberPeople;
	}
	public void setNumberPeople(int numberPeople)
	{
		this.numberPeople = numberPeople;
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
	@Column(name="out_Time")
	public String getOutTime()
	{
		return outTime;
	}
	public void setOutTime(String outTime)
	{
		this.outTime = outTime;
	}
	
	
	@Column(name="is_Hour_Room")
	public boolean getIsHourRoom()
	{
		return isHourRoom;
	}
	public void setIsHourRoom(boolean isHourRoom)
	{
		this.isHourRoom = isHourRoom;
	}
	@Column(name="is_Check_Out")
	public boolean getIsCheckOut()
	{
		return isCheckOut;
	}
	public void setIsCheckOut(boolean isCheckOut)
	{
		this.isCheckOut = isCheckOut;
	}
	
	
	
	
}
