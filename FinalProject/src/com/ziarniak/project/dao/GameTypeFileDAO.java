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
import com.ziarniak.project.models.GameType;
import com.ziarniak.project.service.LoggerEvent;

public class GameTypeFileDAO implements GameTypeDAO{

	@Autowired private ApplicationEventPublisher publisher;
	private final static String PATH_TO_FILE="gamesType.txt";
	private ArrayList<GameType> gameTypes=new ArrayList<GameType>();
	
	
	@PostConstruct
	public void initialize() throws ClassNotFoundException, IOException{
		this.readGameTypes();
	}
	
	@Override
	public GameType getGameType(String name) {
		for(GameType gameType:gameTypes){
			if(gameType.getName().equalsIgnoreCase(name))
				return gameType;
		}
		publisher.publishEvent(new LoggerEvent(this,"No this gameType "+ name + " found"));
		return null;
	}

	@Override
	public List<GameType> getAllGameType() {
		return this.gameTypes;
	}

	@Override
	public List<Game> getAllGamesOfType(String name) {
		
		//TODO: pobrac dane z serwisu GameDAO
		
		return null;
	}

	@Override
	public void deleteGameType(String gameTypeName) {
		for(GameType gameType:gameTypes){
			if(gameType.getName().equalsIgnoreCase(gameTypeName))
				this.gameTypes.remove(gameType);
		}
		try {
			this.writeGameTypes();
		} catch (IOException e) {
			publisher.publishEvent(new LoggerEvent(this,"IOException "+ this.getClass()));
		}
	}

	@Override
	public void addGameType(GameType gameType) {
		if (gameType!=null)  {
			gameType.setId(Long.valueOf(String.valueOf(this.gameTypes.size()+1)));
			this.gameTypes.add(gameType);
			try {
				this.writeGameTypes();
			} catch (IOException e) {
				publisher.publishEvent(new LoggerEvent(this,"Error : IOException gameType "+ gameType.getName() +" not saved"));
			}
			 publisher.publishEvent(new LoggerEvent(this,"Game " + gameType.toString() + " saved"));
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void readGameTypes() throws IOException, ClassNotFoundException {
		if (new File(PATH_TO_FILE).exists()){ 
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(new File(PATH_TO_FILE)));
			if(in!=null)
			this.gameTypes=(ArrayList<GameType>) in.readObject();
			in.close();
			publisher.publishEvent(new LoggerEvent(this,"Readed " + gameTypes.size() + " gamesType from file"));
		}
	}

	private void writeGameTypes() throws FileNotFoundException, IOException{
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File(PATH_TO_FILE)));
		out.writeObject(this.gameTypes);
		out.flush();
		out.close();
		publisher.publishEvent(new LoggerEvent(this,"Saved " + this.gameTypes.size() + " games to file "+ PATH_TO_FILE));
	}

	@Override
	public boolean isGameTypeExist(String gameTypeName) {
		if(this.getGameType(gameTypeName)==null){
			return false;
		}
		return true;
	}
	

}

