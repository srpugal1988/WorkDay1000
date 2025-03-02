package com.niralcenter.business.common;

import org.springframework.stereotype.Component;

@Component
public class Webfaceresponse {
	
	String code;
	String message;
	Object pocket;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
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
