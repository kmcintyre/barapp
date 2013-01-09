package com.nwice.barapp.user;

import com.nwice.barapp.model.BarappUser;

public class JspUserBean {
	
	private String sessionId;
	private BarappUser user;
	
	public void setSessionId(String s) {
		sessionId = s;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setUser(BarappUser uo) {
		user = uo;
	}

	public BarappUser getUser() {
		return user;
	}
	
	public String toString() {
		return user.toString() + "_" + getSessionId();
	}
	
}