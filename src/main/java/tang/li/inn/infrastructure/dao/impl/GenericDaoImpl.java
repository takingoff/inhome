/**   
 * projectName: InnInfrastructure
 *
 * fileName: GenericDaoImpl.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-4-11 下午8:20:23 
 *
 * version : V1.0 
 */
package tang.li.inn.infrastructure.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import tang.li.inn.infrastructure.dao.GenericDao;
import tang.li.inn.infrastructure.exception.InnErrorsUtil;
import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.util.InnConstant;
import tang.li.inn.infrastructure.util.PaginationSupport;
import tang.li.inn.infrastructure.util.ReflectUtil;

/**
 * <description>
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
public class GenericDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK>
{

	// @Entity 对应的class 主要作用在Criteria查询时需要通过反射将数据 转换成相应的entity对象。
	protected Class<?> entityClass;

	public GenericDaoImpl()
	{
		this.entityClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + entityClass.toString());
	}

	public Session createSession() throws InnException
	{
		try
		{
			return super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.createSession"), e);
		}
	}

	public boolean delete(T entity) throws InnException
	{
		Assert.notNull(entity);
		try
		{
			Session session = createSession();
			session.delete(entity);
			session.flush();
			return true;
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.deleteEntity"), e);
		}
	}

	public boolean delete(PK id) throws InnException
	{
		Assert.notNull(id);
		try
		{
			T _t = get(id);
			Session session = createSession();
			session.delete(_t);
			session.flush();
			return true;
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.deleteId"), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... values) throws InnException
	{
		try
		{
			return createQuery(hql, values).list();
		}
		catch (Exception e)
		{
			if (e instanceof InnException)
			{
				throw (InnException) e;
			}
			else
			{
				throw new InnException(InnErrorsUtil.getInnError("inn.dao.find"), e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findRecent(String hql, int num, Object... values) throws InnException
	{
		try
		{
			Query _query = this.createQuery(hql, values);
			_query.setMaxResults(num);
			return _query.list();
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.findRecent"), e);
		}
	}

	public List<T> findAll() throws InnException
	{
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion... criterion) throws InnException
	{
		try
		{
			return createCriteria(criterion).list();
		}
		catch (Exception e)
		{
			if (e instanceof InnException)
			{
				throw (InnException) e;
			}
			else
			{
				throw new InnException(InnErrorsUtil.getInnError("inn.dao.findByCriteria"), e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value) throws InnException
	{
		Assert.hasText(propertyName);
		try
		{
			return createCriteria(Restrictions.eq(propertyName, value)).list();
		}
		catch (Exception e)
		{
			if (e instanceof InnException)
			{
				throw (InnException) e;
			}
			else
			{
				throw new InnException(InnErrorsUtil.getInnError("inn.dao.findByProperty"), e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(String propertyName, Object value) throws InnException
	{
		Assert.hasText(propertyName);
		try
		{
			return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
		}
		catch (Exception e)
		{
			if (e instanceof InnException)
			{
				throw (InnException) e;
			}
			else
			{
				throw new InnException(InnErrorsUtil.getInnError("inn.dao.findUniqueByProperty"), e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) throws InnException
	{
		Assert.notNull(id);
		try
		{
			return (T) createSession().get(entityClass, id);
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.get"), e);
		}
	}

	public Serializable save(T entity) throws InnException
	{
		Assert.notNull(entity);
		try
		{
			Session session = createSession();
			Serializable id = session.save(entity);
			session.flush();
			return id;
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.save"), e);
		}
	}

	public boolean merge(T entity) throws InnException
	{
		Assert.notNull(entity);
		try
		{
			Session session = createSession();
			session.merge(entity);
			session.flush();
			return true;
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.merge"), e);
		}
	}

	public boolean update(T entity) throws InnException
	{
		Assert.notNull(entity);
		try
		{
			Session session = createSession();
			session.update(entity);
			session.flush();
			return true;
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.update"), e);
		}
	}

	public boolean saveOrUpdate(T entity) throws InnException
	{
		Assert.notNull(entity);
		try
		{
			Session session = createSession();
			session.saveOrUpdate(entity);
			session.flush();
			return true;
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.saveOrUpdate"), e);
		}
	}

	public Criteria createCriteria(Criterion... criterions) throws InnException
	{
		Criteria criteria = createSession().createCriteria(entityClass);
		for (Criterion c : criterions)
		{
			criteria.add(c);
		}
		return criteria;
	}

	public Query createQuery(String hql, Object... values) throws InnException
	{
		Assert.hasText(hql);
		Query query = createSession().createQuery(hql);
		if (null != values)
		{
			for (int i = 0; i < values.length; i++)
			{
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	public boolean executeUpdate(String hql, Object... values) throws InnException
	{
		Assert.hasText(hql);
		try
		{
			Session session = createSession();
			Query query = session.createQuery(hql);
			if (null != values)
			{
				for (int i = 0; i < values.length; i++)
				{
					query.setParameter(i, values[i]);
				}
			}
			query.executeUpdate();
			session.flush();
			return true;
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.executeUpdate"), e);
		}
	}

	/** 执行sql更新 */
	public boolean executeSQLUpdate(String sql) throws InnException
	{
		Assert.hasText(sql);
		Session session = createSession();
		Query query = session.createSQLQuery(sql);
		try
		{
			query.executeUpdate();
			session.flush();
			return true;
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.executeSQLUpdate"), e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PaginationSupport pagedFind(int startIndex, int rowsPerPage, String hql, boolean isDesc, Object... values) throws InnException
	{
		Assert.hasText(hql);
		int totalRows = 0;

		// 得到hql语句 设置参数
		String _chql;
		if (hql.indexOf(InnConstant.FILTER_ORDER_BY) == -1)
		{
			_chql = "select count(*) " + hql.substring(hql.indexOf("from"), hql.indexOf(InnConstant.FILTER_ORDER_BY));
		}
		else
		{
			_chql = "select count(*) " + hql.substring(hql.indexOf("from"));
		}
		if (isDesc)
		{
			hql += " desc";
		}
		Query cQuery = createSession().createQuery(_chql);
		Query query = createSession().createQuery(hql);
		if (null != values)
		{
			for (int i = 0; i < values.length; i++)
			{
				query.setParameter(i, values[i]);
				cQuery.setParameter(i, values[i]);
			}
		}

		// /获取总记录数
		List _list = null;
		try
		{
			_list = cQuery.list();
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.pageFind"), e);
		}
		if (_list != null)
		{
			if (_list.size() > 0)
			{
				totalRows = ((Long) _list.get(0)).intValue();
			}
		}

		// 如果传入的“每页记录数参数为0”那么将其设置为“总记录数”
		if (0 == rowsPerPage)
		{
			rowsPerPage = totalRows;
		}

		// 获取记录
		query.setFirstResult(startIndex);
		query.setMaxResults(rowsPerPage);
		List _items = null;
		try
		{
			_items = query.list();
		}
		catch (Exception e)
		{
			logger.error("pagedFind 异常:", e);
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.pageFind"), e);
		}
		if (_items == null)
		{
			_items = new ArrayList();
		}
		PaginationSupport ps = new PaginationSupport(_items, totalRows, rowsPerPage, startIndex);
		return ps;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <C> PaginationSupport<C> pagedCustomFind(int startIndex, int rowsPerPage, String hql, boolean isDesc, Class<C> C, String[] fields, Object... values) throws InnException
	{
		Assert.hasText(hql);
		int totalRows = 0;

		// 得到hql语句 设置参数
		String _chql;
		if (hql.indexOf(InnConstant.FILTER_ORDER_BY) == -1)
		{
			_chql = "select count(*) " + hql.substring(hql.indexOf("from"), hql.indexOf(InnConstant.FILTER_ORDER_BY));
		}
		else
		{
			_chql = "select count(*) " + hql.substring(hql.indexOf("from"));
		}
		if (isDesc)
		{
			hql += " desc";
		}
		Query cQuery = createSession().createQuery(_chql);
		Query query = createSession().createQuery(hql);
		if (null != values)
		{
			for (int i = 0; i < values.length; i++)
			{
				query.setParameter(i, values[i]);
				cQuery.setParameter(i, values[i]);
			}
		}

		// /获取总记录数
		List _list = null;
		try
		{
			_list = cQuery.list();
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.pageFind"), e);
		}
		if (_list != null)
		{
			if (_list.size() > 0)
			{
				totalRows = ((Long) _list.get(0)).intValue();
			}
		}

		// 如果传入的“每页记录数参数为0”那么将其设置为“总记录数”
		if (0 == rowsPerPage)
		{
			rowsPerPage = totalRows;
		}

		// 获取记录
		query.setFirstResult(startIndex);
		query.setMaxResults(rowsPerPage);
		List result = null;
		try
		{
			result = query.list();
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.pagedCustomFind"), e);
		}
		List<C> _items = ReflectUtil.fromArrayToObject(result, C, fields);
		if (_items == null)
			_items = new ArrayList<C>();
		PaginationSupport ps = new PaginationSupport<C>(_items, totalRows, rowsPerPage, startIndex);
		return ps;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <C> PaginationSupport<C> pagedFind(int startIndex, int rowsPerPage, String hql, boolean isDesc, Class<C> claz, Object... values) throws InnException
	{
		Assert.hasText(hql);
		int totalRows = 0;

		// 得到hql语句 设置参数
		String _chql;
		if (hql.indexOf(InnConstant.FILTER_ORDER_BY) == -1)
		{
			_chql = "select count(*) " + hql.substring(hql.indexOf("from"), hql.indexOf(InnConstant.FILTER_ORDER_BY));
		}
		else
		{
			_chql = "select count(*) " + hql.substring(hql.indexOf("from"));
		}
		if (isDesc)
		{
			hql += " desc";
		}
		Query cQuery = createSession().createQuery(_chql);
		Query query = createSession().createQuery(hql);
		if (null != values)
		{
			for (int i = 0; i < values.length; i++)
			{
				query.setParameter(i, values[i]);
				cQuery.setParameter(i, values[i]);
			}
		}

		// /获取总记录数
		List _list = null;
		try
		{
			_list = cQuery.list();
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.pageFind"), e);
		}
		if (_list != null)
		{
			if (_list.size() > 0)
			{
				totalRows = ((Long) _list.get(0)).intValue();
			}
		}

		// 如果传入的“每页记录数参数为0”那么将其设置为“总记录数”
		if (0 == rowsPerPage)
		{
			rowsPerPage = totalRows;
		}

		// 获取记录
		query.setFirstResult(startIndex);
		query.setMaxResults(rowsPerPage);
		List<C> _items = null;
		try
		{
			_items = query.list();
		}
		catch (Exception e)
		{
			logger.error("pagedFind 异常:", e);
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.pageFind"), e);
		}
		if (_items == null)
		{
			_items = new ArrayList<C>();
		}
		PaginationSupport ps = new PaginationSupport<C>(_items, totalRows, rowsPerPage, startIndex);
		return ps;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <C> JuiPaginationSupport<C> juiPageFind(int pageNum, int rowsPerPage, String hql, List<Object> values) throws InnException
	{

		Assert.hasText(hql);

		// 得到hql语句 设置参数
		String _chql;
		if (hql.indexOf(InnConstant.FILTER_ORDER_BY) == -1)
		{
			_chql = "select count(*) " + hql.substring(hql.indexOf("from"));
		}
		else
		{
			_chql = "select count(*) " + hql.substring(hql.indexOf("from"), hql.indexOf(InnConstant.FILTER_ORDER_BY));
		}

		Query cQuery = createSession().createQuery(_chql);
		Query query = createSession().createQuery(hql);

		if (null != values)
		{
			for (int i = 0; i < values.size(); i++)
			{

				query.setParameter(i, values.get(i));
				cQuery.setParameter(i, values.get(i));
			}
		}

		// /获取总记录数
		List _list = null;
		int totalRows = 0;
		try
		{
			_list = cQuery.list();
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.pageFind"), e);
		}
		if (_list != null)
		{
			if (_list.size() > 0)
			{
				totalRows = ((Long) _list.get(0)).intValue();
			}
		}

		// 如果传入的“每页记录数参数为0”那么将其设置为“总记录数”
		if (0 == rowsPerPage)
		{
			rowsPerPage = totalRows;
		}

		// 获取记录
		int startIndex = pageNum <= 0 ? 0 : (pageNum - 1) * rowsPerPage;
		query.setFirstResult(startIndex);
		query.setMaxResults(rowsPerPage);
		List<C> pageData = null;
		try
		{
			pageData = query.list();
		}
		catch (Exception e)
		{
			logger.error("pagedFind 异常:", e);
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.pageFind"), e);
		}
		if (pageData == null)
		{
			pageData = new ArrayList<C>();
		}
		return new JuiPaginationSupport(null, totalRows, pageData);
	}

	@Override
	public Object executeHQLQuery(String hql, Object... values) throws InnException
	{
		Assert.hasText(hql);
		try
		{
			Session session = createSession();
			Query query = session.createQuery(hql);
			if (null != values)
			{
				for (int i = 0; i < values.length; i++)
				{
					query.setParameter(i, values[i]);
				}
			}
			@SuppressWarnings("unchecked")
			List<Object> list = query.list();

			return list == null ? null : list.get(0);
		}
		catch (Exception e)
		{
			throw new InnException(InnErrorsUtil.getInnError("inn.dao.executeUpdate"), e);
		}
	}

}
