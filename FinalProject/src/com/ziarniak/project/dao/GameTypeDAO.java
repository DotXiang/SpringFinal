package com.ziarniak.project.dao;

import java.util.List;

import com.ziarniak.project.models.Game;
import com.ziarniak.project.models.GameType;

public interface GameTypeDAO {

	public abstract GameType getGameType(String name);
	
	public abstract List<GameType> getAllGameType();
	
	public abstract List<Game>getAllGamesOfType(String name);
	
	public abstract void deleteGameType(String string);
	
	public abstract void addGameType(GameType gameType);
	
	public boolean isGameTypeExist(String gameTypeName);
	
}
