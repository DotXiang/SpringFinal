package com.ziarniak.project.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



@Configuration
public class DataBaseConfiguration {
	@Value("${adresbazy}") private String databaseURL;
	@Value("${userbazy}") private String username;
	@Value("${haslobazy}") private String password;
	
	@PostConstruct
	public void init(){
		System.out.println("BAZA: " + this.databaseURL.toString());
	}
	
	public String getDatabaseURL() {
		return databaseURL;
	}
	public void setDatabaseURL(String databaseURL) {
		this.databaseURL = databaseURL;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DatabaseConfiguration [databaseURL=" + databaseURL + ", username="
				+ username + ", password=" + password + "]";
	}
	
	
}
