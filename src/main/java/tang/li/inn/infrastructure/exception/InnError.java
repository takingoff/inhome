 /**   
* projectName: InnInfrastructure
*
* fileName: InnError.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-11 上午9:15:09 
*
* version : V1.0 
*/
package tang.li.inn.infrastructure.exception;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class InnError
{
	private int code;
	private String info;
	private String des;
	public int getCode()
	{
		return code;
	}
	public void setCode(int code)
	{
		this.code = code;
	}
	public String getInfo()
	{
		return info;
	}
	public void setInfo(String info)
	{
		this.info = info;
	}
	public String getDes()
	{
		return des;
	}
	public void setDes(String des)
	{
		this.des = des;
	}
	
}
