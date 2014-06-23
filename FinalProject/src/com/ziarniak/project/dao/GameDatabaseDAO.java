package com.ziarniak.project.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ziarniak.project.models.Game;

public class GameDatabaseDAO implements GameDAO{
	
	@Autowired private JdbcTemplate template;

	@Override
	public void addGame(Game game) throws IOException {
		if(game!=null){
			template.update("INSERT INTO game (game_name,game_maker,game_type,game_price) VALUES (?,?,?,?)",game.getName(),game.getMaker(),game.getType(),game.getPrice());
		}
		
		
	}

	@Override
	public Game getGame(String name) {	
		List<Game> allGame=new ArrayList<Game>();
		List<Map<String,Object>> rows=template.queryForList("SELECT * FROM game WHERE game_name=?",name);
		for(Map<String,Object> row:rows){
			Game gameTemp=new Game();
			gameTemp.setId((Long) row.get("game_id"));
			gameTemp.setName((String)row.get("game_name"));
			gameTemp.setMaker((String)row.get("game_maker"));
			gameTemp.setType((String)row.get("game_type"));
			gameTemp.setPrice((String)row.get("game_price"));
			allGame.add(gameTemp);
		}
		Game game=null;
		if(!allGame.isEmpty()){
		 game=allGame.get(0);
		}	
		return game;
	}

	@Override
	public List<Game> getAllGames() {
		List<Game> allGame=new ArrayList<Game>();
		List<Map<String,Object>> rows=template.queryForList("SELECT * FROM game");
		for(Map<String,Object> row:rows){
			Game game=new Game();
			game.setId((Long) row.get("game_id"));
			game.setName((String)row.get("game_name"));
			game.setMaker((String)row.get("game_maker"));
			game.setType((String)row.get("game_type"));
			game.setPrice((String)row.get("game_price"));
			allGame.add(game);
		}
		System.out.println("All game  : "+allGame);
		return allGame;
	}

	@Override
	public void deleteGame(Game game) {
		if(game!=null){
			template.update("DELETE FROM game WHERE game_id=?", game.getId());
		}
		
	}

	@Override
	public boolean isGameInBase(String gameName) {
		if(this.getGame(gameName)==null){
			return false;
		}
		return true;
	}

	

	
}
