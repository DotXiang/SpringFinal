package com.ziarniak.project.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ziarniak.project.dao.GameDAO;
import com.ziarniak.project.dao.GameFileDAO;
import com.ziarniak.project.dao.GameTypeDAO;
import com.ziarniak.project.dao.GameTypeFileDAO;


@Configuration
@Profile("file")
public class FileProfileConfiguration {

	
	@Bean
	public GameDAO getGameDAO() throws ClassNotFoundException, IOException{
		return new GameFileDAO();
	}
	
	@Bean
	public GameTypeDAO getGameTypeDAO(){
	return new GameTypeFileDAO ();
	}
}
