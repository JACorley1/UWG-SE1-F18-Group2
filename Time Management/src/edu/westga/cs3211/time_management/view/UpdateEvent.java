package edu.westga.cs3211.time_management.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.EventDataValidator;
import edu.westga.cs3211.time_management.model.Visibility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UpdateEvent {

	@FXML
	private Label nameLabel;
	@FXML
	private Label startTimeLabel;

	@FXML
	private Label endTimeLabel;

	@FXML
	private Label locationLabel;

	@FXML
	private Label descriptionLabel;

	@FXML
	private TextField nameText;

	@FXML
	private DatePicker startTimeDate;

	@FXML
	private DatePicker endTimeDate;

	@FXML
	private TextField locationText;

	@FXML
	private TextField descriptionText;

	@FXML
	private ComboBox<Visibility> visibilityList;

	@FXML
	private Label visibilityLabel;

	private Calendar calendar;

	private Event selectedEvent;

	@FXML
	void updateEvent(ActionEvent event) {
		String errorText = "";
    	String name = this.nameText.getText();
    	if(!EventDataValidator.checkName(name)) {
    		errorText += "Name is invalid" + System.lineSeparator();
    	}
    	LocalDateTime startTime = LocalDateTime.of(this.startTimeDate.getValue(), LocalTime.of(9, 0));
    	LocalDateTime endTime = LocalDateTime.of(this.endTimeDate.getValue(), LocalTime.of(5, 0));
    	if(!EventDataValidator.checkStartTime(startTime)) {
    		errorText += "Start time is invalid" + System.lineSeparator();
    	}
    	else if(!EventDataValidator.checkStartTime(endTime)) {
    		errorText += "Start time is invalid" + System.lineSeparator();
    	}
    	if(!errorText.isEmpty()) {
    		this.displayErrorMessage(errorText);
    		return;
    	}
    	
    	String location = this.locationText.getText();
    	if(location == null) {
    		location = "";
    	}
    	String description = this.descriptionText.getText();
    	if(description == null) {
    		description = "";
    	}
    	Visibility visibility = this.visibilityList.getValue();
    	
    	//this.calendar.updateEvent(this.selectedEvent, name, startTime, endTime, location, description, visibility);
    	//TODO change to create new event and delete old event if confirmed
    	
    	List<Event> conflictingEvents = this.calendar.declareConflicts(this.selectedEvent);
    	
    	String eventText = this.selectedEvent.toStringFull();
    	String conflictText = "";
    	//for(Event currEvent : conflictingEvents) {
    	//	conflictText += currEvent.toString() + System.lineSeparator();
    	//}
    	for(int index = 1; index < conflictingEvents.size(); index ++) {
    		conflictText += conflictingEvents.get(index).toString() + System.lineSeparator();
    	}
    	String eventSummaryAndConflictText = "NEW EVENT DETAILS" + System.lineSeparator() + eventText + System.lineSeparator() + "CONFLICTING EVENTS" + conflictText;
		Alert alert = new Alert(AlertType.CONFIRMATION, eventSummaryAndConflictText);
		alert.setTitle("Create New Event?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		 if (result.isPresent() && result.get() == ButtonType.OK) {
			 this.calendar.updateEvent(this.selectedEvent, name, startTime, endTime, location, description, visibility);
			((Node)(event.getSource())).getScene().getWindow().hide();
		 }
	}

	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void initialize() {
		assert visibilityLabel != null : "fx:id=\"visibilityLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert locationText != null : "fx:id=\"locationText\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert startTimeDate != null : "fx:id=\"startTimeDate\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert descriptionText != null : "fx:id=\"descriptionText\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert endTimeDate != null : "fx:id=\"endTimeDate\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert endTimeLabel != null : "fx:id=\"endTimeLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert startTimeLabel != null : "fx:id=\"startTimeLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert visibilityList != null : "fx:id=\"visibilityList\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";

		this.visibilityList.setItems(FXCollections.observableArrayList());
		this.visibilityList.getItems().add(Visibility.PUBLIC);
		this.visibilityList.getItems().add(Visibility.PRIVATE);
		this.visibilityList.getItems().add(Visibility.FRIENDS_ONLY);
	}

	public void setCalendar(Calendar calendar) {
		if (calendar == null) {
			throw new IllegalArgumentException("Calendar provided was null");
		}
		this.calendar = calendar;
	}

	public void setSelectedEvent(Event event) {
		this.selectedEvent = event;
		this.nameText.setText(this.selectedEvent.getName());
		this.startTimeDate.setValue(this.selectedEvent.getStartTime().toLocalDate());
		this.endTimeDate.setValue(this.selectedEvent.getEndTime().toLocalDate());
		this.locationText.setText(this.selectedEvent.getLocation());
		this.descriptionText.setText(this.selectedEvent.getDescription());
		this.visibilityList.setValue(this.selectedEvent.getVisibility());

	}
	
	private void displayErrorMessage(String errorMessage) {
		Alert alert = new Alert(AlertType.ERROR, errorMessage);
		alert.showAndWait();
    }

}
