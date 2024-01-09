package com.seda.commons.management.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.seda.commons.management.Impact;

/**
 * Annotation for MBean operation methods.<p>
 *
 * @author f.ricci
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ManagedOperation {

    /**
     * @return The impact of this operation
     */
    Impact value() default Impact.UNKNOWN;
}
