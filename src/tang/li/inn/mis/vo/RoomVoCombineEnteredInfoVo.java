 /**   
* projectName: InnMIS
*
* fileName: RoomVoCombineEnteredInfoVo.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-4 下午4:42:54 
*
* version : V1.0 
*/
package tang.li.inn.mis.vo;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class RoomVoCombineEnteredInfoVo
{
	private RoomVo rVo;
	private EnteredInfoVo eVo;
	
	public RoomVoCombineEnteredInfoVo(RoomVo rVo,EnteredInfoVo eVo)
	{
		this.rVo = rVo;
		this.eVo = eVo;
		
		//减少数据传输量！
		if(eVo != null && eVo.getEnteredInfo()!= null)
		{
			eVo.getEnteredInfo().setRoom(null);
		}
	}
	public RoomVo getrVo()
	{
		return rVo;
	}
	public void setrVo(RoomVo rVo)
	{
		this.rVo = rVo;
	}
	public EnteredInfoVo geteVo()
	{
		return eVo;
	}
	public void seteVo(EnteredInfoVo eVo)
	{
		this.eVo = eVo;
	}
	
	
}
