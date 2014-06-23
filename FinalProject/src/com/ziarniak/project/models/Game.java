package com.ziarniak.project.models;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.ziarniak.project.validation.Unique;

@Component
public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	@Unique(value = Game.class, property = "name")
	@Size(min=1, max=30)
	private String name;
	@Size(min=1, max=30)
	private String maker;
	@Size(min=1, max=30)
	private String type;
	@Digits(integer=3,fraction=2)
	private String price;
	
	
	public Game(){
	}
	
	
	public Game(String name, String maker, String type, String price) {
		super();
		this.name = name;
		this.maker = maker;
		this.type = type;
		this.price = price;
	}


	public String[] getFields(){
		return new String[]{"id","name","maker","type","price"};
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", maker=" + maker
				+ ", type=" + type + ", price=" + price + "]";
	}
	
	
}
