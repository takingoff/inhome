 /**   
* projectName: InnEntity
*
* fileName: RoomType.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-23 下午12:50:18 
*
* version : V1.0 
*/
package tang.li.inn.entity.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import tang.li.inn.entity.BaseEntity;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Entity
@Table(name = "T_ROOM_TYPE")
public class RoomType extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String genTime;
	private String name;
	private double dayPrice;
	private double hourPrice;
	private String description;
	@Column(name="gen_Time")
	public String getGenTime()
	{
		return genTime;
	}
	public void setGenTime(String genTime)
	{
		this.genTime = genTime;
	}
	@Column(name="day_Price")
	public double getDayPrice()
	{
		return dayPrice;
	}
	public void setDayPrice(double dayPrice)
	{
		this.dayPrice = dayPrice;
	}
	@Column(name="hour_Price")
	public double getHourPrice()
	{
		return hourPrice;
	}
	public void setHourPrice(double hourPrice)
	{
		this.hourPrice = hourPrice;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	@Column(name="description")
	public String getDescription()
	{
		return description;
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