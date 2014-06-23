package com.ziarniak.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.ziarniak.project.aspects.MessagerAspect;

@Configuration
@EnableAspectJAutoProxy
public class AspectsConfiguration {

	
	@Bean
	public MessagerAspect messagerAspect(){
		return new MessagerAspect();
	}	
	
	
}
