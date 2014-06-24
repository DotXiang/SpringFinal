package com.ziarniak.project.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ziarniak.project.models.Game;
import com.ziarniak.project.models.GameType;

public class GameTypeDatabaseDAO implements GameTypeDAO {
	@Autowired
	private JdbcTemplate template;

	@Override
	public GameType getGameType(String name) {

		List<GameType> allGame = new ArrayList<GameType>();
		List<Map<String, Object>> rows = template.queryForList(
				"SELECT * FROM gametype WHERE type_name=?", name);
		for (Map<String, Object> row : rows) {
			GameType gameTemp = new GameType();
			gameTemp.setId((Long) row.get("type_id"));
			gameTemp.setName((String) row.get("type_name"));
			allGame.add(gameTemp);
		}
		GameType gameType = null;
		if (!allGame.isEmpty()) {
			gameType = allGame.get(0);
		}
		return gameType;
	}

	@Override
	public List<GameType> getAllGameType() {
		List<GameType> allGameType = new ArrayList<GameType>();
		List<Map<String, Object>> rows = template
				.queryForList("SELECT * FROM gametype");
		for (Map<String, Object> row : rows) {
			GameType gameType = new GameType();
			gameType.setId((Long) row.get("type_id"));
			gameType.setName((String) row.get("type_name"));
			gameType.setListOfGames(this.getAllGamesOfType(gameType.getName()));
			allGameType.add(gameType);
		}
		return allGameType;
	}

	@Override
	public List<Game> getAllGamesOfType(String name) {
		List<Game> allGameOfType = new ArrayList<Game>();
		List<Map<String, Object>> rows = template.queryForList(
				"SELECT * FROM game WHERE game_type=?", name);
		for (Map<String, Object> row : rows) {
			Game game = new Game();
			game.setId((Long) row.get("game_id"));
			game.setName((String) row.get("game_name"));
			game.setMaker((String) row.get("game_maker"));
			game.setType((String) row.get("game_type"));
			game.setPrice((String) row.get("game_price"));
			allGameOfType.add(game);
		}
		return allGameOfType;
	}

	@Override
	public void deleteGameType(String gameType) {
		if (gameType != null) {
			template.update("DELETE FROM gametype WHERE type_name=?", gameType);
		}

	}

	@Override
	public void addGameType(GameType gameType) {

		if (gameType != null) {
			template.update("INSERT INTO gametype (type_name) VALUES (?)",
					gameType.getName());
		}
	}

	@Override
	public boolean isGameTypeExist(String gameTypeName) {
		if (this.getGameType(gameTypeName) == null) {
			return false;
		}
		return true;
	}

}
