package com.ziarniak.project.dao;

import java.io.IOException;
import java.util.List;

import com.ziarniak.project.models.Game;

public interface GameDAO {

	public abstract void addGame(Game game) throws IOException;

	public abstract Game getGame(String name);
	
	public abstract List<Game>getAllGames();
	
	public abstract void deleteGame(Game game);
	
	public abstract boolean isGameInBase(String gameName);
	
}
