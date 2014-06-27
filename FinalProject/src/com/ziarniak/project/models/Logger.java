package com.ziarniak.project.models;

import org.springframework.context.ApplicationListener;

import com.ziarniak.project.service.LoggerEvent;

public class Logger implements ApplicationListener<LoggerEvent> {

	public Logger() {
	}

	public void log(String message) {
		System.out.println("LOGGER : " + message);
	}

	@Override
	public void onApplicationEvent(LoggerEvent event) {
		this.log(event.getMessage());
	}
}
