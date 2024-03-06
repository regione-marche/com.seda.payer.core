package com.seda.data.logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.reflection.ExceptionUtil;
import com.seda.data.procedure.wrapper.oracle.CallableStatementWrapper;
/**
 * PreparedStatement proxy to add logging
 */
public class PreparedStatementLogger extends JdbcLogger implements InvocationHandler {

	private static final LoggerWrapper log =  CustomLoggerManager.get(PreparedStatementLogger.class);

	private PreparedStatement statement;
	private String sql;

	private PreparedStatementLogger(PreparedStatement stmt, String sql) {
		this.statement = stmt;
		this.sql = sql;
	}

	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		try {
			if (EXECUTE_METHODS.contains(method.getName())) {
if (log.isDebugEnabled()) {
				log.debug("==>  Executing: " + removeExcessiveBlank(sql));
				log.debug("==> Parameters: " + getParameterValueString());
}
				clearColumnInfo();
				if ("executeQuery".equals(method.getName())) {
					ResultSet rs = (ResultSet) method.invoke(statement, params);
					if (rs != null) {
						return ResultSetLogger.getInstance(rs);
					} else {
						return null;
					}
				} else {
					return method.invoke(statement, params);
				}
			} else if (SET_METHODS.contains(method.getName())) {
				if ("setNull".equals(method.getName())) {
					setColumn(params[0], null);
				} else {
					setColumn(params[0], params[1]);
				}
				return method.invoke(statement, params);
			} else if ("getResultSet".equals(method.getName())) {
				ResultSet rs = (ResultSet) method.invoke(statement, params);
				if (rs != null) {
					return ResultSetLogger.getInstance(rs);
				} else {
					return null;
				}
			} else if ("equals".equals(method.getName())) {
				Object ps = params[0];
				return ps instanceof Proxy && proxy == ps;
			} else if ("hashCode".equals(method.getName())) {
				return proxy.hashCode();
			} else {
				return method.invoke(statement, params);
			}
		} catch (Throwable t) {
			throw ExceptionUtil.unwrapThrowable(t);
		}
	}

	/**
	 * Creates a logging version of a PreparedStatement
	 *
	 * @param stmt - the statement
	 * @param sql  - the sql statement
	 * @return - the proxy
	 */
	public static PreparedStatement newInstance(PreparedStatement stmt, String sql) {
		InvocationHandler handler = new PreparedStatementLogger(stmt, sql);
		ClassLoader cl = PreparedStatement.class.getClassLoader();
		return (PreparedStatement) Proxy.newProxyInstance(cl, new Class[]{PreparedStatement.class}, handler);
	}

	/**
	 * Return the wrapped prepared statement
	 *
	 * @return the PreparedStatement
	 */
	public PreparedStatement getPreparedStatement() {
		return statement;
	}

}
