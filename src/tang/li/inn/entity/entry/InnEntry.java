 /**   
* projectName: InnEntity
*
* fileName: InnContainer.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-24 上午7:44:46 
*
* version : V1.0 
*/
package tang.li.inn.entity.entry;

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
@Table(name = "T_INN_CONTAINER")
public class InnEntry extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;
	@Column(name="key")
	public String getKey()
	{
		return key;
	}
	public void setKey(String key)
	{
		this.key = key;
	}
	@Column(name="value")
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	
	
	
}
