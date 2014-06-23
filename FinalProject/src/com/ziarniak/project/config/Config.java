package com.ziarniak.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.ziarniak.project.models.Logger;
import com.ziarniak.project.service.GameService;
import com.ziarniak.project.service.GameTypeService;



@Configuration
@Import({AspectsConfiguration.class,FileProfileConfiguration.class,PostgresProfileConfiguration.class})
//@ComponentScan(basePackages="com.ziarniak")
public class Config {
	
	
	@Bean
	public Logger getLogger(){
		return new Logger();
	}
	

	@Bean
	public GameService gameService(){
		return new GameService();
	}
	

	@Bean
	public GameTypeService gameTypeService(){
		return new GameTypeService();
	}
	
	@Bean
	@Autowired
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource);
		return validator;
	}
	
	
	
	
	
}
