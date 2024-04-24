package com.seda.payer.core.handler.rest;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.payer.core.handler.rest.routine.ERestRoutine;

public class RestCallableStatement implements CallableStatement {

	private static final String RESPONSE_KEY = "Response";
	private static final String RESULT_SETS_KEY = "Resultsets";
	
	private final String baseUrl;
	@SuppressWarnings("unused")
	private final String schema; // non necessario perche ce un baseUrl per ogni schema
	private final ERestRoutine restRoutine;
	
	private final Map<Integer, Object> inputDataMap;
	
	private Map<String, Object> outputDataMap;
	private List<Map<String, Object>> resultSets;
	private int currentResultSetIndex = 0;

	private String methodRest = "POST";

    private String restService = "CITYMAT";
	
	protected LoggerWrapper logger = CustomLoggerManager.get(RestCallableStatement.class);

	public RestCallableStatement(String baseUrl, String schema, ERestRoutine restRoutine) {
		
		this.baseUrl = baseUrl;
		this.schema = schema;
		this.restRoutine = restRoutine;
		this.inputDataMap = new HashMap<Integer, Object>();
	}

	public RestCallableStatement(String baseUrl, String schema, ERestRoutine restRoutine, String methodRest, String restService) {
        this(baseUrl, schema, restRoutine);

		this.methodRest = methodRest;

        this.restService = restService;

	}


