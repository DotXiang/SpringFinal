package com.ziarniak.project.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ziarniak.project.dao.GameDAO;
import com.ziarniak.project.dao.GameDatabaseDAO;
import com.ziarniak.project.dao.GameTypeDAO;
import com.ziarniak.project.dao.GameTypeDatabaseDAO;


@Configuration
@Profile("postgres")
public class PostgresProfileConfiguration {

	
	@Bean
	public GameDAO getGameDAO(){
		return new GameDatabaseDAO();
	}
	
	@Bean
	public GameTypeDAO getGameTypeDAO(){
	return new GameTypeDatabaseDAO();
	}
	
	
	
	@Bean
	public JdbcTemplate pobierzJDBCTemplate(){
		DataBaseConfiguration dbconf=dataBaseConfiguration();
		System.out.println("KONFIGURACJA W JDBC: " + dbconf.toString());
		BasicDataSource ds=new BasicDataSource();	
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(dbconf.getDatabaseURL());
		ds.setUsername(dbconf.getUsername());
		ds.setPassword(dbconf.getPassword());
		return new JdbcTemplate(ds);
	}
	
	@Bean
	public static PropertyPlaceholderConfigurer getDatabaseConfig(){
		PropertyPlaceholderConfigurer configurer=new PropertyPlaceholderConfigurer();
		configurer.setLocations(new Resource[]{new ClassPathResource("database.properties")});
		return configurer;
	}

	@Bean
	public DataBaseConfiguration dataBaseConfiguration(){
		return new DataBaseConfiguration();
	}
}
