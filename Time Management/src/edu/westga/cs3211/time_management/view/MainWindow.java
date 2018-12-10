package edu.westga.cs3211.time_management.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.westga.cs3211.time_management.Main;
import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Codebehind for the MainWindow Scene.
 * 
 * @author Jonathan Corley
 */
public class MainWindow {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ListView<Event> eventList;
    @FXML private TextArea eventDetailsText;
    
    private Calendar calendar;

    @FXML
    void addEvent(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("view/AddEvent.fxml"));
    	loader.load();
    	Parent parent= loader.getRoot();
    	Scene scene= new Scene(parent);
    	Stage addEventStage= new Stage();
    	addEventStage.setTitle("Add New Event");
    	addEventStage.setScene(scene);
    	addEventStage.initModality(Modality.APPLICATION_MODAL);
    	AddEvent addEventDialog = loader.getController();
    	addEventDialog.setCalendar(this.calendar);
    	addEventStage.showAndWait();

        this.eventList.setItems(FXCollections.observableArrayList(this.calendar.getEvents()));
    }
    
    @FXML
    void updateEvent(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	Event eventSelected = this.eventList.getSelectionModel().getSelectedItem();
    	loader.setLocation(Main.class.getResource("view/UpdateEvent.fxml"));
    	loader.load();
    	Parent parent= loader.getRoot();
    	Scene scene= new Scene(parent);
    	Stage updateEventStage= new Stage();
    	updateEventStage.setTitle("Update Event");
    	updateEventStage.setScene(scene);
    	updateEventStage.initModality(Modality.APPLICATION_MODAL);
    	UpdateEvent updateEventDialog = loader.getController();
    	updateEventDialog.setCalendar(this.calendar);
    	updateEventDialog.setSelectedEvent(eventSelected);
    	updateEventStage.showAndWait();

        this.eventList.setItems(FXCollections.observableArrayList(this.calendar.getEvents()));
    }
    
    @FXML
    void removeEvent(ActionEvent event) {
    	Event eventSelected = this.eventList.getSelectionModel().getSelectedItem();
    	if (eventSelected != null) {
    		Event selectedEvent = this.calendar.getEvent(eventSelected);
    		String eventSummary = "Event" + System.lineSeparator() + selectedEvent.toStringFull();
    		Alert alert = new Alert(AlertType.CONFIRMATION, eventSummary, ButtonType.OK, ButtonType.CANCEL);
    		alert.setTitle("Remove Event?");
    		
    		Optional<ButtonType> result = alert.showAndWait();
    		
    		if (result.isPresent() && result.get() == ButtonType.OK) {
    			this.calendar.removeEvent(eventSelected);
        		this.eventList.setItems(FXCollections.observableArrayList(this.calendar.getEvents()));
        		this.eventDetailsText.setText("");
    		}
    	}

    }
    
    @FXML
    void selectEvent(MouseEvent event) {
    	Event eventSelected = this.eventList.getSelectionModel().getSelectedItem();
    	if(eventSelected != null) {
    		this.eventDetailsText.setText(eventSelected.toStringFull());
    	}
    }

    @FXML
    void initialize() {
        assert eventList != null : "fx:id=\"eventList\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert eventDetailsText != null : "fx:id=\"eventDetailsText\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.calendar = new Calendar();
        this.eventList.setItems(FXCollections.observableArrayList(this.calendar.getEvents()));
    }
}

