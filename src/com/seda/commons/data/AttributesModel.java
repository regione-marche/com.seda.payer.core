/**
 * 
 */
package com.seda.commons.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author f.ricci
 *
 */
@SuppressWarnings("serial")
public class AttributesModel implements Serializable {

	private Map<String, Object> attributes;
	private Map<String, Class<?>> attributeClasses;
	
	public Map<String, Object> getAttributes(){
		if (attributes == null) attributes = new HashMap<String, Object>();
		return attributes;
	}
	
	private Map<String, Class<?>> getClasses(){
		if (attributeClasses == null) attributeClasses = new HashMap<String, Class<?>>();
		return attributeClasses;
	}	
	
	public void setBigDecimalAttribute(String attr, java.math.BigDecimal value) {
		safeSetAttribute(attr,value);
	}
	public void setBigIntegerAttribute(String attr, java.math.BigInteger value) {
		safeSetAttribute(attr,value);
	}
	public void setBooleanAttribute(String attr, Boolean value) {
		safeSetAttribute(attr,value);
	}
	public void setCharAttribute(String attr, char value) {
		safeSetAttribute(attr,value);
	}	
	public void setStringAttribute(String attr, String value) {
		safeSetAttribute(attr,value);
	}
	public void setIntegerAttribute(String attr, Integer value) {
		safeSetAttribute(attr,value);
	}
	public void setLongAttribute(String attr, Long value) {
		safeSetAttribute(attr,value);
	}
	public void setShortAttribute(String attr, Short value) {
		safeSetAttribute(attr,value);
	}
	public void setFloatAttribute(String attr, Short value) {
		safeSetAttribute(attr,value);
	}
	public void setDoubleAttribute(String attr, Short value) {
		safeSetAttribute(attr,value);
	}
	public void setDateAttribute(String attr, java.util.Date value) {
		safeSetAttribute(attr,value);
	}
	public void setCalendarAttribute(String attr, java.util.Calendar value) {
		safeSetAttribute(attr,value);
	}	
	public void setSqlDateAttribute(String attr, java.sql.Date value) {
		safeSetAttribute(attr,value);
	}						
	public void setSqlTimeAttribute(String attr, java.sql.Time value) {
		safeSetAttribute(attr,value);
	}
	public void setSqlTimestampAttribute(String attr, java.sql.Timestamp value) {
		safeSetAttribute(attr,value);
	}		
	public void setAttribute(String attr, Object value) {
		safeSetAttribute(attr,value);
	}

	public Object getAttribute(String attr) {
		return getAttributes().get(attr);
	}
	
	public <T> T getAttribute(Class<T> type, String attr, T def) {
		Object o = getAttribute(attr);
		if (o!=null) {
			return (T)o;
		}
		return def;
	}

