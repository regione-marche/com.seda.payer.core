package com.seda.payer.core.handler.rest;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.seda.payer.core.handler.rest.routine.ERestRoutine;

public class RestResultSet implements ResultSet {

	private final ERestRoutine restRoutine;
	private final int resultSetIndex;
	private final List<Map<String, Object>> resultSetRows;
	private final Iterator<Map<String, Object>> iterator;
	private Map<String, Object> currentRow;
	
	public RestResultSet(ERestRoutine restRoutine, int resultSetIndex, List<Map<String, Object>> resultSetRows) {
		
		this.restRoutine = restRoutine;
		this.resultSetIndex = resultSetIndex;
		this.resultSetRows = resultSetRows;
		this.iterator = this.resultSetRows.iterator();
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
	public boolean next() throws SQLException {
		try {
			boolean next = iterator.hasNext();
			
			if (next) {
				currentRow = iterator.next();
				iterator.remove();
			} else {
				currentRow = null;
			}
			
			return next;
		} catch (Exception e) {
			throw new RestSQLException("Exception in next()", e);
		}
	}

	@Override
	public void close() throws SQLException {
		
	}

	@Override
	public boolean wasNull() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public String getString(int columnIndex) throws SQLException {
		
		try {
			return (String) currentRow.get(restRoutine.getResultSetsMap().get(resultSetIndex).get(columnIndex));
		} catch (Exception e) {
			throw new RestSQLException("Exception in getString(int columnIndex)", e);
		}
	}

	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public byte getByte(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public short getShort(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getInt(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public long getLong(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public float getFloat(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public double getDouble(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public byte[] getBytes(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Date getDate(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Time getTime(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public String getString(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean getBoolean(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public byte getByte(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public short getShort(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getInt(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public long getLong(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public float getFloat(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public double getDouble(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public byte[] getBytes(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Date getDate(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Time getTime(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Timestamp getTimestamp(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public InputStream getAsciiStream(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public InputStream getUnicodeStream(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public InputStream getBinaryStream(String columnLabel) throws SQLException {
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
	public String getCursorName() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		
		try {
			return new RestResultSetMetaData(restRoutine.getResultSetsMap().get(resultSetIndex));
		} catch (Exception e) {
			throw new RestSQLException("Exception in getMetaData()", e);
		}
	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		
		try {
			return currentRow.get(restRoutine.getResultSetsMap().get(resultSetIndex).get(columnIndex));
		} catch (Exception e) {
			throw new RestSQLException("Exception in getString(int columnIndex)", e);
		}
	}

	@Override
	public Object getObject(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int findColumn(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Reader getCharacterStream(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Reader getCharacterStream(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		
		try {
			Object obj = currentRow.get(restRoutine.getResultSetsMap().get(resultSetIndex).get(columnIndex));
			
			if (obj != null) {
				String simpleClassName = obj.getClass().getSimpleName();
				
				if (simpleClassName.equals(Integer.class.getSimpleName())) {
					return new BigDecimal((Integer) obj);
				} if (simpleClassName.equals(String.class.getSimpleName())) {
					return new BigDecimal((String) obj);
				}
				else {
					throw new RestSQLException(simpleClassName + " non gestita");
				}
			}
			
			return null;
			
		} catch (RestSQLException e) {
			throw e;
		} catch (Exception e) {
			throw new RestSQLException("Exception in getBigDecimal(int columnIndex)", e);
		}
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isAfterLast() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isFirst() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isLast() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void beforeFirst() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void afterLast() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean first() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean last() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getRow() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean absolute(int row) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean relative(int rows) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean previous() throws SQLException {
		throw new RestSQLException("metodo non implementato");
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
	public int getType() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getConcurrency() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean rowUpdated() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean rowInserted() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean rowDeleted() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNull(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBoolean(int columnIndex, boolean x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateByte(int columnIndex, byte x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateShort(int columnIndex, short x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateInt(int columnIndex, int x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateLong(int columnIndex, long x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateFloat(int columnIndex, float x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateDouble(int columnIndex, double x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateString(int columnIndex, String x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBytes(int columnIndex, byte[] x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateDate(int columnIndex, Date x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateTime(int columnIndex, Time x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateObject(int columnIndex, Object x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNull(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBoolean(String columnLabel, boolean x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateByte(String columnLabel, byte x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateShort(String columnLabel, short x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateInt(String columnLabel, int x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateLong(String columnLabel, long x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateFloat(String columnLabel, float x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateDouble(String columnLabel, double x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateString(String columnLabel, String x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBytes(String columnLabel, byte[] x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateDate(String columnLabel, Date x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateTime(String columnLabel, Time x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateObject(String columnLabel, Object x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void insertRow() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateRow() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void deleteRow() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void refreshRow() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void cancelRowUpdates() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void moveToInsertRow() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void moveToCurrentRow() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Statement getStatement() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Ref getRef(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Blob getBlob(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Clob getClob(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Array getArray(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Ref getRef(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Blob getBlob(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Clob getClob(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Array getArray(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Date getDate(String columnLabel, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Time getTime(String columnLabel, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public URL getURL(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public URL getURL(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateRef(int columnIndex, Ref x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateRef(String columnLabel, Ref x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBlob(int columnIndex, Blob x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBlob(String columnLabel, Blob x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateClob(int columnIndex, Clob x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateClob(String columnLabel, Clob x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateArray(int columnIndex, Array x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateArray(String columnLabel, Array x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public RowId getRowId(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public RowId getRowId(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateRowId(int columnIndex, RowId x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateRowId(String columnLabel, RowId x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public int getHoldability() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public boolean isClosed() throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNString(int columnIndex, String nString) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNString(String columnLabel, String nString) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public NClob getNClob(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public NClob getNClob(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public String getNString(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public String getNString(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateClob(int columnIndex, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateClob(String columnLabel, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNClob(int columnIndex, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public void updateNClob(String columnLabel, Reader reader) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

	@Override
	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
		throw new RestSQLException("metodo non implementato");
	}

}
