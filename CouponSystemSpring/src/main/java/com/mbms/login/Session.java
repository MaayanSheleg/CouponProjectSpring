package com.mbms.login;

import org.springframework.stereotype.Component;

@Component
public class Session{
	
	private CouponClientFacade facade;
	private long lastAccesed;
	
	public CouponClientFacade getFacade() {
		return facade;

	}

	public void setFacade(CouponClientFacade facade) {
		this.facade = facade;
	}

	public long getLastAccesed() {
		return lastAccesed;
	}

	public void setLastAccesed(long lastAccesed) {
		this.lastAccesed = lastAccesed;
	}

	@Override
	public String toString() {
		return "Session [facade=" + facade + ", lastAccesed=" + lastAccesed + "]";

	}
}
