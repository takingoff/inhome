 /**   
* projectName: InnMIS
*
* fileName: BillControl.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-9 下午6:20:28 
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

import tang.li.inn.domain.service.bill.BillPayService;
import tang.li.inn.entity.bill.BillPay;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.mis.vo.BillPayVo;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Controller
@RequestMapping("/billPay")
public class BillPayController
{
	@Autowired
	BillPayService billPayService;
	

	@RequestMapping("/billPayPage")
	@ResponseBody
	public JuiPaginationSupport<BillPayVo> billPayPage(@RequestBody JuiSortingCombineFilter jscf ,String enteredId)
	{
		try
		{
			return BillPayVo.paginationConvert(billPayService.juiPageFind(jscf,enteredId));

		}
		catch (InnException e)
		{
			e.printStackTrace();
			return new JuiPaginationSupport<BillPayVo>(InnConstant.LIST_EXCEPTION, 0, null);
		}

	}

	
	@RequestMapping("/billPayAdd")
	@ResponseBody
	public String billPayAdd(BillPay entity)
	{
		try
		{
			if(billPayService.save(entity) == null)
			{
				return InnConstant.OPERATE_FAILED_TERMINATE;
			}
			return InnConstant.ADD_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.ADD_FAILED;
	}

	
	@RequestMapping("/billPayModify")
	@ResponseBody
	public String billPayModify(String id,String description)
	{
		try
		{
			billPayService.modifyDescription(id,description);
			return InnConstant.MODIFY_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.MODIFY_FAILED;
	}

	@RequestMapping("/billPayDelete")
	@ResponseBody
	public String billPayDelete(@RequestBody List<String> ids)
	{
		try
		{
			billPayService.delete(ids);
			return InnConstant.DELETE_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.DELETE_FAILED;
	}

	@RequestMapping("/billPayGet")
	@ResponseBody
	public BillPayVo billPayGet(@RequestBody String id)
	{
		try
		{
			return new BillPayVo(billPayService.getById(id));

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
