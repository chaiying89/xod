package com.oxd.vo;


public class MessageVo {
	
	private boolean success;
	
	private String message;
	
	public MessageVo() {}
	
	public MessageVo(boolean isSuccess, String message) {
		this.success = isSuccess;
		this.message = message;
	}
	
	public static MessageVo fullErrorMessage(String message) {
		return new MessageVo(false, message);
	}
	
	public static MessageVo fullSuccessMessage(String message) {
		return new MessageVo(true, message);
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
