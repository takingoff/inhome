 /**   
* projectName: InnMIS
*
* fileName: EnteredInfoVo.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-4 上午9:39:10 
*
* version : V1.0 
*/
package tang.li.inn.mis.vo;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.entity.entered.EnteredInfo;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.util.InnConstant;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class EnteredInfoVo
{
	private EnteredInfo enteredInfo;	
	private String hourRoomDescription;		//小时房说明
	private String checkOutDescription;		//是否退房说明
	private String id;						//因为客户端必须要这个字段，没办法。
	private boolean checkOutWarn;			//退房警告，
	public static int CHECKOUT_WARN_ADVANCE_MINUTES = 15;
	
	public static JuiPaginationSupport<EnteredInfoVo> paginationConvert(JuiPaginationSupport<EnteredInfo> jps)
	{
		List<EnteredInfo> es =  jps.getPageData();
		List<EnteredInfoVo> vos = new ArrayList<EnteredInfoVo>();
		for(EnteredInfo e:es)
		{
			vos.add(new EnteredInfoVo(e));
		}
		return new JuiPaginationSupport<EnteredInfoVo>(jps.getError(),jps.getTotalRows(),vos);
		
	}
	
	
	public EnteredInfoVo(EnteredInfo ei)
	{
		if(ei == null)
		{
			return;
		}
		this.id = ei.getId();
		this.enteredInfo = ei;
		this.checkOutDescription = ei.getIsCheckOut() ? InnConstant.ENTEREDINFOVO_CHECKOUT :InnConstant.ENTEREDINFOVO_NOT_CHECKOUT;
		this.hourRoomDescription = ei.getIsHourRoom() ? InnConstant.ENTEREDINFOVO_HOURROOM :InnConstant.ENTEREDINFOVO_NOT_HOURROOM;
		if(ei.getIsHourRoom())
		{
			//小时房 判断警告
			checkOutWarn = true;
		}
		else
		{
			//正常入住 警告。
			checkOutWarn = true;
			
		}
		
	}


	public boolean isCheckOutWarn()
	{
		return checkOutWarn;
	}


	public void setCheckOutWarn(boolean checkOutWarn)
	{
		this.checkOutWarn = checkOutWarn;
	}


	public String getId()
	{
		return id;
	}


	public void setId(String id)
	{
		this.id = id;
	}


	public EnteredInfo getEnteredInfo()
	{
		return enteredInfo;
	}


	public void setEnteredInfo(EnteredInfo enteredInfo)
	{
		this.enteredInfo = enteredInfo;
	}


	public String getHourRoomDescription()
	{
		return hourRoomDescription;
	}


	public void setHourRoomDescription(String hourRoomDescription)
	{
		this.hourRoomDescription = hourRoomDescription;
	}


	public String getCheckOutDescription()
	{
		return checkOutDescription;
	}


	public void setCheckOutDescription(String checkOutDescription)
	{
		this.checkOutDescription = checkOutDescription;
	}
	
	
}
