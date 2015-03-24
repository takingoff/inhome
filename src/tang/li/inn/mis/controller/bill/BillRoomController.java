 /**   
* projectName: InnMIS
*
* fileName: BillRoomController.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-9 下午10:57:16 
*
* version : V1.0 
*/
package tang.li.inn.mis.controller.bill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tang.li.inn.domain.service.bill.BillRoomService;
import tang.li.inn.entity.bill.BillRoom;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.DateTimeUtil;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.mis.vo.BillRoomVo;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Controller
@RequestMapping("/billRoom")
public class BillRoomController
{
	@Autowired
	BillRoomService billRoomService;

	@RequestMapping("/billRoomPage")
	@ResponseBody
	public JuiPaginationSupport<BillRoomVo> billRoomPage(@RequestBody JuiSortingCombineFilter jscf,String enteredId)
	{

		try
		{
			return BillRoomVo.paginationConvert(billRoomService.juiPageFind(jscf,enteredId));

		}
		catch (InnException e)
		{
			e.printStackTrace();
			return new JuiPaginationSupport<BillRoomVo>(InnConstant.LIST_EXCEPTION, 0, null);
		}

	}

	@RequestMapping("/billRoomAdd")
	@ResponseBody
	public String billRoomAdd(BillRoom entity)
	{
		try
		{
			entity.setGenDate(DateTimeUtil.convertNowToInnDateFormat(0));
			billRoomService.save(entity);
			return InnConstant.ADD_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.ADD_FAILED;
	}

	@RequestMapping("/billRoomModify")
	@ResponseBody
	public String billRoomModify(BillRoom entity)
	{
		try
		{
			billRoomService.modifyUpdate(entity);
			return InnConstant.MODIFY_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.MODIFY_FAILED;
	}

	@RequestMapping("/billRoomDelete")
	@ResponseBody
	public String billRoomDelete(@RequestBody List<String> ids)
	{
		try
		{
			for (String id : ids)
			{
				billRoomService.delete(id);
			}
			return InnConstant.DELETE_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.DELETE_FAILED;
	}

	@RequestMapping("/billRoomGet")
	@ResponseBody
	public BillRoomVo billRoomGet(@RequestBody String id)
	{
		try
		{
			return new BillRoomVo(billRoomService.getById(id));

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
