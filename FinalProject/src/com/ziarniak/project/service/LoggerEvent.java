package com.ziarniak.project.service;

import org.springframework.context.ApplicationEvent;

public class LoggerEvent extends ApplicationEvent {


	private static final long serialVersionUID = 1L;
	private String message;

	public LoggerEvent(Object source) {
		super(source);
	}

	public LoggerEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
