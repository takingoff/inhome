/**
 * projectName: InnEntity
 * 
 * fileName: Staff.java
 * 
 * author : tangli <tanglidehaizi@gamil.com>
 * 
 * createTime :2014 2014-4-8 下午10:56:04
 * 
 * version : V1.0
 * 
 */
package tang.li.inn.entity.staff;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import tang.li.inn.entity.BaseEntity;
//@MappedSuperclass
/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
*/
@Entity
@Table(name = "T_STAFF")
public class Staff extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String genTime;
	private String name;
	private String password;
	private int level;
	
	
	@Column(name="gen_Time")
	public String getGenTime()
	{
		return genTime;
	}
	public void setGenTime(String genTime)
	{
		this.genTime = genTime;
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
	@Column(name="password")
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	@Column(name="level")
	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	
}
