package com.ziarniak.project.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ziarniak.project.models.Game;

public class GameDatabaseDAO implements GameDAO {

	@Autowired
	private JdbcTemplate template;

	private static final String GAME_ID="game_id";
	private static final String GAME_NAME="game_name";
	private static final String GAME_MAKER="game_maker";
	private static final String GAME_TYPE="game_type";
	private static final String GAME_PRICE="game_price";
	
	@Override
	public void addGame(Game game) throws IOException {
		if (game != null) {
			template.update(
					"INSERT INTO game (game_name,game_maker,game_type,game_price) VALUES (?,?,?,?)",
					game.getName(), game.getMaker(), game.getType(),
					game.getPrice());
		}

	}

	@Override
	public Game getGame(String name) {
		List<Game> allGame = new ArrayList<Game>();
		List<Map<String, Object>> rows = template.queryForList(
				"SELECT * FROM game WHERE game_name=?", name);
		for (Map<String, Object> row : rows) {
			Game gameTemp = new Game();
			gameTemp.setId((Long) row.get(GAME_ID));
			gameTemp.setName((String) row.get(GAME_NAME));
			gameTemp.setMaker((String) row.get(GAME_MAKER));
			gameTemp.setType((String) row.get(GAME_TYPE));
			gameTemp.setPrice((String) row.get(GAME_PRICE));
			allGame.add(gameTemp);
		}
		Game game = null;
		if (!allGame.isEmpty()) {
			game = allGame.get(0);
		}
		return game;
	}

	@Override
	public List<Game> getAllGames() {
		List<Game> allGame = new ArrayList<Game>();
		List<Map<String, Object>> rows = template
				.queryForList("SELECT * FROM game");
		for (Map<String, Object> row : rows) {
			Game game = new Game();
			game.setId((Long) row.get(GAME_ID));
			game.setName((String) row.get(GAME_NAME));
			game.setMaker((String) row.get(GAME_MAKER));
			game.setType((String) row.get(GAME_TYPE));
			game.setPrice((String) row.get(GAME_PRICE));
			allGame.add(game);
		}
		System.out.println("All game  : " + allGame);
		return allGame;
	}

	@Override
	public void deleteGame(Game game) {
		if (game != null) {
			template.update("DELETE FROM game WHERE game_id=?", game.getId());
		}

	}

	@Override
	public boolean isGameInBase(String gameName) {
		if (this.getGame(gameName) == null) {
			return false;
		}
		return true;
	}

}
