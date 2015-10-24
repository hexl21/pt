package com.xl.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xl.dao.BaseDaoI;
import com.xl.pageModel.base.ReturnValue;
import com.xl.pageModel.base.SessionInfo;
import com.xl.utils.StringUtil;


@SuppressWarnings("unchecked")
@Repository
public class BaseDaoImpl<T> implements BaseDaoI<T> {

	@Autowired
	private SessionFactory sessionFactory;

	

	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public Serializable save(T o) {
		if (o != null) {
			return this.getCurrentSession().save(o);
		}
		return null;
	}

	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	@Override
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if ((l != null) && (l.size() > 0)) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if ((l != null) && (l.size() > 0)) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(T o) {
		if (o != null) {
			this.getCurrentSession().delete(o);
		}
	}

	@Override
	public void update(T o) {
		if (o != null) {
			this.getCurrentSession().update(o);
		}
	}

	@Override
	public void saveOrUpdate(T o) {
		if (o != null) {
			this.getCurrentSession().saveOrUpdate(o);
		}
	}

	@Override
	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public List<Object[]> findBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.list();
	}

	@Override
	public List<Object[]> findBySql(String sql, int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public List<Object[]> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public List<Object[]> findBySql(String sql, Map<String, Object> params,
			int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public int executeSql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public BigInteger countBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return (BigInteger) q.uniqueResult();
	}

	@Override
	public BigInteger countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if ((params != null) && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (BigInteger) q.uniqueResult();
	}
	/**
	 * 调用过程
	 * @param procedure
	 * @param parameter
	 * @return
	 */
	@Override
	public ReturnValue callProcedure(final String procedure, final List parameter){
		return this.getCurrentSession().doReturningWork(new ReturningWork<ReturnValue>() {
			public ReturnValue execute(Connection con){
				ReturnValue returnValue = new ReturnValue();
				String vCall = (new StringBuilder("{call ")).append(procedure).append(
				"(").toString();
				for (int i = 0; i < parameter.size(); i++)
					vCall = (new StringBuilder(String.valueOf(vCall))).append("'")
					.append(parameter.get(i).toString()).append("',")
					.toString();
				
				vCall = (new StringBuilder(String.valueOf(vCall))).append("?,?)}")
				.toString();
				int cstmtCode = 0;
				String cstmtMessage = null;
				CallableStatement cstmt = null;
				try {
					cstmt = con.prepareCall(vCall);
					cstmt.registerOutParameter(1, 4);
					cstmt.registerOutParameter(2, 12);
					cstmt.execute();
					cstmtCode = cstmt.getInt(1);
					cstmtMessage = cstmt.getString(2);
					returnValue.setReturnInt(cstmtCode);
					returnValue.setReturnString(cstmtMessage);
				} catch (SQLException e) {
				} finally {
					try {
						cstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return returnValue;
			}
		});
	}
	
	/**
	 * 数据库sessionInfo信息
	 * @param procedure
	 * @param parameter
	 * @param sessionInfo
	 * @return
	 */
	@Override
	public ReturnValue callProcedureWithSession(String procedure, List parameter,final SessionInfo sessionInfo) {
		return this.getCurrentSession().doReturningWork(new ReturningWork<ReturnValue>() {
			public ReturnValue execute(Connection con){
				ReturnValue returnValue = new ReturnValue();
				if (sessionInfo == null || sessionInfo.getId() == null){}	
				int cstmtCode = 0;
				String cstmtMessage = null;
				String longUserId = String.valueOf(sessionInfo.getId());
				String strUserIp = sessionInfo.getIp();
				String currentUserRoleId = sessionInfo.getCurrentUserRoleId();
				boolean boolIsSqlException = false;
				String strExceptionMessage = new String();
				String vCall = "{call Get_Session_Info.set_User_Info (";
				vCall = (new StringBuilder(String.valueOf(vCall))).append("'").append(
						longUserId).append("',").toString();
				vCall = (new StringBuilder(String.valueOf(vCall))).append("'").append(
						strUserIp).append("',").toString();
				vCall = (new StringBuilder(String.valueOf(vCall))).append("'").append(
						StringUtil.replaceNull2Space(currentUserRoleId)).append("',")
						.toString();
				vCall = (new StringBuilder(String.valueOf(vCall))).append("'").append(
						StringUtil.replaceNull2Space(String.valueOf(sessionInfo
								.getUserDeptId()))).append("',").toString();
				vCall = (new StringBuilder(String.valueOf(vCall))).append("'").append(
						StringUtil.replaceNull2Space(String.valueOf(sessionInfo
								.getEntityDeptId()))).append("',").toString();
				vCall = (new StringBuilder(String.valueOf(vCall))).append("?,?)}")
						.toString();
				CallableStatement cstmt = null;
				try {
					cstmt = con.prepareCall(vCall);
					cstmt.registerOutParameter(1, 4);
					cstmt.registerOutParameter(2, 12);
					cstmt.execute();
					cstmtCode = cstmt.getInt(1);
					cstmtMessage = cstmt.getString(2);
					returnValue.setReturnInt(cstmtCode);
					returnValue.setReturnString(cstmtMessage);
				} catch (SQLException e) {
					boolIsSqlException = true;
					strExceptionMessage = e.getMessage();
				} finally {
					try {
						cstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return returnValue;
			}
		});
	}
}
