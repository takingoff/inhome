/**
 * projectName: InnEntity
 * 
 * fileName: StaffLog.java
 * 
 * author : tangli <tanglidehaizi@gamil.com>
 * 
 * createTime :2014 2014-4-11 下午10:03:34
 * 
 * version : V1.0
 * 
 */
package tang.li.inn.entity.staff;

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
@Table(name="T_STAFF_LOG")
public class StaffLog extends BaseEntity
{
	private static final long serialVersionUID = -1286224758178247422L;
	
	private Staff staff;
	
	private String genTime;
	
	private String description;
	
	private String operateData;


	//@PrimaryKeyJoinColumn
	//PrimaryKeyJoinColumn 指示staff的主键将会被作为外键。
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="staff_Id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Staff getStaff()
	{
		return staff;
	}

	public void setStaff(Staff staffId)
	{
		this.staff = staffId;
	}

	@Column(name="gen_Time")
	public String getGenTime()
	{
		return genTime;
	}

	public void setGenTime(String genTime)
	{
		this.genTime = genTime;
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

	@Column(name="operate_Data")
	public String getOperateData()
	{
		return operateData;
	}

	public void setOperateData(String operateData)
	{
		this.operateData = operateData;
	}


	
	
}
