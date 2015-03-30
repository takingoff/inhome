package tang.li.inn.infrastructure.util;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import tang.li.inn.entity.staff.Staff;
import tang.li.inn.infrastructure.dao.staff.StaffDao;
import tang.li.inn.mis.util.ApplicationContextHolder;

public class InnAuthticateRealm extends AuthorizingRealm  
{

	StaffDao staffDao =null;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		try
		{
			staffDao = ApplicationContextHolder.getBean("staffDao");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		try
		{
			staffDao = ApplicationContextHolder.getBean("staffDao");
			//name
			String principal = (String) token.getPrincipal();
			Staff staff = staffDao.findUniqueByProperty(InnConstant.T_STAFF_NAME,principal);
			if(staff != null)
				return new SimpleAuthenticationInfo(token.getPrincipal(), staff.getPassword(),getName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	

}
