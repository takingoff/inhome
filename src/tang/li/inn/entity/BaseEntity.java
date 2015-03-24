/**   
 * projectName: InnEntity
 *
 * fileName: BaseEntity.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-4-11 上午8:31:27 
 *
 * version : V1.0 
 */
package tang.li.inn.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * <description>
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
@MappedSuperclass
public class BaseEntity implements Serializable
{

	private static final long serialVersionUID = 1718555846651757641L;
	private String id;
//	@Id
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}


}
