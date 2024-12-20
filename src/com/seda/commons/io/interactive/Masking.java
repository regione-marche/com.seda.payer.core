/**
 * 
 */
package com.seda.commons.io.interactive;

/**
 * This class attempts to erase characters echoed to the console.
 * @author f.ricci
 *
 */
public class Masking extends Thread {
	private volatile boolean stop;
	private char echochar = '*';

	/**
	 *@param prompt The prompt displayed to the user
	 */
	public Masking(String prompt) {
		System.out.print(prompt.concat(" "));
	}

	/**
	 *@param prompt The prompt displayed to the user
	 */
	public Masking(String prompt, char echocar) {
		this(prompt);
		this.echochar=echocar;		
	}	
	
	/**
	 * Begin masking until asked to stop.
	 */
	public void run() {
		int priority = Thread.currentThread().getPriority();
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

		try {
			stop = true;
			while(stop) {
				System.out.print("\010" + echochar);
				try {
					// attempt masking at this rate
					Thread.currentThread().sleep(1);
				}catch (InterruptedException iex) {
					Thread.currentThread().interrupt();
					return;
				}
			}
		} finally { // restore the original priority
			Thread.currentThread().setPriority(priority);
		}
	}

	/**
	 * Instruct the thread to stop masking.
	 */
	public void stopMasking() {
		this.stop = false;
	}
}