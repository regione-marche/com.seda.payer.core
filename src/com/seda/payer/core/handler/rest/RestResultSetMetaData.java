package com.seda.payer.core.handler.rest;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class RestResultSetMetaData implements ResultSetMetaData {

	private final Map<Integer, String> resultSetMap;
	
	public RestResultSetMetaData(Map<Integer, String> resultSetMap) {
		
		this.resultSetMap = resultSetMap;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getColumnCount() throws SQLException {
		
		return resultSetMap.size();
	}

	@Override
	public boolean isAutoIncrement(int column) throws SQLException {
		
		return false;
	}

	@Override
	public boolean isCaseSensitive(int column) throws SQLException {
		
		return false;
	}

	@Override
	public boolean isSearchable(int column) throws SQLException {
		
		return false;
	}

	@Override
	public boolean isCurrency(int column) throws SQLException {
		
		return false;
	}

	@Override
	public int isNullable(int column) throws SQLException {
		
		return 0;
	}

	@Override
	public boolean isSigned(int column) throws SQLException {
		
		return false;
	}

	@Override
	public int getColumnDisplaySize(int column) throws SQLException {
		
		return 0;
	}

	@Override
	public String getColumnLabel(int column) throws SQLException {
		
		return resultSetMap.get(column);
	}

	@Override
	public String getColumnName(int column) throws SQLException {
		
		return resultSetMap.get(column);
	}

	@Override
	public String getSchemaName(int column) throws SQLException {
		
		return "";
	}

	@Override
	public int getPrecision(int column) throws SQLException {
		
		return 0;
	}

	@Override
	public int getScale(int column) throws SQLException {
		
		return 0;
	}

	@Override
	public String getTableName(int column) throws SQLException {
		
		return "";
	}

	@Override
	public String getCatalogName(int column) throws SQLException {
		
		return "";
	}

	@Override
	public int getColumnType(int column) throws SQLException {
		
		return Types.VARCHAR;
	}

	@Override
	public String getColumnTypeName(int column) throws SQLException {
		
		return "VARCHAR";
	}

	@Override
	public boolean isReadOnly(int column) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isWritable(int column) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isDefinitelyWritable(int column) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public String getColumnClassName(int column) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

}
