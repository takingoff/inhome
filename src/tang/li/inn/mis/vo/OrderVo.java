 /**   
* projectName: InnMIS
*
* fileName: OrderVo.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-25 下午8:58:00 
*
* version : V1.0 
*/
package tang.li.inn.mis.vo;

import java.util.ArrayList;
import java.util.List;

import tang.li.inn.entity.order.Order;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.util.InnConstant;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class OrderVo
{
	private Order order;
	private String stateDescription;
	private String id;
	
	public static JuiPaginationSupport<OrderVo> paginationConvert(JuiPaginationSupport<Order> jps)
	{
		List<Order> lr =  jps.getPageData();
		List<OrderVo> lrv = new ArrayList<OrderVo>();
		for(Order r:lr)
		{
			lrv.add(new OrderVo(r));
		}
		return new JuiPaginationSupport<OrderVo>(jps.getError(),jps.getTotalRows(),lrv);
		
	}
	
	public OrderVo(Order order)
	{
		if(order == null)
		{
			return;
		}
		this.order = order;
		this.id = order.getId();
		
		if(order.getState() == InnConstant.T_ORDER_STATE_EXPIER)
		{
			this.stateDescription = InnConstant.ORDERVO_EXPIERSTATE;
		}
		else if(order.getState() == InnConstant.T_ORDER_STATE_USED)
		{
			this.stateDescription = InnConstant.ORDERVO_USEDLSTATE;
		}
		else if(order.getState() == InnConstant.T_ORDER_STATE_GIVEUP)
		{
			this.stateDescription = InnConstant.ORDERVO_GIVEUPSTATE;
		}
		else if(order.getState()==InnConstant.T_ORDER_STATE_NORMAL)
		{
			this.stateDescription = InnConstant.ORDERVO_NORMALSTATE;
		}
		else
		{
			this.stateDescription = InnConstant.VO_INVALIDATE;
		}
		
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public String getStateDescription()
	{
		return stateDescription;
	}

	public void setStateDescription(String stateDescription)
	{
		this.stateDescription = stateDescription;
	}
	
	
	
}
