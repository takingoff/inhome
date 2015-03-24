 /**   
* projectName: InnMIS
*
* fileName: OrderController.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-25 下午2:50:59 
*
* version : V1.0 
*/
package tang.li.inn.mis.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tang.li.inn.domain.service.order.OrderService;
import tang.li.inn.domain.service.room.RoomService;
import tang.li.inn.entity.order.Order;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.mis.vo.OrderVo;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Controller
@RequestMapping("/order")
public class OrderController
{

	@Autowired
	OrderService orderService;
	
	@Autowired
	RoomService roomService;

	@RequestMapping("/order")
	public String paginationOrderView()
	{
		return "order/paginationOrder";
	}

	@RequestMapping("/orderPage")
	@ResponseBody
	public JuiPaginationSupport<OrderVo> orderPage(@RequestBody JuiSortingCombineFilter jscf)
	{

		try
		{
			return OrderVo.paginationConvert(orderService.juiPageFind(jscf)) ;

		}
		catch (InnException e)
		{
			e.printStackTrace();
			return new JuiPaginationSupport<OrderVo>(InnConstant.LIST_EXCEPTION, 0, null);
		}

	}


	
	@RequestMapping("/orderAdd")
	@ResponseBody
	public String orderAdd(Order entity)
	{
		try
		{
			entity.setState(InnConstant.T_ORDER_STATE_NORMAL);
			orderService.save(entity);
			return InnConstant.OPERATE_SUCCESS;
		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.OPERATE_FAILD;
	}
	
	
	@RequestMapping("/orderModify")
	@ResponseBody
	public String orderModify(Order entity)
	{
		try
		{

			orderService.executeModify(entity);
			return InnConstant.MODIFY_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.MODIFY_FAILED;
	}

	
	@RequestMapping("/orderDelete")
	@ResponseBody
	public String orderDelete(@RequestBody List<String> ids)
	{
		try
		{
			for (String id : ids)
			{
				orderService.delete(id);
			}
			return InnConstant.DELETE_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.DELETE_FAILED;
	}

	@RequestMapping("/orderGet")
	@ResponseBody
	public OrderVo orderGet(@RequestBody String id)
	{
		try
		{
			return new OrderVo(orderService.getById(id));

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
