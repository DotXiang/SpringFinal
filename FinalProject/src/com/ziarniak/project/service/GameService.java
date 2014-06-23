package com.ziarniak.project.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.ziarniak.project.dao.GameDAO;
import com.ziarniak.project.models.Game;

@Configuration
public class GameService extends ValidatorService {

	@Autowired
	private GameDAO gameDAO;

	@PostConstruct
	public void afterCreate() {
		System.out.println("DAO in use " + gameDAO);
	}

	public void addGame(Game game) throws IOException {

		this.gameDAO.addGame(game);

	}

	public Game getGame(String name) {
		return this.gameDAO.getGame(name);
	}

	public List<Game> getAllGames() {

		return this.gameDAO.getAllGames();
	}

	public void deleteGame(Game game) {
		this.gameDAO.deleteGame(game);
	}

	public boolean isGameInBase(String gameField) {
		return this.gameDAO.isGameInBase(gameField);
	}
}
