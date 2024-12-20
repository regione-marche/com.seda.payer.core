package com.seda.commons.management.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author f.ricci
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ManagedBean {
    /**
     * @return the ObjectName with which the MBean should be registered with the MBean server.<P>
     * Refer to {@link javax.management.ObjectName} for details of objectname syntax
     * Sample object names:<br>
     * org.management:name=MyBean,type=org.management.ProcessingMonitor
     * org.management:application=ESB,name=MyBean,type=org.management.ProcessingMonitor
     */
    String objectName() default "";
}
