/**
 * 
 */
package com.seda.commons.management.samples;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;

import com.seda.commons.management.DynamicMBeanFacade;
import com.seda.commons.management.annotations.Description;
import com.seda.commons.management.annotations.ManagedAttribute;
import com.seda.commons.management.annotations.ManagedOperation;
import com.seda.commons.management.annotations.Parameter;

/**
 * @author f.ricci
 *
 */
public class Hello extends DynamicMBeanFacade {

	@ManagedOperation @Description("This bean say hello")
	public void sayHello() {
		System.out.println("hello, world");
	}

	@ManagedOperation @Description("This bean sum two integer and gives the result")
	public int add(@Parameter("left") @Description("The left value") int x, 
			@Parameter("right") @Description("The right value") int y) {
		return x + y;
	}

	/* Getter for the Name attribute. The pattern shown here is frequent: the
   getter returns a private field representing the attribute value. In our
   case, the attribute value never changes, but for other attributes it
   might change as the application runs. Consider an attribute representing
   statistics such as uptime or memory usage, for example. Being read-only
   just means that it can't be changed through the management interface. */
	@ManagedAttribute() @Description("The name of this cache")
	public String getName() {
		return this.name;
	}

	/* Getter for the CacheSize attribute. The pattern shown here is
   frequent: the getter returns a private field representing the
   attribute value, and the setter changes that field. */
	@ManagedAttribute() @Description("The size of this cache")
	public int getCacheSize() {
		return this.cacheSize;
	}

	/* Setter for the CacheSize attribute. To avoid problems with
   stale values in multithreaded situations, it is a good idea
   for setters to be synchronized. */
	@ManagedAttribute()
	public synchronized void setCacheSize(int size) {
		int oldSize = this.cacheSize;
		this.cacheSize = size;

		/* In a real application, changing the attribute would
   typically have effects beyond just modifying the cacheSize
   field.  For example, resizing the cache might mean
   discarding entries or allocating new ones. The logic for
   these effects would be here. */
		System.out.println("Cache size now " + this.cacheSize);

		/* Construct a notification that describes the change. The
   "source" of a notification is the ObjectName of the MBean
   that emitted it. But an MBean can put a reference to
   itself ("this") in the source, and the MBean server will
   replace this with the ObjectName before sending the
   notification on to its clients.

   For good measure, we maintain a sequence number for each
   notification emitted by this MBean.

   The oldValue and newValue parameters to the constructor are
   of type Object, so we are relying on Tiger's autoboxing
   here.  */
		Notification n =
			new AttributeChangeNotification(this,
					sequenceNumber++,
					System.currentTimeMillis(),
					"CacheSize changed",
					"CacheSize",
					"int",
					oldSize,
					this.cacheSize);

		/* Now send the notification */
		sendNotification(n);
	}


	private final String name = "Reginald";
	private int cacheSize = DEFAULT_CACHE_SIZE;
	private static final int DEFAULT_CACHE_SIZE = 200;

	private long sequenceNumber = 1;
}