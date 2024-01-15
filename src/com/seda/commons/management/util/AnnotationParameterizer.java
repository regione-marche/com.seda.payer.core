/**
 * 
 */
package com.seda.commons.management.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.management.IntrospectionException;
import javax.management.MBeanParameterInfo;

import com.seda.commons.management.DynamicMBeanFacade;
import com.seda.commons.management.Impact;
import com.seda.commons.management.ManagementException;
import com.seda.commons.management.annotations.Description;
import com.seda.commons.management.annotations.ManagedAttribute;
import com.seda.commons.management.annotations.ManagedOperation;
import com.seda.commons.management.annotations.Parameter;

/**
 * @author f.ricci
 *
 */
public class AnnotationParameterizer {

	public static synchronized void parameterize(DynamicMBeanFacade mbean) throws ManagementException {
		createOperations(mbean);
		createAttributes(mbean);
		createNotifications(mbean);
	}
	
    /**
     * @return an MBeanOPerationInfo array that describes the {@link ManagedOperation} annotated methods of the operationMethods
     * @throws ManagementException
     */
    private static void createOperations(DynamicMBeanFacade mbean) throws ManagementException {
        // Iterate in method name order
        for (Method method : mbean.getIntrospection()._operations) {
            ManagedOperation annotation = method.getAnnotation(ManagedOperation.class);
            if (annotation != null) {
                // This method is an operation
                // add name to operation
                String name = method.getName();
                // add description and names to parameters
                MBeanParameterInfo[] signature = createParameterInfo(method);
                // add description and parameter info to operation method
                Impact impact = annotation.value();
                int impactValue = impact.impactValue;
                String description = description(method);
                mbean.addOperation(name, description, impactValue, signature);
            }
        }
    }

    protected static MBeanParameterInfo[] createParameterInfo(Method method) {
    	MBeanParameterInfo[] parameters = new MBeanParameterInfo[method.getParameterTypes().length];
        for (int parameterIndex = 0; parameterIndex < parameters.length; parameterIndex++) {
            final String pType = method.getParameterTypes()[parameterIndex].getName();
            // locate parameter annotation
            Parameter parameter = getParameterAnnotation(method, parameterIndex, Parameter.class);
            Description description = getParameterAnnotation(method, parameterIndex, Description.class);
            final String pName = (parameter != null) ? parameter.value() : "Parameter-" + (parameterIndex + 1); // 1 .. n
            final String pDesc = (description != null) ? description.value() : null;
            parameters[parameterIndex] = new MBeanParameterInfo(pName, pType, pDesc);
        }
        return parameters;
    }
	
    
    /**
    *
    * @param propertyDescriptors property descriptors that are known to have at least one {@link ManagedAttribute}
    * annotation on its getter or setter method
    * @return MBean attributeInfo instances with getter/setter methods and description according to annotations
    * @throws ManagementException
    * @throws IntrospectionException
    */
   private static void createAttributes(DynamicMBeanFacade mbean) throws ManagementException {
       // iterate over properties that are known to have ManagedAttribute annotations, sorted by name
       for (String propertyName : sortedKeys(mbean.getIntrospection()._attributesName)) {
           Method readMethod = mbean.getIntrospection()._getters.get(propertyName);
           Method writeMethod = mbean.getIntrospection()._setters.get(propertyName);
           Annotation readAnnotation = getAnnotation(readMethod, ManagedAttribute.class);
           Annotation writeAnnotation =  getAnnotation(writeMethod, ManagedAttribute.class);
           if (readAnnotation!=null || writeAnnotation!=null) {
               Description descriptionAnnotation = getSingleAnnotation(mbean.getClass(),propertyName, Description.class, readMethod, writeMethod);
               String description = (descriptionAnnotation != null) ? descriptionAnnotation.value() : null;
               if (writeAnnotation!=null) {
            	   mbean.addAttribute(propertyName, description);   
               } else {
            	   mbean.addROAttribute(propertyName, description);
               }
                       	   
           }
       }
   }
   
   
   private static void createNotifications(DynamicMBeanFacade mbean)  {
	   // implements here
	   // nothing to do for now
   }
   
   /**
   *
   * @param <T>
   * @param property The property to which entities belong
   * @param annotationClass Annotation type
   * @param entities A number of {@code Method}'s or {@code null}'s
   * @return The one (and only) annotation of type {@code annotationClass} that appears on {@code methods},
   * or null if none of the entities are annotated with annotationClass
   * @throws ManagementException if more than one of the entities are annotated with annotationClass
   */
  private static <T extends Annotation> T getSingleAnnotation(Class<?> mbeanClass, String propertyName, Class<T> annotationClass,
          AccessibleObject... entities) throws ManagementException {
      T result = null;
      for (AccessibleObject entity : entities) {
          if (entity != null) {
              T annotation = entity.getAnnotation(annotationClass);
              if (annotation != null) {
                  if (result != null) {
                      throw new ManagementException(
                              String.format("Multiple %s annotations found for property %s of %s",
                                      annotationClass.getName(), propertyName, mbeanClass));
                  }
                  result = annotation;
              }
          }
      }
      return result;
  }

  /**
   * Find an annotation for a parameter on a method.
   *
   * @param <A> The annotation.
   * @param method The method.
   * @param index The index (0 .. n-1) of the parameter in the parameters list
   * @param annotationClass The annotation class
   * @return The annotation, or null
   */
  private static <A extends Annotation> A getParameterAnnotation(Method method,
          int index, Class<A> annotationClass) {
      for (Annotation a : method.getParameterAnnotations()[index]) {
          if (annotationClass.isInstance(a)) {
              return annotationClass.cast(a);
          }
      }
      return null;
  }

  /**
   * Null safe annotation checker
   * @param <A>
   * @param element element or null
   * @param annotationClass
   * @return the annotation, if element is not null and the annotation is present. Otherwise null
   */
  public static <A extends Annotation> A getAnnotation(AnnotatedElement element, Class<A> annotationClass) {
      return (element != null) ? element.getAnnotation(annotationClass) : null;
  }

  private static String description(AnnotatedElement element) {
      Description annotation = element.getAnnotation(Description.class);
      return (annotation != null) ? annotation.value() : null;
  }

  /**
   * @param set
   * @return a list of the keys in map, sorted
   */
//  private List<String> sortedKeys(Map<String, ?> map) {
//      List<String> keys = new ArrayList<String>(map.keySet());
//      Collections.sort(keys);
//      return keys;
//  }
  
  private static List<String> sortedKeys(Set<String> set) {
      List<String> keys = new ArrayList<String>(set);
      Collections.sort(keys);
      return keys;
  }
}
