 /**   
* projectName: InnEntity
*
* fileName: BillRoom.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午2:01:44 
*
* version : V1.0 
*/
package tang.li.inn.entity.bill;

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
import tang.li.inn.entity.room.Room;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Entity
@Table(name = "T_BILL_PAY")
public class BillPay extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String genTime;
	private EnteredInfo enteredInfo;
	private Room room;
	private double bill;
	private String description;
	private int payWay;
	@Column(name="gen_Time")
	public String getGenTime()
	{
		return genTime;
	}
	public void setGenTime(String gen_Date)
	{
		this.genTime = gen_Date;
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
	@JoinColumn(name="room_Id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Room getRoom()
	{
		return room;
	}
	public void setRoom(Room room)
	{
		this.room = room;
	}
	@Column(name="bill")
	public double getBill()
	{
		return bill;
	}
	public void setBill(double bill)
	{
		this.bill = bill;
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
	@Column(name="pay_Way")
	public int getPayWay()
	{
		return payWay;
	}
	public void setPayWay(int payWay)
	{
		this.payWay = payWay;
	}
	
	
}
