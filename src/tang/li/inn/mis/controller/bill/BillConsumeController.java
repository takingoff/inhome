/**   
 * projectName: InnMIS
 *
 * fileName: BillConsumeController.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-5-9 下午10:57:01 
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

import tang.li.inn.domain.service.bill.BillConsumeService;
import tang.li.inn.entity.bill.BillConsume;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.mis.vo.BillConsumeVo;

/**
 * <description>
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
@Controller
@RequestMapping("/billConsume")
public class BillConsumeController
{
	@Autowired
	BillConsumeService billConsumeService;

	@RequestMapping("/billConsumePage")
	@ResponseBody
	public JuiPaginationSupport<BillConsumeVo> billConsumePage(@RequestBody JuiSortingCombineFilter jscf,String enteredId)
	{

		try
		{
			return BillConsumeVo.paginationConvert(billConsumeService.juiPageFind(jscf,enteredId));

		}
		catch (InnException e)
		{
			e.printStackTrace();
			return new JuiPaginationSupport<BillConsumeVo>(InnConstant.LIST_EXCEPTION, 0, null);
		}

	}

	@RequestMapping("/billConsumeAdd")
	@ResponseBody
	public String billConsumeAdd(BillConsume entity)
	{
		try
		{
			if(billConsumeService.save(entity) == null)
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

	@RequestMapping("/billConsumeModify")
	@ResponseBody
	public String billConsumeModify(String id,String description)
	{
		try
		{
			billConsumeService.modifyDescription(id,description);
			return InnConstant.MODIFY_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.MODIFY_FAILED;
	}

	@RequestMapping("/billConsumeDelete")
	@ResponseBody
	public String billConsumeDelete(@RequestBody List<String> ids)
	{
		try
		{
			for (String id : ids)
			{
				billConsumeService.delete(id);
			}
			return InnConstant.DELETE_SUCCESS;

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.DELETE_FAILED;
	}

	@RequestMapping("/billConsumeGet")
	@ResponseBody
	public BillConsumeVo billConsumeGet(@RequestBody String id)
	{
		try
		{
			return new BillConsumeVo(billConsumeService.getById(id));

		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
