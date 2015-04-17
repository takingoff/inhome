/**
 * projectName: InnInfrastructure
 * 
 * fileName: StaffDaoImpl.java
 * 
 * author : tangli <tanglidehaizi@gamil.com>
 * 
 * createTime :2014 2014-4-8 下午10:57:23
 * 
 * version : V1.0
 * 
 */
package tang.li.inn.infrastructure.dao.staff.impl;

import tang.li.inn.entity.staff.Staff;
import tang.li.inn.infrastructure.dao.impl.GenericDaoImpl;
import tang.li.inn.infrastructure.dao.staff.StaffDao;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
*/
public class StaffDaoImpl extends GenericDaoImpl<Staff,String> implements StaffDao
{

	/*@Override
	public void getStaff()
	{
		Session s = this.getHibernateTemplate().getSessionFactory().getCurrentSession();

		Staff m = (Staff) s.get(Staff.class, Long.parseLong("1"));

		System.out.println(m.getName());

		s.close();

	}

	@Override
	public void saveStaff() throws Exception
	{

		Session s = this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction t = s.beginTransaction();
		try
		{
			Staff m = new Staff();
			m.setName("mmmm");
			m.setPassword("ssss");
			m.setLevel(1);
			s.save(m);
			
			s.flush();
			Staff m2 = new Staff();
			s.save(m2);
			t.commit();
			
		}
		catch(Exception e)
		{
			t.rollback();
			throw new RuntimeException();
		}
		finally
		{
			s.close();
		}

	}

	public void infoStaff(String name, String value, String level)
	{
		@SuppressWarnings("deprecation")
//		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sf = new Configuration().addAnnotatedClass(Staff.class).configure().buildSessionFactory();
		
		Session s = sf.openSession();
		
//		Transaction tx = s.beginTransaction();
		Staff m = new Staff();
		m.setName("mmmm");
		m.setPassword("ssss");
		m.setLevel(1);
		
		s.save(m);
		s.flush();
//		s.save(new Staff());
//		tx.commit();
		
		@SuppressWarnings("unchecked")
		List<Staff> list =  s.createQuery("from Staff ").list();
		for(int i= 0 ;i < list.size(); i ++  )
			System.out.println(list.get(i).getName());
		
		s.close();
		sf.close();
	}*/
	
		

}
