 /**   
* projectName: InnMIS
*
* fileName: CommonController.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-4-19 下午6:32:21 
*
* version : V1.0 
*/
package tang.li.inn.mis.controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tang.li.inn.domain.service.entry.InnEntryService;
import tang.li.inn.domain.service.staff.StaffService;
import tang.li.inn.entity.entry.InnEntry;
import tang.li.inn.entity.staff.Staff;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.infrastructure.util.StringUtil;
import tang.li.inn.mis.util.AuthCodeGenerator;
import tang.li.inn.mis.util.SessionUtil;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
@Controller
@RequestMapping("/common")
public class CommonController
{
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private InnEntryService innEntryService;
	
	@RequestMapping("/authCode")
	public void genAuthCode( HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		//response 参数的 outStream 进行响应。
		AuthCodeGenerator.generateAuthCode(request,response);
	}
	
	@RequestMapping("/checkAuthCode")
	@ResponseBody
	public String checkAuthCode(String authCode,HttpSession session) throws ServletException
	{
		//验证码错误
		if(InnConstant.OPEN_AUTH_CODE && 
				(SessionUtil.getAuthCode(session) == null ||StringUtil.validateAuthCode(SessionUtil.getAuthCode(session),authCode) == false))
		{
			return InnConstant.AUTHCODE_ERROR;
		}
		//验证码正确
		return null;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpSession session,HttpServletResponse response,HttpServletRequest request)
	{
		SessionUtil.clearLoginAttribute(session);
		try
		{
			response.sendRedirect( request.getContextPath() + InnConstant.LOGIN_PATH);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String staffLogin(String name,String password,String authCode,HttpSession session)
	{
		try
		{
			//验证码错误
			if(InnConstant.OPEN_AUTH_CODE && 
					(SessionUtil.getAuthCode(session) == null ||StringUtil.validateAuthCode(SessionUtil.getAuthCode(session),authCode) == false))
			{
				return InnConstant.AUTHCODE_ERROR;
			}
			
			Staff staff = staffService.findStaffByName(name);
			//用户名不存在
			if(staff == null)
			{
				return InnConstant.LOGIN_STAFF_NOT_EXIST;
			}
			//密码错误
			if(!staff.getPassword().equals(password))
			{
				return InnConstant.LOGIN_STAFF_PSSWORD_ERROR;
			}
			
			//登录成功 保存用户名 用户级别 ，以及T_INN_CONTAINER表中的键值对
			SessionUtil.saveStaffLEVEL(session,staff.getLevel());
			SessionUtil.saveStaffName(session,staff.getName());
			SessionUtil.saveInnEntry(session,innEntryService.getAll());
			
			//登录成功后返回 null客户端接着处理
			return null;
		}
		catch (InnException e)
		{
			e.printStackTrace();
			return InnConstant.ACCESS_EXCEPTION;
		}
		
	}
	
	
	@RequestMapping("/changeInnEntry")
	@ResponseBody
	public String changeInnEntry(HttpSession s,String key,String value)
	{
		try
		{
			
			System.out.println(key);
			System.out.println(value);
			//获取
			InnEntry entry =  innEntryService.findUniqueByKey(key);
			//修改
			if(entry != null)
			{
				entry.setValue(value);
				innEntryService.modify(entry);
				//刷新session
				SessionUtil.saveInnEntry(s,innEntryService.getAll());
				
			}
		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@RequestMapping("/controlCenter")
	public String enterControlCenter()
	{
		//跳转到控制中心。
		return "common/controlCenter";
	}
	
	/**
	 * 跳转到账单统计
	 * @return
	 */
	@RequestMapping("/billPage")
	public String enterBill()
	{
		return "bill/billPage";
	}
	
	/**
	 * 跳转到某个入住信息的账单统计
	 * @param enteredId
	 * @param mm
	 * @return
	 */
	@RequestMapping("/enteredBillPage")
	public String enterEnteredBill(String enteredId,ModelMap mm)
	{
		mm.put("enteredId",enteredId);
		return "bill/enteredBillPage";
	}
	
	
}
