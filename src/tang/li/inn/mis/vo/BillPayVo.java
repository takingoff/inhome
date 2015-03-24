 /**   
* projectName: InnMIS
*
* fileName: BillPayVo.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-9 下午10:21:38 
*
* version : V1.0 
*/
package tang.li.inn.mis.vo;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.entity.bill.BillPay;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.util.InnConstant;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class BillPayVo
{
	private BillPay bill;
	private String payWayDescription;
	private String id;
	public BillPayVo(BillPay bill)
	{
		if(bill == null)
		{
			return ;
		}
		this.id = bill.getId();
		this.bill = bill;
		if(bill.getPayWay()== InnConstant.T_BILLPAY_PAYWAY_BANK)
		{
			payWayDescription = InnConstant.BILLPAYVO_PAYWAY_BANK;
		}
		else if(bill.getPayWay()== InnConstant.T_BILLPAY_PAYWAY_CASH)
		{
			payWayDescription = InnConstant.BILLPAYVO_PAYWAY_CASH;
		}else
		{
			payWayDescription = InnConstant.BILLPAYVO_PAYWAY_OTHER;
		}
		
	}
	
	public static JuiPaginationSupport<BillPayVo> paginationConvert(JuiPaginationSupport<BillPay> jps)
	{
		List<BillPay> lr =  jps.getPageData();
		List<BillPayVo> lrv = new ArrayList<BillPayVo>();
		for(BillPay r:lr)
		{
			lrv.add(new BillPayVo(r));
		}
		return new JuiPaginationSupport<BillPayVo>(jps.getError(),jps.getTotalRows(),lrv);
		
	}

	public BillPay getBill()
	{
		return bill;
	}

	public void setBill(BillPay bill)
	{
		this.bill = bill;
	}

	public String getPayWayDescription()
	{
		return payWayDescription;
	}

	public void setPayWayDescription(String payWayDescription)
	{
		this.payWayDescription = payWayDescription;
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