	@Override
	public ResultSet executeQuery() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int executeUpdate() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		
		inputDataMap.put(parameterIndex, x);
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		
		inputDataMap.put(parameterIndex, x);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {

		inputDataMap.put(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void clearParameters() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean execute() throws SQLException {
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		Client client = null;
		try {
			client = ClientBuilder.newClient();
			return Optional.of(client)
				.map(c -> {
					try {
						Entity<Map<String, Object>> entity = Entity.entity(getEntity(), MediaType.APPLICATION_JSON);
						try {
							logger.info(new ObjectMapper().writeValueAsString(entity));
							System.out.println(new ObjectMapper().writeValueAsString(entity));
						} catch (JsonProcessingException e) {}
						if (this.methodRest.equals("POST") ) {
							System.out.println("Chiamata con metodo POST");
							return c.target(baseUrl).path(restRoutine.getRoutine()).request(MediaType.APPLICATION_JSON).post(entity);
						} else if (this.methodRest.equals("PUT") ) {
							System.out.println("Chiamata con metodo PUT");
							return c.target(baseUrl).path(restRoutine.getRoutine()).request(MediaType.APPLICATION_JSON).put(entity);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
					return null;
				})
				.map(response -> {
					try {
						if (restService.equals("CITYMAT")) {
							System.out.println("Risposta CITYMAT");
							System.out.println(response.toString());
							return checkResponse(response);
						}
						else if (restService.equals("SEPA")) {
							System.out.println("Risposta SEPA");
							System.out.println(response.toString());
							return checkResponseSEPA(response);
						}
						else
							throw new RestSQLException("Servizio non supportato");
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				})
				.get();
		} catch (Throwable e) {
			throw new RestSQLException("Exception in execute()", e);
		} finally {
			if (client != null) try { client.close(); } catch (Exception ex) { }
		}
	}

	@Override
	public void addBatch() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void close() throws SQLException {
		
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getMaxRows() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void cancel() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void clearWarnings() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}
	
	@Override
	public ResultSet getResultSet() throws SQLException {
		
		try {

			if (resultSets != null && !resultSets.isEmpty()) {
				if (restService.equals("CITYMAT")) {
					Iterator<Map<String, Object>> iterator = resultSets.iterator();

					while (iterator.hasNext()) {

						Map<String, Object> map = (Map<String, Object>) iterator.next();


							if (map != null && map.containsKey("Resultset " + currentResultSetIndex)) {

								iterator.remove();
								@SuppressWarnings("unchecked")
								List<Map<String, Object>> resultSetRows = (List<Map<String, Object>>) map.get("Resultset " + currentResultSetIndex);
								RestResultSet restResultSet = new RestResultSet(restRoutine, currentResultSetIndex, resultSetRows);
								currentResultSetIndex++;
								return restResultSet;
							}

					}
				}else if (restService.equals("SEPA")){
					return new RestResultSet(restRoutine, currentResultSetIndex, resultSets);

				}
			}

			return null;
		} catch (Exception e) {
			throw new RestSQLException("Exception in getResultSet()", e);
		}
	}

	@Override
	public int getUpdateCount() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		
		try {
			return resultSets != null && !resultSets.isEmpty();
		} catch (Exception e) {
			throw new RestSQLException("Exception in getMoreResults()", e);
		}
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getFetchDirection() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getFetchSize() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getResultSetType() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void clearBatch() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int[] executeBatch() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Connection getConnection() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isClosed() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isPoolable() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		throw new RestSQLException("metodo non implementato");
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
	public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
		//TODO

	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean wasNull() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public String getString(int parameterIndex) throws SQLException {
		
		try {
			
			String value = null;
			
			String key = restRoutine.getOutParameterMap().get(parameterIndex);
			
			if (key.contains(".")) {
				
				String[] keyArray = key.split("\\.");
				
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) outputDataMap.get(keyArray[0]);
				
				value = (String) map.get(keyArray[1]);
				if (value == null) value = "";
			} else {
				
				value = (String) outputDataMap.get(key);
			}
			
			return value;
		} catch (Exception e) {
			throw new RestSQLException("Exception in getString(int parameterIndex)", e);
		}
	}

	@Override
	public boolean getBoolean(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public byte getByte(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public short getShort(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getInt(int parameterIndex) throws SQLException {
		//outputDataMap.get("O_HV_SQLCODE")
		return ((Integer) outputDataMap.get(this.restRoutine.getOutParameterMap().get(parameterIndex - inputDataMap.size()))).intValue();
		//return (int) outputDataMap.get(String.valueOf(parameterIndex - inputDataMap.size()));


	}

	@Override
	public long getLong(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public float getFloat(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public double getDouble(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public byte[] getBytes(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Date getDate(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Time getTime(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Object getObject(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
		
		try {
			Object obj = outputDataMap.get(restRoutine.getOutParameterMap().get(parameterIndex));
			
			if (obj != null) {
				String simpleClassName = obj.getClass().getSimpleName();
				
				if (simpleClassName.equals(Integer.class.getSimpleName())) {
					return new BigDecimal((Integer) obj);
				} else if (simpleClassName.equals(Long.class.getSimpleName())) {
					return new BigDecimal((Long) obj);
				} else if (simpleClassName.equals(String.class.getSimpleName())) {
					return new BigDecimal((Integer) obj);
				} else if (simpleClassName.equals(BigDecimal.class.getSimpleName())) {
					return (BigDecimal)obj;
				} else {
					throw new RestSQLException(simpleClassName + " non gestita");
				}
			}
			
			return null;
			
		} catch (RestSQLException e) {
			throw e;
		} catch (Exception e) {
			throw new RestSQLException("Exception in getBigDecimal(int parameterIndex)", e);
		}
	}

	@Override
	public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Ref getRef(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Blob getBlob(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Clob getClob(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Array getArray(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public URL getURL(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setURL(String parameterName, URL val) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNull(String parameterName, int sqlType) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBoolean(String parameterName, boolean x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setByte(String parameterName, byte x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setShort(String parameterName, short x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setInt(String parameterName, int x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setLong(String parameterName, long x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setFloat(String parameterName, float x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setDouble(String parameterName, double x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setString(String parameterName, String x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBytes(String parameterName, byte[] x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setDate(String parameterName, Date x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setTime(String parameterName, Time x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setObject(String parameterName, Object x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public String getString(String key) throws SQLException {

		try {
			
			String value = null;
			
			if (key.contains(".")) {
				
				String[] keyArray = key.split("\\.");
				
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) outputDataMap.get(keyArray[0]);
				
				value = (String) map.get(keyArray[1]);
				if (value == null) value = "";
			} else {
				
				value = (String) outputDataMap.get(key);
			}
			
			return value;
		} catch (Exception e) {
			throw new RestSQLException("Exception in getString(int parameterIndex)", e);
		}


	}

	@Override
	public boolean getBoolean(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public byte getByte(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public short getShort(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getInt(String parameterName) throws SQLException {
		return Integer.parseInt(getString(parameterName));

		//throw new RestSQLException("metodo non implementato");
	}

	@Override
	public long getLong(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public float getFloat(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public double getDouble(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public byte[] getBytes(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Date getDate(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Time getTime(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Timestamp getTimestamp(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Object getObject(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public BigDecimal getBigDecimal(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Ref getRef(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Blob getBlob(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Clob getClob(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Array getArray(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Date getDate(String parameterName, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Time getTime(String parameterName, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public URL getURL(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public RowId getRowId(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public RowId getRowId(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setRowId(String parameterName, RowId x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNString(String parameterName, String value) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNClob(String parameterName, NClob value) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setClob(String parameterName, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public NClob getNClob(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public NClob getNClob(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public SQLXML getSQLXML(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public SQLXML getSQLXML(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public String getNString(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public String getNString(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Reader getNCharacterStream(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Reader getCharacterStream(int parameterIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Reader getCharacterStream(String parameterName) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBlob(String parameterName, Blob x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setClob(String parameterName, Clob x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setClob(String parameterName, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void setNClob(String parameterName, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}
	
	private Map<String, Object> getEntity() throws SQLException {
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			
			for (Entry<Integer, String> entry : restRoutine.getInParameterMap().entrySet()) {
				
				String key = entry.getValue();
				Object value = null;
				
				if (key.contains(".")) {
					
					String[] keyArray = key.split("\\.");
					
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put(keyArray[1], inputDataMap.get(entry.getKey()));
					
					key = keyArray[0];
					value = map2;
					
				} else {
					value = inputDataMap.get(entry.getKey());
				}
				
				map.put(key, value);
			}
			
			return map;
		} catch (Exception e) {
			throw new RestSQLException("Exception in getEntity()", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean checkResponse(Response response) throws SQLException {
		
		try {
			Map<String, Object> responseEntity = null;
			
			if (response.getStatus() < 300) {
				
				responseEntity = Optional.of(response)
					.map(r -> response.readEntity(String.class))
					.map(e -> {
						try {
							logger.info(e);
						} catch (Exception ex) {}
						try {
							return new ObjectMapper().readValue(e, HashMap.class);
						} catch (Exception e1) {
							throw new RuntimeException(e1);
						}
					})
					.get();

				if (responseEntity.containsKey(RESPONSE_KEY)) {
					outputDataMap = (Map<String, Object>) responseEntity.get(RESPONSE_KEY);
					if (outputDataMap.containsKey(RESULT_SETS_KEY)) {
						resultSets = (List<Map<String, Object>>) outputDataMap.get(RESULT_SETS_KEY);
					}
				} else {
					throw new RestSQLException("La response non contiene la chiave '" + RESPONSE_KEY + "'");
				}
			}
			
			return resultSets != null && !resultSets.isEmpty();
		} catch (Exception e) {
			throw new RestSQLException("Exception in checkResponse(Response response)", e);
		}
	}


    @SuppressWarnings("unchecked")
	private boolean checkResponseSEPA(Response response) throws SQLException {
        try {
            if (response.getStatus() < 300) {

                return Optional.of(response)
                        .map(r -> response.readEntity(String.class))
                        .map(e -> {
                            try {
                                logger.info(e);
                            } catch (Exception ex) {}
                            try {
                               	if (methodRest.equals("POST") ) {
									//TODO il valore di ritorno è simile a questo oggetto {}
									outputDataMap =	new ObjectMapper().readValue(e, HashMap.class);
									return true;
								} else if (methodRest.equals("PUT") ) {
									// il valore di ritorno è simile a questo oggetto [{}]

									outputDataMap =	new ObjectMapper().readValue(e, HashMap.class);
									resultSets = (List<Map<String, Object>>) outputDataMap.get("ResultSet");

									outputDataMap.remove("ResultSet");
								//	outputDataMap.entrySet().removeIf(entry -> entry.getKey().startsWith("I_"));

										/*
										* {
										"I_DAU_CUTECUTE": "000TO",
										"I_DAU_CDCSCSIA": "AASCE",
										"I_DAU_CDAUTPAU": "4",
										"I_DAU_CDAUCOAU": "082010G561074222",
										"O_DVI_CDVIVOCI": "BORSELTO",
										"O_DCS_CDVCABIA": "02008",
										"O_MESSAGE": "",
										"ResultSet": [
											{
												"DAU_CDCSCSIA": "AASCE",
												"DAU_CDAUTPAU": "4",
												"DAU_CDAUCOAU": "082010G561074222",
												"DAU_FDAUSTAT": "NON",
												"DAU_GDAUVARI": ""
											}
										]
										}*/

									return resultSets != null && !resultSets.isEmpty();
								}
                            } catch (Exception e1) {
                                throw new RuntimeException(e1);
                            }
                            return false;
                        })
                        .get();
            }
        } catch (Exception e) {
            throw new RestSQLException("Exception in checkResponse(Response response)", e);
        }
		return false;
    }

	public String getRestService() {
		return restService;
	}
}
