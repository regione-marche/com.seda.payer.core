package com.seda.commons.params;

import java.util.HashMap;

import com.seda.commons.cache.impl.LoggingCache;
import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;

/**
 * This class represents a collection of parameters. Parameters
 * may be linked together to form a hierarchy. If a particular parameter value has not been
 * explicitly defined in the collection itself, its value will be drawn from the parent 
 * collection of parameters.
 * 
 * @author f.ricci
 *
 */
public class DefaultParameters implements Parameters {

    /** Log object for this class. */
    private static final LoggerWrapper LOG =  CustomLoggerManager.get(DefaultParameters.class);


    /** Parameters class factory. */
    protected static ParametersFactory paramsFactory = new DefaultParametersFactory();

    /**
     * Gets the default Parameters to be used.
     * 
     * @return the value returned from <code>ParametersFactory#getDefaultParams()</code>
     * 
     * @see ParametersFactory#getDefaultParameters()
     */
    public static Parameters getDefaultParameters() {
        return paramsFactory.getDefaultParameters();
    }
    
    /**
     * Sets the factory that will provide the default Parameters.
     * 
     * @param paramsFactory an instance of ParametersFactory
     * 
     * @see #getDefaultParameters()
     */
    public static void setParametersFactory(ParametersFactory paramsFactory) {
        if (paramsFactory == null) {
            throw new IllegalArgumentException("paramsFactory may not be null");
        }
        DefaultParameters.paramsFactory = paramsFactory;
    }

    /** The set of default values to defer to */
    private Parameters defaults = null;

    /** Hash map of parameters that this collection contains */
    private HashMap<String,Object> parameters = null;
    
    /**
     * Creates a new collection of parameters with the given parent. 
     * The collection will defer to its parent for a default value 
     * if a particular parameter is not explicitly set in the collection
     * itself.
     * 
     * @param defaults the parent collection to defer to, if a parameter
     * is not explictly set in the collection itself.
     */
    public DefaultParameters(final Parameters defaults) {
        super();
        this.defaults = defaults; 
    }
    
    /**
     * Creates a new collection of parameters with the collection returned
     * by {@link #getDefaultParameters()} as a parent. The collection will defer
     * to its parent for a default value if a particular parameter is not 
     * explicitly set in the collection itself.
     * 
     * @see #getDefaultParameters()
     */
    public DefaultParameters() {
        this(getDefaultParameters());
    }
    
    public synchronized Parameters getDefaults() {
        return this.defaults;
    }
    
    public synchronized void setDefaults(final Parameters params) {
        this.defaults = params;
    }
    
    public synchronized Object getParameter(final String name) {
        // See if the parameter has been explicitly defined
        Object param = null;
        if (this.parameters != null) {
            param = this.parameters.get(name);
        }    
        if (param != null) {
            // If so, return
            return param;
        } else {
            // If not, see if defaults are available
            if (this.defaults != null) {
                // Return default parameter value
                return this.defaults.getParameter(name);
            } else {
                // Otherwise, return null
                return null;
            }
        }
    }

    public synchronized void setParameter(final String name, final Object value) {
        if (this.parameters == null) {
            this.parameters = new HashMap<String,Object>();
        }
        this.parameters.put(name, value);
if (LOG.isDebugEnabled()) {
        LOG.debug("Set parameter " + name + " = " + value);
    }
}
    
    /**
     * Assigns the value to all the parameter with the given names
     * 
     * @param names array of parameter name
     * @param value parameter value
     */ 
    public synchronized void setParameters(final String[] names, final Object value) {
        for (int i = 0; i < names.length; i++) {
            setParameter(names[i], value);
        }
    }

    public long getLongParameter(final String name, long defaultValue) { 
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Long)param).longValue();
    }
    
    public void setLongParameter(final String name, long value) {
        setParameter(name, new Long(value));
    }

    public int getIntParameter(final String name, int defaultValue) { 
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Integer)param).intValue();
    }
    
    public void setIntParameter(final String name, int value) {
        setParameter(name, new Integer(value));
    }

    public double getDoubleParameter(final String name, double defaultValue) { 
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Double)param).doubleValue();
    }
    
    public void setDoubleParameter(final String name, double value) {
        setParameter(name, new Double(value));
    }

    public boolean getBooleanParameter(final String name, boolean defaultValue) { 
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Boolean)param).booleanValue();
    }
    
    public void setBooleanParameter(final String name, boolean value) {
        setParameter(name, value ? Boolean.TRUE : Boolean.FALSE);// Boolean.valueOf() = Java 1.4+
    }

    public boolean isParameterSet(final String name) {
        return getParameter(name) != null;
    }
        
    public boolean isParameterSetLocally(final String name) {
        return this.parameters != null && this.parameters.get(name) != null;
    }
        
    public boolean isParameterTrue(final String name) {
        return getBooleanParameter(name, false);
    }
        
    public boolean isParameterFalse(final String name) {
        return !getBooleanParameter(name, false);
    }

    /**
     * Removes all parameters from this collection. 
     */
    public void clear() {
        this.parameters = null;
    }

    /**
     * Clones this collection of parameters. Please note that parameter values
     * themselves are not cloned. 
     * 
     * @see java.io.Serializable
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        DefaultParameters clone = (DefaultParameters)super.clone();
        if (this.parameters != null) {
            clone.parameters = (HashMap<String,Object>)this.parameters.clone(); 
        }
        clone.setDefaults(this.defaults);
        return clone;
    }

    
    
}
