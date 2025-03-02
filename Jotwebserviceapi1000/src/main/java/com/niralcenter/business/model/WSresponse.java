package com.niralcenter.business.model;

import org.springframework.stereotype.Component;

@Component
public class WSresponse {

	int code;
	String message;
	Object pocket;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getPocket() {
		return pocket;
	}
	public void setPocket(Object pocket) {
		this.pocket = pocket;
	}


}
