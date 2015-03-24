/**   
 * projectName: InnMIS
 *
 * fileName: AccessInterceptor.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-4-19 下午8:45:11 
 *
 * version : V1.0 
 */
package tang.li.inn.mis.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tang.li.inn.infrastructure.util.InnConstant;

/**
 * 拦截除了登录以外的所有操作 检查是否登录失效
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
public class AccessInterceptor extends HandlerInterceptorAdapter
{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception
	{

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		String methodMessage  = method.getDeclaringClass().getSimpleName()+"."+method.getName() +"(";
		for(MethodParameter o : handlerMethod.getMethodParameters())
		{
			methodMessage += o.getParameterName() + " ";
		}
		methodMessage += ")";
		
		
		String reqUrl = request.getServletPath().substring(1);
		String queryString = request.getQueryString();
		
		///显示访问路径 以及执行函数
		if(queryString == null)
		{
			System.out.println("++++++++++请求地址：" +  reqUrl + " ===> " + methodMessage);
		}
		else
		{
			System.out.println("++++++++++请求地址：" +  reqUrl +"?" +queryString + " ===> " + methodMessage);
		}

		
		
		//拦截开关
		if(InnConstant.OPEN_ACCESS_INTERCEPTOR == false)
		{
			return true;
		}
		
		
		//// 过滤不需要的拦截
		for(String url:InnConstant.EXCLUDE_INTERCEPTOR)
		{
			if (reqUrl.startsWith(url))
			{
				return true;
			}
		}
		
		
		
		
		String isAjax = request.getHeader("X-Requested-With");
		if(isAjax != null)
		{
			System.out.println("XXXXXXXXXX：请求将会被拦截,是ajax访问");
		}
		else
		{
			System.out.println("XXXXXXXXXX：请求将会被拦截,是普通访问");
		}
		
		
		if(SessionUtil.getStaffName(request.getSession()) != null)
		{
			return true;
		}
		
		String loginUrl = request.getContextPath() + InnConstant.LOGIN_PATH;
		////如果登录超时
		//是ajax访问
		if (null != isAjax && isAjax.equalsIgnoreCase("xmlhttprequest"))
		{
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(InnConstant.LOGIN_TIME_OUT);
			
		}
		//不是ajax定位到登录页面
		else
		{
			response.sendRedirect(loginUrl);
		}

		// 如果返回false 那么认为它已经处理完了response
		return false;
		
	}

}
