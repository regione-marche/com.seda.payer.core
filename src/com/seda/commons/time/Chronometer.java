/**
 * 
 */
package com.seda.commons.time;

import com.seda.commons.string.Convert;

/**
 * A chronometer accumulates time while running. 
 * You can start and stop the chronometer every time you want. 
 * You can use a chronometer to measure the elapsed time between two 
 * different moments in time.
 * 
 * @author Seda labs
 *
 */
public class Chronometer {

	private long elapsedTime;
	private long startTime;
	private boolean isRunning;
	/**
	 * Constructs a chronometer in the stopped state with no time accumulated.
	 */
	public Chronometer() {
		reset();
	}

	/**
	 * Start the chronometer. The time begins to accumulate from this moment. 
	 */
	public void start(){
		if (isRunning) return;
		startTime=System.currentTimeMillis();
		isRunning=true;
	}
	
	/**
	 * Stop the chronometer. Time stops accumulate and is added to the elapsed time. 
	 */
	public void stop() {
		if (!isRunning) return;
		long endTime=System.currentTimeMillis();
		elapsedTime=elapsedTime + endTime - startTime;
		isRunning=false;
	}
	
	/**
	 * Returns the total time spent.
	 * @return <code>long</code> total time spent
	 */
	public long getElapsedTime() {
		if (isRunning) {
			long endTime=System.currentTimeMillis();
			elapsedTime=elapsedTime + endTime - startTime;
			startTime=endTime;
		}
		return elapsedTime;
	}
	/**
	 * Returns the total time spent in the format HHhMM'SS".
	 * @return <code>String</code> total time spent in the format HHhMM'SS"
	 */
	public String getElapsedTimeHMS() {
		return Convert.millisTimeToString(getElapsedTime());
	}
	/**
	 * Stops the timer and set the elapsed time to 0.
	 */
	public void reset() {
		elapsedTime=0;
		isRunning=false;
	}
}
