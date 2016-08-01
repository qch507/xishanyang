package com.xishanyang.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dataDao")
public class DataDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		// Session session = null;
		// try {
		// session = sessionFactory.getCurrentSession();
		// } catch (Exception e) {
		// session = sessionFactory.openSession();
		// }
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 删除数据库中添加对象记录
	 * 
	 * @param 需要添加的对象
	 */
	public void addObject(Object o) {
		getSession().save(o);
	}

	/**
	 * 删除数据库中指定对象
	 * 
	 * @param 需要删除的对象
	 */
	public void deleteObject(Object o) {
		getSession().delete(o);
	}

	/**
	 * 更新数据库中指定对象
	 * 
	 * @param 需要更新的对象
	 */
	public void updateObject(Object o) {
		getSession().update(o);
	}

	/**
	 * 更新数据库中指定对象，如果对象记录不存在，则插入新的
	 * 
	 * @param 需要更新的对象
	 */
	public void saveOrUpdateObject(Object o) {
		getSession().saveOrUpdate(o);
	}

	/**
	 * 获取整张表的记录集合
	 * 
	 * @param 对象类型
	 * @return 对象列表集合
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getAllObject(Class<T> c) {
		return (List<T>) getSession().createQuery("from " + c.getName()).list();
	}

	/**
	 * 根据id查询对象记录
	 * 
	 * @param 查询对象类型
	 * @param 查询对象ID
	 * @return 查询对象结果
	 */
	public <T> T getObjectById(Class<T> c, Serializable id) {
		return (T) getSession().get(c, id);
	}

	/**
	 * 根据id删除对象记录
	 * 
	 * @param 删除对象类型
	 * @param 删除对象ID
	 */
	public <T> void deleteObjectById(Class<T> c, Serializable id) {
		deleteObject(getObjectById(c, id));
	}

	/**
	 * 条件检索对象数据集
	 * 
	 * @param 检索hql语句
	 * @param 条件名称
	 * @param 条件值
	 * @return 检索结果数据集
	 */
	public List<?> getObjectsViaParam(String hql, String[] params, Object... p) {
		Query query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < p.length; i++) {
				query.setParameter(params[i], p[i]);
			}
		}
		return query.list();
	}

	/**
	 * 条件检索对象数据集(省略参数名)
	 * 
	 * @deprecated 使用 {@link DataDao#getObjectsViaParam(String, String[], Object...)}
	 * @param 检索hql语句
	 * @param 条件值
	 * @return 检索结果数据集
	 */
	public List<?> getObjectByCondition(String hql, Object... p) {
		String[] params = new String[p.length];
		for (int i = 0; i < p.length; i++) {
			params[i] = "p" + i;
			hql = hql.replaceFirst("\\?", ":" + params[i]);
		}
		return getObjectsViaParam(hql, params, p);
	}

	/**
	 * @deprecated 按条件查询数据条数,尽量使用{@link DataDao#getCount(String, Object...)}
	 * @param hql
	 *            查询hql语句
	 * @param p
	 *            条件值
	 * @return 查询条数
	 */
	public int getQueryCount(String hql, Object... p) {
		return getObjectByCondition(hql, p).size();
	}

	/**
	 * 按条件查询数据条数
	 * 
	 * @param hql
	 *            查询hql语句，select count 检索
	 * @param p
	 *            条件值
	 * @return 查询条数
	 */
	public long getCount(String hql, Object... p) {
		return (Long) getObjectByCondition(hql, p).listIterator().next();
	}

	/**
	 * 分页查询数据集
	 * 
	 * @deprecated 使用 {@link DataDao#pageQueryViaParam(String, Integer, Integer, String[], Object...)}
	 * @param hql
	 *            查询hql语句
	 * @param pageSize
	 *            每页条数
	 * @param page
	 *            当前页数，从1开始
	 * @param p
	 *            条件值
	 * @return 检索结果数据集
	 */
	public List<?> pageQuery(String hql, Integer pageSize, Integer page, Object... p) {
		String[] params = new String[p.length];
		for (int i = 0; i < p.length; i++) {
			params[i] = ":p" + i;
			hql = hql.replaceFirst("?", params[i]);
		}
		return pageQueryViaParam(hql, pageSize, page, params, p);
	}

	/**
	 * 分页查询数据集
	 * 
	 * @param hql
	 *            查询hql语句
	 * @param pageSize
	 *            每页条数
	 * @param page
	 *            当前页数，从1开始
	 * @param params
	 *            条件名称数组
	 * @param p
	 *            条件值
	 * @return 检索结果数据集
	 */
	public List<?> pageQueryViaParam(String hql, Integer pageSize, Integer page, String[] params, Object... p) {
		Query query = getSession().createQuery(hql);
		if (p != null) {
			for (int i = 0; i < p.length; i++) {
				query.setParameter(params[i], p[i]);
			}
		}
		if (pageSize != null && pageSize > 0 && page != null && page > 0) {
			query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize);
		}
		return query.list();
	}

	/**
	 * 按条件查询单条数据
	 * 
	 * @deprecated 使用 {@link DataDao#getFirstObjectViaParam(String, String[], Object...)}
	 * @param hql
	 *            查询hql语句
	 * @param p
	 *            条件值
	 * @return 检索结果数据集
	 */
	public Object getFirstObject(String hql, Object... p) {
		String[] params = new String[p.length];
		for (int i = 0; i < p.length; i++) {
			params[i] = ":p" + i;
			hql = hql.replaceFirst("?", params[i]);
		}
		return getFirstObjectViaParam(hql, params, p);
	}

	/**
	 * 按条件查询单条数据
	 * 
	 * @param hql
	 *            查询hql语句
	 * @param params
	 *            条件名称数组
	 * @param p
	 *            条件值
	 * @return 检索结果数据集
	 */
	public Object getFirstObjectViaParam(String hql, String[] params, Object... p) {
		List<?> list = getObjectsViaParam(hql, params, p);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 按条件批量删除数据
	 * 
	 * @deprecated 使用 {@link DataDao#deleteObjectByCondition(String, String[], Object...)}
	 * @param hql
	 *            删除hql语句，delete from 开头
	 * @param p
	 *            条件值
	 */
	public void deleteObjectByCondition(String hql, Object... p) {
		String[] params = new String[p.length];
		for (int i = 0; i < p.length; i++) {
			params[i] = ":p" + i;
			hql = hql.replaceFirst("?", params[i]);
		}
		deleteObjectsViaParam(hql, params, p);
	}

	/**
	 * 按条件批量删除数据
	 * 
	 * @param hql
	 *            删除hql语句，delete from 开头
	 * @param params
	 *            条件名称数组
	 * @param p
	 *            条件值
	 */
	public void deleteObjectsViaParam(String hql, String[] params, Object... p) {
		Query query = getSession().createQuery(hql);
		if (p != null) {
			for (int i = 0; i < p.length; i++) {
				query.setParameter(params[i], p[i]);
			}
		}
		query.executeUpdate();
	}

	/**
	 * 用SQL文批量删除数据集
	 * 
	 * @param hql
	 *            删除sql语句
	 */
	public void deleteBySql(String sql) {
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	/**
	 * 执行sql
	 * 
	 * @param sql
	 *            执行sql语句
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> executeSql(String sql) {
		SQLQuery query = getSession().createSQLQuery(sql);
		return query.list();
	}

	public List<?> sqlQuery(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.list();
	}

	public int alterBySql(String sql) {
		SQLQuery query = getSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

	public Object[] queryBysql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		List<?> list = query.list();
		if (list != null && list.size() > 0) {
			return (Object[]) query.list().get(0);
		}
		return null;
	}
}
