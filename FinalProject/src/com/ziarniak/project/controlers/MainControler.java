package com.ziarniak.project.controlers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ziarniak.project.config.Config;
import com.ziarniak.project.models.Game;
import com.ziarniak.project.models.GameType;
import com.ziarniak.project.service.GameService;
import com.ziarniak.project.service.GameTypeService;
import com.ziarniak.project.service.LoggerEvent;

public class MainControler implements Initializable{

	private GameService gameService;
	private GameTypeService gameTypeService;
	// Game Tab View
	
	@FXML private Button addGameButton;
	@FXML private Button deleteGameButton;
	@FXML private TextField gameId;
	@FXML private TextField gameName;
	@FXML private TextField gameMaker;
	@FXML private TextField gameType;
	@FXML private TextField gamePrice;
	@FXML private Label selectedRowLabelID;
	@FXML private Label selectedRowLabelName;
	@FXML private Label selectedRowLabelMaker;
	@FXML private Label selectedRowLabelType;
	@FXML private Label selectedRowLabelPrice;	
	@FXML private TableView<Game> gameTableView;
	@FXML private ComboBox<String> gameTypeComboBox;
	// Type game tab view
	

	
	@FXML private Button addTypeButton;
	@FXML private Button deleteTypeButton;
	@FXML private TextField typeName;
	@FXML private TableView<Game> typeTableView;
	@FXML private ComboBox<String> comboBoxGameTypeSearch;
	@FXML private ComboBox<String> comboBoxGameTypeDelete;
	@FXML private Stage stage;
	// forms
		
	@FXML private TabPane mainPane;
	@Autowired private ApplicationEventPublisher publisher;
	final ObservableList<Game> data=FXCollections.observableArrayList();
	final ObservableList<Game> dataOnTypeTabView=FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		 initNullErrors();
		 @SuppressWarnings("resource")
		AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext();
		context.getEnvironment().setActiveProfiles("postgres");
		/*context.getEnvironment().setActiveProfiles("file");
	    System.setProperty("spring.profiles.active", "file");
	    System.setProperty("spring.profiles.active", "file");
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");*/
		context.register(Config.class);
		context.refresh();
		typeName.setText("zosia");
		 gameService=context.getBean(GameService.class);
		 gameTypeService=context.getBean(GameTypeService.class);
		
		 refreshData();
		 initDataPresent();
	     

