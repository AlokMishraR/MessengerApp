package com.gupta.shanu.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	private int errorCode;
	private String desciption;
	
	public ErrorMessage(){
		
	}
	
	public ErrorMessage(String errorMessage, int errorCode, String desciption) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.desciption = desciption;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
}
