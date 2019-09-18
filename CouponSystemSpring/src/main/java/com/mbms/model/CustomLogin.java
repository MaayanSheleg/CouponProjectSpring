package com.mbms.model;

import com.mbms.model.LoginType;

public class CustomLogin {
	
	private LoginType loginType;
	private int typeId;

	public CustomLogin(LoginType loginType, int typeId) {
		this.loginType = loginType;
		this.typeId = typeId;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override

	public String toString() {
		return "CustomLogin [loginType=" + loginType + ", typeId=" + typeId + "]";

	}

}
