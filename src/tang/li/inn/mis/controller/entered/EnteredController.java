 /**   
* projectName: InnMIS
*
* fileName: EnteredController.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-4 上午9:23:11 
*
* version : V1.0 
*/
package tang.li.inn.mis.controller.entered;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tang.li.inn.domain.service.entered.EnteredInfoService;
import tang.li.inn.entity.entered.EnteredInfo;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.jui.JuiSortingCombineFilter;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.mis.vo.EnteredInfoVo;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Controller
@RequestMapping("/entered")
public class EnteredController
{
	
	@Autowired
	private EnteredInfoService enteredInfoService;

	
	
	@RequestMapping("/enteredAdd")
	@ResponseBody
	public String enteredAdd(EnteredInfo entity,int enterDays,int payWay,double money)
	{
		try
		{
			if(enteredInfoService.enterIn(entity,payWay,money,enterDays))
			{
				return InnConstant.ADD_SUCCESS;
			}
			else
			{
				return InnConstant.OPERATE_FAILED_TERMINATE;
			}
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.ADD_FAILED;
	}

	
	@RequestMapping("/enteredModifyDescription")
	@ResponseBody
	public String enteredModify(String description,String id)
	{
		try
		{
			
			if(enteredInfoService.enteredModifyDescription(id,description))
			{
				return  InnConstant.MODIFY_SUCCESS;
			}
			else
			{
				return  InnConstant.MODIFY_FAILED;
			}
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return  InnConstant.MODIFY_FAILED;
	}
	
	/**
	 * @param entity 要求包含 id , name,phoneNumber,numberPeople,description
	 * @return
	 */
	@RequestMapping("/enteredModify")
	@ResponseBody
	public String enteredModify(EnteredInfo entity)
	{
		try
		{
			
			if(enteredInfoService.enteredModify(entity))
			{
				return  InnConstant.MODIFY_SUCCESS;
			}
			else
			{
				return  InnConstant.MODIFY_FAILED;
			}
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return  InnConstant.MODIFY_FAILED;
	}
	
	@RequestMapping("/enteredCheckOut")
	@ResponseBody
	public String enteredCheckOut(String id,int payWay,double money)
	{
		try
		{
			if(enteredInfoService.enteredCheckOut(id,payWay,money))
			{
				return  InnConstant.OPERATE_SUCCESS;
			}
			else
			{
				return  InnConstant.CHECKOUT_FAILED;
			}
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return  InnConstant.OPERATE_EXCEPTION;
	}
	
	
	@RequestMapping("/enteredContinue")
	@ResponseBody
	public String enteredContinue(String id,int days)
	{
		try
		{
			if(enteredInfoService.enteredContinue(id,days))
			{
				return  InnConstant.MODIFY_SUCCESS;
			}
			else
			{
				return  InnConstant.CONTINUE_FAILED;
			}
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return  InnConstant.CONTINUE_FAILED;
	}
	
	@RequestMapping("/enteredSwitch")
	@ResponseBody
	public String enteredSwitch(String id,String newRoomId,String oldRoomId)
	{
		try
		{
			if(enteredInfoService.enteredSwitchRoom(newRoomId,oldRoomId,id))
			{
				return  InnConstant.MODIFY_SUCCESS;
			}
			else
			{
				return  InnConstant.OPERATE_FAILED_TERMINATE;
			}
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return  InnConstant.MODIFY_FAILED;
	}
	

	
	
	@RequestMapping("/enteredPageView")
	public String enteredPageView()
	{
		return "/entered/paginationEntered";
	}
	
	@RequestMapping("/enteredPage")
	@ResponseBody
	public JuiPaginationSupport<EnteredInfoVo> enteredPage(@RequestBody JuiSortingCombineFilter jscf)
	{
		
		try
		{
			return EnteredInfoVo.paginationConvert(enteredInfoService.juiPageFind(jscf));
			
		}catch(InnException e)
		{
			e.printStackTrace();
			return new JuiPaginationSupport<EnteredInfoVo>(InnConstant.LIST_EXCEPTION,0,null);
		}
		
	}
	
	
	@RequestMapping("/enteredDelete")
	@ResponseBody
	public String enteredDelete(@RequestBody List<String> ids)
	{
		try
		{
			for(String id:ids)
			{
				enteredInfoService.delete(id);
			}
			return InnConstant.ELETE_ENTEREDIFNO_FINISH;
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return InnConstant.DELETE_FAILED;
	}
	
	
	@RequestMapping("/enteredGet")
	@ResponseBody
	public EnteredInfoVo enteredGet(@RequestBody String id)
	{
		try
		{
			return new EnteredInfoVo(enteredInfoService.getById(id));
			
		}catch(InnException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
}