		gameTableView.setOnMouseClicked(new OnClick());
		comboBoxGameTypeSearch.valueProperty().addListener(new ComboBoxGameTypeSearchListener());
	    }
	
	
	private class ComboBoxGameTypeSearchListener implements ChangeListener<String>{

		@Override
		public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			dataOnTypeTabView.clear();
			dataOnTypeTabView.addAll(gameTypeService.getAllGamesOfType(comboBoxGameTypeSearch.getSelectionModel().getSelectedItem()));
			refreshTableGameOfType();
			typeTableView.setItems(dataOnTypeTabView);
		}
		
		
	}
	
	
	private class OnClick implements EventHandler<Event>{

		@Override
		public void handle(Event event) {
			if (!gameTableView.getSelectionModel().isEmpty()){
				selected();
			}
			
		}
		
		
	}
	@FXML protected void deleteGameHandler(ActionEvent event){	
				
				if(!gameTableView.getSelectionModel().isEmpty()){
					Game selectedGame=selected();
					mainPane.setDisable(true);
					DialogResponse response = Dialogs.showConfirmDialog(stage, 
						    "Do you want to continue?", "Confirm  deleting of Game", "Info");
					if(response==DialogResponse.YES){
					gameService.deleteGame(selectedGame);
					data.remove(selectedGame);		
					gameTableView.setItems(data);}
					
					mainPane.setDisable(false);
					
				}	
	}
	
	@FXML protected void addTypeHandler(ActionEvent event){		
				GameType gameType=new GameType();
				gameType.setName(typeName.getText());
				gameTypeService.addGameType(gameType);	
				 refreshData();
	}
	
	@FXML protected void deleteTypeHandler(ActionEvent event){
		mainPane.setDisable(true);
	
		DialogResponse response = Dialogs.showConfirmDialog(stage, 
			    "Do you want to continue?", "Confirm deleting of GameType", "Info");
		if(response==DialogResponse.YES){
				gameTypeService.deleteGameType(comboBoxGameTypeDelete.getSelectionModel().getSelectedItem());	
				 refreshData();
				 
		}
		mainPane.setDisable(false);
		
	}
	
	
	@FXML protected void addGameHandler(ActionEvent event) {

		try {
			Game game = new Game(gameName.getText(), gameMaker.getText(),
					gameTypeComboBox.getSelectionModel().getSelectedItem(),
				gamePrice.getText());
			
			gameService.addGame(game);
			data.clear();
			data.addAll(gameService.getAllGames());
			gameTableView.setItems(data);
		}catch(ConstraintViolationException e){			
			mainPane.setDisable(true);	
			String temp="";
			for(ConstraintViolation<?> cons:e.getConstraintViolations()){
				temp+=cons.getMessage()+"\n";	
	        }
			DialogResponse response= Dialogs.showErrorDialog(stage, temp, "Error Dialog", "Game add Error");
			if(response==DialogResponse.OK){
				mainPane.setDisable(false);
			}
			
		} catch (NumberFormatException | IOException e ) {
			publisher.publishEvent(new LoggerEvent(this,e.getStackTrace().toString()));
		}

	}
	
	
	@FXML protected void addingModeSelected(){
		addTypeButton.setDisable(false);
		typeName.setDisable(false);
		deleteTypeButton.setDisable(true);
		comboBoxGameTypeDelete.setDisable(true);
		comboBoxGameTypeSearch.setDisable(true);
	}
	
	@FXML protected void deletingModeSelected(){
		addTypeButton.setDisable(true);
		typeName.setDisable(true);
		deleteTypeButton.setDisable(false);
		comboBoxGameTypeDelete.setDisable(false);
		comboBoxGameTypeSearch.setDisable(true);
		
	}
	
	
	@FXML protected void searchingModeSelected(){
		addTypeButton.setDisable(true);
		typeName.setDisable(true);
		deleteTypeButton.setDisable(true);
		comboBoxGameTypeDelete.setDisable(true);
		comboBoxGameTypeSearch.setDisable(false);
	}
	
	public void refreshData(){
		gameTypeComboBox.getItems().clear();
		comboBoxGameTypeSearch.getItems().clear();
		comboBoxGameTypeDelete.getItems().clear();
		
		for(GameType gameType:gameTypeService.getAllGameType()){
			  gameTypeComboBox.getItems().add(gameType.getName());
			  comboBoxGameTypeSearch.getItems().add(gameType.getName());
			  comboBoxGameTypeDelete.getItems().add(gameType.getName());
		 }
		
	}
	
	

	public void refreshTableGameOfType() {

		int i = 0;
		for (TableColumn tc : typeTableView.getColumns()) {
			tc.setCellValueFactory(new PropertyValueFactory<>(new Game()
					.getFields()[i]));
			i++;
		}
	}
	
	
	public void initDataPresent(){
		
		for (Game gam : gameService.getAllGames()) {
			data.add(gam);
		}
		int i = 0;
		for (TableColumn tc : gameTableView.getColumns()) {
			tc.setCellValueFactory(new PropertyValueFactory<>(new Game()
					.getFields()[i]));
			i++;
		}

		gameTableView.setItems(data); 
		  
	
	}
	
	
	
	public Game selected(){
		
		Game game = (Game)gameTableView.getSelectionModel().getSelectedItem();
		if(game!=null){	
			selectedRowLabelID.setText(String.valueOf(game.getId()));
			selectedRowLabelName.setText(game.getName());
			selectedRowLabelMaker.setText(game.getMaker());
			selectedRowLabelType.setText(game.getType());
			selectedRowLabelPrice.setText(String.valueOf(game.getPrice()));
		}
		
		return game;
	}

	public void initNullErrors(){
		
		
		assert addTypeButton !=null:"fx:id=\"addTypeButton\" was not injected: check your FXML file 'Test.fxml'.";
		assert gameTypeComboBox != null:"fx:id=\"gameTypeComboBox\" was not injected: check your FXML file 'Test.fxml'.";
		assert addGameButton != null : "fx:id=\"myTestButton\" was not injected: check your FXML file 'Test.fxml'.";
		assert deleteGameButton != null : "fx:id=\"deleteGameButton\" was not injected: check your FXML file 'Test.fxml'.";
        assert gameId !=null: "fx:id=\"gameId\" was not injected: check your FXML file 'Test.fxml'.";
        assert gameName !=null: "fx:id=\"gameName\" was not injected: check your FXML file 'Test.fxml'.";
        assert gameMaker !=null: "fx:id=\"gameMaker\" was not injected: check your FXML file 'Test.fxml'.";
        assert gameType !=null: "fx:id=\"gameType\" was not injected: check your FXML file 'Test.fxml'.";
        assert gamePrice !=null: "fx:id=\"gamePrice\" was not injected: check your FXML file 'Test.fxml'.";
        assert gameTableView !=null: "fx:id=\"tableView\" was not injected: check your FXML file 'Test.fxml'.";
		
	}
	
	
}
