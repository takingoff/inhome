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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Entity
@Table(name = "T_INN_CONTAINER")
public class InnEntry implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String innKey;
	private String value;
	
	@Column(name="value")
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	
	@Id
	@Column(name="innKey")
	public String getInnKey()
	{
		return innKey;
	}
	public void setInnKey(String innKey)
	{
		this.innKey = innKey;
	}
	
	
	
	
}
