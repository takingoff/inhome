 /**   
* projectName: InnMIS
*
* fileName: BillRoomVo.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-9 下午10:47:30 
*
* version : V1.0 
*/
package tang.li.inn.mis.vo;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.entity.bill.BillRoom;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.util.InnConstant;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class BillRoomVo
{
	
	
	private BillRoom bill;
	private String id;
	
	private String roomExpenseTypeDescription;
	
	public BillRoomVo(BillRoom br)
	{
		if(br == null)
		{
			return;
		}
		this.id = br.getId();
		this.bill = br;
		if(br.getRoomExpenseType() == InnConstant.T_BILLPAY_EXPENSE_ALLDAY)
		{
			roomExpenseTypeDescription = InnConstant.BILLROOMVO_EXPENSE_ALLDAY;
		}
		else if(br.getRoomExpenseType() == InnConstant.T_BILLPAY_EXPENSE_HALFDAY)
		{
			roomExpenseTypeDescription = InnConstant.BILLROOMVO_EXPENSE_HALFDAY;
		}
		else
		{
			roomExpenseTypeDescription = InnConstant.BILLROOMVO_EXPENSE_HOUR;
		}
	}
	
	public static JuiPaginationSupport<BillRoomVo> paginationConvert(JuiPaginationSupport<BillRoom> jps)
	{
		List<BillRoom> lr =  jps.getPageData();
		List<BillRoomVo> lrv = new ArrayList<BillRoomVo>();
		for(BillRoom r:lr)
		{
			lrv.add(new BillRoomVo(r));
		}
		return new JuiPaginationSupport<BillRoomVo>(jps.getError(),jps.getTotalRows(),lrv);
		
	}

	public BillRoom getBill()
	{
		return bill;
	}

	public void setBill(BillRoom bill)
	{
		this.bill = bill;
	}

	public String getRoomExpenseTypeDescription()
	{
		return roomExpenseTypeDescription;
	}

	public void setRoomExpenseTypeDescription(String roomExpenseTypeDescription)
	{
		this.roomExpenseTypeDescription = roomExpenseTypeDescription;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	
	
}
