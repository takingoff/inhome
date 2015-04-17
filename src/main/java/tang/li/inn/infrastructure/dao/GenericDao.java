/**   
 * projectName: InnInfrastructure
 *
 * fileName: GenericDao.java 
 *
 * author : tangli <tanglidehaizi@gamil.com>
 *
 * createTime :2014 2014-4-9 下午6:12:30 
 *
 * version : V1.0 
 */
package tang.li.inn.infrastructure.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import tang.li.inn.infrastructure.exception.InnException;
import tang.li.inn.infrastructure.jui.JuiPaginationSupport;
import tang.li.inn.infrastructure.util.PaginationSupport;

/**
 * 通用泛型Dao 接口 PK为entity的主键类型 ，T为entity类型
 * 
 * @author tangli <tanglidehaizi@gamil.com>
 * @version V1.0
 * @see
 * @since
 */
public interface GenericDao<T, PK extends Serializable>
{
	/**
	 * <strong>新增实体</strong>
	 * 
	 * @param entity
	 *            实体
	 * @return 实体唯一标识，id
	 */
	Serializable save(T entity) throws InnException;

	/**
	 * <strong>更新实体的属性</strong>
	 * 
	 * @param entity
	 *            实体
	 * @return 成功true，失败false
	 */
	boolean merge(T entity) throws InnException;

	/**
	 * <strong>更新实体</strong>
	 * 
	 * @param entity
	 *            实体
	 * @return 成功true，失败false
	 */
	public boolean update(T entity) throws InnException;

	/**
	 * <strong>更新或保存实体(当使用merge方法，如出现此 An entity copy was already assigned to a
	 * different entity异常以及一些缓存错误时，可以尝试使用此方法)</strong>
	 * 
	 * @param entity
	 *            实体
	 * @return 成功true，失败false
	 */
	boolean saveOrUpdate(T entity) throws InnException;

	/**
	 * <strong>删除实体</strong>
	 * 
	 * @param entity
	 *            实体
	 */
	boolean delete(T entity) throws InnException;

	/**
	 * <strong>根据实体id删除实体</strong>
	 * 
	 * @param id
	 *            实体id
	 */
	boolean delete(PK id) throws InnException;

	/**
	 * <strong>返回实体集合</strong>
	 * 
	 * @return 实体集合List
	 */
	List<T> findAll() throws InnException;

	/**
	 * <strong>按Criterion查询实体集合</strong>
	 * 
	 * @param criterion
	 *            数量可变的Criterion
	 * @return List
	 */
	List<T> findByCriteria(Criterion... criterion) throws InnException;

	/**
	 * <strong>获取指定实体</strong>
	 * 
	 * @param id
	 *            实体id
	 * @return 实体
	 */
	T get(final PK id) throws InnException;

	/**
	 * <strong>按HQL查询对象列表</strong>
	 * 
	 * @param hql
	 *            查询HQL
	 * @param values
	 *            数量可变的参数
	 * @return List
	 */
	List<T> find(String hql, Object... values) throws InnException;

	/**
	 * <strong>按HQL查询符合条件的前N条记</strong>
	 * 
	 * @param hql
	 *            查询hql
	 * @param num
	 *            返回记录数
	 * @param values
	 *            数量可变的参数
	 * @return List
	 */
	List<T> findRecent(String hql, int num, Object... values) throws InnException;

	/**
	 * <strong>按属性获得实体集</strong>
	 * 
	 * @param propertyName
	 *            属性
	 * @param value
	 *            属性值
	 * @return List
	 */
	List<T> findByProperty(String propertyName, Object value) throws InnException;

	/**
	 * <strong>按属性获得唯一实体</strong>
	 * 如果没有返回的是null ,如果查找到一个那么返回该对象，如果有多个将会抛出query did not return a unique result 异常
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            属性值
	 * @return 实体
	 */
	T findUniqueByProperty(String propertyName, Object value) throws InnException;

	/**
	 * <strong>根据Criterion条件创建Criteria</strong>
	 * 
	 * @param criterions
	 *            任意数量的Criteria条件
	 * @return Criteria
	 */
	Criteria createCriteria(Criterion... criterions) throws InnException;

	/**
	 * <strong>根据查询HQL与参数列表创建Query对象,后续可进行更多处理辅助函数</strong>
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            参数列表
	 * @return Query
	 */
	Query createQuery(String hql, Object... values) throws InnException;

	/**
	 * @Title: executeUpdate
	 * @Description: <strong>清空回收站</strong>
	 * @param hql
	 * @param values
	 * @return 只要执行过程中没抛异常都会返回true，即便更新的记录不存在也返回true。
	 */
	boolean executeUpdate(String hql, Object... values) throws InnException;

	/** 执行sql更新 */
	boolean executeSQLUpdate(String sql) throws InnException;

	/**
	 * @param startIndex 从0开始的
	 * @param rowsPerPage
	 * @param hql
	 *            <strong>hql语句形式规定为："<font color="red
	 *            ">from Xx xx where xx.xx=? order by xx.xx</font>"</strong> 必须有order by
	 * @param isDesc
	 * @param C
	 * @param values
	 * @return
	 * @discript 返回映射实体的查询
	 */
	<C> PaginationSupport<C> pagedFind(int startIndex, int rowsPerPage, String hql, boolean isDesc,
			Class<C> C, Object... values) throws InnException;

	/**
	 * @param startIndex 从0开始的
	 * @param rowsPerPage
	 * @param hql
	 *            <strong>hql语句形式规定为："<font color="red
	 *            ">from Xx xx where xx.xx=? order by xx.xx</font>"</strong> 必须有order by
	 * @param isDesc
	 * @param C
	 * @param fields
	 * @param values
	 * @return
	 * @discript 自定义返回类型的查询
	 */
	<C> PaginationSupport<C> pagedCustomFind(int startIndex, int rowsPerPage, String hql,
			boolean isDesc, Class<C> C, String[] fields, Object... values) throws InnException;

	Session createSession() throws InnException;

	/**
	 * <strong>根据查询HQL语句返回分页对象</strong>
	 * 
	 * @param startIndex 从0开始的
	 *            分页的索引。
	 * @param rowsPerPage
	 *            每页显示条数
	 * @param hql
	 *            查询hql语句&nbsp; <strong>hql语句形式规定为："<font color="red
	 *            ">from Xx xx where xx.xx=? order by xx.xx</font>"</strong> 必须有order by
	 * @param isDesc
	 *            true=倒序，false=顺序
	 * @param values
	 *            任意数量的条件参数
	 * @return PaginationSupport
	 */
	@SuppressWarnings("rawtypes")
	PaginationSupport pagedFind(int startIndex, int rowsPerPage, String hql, boolean isDesc,
			Object... values) throws InnException;
	
	
	/**
	 * @param pageNum  请求的页码
	 * @param rowsPerpage 每页的记录数
	 * @param hql  hql<strong><font color="red">from Xx xx where xx.xx=? order by xx.xx</font></strong>
	 * @param values
	 * @return
	 * @throws InnException
	 */
	<C> JuiPaginationSupport<C> juiPageFind(int pageNum,int rowsPerpage,String hql,List<Object> values)throws InnException;
	
	
	/**
	 * @param hql
	 * @param values
	 * @return	注意的是如果没有查找到数据返回的是null。
	 * @throws InnException
	 */
	public Object executeHQLQuery(String hql, Object... values) throws InnException;
	
}
