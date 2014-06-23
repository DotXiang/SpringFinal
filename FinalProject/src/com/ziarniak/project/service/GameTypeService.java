package com.ziarniak.project.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.ziarniak.project.dao.GameTypeDAO;
import com.ziarniak.project.models.Game;
import com.ziarniak.project.models.GameType;
@Configuration
public class GameTypeService {

	
	@Autowired
	private GameTypeDAO gameTypeDao;
	
	private final static Logger LOGGER = Logger.getLogger(GameTypeService.class.getName());
	
	public GameType getGameType(String name){	
		LOGGER.info("Getting gameType");
		return gameTypeDao.getGameType(name);
	}
	
	public List<GameType> getAllGameType(){
		LOGGER.info("Getting all gameType");
		return gameTypeDao.getAllGameType();
	}
	
	public List<Game>getAllGamesOfType(String name){
		LOGGER.info("Getting all game of Type");
		return gameTypeDao.getAllGamesOfType(name);
	}
	
	public void deleteGameType(String string){
		LOGGER.info("Deleting gameType");
		gameTypeDao.deleteGameType(string);
	}
	
	public void addGameType(GameType gameType){
		LOGGER.info("Adding gameType");
		gameTypeDao.addGameType(gameType);
	}
	
	public boolean isGameTypeExist(String gameTypeName){
		return gameTypeDao.isGameTypeExist(gameTypeName);
	}
	
}
