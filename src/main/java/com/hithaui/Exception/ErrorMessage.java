package com.hithaui.Exception;

public class ErrorMessage {

	private Integer status;
	private String message;
	public ErrorMessage(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public ErrorMessage() {
		super();
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
