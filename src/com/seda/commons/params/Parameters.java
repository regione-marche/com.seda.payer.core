package com.seda.commons.params;

/**
 * @author f.ricci
 *
 */
public interface Parameters {

    /** 
     * Returns the parent collection that this collection will defer to
     * for a default value if a particular parameter is not explicitly 
     * set in the collection itself
     * 
     * @return the parent collection to defer to, if a particular parameter
     * is not explictly set in the collection itself.
     * 
     * @see #setDefaults(Parameters)
     */
    public Parameters getDefaults();

    /** 
     * Assigns the parent collection that this collection will defer to
     * for a default value if a particular parameter is not explicitly 
     * set in the collection itself
     * 
     * @param params the parent collection to defer to, if a particular 
     * parameter is not explictly set in the collection itself.
     * 
     * @see #getDefaults()
     */
    public void setDefaults(final Parameters params);
    
    /** 
     * Returns a parameter value with the given name. If the parameter is
     * not explicitly defined in this collection, its value will be drawn 
     * from a higer level collection at which this parameter is defined.
     * If the parameter is not explicitly set anywhere up the hierarchy,
     * <tt>null</tt> value is returned.  
     * 
     * @param name the parent name.
     * 
     * @return an object that represents the value of the parameter.
     * 
     * @see #setParameter(String, Object)
     */
    public Object getParameter(final String name);

    /**
     * Assigns the value to the parameter with the given name
     * 
     * @param name parameter name
     * @param value parameter value
     */ 
    public void setParameter(final String name, final Object value);
    
    /** 
     * Returns a {@link Long} parameter value with the given name. 
     * If the parameter is not explicitly defined in this collection, its 
     * value will be drawn from a higer level collection at which this parameter 
     * is defined. If the parameter is not explicitly set anywhere up the hierarchy,
     * the default value is returned.  
     * 
     * @param name the parent name.
     * @param defaultValue the default value.
     * 
     * @return a {@link Long} that represents the value of the parameter.
     * 
     * @see #setLongParameter(String, long)
     */
    public long getLongParameter(final String name, long defaultValue); 
    
    /**
     * Assigns a {@link Long} to the parameter with the given name
     * 
     * @param name parameter name
     * @param value parameter value
     */ 
    public void setLongParameter(final String name, long value);

    /** 
     * Returns an {@link Integer} parameter value with the given name. 
     * If the parameter is not explicitly defined in this collection, its 
     * value will be drawn from a higer level collection at which this parameter 
     * is defined. If the parameter is not explicitly set anywhere up the hierarchy,
     * the default value is returned.  
     * 
     * @param name the parent name.
     * @param defaultValue the default value.
     * 
     * @return a {@link Integer} that represents the value of the parameter.
     * 
     * @see #setIntParameter(String, int)
     */
    public int getIntParameter(final String name, int defaultValue); 
    
    /**
     * Assigns an {@link Integer} to the parameter with the given name
     * 
     * @param name parameter name
     * @param value parameter value
     */ 
    public void setIntParameter(final String name, int value);

    /** 
     * Returns a {@link Double} parameter value with the given name. 
     * If the parameter is not explicitly defined in this collection, its 
     * value will be drawn from a higer level collection at which this parameter 
     * is defined. If the parameter is not explicitly set anywhere up the hierarchy,
     * the default value is returned.  
     * 
     * @param name the parent name.
     * @param defaultValue the default value.
     * 
     * @return a {@link Double} that represents the value of the parameter.
     * 
     * @see #setDoubleParameter(String, double)
     */
    public double getDoubleParameter(final String name, double defaultValue); 
    
    /**
     * Assigns a {@link Double} to the parameter with the given name
     * 
     * @param name parameter name
     * @param value parameter value
     */ 
    public void setDoubleParameter(final String name, double value);

    /** 
     * Returns a {@link Boolean} parameter value with the given name. 
     * If the parameter is not explicitly defined in this collection, its 
     * value will be drawn from a higer level collection at which this parameter 
     * is defined. If the parameter is not explicitly set anywhere up the hierarchy,
     * the default value is returned.  
     * 
     * @param name the parent name.
     * @param defaultValue the default value.
     * 
     * @return a {@link Boolean} that represents the value of the parameter.
     * 
     * @see #setBooleanParameter(String, boolean)
     */
    public boolean getBooleanParameter(final String name, boolean defaultValue); 
    
    /**
     * Assigns a {@link Boolean} to the parameter with the given name
     * 
     * @param name parameter name
     * @param value parameter value
     */ 
    public void setBooleanParameter(final String name, boolean value);

    /**
     * Returns <tt>true</tt> if the parameter is set at any level, <tt>false</tt> otherwise.
     * 
     * @param name parameter name
     * 
     * @return <tt>true</tt> if the parameter is set at any level, <tt>false</tt>
     * otherwise.
     */
    public boolean isParameterSet(final String name);
        
    /**
     * Returns <tt>true</tt> if the parameter is set locally, <tt>false</tt> otherwise.
     * 
     * @param name parameter name
     * 
     * @return <tt>true</tt> if the parameter is set locally, <tt>false</tt>
     * otherwise.
     */
    public boolean isParameterSetLocally(final String name);
        
    /**
     * Returns <tt>true</tt> if the parameter is set and is <tt>true</tt>, <tt>false</tt>
     * otherwise.
     * 
     * @param name parameter name
     * 
     * @return <tt>true</tt> if the parameter is set and is <tt>true</tt>, <tt>false</tt>
     * otherwise.
     */
    public boolean isParameterTrue(final String name);
        
    /**
     * Returns <tt>true</tt> if the parameter is either not set or is <tt>false</tt>, 
     * <tt>false</tt> otherwise.
     * 
     * @param name parameter name
     * 
     * @return <tt>true</tt> if the parameter is either not set or is <tt>false</tt>, 
     * <tt>false</tt> otherwise.
     */
    public boolean isParameterFalse(final String name);

	
}
