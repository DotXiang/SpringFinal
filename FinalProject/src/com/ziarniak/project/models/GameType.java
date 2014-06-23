package com.ziarniak.project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GameType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private ArrayList<Game>listOfGames;
	
	
	public GameType() {
		super();
	}
	public GameType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public GameType(Long id, String name, ArrayList<Game> listOfGames) {
		super();
		this.id = id;
		this.name = name;
		this.listOfGames = listOfGames;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Game> getListOfGames() {
		return listOfGames;
	}
	public void setListOfGames(List<Game> list) {
		this.listOfGames = (ArrayList<Game>) list;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "GameType [id=" + id + ", name=" + name + ", listOfGames="
				+ listOfGames + "]";
	}
	

	
	
}
