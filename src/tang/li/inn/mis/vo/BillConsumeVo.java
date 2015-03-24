 /**   
* projectName: InnMIS
*
* fileName: BillConsumeVo.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-9 下午10:55:18 
*
* version : V1.0 
*/
package tang.li.inn.mis.vo;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.entity.bill.BillConsume;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class BillConsumeVo
{
	private BillConsume bill;
	private String id;
	public BillConsumeVo(BillConsume bc)
	{
		if(bc == null)
		{
			return ;
		}
		this.bill = bc;
		this.id = bc.getId();
	}
	
	public static JuiPaginationSupport<BillConsumeVo> paginationConvert(JuiPaginationSupport<BillConsume> jps)
	{
		List<BillConsume> lr =  jps.getPageData();
		List<BillConsumeVo> lrv = new ArrayList<BillConsumeVo>();
		for(BillConsume r:lr)
		{
			
			lrv.add(new BillConsumeVo(r));
		}
		return new JuiPaginationSupport<BillConsumeVo>(jps.getError(),jps.getTotalRows(),lrv);
		
	}

	public BillConsume getBill()
	{
		return bill;
	}

	public void setBill(BillConsume bill)
	{
		this.bill = bill;
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