	public java.math.BigDecimal getBigDecimalAttribute(String attr, java.math.BigDecimal def) {
		return getAttribute(java.math.BigDecimal.class, attr, def);
	}
	public java.math.BigDecimal getBigDecimalAttribute(String attr) {
		return getAttribute(java.math.BigDecimal.class, attr, null);
	}
	public java.math.BigInteger getBigIntegerAttribute(String attr, java.math.BigInteger def) {
		return getAttribute(java.math.BigInteger.class, attr, def);
	}
	public java.math.BigInteger getBigIntegerAttribute(String attr) {
		return getAttribute(java.math.BigInteger.class, attr, null);
	}
	public Boolean getBooleanAttribute(String attr, Boolean def) {
		return getAttribute(Boolean.class, attr, def);
	}
	public Boolean getBooleanAttribute(String attr) {
		return getAttribute(Boolean.class, attr, null);
	}	
	public char getCharAttribute(String attr) {
		return getAttribute(char.class, attr, null);
	}	
	public String getStringAttribute(String attr, String def) {
		return getAttribute(String.class, attr, def);
	}
	public String getStringAttribute(String attr) {
		return getAttribute(String.class, attr, null);
	}
	public Integer getIntegerAttribute(String attr, Integer def) {
		return getAttribute(Integer.class, attr, def);
	}
	public Integer getIntegerAttribute(String attr) {
		return getAttribute(Integer.class, attr, null);
	}
	public Long getLongAttribute(String attr, Long def) {
		return getAttribute(Long.class, attr, def);
	}
	public Long getLongAttribute(String attr) {
		return getAttribute(Long.class, attr, null);
	}	
	public Short getShortAttribute(String attr, Short def) {
		return getAttribute(short.class, attr, def);
	}
	public Short getShortAttribute(String attr) {
		return getAttribute(short.class, attr, null);
	}	
	public Float getFloatAttribute(String attr, Float def) {
		return getAttribute(Float.class, attr, def);
	}
	public Float getFloatAttribute(String attr) {
		return getAttribute(Float.class, attr, null);
	}	
	public Double getDoubleAttribute(String attr, Double def) {
		return getAttribute(Double.class, attr, def);
	}
	public Double getDoubleAttribute(String attr) {
		return getAttribute(Double.class, attr, null);
	}	
	public java.util.Date getDateAttribute(String attr, java.util.Date def) {
		return getAttribute(java.util.Date.class, attr, def);
	}
	public java.util.Date getDateAttribute(String attr) {
		return getAttribute(java.util.Date.class, attr, null);
	}	
	public java.util.Calendar getCalendarAttribute(String attr, java.util.Calendar def) {
		return getAttribute(java.util.Calendar.class, attr, def);
	}
	public java.util.Calendar getCalendarAttribute(String attr) {
		return getAttribute(java.util.Calendar.class, attr, null);
	}		
	public java.sql.Date getSqlDateAttribute(String attr, java.sql.Date def) {
		return getAttribute(java.sql.Date.class, attr, def);
	}
	public java.sql.Date getSqlDateAttribute(String attr) {
		return getAttribute(java.sql.Date.class, attr, null);
	}							
	public java.sql.Time getSqlTimeAttribute(String attr, java.sql.Time def) {
		return getAttribute(java.sql.Time.class, attr, def);
	}
	public java.sql.Time getSqlTimeAttribute(String attr) {
		return getAttribute(java.sql.Time.class, attr, null);
	}	
	public java.sql.Timestamp getSqlTimestampAttribute(String attr, java.sql.Timestamp def) {
		return getAttribute(java.sql.Timestamp.class, attr, def);
	}
	public java.sql.Timestamp getSqlTimestampAttribute(String attr) {
		return getAttribute(java.sql.Timestamp.class, attr, null);
	}	
	
	public Class<?> getAttributeClass(String attr) {
		return getClasses().get(attr);
	}		

	private void safeSetAttribute(String attr, Object value) {
		if (value!=null) {
			Class<?> c = value.getClass();
			if (getAttributes().containsKey(attr)) {
				Class<?> cx = getAttributeClass(attr);
				if (cx.equals(c)) {
					getAttributes().put(attr, value);
				} else {
					throw new AttributesModelException("Ambiguos type for '" + attr + "=" + cx + "' -> " + c + " ");
				}
			} else {
				getAttributes().put(attr, value);
				getClasses().put(attr, c);
			}			
		}
	}
	
	protected String attributesToString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" [AttributesModel [");
		Enumeration<String> enumKeys = Collections.enumeration(getAttributes().keySet());
		boolean first=true;
		while (enumKeys.hasMoreElements()) {
			String key = (String) enumKeys.nextElement();
			buffer.append(first?"":" ,");first=false;
			buffer.append(key);
			buffer.append("={");
			buffer.append(getAttribute(key));
			buffer.append("(");
			buffer.append(getAttributeClass(key));
			buffer.append(")}");				
		}
		buffer.append("]");
		return buffer.toString();
	}	
}
