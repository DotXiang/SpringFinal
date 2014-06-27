package com.ziarniak.project.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.ziarniak.project.models.Game;
import com.ziarniak.project.service.LoggerEvent;

public class GameFileDAO implements GameDAO{

	@Autowired private ApplicationEventPublisher publisher;
	
	
	private static final String PATH_TO_FILE="games.txt";
	
	private List<Game> games=new ArrayList<Game>();
	
	
	@PostConstruct
	public void initialize() throws ClassNotFoundException, IOException{
		this.readGames();
	}
	
	@Override
	public void addGame(Game game) throws IOException {	
		if (game!=null)  {
			game.setId(Long.valueOf(String.valueOf(this.games.size()+1)));
			this.games.add(game);
			this.writeGames();
			 publisher.publishEvent(new LoggerEvent(this,"Game " + game.toString() + " saved"));
		}
			
	}

	@Override
	public Game getGame(String name) {	
		for(Game game:this.games){
			if(game.getName().equalsIgnoreCase(name)){
				return game;
			}
		}
		publisher.publishEvent(new LoggerEvent(this,"No this game "+ name + " found"));
		return null;
	}

	@Override
	public List<Game> getAllGames() {
		return games;
	}
	
	@SuppressWarnings("unchecked")
	private void readGames() throws IOException, ClassNotFoundException {
		if (new File(PATH_TO_FILE).exists()){ 
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(new File(PATH_TO_FILE)));
			if(in!=null){
				this.games=(ArrayList<Game>) in.readObject();
				in.close();
				publisher.publishEvent(new LoggerEvent(this,"Readed " + games.size() + " games from file"));
			}
		}
	}

	private void writeGames() throws FileNotFoundException, IOException{
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File(PATH_TO_FILE)));
		out.writeObject(this.games);
		out.flush();
		out.close();
		publisher.publishEvent(new LoggerEvent(this,"Saved " + this.games.size() + " games to file "+ PATH_TO_FILE));
	}
	
	@Override
	public void deleteGame(Game game) {
	this.games.remove(game);
	try {
		writeGames();
	} catch (IOException e) {
		publisher.publishEvent(new LoggerEvent(this,"IOException "+ this.getClass()));
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
