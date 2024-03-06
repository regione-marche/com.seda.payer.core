/**
 * 
 */
package com.seda.j2ee5.maf.components.defender.bfl;

/**
 * This class represents the user login attempts
 * 
 * @author Seda Lab
 *
 */
public class BFLUser {

	private String name;
	private String ip;
	private int attempts;
	private int maxAttempts;
	private long timestamp;
	private long duration;
	private boolean locked;
	
	
	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}

	public boolean isLocked() {
		return locked;
	}
	
	
	public BFLUser(String name, String ip, long duration, int maxAttempts) {
        this.name=name;
        this.ip=ip;
        this.duration=duration;
        this.maxAttempts=maxAttempts;
        this.attempts=0;
        this.locked=false;
    }

	public BFLUser(String name, String ip, long duration, int maxAttempts, int attempts, boolean locked) {
        this.name=name;
        this.ip=ip;
        this.duration=duration;
        this.maxAttempts=maxAttempts;
        this.attempts=attempts;
        this.locked=locked;
    }	
	
	public int upAttempts(String ip) throws BFLLockedException {
		if (!ip.equals(this.ip)) {
			lock();
			throw new BFLLockedException("'"+name+"' is locked for changed ip event: -" + ip + " vs +" + this.ip);
		}
			
		if (isLocked()) {
			if (isExpired()) {
				reset();
			} else {
				throw new BFLLockedException("'"+name+"' is locked but the timeout is not expired");
			}
		} else {
			attempts++;
			if (attempts>maxAttempts) {
				lock();
				throw new BFLLockedException("'"+name+"' has reached " + maxAttempts + " attempts and will be locked");
			}						
		}
		return attempts;
	}
	
	public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        return ((currentTime - timestamp) > duration);
    }
	
	public void lock() {
		attempts=maxAttempts++;
		locked=true;
		this.timestamp=System.currentTimeMillis(); // start clock for timeout management
	}
	
	public void reset() {
		attempts=1;
		locked=false;
	}

	public BFLUser clone() {
		return new BFLUser(name, ip, duration, maxAttempts, attempts, locked);
	}
	
	@Override
	public String toString() {
		return "BFLUser [attempts=" + attempts + ", duration=" + duration
				+ ", ip=" + ip + ", locked=" + locked + ", maxAttempts="
				+ maxAttempts + ", name=" + name + ", timestamp=" + timestamp
				+ "]";
	}
	
}
