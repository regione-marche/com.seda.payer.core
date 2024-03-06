/**
 * 
 */
package com.seda.commons.management.util;

import static java.lang.String.format;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;

import javax.management.MBeanParameterInfo;

import com.seda.commons.management.DynamicMBeanFacade;
import com.seda.commons.management.Impact;
import com.seda.commons.management.ManagementException;
import com.seda.commons.xparser.XNode;
import com.seda.commons.xparser.XPathParser;

/**
 * @author f.ricci
 *
 */
public class XMLParamterizer {

	private XPathParser _parser;
	
	public XMLParamterizer(String mbeanXml) {
		this(new StringReader(mbeanXml));
	}
	
	public XMLParamterizer(Reader reader) {
		try {
			_parser = new XPathParser(reader, true);
		} catch (Exception e) {
			throw new RuntimeException("Error building XPathParser.", e);
		} finally {
			try {
				if (reader!=null) reader.close();
			} catch (IOException e) {}
		}
	}
	
	public DynamicMBeanFacade parameterize(DynamicMBeanFacade resource) throws ManagementException {
		if (_parser==null)
			throw new ManagementException("Parser null");
		
		String className = resource.getClass().getName();
		
		XNode mbeanNode = _parser.evalNode("//mbean[className=\""+className+"\"]");
		if (mbeanNode!=null) {
            List<XNode> configChildren = mbeanNode.getChildren();
            for (XNode configChild : configChildren) {
            	if ("attribute".equals(configChild.getName())) {
                    String attName = configChild.getStringAttribute("name");
                    String attDesc = configChild.getStringBody();
                    resource.addAttribute(attName, attDesc);
                } else if ("operation".equals(configChild.getName())) {
                	String opName = configChild.getStringAttribute("name");
                    String opDesc = configChild.getStringAttribute("description");
                    String impact = configChild.getStringAttribute("impact");
                    MBeanParameterInfo[] parametersInfo = createParameterInfo(mbeanNode.evalNode("/parameters"), resource, opName);
                    resource.addOperation(opName, opDesc, Impact.getImpact(impact), parametersInfo);
                } else if ("notification".equals(configChild.getName())) {
                	String notifName = configChild.getStringAttribute("type");
                    String notifDesc = configChild.getStringBody();
                    resource.addNotification(notifName, notifDesc);
                }
            }
		}
		return resource;
	}

	private MBeanParameterInfo[] createParameterInfo(XNode parametersNode, DynamicMBeanFacade resource, String opName) throws ManagementException {
		Method operation = findOperation(resource, opName);
		if (operation==null) {
			throw new ManagementException(format("Operation not found %s in %s",opName,resource.getClass()));
		}
		ParameterSet xparameters = new ParameterSet();
		int index=0;
		for (XNode parameter : parametersNode.getChildren()) {
			String name = parameter.getStringAttribute("name");
			String description = parameter.getStringAttribute("description");
			xparameters.add(new Parameter(name, description, index));
			index++;
		}
		
		MBeanParameterInfo[] parametersInfo = new MBeanParameterInfo[operation.getParameterTypes().length];
        for (int parameterIndex = 0; parameterIndex < parametersInfo.length; parameterIndex++) {
            final String pType = operation.getParameterTypes()[parameterIndex].getName();
            // locate parameter annotation
            Parameter xparameter = xparameters.getByIndex(parameterIndex);
            if (xparameter==null) {
            	xparameter=new Parameter("Parameter-" + (parameterIndex + 1), "unknown", parameterIndex);
            }
            final String pName = (xparameter.getName() != null) ? xparameter.getName() : "Parameter-" + (parameterIndex + 1); // 1 .. n
            final String pDesc = (xparameter.getDescription() != null) ? xparameter.getDescription() : "unknown";
            parametersInfo[parameterIndex] = new MBeanParameterInfo(pName, pType, pDesc);
        }
		
		return parametersInfo;
	}

	private Method findOperation(DynamicMBeanFacade resource, String opName) {
		for (Method operation : resource.getIntrospection()._operations) {
			if (opName.equals(operation.getName())) {
				return operation;
			}
		}
		return null;
	}
	
	@SuppressWarnings("serial")
	class ParameterSet extends HashSet<Parameter> {
		
		public Parameter getByName(String name) {
			for (Parameter parameter : this) {
				if (parameter.getName().equals(name)) {
					return parameter;
				}
			}
			return null;
		}
		
		public Parameter getByIndex(int index) {
			for (Parameter parameter : this) {
				if (parameter.getIndex()==index) {
					return parameter;
				}
			}
			return null;
		}
	}
	
	class Parameter {
		private String name;
		private String description;
		private int index;
		
		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public int getIndex() {
			return index;
		}

		public Parameter(String name, String description, int index) {
			super();
			this.name = name;
			this.description = description;
			this.index = index;
		}
		
	}
}