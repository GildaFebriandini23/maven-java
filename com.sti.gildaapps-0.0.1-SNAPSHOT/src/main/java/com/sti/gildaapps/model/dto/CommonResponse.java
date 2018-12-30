package com.sti.gildaapps.model.dto;

public class CommonResponse<T> {
	private String status;
	private String message;
	private T values;
	
	public CommonResponse() {//default constructer --> by default is success
		this.status = "00";
		this.message = "Success";
	}
	
	public CommonResponse(T data) {//default constructor with data
		this();
		this.values = data;
	}

	public CommonResponse(String status, String message) {//new instance with overriden status
		this.status = status;
		this.message = message;
	}
	
	public CommonResponse(String status, String message, T data) {//new instance with overriden status & data
		this.status = status;
		this.message = message;
		this.values = data;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getValues() {
		return values;
	}
	public void setValues(T values) {
		this.values = values;
	}
	
}
