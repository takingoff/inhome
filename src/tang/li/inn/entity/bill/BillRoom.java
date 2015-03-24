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
@Table(name = "T_BILL_ROOM")
public class BillRoom extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String genDate;
	private EnteredInfo enteredInfo;
	private Room room;
	private double bill;
	private int roomExpenseType;
	@Column(name="gen_Date")
	public String getGenDate()
	{
		return genDate;
	}
	public void setGenDate(String gen_Date)
	{
		this.genDate = gen_Date;
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
	@Column(name="room_Expense_Type")
	public int getRoomExpenseType()
	{
		return roomExpenseType;
	}
	public void setRoomExpenseType(int roomExpenseType)
	{
		this.roomExpenseType = roomExpenseType;
	}
	
	
}
