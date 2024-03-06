/**
 * 
 */
package com.seda.commons.management.samples;

import javax.management.ObjectName;

import com.seda.commons.management.DynamicMBeanFacade;
import com.seda.commons.management.agents.RMIAdaptorDelegate;
import com.seda.commons.management.agents.ServiceAgent;
import com.seda.commons.management.agents.ServiceAgent.ServiceAgentException;
import com.seda.commons.management.util.AnnotationParameterizer;

/**
 * @author f.ricci
 *
 */
public class HelloAgent extends DynamicMBeanFacade {

	//service:jmx:rmi:///jndi/rmi://localhost:3000/jmxrmi
	
	private volatile static boolean run=true;

	public void stopRun() {
		run=false;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		String domain = "org.jmx";
		RMIAdaptorDelegate adaptorDelegate = new RMIAdaptorDelegate(domain);
		adaptorDelegate.setPort(3000);
		ServiceAgent serviceAgent = new ServiceAgent(domain);
		try {
			serviceAgent.registerAdaptor(adaptorDelegate);
			serviceAgent.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
			HelloAgent helloAgent = new HelloAgent();
			helloAgent.addOperation("stopRun", "stop this agent");
			serviceAgent.addResource(new ObjectName("org.jmx:type=HelloAgent"), helloAgent);
			
			Hello hello = new Hello();
			AnnotationParameterizer.parameterize(hello);
			serviceAgent.addResource(new ObjectName("org.jmx:type=HelloAgent,child=HelloBean"), hello);
			
			while (run) {
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				serviceAgent.stop();
			} catch (ServiceAgentException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}

}
