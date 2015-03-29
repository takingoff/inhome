package tang.li.inn.infrastructure.dao.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;
import org.springframework.stereotype.Repository;

import tang.li.inn.entity.staff.Staff;
import tang.li.inn.infrastructure.dao.staff.StaffDao;
import tang.li.inn.infrastructure.exception.InnException;

@Repository
public class InnAuthticate implements Realm
{

	@Resource
	StaffDao staffDao;
	
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException
	{
		return null;
	}

	@Override
	public String getName()
	{
		return null;
	}

	@Override
	public boolean supports(AuthenticationToken token)
	{
		//pass
		String credentials = ((char[]) token.getCredentials()).toString();
		//name
		String principal = (String) token.getPrincipal();
		
		try
		{
			Staff staff = staffDao.get(principal);
//			if(staff.getPassword().equals(arg0))
		}
		catch (InnException e)
		{
			e.printStackTrace();
		}
		
		
		return false;
	}

}
